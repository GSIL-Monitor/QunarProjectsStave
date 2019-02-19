package com.qunar.fintech.plat.admin.newmarketing.controller;

import com.qunar.fintech.marketing.api.admin.facade.ActivityFacade;
import com.qunar.fintech.marketing.api.admin.model.ActivityAccountDto;
import com.qunar.fintech.marketing.api.admin.model.ActivityQueryDto;
import com.qunar.fintech.marketing.api.admin.model.TblActivityDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.CouponDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.GrantCouponReq;
import com.qunar.fintech.plat.admin.newmarketing.service.FileUploadService;
import com.qunar.fintech.plat.admin.newmarketing.service.GrantCouponService;
import com.qunar.fintech.plat.admin.query.enums.SwitchEnum;
import com.qunar.fintech.plat.admin.support.exception.MarketErrorCodes;
import com.qunar.fintech.plat.admin.system.dao.entity.ReviewInfo;
import com.qunar.fintech.plat.admin.newmarketing.dto.CouponQueryReq;
import com.qunar.fintech.plat.admin.newmarketing.facade.MarketingFacadeProxy;
import com.qunar.fintech.plat.admin.newmarketing.service.MarketingCouponService;
import com.qunar.fintech.plat.admin.newmarketing.service.MarketingReviewService;
import com.qunar.fintech.plat.admin.newmarketing.service.ParamValidate;
import com.qunar.fintech.plat.admin.support.security.AccessLog;
import com.qunar.fintech.plat.admin.support.util.JsonUtil;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import com.qunar.pay.g2.api.account.dto.AccountQueryParam;
import com.qunar.pay.g2.api.account.dto.AccountQueryResultDto;
import com.qunar.pay.g2.api.account.service.MerchantAccountQueryFacade;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author qun.shi
 * @since 2019-01-24 11:29 AM
 */
@RequestMapping("/newmarketing/coupon")
@Controller
public class MarketingCouponController {
    /**
     * 创建或更新优惠券
     */
    @AccessLog("新增优惠券")
    @RequiresPermissions("market:coupon:manage")
    @ResponseBody
    @PostMapping("/addOrUpdateCoupon")
    R addOrUpdateCoupon(@RequestParam(value = "userInfoFile", required = false) MultipartFile userInfoFile, CouponDetailDto req) {
        try {
            ParamChecker.notNull(req,"优惠券信息不能为空！");
            ParamChecker.notBlank(req.getCouponCode(),"券号不能为空！");
            ParamChecker.notBlank(req.getReviewNo(),"审核流水号不能为空！");
            ParamChecker.notBlank(req.getRequestMenuId(),"菜单id不能为空！");

            // check 活动和券的配置
            paramValidate.checkActivityConfig(req);
            paramValidate.checkActivityConfig(req);

            // 创券：已发金额和数量为零,  活动剩余金额和总金额一致
            req.setCouponGrantedAmt("0");
            req.setCouponGrantedNum("0");
            req.setActivityRemainAmt(req.getActivityTotalAmt());
            req.setCouponSwitchStatus(SwitchEnum.OPEN.getCode().toString());

            ReviewInfo reviewInfo = ReviewInfo.buildReviewInfo(req);
            marketingReviewService.add(userInfoFile, reviewInfo);
        } catch (Exception e) {
            logger.error("提交审核失败, couponDetailDto={},msg={}", req, e.getMessage());
            return R.error(e.getMessage());
        }
        return R.ok("提交审核成功，等待审核！");
    }

    /**
     * 查询优惠券详情
     */
    @AccessLog("查询优惠券详情")
    @RequiresPermissions("market:coupon:manage")
    @ResponseBody
    @PostMapping("/detail")
    R getCouponDetail(CouponDetailDto couponDetailDto) {
        try {
            CouponDetailDto resp = marketingCouponService.queryDetail(couponDetailDto);
            return R.ok().put("data", JsonUtil.toJson(resp));
        } catch (Exception e) {
            logger.error("获取优惠券详情失败, couponDetailDto={},msg={}", couponDetailDto, e.getMessage());
            return R.error(e.getMessage());
        }
    }

