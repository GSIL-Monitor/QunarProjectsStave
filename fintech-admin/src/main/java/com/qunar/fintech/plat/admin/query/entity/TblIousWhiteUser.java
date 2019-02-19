package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.fintech.plat.admin.query.utils.HiddenUtil;
import com.qunar.pay.g2.utils.persistence.Entity;

import java.util.Date;

/**
 * Created by guoyue.sun on 2015/5/22.
 */
public class TblIousWhiteUser implements Entity<Long> {

    private static final long serialVersionUID = 5033932797320185241L;
    /**
     *主键
     */
    private Long id;
    /**
     *用户ID
     */
    private String userId;
    /**
     *姓名
     */
    private String userName;
    /**
     *证件类型
     */
    private String identityType;
    /**
     *证件号
     */
    private String identityCode;
    /**
     *掩码后证件号
     */
    private String mosaicIdentCode;
    /**
     *银行卡号
     */
    private String bankCardNo;
    /**
     *掩码后银行卡号
     */
    private String mosaicBankCardNo;
    /**
     *手机号
     */
    private String mobile;
    /**
     *用户状态 0 初始状态 1.预授信中 2.预授信成功 3.预授信失败
     */
    private Integer preCreditStatus;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *更新时间
     */
    private Date updateTime;
    /**
     * 卡类型 0 借记卡 1 信用卡 99 未知卡类型
     * @return
     */
    private Integer cardType;

    /**
     * 带宽提供商
     */
    private String tppCode;

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

    public String getMosaicBankCardNo() {
        return mosaicBankCardNo;
    }

    public void setMosaicBankCardNo(String mosaicBankCardNo) {
        this.mosaicBankCardNo = mosaicBankCardNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getPreCreditStatus() {
        return preCreditStatus;
    }

    public void setPreCreditStatus(Integer preCreditStatus) {
        this.preCreditStatus = preCreditStatus;
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

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    @Override
    public String toString() {
        return "TblIousWhiteUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", identityType='" + identityType + '\'' +
                ", identityCode='" + HiddenUtil.hiddenIdentityCardNo(identityCode) + '\'' +
                ", mosaicIdentCode='" + mosaicIdentCode + '\'' +
                ", bankCardNo='" + bankCardNo + '\'' +
                ", mosaicBankCardNo='" + mosaicBankCardNo + '\'' +
                ", mobile='" + mobile + '\'' +
                ", preCreditStatus=" + preCreditStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", cardType=" + cardType +
                ", tppCode='" + tppCode + '\'' +
                '}';
    }
}
