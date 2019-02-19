package com.qunar.fintech.plat.admin.hank.mapper;

import com.qunar.fintech.plat.admin.hank.entity.AccountFileLaunch;
import com.qunar.fintech.plat.admin.support.web.Query;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tongda.sun on 2018/12/13  11:33.
 * Description: 对账信息查询
 */
@Repository
public interface AccountFileMapper {

    List<String> selectTaskType(@Param("receiver") String receiver);

    List<String> selectReceiver(@Param("taskType") String taskType);

    String selectFileName(@Param("taskType") String taskType,
                          @Param("receiver") String receiver);

    List<AccountFileLaunch> selectAccountFileLaunchList(Query query);

    int selectAccountFileLaunchListCount(Query query);
}
