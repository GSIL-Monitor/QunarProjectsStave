package com.qunar.fintech.plat.admin.hank.mapper;

import com.qunar.fintech.plat.admin.hank.entity.TppConfig;
import com.qunar.fintech.plat.admin.support.web.Query;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TppConfigMapper{

    TppConfig selectById(@Param("id") Long id);

    TppConfig selectByTaskTypeAndReceiver(@Param("taskType") String taskType,
                                          @Param("receiver") String receiver);

    List<TppConfig> select(Query query);

    int count(Query query);

    int insertSelective(TppConfig record);

    int updateByPrimaryKeySelective(TppConfig record);
}
