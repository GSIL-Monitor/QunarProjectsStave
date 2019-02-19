package com.qunar.fintech.plat.admin.contract.dao.mapper;

import com.qunar.fintech.plat.admin.contract.dao.entity.UserProductHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: yupei.wang
 * Date: 2018/1/8
 */
@Repository
public interface UserProductHistoryDao {

    List<UserProductHistory> queryUpHisList(@Param("customId") String customId,
                                             @Param("platId") String platId,
                                             @Param("productNo") String productNo,
                                             @Param("bindOrgChannel") String bindOrgChannel);
}
