package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;


/**
 * Created by xi.cheng on 2016/9/19.
 */
public class CashRemit extends ToString {

    private static final long serialVersionUID = 2203875076592349189L;
    /**
     * 主键id
     */
    private Long id;

    /**
     * 渠道编码
     */
    private String orgChannel;

    /**
     * 借款流水
     */
    private String loanProvideNo;
    /**
     * 借款流水
     */
    private String serialNo;

    /**
     * 借款金额
     */
    private BigDecimal requestAmount;

    /**
     * 借款期数
     */
    private Integer loanTerm;

    /**
     * uid
     */
    private String userId;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 打款订单号
     */
    private String remitRequestNo;

    /**
     * 打款状态
     */
    private Integer remitOrderStatus;

    /**
     * 打款申请金额
     */
    private BigDecimal remitAmount;

    /**
     * 实际打款成功金额
     */
    private BigDecimal actualAmount;

    /**
     * 出款商户
     */
    private String targetMerchantCode;

    /**
     * 收款方账户类型
     * 0：对私，1：对公
     */
    private Integer accountType;

    /**
     * 收款银行名称
     */
    private String bankName;

    /**
     * 收款银行卡号（卡索引）
     */
    private String cardNo;

    /**
     * 打款申请时间
     */
    private String requestTime;

    /**
     * 打款完成时间
     */
    private String finishTime;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 通道方
     */
    private String tppCode;


    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(BigDecimal requestAmount) {
        this.requestAmount = requestAmount;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getRemitRequestNo() {
        return remitRequestNo;
    }

    public void setRemitRequestNo(String remitRequestNo) {
        this.remitRequestNo = remitRequestNo;
    }

    public Integer getRemitOrderStatus() {
        return remitOrderStatus;
    }

    public void setRemitOrderStatus(Integer remitOrderStatus) {
        this.remitOrderStatus = remitOrderStatus;
    }

    public BigDecimal getRemitAmount() {
        return remitAmount;
    }

    public void setRemitAmount(BigDecimal remitAmount) {
        this.remitAmount = remitAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getTargetMerchantCode() {
        return targetMerchantCode;
    }

    public void setTargetMerchantCode(String targetMerchantCode) {
        this.targetMerchantCode = targetMerchantCode;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
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

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
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

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

}
