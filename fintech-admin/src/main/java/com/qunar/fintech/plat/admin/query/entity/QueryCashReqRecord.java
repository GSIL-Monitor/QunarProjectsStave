package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 现金贷借钱申请记录
 * 需连表查询字段:
 * 用户姓名, 用户手机号, 当前欠款金额
 * <p>
 * Created by shuaifeng.gao on 16-9-20.
 */
public class QueryCashReqRecord extends ToString {

    /* 主键 */
    private Long id;

    /* 渠道编码 */
    private String orgChannel;

    /* 借款流水 */
    private String serialNo;

    /* 借据号 */
    private String loanProvideNo;

    /* 借款金额 */
    private BigDecimal amount;

    /* 借款周期 */
    private Integer loanTerm;

    /**
     * 还款方式
     *
     */
    private String repayType;

    /* 通道编码 */
    private String tppCode;

    /* 用户Id */
    private String userId;

    /**
     * 利率
     */
    private BigDecimal loanRate;

    /* 用户姓名 */
    private String userName;

    /* 用户手机号 */
    private String mobile;

    /* 用户身份证号 */
    private String identity;

    /* 申请日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date requestTime;

    /* 完成日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    /**
     * 借款状态
     *
     */
    private Integer status;

    /**
     * 汇款状态
     *
     */
    private Integer remitStatus;

    /* 银行名称 */
    private String bankName;

    /* 银行卡号(卡索引) */
    private String cardNo;

    /**
     * 当前欠款金额 = 剩余本金+利息+罚息
     * [ə'riərɪdʒ], 欠款
     */
    private BigDecimal arrearage;

    /* 错误码 */
    private String errorCode;

    /* 错误信息 */
    private String errorMsg;

    /*发往服务商请款流水*/
    private String servLoanProvideId;

    /*第三方清算时间*/
    private Date settleTime;

    /*打款完成时间*/
    private Date remitFinishTime;

    /*支付中心统一流水号*/
    private String qunarTradeNo;

    /*产品号*/
    private String productNo;

    /*贷款发放日期*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startInterestDate;

    /* 建议话术 */
    private String suggest;

    public String getQunarTradeNo() {
        return qunarTradeNo;
    }

    public void setQunarTradeNo(String qunarTradeNo) {
        this.qunarTradeNo = qunarTradeNo;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(BigDecimal loanRate) {
        this.loanRate = loanRate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRemitStatus() {
        return remitStatus;
    }

    public void setRemitStatus(Integer remitStatus) {
        this.remitStatus = remitStatus;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public BigDecimal getArrearage() {
        return arrearage;
    }

    public void setArrearage(BigDecimal arrearage) {
        this.arrearage = arrearage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getServLoanProvideId() {
        return servLoanProvideId;
    }

    public void setServLoanProvideId(String servLoanProvideId) {
        this.servLoanProvideId = servLoanProvideId;
    }

    public Date getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

    public Date getRemitFinishTime() {
        return remitFinishTime;
    }

    public void setRemitFinishTime(Date remitFinishTime) {
        this.remitFinishTime = remitFinishTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Date getStartInterestDate() {
        return startInterestDate;
    }

    public void setStartInterestDate(Date startInterestDate) {
        this.startInterestDate = startInterestDate;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }
}
