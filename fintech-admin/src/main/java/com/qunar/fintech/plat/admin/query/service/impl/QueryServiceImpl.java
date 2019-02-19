package com.qunar.fintech.plat.admin.query.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.qunar.es.model.PrimaryKey;
import com.qunar.fintech.plat.admin.query.dao.ious.IousRefundDao;
import com.qunar.fintech.plat.admin.query.dao.ious.TblIousPayDao;
import com.qunar.fintech.plat.admin.query.dao.ious.TblLoanCashReqDao;
import com.qunar.fintech.plat.admin.query.dao.preloan.IousUserInfoDao;
import com.qunar.fintech.plat.admin.query.dao.preloan.TblCreditReqDao;
import com.qunar.fintech.plat.admin.query.dao.repay.LoanInfoDao;
import com.qunar.fintech.plat.admin.query.dao.repay.RepaymentOrderDao;
import com.qunar.fintech.plat.admin.query.dao.repay.ScheduleDetailDao;
import com.qunar.fintech.plat.admin.query.dao.repay.TblOperRecordMapper;
import com.qunar.fintech.plat.admin.query.dao.repay.UserRepayWithholdReqDao;
import com.qunar.fintech.plat.admin.query.dao.repay.UserRepayWithholdSourceDao;
import com.qunar.fintech.plat.admin.query.entity.QueryCashReqRecord;
import com.qunar.fintech.plat.admin.query.entity.TblCreditReq;
import com.qunar.fintech.plat.admin.query.entity.TblIousPay;
import com.qunar.fintech.plat.admin.query.entity.TblIousUserInfo;
import com.qunar.fintech.plat.admin.query.entity.TblLoanInfo;
import com.qunar.fintech.plat.admin.query.entity.TblOperRecord;
import com.qunar.fintech.plat.admin.query.entity.TblScheduleDetail;
import com.qunar.fintech.plat.admin.query.entity.UserProductInfo;
import com.qunar.fintech.plat.admin.query.entity.UserRepayWithholdReq;
import com.qunar.fintech.plat.admin.query.entity.UserRepayWithholdSource;
import com.qunar.fintech.plat.admin.query.enums.PaySwitchEnum;
import com.qunar.fintech.plat.admin.query.enums.SignStatusEnum;
import com.qunar.fintech.plat.admin.query.service.CommonService;
import com.qunar.fintech.plat.admin.query.service.QueryService;
import com.qunar.fintech.plat.admin.query.service.RepayService;
import com.qunar.fintech.plat.admin.query.service.UserProductInfoService;
import com.qunar.fintech.plat.admin.query.support.SuggestQconfig;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.PayDetail;
import com.qunar.fintech.plat.admin.query.vo.PayDetailParamVo;
import com.qunar.fintech.plat.admin.query.vo.QueryAccountRecord;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import com.qunar.fintech.plat.admin.query.vo.QueryPreCreditRequest;
import com.qunar.fintech.plat.admin.query.vo.QueryRefundRecord;
import com.qunar.fintech.plat.admin.query.vo.QueryRepayRecord;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.pay.finance.common.api.QResponse;
import com.qunar.pay.finance.preloan.api.dto.request.QueryUserContractRequestDto;
import com.qunar.pay.finance.preloan.api.dto.response.UserContractWrapper;
import com.qunar.pay.finance.preloan.api.facade.PreLoanQueryFacade;
import com.qunar.pay.finance.qf.commons.utils.base.DateUtil;
import com.qunar.pay.finance.repaykernel.api.userrepay.enums.RecordRefundCategoryEnum;
import com.qunar.pay.g2.api.account.dto.credit.CreditSigningQueryResultDto;
import com.qunar.pay.g2.api.account.dto.credit.CreditUserSignQueryDto;
import com.qunar.pay.g2.api.account.enumtype.FinancePrdCode;
import com.qunar.pay.g2.api.account.service.credit.CreditSigningManagementFacade;
import com.qunar.pay.g2.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by baron.jiang on 2015/2/4.
 */
@Service
public class QueryServiceImpl implements QueryService {
    private static final Logger logger = LoggerFactory.getLogger(QueryServiceImpl.class);
    private static final Integer LAST4DIGITS = 4;

    @Autowired
    private IousUserInfoDao iousUserInfoDao;

