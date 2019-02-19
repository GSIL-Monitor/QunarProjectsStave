package com.qunar.fintech.plat.admin.hank.service;

import com.qunar.fintech.plat.admin.hank.entity.TaskConfig;
import com.qunar.fintech.plat.admin.support.web.Query;

import java.util.List;

/**
 * Created by tongda.sun on 2018/12/08  16:10.
 * Description: 任务配置服务类
 */
public interface TaskConfigService {

    /**
     * 通过 id 查询
     * @param id
     * @return
     */
    TaskConfig selectById(Long id);

    /**
     * 通过 taskType 查询
     * @param taskType
     * @return
     */
    TaskConfig selectConfigByTaskType(String taskType);

    /**
     * 条件查询
     * @param query: id & taskType
     * @return
     */
    List<TaskConfig> select(Query query);

    /**
     * 条件查询总量
     * @param query: id & taskType
     * @return
     */
    int count(Query query);

    /**
     * 插入
     * @param record
     * @return
     */
    int insertSelective(TaskConfig record);

    /**
     * 通过主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TaskConfig record);
}
