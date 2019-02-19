package com.qunar.fintech.plat.admin.hank.service;

import com.qunar.fintech.plat.admin.hank.entity.TppConfig;
import com.qunar.fintech.plat.admin.support.web.Query;

import java.util.List;

/**
 * Created by tongda.sun on 2018/12/06  17:19.
 * Description: 通道配置服务类
 */
public interface TppConfigService {

    /**
     * 根据 id 查询
     */
    TppConfig selectById(Long id);

    /**
     * 根据 taskType 和 receiver 查询
     */
    TppConfig selectByTaskTypeAndReceiver(String taskType, String receiver);

    /**
     * 条件查询
     */
    List<TppConfig> select(Query query);

    /**
     * 条件查询数据总量
     */
    int count(Query query);

    /**
     * 插入
     */
    int insertSelective(TppConfig record);

    /**
     * 通过主键更新
     */
    int updateByPrimaryKeySelective(TppConfig record);
}
