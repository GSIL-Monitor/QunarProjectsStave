package com.qunar.fintech.plat.admin.hank.mapper;

import com.qunar.fintech.plat.admin.hank.entity.FtpConfig;
import com.qunar.fintech.plat.admin.support.web.Query;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangzhiguo on 2017/10/12.
 */
@Repository
public interface FtpConfigMapper{

    FtpConfig selectById(@Param("id") Long id);

    List<FtpConfig> selectByTaskTypeAndReceiver(@Param("taskType") String taskType,
                                                @Param("receiver") String receiver);

    List<FtpConfig> select(Query query);

    int count(Query query);

    int insertSelective(FtpConfig record);

    int updateByPrimaryKeySelective(FtpConfig record);
}
