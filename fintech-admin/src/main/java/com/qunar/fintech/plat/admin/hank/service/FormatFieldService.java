package com.qunar.fintech.plat.admin.hank.service;

import com.qunar.fintech.plat.admin.hank.entity.FormatField;
import com.qunar.fintech.plat.admin.support.web.Query;

import java.util.List;

/**
 * Created by tongda.sun on 2018/12/09  19:07.
 * Description: 输出字段规则服务类
 */
public interface FormatFieldService {

    /**
     * 通过 id 查询
     */
    FormatField selectById(Long id);

    /**
     * 通过 taskType & receiver 查询
     */
    List<FormatField> selectByTaskTypeAndReceiver(String taskType,String receiver);

    /**
     * 条件查询
     */
    List<FormatField> select(Query query);

    /**
     * 条件查询数量
     */
    int count(Query query);

    /**
     * 插入
     */
    int insertSelective(FormatField record);

    /**
     * 更新
     */
    int updateByPrimaryKeySelective(FormatField record);
}
