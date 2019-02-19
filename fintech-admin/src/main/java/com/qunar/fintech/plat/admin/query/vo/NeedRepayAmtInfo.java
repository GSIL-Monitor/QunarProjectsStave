package com.qunar.fintech.plat.admin.query.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by bob.li on 2016/6/1.
 */
public class NeedRepayAmtInfo implements Serializable {
    private static final long serialVersionUID = 4819742111017850323L;

    /**
     * 第几期
     */
    private Integer term;
    /**
     * 借据号
     */
    private String loanProvideNo;
    /**
     * 到期日(yyyyMMdd)
     */
    private String dueDate;
    /**
     * 还款计划应还金额
     */
    private BigDecimal repayScheduleAmt;

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getRepayScheduleAmt() {
        return repayScheduleAmt;
    }

    public void setRepayScheduleAmt(BigDecimal repayScheduleAmt) {
        this.repayScheduleAmt = repayScheduleAmt;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NeedRepayAmtInfo{");
        sb.append("loanProvideNo='").append(loanProvideNo).append('\'');
        sb.append(", dueDate='").append(dueDate).append('\'');
        sb.append(", repayScheduleAmt=").append(repayScheduleAmt);
        sb.append(", term=").append(term);
        sb.append('}');
        return sb.toString();
    }
}
