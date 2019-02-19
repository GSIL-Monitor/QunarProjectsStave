package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.g2.utils.persistence.Entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dw.wang
 * @since 2016-03-09.
 */
public class UserRepayWithhold implements Entity<Long> {
    private static final long serialVersionUID = 854957529419631689L;

    private Long id;
    /**
     * 用户userd
     */
    private String userId;
    /**
     * 请求流水号
     */
    private String reqSerialNo;
    /**
     * 还款扣款场景
     */
    private String payScene;
    /**
     * 扣款资产类型枚举
     */
    private String assetMode;
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
     * 交易类型
     */
    private String transTypeId;
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
     * 已退款金额 以元为单位
     */
    private BigDecimal refAmount;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品编号
     */
    private String productId;
    /**
     * 分账扩展信息
     */
    private String shareData;
    /**
     * 支付方式
     */
    private String pmCode;
    /**
     * 支付通道
     */
    private String tppCode;
    /**
     * 银行代码
     */
    private String bankCode;
    /**
     * 货币代码
     */
    private String curId;
    /**
     * 页面回调地址
     */
    private String pageRetUrl;
    /**
     * 后端回调地址
     */
    private String bgRetUrl;
    /**
     * 持卡人姓名
     */
    private String cardholder;
    /**
     * 证件类型
     */
    private String identitytype;
    /**
     * 证件号
     */
    private String identitycode;
    /**
     * 卡号
     */
    private String cardId;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 是否绑卡
     */
    private String bindCard;
    /**
     * 商户号
     */
    private String paymentMerchant;
    /**
     * 交易有效时间
     */
    private Date validTime;
    /**
     * 业务订单号
     */
    private String busiOrderNo;
    /**
     * 账务统一流水号
     */
    private String qunarTradeNo;
    /**
     *
     */
    private String combineInfo;
    /**
     * 请求交易版本信息
     */
    private String ext;
    /**
     * 扣款状态
     */
    private String payStatus;
    /**
     * 请求交易id
     */
    private String payId;
    /**
     * 请求交易时间
     */
    private Date payTime;
    /**
     * 错误码
     */
    private String errCode;
    /**
     * 错误信息
     */
    private String errMsg;
    /**
     * 请求次数
     */
    private Integer reqNum;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 姓名
     */
    private String userName;

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

    public String getReqSerialNo() {
        return reqSerialNo;
    }

    public void setReqSerialNo(String reqSerialNo) {
        this.reqSerialNo = reqSerialNo;
    }

    public String getPayScene() {
        return payScene;
    }

    public void setPayScene(String payScene) {
        this.payScene = payScene;
    }

    public String getAssetMode() {
        return assetMode;
    }

    public void setAssetMode(String assetMode) {
        this.assetMode = assetMode;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getShareData() {
        return shareData;
    }

    public void setShareData(String shareData) {
        this.shareData = shareData;
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

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCurId() {
        return curId;
    }

    public void setCurId(String curId) {
        this.curId = curId;
    }

    public String getPageRetUrl() {
        return pageRetUrl;
    }

    public void setPageRetUrl(String pageRetUrl) {
        this.pageRetUrl = pageRetUrl;
    }

    public String getBgRetUrl() {
        return bgRetUrl;
    }

    public void setBgRetUrl(String bgRetUrl) {
        this.bgRetUrl = bgRetUrl;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getIdentitytype() {
        return identitytype;
    }

    public void setIdentitytype(String identitytype) {
        this.identitytype = identitytype;
    }

    public String getIdentitycode() {
        return identitycode;
    }

    public void setIdentitycode(String identitycode) {
        this.identitycode = identitycode;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBindCard() {
        return bindCard;
    }

    public void setBindCard(String bindCard) {
        this.bindCard = bindCard;
    }

    public String getPaymentMerchant() {
        return paymentMerchant;
    }

    public void setPaymentMerchant(String paymentMerchant) {
        this.paymentMerchant = paymentMerchant;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public String getBusiOrderNo() {
        return busiOrderNo;
    }

    public void setBusiOrderNo(String busiOrderNo) {
        this.busiOrderNo = busiOrderNo;
    }

    public String getQunarTradeNo() {
        return qunarTradeNo;
    }

    public void setQunarTradeNo(String qunarTradeNo) {
        this.qunarTradeNo = qunarTradeNo;
    }

    public String getCombineInfo() {
        return combineInfo;
    }

    public void setCombineInfo(String combineInfo) {
        this.combineInfo = combineInfo;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getReqNum() {
        return reqNum;
    }

    public void setReqNum(Integer reqNum) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserRepayWithhold{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", reqSerialNo='").append(reqSerialNo).append('\'');
        sb.append(", payScene='").append(payScene).append('\'');
        sb.append(", assetMode='").append(assetMode).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", busiTypeId='").append(busiTypeId).append('\'');
        sb.append(", merchantCode='").append(merchantCode).append('\'');
        sb.append(", transTypeId='").append(transTypeId).append('\'');
        sb.append(", orderNo='").append(orderNo).append('\'');
        sb.append(", orderDate=").append(orderDate);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", refAmount=").append(refAmount);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", productId='").append(productId).append('\'');
        sb.append(", shareData='").append(shareData).append('\'');
        sb.append(", pmCode='").append(pmCode).append('\'');
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", bankCode='").append(bankCode).append('\'');
        sb.append(", curId='").append(curId).append('\'');
        sb.append(", pageRetUrl='").append(pageRetUrl).append('\'');
        sb.append(", bgRetUrl='").append(bgRetUrl).append('\'');
        sb.append(", cardholder='").append(cardholder).append('\'');
        sb.append(", identitytype='").append(identitytype).append('\'');
        sb.append(", identitycode='").append(identitycode).append('\'');
        sb.append(", cardId='").append(cardId).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", bindCard='").append(bindCard).append('\'');
        sb.append(", paymentMerchant='").append(paymentMerchant).append('\'');
        sb.append(", validTime=").append(validTime);
        sb.append(", busiOrderNo='").append(busiOrderNo).append('\'');
        sb.append(", qunarTradeNo='").append(qunarTradeNo).append('\'');
        sb.append(", combineInfo='").append(combineInfo).append('\'');
        sb.append(", ext='").append(ext).append('\'');
        sb.append(", payStatus='").append(payStatus).append('\'');
        sb.append(", payId='").append(payId).append('\'');
        sb.append(", payTime=").append(payTime);
        sb.append(", errCode='").append(errCode).append('\'');
        sb.append(", errMsg='").append(errMsg).append('\'');
        sb.append(", reqNum=").append(reqNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", userName='").append(userName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

