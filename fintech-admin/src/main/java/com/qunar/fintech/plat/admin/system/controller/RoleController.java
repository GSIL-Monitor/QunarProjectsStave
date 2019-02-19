package com.qunar.fintech.plat.admin.system.controller;

import com.qunar.fintech.plat.admin.support.security.AccessLog;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.plat.admin.system.dao.entity.RoleDO;
import com.qunar.fintech.plat.admin.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/sys/role")
@Controller
public class RoleController extends BaseController {

    @RequiresPermissions("sys:role:role")
    @GetMapping()
    String role() {
        return prefix + "/role";
    }

    @RequiresPermissions("sys:role:role")
    @GetMapping("/list")
    @ResponseBody()
    List<RoleDO> list() {
        List<RoleDO> roles = roleService.list();
        return roles;
    }

    @AccessLog("添加角色")
    @RequiresPermissions("sys:role:add")
    @GetMapping("/add")
    String add() {
        return prefix + "/add";
    }

    @AccessLog("编辑角色")
    @RequiresPermissions("sys:role:edit")
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Long id, Model model) {
        RoleDO roleDO = roleService.get(id);
        model.addAttribute("role", roleDO);
        return prefix + "/edit";
    }

    @AccessLog("保存角色")
    @RequiresPermissions("sys:role:add")
    @PostMapping("/save")
    @ResponseBody()
    R save(RoleDO role) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (roleService.save(role) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @AccessLog("更新角色")
    @RequiresPermissions("sys:role:edit")
    @PostMapping("/update")
    @ResponseBody()
    R update(RoleDO role) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (roleService.update(role) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @AccessLog("删除角色")
    @RequiresPermissions("sys:role:remove")
    @PostMapping("/remove")
    @ResponseBody()
    R save(Long id) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (roleService.remove(id) > 0) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }

    String prefix = "sys/role";

    @Autowired
    RoleService roleService;
}
