package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.finance.qf.commons.api.enums.ProductEnum;
import com.qunar.pay.finance.repaykernel.api.userrepay.enums.PaySceneEnum;
import com.qunar.pay.g2.utils.persistence.Entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserRepayWithholdReq implements Entity<Long> {
    private Long id;
    /**
     * 用户userd
     */
    private String userId;
    /**
     * 产品名称
     */
    private ProductEnum productNo;
    /**
     * 机构通道
     */
    private String orgChannel;
    /**
     * 请求流水号
     */
    private String reqSerialNo;
    /**
     * 交易订单号
     */
    private String orderNo;
    /**
     * 交易请求订单号
     */
    private String reqOrderNo;

    /**
     * 交易日期
     */
    private Date orderDate;
    /**
     * 订单金额 以元为单位
     */
    private BigDecimal orderAmount;

    /**
     * 还款扣款场景
     */
    private PaySceneEnum payScene;
    /**
     * 版本号
     */
    private String version;
    /**
     * 业务系统编号
     */
    private String busiTypeId;
    /**
     * 接口商户号
     */
    private String merchantCode;
    /**
     * 收款模式  代付
     */
    private String paymentMerchant;
    /**
     * 交易类型
     */
    private String transTypeId;


    /**
     * 分账扩展信息
     */
    private String shareData;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 业务流水号
     */
    private String qunarTradeNo;
    /**
     * 券id
     */
    private String couponNo;

    public String getPaymentMerchant() {
        return paymentMerchant;
    }

    public void setPaymentMerchant(String paymentMerchant) {
        this.paymentMerchant = paymentMerchant;
    }

    public String getQunarTradeNo() {
        return qunarTradeNo;
    }

    public void setQunarTradeNo(String qunarTradeNo) {
        this.qunarTradeNo = qunarTradeNo;
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

    public ProductEnum getProductNo() {
        return productNo;
    }

    public void setProductNo(ProductEnum productNo) {
        this.productNo = productNo;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public String getReqSerialNo() {
        return reqSerialNo;
    }

    public void setReqSerialNo(String reqSerialNo) {
        this.reqSerialNo = reqSerialNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getReqOrderNo() {
        return reqOrderNo;
    }

    public void setReqOrderNo(String reqOrderNo) {
        this.reqOrderNo = reqOrderNo;
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

    public PaySceneEnum getPayScene() {
        return payScene;
    }

    public void setPayScene(PaySceneEnum payScene) {
        this.payScene = payScene;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public String getTransTypeId() {
        return transTypeId;
    }

    public void setTransTypeId(String transTypeId) {
        this.transTypeId = transTypeId;
    }

    public String getShareData() {
        return shareData;
    }

    public void setShareData(String shareData) {
        this.shareData = shareData;
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

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserRepayWithholdReq{");
        sb.append("id=").append(id);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", productNo=").append(productNo);
        sb.append(", orgChannel='").append(orgChannel).append('\'');
        sb.append(", reqSerialNo='").append(reqSerialNo).append('\'');
        sb.append(", orderNo='").append(orderNo).append('\'');
        sb.append(", reqOrderNo='").append(reqOrderNo).append('\'');
        sb.append(", orderDate=").append(orderDate);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", payScene=").append(payScene);
        sb.append(", version='").append(version).append('\'');
        sb.append(", busiTypeId='").append(busiTypeId).append('\'');
        sb.append(", merchantCode='").append(merchantCode).append('\'');
        sb.append(", paymentMerchant='").append(paymentMerchant).append('\'');
        sb.append(", transTypeId='").append(transTypeId).append('\'');
        sb.append(", shareData='").append(shareData).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", qunarTradeNo='").append(qunarTradeNo).append('\'');
        sb.append(", couponNo='").append(couponNo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}