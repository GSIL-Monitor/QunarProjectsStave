package com.qunar.fintech.plat.admin.newmarketing.facade.impl;

import com.google.common.base.Splitter;
import com.google.common.io.CharStreams;
import com.qunar.fintech.marketing.api.admin.facade.ActivityFacade;
import com.qunar.fintech.marketing.api.admin.facade.CouponFacade;
import com.qunar.fintech.marketing.api.admin.model.ActivityQueryDto;
import com.qunar.fintech.marketing.api.admin.model.ActivityResponseDto;
import com.qunar.fintech.marketing.api.admin.model.CouponQueryDto;
import com.qunar.fintech.marketing.api.admin.model.CouponResponseDto;
import com.qunar.fintech.marketing.api.admin.model.TblActivityDto;
import com.qunar.fintech.marketing.api.admin.model.TblCouponDto;
import com.qunar.fintech.marketing.api.dto.GrantCouponReqDto;
import com.qunar.fintech.marketing.api.dto.GrantCouponRespDto;
import com.qunar.fintech.marketing.api.facade.MarketingFacade;
import com.qunar.fintech.marketing.api.model.CouponGrantReqInfo;
import com.qunar.fintech.marketing.api.model.UserInfo;
import com.qunar.fintech.monitor.core.Metrics;

import static com.qunar.fintech.plat.admin.newmarketing.exception.MarketingExceptionCode.*;

import com.qunar.fintech.plat.admin.newmarketing.dto.GrantCouponReq;
import com.qunar.fintech.plat.admin.newmarketing.facade.MarketingFacadeProxy;
import com.qunar.fintech.plat.admin.newmarketing.monitor.MetricsName;
import com.qunar.fintech.plat.admin.newmarketing.monitor.MonitorMertricsGroup;
import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import com.qunar.fintech.util.simple.BigDecimalUtil;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * @author qun.shi
 * @since 2019-01-31 1:01 AM
 */
@Service
public class MarketingFacadeProxyImpl implements MarketingFacadeProxy {

    /**
     * 发券
     */
    @Override
    public int grantCoupon(MultipartFile[] userInfoFiles, GrantCouponReq req){

        int count = 0;
        LOGGER.info("granCoupon - couponCode: {}", req.getCouponCode());
        for(MultipartFile source:userInfoFiles) {
            try {
                if (source != null) {
                    //读取文件存进集合中
                    List<String> stringList = CharStreams.readLines(new InputStreamReader(source.getInputStream()));
                    LOGGER.debug("addInterestFreeCoupon strlist: {}", stringList);
                    //计数器，如果执行过程失败记录下已经执行了多少条数据
                    for (int i = 1; i < stringList.size(); i++) {
                        //便利集合的每一个元素，把它用“，”分割成两部分
                        List<String> uidMobile = Splitter.on(",").splitToList(stringList.get(i));
                        String uid = uidMobile.get(0);
                        String mobile = uidMobile.get(1);
                        //封装实体
                        LOGGER.debug("addInterestFreeCoupon - grant param: {}, mobile: {}", uid, mobile);
                        GrantCouponReqDto gcr = buildGrantCouponReqDto(req, uid, mobile);
                        // 调用发券服务
                        QResponse<GrantCouponRespDto> qResponse = marketingFacade.grant(gcr);

                        //异常处理
                        if (!qResponse.isSuccess()) {
                            LOGGER.error("发券失败:{},uid:{},mobile:{}", qResponse.getReturnMsg(), uid, mobile);
                            continue;
                        }
                        count++;
                    }
                    LOGGER.info("addInterestFreeCoupon - end: {}", count);
                }
            } catch (IOException e) {
                LOGGER.error("updateRepaymentFile#update repayment schedule with file exception", e);
            } catch (IllegalStateException e) {
                LOGGER.error("addInterestFreeCoupon - err", e);
                throw e;
            } catch (Exception e) {
                LOGGER.error("addInterestFreeCoupon - err", e);
            }
        }
        return count;
    }

    /**
     * 根据活动id查询活动信息
     */
    @Override
    public TblActivityDto queryActivityById(ActivityQueryDto activityQueryDto) {
        LOGGER.info("开始查询营销活动信息，activityQueryDto={}",activityQueryDto);
        ParamChecker.notNull(activityQueryDto.getId(), "活动code不能为空！");

        QResponse<TblActivityDto> response = activityFacade.queryActivityById(activityQueryDto);
        if (response != null && (!response.isSuccess() || response.getData() == null)) {
            LOGGER.error("查询营销活动接口异常#queryActivityById");
            Metrics.sum(MonitorMertricsGroup.ACTIVITY_FAIL_COUNT, MetricsName.ACTIVITY_QUERY);
            throw new BusiException(MARKETING_ACTIVITY_QUERY_FAIL, response.getReturnMsg());
        }

        LOGGER.info("营销活动查询，response={}", response);
        return response.getData();
    }

