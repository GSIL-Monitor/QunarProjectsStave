package com.qunar.fintech.plat.admin.system.controller;

import com.qunar.fintech.plat.admin.support.security.AccessLog;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.plat.admin.system.dao.entity.MenuDO;
import com.qunar.fintech.plat.admin.system.dao.entity.RoleDO;
import com.qunar.fintech.plat.admin.system.dao.entity.Tree;
import com.qunar.fintech.plat.admin.system.dto.MenuDto;
import com.qunar.fintech.plat.admin.system.service.MenuService;
import com.qunar.fintech.plat.admin.system.service.RoleMenuService;
import com.qunar.fintech.plat.admin.system.service.RoleService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/sys/menu")
@Controller
public class MenuController extends BaseController {

    @RequiresPermissions("sys:menu:menu")
    @GetMapping()
    String menu(Model model) {
        return "sys/menu/menu";
    }

    @RequiresPermissions("sys:menu:menu")
    @RequestMapping("/list")
    @ResponseBody
    List<MenuDO> list() {
        List<MenuDO> menus = menuService.list();
        return menus;
    }

    @AccessLog("添加菜单")
    @RequiresPermissions("sys:menu:add")
    @GetMapping("/add/{pId}")
    String add(Model model, @PathVariable("pId") Long pId) {
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.get(pId).getName());
        }
        List<RoleDO> roles = roleService.list();
        model.addAttribute("roles", roles);
        return "sys/menu/add";
    }

    @AccessLog("编辑菜单")
    @RequiresPermissions("sys:menu:edit")
    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("menu", menuService.get(id));
        return "sys/menu/edit";
    }

    @AccessLog("保存菜单")
    @RequiresPermissions("sys:menu:add")
    @PostMapping("/save")
    @ResponseBody
    R save(MenuDto menuDto) {

        logger.info("menu save parameter:{}",menuDto);

        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        ParamChecker.notNull(menuDto,"menuDto不能为空");
        List<Long> roleIds = menuDto.getRole();
        MenuDO menu = menuDto.getMenu();
        if (menuService.save(menu,roleIds)) {
            return R.ok();
        }else {
            return R.error(1, "保存失败");
        }
    }

    @AccessLog("更新菜单")
    @RequiresPermissions("sys:menu:edit")
    @PostMapping("/update")
    @ResponseBody
    R update(MenuDO menu) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (menuService.update(menu) > 0) {
            return R.ok();
        } else {
            return R.error(1, "更新失败");
        }
    }

    @AccessLog("删除菜单")
    @RequiresPermissions("sys:menu:remove")
    @PostMapping("/remove")
    @ResponseBody
    R remove(Long id) {
        logger.info("remove menu id is: {}",id);
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (menuService.remove(id)) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }

    @GetMapping("/tree")
    @ResponseBody
    Tree<MenuDO> tree() {
        Tree<MenuDO> tree = new Tree<MenuDO>();
        tree = menuService.getTree();
        return tree;
    }

    @GetMapping("/tree/{roleId}")
    @ResponseBody
    Tree<MenuDO> tree(@PathVariable("roleId") Long roleId) {
        Tree<MenuDO> tree = new Tree<MenuDO>();
        tree = menuService.getTree(roleId);
        return tree;
    }

    String prefix = "sys/menu";

    @Autowired
    MenuService menuService;
    @Autowired
    RoleMenuService roleMenuService;
    @Autowired
    RoleService roleService;

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
}
