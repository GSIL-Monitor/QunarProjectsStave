package com.qunar.fintech.plat.admin.newmarketing.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qunar.fintech.marketing.api.admin.model.ActivityQueryDto;
import com.qunar.fintech.marketing.api.admin.model.CouponQueryDto;
import com.qunar.fintech.marketing.api.admin.model.TblActivityDto;
import com.qunar.fintech.marketing.api.admin.model.TblCouponDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.AccountAlarmDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.CouponDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.service.FileUploadService;
import com.qunar.fintech.plat.admin.system.dao.entity.ReviewInfo;
import com.qunar.fintech.plat.admin.newmarketing.dto.AccountAlarmQueryReq;
import com.qunar.fintech.plat.admin.newmarketing.dto.CouponQueryReq;
import com.qunar.fintech.plat.admin.newmarketing.facade.MarketingFacadeProxy;
import com.qunar.fintech.plat.admin.newmarketing.constants.CouponConstants;
import com.qunar.fintech.plat.admin.newmarketing.service.AccountAlarmService;
import com.qunar.fintech.plat.admin.newmarketing.service.MarketingCouponService;
import com.qunar.fintech.plat.admin.newmarketing.service.MarketingReviewService;
import com.qunar.fintech.plat.admin.newmarketing.service.ParamValidate;
import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author qun.shi
 * @since 2019-01-30 1:31 AM
 */
@Service
public class MarketingCouponServiceImpl implements MarketingCouponService {

    /**
     * 查询营销所有的优惠券
     * 1、调营销查询活动信息、券信息 2、调账务查询报警配置 3、组装优惠券信息，按条件过过滤
     */
    @Override
    public List<CouponDetailDto> getCouponList(CouponQueryReq couponQueryReq){

        // 查询所有的活动
        List<TblActivityDto> allActivity = marketingFacadeProxy.queryAllActivity();
        Set<String> activityCodes = Sets.newHashSet();
        for (TblActivityDto activityDto:allActivity) {
            activityCodes.add(activityDto.getActivityCode());
        }

        // 查询所有的优惠券
        List<TblCouponDto> allCoupon = Lists.newArrayList();
        for(String activityCode:activityCodes) {
            allCoupon.addAll(marketingFacadeProxy.queryAllCouponInfo(activityCode));
        }

        // 组装信息= 优惠券信息+活动信息+报警信息
        List<CouponDetailDto> couponDetailDtos = assemCouponInfoList(allActivity,allCoupon);

        // 按条件过滤券信息
        List<CouponDetailDto> filteredCouponDetailDtos = filterCoupon(couponDetailDtos,couponQueryReq);

        return filteredCouponDetailDtos;
    }

    /**
     * 创建优惠券：
     * 1、调营销创建活动信息 2、调营销创建优惠券信息 3、调账务创建报警配置
     */
    @Override
    public void add(CouponDetailDto couponDetailDto) {
        logger.info("MarketingCouponServiceImpl#addCoupon couponDetailDto={}", couponDetailDto);

        // 创建活动
        paramValidate.checkActivityConfig(couponDetailDto);
        TblActivityDto tblActivityDto = CouponDetailDto.buildTblActivityDto(couponDetailDto);
        marketingFacadeProxy.addActivity(tblActivityDto);

        // 查询活动获得id
        Long activityId = null;
        List<TblActivityDto> tblActivityDtos = marketingFacadeProxy.queryAllActivity();
        for (TblActivityDto dbActivity:tblActivityDtos){
            if(dbActivity.getActivityCode().equals(tblActivityDto.getActivityCode())){
                activityId = dbActivity.getId();
            }
        }

        // 创建券
        paramValidate.checkCouponConfig(couponDetailDto);
        TblCouponDto couponDto = CouponDetailDto.buildTblCouponDto(couponDetailDto);
        marketingFacadeProxy.addCoupon(couponDto);

        // 配置报警
        couponDetailDto.setActivityId(String.valueOf(activityId));
        AccountAlarmDetailDto accountAlarmQueryReq = AccountAlarmDetailDto.buildAccountAlarmDto(couponDetailDto);
        accountAlarmQueryReq.setOperType(CouponConstants.CREATE_ACCOUNT_ALARM_OPER_TYPE);
        accountAlarmService.addActivityAmtAlarmConfig(accountAlarmQueryReq);

        // 建立容器，保存此券code的发券文件
        fileUploadService.createContainer(couponDto.getCouponCode());
    }

    /**
     * 更新优惠券
     * 1、调营销更新活动信息 2、调营销更新优惠券信息 3、调账务更新报警配置
     */
    @Override
    public void update(CouponDetailDto couponDetailDto){
        // 更新活动信息
        paramValidate.checkActivityConfig(couponDetailDto);
        TblActivityDto tblActivityDto = CouponDetailDto.buildTblActivityDto(couponDetailDto);
        marketingFacadeProxy.updateActivity(tblActivityDto);

        // 更新券信息
        paramValidate.checkCouponConfig(couponDetailDto);
        TblCouponDto couponDto = CouponDetailDto.buildTblCouponDto(couponDetailDto);
        marketingFacadeProxy.updateCoupon(couponDto);

        // 更新账务报警配置
        AccountAlarmDetailDto accountAlarmQueryReq = AccountAlarmDetailDto.buildAccountAlarmDto(couponDetailDto);
        accountAlarmQueryReq.setOperType(CouponConstants.UPDATE_ACCOUNT_ALARM_OPER_TYPE);
        accountAlarmService.updateActivityAmtAlarmConfig(accountAlarmQueryReq);
    }

