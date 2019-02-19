package com.qunar.fintech.plat.admin.system.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qunar.fintech.plat.admin.support.util.BuildTree;
import com.qunar.fintech.plat.admin.system.dao.entity.MenuDO;
import com.qunar.fintech.plat.admin.system.dao.entity.RoleMenuDO;
import com.qunar.fintech.plat.admin.system.dao.entity.Tree;
import com.qunar.fintech.plat.admin.system.dao.mapper.MenuDao;
import com.qunar.fintech.plat.admin.system.dao.mapper.RoleMenuDao;
import com.qunar.fintech.plat.admin.system.service.MenuService;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {

    /**
     * @param id
     * @return 树形菜单
     */
    //@Cacheable
    @Override
    public Tree<MenuDO> getSysMenuTree(Long id) {
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<MenuDO> t = BuildTree.build(getMenuTreeList(id));
        return t;
    }

    @Override
    public List<Tree<MenuDO>> listMenuTree(Long id) {
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<MenuDO>> list = BuildTree.buildList(getMenuTreeList(id), "0");
        return list;
    }

    @Override
    public List<MenuDO> list() {

        List<MenuDO> menus = menuMapper.list(new HashMap<String, Object>());
        return menus;
    }

    @Override
    @Transactional
    public boolean remove(Long id) {
        int result = menuMapper.remove(id);
        Map<String,Object> map = Maps.newHashMap();
        map.put("menuId",id);
        List<Long> roleMenuIds = Lists.transform(roleMenuMapper.list(map), new Function<RoleMenuDO, Long>() {
            @Override
            public Long apply(RoleMenuDO roleMenuDO) {
                return roleMenuDO.getId();
            }
        });
        int r = roleMenuMapper.batchRemove(roleMenuIds.toArray(new Long[roleMenuIds.size()]));
        return result>0 && r==roleMenuIds.size();
    }

    @Override
    @Transactional
    public boolean save(MenuDO menu, List<Long> roleIds) {
        ParamChecker.notNull(menu,"menu不能为空");
        if (menuMapper.save(menu) > 0) {
            menu.setMenuId(menuMapper.getMaxId()+1);
            if(menuMapper.updateMenuId(menu) > 0 && CollectionUtils.isNotEmpty(roleIds)) {
                List<RoleMenuDO> roleMenus = Lists.newArrayList();
                for (long roleId : roleIds) {
                    roleMenus.add(new RoleMenuDO(roleId, menu.getMenuId()));
                }
                return roleMenuMapper.batchSave(roleMenus) == roleMenus.size();
            }
            return true;
        }
        return false;
    }

    @Override
    public int update(MenuDO menu) {
        int r = menuMapper.update(menu);
        return r;
    }

    @Override
    public MenuDO get(Long id) {
        MenuDO menuDO = menuMapper.get(id);
        return menuDO;
    }

    @Override
    public Tree<MenuDO> getTree(Long id) {
        // 根据roleId查询权限
        List<Long> menuIds = roleMenuMapper.listMenuIdByRoleId(id);
        List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
        List<MenuDO> menuDOs = menuMapper.list(new HashMap<String, Object>());
        for (MenuDO sysMenuDO : menuDOs) {
            Tree<MenuDO> tree = new Tree<MenuDO>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> state = new HashMap<String, Object>();
            Long menuId = sysMenuDO.getMenuId();
            if (menuIds.contains(menuId)) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<MenuDO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Set<String> listPerms(Long userId) {
        List<String> perms = menuMapper.listUserPerms(userId);
        Set<String> permsSet = new HashSet<String>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    private List<Tree<MenuDO>> getMenuTreeList(Long userId) {
        List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
        List<MenuDO> menuDOs = menuMapper.listMenuByUserId(userId);
        for (MenuDO sysMenuDO : menuDOs) {
            Tree<MenuDO> tree = new Tree<MenuDO>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> attributes = new HashMap<String, Object>();
            attributes.put("url", sysMenuDO.getUrl());
            attributes.put("icon", sysMenuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        return trees;
    }

    @Autowired
    private MenuDao menuMapper;

    @Autowired
    private RoleMenuDao roleMenuMapper;

    @Override
    public Tree<MenuDO> getTree() {
        List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
        List<MenuDO> menuDOs = menuMapper.list(new HashMap<String, Object>());
        for (MenuDO sysMenuDO : menuDOs) {
            Tree<MenuDO> tree = new Tree<MenuDO>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<MenuDO> t = BuildTree.build(trees);
        return t;
    }

}
