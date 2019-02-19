package com.qunar.fintech.plat.admin.query.dao.preloan;

import com.qunar.fintech.plat.admin.query.entity.TblCreditReq;
import com.qunar.fintech.plat.admin.query.entity.TblCreditReqDetail;
import com.qunar.fintech.plat.admin.query.vo.QueryPreCreditRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bob.li on 2015/11/30.
 */
@Repository
public interface TblCreditReqDao {
    /**
     * 根据条件查询信任付支付订单表信息
     *
     * @param request
     * @return
     */
    List<TblCreditReq> queryPreCreditInfoByRequest(@Param("request") QueryPreCreditRequest request);

    /**
     * 统计符合request预授信信息数目
     *
     * @param request
     * @return
     */
    int countPreCreditByRequest(@Param("request") QueryPreCreditRequest request);

    /**
     * 根据（`credit_no`,`user_id`,`tpp_code`）查询预授信请求扩展表
     */
    TblCreditReqDetail queryCreditReqDetailByUniq(String userId, String tppCode, String creditNo, String applyNo);
}
