package com.qunar.fintech.plat.admin.system.service;

import com.qunar.fintech.plat.admin.system.dao.entity.DictDO;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 *
 * @author huanyunz
 * @email huanyun.zhou@qunar.com
 * @date 2017-09-29 18:28:07
 */
public interface SysDictService {

    DictDO get(Long id);

    List<DictDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(DictDO sysDict);

    int update(DictDO sysDict);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<DictDO> listType();
}