    /**
     * 查询营销的所有优惠券信息
     */
    @AccessLog("查询ALL优惠券")
    @RequiresPermissions("market:coupon:manage")
    @ResponseBody
    @PostMapping("/getAllCouponByCondition")
    R getAllCouponByCondition(CouponQueryReq couponQueryReq) {
        try {
            List<CouponDetailDto> resp = marketingCouponService.getCouponList(couponQueryReq);
            return R.ok().put("data", resp.toArray());
        } catch (Exception e) {
            logger.error("查询ALL优惠券失败, couponDetailDto={},msg={}", couponQueryReq, e.getMessage());
            return R.error(e.getMessage());
        }
    }

    /**
     * 更改活动状态达到，开关活动
     */
    @AccessLog("关闭或者开启活动")
    @RequiresPermissions("market:coupon:manage")
    @ResponseBody
    @PostMapping("/alterCouponSwitchStatus")
    R alterCouponSwitchStatus(CouponDetailDto couponDetailDto) {
        try {
            ParamChecker.notBlank(couponDetailDto.getActivityId(),"活动id不能为空！");
            ParamChecker.notBlank(couponDetailDto.getCouponSwitchStatus(),"活动状态不能为空！");

            ActivityQueryDto activityQueryDto = new ActivityQueryDto();
            activityQueryDto.setId(Long.valueOf(couponDetailDto.getActivityId()));
            TblActivityDto dbActivityDto = marketingFacadeProxy.queryActivityById(activityQueryDto);

            TblActivityDto upActivityDto = CouponDetailDto.buildTblActivityDto(couponDetailDto);
            upActivityDto.setId(Long.valueOf(couponDetailDto.getActivityId()));
            upActivityDto.setStartTime(dbActivityDto.getStartTime());
            upActivityDto.setEndTime(dbActivityDto.getEndTime());

            marketingFacadeProxy.updateActivity(upActivityDto);
        } catch (Exception e) {
            logger.error("更新活动失败, couponDetailDto={},msg={}", couponDetailDto, e.getMessage());
            return R.error(e.getMessage());
        }
        return R.ok("更新活动成功");
    }

    @AccessLog("发券")
    @RequiresPermissions("market:coupon:manage")
    @ResponseBody
    @PostMapping("/grantCoupon")
    R grantCoupon(GrantCouponReq req, MultipartFile[] grantCouponFiles) {
        ParamChecker.notNull(req.getCouponCode(),"券号不能为空！");
        ParamChecker.notNull(req.getOrgChannel(),"发券通道不能为空！");
        ParamChecker.notNull(req.getProductNo(),"发券业务线不能为空！");
        ParamChecker.notNull(grantCouponFiles,"发券文件不能为空！");
        ParamChecker.isTrue(grantCouponFiles.length > 0,"发券文件不能为空！");

        // 保存文件
        for(MultipartFile file:grantCouponFiles) {
            // 发券文件名为券号加上时间戳
            String name = req.getCouponCode()+System.currentTimeMillis();
            fileUploadService.saveUserInfoFile(file,name);
        }

        int count = 0;
        try {
            grantCouponService.grant(req, grantCouponFiles);
            return R.ok("异步执行发券操作，验证数据库券是否已经全部发完！");
        } catch (BusiException be) {
            return R.error(be.getErrMsg() + " " + "整体发券失败，当前执行成功" + count + "条");
        } catch (Exception e) {
            logger.error("异步执行发券失败！e={}", e);
            return R.error(e.getMessage());
        }
    }

    @AccessLog("转入金额")
    @RequiresPermissions("market:coupon:manage")
    @GetMapping("/accountIn")
    String accountIn(Model model, Long id, String merchantCode) {
        ParamChecker.notNull(id, "活动id不能为空");
        ParamChecker.notBlank(merchantCode, "商户号不能为空");
        ActivityQueryDto queryDto = new ActivityQueryDto();
        queryDto.setId(id);

        BigDecimal cash = BigDecimal.ZERO;
        try{
            cash = queryCashAccount(merchantCode);
        } catch (BusiException e){
            logger.error("查询现金户出错：{}",e.getErrMsg(),e);
            return "error";
        }
        model.addAttribute("id",id);
        model.addAttribute("cash",cash);
        return "newmarketing/coupon/accountIn";
    }

