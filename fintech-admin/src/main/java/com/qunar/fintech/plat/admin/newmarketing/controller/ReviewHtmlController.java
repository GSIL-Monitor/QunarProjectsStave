package com.qunar.fintech.plat.admin.newmarketing.controller;

import com.qunar.fintech.plat.admin.support.security.AccessLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/newmarketing/review")
@Controller
public class ReviewHtmlController {

    @GetMapping()
    String list() {
        return "/newmarketing/check/list";
    }

    @AccessLog("审核详情")
    @RequiresPermissions("market:review:manage")
    @GetMapping("/checkCon")
    String checkCon() {
        return "/newmarketing/check/checkCon";
    }

    @AccessLog("审核优惠券")
    @RequiresPermissions("market:review:manage")
    @GetMapping("/check")
    String check() {
        return "/newmarketing/check/check";
    }
}
