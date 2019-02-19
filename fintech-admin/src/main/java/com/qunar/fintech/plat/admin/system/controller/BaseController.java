package com.qunar.fintech.plat.admin.system.controller;

import com.qunar.fintech.plat.admin.support.security.ShiroUtils;
import com.qunar.fintech.plat.admin.system.dao.entity.UserDO;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    public UserDO getUser() {
        return ShiroUtils.getUser();
    }

    public Long getUserId() {
        return getUser().getUserId();
    }

    public String getUsername() {
        return getUser().getUsername();
    }

}