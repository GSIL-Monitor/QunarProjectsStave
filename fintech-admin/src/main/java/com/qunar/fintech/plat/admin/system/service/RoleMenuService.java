package com.qunar.fintech.plat.admin.system.service;

import com.qunar.fintech.plat.admin.system.dao.entity.RoleMenuDO;

import java.util.List;

/**
 * Created with Intellij IDEA.
 * Description:
 * User: weiduan
 * Date: 2017-11-28
 */
public interface RoleMenuService {

    int save(RoleMenuDO roleMenu);

    int batchSave(List<RoleMenuDO> list);

}
