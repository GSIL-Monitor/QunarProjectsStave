package com.qunar.fintech.plat.admin.query.service;


import com.qunar.fintech.plat.admin.query.entity.QueryCashReqRecord;
import com.qunar.fintech.plat.admin.query.vo.CashRemitRequest;
import com.qunar.fintech.plat.admin.query.vo.CashRemitResponse;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;

/**
 * 现金贷查询 Service
 */

public interface LoanCashQueryService {

    /**
     * 根据CashPlayMoneyRequest 分页查询现金贷打款记录
     * */
    CashRemitResponse queryCashRemit(CashRemitRequest request) throws Exception;

    /**
     * 分页查询现金贷借钱申请记录
     * @param request 查询参数
     * @return        查询记录结果
     */
    QueryResponse<QueryCashReqRecord> queryCashReq(QueryDto request, Page page) throws Exception;

}
