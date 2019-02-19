package com.qunar.fintech.plat.admin.system.quartz;

import com.qunar.fintech.plat.admin.system.dao.entity.ScheduleJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * All rights Reserved, Designed By www.jeeweb.cn
 *
 * @version V1.0
 * @title: QuartzJobFactory.java
 * @package cn.jeeweb.core.quartz.factory
 * @description: 计划任务执行处 无状态
 * @author: 王存见
 * @date: 2017年5月10日 上午12:24:41
 * @copyright: 2017 www.jeeweb.cn Inc. All rights reserved.
 */
public class QuartzJobFactory implements Job {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        TaskUtils.invokMethod(scheduleJob);
    }
}