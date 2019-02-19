package com.qunar.fintech.plat.admin.hank.service;

import com.qunar.fintech.plat.admin.hank.entity.FileTask;
import com.qunar.fintech.plat.admin.support.web.Query;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by tongda.sun on 2018/12/10  15:57.
 * Description: 文件任务服务类
 */
public interface FileTaskService {

    /**
     * 通过 id 查询
     */
    FileTask selectById(Long id);

    /**
     * 通过 taskType & accountTime 查询
     */
    List<FileTask> selectByTaskTypeAndAccountTime(String taskType, Date accountTime);

    /**
     * 条件查询
     */
    List<FileTask> select(Query query);

    /**
     * 条件查询数量
     */
    int count(Query query);

    /**
     * 插入数据
     */
    int insertSelective(FileTask record);

    /**
     * 删除数据
     */
    int updateByPrimaryKeySelective(FileTask record);
}
