package com.qunar.fintech.plat.admin.hank.mapper;

import com.qunar.fintech.plat.admin.hank.entity.FormatField;
import com.qunar.fintech.plat.admin.support.web.Query;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangzhiguo on 2017/10/24.
 */
@Repository
public interface FormatFieldMapper{

    FormatField selectById(@Param("id") Long id);

    List<FormatField> selectByTaskTypeAndReceiver(@Param("taskType") String taskType,
                                                  @Param("receiver") String receiver);

    List<FormatField> select(Query query);

    int count(Query query);

    int insertSelective(FormatField record);

    int updateByPrimaryKeySelective(FormatField record);
}
