package com.qunar.fintech.plat.admin.marketing.controller;

import com.google.gson.Gson;
import com.qunar.fintech.marketing.api.admin.facade.CouponFacade;
import com.qunar.fintech.marketing.api.admin.model.TblCouponDto;
import com.qunar.fintech.plat.admin.marketing.dto.CouponReviewDto;
import com.qunar.fintech.plat.admin.marketing.service.MarketingReviewService;
import com.qunar.fintech.plat.admin.support.security.AccessLog;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.plat.admin.system.service.UserService;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: shiqun
 * Date: 2018-11-17
 * Time: 10:01 PM
 */
@RequestMapping("/market/review")
@Controller
public class ReviewController {

    private static final String prefix = "marketing/review/list";

    @Resource
    MarketingReviewService marketingReviewService;

    @Autowired
    UserService userService;

    @GetMapping()
    String list() {
        return prefix;
    }

    @AccessLog("显示审核记录")
    @RequiresPermissions("market:review:list")
    @ResponseBody
    @GetMapping("/queryAllReviews")
    List<CouponReviewDto> queryAllReviews() {
        return marketingReviewService.queryAllReviews();
    }

    @AccessLog("审核详情")
    @GetMapping("/detail/{id}")
    String reviewDetail(Model model, @PathVariable("id") Long id) {
        ParamChecker.notNull(id, "审核id不能为空");

        // 需要将json转为对象，作为属性存入model中
        Gson gson = new Gson();
        TblCouponDto tblCouponDto
                = gson.fromJson(marketingReviewService.queryReviewById(id).getContentValue(), TblCouponDto.class);

        model.addAttribute("couponInfo", tblCouponDto);
        return "marketing/review/detail";
    }

    @AccessLog("审核通过")
    @RequiresPermissions("market:coupon:edit")
    @ResponseBody
    @PostMapping("/pass")
    R reviewPass(@RequestParam( "adminName" ) String adminName, @RequestParam( "id" ) Long id, HttpSession httpSession) {
        ParamChecker.notNull(id, "id不能为空");

        // 从session中获取登录用户的userid
        String userId = (String) httpSession.getAttribute("userId");

        // 审核通过流程
        return marketingReviewService.pass(adminName, id,userId);
    }

    @AccessLog("审核拒绝")
    @RequiresPermissions("market:coupon:edit")
    @ResponseBody
    @PostMapping("/reject")
    R reviewReject(@RequestParam( "adminName" ) String adminName, @RequestParam( "id" ) Long id,HttpSession httpSession) {

        ParamChecker.notNull(id, "id不能为空");

        // 从session中获取登录用户的userid
        String userId = (String) httpSession.getAttribute("userId");

        // 拒绝通过流程
        return marketingReviewService.reject(adminName, id,userId);
    }
}
