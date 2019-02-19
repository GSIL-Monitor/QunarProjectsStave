
package com.qunar.fintech.plat.admin.newmarketing.controller;

import com.qunar.fintech.plat.admin.support.security.AccessLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/newmarketing/coupon")
@Controller
public class CouponHtmlController {

    @GetMapping()
    String list() {
        return "/newmarketing/coupon/list";
    }

    @AccessLog("添加活动")
    @RequiresPermissions("market:coupon:manage")
    @GetMapping("/add")
    String addActivity() {
        return "/newmarketing/coupon/add";
    }

    @AccessLog("复制活动")
    @RequiresPermissions("market:coupon:manage")
    @GetMapping("/copyTicket")
    String ticketCode() {
        return "/newmarketing/coupon/copyTicket";
    }

    @AccessLog("发券")
    @RequiresPermissions("market:coupon:manage")
    @GetMapping("/sendTicket")
    String sendTicket() {
        return "/newmarketing/coupon/sendTicket";
    }

    @AccessLog("编辑优惠券")
    @RequiresPermissions("market:coupon:manage")
    @GetMapping("/edit")
    String edit() {
        return "/newmarketing/coupon/edit";
    }

    @AccessLog("活动信息")
    @RequiresPermissions("market:coupon:manage")
    @GetMapping("/activityAdd")
    String activityAdd() {
        return "/newmarketing/couponInfo/activityAdd";
    }

    @AccessLog("优惠券预算")
    @RequiresPermissions("market:coupon:manage")
    @GetMapping("/ticketBudget")
    String ticketBudget() {
        return "/newmarketing/couponInfo/ticketBudget";
    }

    @AccessLog("优惠券信息")
    @RequiresPermissions("market:coupon:manage")
    @GetMapping("/ticketConfig")
    String ticketConfig() {
        return "/newmarketing/couponInfo/ticketConfig";
    }

    @AccessLog("报警信息")
    @RequiresPermissions("market:coupon:manage")
    @GetMapping("/editAlarmConfig")
    String editAlarmConfig() {
        return "/newmarketing/couponInfo/editAlarmConfig";
    }

    @AccessLog("查看优惠券")
    @RequiresPermissions("market:coupon:manage")
    @GetMapping("/content")
    String content() {
        return "/newmarketing/coupon/content";
    }
}
