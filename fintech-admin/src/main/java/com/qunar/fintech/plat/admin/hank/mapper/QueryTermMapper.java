package com.qunar.fintech.plat.admin.hank.mapper;

import com.qunar.fintech.plat.admin.hank.entity.QueryTerm;
import com.qunar.fintech.plat.admin.support.web.Query;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangzhiguo on 2017/10/24.
 */
@Repository
public interface QueryTermMapper{

    QueryTerm selectById(@Param("id") Long id);

    List<QueryTerm> selectByTaskTypeAndQueryMode(@Param("taskType") String taskType,
                                                 @Param("queryMode") Integer queryMode);

    List<QueryTerm> select(Query query);

    int count(Query query);

    int insertSelective(QueryTerm record);

    int updateByPrimaryKeySelective(QueryTerm record);
}
