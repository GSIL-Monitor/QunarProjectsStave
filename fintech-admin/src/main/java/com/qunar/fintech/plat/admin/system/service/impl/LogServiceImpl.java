package com.qunar.fintech.plat.admin.system.service.impl;

import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.fintech.plat.admin.system.dao.entity.LogDO;
import com.qunar.fintech.plat.admin.system.dao.entity.PageDO;
import com.qunar.fintech.plat.admin.system.dao.mapper.LogDao;
import com.qunar.fintech.plat.admin.system.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Override
    public PageDO<LogDO> queryList(Query query) {
        List<LogDO> logs = logMapper.list(query);
        int total = logMapper.count(query);
        PageDO<LogDO> page = new PageDO<LogDO>();
        page.setTotal(total);
        page.setRows(logs);
        return page;
    }

    @Override
    public int remove(Long id) {
        int count = logMapper.remove(id);
        return count;
    }

    @Override
    public int batchRemove(Long[] ids) {
        return logMapper.batchRemove(ids);
    }

    @Autowired
    LogDao logMapper;
}
