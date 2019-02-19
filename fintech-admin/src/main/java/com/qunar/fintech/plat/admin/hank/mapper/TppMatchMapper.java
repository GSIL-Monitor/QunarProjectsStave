package com.qunar.fintech.plat.admin.hank.mapper;

import com.qunar.fintech.plat.admin.hank.entity.TppMatch;
import com.qunar.fintech.plat.admin.support.web.Query;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangzhiguo on 2017/10/25.
 */
@Repository
public interface TppMatchMapper{

    TppMatch selectById (@Param("id") Long id);

    List<TppMatch> selectByTaskTypeAndReceiver(@Param("taskType") String taskType,
                                               @Param("receiver") String receiver);

    List<TppMatch> select(Query query);

    int count(Query query);

    int insertSelective(TppMatch record);

    int updateByPrimaryKeySelective(TppMatch record);
}
