package com.qunar.fintech.plat.admin.hank.service;

import com.qunar.fintech.plat.admin.hank.entity.QueryTerm;
import com.qunar.fintech.plat.admin.support.web.Query;

import java.util.List;

/**
 * Created by tongda.sun on 2018/12/08  16:43.
 * Description: 查询条件配置服务类
 */
public interface QueryTermService {

    /**
     * 通过 id 查询
     */
    QueryTerm selectById(Long id);

    /**
     * 通过 taskType & queryMode 查询
     */
    List<QueryTerm> selectByTaskTypeAndQueryMode(String taskType,Integer queryMode);

    /**
     * 条件查询
     */
    List<QueryTerm> select(Query query);

    /**
     * 条件查询数量
     */
    int count(Query query);

    /**
     * 插入
     */
    int insertSelective(QueryTerm record);

    /**
     * 更新
     */
    int updateByPrimaryKeySelective(QueryTerm record);
}
