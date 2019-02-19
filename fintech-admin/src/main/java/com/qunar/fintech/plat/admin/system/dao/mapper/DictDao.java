package com.qunar.fintech.plat.admin.system.dao.mapper;

import com.qunar.fintech.plat.admin.system.dao.entity.DictDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 *
 * @author huanyunz
 * @email huanyun.zhou@qunar.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface DictDao {

    DictDO get(Long id);

    List<DictDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(DictDO dict);

    int update(DictDO dict);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<DictDO> listType();

}
