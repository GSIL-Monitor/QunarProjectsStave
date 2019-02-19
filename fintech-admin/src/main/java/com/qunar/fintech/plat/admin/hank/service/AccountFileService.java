package com.qunar.fintech.plat.admin.hank.service;


import com.qunar.fintech.plat.admin.hank.entity.AccountFileLaunch;
import com.qunar.fintech.plat.admin.support.web.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by tongda.sun on 2018/12/13  11:32.
 * Description:  对账文件服务类
 */
public interface AccountFileService {

    /**
     * 条件查询 TaskType 列表
     * @param receiver
     */
    List<String> getTaskTypes(String receiver);

    /**
     * 条件查询 receiver 列表
     * @param taskType
     */
    List<String> getReceivers(String taskType);

    /**
     * 根据taskType & receiver 唯一键查询文件名
     */
    String getFileName(String taskType, String receiver);

    /**
     * 条件查询对账文件执行记录
     */
    List<AccountFileLaunch> getAccountFileLaunchList(Query query, String reqUser);

    /**
     * 条件查询对账文件执行记录数量
     */
    int getAccountFileLaunchListCount(Query query);

    /**
     * 执行对账文件处理请求
     */
    void export(Map<String,Object> params, String exportUser);
}