    @Autowired
    private LoanInfoDao loanInfoDao;

    @Autowired
    private ScheduleDetailDao scheduleDetailDao;

    @Autowired
    private RepaymentOrderDao repaymentOrderDao;

    @Autowired
    private IousRefundDao iousRefundDao;

    @Autowired
    private TblCreditReqDao tblCreditReqDao;

    @Resource
    private CreditSigningManagementFacade creditSigningManagementFacade;

    @Autowired
    private TblIousPayDao tblIousPayDao;

    @Resource
    private UserProductInfoService userProductInfoService;

    @Resource
    private CommonService commonService;

    @Resource
    private TblLoanCashReqDao tblLoanCashReqDao;

    @Resource
    private TblOperRecordMapper tblOperRecordMapper;

    @Resource
    private UserRepayWithholdSourceDao repayWithholdSourceDao;

    @Resource
    private SuggestQconfig suggestQconfig;

    @Autowired
    private PreLoanQueryFacade preLoanQueryFacade;

    @Resource
    private UserRepayWithholdReqDao userRepayWithholdReqDao;

    @Resource
    private RepayService repayService;

    @Override
    public QueryResponse<QueryAccountRecord> queryAccount(QueryDto query, Page page) {
        logger.info("queryService.queryAccount req query is {}",query);
        query.setMainChannel(query.getOrgChannel());
        commonService.mobileToId(query);
        List<QueryAccountRecord> queryAccountRecords = Lists.newArrayList();
        // 设置主uid
        if (StringUtils.isNotBlank(query.getUserId())) {
            List<UserProductInfo> infoList = userProductInfoService.queryUserProductInfoByUserId(query.getIdentity(), query.getProductNo(), query.getUserId());
            logger.info("queryIousAccount UserProductInfo={}", infoList);
            if (CollectionUtils.isNotEmpty(infoList)) {
                query.setUserId(infoList.get(0).getMainUserId());
                String mainChannel = infoList.get(0).getMainChannelCode();
                if(StringUtils.isNotBlank(mainChannel) && infoList.get(0).getChannelCode().equals(query.getOrgChannel())) {
                    query.setMainChannel(mainChannel);
                } else {
                    query.setMainChannel(query.getOrgChannel());
                }
            }
        }
        int totalRecord = iousUserInfoDao.countIousAccountByRequest(query);
        logger.info("queryAccount#match record number is {}", totalRecord);
        if (totalRecord > 0) {
            List<TblIousUserInfo> tblIousUserInfos = iousUserInfoDao.queryIousAccountByRequest(query,page);
            if (CollectionUtils.isEmpty(tblIousUserInfos)) {
                logger.error("database query tbl_ious_user_info exception");
                return new QueryResponse<>();
            } else {
                for (TblIousUserInfo tblIousUserInfo : tblIousUserInfos) {

                    tblIousUserInfo.setDebtAmountAll(scheduleDetailDao.queryDebtAmountAllByUserIdAndProductNo(tblIousUserInfo.getUserId(), tblIousUserInfo.getProductNo(), tblIousUserInfo.getTppCode())); // 欠款金额
                    tblIousUserInfo.setCurrentOverdueCount(scheduleDetailDao.queryCurrentOverdueCountByUserIdAndProductNo(tblIousUserInfo.getUserId(), tblIousUserInfo.getProductNo(), tblIousUserInfo.getTppCode())); // 当前逾期订单数量
                    tblIousUserInfo.setDebtCount(scheduleDetailDao.queryDebtCountByUserIdAndProductNo(tblIousUserInfo.getUserId(), tblIousUserInfo.getProductNo(), tblIousUserInfo.getTppCode()));
                    tblIousUserInfo.setCurrentOverdueAmount(scheduleDetailDao.queryCurrentOverdueAmountByUserIdAndProductNo(tblIousUserInfo.getUserId(), tblIousUserInfo.getProductNo(), tblIousUserInfo.getTppCode()));
                    QueryAccountRecord queryAccountRecord = toQueryAccountRecord(tblIousUserInfo);
                    queryAccountRecord.setTotal(totalRecord);
                    //查询主通道额度和已用额度
                    QueryUserContractRequestDto requestDto = new QueryUserContractRequestDto();
                    requestDto.setUserId(queryAccountRecord.getUserId());
                    requestDto.setProductNo(queryAccountRecord.getProductNo());
                    requestDto.setOrgChannel(queryAccountRecord.getChannel());
                    QResponse<UserContractWrapper> response = preLoanQueryFacade.queryUserContract(requestDto);
                    if(response != null && response.isSuccess() && response.getData() != null && response.getData().getVirtualContrat() != null) {
                        queryAccountRecord.setMainAmount(response.getData().getVirtualContrat().getContractAmount());
                    }
//                    CreditSigningQueryResultDto mainResultDto = creditSigningManagementFacade.queryMainSigningInfo(tblIousUserInfo.getUserId());
//                    if(mainResultDto != null && "2".equals(mainResultDto.getStatus()) && CollectionUtils.isNotEmpty(mainResultDto.getDetials())){
//                        queryAccountRecord.setMainAmount(mainResultDto.getDetials().get(0).getAmount());
//                    }
                    CreditUserSignQueryDto creditSigningDto = new CreditUserSignQueryDto();
                    creditSigningDto.setUserId(tblIousUserInfo.getUserId());
                    creditSigningDto.setServId(tblIousUserInfo.getTppCode());
                    creditSigningDto.setPrdCode(FinancePrdCode.toEnum(tblIousUserInfo.getProductNo()));
                    CreditSigningQueryResultDto resultDto = creditSigningManagementFacade.querySigningInfos(creditSigningDto);
                    if(resultDto != null && "2".equals(resultDto.getStatus()) && CollectionUtils.isNotEmpty(resultDto.getDetials())){
                        queryAccountRecord.setUsedAmount(resultDto.getDetials().get(0).getUsedAmount());
                    }
                    QueryPreCreditRequest creditRequest = new QueryPreCreditRequest();
                    creditRequest.setProductNo(tblIousUserInfo.getProductNo());
                    creditRequest.setUserId(tblIousUserInfo.getUserId());
                    creditRequest.setTppCode(tblIousUserInfo.getTppCode());
                    List<TblCreditReq> tblCreditReqs = tblCreditReqDao.queryPreCreditInfoByRequest(creditRequest);
                    if (CollectionUtils.isNotEmpty(tblCreditReqs)) {
                        queryAccountRecord.setCreditType(tblCreditReqs.get(0).getCreditType());
                        queryAccountRecord.setReqStatus(tblCreditReqs.get(0).getReqStatus());
                        queryAccountRecord.setTppCode(tblCreditReqs.get(0).getTppCode());
                    }
                    if(StringUtils.isNotBlank(query.getOrgChannel())) {
                        queryAccountRecord.setOrgChannel(query.getOrgChannel());
                    }
                    try{
                        queryAccountRecord.setValidDay(DateUtil.daysDiff(tblIousUserInfo.getIousBeginTime(),tblIousUserInfo.getIousEndTime()));
                    } catch (Exception e){
                        logger.error("时间转换错误",e);
                    }
                    queryAccountRecords.add(queryAccountRecord);
                }
            }
        } else {
            queryAccountRecords = null;
        }
        QueryResponse<QueryAccountRecord> queryResponse = new QueryResponse<>();
        queryResponse.setRows(queryAccountRecords);
        queryResponse.setTotal(totalRecord);
        return queryResponse;
    }

