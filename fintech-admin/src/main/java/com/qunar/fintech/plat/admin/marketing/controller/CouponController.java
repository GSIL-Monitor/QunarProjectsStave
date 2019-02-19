package com.qunar.fintech.plat.admin.marketing.controller;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.io.CharStreams;
import com.qunar.fintech.marketing.api.admin.facade.CouponFacade;
import com.qunar.fintech.marketing.api.admin.model.CouponQueryDto;
import com.qunar.fintech.marketing.api.admin.model.TblCouponDto;
import com.qunar.fintech.plat.admin.marketing.dto.InterestFreeCouponDto;
import com.qunar.fintech.plat.admin.marketing.service.CouponService;
import com.qunar.fintech.plat.admin.marketing.service.MarketingReviewService;
import com.qunar.fintech.plat.admin.query.entity.MarketingCouponReq;
import com.qunar.fintech.plat.admin.query.enums.CouponOperEnum;
import com.qunar.fintech.plat.admin.support.exception.MarketErrorCodes;
import com.qunar.fintech.plat.admin.support.lock.BusiLocker;
import com.qunar.fintech.plat.admin.support.security.AccessLog;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.util.lock.BusiCallable;
import com.qunar.fintech.util.simple.BigDecimalUtil;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/marketing/coupon")
@Controller
public class CouponController {

    @Resource
    MarketingReviewService marketingReviewService;

    @AccessLog("添加券")
    @RequiresPermissions("market:coupon:add")
    @GetMapping("/add/{activityCode}")
    String addCoupon(Model model, @PathVariable("activityCode") String activityCode) {
        ParamChecker.notNull(activityCode, "activityCode不能为空");
        LOGGER.info("add coupon parameter is :{}", activityCode);
        model.addAttribute("activityCode", activityCode);
        return prefix + "/add";
    }

    @AccessLog("保存券")
    @RequiresPermissions("market:coupon:add")
    @ResponseBody
    @PostMapping("/save")
    R saveCoupon(MarketingCouponReq req,TblCouponDto couponDto) {
        LOGGER.info("save coupon parameter，req = {}, couponDto = {}", req, couponDto);
        checkParam(couponDto);

        // 券信息暂存到admin review表中，等待审核
        marketingReviewService.addCouponNeedToReview(CouponOperEnum.CREATE,couponDto,req);
        return R.ok("等待管理员审核");
    }


    @AccessLog("查看券")
    @RequiresPermissions("market:coupon:list")
    @ResponseBody
    @GetMapping("/listCoupon")
    List<TblCouponDto> listCoupon(String activityCode) {
        LOGGER.info("listCoupon parameter is :{}", activityCode);
        ParamChecker.notNull(activityCode, "activityCode不能为空");
        CouponQueryDto queryDto = new CouponQueryDto();
        queryDto.setActivityCode(activityCode);
        return couponFacade.queryAllCouponInfo(queryDto).getData();
    }

    @AccessLog("查看")
    @RequiresPermissions("market:coupon:list")
    @GetMapping("/list")
    ModelAndView list(String activityCode) {
        ParamChecker.notNull(activityCode, "activityCode不能为空");
        ModelAndView mv = new ModelAndView("/marketing/coupon/list");
        mv.addObject("activityCode", activityCode);
        return mv;
    }

    @AccessLog("编辑券")
    @RequiresPermissions("market:coupon:edit")
    @GetMapping("/edit/{id}")
    String editCoupon(Model model, @PathVariable("id") Long id) {
        ParamChecker.notNull(id, "券id不能为空");
        CouponQueryDto queryDto = new CouponQueryDto();
        queryDto.setId(id);
        TblCouponDto tblCouponDto = couponFacade.queryCouponInfoById(queryDto).getData();
        model.addAttribute("couponInfo", tblCouponDto);
        return "marketing/coupon/edit";
    }

