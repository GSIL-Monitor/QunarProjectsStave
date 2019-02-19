package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.ious.common.enums.ProductEnum;
import com.qunar.pay.finance.qf.commons.api.model.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dw.wang
 * @since 2017-03-14.
 */
public class OverdueRepayPlan extends ToString{

    private String userId;

    private String userName;

    private String mobile;

    /**
     * @see ProductEnum
     */
    private String productNo;

    private String tppCode;

    private String orgChannel;

    private String loanProvideNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    private Integer repayIndex;

    private BigDecimal prcpAmt;

    private BigDecimal setlPrcpAmt;

    private BigDecimal intAmt;

    private BigDecimal setlIntAmt;

    private BigDecimal spreadsAmt;

    private BigDecimal setlSpreadsAmt;

    private BigDecimal fineAmt;

    private BigDecimal setlFineAmt;

    /**
     * UserRepayPlanStatusEnum
     */
    private Integer status;

    /**
     * UserRepayPlanLockStatusEnum
     */
    private Integer lockStatus;

    /**
     * GatewayRepayStatusEnum
     */
    private Integer gateStatus;

    /**
     * UserRepayFlagEnum
     */
    private String repayFlag;

    private Date actRepayDate;

    private Date lastSyncTime;

    private Date lockTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private Date updateTime;

    /**
     *  逾期天数。
     */
    private String overDueDays;
    /**
     *  分期总数
     */
    private Integer loanTerm;

    private String term;
    /**
     *  还款总额
     */
    private BigDecimal repayTotalAmt;

    private String msgStatus;

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

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
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

    public Integer getRepayIndex() {
        return repayIndex;
    }

    public void setRepayIndex(Integer repayIndex) {
        this.repayIndex = repayIndex;
    }

    public BigDecimal getPrcpAmt() {
        return prcpAmt;
    }

    public void setPrcpAmt(BigDecimal prcpAmt) {
        this.prcpAmt = prcpAmt;
    }

    public BigDecimal getSetlPrcpAmt() {
        return setlPrcpAmt;
    }

    public void setSetlPrcpAmt(BigDecimal setlPrcpAmt) {
        this.setlPrcpAmt = setlPrcpAmt;
    }

    public BigDecimal getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    public BigDecimal getSetlIntAmt() {
        return setlIntAmt;
    }

    public void setSetlIntAmt(BigDecimal setlIntAmt) {
        this.setlIntAmt = setlIntAmt;
    }

    public BigDecimal getSpreadsAmt() {
        return spreadsAmt;
    }

    public void setSpreadsAmt(BigDecimal spreadsAmt) {
        this.spreadsAmt = spreadsAmt;
    }

    public BigDecimal getSetlSpreadsAmt() {
        return setlSpreadsAmt;
    }

    public void setSetlSpreadsAmt(BigDecimal setlSpreadsAmt) {
        this.setlSpreadsAmt = setlSpreadsAmt;
    }

    public BigDecimal getFineAmt() {
        return fineAmt;
    }

    public void setFineAmt(BigDecimal fineAmt) {
        this.fineAmt = fineAmt;
    }

    public BigDecimal getSetlFineAmt() {
        return setlFineAmt;
    }

    public void setSetlFineAmt(BigDecimal setlFineAmt) {
        this.setlFineAmt = setlFineAmt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Integer getGateStatus() {
        return gateStatus;
    }

    public void setGateStatus(Integer gateStatus) {
        this.gateStatus = gateStatus;
    }

    public String getRepayFlag() {
        return repayFlag;
    }

    public void setRepayFlag(String repayFlag) {
        this.repayFlag = repayFlag;
    }

    public Date getActRepayDate() {
        return actRepayDate;
    }

    public void setActRepayDate(Date actRepayDate) {
        this.actRepayDate = actRepayDate;
    }

    public Date getLastSyncTime() {
        return lastSyncTime;
    }

    public void setLastSyncTime(Date lastSyncTime) {
        this.lastSyncTime = lastSyncTime;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
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

    public String getOverDueDays() {
        return overDueDays;
    }

    public void setOverDueDays(String overDueDays) {
        this.overDueDays = overDueDays;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public BigDecimal getRepayTotalAmt() {
        return repayTotalAmt;
    }

    public void setRepayTotalAmt(BigDecimal repayTotalAmt) {
        this.repayTotalAmt = repayTotalAmt;
    }

    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
