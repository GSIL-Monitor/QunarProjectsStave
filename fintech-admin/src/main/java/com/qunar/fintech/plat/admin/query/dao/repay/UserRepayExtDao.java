package com.qunar.fintech.plat.admin.query.dao.repay;

import com.qunar.fintech.plat.admin.query.entity.UserRepayExt;
import com.qunar.fintech.plat.admin.query.vo.QueryLockOrderReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dw.wang
 * @since 2016-03-16.
 */
@Repository
public interface UserRepayExtDao {

    /**
     *  返回满足查询条件的记录
     */
    List<UserRepayExt> queryUserRepayExt(@Param("serialNo") String serialNo);



    List<UserRepayExt> selectForBackRefund(String userId, String loanProvideNo);

    /**
     * 锁定订单查询
     * @param query
     * @return
     */
    List<UserRepayExt> queryUserRepayExtByLockQuery(QueryLockOrderReq query);
}