    /**
     * 查询优惠券详情
     * 1、调营销查询活动信息 2、调营销查询优惠券信息 3、调账务查询报警配置
     */
    @Override
    public CouponDetailDto queryDetail(CouponDetailDto req){
        ParamChecker.notNull(req.getActivityId(),"活动id不能为空！");
        ParamChecker.notNull(req.getCouponId(),"券id不能为空！");

        // 查询活动信息
        ActivityQueryDto activityQueryDto = new ActivityQueryDto();
        activityQueryDto.setId(Long.valueOf(req.getActivityId()));
        TblActivityDto activityDto = marketingFacadeProxy.queryActivityById(activityQueryDto);

        // 查询券信息
        TblCouponDto couponDto = marketingFacadeProxy.queryCouponInfoById(Long.valueOf(req.getCouponId()));

        // 查询报警信息
        AccountAlarmQueryReq accountAlarmQueryReq = new AccountAlarmQueryReq();
        accountAlarmQueryReq.setActivityId(Long.valueOf(req.getActivityId()));
        accountAlarmQueryReq.setCustomerNo(activityDto.getMerchantCode());
        AccountAlarmDetailDto alarmDto = accountAlarmService.queryActivityAmtAlarmConfig(accountAlarmQueryReq);

        // 查询辅助信息
        ReviewInfo reviewInfo = marketingReviewService.queryLastPassedReviewInfoByKey(req.getCouponCode());

        // 组装优惠券信息
        CouponDetailDto resp = CouponDetailDto.buildCouponInfoDto(activityDto, couponDto, alarmDto,reviewInfo);
        return resp;
    }

    /**
     * 按条件过滤券信息
     */
    private List<CouponDetailDto> filterCoupon(List<CouponDetailDto> couponDetailDtos, CouponQueryReq couponQueryReq){
        List<CouponDetailDto> filteredCouponDetailDtos = Lists.newArrayList();
        for (CouponDetailDto couponDetailDto : couponDetailDtos) {
            if(couponQueryReq.match(couponDetailDto)){
                filteredCouponDetailDtos.add(couponDetailDto);
            }
        }
        return filteredCouponDetailDtos;
    }

    /**
     * 组装优惠券信息：活动信息+券信息+报警信息
     */
    private List<CouponDetailDto> assemCouponInfoList(List<TblActivityDto> allActivity,
                                                      List<TblCouponDto> allCoupon){

        // key：activityCode，value：TblActivityDto
        Map<String,TblActivityDto> activityCodeMap = Maps.newHashMap();
        for (TblActivityDto tblActivityDto:allActivity) {
            activityCodeMap.put(tblActivityDto.getActivityCode(),tblActivityDto);
        }

        List<CouponDetailDto> couponDetailDtos = Lists.newArrayList();
        for (TblCouponDto couponDto:allCoupon) {

            // 活动信息
            TblActivityDto activityDto = activityCodeMap.get(couponDto.getActivityCode());

            // 报警信息
            CouponDetailDto couponDetailDto = new CouponDetailDto();
            couponDetailDto.setActivityAccountName(activityDto.getMerchantCode());
            couponDetailDto.setActivityId(String.valueOf(activityDto.getId()));
            AccountAlarmDetailDto alarmDto = queryAccountAlarmConfig(couponDetailDto);

            // 查询辅助信息
            ReviewInfo reviewInfo = marketingReviewService.
                    queryLastPassedReviewInfoByKey(couponDto.getCouponCode());

            CouponDetailDto resp = CouponDetailDto.
                    buildCouponInfoDto(activityDto, couponDto,alarmDto,reviewInfo);
            couponDetailDtos.add(resp);
        }
        return couponDetailDtos;
    }

    /**
     * 查询账务报警配置
     */
    private AccountAlarmDetailDto queryAccountAlarmConfig(CouponDetailDto couponDetailDto){
        ParamChecker.notNull(couponDetailDto.getActivityId(),"活动id不能为空！");
        ParamChecker.notNull(couponDetailDto.getActivityAccountName(),"营销账户名称不能为空！");

        AccountAlarmQueryReq accountAlarmQueryReq = new AccountAlarmQueryReq();
        accountAlarmQueryReq.setActivityId(Long.valueOf(couponDetailDto.getActivityId()));
        accountAlarmQueryReq.setCustomerNo(couponDetailDto.getActivityAccountName());
        AccountAlarmDetailDto alarmDto = accountAlarmService.queryActivityAmtAlarmConfig(accountAlarmQueryReq);
        return alarmDto;
    }

    @Resource
    private AccountAlarmService accountAlarmService;

    @Resource
    private ParamValidate paramValidate;

    @Resource
    private MarketingReviewService marketingReviewService;

    @Resource
    private MarketingFacadeProxy marketingFacadeProxy;

    @Resource
    FileUploadService fileUploadService;

    private static final Logger logger = LoggerFactory.getLogger(MarketingCouponServiceImpl.class);
}
