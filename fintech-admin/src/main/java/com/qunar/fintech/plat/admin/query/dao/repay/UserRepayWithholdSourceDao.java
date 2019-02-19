package com.qunar.fintech.plat.admin.query.dao.repay;


import com.qunar.fintech.plat.admin.query.entity.UserRepayWithholdSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shuaifeng.gao
 * @since 2017-06-12 15:31
 **/
@Repository
public interface UserRepayWithholdSourceDao {
    /**
     * 根据批次订单号查询
     * @param reqSerialNo 请求订单号
     */
    List<UserRepayWithholdSource> selectByReqSerialNo(String reqSerialNo);

}
