package com.qunar.fintech.plat.admin.query.dao.repay;


import com.qunar.fintech.plat.admin.query.entity.UserRepayReqDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dw.wang
 * @since 2016-03-07
 */
@Repository
public interface UserRepayReqDetailDao {

    /**
     *  返回满足查询条件的记录
     */
    List<UserRepayReqDetail> queryUserRepayReqDetail(@Param("reqSerialNo") String reqSerialNo);
}