    public QueryResponse<QueryRefundRecord> queryRefund(QueryDto query, Page page){
        List<QueryRefundRecord> refundInfos = Lists.newArrayList();
        commonService.mobileToId(query);
        // 设置主uid
        if (StringUtils.isNotBlank(query.getUserId())) {
            List<UserProductInfo> infoList = userProductInfoService.queryUserProductInfoByUserId(query.getIdentity(), query.getProductNo(), query.getUserId());
            logger.info("queryRefund UserProductInfo={}", infoList);
            if (CollectionUtils.isNotEmpty(infoList)) {
                query.setUserId(infoList.get(0).getMainUserId());
            }
        }
        try {
            commonService.addUserIdWithCheck(query);
        } catch (Exception e) {
            logger.error("addUserIdWithCheck error",e);
            return new QueryResponse<>();
        }

        Integer totalRecord = iousRefundDao.countIousRefundByRequest(query);
        logger.info("queryRefund#match record number is :{}", totalRecord);

        if (totalRecord > 0) {
            refundInfos = iousRefundDao.queryIousRefundByRequest(query,page);
            if (CollectionUtils.isEmpty(refundInfos)) {
                logger.error("refundInfos is empty");
                return new QueryResponse<>();
            }
            for(QueryRefundRecord refundRecord : refundInfos) {
                refundRecord.setRefundDetail(reqRepayForRefundDetail(refundRecord.getLoanProvideNo()));
                if(StringUtils.isNotEmpty(refundRecord.getErrorCode())) {
                    String suggest = suggestQconfig.getSuugest(refundRecord.getErrorCode());
                    refundRecord.setSuggest(suggest);
                }
            }
        }

        //设置用户姓名和手机号
        //addUserNameAndMobile(queryRefundRecords);
        commonService.addUserNameAndMobile(refundInfos);
        QueryResponse<QueryRefundRecord> queryResponse = new QueryResponse<>();
        queryResponse.setRows(refundInfos);
        queryResponse.setTotal(totalRecord);
        return queryResponse;
    }

