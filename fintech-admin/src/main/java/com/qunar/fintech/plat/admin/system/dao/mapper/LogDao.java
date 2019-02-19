package com.qunar.fintech.plat.admin.system.dao.mapper;

import com.qunar.fintech.plat.admin.system.dao.entity.LogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 *
 * @author huanyunz
 * @email huanyun.zhou@qunar.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface LogDao {

    LogDO get(Long id);

    List<LogDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(LogDO log);

    int update(LogDO log);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
