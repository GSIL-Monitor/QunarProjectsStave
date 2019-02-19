package com.qunar.fintech.plat.admin.system.service;

import com.qunar.fintech.plat.admin.system.dao.entity.TaskDO;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

/**
 * @author huanyunz
 * @email huanyun.zhou@qunar.com
 * @date 2017-09-26 20:53:48
 */
public interface TaskScheduleJobService {

    TaskDO get(Long id);

    List<TaskDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(TaskDO taskScheduleJob);

    int update(TaskDO taskScheduleJob);

    int remove(Long id);

    int batchRemove(Long[] ids);

    void initSchedule() throws SchedulerException;

    void changeStatus(Long jobId, String cmd) throws SchedulerException;

    void updateCron(Long jobId) throws SchedulerException;
}
