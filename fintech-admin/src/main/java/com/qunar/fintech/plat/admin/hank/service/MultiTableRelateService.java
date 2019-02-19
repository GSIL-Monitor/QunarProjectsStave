package com.qunar.fintech.plat.admin.hank.service;

import com.qunar.fintech.plat.admin.hank.entity.MultiTableRelate;
import com.qunar.fintech.plat.admin.support.web.Query;

import java.util.List;

/**
 * Created by tongda.sun on 2018/12/09  17:26.
 * Description: 多表关联服务类
 */
public interface MultiTableRelateService {

    /**
     * 根据 id 查询关联信息
     */
    MultiTableRelate selectById(Long id);

    /**
     * 根据 taskType 查询关联信息
     */
    List<MultiTableRelate> selectByTaskType(String taskType);

    /**
     * 条件查询关联信息
     * 查询条件 ： id & taskType
     */
    List<MultiTableRelate> select(Query query);

    /**
     * 条件查询数据总量
     */
    int count(Query query);

    /**
     * 插入数据
     */
    int insertSelective(MultiTableRelate record);

    /**
     * 更新数据
     */
    int updateByPrimaryKeySelective(MultiTableRelate record);
}
