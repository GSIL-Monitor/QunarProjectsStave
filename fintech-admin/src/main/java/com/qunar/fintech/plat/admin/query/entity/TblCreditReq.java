package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

public class TblCreditReq extends ToString {
    /**
     * 主键
     */
    private Long id;

    /**
     * 授信流水号
     */
    private String creditNo;

    /**
     * 征信查询流水号
     */
    private String qcreditNo;

    /**
     * 申请网关流水号
     */
    private String applyNo;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 贷款提供商编码
     */
    private String productNo;

    /**
     * 贷款提供商编码
     */
    private String tppCode;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 证件类型
     */
    private String identityType;

    /**
     * 证件号
     */
    private String identityCode;

    /**
     * 掩码证件号
     */
    private String mosaicIdentCode;

    /**
     * 银行卡号
     */
    private String bankCardNo;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 授信请求状态
     */
    private Integer reqStatus;

    /**
     * 利率
     */
    private BigDecimal rate;

    /**
     * 利率类型(0 月利率 1.年利率)
     */
    private Integer rateType;

    /**
     * 授信额度
     */
    private BigDecimal creditAmt;

    /**
     * 发往服务商授信流水
     */
    private String servCreditNo;

    /**
     * 卡类型
     */
    private Integer cardType;

    /**
     *  有效期开始时间
     */
    private Date startEffTime;

    /**
     * 有效期结束时间
     */
    private Date endEffTime;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误描述
     */
    private String errorMsg;

    /**
     * 授信来源
     */
    private String creditSrc;

    /**
     * 授信类型
     */
    private String creditType;

    /**
     * 实际申请流水号
     */
    private String actApplyNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditNo() {
        return creditNo;
    }

    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo;
    }

    public String getQcreditNo() {
        return qcreditNo;
    }

    public void setQcreditNo(String qcreditNo) {
        this.qcreditNo = qcreditNo;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getMosaicIdentCode() {
        return mosaicIdentCode;
    }

    public void setMosaicIdentCode(String mosaicIdentCode) {
        this.mosaicIdentCode = mosaicIdentCode;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getRateType() {
        return rateType;
    }

    public void setRateType(Integer rateType) {
        this.rateType = rateType;
    }

    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = creditAmt;
    }

    public String getServCreditNo() {
        return servCreditNo;
    }

    public void setServCreditNo(String servCreditNo) {
        this.servCreditNo = servCreditNo;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Date getStartEffTime() {
        return startEffTime;
    }

    public void setStartEffTime(Date startEffTime) {
        this.startEffTime = startEffTime;
    }

    public Date getEndEffTime() {
        return endEffTime;
    }

    public void setEndEffTime(Date endEffTime) {
        this.endEffTime = endEffTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
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

    public String getCreditSrc() {
        return creditSrc;
    }

    public void setCreditSrc(String creditSrc) {
        this.creditSrc = creditSrc;
    }

    public String getActApplyNo() {
        return actApplyNo;
    }

    public void setActApplyNo(String actApplyNo) {
        this.actApplyNo = actApplyNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }
}