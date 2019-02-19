package com.qunar.fintech.plat.admin.query.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.collect.Lists;
import com.qunar.fintech.plat.admin.query.dao.ious.TblLoanCashRemitMapper;
import com.qunar.fintech.plat.admin.query.dao.ious.TblLoanCashReqDao;
import com.qunar.fintech.plat.admin.query.dao.repay.UserRepayPlanDao;
import com.qunar.fintech.plat.admin.query.entity.QueryCashReqRecord;
import com.qunar.fintech.plat.admin.query.entity.UserRepayPlan;
import com.qunar.fintech.plat.admin.query.service.CommonService;
import com.qunar.fintech.plat.admin.query.service.LoanCashQueryService;
import com.qunar.fintech.plat.admin.query.support.SuggestQconfig;
import com.qunar.fintech.plat.admin.query.vo.CashRemit;
import com.qunar.fintech.plat.admin.query.vo.CashRemitRequest;
import com.qunar.fintech.plat.admin.query.vo.CashRemitResponse;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.pay.finance.qf.commons.utils.base.CalcUtil;
import com.qunar.pay.finance.qf.commons.utils.base.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


/**
 * 现金贷req Service
 */
@Service
public class LoanCashQueryServiceImpl implements LoanCashQueryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanCashQueryServiceImpl.class);

    @Resource
    private TblLoanCashReqDao tblLoanCashReqDao;

    @Resource
    private UserRepayPlanDao userRepayPlanDao;

    @Resource
    private TblLoanCashRemitMapper tblLoanCashRemitMapper;

    @Resource
    private CommonService commonService;

    @Resource
    private SuggestQconfig suggestQconfig;

    private static final String PREFIX = "LOAN";

    /**
     * 打钱查询
     */
    @Override
    public CashRemitResponse queryCashRemit(CashRemitRequest request) throws Exception {
        LOGGER.info("LoanCashReqServiceImpl#queryCashRemit start,request={},page={}", request);

        commonService.addUserIdWithCheck(request);
        int totalRecord = tblLoanCashRemitMapper.countCashRemitByRequest(request);
        List<CashRemit> cashRemits = Lists.newArrayList();
        if (totalRecord > 0) {
            cashRemits = tblLoanCashRemitMapper.queryCashRemitByRequest(request);
        }
        //设置手机号和姓名
        commonService.addUserNameAndMobile(cashRemits);
        CashRemitResponse cashRemitResponse = new CashRemitResponse();
        cashRemitResponse.setCashRemitList(cashRemits);
        LOGGER.info("LoanCashReqServiceImpl#queryCashRemit end,cashRemitResponse={}", cashRemitResponse);
        return cashRemitResponse;
    }

    /**
     * 查询现金贷借钱申请记录
     * @param request 查询参数
     * @return        查询记录结果
     */
    @Override
    public QueryResponse<QueryCashReqRecord> queryCashReq(QueryDto request, Page page) throws Exception {

        commonService.mobileToId(request);
        commonService.addUserIdWithCheck(request);
        int totalRecord = tblLoanCashReqDao.countCashReqByRequest(request);
        LOGGER.info("queryCashReq#match record number is {}", totalRecord);

        List<QueryCashReqRecord> queryCashReqRecords = Lists.newArrayList();
        if (totalRecord > 0) {
            queryCashReqRecords = tblLoanCashReqDao.pagingQueryCashReqRecordByRequest(request, page);
            if (CollectionUtils.isEmpty(queryCashReqRecords)) {
                throw new IllegalArgumentException("database query tbl_loan_cash_req exception");
            }
            for(QueryCashReqRecord record : queryCashReqRecords) {
                if(StringUtils.isNotEmpty(record.getErrorCode())) {
                    String suggest = suggestQconfig.getSuugest(record.getErrorCode());
                    record.setSuggest(suggest);
                }
            }
            /* 计算当前欠款金额 */
//            for (QueryCashReqRecord queryCashReqRecord : queryCashReqRecords) {
//                BigDecimal totalArrearage = getTotalArrearage(queryCashReqRecord);
//                queryCashReqRecord.setArrearage(totalArrearage);
//            }
        }
        commonService.addUserNameAndMobile(queryCashReqRecords);
        QueryResponse<QueryCashReqRecord> queryResponse = new QueryResponse<>();
        queryResponse.setRows(queryCashReqRecords);
        queryResponse.setTotal(totalRecord);
        return queryResponse;
    }

    /**
     * 获取当前欠款总金额
     *
     */
    private BigDecimal getTotalArrearage(QueryCashReqRecord queryCashReqRecord){
        List<UserRepayPlan> userRepayPlanList = userRepayPlanDao.selectByLoanProvideNo(queryCashReqRecord.getLoanProvideNo());
        BigDecimal totalArrearage = BigDecimal.ZERO;
        if (CollectionUtils.isEmpty(userRepayPlanList)){
            LOGGER.info("没有借据号为{}对应的还款计划记录, 返回-1", queryCashReqRecord.getLoanProvideNo());
            return new BigDecimal("-1");
        }
        for (UserRepayPlan userRepayPlan : userRepayPlanList) {
            totalArrearage = CalcUtil.add(totalArrearage, userRepayPlan.getUserRepayTotalAmount());
        }
        return totalArrearage;
    }

}
