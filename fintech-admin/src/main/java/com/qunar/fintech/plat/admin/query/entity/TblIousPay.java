package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

public class TblIousPay extends ToString {

    private static final long serialVersionUID = 5630535753515921867L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 请求用户id
     */
    private String reqUserId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 支付流水,同时作为贷款流水号
     */
    private String payId;
    /**
     * 支付订单号
     */
    private String orderNo;
    /**
     * 订单时间
     */
    private Date orderTime;
    /**
     * 请求时间
     */
    private Date requestTime;
    /**
     * 业务线订单号
     */
    private String busiOrderNo;
    /**
     * 业务线ID
     */
    private String busiTypeId;
    /**
     * 业务线名称
     */
    private String busiTypeName;
    /**
     * 代理商ID
     */
    private String merchantCode;
    /**
     * 订单支付总金额
     */
    private BigDecimal payAmount;
    /**
     * 信任付支付金额
     */
    private BigDecimal iousPayAmount;
    /**
     * 信任付已退金额
     */
    private BigDecimal iousRefundAmount;
    /**
     * 贷款周期
     */
    private Integer loanTerm;
    /**
     * 当前欠款金额（这笔订单的当前欠款金额）
     */
    private BigDecimal debtAmountAll;
    /**
     * 发送给网关,网关发送给服务商的贷款流水
     */
    private String loanProvideNo;
    /**
     * 产品号
     */
    private String productNo;
    /**
     * 签约银行
     */
    private String tppCode;
    /**
     * 服务商返回流水
     */
    private String trxId;
    /**
     * 支付中心统一流水号
     */
    private String qunarTradeNo;
    /**
     * 支付完成时间
     */
    private Date payTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态 0:初始,1:成功,2:失败,3:处理中
     */
    private Integer status;
    /**
     * 来源 1:普通支付 2:预授权确认
     */
    private Byte source;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 账务请求流水
     */
    private String accountingSequence;
    /**
     * 0:初始状态,1:待入账,2:入账成功,3:入账失败
     */
    private Byte accountingStatus;
    /**
     * 请求账务失败次数
     */
    private Byte reqNum;
    /**
     * 网关支付流水
     */
    private String gwPayId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public String getReqUserId() {
        return reqUserId;
    }

    public void setReqUserId(String reqUserId) {
        this.reqUserId = reqUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getBusiOrderNo() {
        return busiOrderNo;
    }

    public void setBusiOrderNo(String busiOrderNo) {
        this.busiOrderNo = busiOrderNo;
    }

    public String getBusiTypeId() {
        return busiTypeId;
    }

    public void setBusiTypeId(String busiTypeId) {
        this.busiTypeId = busiTypeId;
    }

    public String getBusiTypeName() {
        return busiTypeName;
    }

    public void setBusiTypeName(String busiTypeName) {
        this.busiTypeName = busiTypeName;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getIousPayAmount() {
        return iousPayAmount;
    }

    public void setIousPayAmount(BigDecimal iousPayAmount) {
        this.iousPayAmount = iousPayAmount;
    }

    public BigDecimal getIousRefundAmount() {
        return iousRefundAmount;
    }

    public void setIousRefundAmount(BigDecimal iousRefundAmount) {
        this.iousRefundAmount = iousRefundAmount;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public BigDecimal getDebtAmountAll() {
        return debtAmountAll;
    }

    public void setDebtAmountAll(BigDecimal debtAmountAll) {
        this.debtAmountAll = debtAmountAll;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getQunarTradeNo() {
        return qunarTradeNo;
    }

    public void setQunarTradeNo(String qunarTradeNo) {
        this.qunarTradeNo = qunarTradeNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
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

    public String getAccountingSequence() {
        return accountingSequence;
    }

    public void setAccountingSequence(String accountingSequence) {
        this.accountingSequence = accountingSequence;
    }

    public Byte getAccountingStatus() {
        return accountingStatus;
    }

    public void setAccountingStatus(Byte accountingStatus) {
        this.accountingStatus = accountingStatus;
    }

    public Byte getReqNum() {
        return reqNum;
    }

    public void setReqNum(Byte reqNum) {
        this.reqNum = reqNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getGwPayId() {
        return gwPayId;
    }

    public void setGwPayId(String gwPayId) {
        this.gwPayId = gwPayId;
    }

}