package com.qunar.fintech.plat.admin.hank.service;

import com.qunar.fintech.plat.admin.hank.entity.FtpConfig;
import com.qunar.fintech.plat.admin.support.web.Query;

import java.util.List;

/**
 * Created by tongda.sun on 2018/12/09  16:28.
 * Description: FTP 配置服务类
 */
public interface FtpConfigService {

    /**
     * 通过 id 查询
     */
    FtpConfig selectById(Long id);

    /**
     * 通过 taskType & receiver 查询
     */
    List<FtpConfig> selectByTaskTypeAndReceiver(String taskType,String receiver);

    /**
     * 条件查询
     */
    List<FtpConfig> select(Query query);

    /**
     * 条件查询数量
     */
    int count(Query query);

    /**
     * 插入数据
     */
    int insertSelective(FtpConfig record);

    /**
     * 更新数据
     */
    int updateByPrimaryKeySelective(FtpConfig record);
}
