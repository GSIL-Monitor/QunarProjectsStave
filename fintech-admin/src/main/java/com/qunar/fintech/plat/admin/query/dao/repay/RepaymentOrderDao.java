package com.qunar.fintech.plat.admin.query.dao.repay;

import com.qunar.fintech.plat.admin.query.entity.TblRepaymentReq;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import com.qunar.fintech.plat.admin.query.vo.QueryRepayRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by baron.jiang on 2015/3/6.
 */
@Repository
public interface RepaymentOrderDao {

    /**
     * 根据条件查询信任付还款订单表信息
     *
     * @param request
     * @return
     */
    List<QueryRepayRecord> queryRepaymentOrderByRequest(@Param("request") QueryDto request,@Param("page") Page page);

    /**
     * 统计符合request信任付还款记录信息数目
     *
     * @param request
     * @return
     */
    int countRepaymentOrderByRequest(@Param("request") QueryDto request);

    /**
     * 还款查询
     * @param loanProvideNo
     * @return
     */
    public List<QueryRepayRecord> queryRepaymentOrderDetail(String loanProvideNo);

    /**
     * 根据还款订单号查询请求网关记录
     * @param repayOrderNo
     * @return
     */
    List<TblRepaymentReq> queryRepaymentReqs(String repayOrderNo);

    /**
     * 根据还款请求流水号查询请求网关记录
     * @param repayReqNo
     * @return
     */
    TblRepaymentReq queryRepaymentReqByUniq(String repayReqNo);

    /**
     * 根据order_no查repaymentOrder
     * @param orderNo
     * @return
     */
    QueryRepayRecord queryRepaymentOrderByOrderNo(String orderNo);

}

