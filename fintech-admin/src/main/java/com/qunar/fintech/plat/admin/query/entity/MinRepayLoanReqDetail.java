package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.g2.utils.persistence.Entity;

import java.math.BigDecimal;
import java.util.Date;

public class MinRepayLoanReqDetail implements Entity<Long>  {
    private Long id;

    private String serialNo;

    private String userId;

    private String billNo;

    private String loanProvideNo;

    private Date dueDate;

    private String oriTppcode;

    private String oriFundOrgCode;

    private String newLoanNo;

    private String newTppcode;

    private String newFundOrgCode;
    /**
     * 转债金额
     */
    private BigDecimal transAmount;

    /**
     * MinRepayProcessType
     */
    private String processType;

    /* 贷款利率 XXX 日利率 月利率? */
    private BigDecimal loanRate;
    /**
     * 新单贷款金额
     * 后续可能与transAmount不同，比如一批次N单通道返回对应M单（M<N）
     */
    private BigDecimal newLoanAmount;

    /* 起息日 */
    private Date startInterestDate;

    /* 止息日 */
    private Date endInterestDate;

    private String trxId;

    private String ext;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getOriTppcode() {
        return oriTppcode;
    }

    public void setOriTppcode(String oriTppcode) {
        this.oriTppcode = oriTppcode;
    }

    public String getOriFundOrgCode() {
        return oriFundOrgCode;
    }

    public void setOriFundOrgCode(String oriFundOrgCode) {
        this.oriFundOrgCode = oriFundOrgCode;
    }

    public String getNewLoanNo() {
        return newLoanNo;
    }

    public void setNewLoanNo(String newLoanNo) {
        this.newLoanNo = newLoanNo;
    }

    public String getNewTppcode() {
        return newTppcode;
    }

    public void setNewTppcode(String newTppcode) {
        this.newTppcode = newTppcode;
    }

    public String getNewFundOrgCode() {
        return newFundOrgCode;
    }

    public void setNewFundOrgCode(String newFundOrgCode) {
        this.newFundOrgCode = newFundOrgCode;
    }

    public BigDecimal getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public BigDecimal getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(BigDecimal loanRate) {
        this.loanRate = loanRate;
    }

    public BigDecimal getNewLoanAmount() {
        return newLoanAmount;
    }

    public void setNewLoanAmount(BigDecimal newLoanAmount) {
        this.newLoanAmount = newLoanAmount;
    }

    public Date getStartInterestDate() {
        return startInterestDate;
    }

    public void setStartInterestDate(Date startInterestDate) {
        this.startInterestDate = startInterestDate;
    }

    public Date getEndInterestDate() {
        return endInterestDate;
    }

    public void setEndInterestDate(Date endInterestDate) {
        this.endInterestDate = endInterestDate;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
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

    @Override
    public String toString() {
        return "MinRepayLoanReqDetail{" +
                "id=" + id +
                ", serialNo='" + serialNo + '\'' +
                ", userId='" + userId + '\'' +
                ", billNo='" + billNo + '\'' +
                ", loanProvideNo='" + loanProvideNo + '\'' +
                ", dueDate=" + dueDate +
                ", oriTppcode='" + oriTppcode + '\'' +
                ", oriFundOrgCode='" + oriFundOrgCode + '\'' +
                ", newLoanNo='" + newLoanNo + '\'' +
                ", newTppcode='" + newTppcode + '\'' +
                ", newFundOrgCode='" + newFundOrgCode + '\'' +
                ", transAmount=" + transAmount +
                ", processType=" + processType +
                ", loanRate=" + loanRate +
                ", newLoanAmount=" + newLoanAmount +
                ", startInterestDate=" + startInterestDate +
                ", endInterestDate=" + endInterestDate +
                ", trxId='" + trxId + '\'' +
                ", ext='" + ext + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}