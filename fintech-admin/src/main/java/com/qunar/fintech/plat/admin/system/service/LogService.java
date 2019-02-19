package com.qunar.fintech.plat.admin.system.service;

import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.fintech.plat.admin.system.dao.entity.LogDO;
import com.qunar.fintech.plat.admin.system.dao.entity.PageDO;
import org.springframework.stereotype.Service;

@Service
public interface LogService {

    PageDO<LogDO> queryList(Query query);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