    @AccessLog("更新券")
    @RequiresPermissions("market:coupon:edit")
    @ResponseBody
    @PostMapping("/update")
    R updateActivity(MarketingCouponReq req,TblCouponDto couponDto) {
        LOGGER.info("update coupon parameter is :{}", couponDto);
        ParamChecker.notNull(couponDto.getId(), "id不能为空");
        checkParam(couponDto);

        // 券信息暂存到admin review表中，等待审核
        marketingReviewService.addCouponNeedToReview(CouponOperEnum.UPDATE,couponDto,req);
        return R.ok("等待审核");
    }

    @AccessLog("添加发券文件")
    @RequiresPermissions("market:coupon:add")
    @GetMapping("/addInterestFreeFile/{id}")
    String addInterestFreeFile(Model model, @PathVariable("id") Long id) {
        ParamChecker.notNull(id, "券id不能为空");
        CouponQueryDto queryDto = new CouponQueryDto();
        queryDto.setId(id);
        model.addAttribute("couponInfo", couponFacade.queryCouponInfoById(queryDto).getData());
        return prefix + "/addInterestFree";
    }

    @AccessLog("发券")
    @RequiresPermissions("market:coupon:add")
    @ResponseBody
    @PostMapping("/addInterestFree")
    R addInterestFree(@RequestParam(value = "couponFile", required = false) MultipartFile couponFile,
                      @RequestParam(value = "orgChannel", required = false) String orgChannel,
                      @RequestParam(value = "productNo", required = false) String productNo,
                      @RequestParam(value = "couponAmount", required = false) BigDecimal couponAmount,
                      @RequestParam(value = "activityCode", required = false) String activityCode,
                      @RequestParam(value = "couponCode", required = false) final String couponCode) {
        final InterestFreeCouponDto ifc = bulidInterestFreeCouponDto(couponFile, orgChannel, productNo, couponAmount, activityCode, couponCode);
        checkFileParam(ifc);

        int count = 0;
        try {
            int expiredCnt = checkFileData(couponFile);

            tryGrant(ifc, expiredCnt);
            return R.ok("发券执行中");
        } catch (BusiException be) {
            return R.error(2, be.getErrMsg() + " " + "整体发券失败，当前执行成功" + count + "条");
        } catch (Exception e) {
            LOGGER.error("券发放失败", e);
            return R.error(2, "券发放失败");
        }
    }


