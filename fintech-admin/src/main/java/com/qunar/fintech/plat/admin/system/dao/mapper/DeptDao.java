package com.qunar.fintech.plat.admin.system.dao.mapper;

import com.qunar.fintech.plat.admin.system.dao.entity.DeptDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author huanyunz
 * @email huanyun.zhou@qunar.com
 * @date 2017-10-03 15:35:39
 */
@Mapper
public interface DeptDao {

    DeptDO get(Long deptId);

    List<DeptDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(DeptDO dept);

    int update(DeptDO dept);

    int remove(Long dept_id);

    int batchRemove(Long[] deptIds);

    int updateDept(DeptDO dept);

    long getMaxId();
}
