package com.qunar.fintech.plat.admin.system.service.impl;

import com.qunar.fintech.plat.admin.system.dao.entity.RoleMenuDO;
import com.qunar.fintech.plat.admin.system.dao.mapper.RoleMenuDao;
import com.qunar.fintech.plat.admin.system.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with Intellij IDEA.
 * Description:
 * User: weiduan
 * Date: 2017-11-28
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService{
    
    public int save(RoleMenuDO roleMenu){
        return roleMenuDao.save(roleMenu);
    }

    public int batchSave(List<RoleMenuDO> list){
        return roleMenuDao.batchSave(list);
    }

    @Autowired
    private RoleMenuDao roleMenuDao;
}
