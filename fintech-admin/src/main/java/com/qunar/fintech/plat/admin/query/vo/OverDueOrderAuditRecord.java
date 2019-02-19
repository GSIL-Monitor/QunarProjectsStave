package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bob.li on 2015/9/17.
 */
public class OverDueOrderAuditRecord extends ToString {
    private static final long serialVersionUID = 1280123651037114896L;

    /* 用户id */
    private String userId;

    /* 姓名 */
    private String userName;

    /* 手机号 */
    private String mobile;

    /* 贷款提供商 */
    private String tppCode;

    /* 业务订单号 */
    private String busiOrderNo;

    /* 订单日期 */
    private Date orderTime;

    /* 贷款金额 */
    private BigDecimal loanAmt;

    /* 贷款周期 */
    private Integer loanTerm;

    /* 贷款期数 */
    private Integer loanIndex;

    /* 扣款金额 */
    private BigDecimal withHoldAmt;

    /* 本期应还金额 */
    private BigDecimal repayAmtCurrentTerm;

    /* 审核状态 */
    private Integer auditStatus;

    /* 贷款流水号 */
    private String loanProvideNo;

    /* 到期日 */
    private Date dueDate;

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
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

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getBusiOrderNo() {
        return busiOrderNo;
    }

    public void setBusiOrderNo(String busiOrderNo) {
        this.busiOrderNo = busiOrderNo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(BigDecimal loanAmt) {
        this.loanAmt = loanAmt;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public Integer getLoanIndex() {
        return loanIndex;
    }

    public void setLoanIndex(Integer loanIndex) {
        this.loanIndex = loanIndex;
    }

    public BigDecimal getWithHoldAmt() {
        return withHoldAmt;
    }

    public void setWithHoldAmt(BigDecimal withHoldAmt) {
        this.withHoldAmt = withHoldAmt;
    }

    public BigDecimal getRepayAmtCurrentTerm() {
        return repayAmtCurrentTerm;
    }

    public void setRepayAmtCurrentTerm(BigDecimal repayAmtCurrentTerm) {
        this.repayAmtCurrentTerm = repayAmtCurrentTerm;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

}
