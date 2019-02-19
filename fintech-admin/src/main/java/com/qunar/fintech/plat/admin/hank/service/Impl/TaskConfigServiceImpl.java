package com.qunar.fintech.plat.admin.hank.service.Impl;

import com.qunar.fintech.plat.admin.hank.entity.TaskConfig;
import com.qunar.fintech.plat.admin.hank.mapper.TaskConfigMapper;
import com.qunar.fintech.plat.admin.hank.service.TaskConfigService;
import com.qunar.fintech.plat.admin.support.exception.BDException;
import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.pay.g2.utils.common.CheckUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tongda.sun on 2018/12/08  16:12.
 * Description:
 */
@Service
public class TaskConfigServiceImpl implements TaskConfigService {

    @Override
    public TaskConfig selectById(Long id) {
        if(CheckUtils.isEmpty(id)){
            throw new BDException("TaskConfigService.selectById id参数为空");
        }
        return mapper.selectById(id);
    }

    @Override
    public TaskConfig selectConfigByTaskType(String taskType) {
        logger.info("TaskConfigService.selectConfigByTaskType# taskType={}",taskType);
        if(CheckUtils.isEmpty(taskType)){
            throw new BDException("TaskConfigService.selectConfigByTaskType# taskType 参数异常");
        }
        return mapper.selectConfigByTaskType(taskType.trim());
    }

    @Override
    public List<TaskConfig> select(Query query) {
        logger.info("TaskConfigService.select# query={}",query);
        query.put("taskType",query.get("taskType").toString().trim());
        return mapper.select(query);
    }

    @Override
    public int count(Query query) {
        logger.info("TaskConfigService.count# query={}",query);
        query.put("taskType",query.get("taskType").toString().trim());
        return mapper.count(query);
    }

    @Override
    public int insertSelective(TaskConfig record) {
        if(CheckUtils.isEmpty(record)){
            throw new BDException("TaskConfigService.insertSelective# record 参数异常");
        }
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(TaskConfig record) {
        if(CheckUtils.isEmpty(record)){
            throw new BDException("TaskConfigService.updateByPrimaryKeySelective# record 参数异常");
        }
        if(CheckUtils.isEmpty(record.getId())){
            throw new BDException("TaskConfigService.updateByPrimaryKeySelective# record.id 参数异常");
        }
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Autowired
    private TaskConfigMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(TaskConfigServiceImpl.class);
}
