package com.qunar.fintech.plat.admin.query.entity;


import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dw.wang
 * @since 2016-03-29
 */
public class UserRepayWithholdSource extends ToString{
    private static final long serialVersionUID = -5478018415275135258L;

    private Long id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 批次订单号
     */
    private String reqSerialNo;
    /**
     * 扣款发起记录归属表
     */
    private String sourceTable;
    /**
     * 业务系统编号
     */
    private String busiTypeId;
    /**
     * 接口商户ID
     */
    private String merchantCode;
    /**
     * 交易订单号
     */
    private String orderNo;
    /**
     * 交易日期
     */
    private Date orderDate;
    /**
     * 订单金额 以元为单位
     */
    private BigDecimal orderAmount;
    /**
     * 已退款金额
     */
    private BigDecimal refAmount;
    /**
     * 支付金额
     */
    private BigDecimal payAmount;
    /**
     * 支付方式
     */
    private String pmCode;
    /**
     * 支付通道
     */
    private String tppCode;
    /**
     * 支付通道名称
     */
    private String tppName;
    /**
     * 银行代码
     */
    private String bankCode;
    /**
     * 银行中文名称
     */
    private String bankName;
    /**
     * 持卡人姓名
     */
    private String cardholder;
    /**
     * 证件类型  l IDENTITYCARD：身份证 l PASSPORT：护照 l OFFICERCERT：军官证
     */
    private String identityType;
    /**
     * 证件号
     */
    private String identityCode;
    /**
     * 卡号
     */
    private String cardId;
    /**
     * 卡号
     */
    private String cardDesc;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 支付商户
     */
    private String paymentMerchant;
    /**
     * 支付成功时间
     */
    private Date payTime;
    /**
     * 生成时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReqSerialNo() {
        return reqSerialNo;
    }

    public void setReqSerialNo(String reqSerialNo) {
        this.reqSerialNo = reqSerialNo;
    }

    public String getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable;
    }

    public String getBusiTypeId() {
        return busiTypeId;
    }

    public void setBusiTypeId(String busiTypeId) {
        this.busiTypeId = busiTypeId;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getRefAmount() {
        return refAmount;
    }

    public void setRefAmount(BigDecimal refAmount) {
        this.refAmount = refAmount;
    }

    public String getPmCode() {
        return pmCode;
    }

    public void setPmCode(String pmCode) {
        this.pmCode = pmCode;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getTppName() {
        return tppName;
    }

    public void setTppName(String tppName) {
        this.tppName = tppName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }


    public String getCardDesc() {
        return cardDesc;
    }

    public void setCardDesc(String cardDesc) {
        this.cardDesc = cardDesc;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPaymentMerchant() {
        return paymentMerchant;
    }

    public void setPaymentMerchant(String paymentMerchant) {
        this.paymentMerchant = paymentMerchant;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}