    @AccessLog("转入金额")
    @RequiresPermissions("market:coupon:manage")
    @ResponseBody
    @PostMapping("/account-in")
    R transIn(ActivityAccountDto accountDto) {
        logger.info("transIn param is :{}", accountDto);
        ParamChecker.notNull(accountDto, "accountDto不能为空");
        ParamChecker.notNull(accountDto.getActivityId(), "活动id不能为空");
        ParamChecker.notNull(accountDto.getAmount(), "转入金额不能为空");
        try {
            if (activityFacade.accountIn(accountDto).isSuccess()) {
                return R.ok();
            } else {
                return R.error(1, "转入金额失败");
            }
        } catch (Exception e) {
            logger.error("转入金额失败", e);
            return R.error(2, "转入金额失败");
        }
    }

    @AccessLog("转出金额")
    @RequiresPermissions("market:coupon:manage")
    @GetMapping("/accountOut")
    String accountOut(Model model, Long id, BigDecimal activityAccount) {
        ParamChecker.notNull(id, "活动id不能为空");
        ParamChecker.notNull(activityAccount, "活动剩余金额不能为空");
        model.addAttribute("id",id);
        model.addAttribute("activityAccount",activityAccount);
        return "newmarketing/coupon/accountOut";
    }

    @AccessLog("转出金额")
    @RequiresPermissions("market:coupon:manage")
    @ResponseBody
    @PostMapping("/account-out")
    R transOut(ActivityAccountDto accountDto) {
        logger.info("transOut param is :{}", accountDto);
        ParamChecker.notNull(accountDto, "accountDto不能为空");
        ParamChecker.notNull(accountDto.getActivityId(), "活动id不能为空");
        ParamChecker.notNull(accountDto.getAmount(), "转出金额不能为空");
        try {
            if (activityFacade.accountOut(accountDto).isSuccess()) {
                return R.ok();
            } else {
                return R.error(1, "转出金额失败");
            }
        } catch (Exception e) {
            logger.error("转出金额失败", e);
            return R.error(2, "转出金额失败");
        }
    }

    /**
     * 查询现金账户
     */
    private BigDecimal queryCashAccount(String merchantCode){

        AccountQueryResultDto param = null;
        try{
            param = merchantAccountQueryFacade.queryAccount(merchantCode, "QUNAR");
        }catch(Exception e){
            logger.error("获取现金账户金额错误",e);
            throw new BusiException(MarketErrorCodes.QUERY_ACCOUNT_ERROR);
        }
        if(param == null){
            logger.info(" queryMarketingActivity resule is : null ");
            throw new BusiException(MarketErrorCodes.QUERY_ACCOUNT_ERROR);
        }
        logger.info(" queryMarketingActivity resule is : {}" , param.toString());
        if(!param.getStatus().equalsIgnoreCase(AccountQueryResultDto.STATUS_SUCCESS)){
            throw new BusiException(MarketErrorCodes.QUERY_ACCOUNT_EXCE);
        }
        AccountQueryParam paramObj = param.getAccountQueryParam();
        if(paramObj == null ){
            throw new BusiException(MarketErrorCodes.QUERY_ACCOUNT_EXCE);
        }
        logger.info(" queryMarketingActivity AccountQueryParam resule is : " + paramObj.toString());
        return paramObj.getValidBalance();
    }

    @Resource
    private ParamValidate paramValidate;

    @Resource
    private MarketingFacadeProxy marketingFacadeProxy;

    @Resource
    private MarketingCouponService marketingCouponService;

    @Resource
    private MarketingReviewService marketingReviewService;

    @Resource
    private FileUploadService fileUploadService;

    @Resource
    private ActivityFacade activityFacade;

    @Resource
    private MerchantAccountQueryFacade merchantAccountQueryFacade;

    @Resource
    private GrantCouponService grantCouponService;

    private static final Logger logger = LoggerFactory.getLogger(MarketingCouponController.class);
}
