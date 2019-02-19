package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.g2.utils.persistence.Entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dw.wang
 * @since 2016-03-08.
 */
public class TblIousVirtualContract implements Entity<Long> {
    private static final long serialVersionUID = -1936458844424736777L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户Id
     */
    private String productNo;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 证件类型
     */
    private String identityType;

    /**
     * 证件号
     */
    private String identityCode;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 使用状态
     */
    private String contractStatus;

    /**
     * 合同开始时间
     */
    private Date iousBeginTime;

    /**
     * 合同结束时间
     */
    private Date iousEndTime;

    /**
     * 合同号
     */
    private String contractNo;

    /**
     * 签约金额
     */
    private BigDecimal contractAmount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 账务状态
     */
    private String accountingStatus;

    /**
     * 利率
     */
    private BigDecimal rate;

    /**
     * 利率类型
     */
    private String rateType;
    /**
     * "SINGED"已签约，"UNSIGNED"未签约
     */
    private String autoRepaySignStatus;
    /**
     * yes已经弹出过,never没有弹出过
     */
    private String isAutoPepayPop;

    /**
     * 支付开关
     */
    private String paySwitch;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Date getIousBeginTime() {
        return iousBeginTime;
    }

    public void setIousBeginTime(Date iousBeginTime) {
        this.iousBeginTime = iousBeginTime;
    }

    public Date getIousEndTime() {
        return iousEndTime;
    }

    public void setIousEndTime(Date iousEndTime) {
        this.iousEndTime = iousEndTime;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
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

    public String getAccountingStatus() {
        return accountingStatus;
    }

    public void setAccountingStatus(String accountingStatus) {
        this.accountingStatus = accountingStatus;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getAutoRepaySignStatus() {
        return autoRepaySignStatus;
    }

    public void setAutoRepaySignStatus(String autoRepaySignStatus) {
        this.autoRepaySignStatus = autoRepaySignStatus;
    }

    public String getIsAutoPepayPop() {
        return isAutoPepayPop;
    }

    public void setIsAutoPepayPop(String isAutoPepayPop) {
        this.isAutoPepayPop = isAutoPepayPop;
    }

    public String getPaySwitch() {
        return paySwitch;
    }

    public void setPaySwitch(String paySwitch) {
        this.paySwitch = paySwitch;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TblIousVirtualContract{");
        sb.append("id=").append(id);
        sb.append(", productNo=").append(productNo);
        sb.append(", userId=").append(userId);
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", identityType='").append(identityType).append('\'');
        sb.append(", identityCode='").append(identityCode).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", contractStatus='").append(contractStatus).append('\'');
        sb.append(", iousBeginTime=").append(iousBeginTime);
        sb.append(", iousEndTime=").append(iousEndTime);
        sb.append(", contractNo='").append(contractNo).append('\'');
        sb.append(", contractAmount=").append(contractAmount);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", accountingStatus='").append(accountingStatus).append('\'');
        sb.append(", rate=").append(rate);
        sb.append(", rateType='").append(rateType).append('\'');
        sb.append(", autoRepaySignStatus='").append(autoRepaySignStatus).append('\'');
        sb.append(", isAutoPepayPop='").append(isAutoPepayPop).append('\'');
        sb.append(", paySwitch='").append(paySwitch).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

