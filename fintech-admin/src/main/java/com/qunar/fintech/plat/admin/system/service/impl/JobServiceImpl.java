package com.qunar.fintech.plat.admin.system.service.impl;

import com.qunar.fintech.plat.admin.system.dao.entity.ScheduleJob;
import com.qunar.fintech.plat.admin.system.dao.entity.TaskDO;
import com.qunar.fintech.plat.admin.system.dao.mapper.TaskDao;
import com.qunar.fintech.plat.admin.system.quartz.QuartzManager;
import com.qunar.fintech.plat.admin.system.service.TaskScheduleJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobServiceImpl implements TaskScheduleJobService {

    @Override
    public TaskDO get(Long id) {
        return taskScheduleJobMapper.get(id);
    }

    @Override
    public List<TaskDO> list(Map<String, Object> map) {
        return taskScheduleJobMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return taskScheduleJobMapper.count(map);
    }

    @Override
    public int save(TaskDO taskScheduleJob) {
        return taskScheduleJobMapper.save(taskScheduleJob);
    }

    @Override
    public int update(TaskDO taskScheduleJob) {
        return taskScheduleJobMapper.update(taskScheduleJob);
    }

    @Override
    public int remove(Long id) {
        QuartzManager quartzManager = new QuartzManager();
        try {
            TaskDO scheduleJob = get(id);
            quartzManager.deleteJob(entityToData(scheduleJob));
            return taskScheduleJobMapper.remove(id);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int batchRemove(Long[] ids) {
        QuartzManager quartzManager = new QuartzManager();
        for (Long id : ids) {
            try {
                TaskDO scheduleJob = get(id);
                quartzManager.deleteJob(entityToData(scheduleJob));
            } catch (SchedulerException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return taskScheduleJobMapper.batchRemove(ids);
    }

    @Override
    public void initSchedule() throws SchedulerException {
        // 这里获取任务信息数据
        QuartzManager quartzManager = new QuartzManager();
        List<TaskDO> jobList = taskScheduleJobMapper.list(new HashMap());
        for (TaskDO scheduleJob : jobList) {
            quartzManager.addJob(entityToData(scheduleJob));
        }
    }

    @Override
    public void changeStatus(Long jobId, String cmd) throws SchedulerException {
        QuartzManager quartzManager = new QuartzManager();
        TaskDO scheduleJob = get(jobId);
        if (scheduleJob == null) {
            return;
        }
        if ("stop".equals(cmd)) {
            quartzManager.deleteJob(entityToData(scheduleJob));
            scheduleJob.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
        } else if ("start".equals(cmd)) {
            scheduleJob.setJobStatus(ScheduleJob.STATUS_RUNNING);
            quartzManager.addJob(entityToData(scheduleJob));
        }
        update(scheduleJob);
    }

    @Override
    public void updateCron(Long jobId) throws SchedulerException {
        QuartzManager quartzManager = new QuartzManager();
        TaskDO scheduleJob = get(jobId);
        if (scheduleJob == null) {
            return;
        }
        if (ScheduleJob.STATUS_RUNNING.equals(scheduleJob.getJobStatus())) {
            quartzManager.updateJobCron(entityToData(scheduleJob));
        }
        update(scheduleJob);
    }

    private ScheduleJob entityToData(TaskDO scheduleJobEntity) {
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setBeanClass(scheduleJobEntity.getBeanClass());
        scheduleJob.setCronExpression(scheduleJobEntity.getCronExpression());
        scheduleJob.setDescription(scheduleJobEntity.getDescription());
        scheduleJob.setIsConcurrent(scheduleJobEntity.getIsConcurrent());
        scheduleJob.setJobName(scheduleJobEntity.getJobName());
        scheduleJob.setJobGroup(scheduleJobEntity.getJobGroup());
        scheduleJob.setJobStatus(scheduleJobEntity.getJobStatus());
        scheduleJob.setMethodName(scheduleJobEntity.getMethodName());
        scheduleJob.setSpringBean(scheduleJobEntity.getSpringBean());
        return scheduleJob;
    }

    @Autowired
    private TaskDao taskScheduleJobMapper;

}
