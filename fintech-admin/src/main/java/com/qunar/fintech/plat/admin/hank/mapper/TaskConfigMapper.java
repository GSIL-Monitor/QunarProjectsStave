package com.qunar.fintech.plat.admin.hank.mapper;

import com.qunar.fintech.plat.admin.hank.entity.TaskConfig;
import com.qunar.fintech.plat.admin.support.web.Query;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskConfigMapper{

    TaskConfig selectById(@Param("id") Long id);

    TaskConfig selectConfigByTaskType(@Param("taskType") String taskType);

    List<TaskConfig> select(Query query);

    int count(Query query);

    int insertSelective(TaskConfig record);

    int updateByPrimaryKeySelective(TaskConfig record);
}
