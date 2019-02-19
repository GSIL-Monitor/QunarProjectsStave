package com.qunar.fintech.plat.admin.query.vo;


import com.qunar.fintech.plat.admin.query.utils.HiddenUtil;

import java.util.Date;

/**
 * Created by guoyue.sun on 2015/5/22.
 */
public class QueryIousWhiteUserRecord {

    /* 用户ID */
    private String userId;

    /* 手机号 */
    private String mobile;

    /* 姓名 */
    private String userName;

    /* 证件号 */
    private String identityCode;

    /* 主使用卡号 */
    private String bankCardNo;

    private String identityType;

    private Integer cardType;

    /* 生成时间 */
    private Date createTime;

    /* 修改时间 */
    private Date updateTime;

    /* 贷款提供方 */
    private String tppCode;

    /* 白名单状态 */
    private Integer preCreditStatus;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
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

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public Integer getPreCreditStatus() {
        return preCreditStatus;
    }

    public void setPreCreditStatus(Integer preCreditStatus) {
        this.preCreditStatus = preCreditStatus;
    }

    @Override
    public String toString() {
        return "QueryIousWhiteUserRecord{" +
                "bankCardNo='" + HiddenUtil.hiddenBankCardNo(bankCardNo) + '\'' +
                ", userId=" + userId +
                ", mobile='" +HiddenUtil.hiddenMobile(mobile)  + '\'' +
                ", userName='" + userName + '\'' +
                ", identityCode='" +HiddenUtil.hiddenIdentityCardNo(identityCode)  + '\'' +
                ", identityType='" + identityType + '\'' +
                ", cardType='" + cardType + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", tppCode='" + tppCode + '\'' +
                ", preCreditStatus='" + preCreditStatus + '\'' +
                '}';
    }
}
