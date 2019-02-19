package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.g2.utils.persistence.Entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by guoyue.sun on 2015/5/27.
 */
public class TblIousAuth implements Entity<Long> {
    private static final long serialVersionUID = -3479426817607207315L;
    /**
     *主键
     */
    private Long id;
    /**
     *用户id
     */
    private String userId;
    /**
     *请求id
     */
    private String reqUserId;
    /**
     *流水号
     */
    private String serialNo;
    /**
     *支付订单号
     */
    private String orderNo;
    /**
     *订单时间
     */
    private Date orderTime;
    /**
     *请求时间
     */
    private Date requestTime;
    /**
     *业务线订单号
     */
    private String busiOrderNo;
    /**
     *业务线ID
     */
    private String busiTypeId;
    /**
     *业务线名称
     */
    private String busiTypeName;
    /**
     *代理商ID
     */
    private String merchantCode;
    /**
     *订单申请总金额
     */
    private BigDecimal applyAmount;
    /**
     *信任付申请金额
     */
    private BigDecimal iousApplyAmount;
    /**
     *信任付已确认金额
     */
    private BigDecimal iousConfirmAmount;
    /**
     *信任付已撤销金额
     */
    private BigDecimal iousCancelAmount;
    /**
     *贷款周期
     */
    private Integer loanTerm;
    /**
     *产品编号
     */
    private String productNo;
    /**
     *签约银行
     */
    private String tppCode;
    /**
     *是否支持自动撤销 0:初始,1:是,2:否
     */
    private Integer autoCancel;
    /**
     *支付中心统一流水号
     */
    private String qunarTradeNo;
    /**
     *备注
     */
    private String remark;
    /**
     *状态 0:初始,1:成功,2:失败
     */
    private Integer status;
    /**
     *错误码
     */
    private String errorCode;
    /**
     *错误信息
     */
    private String errorMsg;
    /**
     *账务请求流水
     */
    private String accountingSequence;
    /**
     *0:初始状态,1:待入账,2:入账成功,3:入账失败
     */
    private Integer accountingStatus;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *更新时间
     */
    private Date updateTime;
    /**
     * 预授权已撤销的SerailNo
     */
    private String cancelSerialNo;
    /**
     * 预授权已确认的SerailNo
     */
    private String confirmSerialNo;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String mobile;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
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

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public BigDecimal getIousApplyAmount() {
        return iousApplyAmount;
    }

    public void setIousApplyAmount(BigDecimal iousApplyAmount) {
        this.iousApplyAmount = iousApplyAmount;
    }

    public BigDecimal getIousConfirmAmount() {
        return iousConfirmAmount;
    }

    public void setIousConfirmAmount(BigDecimal iousConfirmAmount) {
        this.iousConfirmAmount = iousConfirmAmount;
    }

    public BigDecimal getIousCancelAmount() {
        return iousCancelAmount;
    }

    public void setIousCancelAmount(BigDecimal iousCancelAmount) {
        this.iousCancelAmount = iousCancelAmount;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public Integer getAutoCancel() {
        return autoCancel;
    }

    public void setAutoCancel(Integer autoCancel) {
        this.autoCancel = autoCancel;
    }

    public String getQunarTradeNo() {
        return qunarTradeNo;
    }

    public void setQunarTradeNo(String qunarTradeNo) {
        this.qunarTradeNo = qunarTradeNo;
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

    public Integer getAccountingStatus() {
        return accountingStatus;
    }

    public void setAccountingStatus(Integer accountingStatus) {
        this.accountingStatus = accountingStatus;
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

    public String getCancelSerialNo() {
        return cancelSerialNo;
    }

    public void setCancelSerialNo(String cancelSerialNo) {
        this.cancelSerialNo = cancelSerialNo;
    }

    public String getConfirmSerialNo() {
        return confirmSerialNo;
    }

    public void setConfirmSerialNo(String confirmSerialNo) {
        this.confirmSerialNo = confirmSerialNo;
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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getReqUserId() {
        return reqUserId;
    }

    public void setReqUserId(String reqUserId) {
        this.reqUserId = reqUserId;
    }
}