    public QueryResponse<QueryRepayRecord> queryRepay(QueryDto query, Page page){
        commonService.mobileToId(query);
        // 设置主uid
        if (StringUtils.isNotBlank(query.getUserId())) {
            List<UserProductInfo> infoList = userProductInfoService.queryUserProductInfoByUserId(query.getIdentity(), query.getProductNo(), query.getUserId());
            logger.info("queryRepayment UserProductInfo={}", infoList);
            if (CollectionUtils.isNotEmpty(infoList)) {
                query.setUserId(infoList.get(0).getMainUserId());
            }
        }

        try {
            commonService.addUserIdWithCheck(query);
        } catch (Exception e) {
            logger.error("addUserIdWithCheck error",e);
            return new QueryResponse<>();
        }
        int totalRecord = repaymentOrderDao.countRepaymentOrderByRequest(query);
        logger.info("queryRepayment#match record number is {}", totalRecord);
        List<QueryRepayRecord> repaymentOrders = Lists.newArrayList();
        if (totalRecord > 0) {
            repaymentOrders = repaymentOrderDao.queryRepaymentOrderByRequest(query,page);
            if (CollectionUtils.isEmpty(repaymentOrders)) {
                logger.error("database query tbl_repayment_order exception");
                return new QueryResponse<>();
            }
            for (QueryRepayRecord repaymentOrder : repaymentOrders) {
                repaymentOrder.setStatusName(repaymentOrder.transRepayStatus(repaymentOrder.getStatus()));
                //添加还款来源
                repaymentOrder.setChannel(addRepayEntryAndChannel(repaymentOrder.getReqSerialNo()));
                //添加业务单号
                addLoanInfoData(repaymentOrder);

                if(StringUtils.isNotEmpty(repaymentOrder.getErrorCode())) {
                    String suggest = suggestQconfig.getSuugest(repaymentOrder.getErrorCode());
                    repaymentOrder.setSuggest(suggest);
                }
            }
            //添加姓名,身份证号和手机号
            commonService.addUserNameAndMobile(repaymentOrders);
        }
        QueryResponse<QueryRepayRecord> queryResponse = new QueryResponse<>();
        queryResponse.setRows(repaymentOrders);
        queryResponse.setTotal(totalRecord);
        return queryResponse;
    }

