package com.qunar.fintech.plat.admin.query.dao.repay;

import com.qunar.fintech.plat.admin.query.entity.UserRepayWithholdReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dw.wang
 * @since 2016-03-07
 */
@Repository
public interface UserRepayWithholdReqDao {

    /**
     * 根据流水号查询用户的还款强扣记录表
     * @param orderNo 订单号
     */
    List<UserRepayWithholdReq> queryUserRepayWithholdReqByOrderNo(@Param("orderNo") String orderNo);

}
