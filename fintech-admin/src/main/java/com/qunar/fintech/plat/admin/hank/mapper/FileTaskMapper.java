package com.qunar.fintech.plat.admin.hank.mapper;

import com.qunar.fintech.plat.admin.hank.entity.FileTask;
import com.qunar.fintech.plat.admin.support.web.Query;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by yangzhiguo on 2017/10/19.
 */
@Repository
public interface FileTaskMapper{

    FileTask selectById(@Param("id") Long id);

    List<FileTask> selectByTaskTypeAndAccountTime(@Param("taskType") String taskType,
                                                  @Param("accountTime") Date accountTime);

    List<FileTask> select(Query query);

    int count(Query query);

    int insertSelective(FileTask record);

    int updateByPrimaryKeySelective(FileTask record);
}