    public QueryResponse<TblLoanInfo> queryLoanIousReq(QueryDto query, Page page){
        commonService.mobileToId(query);
        // 设置主uid
        if (StringUtils.isNotBlank(query.getUserId())) {
            List<UserProductInfo> infoList = userProductInfoService.queryUserProductInfoByUserId(query.getIdentity(), query.getProductNo(), query.getUserId());
            logger.info("queryLoanIousReq UserProductInfo={}", infoList);
            if (CollectionUtils.isNotEmpty(infoList)) {
                query.setUserId(infoList.get(0).getMainUserId());
            }
        }
        try {
            commonService.addUserIdWithCheck(query);
        } catch (Exception e) {
            logger.error("addUserIdWithCheck error",e);
            return new QueryResponse<>();
        }
        int totalRecord = loanInfoDao.countLoanInfoByRequest(query);
        logger.info("queryLoanIousReq#match record number is {}", totalRecord);
        List<TblLoanInfo> loanInfos = Lists.newArrayList();
        if(totalRecord > 0) {
            loanInfos = loanInfoDao.queryLoanInfoByRequest(query,page);
            if (CollectionUtils.isEmpty(loanInfos)) {
                logger.error("database query tbl_loan_info exception");
                return new QueryResponse<>();
            }
            for(TblLoanInfo loanInfo : loanInfos) {
                loanInfo.setDebtAmountAll(scheduleDetailDao.queryDebtAmountAllByLoanProviderNo(loanInfo.getLoanProvideNo())); // 当前欠款金额
                loanInfo.setCurrentOverdueCount(scheduleDetailDao.queryCurrentOverdueCountByProvideNo(loanInfo.getLoanProvideNo()));
                loanInfo.setOverDueAmount(scheduleDetailDao.queryCurrentOverdueAmountByProvideNo(loanInfo.getLoanProvideNo()));
            }
            //添加姓名,身份证号和手机号
            commonService.addUserNameAndMobile(loanInfos);
        }
        QueryResponse<TblLoanInfo> queryResponse = new QueryResponse<>();
        queryResponse.setRows(loanInfos);
        queryResponse.setTotal(totalRecord);
        return queryResponse;
    }

    public List<TblScheduleDetail> queryScheduleDetails(String loanProvideNo) {
        return scheduleDetailDao.queryDetailByLoanProvideNo(loanProvideNo);
    }

    public PayDetailParamVo queryPayDetail(String orderNo) {
        List<PrimaryKey> primaryKeys = Lists.newArrayList();
        try {
            //查询user_repay_withhold_req
            List<UserRepayWithholdReq> withholdReqList = userRepayWithholdReqDao.queryUserRepayWithholdReqByOrderNo(orderNo);
            logger.info("#payDetail#withholdReqList is {}",withholdReqList);
            if (CollectionUtils.isNotEmpty(withholdReqList)){
                for (UserRepayWithholdReq withholdReq : withholdReqList){
                    primaryKeys.addAll(repayService.queryPrimaryKey(withholdReq.getReqOrderNo()));
                }
            } else {
                primaryKeys.addAll(repayService.queryPrimaryKey(orderNo));
            }
        } catch (Exception e) {
            logger.error("queryUserRepayWithholdDetails exception, msg={}", e.getMessage(), e);
        }
        return buildPayDetailParam(primaryKeys);
//        TblIousPay iousPay = tblIousPayDao.selectPayByOrderNo(orderNo);
//        if(iousPay == null){
//            return null;
//        }
//        QueryCashReqRecord cashReqRecord = tblLoanCashReqDao.queryCashReqRecordByLoanProvideNo(Lists.newArrayList(iousPay.getLoanProvideNo()));
//        QueryRefundRecord refundRecord = iousRefundDao.queryIousRefundByOrderNo(orderNo);
//        return buildPayDetail(iousPay, cashReqRecord, refundRecord);

    }

    private PayDetail buildPayDetail(TblIousPay iousPay, QueryCashReqRecord cashReqRecord, QueryRefundRecord refundRecord) {
        PayDetail payDetail = new PayDetail();
        if(iousPay != null) {
            payDetail.setStartTime(iousPay.getOrderTime());
            payDetail.setEndTime(iousPay.getPayTime());
            payDetail.setStatus(iousPay.getStatus());
            payDetail.setErrorMsg(iousPay.getErrorMsg());
            payDetail.setStatusName(transPayStatus(iousPay.getStatus()));
        }
        if(cashReqRecord != null) {
            payDetail.setBankName(cashReqRecord.getBankName());
            payDetail.setCardNo(cashReqRecord.getCardNo());
        }
        if(refundRecord != null) {
            payDetail.setRefundAmount(refundRecord.getRefundAmount());
            payDetail.setRefundStartTime(refundRecord.getRequestTime());
            payDetail.setRefundEndTime(refundRecord.getRefundTime());
        }
        return payDetail;
    }

    private String transPayStatus(Integer status) {
        if(status == 0) {
            return "初始";
        } else if(status == 1) {
            return "成功";
        } else if(status == 2) {
            return "失败";
        } else if(status == 3) {
            return "处理中";
        } else {
            return "";
        }
    }

