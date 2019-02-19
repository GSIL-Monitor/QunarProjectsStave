package com.qunar.fintech.plat.admin.query.service;

import com.qunar.fintech.plat.admin.query.entity.TblLoanInfo;
import com.qunar.fintech.plat.admin.query.entity.TblScheduleDetail;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.PayDetailParamVo;
import com.qunar.fintech.plat.admin.query.vo.QueryAccountRecord;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import com.qunar.fintech.plat.admin.query.vo.QueryRefundRecord;
import com.qunar.fintech.plat.admin.query.vo.QueryRepayRecord;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;

import java.util.List;

public interface QueryService {

    /**
     * 查询账户信息
     * @param query
     * @return
     */
    QueryResponse<QueryAccountRecord> queryAccount(QueryDto query, Page page);

    /**
     * 查询退款信息
     * @param query
     * @return
     */
    QueryResponse<QueryRefundRecord> queryRefund(QueryDto query, Page page);

    /**
     * 查询还款信息
     * @param query
     * @return
     */
    QueryResponse<QueryRepayRecord> queryRepay(QueryDto query, Page page);

    /**
     * 查询借去花贷款信息
     * @param query
     * @return
     */
    QueryResponse<TblLoanInfo> queryLoanIousReq(QueryDto query, Page page);

    /**
     * 查询还款计划
     * @param loanProvideNo
     * @return
     */
    List<TblScheduleDetail> queryScheduleDetails(String loanProvideNo);

    /**
     * 查询支付详情
     * @param orderNo
     * @return
     */
    PayDetailParamVo queryPayDetail(String orderNo);

}
