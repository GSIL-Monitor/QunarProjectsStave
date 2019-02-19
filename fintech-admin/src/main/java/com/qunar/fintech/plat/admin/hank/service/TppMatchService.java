package com.qunar.fintech.plat.admin.hank.service;

import com.qunar.fintech.plat.admin.hank.entity.TppMatch;
import com.qunar.fintech.plat.admin.support.web.Query;

import java.util.List;

/**
 * Created by tongda.sun on 2018/12/08  12:04.
 * Description: 通道匹配查询服务
 */
public interface TppMatchService {


    /**
     * 根据 id 查询
     */
    TppMatch selectById(Long id);

    /**
     * 根据taskType & receiver 查询
     */
    List<TppMatch> selectByTaskTypeAndReceiver(String taskType, String receiver);

    /**
     * 条件查询
     */
    List<TppMatch> select(Query query);

    /**
     * 条件查询数据总量
     */
    int count(Query query);

    /**
     * 插入新记录
     */
    int insertSelective(TppMatch record);

    /**
     * 通过主键更新 文件数据
     */
    int updateByPrimaryKeySelective(TppMatch record);
}