    private String addRepayEntryAndChannel(String reqSerialNo) {
//        List<UserRepayReqDetail> userRepayReqDetails = userRepayReqDetailDao.queryUserRepayReqDetail(repaymentOrder.getReqSerialNo());
//        if(CollectionUtils.isNotEmpty(userRepayReqDetails)){
//            repaymentOrder.setChannel(userRepayReqDetails.get(0).getChannel());
//            repaymentOrder.setRepayEntry(userRepayReqDetails.get(0).getRepayEntry());
//        }
        List<UserRepayWithholdSource> repayWithholdSources = repayWithholdSourceDao.selectByReqSerialNo(reqSerialNo);
        List<String> sources = Lists.transform(repayWithholdSources, new Function<UserRepayWithholdSource, String>() {
            @Override
            public String apply(UserRepayWithholdSource userRepayWithholdSource) {
                return userRepayWithholdSource.getTppName();
            }
        });
        return Joiner.on(",").skipNulls().join(sources);
    }

    private void addLoanInfoData(QueryRepayRecord repaymentOrder) {
        if(repaymentOrder != null && repaymentOrder.getLoanProvideNo() != null){
            TblLoanInfo tblLoanInfo = loanInfoDao.queryLoanInfoByLoanProvideNo(repaymentOrder.getLoanProvideNo());
            if(tblLoanInfo != null){
                if("IOUS".equals(repaymentOrder.getProductNo())){
                    repaymentOrder.setBusiOrderNo(tblLoanInfo.getBusiOrderNo());//业务订单号
                    repaymentOrder.setOrderNo(tblLoanInfo.getOrderNo());
                }
                if("CASH".equals(repaymentOrder.getProductNo())){
                    repaymentOrder.setEndDate(tblLoanInfo.getEndDate()); //贷款到期日
                    repaymentOrder.setTppCode(tblLoanInfo.getTppCode());
                }
//                repaymentOrder.setPayAmount(tblLoanInfo.getPayAmount());//订单支付总金额
//                repaymentOrder.setPayTime(tblLoanInfo.getPayTime());//支付完成时间
            }
        }
    }

    private QueryAccountRecord toQueryAccountRecord(TblIousUserInfo iousUserInfo) {
        QueryAccountRecord queryAccountRecord = new QueryAccountRecord();
        queryAccountRecord.setUserId(iousUserInfo.getUserId());
        queryAccountRecord.setMobile(iousUserInfo.getMobile());
        queryAccountRecord.setProductNo(iousUserInfo.getProductNo());
        queryAccountRecord.setTppCode(iousUserInfo.getTppCode());
        queryAccountRecord.setUserName(iousUserInfo.getUserName());
        queryAccountRecord.setIdentity(iousUserInfo.getIdentityCode());
        queryAccountRecord.setIousBeginTime(iousUserInfo.getIousBeginTime());
        queryAccountRecord.setIousEndTime(iousUserInfo.getIousEndTime());
        queryAccountRecord.setSignTime(iousUserInfo.getSignTime());
        queryAccountRecord.setAnnualRate(iousUserInfo.getAnnualRate());
        queryAccountRecord.setRate6(iousUserInfo.getRate6());
        queryAccountRecord.setRate9(iousUserInfo.getRate9());
        queryAccountRecord.setRate12(iousUserInfo.getRate12());
        queryAccountRecord.setUpdateTime(iousUserInfo.getUpdateTime());
        queryAccountRecord.setOrgChannel(iousUserInfo.getChannel());
        queryAccountRecord.setContractStatus(iousUserInfo.getContractStatus());
        Date nowTime = new Date();
        if(SignStatusEnum.BANKREJECT.equals(iousUserInfo.getStatus())){
            queryAccountRecord.setStatus("合同失效");
        }else if ((null != iousUserInfo.getIousEndTime()) && (null != iousUserInfo.getIousBeginTime())) {
            if (nowTime.after(iousUserInfo.getIousEndTime())) {
                queryAccountRecord.setStatus("已过期");
            } else if (nowTime.before(iousUserInfo.getIousEndTime()) && nowTime.after(iousUserInfo.getIousBeginTime())) {
                queryAccountRecord.setStatus("正在使用");
            }
        }
        queryAccountRecord.setCreditAmount(iousUserInfo.getCreditAmount());
        queryAccountRecord.setUseStatus(iousUserInfo.getUseStatus());
        queryAccountRecord.setAutoRepaySignStatus(iousUserInfo.getAutoRepaySignStatus());
        queryAccountRecord.setReqChannel(iousUserInfo.getReqChannel());
        queryAccountRecord.setChannel(iousUserInfo.getChannel());
        queryAccountRecord.setPaySwitch(PaySwitchEnum.toEnum(iousUserInfo.getPaySwitch())); // 支付开关 0:关闭；1:开启
        queryAccountRecord.setDebtAmountAll(iousUserInfo.getDebtAmountAll()); // 欠款总金额
        queryAccountRecord.setCurrentOverdueCount(iousUserInfo.getCurrentOverdueCount()); // 当前逾期订单数量
        queryAccountRecord.setCurrentDebtCount(iousUserInfo.getDebtCount()); // 欠款笔数
        queryAccountRecord.setOverDueAmountAll(iousUserInfo.getCurrentOverdueAmount()); // 当前逾期订单金额
        return queryAccountRecord;
    }

