package com.qunar.fintech.plat.admin.system.dao.mapper;

import com.qunar.fintech.plat.admin.system.dao.entity.RoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author huanyunz
 * @email huanyun.zhou@qunar.com
 * @date 2017-10-02 20:24:47
 */
@Mapper
public interface RoleDao {

    RoleDO get(Long roleId);

    List<RoleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(RoleDO role);

    int update(RoleDO role);

    int remove(Long role_id);

    int batchRemove(Long[] roleIds);

    int updateRoleId(RoleDO role);

    long getMaxId();
}
