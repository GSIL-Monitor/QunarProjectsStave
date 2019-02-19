package com.qunar.fintech.plat.admin.system.service.impl;

import com.qunar.fintech.plat.admin.system.dao.entity.FileDO;
import com.qunar.fintech.plat.admin.system.dao.mapper.FileDao;
import com.qunar.fintech.plat.admin.system.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class FileServiceImpl implements SysFileService {

    @Override
    public FileDO get(Long id) {
        return sysFileMapper.get(id);
    }

    @Override
    public List<FileDO> list(Map<String, Object> map) {
        return sysFileMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return sysFileMapper.count(map);
    }

    @Override
    public int save(FileDO sysFile) {
        return sysFileMapper.save(sysFile);
    }

    @Override
    public int update(FileDO sysFile) {
        return sysFileMapper.update(sysFile);
    }

    @Override
    public int remove(Long id) {
        return sysFileMapper.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return sysFileMapper.batchRemove(ids);
    }

    @Autowired
    private FileDao sysFileMapper;

}