    /**
     * 创建营销活动
     */
    @Override
    public void addActivity(TblActivityDto activityDto) {
        LOGGER.info("开始创建营销活动，tblActivityDto={}", activityDto);
        checkActivityConfig(activityDto);

        QResponse<ActivityResponseDto> response = activityFacade.addActivity(activityDto);
        if (response != null && !response.isSuccess()) {
            Metrics.sum(MonitorMertricsGroup.ACTIVITY_FAIL_COUNT, MetricsName.ACTIVITY_ADD);
            LOGGER.error("创建营销活动失败，msg={}", response.getReturnMsg());
            throw new BusiException(MARKETING_ACTIVITY_ADD_FAIL, response.getReturnMsg());
        }

        LOGGER.info("成功创建营销活动，response={}", response);
    }

    /**
     * 创建优惠券
     */
    @Override
    public void addCoupon(TblCouponDto couponDto) {
        LOGGER.info("开始创建营销优惠券，tblCouponDto={}", couponDto);
        checkCouponConfig(couponDto);

        QResponse<CouponResponseDto> response = couponFacade.addCouponInfo(couponDto);
        if (response != null && !response.isSuccess()) {
            Metrics.sum(MonitorMertricsGroup.COUPON_FAIL_COUNT, MetricsName.COUPON_ADD);
            throw new BusiException(MARKETING_COUPON_ADD_FAIL, response.getReturnMsg());
        }

        LOGGER.info("成功创建营销优惠券，response={}", response);
    }

    /**
     * 查询营销所有的活动
     */
    @Override
    public List<TblActivityDto> queryAllActivity() {
        LOGGER.info("开始查询ALL营销活动");
        QResponse<List<TblActivityDto>> response = activityFacade.queryAllActivity(new ActivityQueryDto());

        // 查询失败
        if (response != null && (!response.isSuccess() || response.getData() == null)) {
            LOGGER.error("查询ALL营销活动接口异常#queryAllActivity");
            Metrics.sum(MonitorMertricsGroup.ACTIVITY_FAIL_COUNT, MetricsName.ACTIVITY_QUERY);
            throw new BusiException(MARKETING_ACTIVITY_QUERY_FAIL, response.getReturnMsg());
        }

        LOGGER.info("成功创建营销优惠券，response={}", response);
        return response.getData();
    }

    /**
     * 查询营销所有的活动
     */
    @Override
    public List<TblCouponDto> queryAllCouponInfo(String activityCode) {
        LOGGER.info("开始查询营销ALL优惠券");
        CouponQueryDto couponQueryDto = new CouponQueryDto();
        couponQueryDto.setActivityCode(activityCode);
        QResponse<List<TblCouponDto>> response = couponFacade.queryAllCouponInfo(couponQueryDto);

        // 查询失败
        if (response == null || !response.isSuccess() || response.getData() == null) {
            LOGGER.error("查询营销ALL优惠券接口异常#queryAllActivity");
            Metrics.sum(MonitorMertricsGroup.COUPON_FAIL_COUNT, MetricsName.COUPON_QUERY);
            throw new BusiException(MARKETING_ACTIVITY_QUERY_FAIL, "查询营销ALL优惠券失败！");
        }

        LOGGER.info("成功创建营销ALL优惠券，response={}", response);
        return response.getData();
    }


    /**
     * 根据id查询优惠券信息
     */
    @Override
    public TblCouponDto queryCouponInfoById(long id){
        LOGGER.info("开始查询营销优惠券，id={}",id);
        CouponQueryDto queryDto = new CouponQueryDto();
        queryDto.setId(id);
        QResponse<TblCouponDto> response = couponFacade.queryCouponInfoById(queryDto);

        // 查询失败
        if (response == null || !response.isSuccess() || response.getData() == null) {
            LOGGER.error("查询营销优惠券接口异常#queryAllActivity");
            Metrics.sum(MonitorMertricsGroup.COUPON_FAIL_COUNT, MetricsName.COUPON_QUERY);
            throw new BusiException(MARKETING_COUPON_QUERY_FAIL, "查询营销优惠券失败！");
        }

        LOGGER.info("成功创建营销优惠券，response={}", response);
        return response.getData();
    }

    /**
     * 更新券信息
     */
    @Override
    public void updateCoupon(TblCouponDto couponDto) {
        LOGGER.info("开始更新营销活动信息");
        checkCouponConfig(couponDto);
        QResponse<CouponResponseDto> response = couponFacade.updateCouponInfo(couponDto);

        // 查询失败
        if (response == null || !response.isSuccess() || response.getData() == null) {
            LOGGER.error("更新营销券信息异常#queryAllActivity");
            Metrics.sum(MonitorMertricsGroup.COUPON_FAIL_COUNT, MetricsName.COUPON_UPDATE);
            throw new BusiException(MARKETING_COUPON_UPDATE_FAIL, response.getReturnMsg());
        }

        LOGGER.info("成功更新营销券信息，response={}", response);
    }