    /**
     * 请求贷后查询退款明细
     * @param serialNo
     */
    private String reqRepayForRefundDetail(String serialNo) {
        StringBuilder refundPathStr = new StringBuilder();
        if (StringUtils.isEmpty(serialNo)) {
            return refundPathStr.toString();
        }
        List<TblOperRecord> tblOperRecordList = tblOperRecordMapper.selectOperRecordByLoanProvideNo(serialNo);
        logger.info("queryUserRefundPath# serialNo:{}, tblOperRecordList:{}", serialNo, tblOperRecordList);
        for (TblOperRecord tblOperRecord : tblOperRecordList) {
            if (tblOperRecord.getAmount().compareTo(BigDecimal.ZERO) == 0 || RecordRefundCategoryEnum.REFUND_TOTAL.name().equals(tblOperRecord.getRefundCategory())) {
                continue;
            }
            if (tblOperRecord.getRefundCategory().equals(RecordRefundCategoryEnum.SERVER.name())) {
                refundPathStr.append("退款至拿去花额度：").append(tblOperRecord.getAmount()).append("元，");
            } else if (tblOperRecord.getRefundCategory() .equals(RecordRefundCategoryEnum.BALANCE.name())) {
                refundPathStr.append("退款至余额：").append(tblOperRecord.getAmount()).append("元，");
            } else if (tblOperRecord.getRefundCategory() .equals(RecordRefundCategoryEnum.ORIGINAL_RETURN_PENDING.name())) {
                refundPathStr.append("原路退款处理中：").append(tblOperRecord.getAmount()).append("元，");
            } else if (tblOperRecord.getRefundCategory() .equals(RecordRefundCategoryEnum.ORIGINAL_RETURN_DETAIL.name())) {
                refundPathStr.append("原路退款至").append(tblOperRecord.getPaymentWayName()).append("(尾号：").append(buildLast4digits(tblOperRecord.getShowCardNo())).append(") ").append(tblOperRecord.getAmount()).append("元，");
            } else {
                logger.error("queryUserRefundPath# 退款路径未知");
                refundPathStr.append("退款路径未知，");
            }
        }
        return refundPathStr.toString();
    }
    /**
     * 获取字符串后四位方法
     */
    private String buildLast4digits(String srcStr) {
        if (srcStr.length() <= LAST4DIGITS) {
            return srcStr;
        } else {
            return srcStr.substring(srcStr.length() - LAST4DIGITS, srcStr.length());
        }
    }

    private PayDetailParamVo buildPayDetailParam(List<PrimaryKey> primaryKeys) {
        if(CollectionUtils.isNotEmpty(primaryKeys)) {
            PrimaryKey primaryKey = primaryKeys.get(0);
            PayDetailParamVo payDetailParamVo = new PayDetailParamVo();
            payDetailParamVo.setBatchOrderId(String.valueOf(primaryKey.getBatchOrderId()));
            payDetailParamVo.setBatchOrderNo(primaryKey.getBatchOrderNo());
            payDetailParamVo.setCreateTime(primaryKey.getCreateTime());
            payDetailParamVo.setOrderNo(primaryKey.getOrderNo());
            return payDetailParamVo;
        }
        return null;
    }
}
