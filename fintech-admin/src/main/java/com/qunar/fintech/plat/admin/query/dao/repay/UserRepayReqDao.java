package com.qunar.fintech.plat.admin.query.dao.repay;

import com.qunar.fintech.plat.admin.query.entity.UserRepayReq;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dw.wang
 * @since 2016-03-07
 */
@Repository
public interface UserRepayReqDao {

    /**
     *  统计满足查询条件的记录数
     */
    int countByRequest(@Param("request") QueryDto reqVo);

    /**
     *  返回满足查询条件的记录
     */
    List<UserRepayReq> queryUserRepayReqList(@Param("request") QueryDto reqVo, @Param("page") Page page);

    /**
     * 根据serialNo查询UserRepayReq记录
     */
    UserRepayReq queryBySerialNo(String serialNo);
}
