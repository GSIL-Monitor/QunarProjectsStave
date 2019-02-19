package com.qunar.fintech.plat.admin.query.entity;


import com.qunar.pay.g2.utils.persistence.Entity;

import java.math.BigDecimal;
import java.util.Date;

public class MinRepayLoanReq implements  Entity<Long>  {
    private Long id;

    private String userId;

    private String orgChannel;

    private String productNo;

    private String billNo;

    private String serialNo;

    private Date dueDate;

    private String newTppcode;

    private String newFundOrgCode;
    /**
     * MinRepayProcessType
     */
    private String processType;

    /**
     * CommonStatus
     */
    private Integer processStatus;

    /* 总转债金额 */
    private BigDecimal totalTransAmount;

    /* 首次到期日 */
    private Date fixDueDate;

    private String qunarTradeNo;

    private String ext;

    private String errorCode;

    private String errorMsg;

    /* 完成时间 */
    private Date finishTime;

    private Date createTime;

    private Date updateTime;

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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
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

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
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

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public BigDecimal getTotalTransAmount() {
        return totalTransAmount;
    }

    public void setTotalTransAmount(BigDecimal totalTransAmount) {
        this.totalTransAmount = totalTransAmount;
    }

    public String getQunarTradeNo() {
        return qunarTradeNo;
    }

    public void setQunarTradeNo(String qunarTradeNo) {
        this.qunarTradeNo = qunarTradeNo;
    }

    public Date getFixDueDate() {
        return fixDueDate;
    }

    public void setFixDueDate(Date fixDueDate) {
        this.fixDueDate = fixDueDate;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "MinRepayLoanReq{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", orgChannel='" + orgChannel + '\'' +
                ", productNo='" + productNo + '\'' +
                ", billNo='" + billNo + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", dueDate=" + dueDate +
                ", newTppcode='" + newTppcode + '\'' +
                ", newFundOrgCode='" + newFundOrgCode + '\'' +
                ", processType='" + processType + '\'' +
                ", processStatus=" + processStatus +
                ", totalTransAmount=" + totalTransAmount +
                ", fixDueDate=" + fixDueDate +
                ", qunarTradeNo='" + qunarTradeNo + '\'' +
                ", ext='" + ext + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", finishTime=" + finishTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}