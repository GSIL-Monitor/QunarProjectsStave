package com.qunar.fintech.plat.admin.system.dao.mapper;

import com.qunar.fintech.plat.admin.system.dao.entity.FileDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 *
 * @author huanyunz
 * @email huanyun.zhou@qunar.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface FileDao {

    FileDO get(Long id);

    List<FileDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(FileDO file);

    int update(FileDO file);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
