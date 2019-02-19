package com.qunar.fintech.plat.admin.system.service.impl;

import com.qunar.fintech.plat.admin.system.dao.entity.RoleDO;
import com.qunar.fintech.plat.admin.system.dao.entity.RoleMenuDO;
import com.qunar.fintech.plat.admin.system.dao.mapper.RoleDao;
import com.qunar.fintech.plat.admin.system.dao.mapper.RoleMenuDao;
import com.qunar.fintech.plat.admin.system.dao.mapper.UserDao;
import com.qunar.fintech.plat.admin.system.dao.mapper.UserRoleDao;
import com.qunar.fintech.plat.admin.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";

    @Override
    public List<RoleDO> list() {
        List<RoleDO> roles = roleMapper.list(new HashMap<String, Object>());
        return roles;
    }

    //@Cacheable(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Override
    public List<RoleDO> list(Long userId) {
        List<Long> rolesIds = userRoleMapper.listRoleId(userId);
        List<RoleDO> roles = roleMapper.list(new HashMap<String, Object>());
        for (RoleDO roleDO : roles) {
            roleDO.setRoleSign("false");
            for (Long roleId : rolesIds) {
                if (roleDO.getRoleId().equals(roleId)) {
                    roleDO.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }

    @Transactional
    @Override
    public int save(RoleDO role) {
        int count = roleMapper.save(role);
        if(count > 0) {
            role.setRoleId(roleMapper.getMaxId()+1);
            roleMapper.updateRoleId(role);
        }
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        List<RoleMenuDO> rms = new ArrayList<RoleMenuDO>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return count;
    }

    //@CacheEvict(value = DEMO_CACHE_NAME)
    @Transactional
    @Override
    public int remove(Long id) {
        int count = roleMapper.remove(id);
        roleMenuMapper.removeByRoleId(id);
        return count;
    }

    @Override
    public RoleDO get(Long id) {
        RoleDO roleDO = roleMapper.get(id);
        return roleDO;
    }

    //@CacheEvict(value = DEMO_CACHE_NAME)
    @Override
    public int update(RoleDO role) {
        int r = roleMapper.update(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        roleMenuMapper.removeByRoleId(roleId);
        List<RoleMenuDO> rms = new ArrayList<RoleMenuDO>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        //roleMenuMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return r;
    }

    @Autowired
    RoleDao roleMapper;

    @Autowired
    RoleMenuDao roleMenuMapper;

    @Autowired
    UserDao userMapper;

    @Autowired
    UserRoleDao userRoleMapper;

}