    /**
     * 更新营销活动
     */
    @Override
    public void updateActivity(TblActivityDto activityDto) {
        LOGGER.info("开始更新营销活动信息");
        QResponse<ActivityResponseDto> response = activityFacade.updateActivity(activityDto);

        // 查询失败
        if (response != null && (!response.isSuccess() || response.getData() == null)) {
            LOGGER.error("更新营销活动信息异常#queryAllActivity");
            Metrics.sum(MonitorMertricsGroup.ACTIVITY_FAIL_COUNT, MetricsName.ACTIVITY_UPDATE);
            throw new BusiException(MARKETING_ACTIVITY_UPDATE_FAIL, response.getReturnMsg());
        }

        LOGGER.info("成功更新营销活动信息，response={}", response);
    }

    /**
     * check 活动的配置信息
     */
    private void checkActivityConfig(TblActivityDto activityDto) {
        ParamChecker.notNull(activityDto.getActivityCode(), "活动code不能为空！");
        ParamChecker.notNull(activityDto.getActivityName(), "活动名称不能为空！");
        ParamChecker.notNull(activityDto.getActivityDesc(), "活动描述不能为空！");
        ParamChecker.notNull(activityDto.getSwitchStatus(), "活动状态不能为空！");
        ParamChecker.notNull(BigDecimalUtil.notLessThanZero(activityDto.getTotalAmt()), "活动总金额不能为空！");
        ParamChecker.notNull(activityDto.getMerchantCode(), "活动账户名称不能为空！");

        ParamChecker.notNull(activityDto.getStartTime(), "活动开始时间不能为空！");
        ParamChecker.notNull(activityDto.getEndTime(), "活动结束时间不能为空！");
        ParamChecker.isTrue(activityDto.getStartTime().compareTo(activityDto.getEndTime()) <= 0, "活动开始时间不能大于等于结束时间");
    }

    /**
     * check 券的配置信息
     */
    private void checkCouponConfig(TblCouponDto couponDto) {
        ParamChecker.notNull(couponDto.getCouponCode(), "优惠券code不能为空！");
        ParamChecker.notNull(couponDto.getCouponName(), "优惠券名称不能为空！");
        ParamChecker.notNull(couponDto.getStartTime(), "优惠券开始时间不能为空！");
        ParamChecker.notNull(couponDto.getEndTime(), "优惠券结束时间不能为空！");
        ParamChecker.notNull(couponDto.getTotalNum(), "优惠券总数量不能为空！");
        ParamChecker.notNull(couponDto.getTotalAmt(), "优惠券总金额不能为空！");
        ParamChecker.notNull(couponDto.getCouponDimon(), "优惠券维度不能为空！");
        ParamChecker.notNull(couponDto.getCouponScene(), "优惠券场景不能为空！");
        ParamChecker.notNull(couponDto.getCouponType(), "优惠券类型不能为空！");
        ParamChecker.notNull(couponDto.getExt(), "优惠券扩展信息不能为空！");
        ParamChecker.notNull(couponDto.getRuleParams(), "优惠券规则信息不能为空！");
        ParamChecker.notNull(couponDto.getUserPercept(), "优惠券感知字段不能为空！");
        ParamChecker.notNull(couponDto.getInstructions(), "优惠券说明不能为空！");
        ParamChecker.notNull(couponDto.getActivityCode(), "活动code不能为空！");
    }

    /**
     * 组装GrantCouponReqDto
     */
    private static GrantCouponReqDto buildGrantCouponReqDto(GrantCouponReq req, String uid, String mobile){
        GrantCouponReqDto reqDto = new GrantCouponReqDto();
        reqDto.setOrgChannel(req.getOrgChannel());
        reqDto.setProductNo(req.getProductNo());
        List<CouponGrantReqInfo> list = new ArrayList<>();
        CouponGrantReqInfo cgr = new CouponGrantReqInfo();
        cgr.setCouponCode(req.getCouponCode());
        cgr.setCouponAmount(req.getCouponAmount());
        list.add(cgr);
        reqDto.setCouponGrantReqInfos(list);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(uid);
        userInfo.setMobile(mobile);
        reqDto.setUserInfo(userInfo);
        return reqDto;
    }

    @Resource
    ActivityFacade activityFacade;

    @Resource
    CouponFacade couponFacade;

    @Resource
    private MarketingFacade marketingFacade;

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketingFacadeProxyImpl.class);
}
