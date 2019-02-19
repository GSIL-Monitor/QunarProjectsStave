package com.qunar.fintech.plat.admin.hank.mapper;

import com.qunar.fintech.plat.admin.hank.entity.MultiTableRelate;
import com.qunar.fintech.plat.admin.support.web.Query;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangzhiguo on 2017/9/29.
 */
@Repository
public interface MultiTableRelateMapper{

    MultiTableRelate selectById(@Param("id") Long id);

    List<MultiTableRelate> selectByTaskType(@Param("taskType") String taskType);

    List<MultiTableRelate> select(Query query);

    int count(Query query);

    int insertSelective(MultiTableRelate record);

    int updateByPrimaryKeySelective(MultiTableRelate record);
}
