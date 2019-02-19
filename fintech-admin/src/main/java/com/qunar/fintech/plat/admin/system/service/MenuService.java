package com.qunar.fintech.plat.admin.system.service;

import com.qunar.fintech.plat.admin.system.dao.entity.MenuDO;
import com.qunar.fintech.plat.admin.system.dao.entity.Tree;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface MenuService {

    Tree<MenuDO> getSysMenuTree(Long id);

    List<Tree<MenuDO>> listMenuTree(Long id);

    Tree<MenuDO> getTree(Long id);

    List<MenuDO> list();

    boolean remove(Long id);

    boolean save(MenuDO menu, List<Long> roleIds);

    int update(MenuDO menu);

    MenuDO get(Long id);

    Set<String> listPerms(Long userId);

    Tree<MenuDO> getTree();
}