    private void tryGrant(final InterestFreeCouponDto ifc, int expiredCnt) throws Exception {
        if (expiredCnt <= 0) {
            return;
        }

        // 默认过期时长
        final int expiredSecs = new Double(0.15 * expiredCnt + 1).intValue();
        final String lockKey = genLockKey("grant", ifc.getCouponCode());

        LOGGER.info("tryGrant - expiredSecs: {}, lockKey: {}", expiredSecs, lockKey);
        //异步调用发券接口
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 尝试执行
                    busiLocker.lockedExecute(lockKey, expiredSecs, new BusiCallable<Integer>() {
                        @Override
                        public Integer executeBusi() throws Exception {
                            LOGGER.debug("tryGrant - begin");
                            return couponService.addInterestFreeCoupon(ifc);
                        }
                    });
                    // 释放锁
                    LOGGER.debug("excute busi finish, try release lock.");
                } catch (BusiException e) {
                    // 业务异常，直接抛出
                    throw e;

                } catch (Exception e) {
                    // 尝试加锁异常, 任务终止
                    LOGGER.error("当前有正在执行的发券任务: {}, err:{} ", ifc.getCouponCode(), e);
                } finally {
                    busiLocker.releaseLock(lockKey);
                    LOGGER.debug("tryGrant - release lock succ: {}", lockKey);
                }
            }
        });
        thread.start();
    }


    private String genLockKey(String prefix, String couponCode) {
        return Joiner.on("-").join(prefix, couponCode);
    }


    /**
     * 校验文件参数
     *
     * @param interestFreeCouponDto
     */
    private void checkFileParam(InterestFreeCouponDto interestFreeCouponDto) {
        ParamChecker.notNull(interestFreeCouponDto, "interestFreeCouponDto不能为空");
        ParamChecker.notBlank(interestFreeCouponDto.getOrgChannel(), "渠道不能为空");
        ParamChecker.notNull(interestFreeCouponDto.getProductNo(), "产品类型不能为空");
        ParamChecker.notNull(interestFreeCouponDto.getActivityCode(), "活动编号不能为空");
        ParamChecker.notNull(interestFreeCouponDto.getCouponCode(), "券编号不能为空");
        ParamChecker.notNull(interestFreeCouponDto.getFile(), "发券文件不能为空");
        //ParamChecker.isTrue(BigDecimalUtil.notLessThanZero(interestFreeCouponDto.getCouponAmount()), "券面额不能小于0");
    }

    /**
     * 校验文件发券参数
     *
     * @param couponDto
     */
    private void checkParam(TblCouponDto couponDto) {
        ParamChecker.notNull(couponDto, "couponDto不能为空");
        ParamChecker.notBlank(couponDto.getCouponCode(),"券code不能为空");
        ParamChecker.notBlank(couponDto.getCouponName(), "券名称不能为空");
        ParamChecker.notNull(couponDto.getCouponType(), "券类型不能为空");
        ParamChecker.notNull(couponDto.getStartTime(), "开始时间不能为空");
        ParamChecker.notNull(couponDto.getEndTime(), "结束时间不能为空");
        ParamChecker.notNull(couponDto.getTotalNum(), "券总数量不能为空");
        ParamChecker.notNull(couponDto.getTotalAmt(), "券总金额不能为空");
        ParamChecker.notNull(couponDto.getUserPercept(), "是否用户感知不能为空");
        ParamChecker.notNull(couponDto.getAutoUnUse(), "是否自动撤销不能为空");
        ParamChecker.notNull(couponDto.getCouponScene(), "使用场景不能为空");
        ParamChecker.isTrue(couponDto.getStartTime().compareTo(couponDto.getEndTime()) < 0, "券开始时间不能大于结束时间");
        ParamChecker.isTrue(BigDecimalUtil.notLessThanZero(couponDto.getTotalAmt()), "券总金额不能小于0");
        ParamChecker.isTrue(couponDto.getTotalNum() >= 0, "券总数量不能小于0");
    }

    /**
     * 封装InterestFreeCouponDto
     */
    private InterestFreeCouponDto bulidInterestFreeCouponDto(MultipartFile couponFile, String orgChannel, String productNo, BigDecimal couponAmount, String activityCode, String couponCode) {
        InterestFreeCouponDto ifc = new InterestFreeCouponDto();
        ifc.setFile((CommonsMultipartFile) couponFile);
        ifc.setOrgChannel(orgChannel);
        ifc.setProductNo(productNo);
        ifc.setCouponAmount(couponAmount);
        ifc.setActivityCode(activityCode);
        ifc.setCouponCode(couponCode);
        return ifc;
    }

    /**
     * 校验文件合理性
     *
     * @param
     */
    private int checkFileData(MultipartFile couponFile) {
        // 校验文件合理性
        try {
            List<String> stringList = CharStreams.readLines(new InputStreamReader(couponFile.getInputStream()));
            for (String str : stringList) {
                //遍历集合的每一个元素，把它用“，”分割成两部分
                List<String> uidMobile = Splitter.on(",").splitToList(str);
                if (uidMobile.size() < 2) {
                    throw new BusiException(MarketErrorCodes.PARAM_ERROR, "uid或mobile为空");
                }

                if (StringUtils.isEmpty(uidMobile.get(0)) || StringUtils.isEmpty(uidMobile.get(1))) {
                    throw new BusiException(MarketErrorCodes.PARAM_ERROR, "uid或mobile为空");
                }
            }
            return stringList.size();
        } catch (BusiException e) {
            LOGGER.error("checkFileData - err: {}", e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("checkFileData - err: {}", e);
            throw new BusiException(MarketErrorCodes.UNKOWN_ERROR);
        }
    }

    private static final String prefix = "marketing/coupon";

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    private CouponFacade couponFacade;
    @Autowired
    private CouponService couponService;
    @Resource
    private BusiLocker busiLocker;

}
