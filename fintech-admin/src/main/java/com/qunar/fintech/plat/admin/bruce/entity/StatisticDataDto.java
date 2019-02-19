package com.qunar.fintech.plat.admin.bruce.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: xian.cheng
 * Date: 2018-12-29
 * Time: 16:44
 */
public class StatisticDataDto extends ToString {
    // 统计日期
    private String staticTime;
    // 通道编码
    private String tppCode;
    // 在贷余额
    private BigDecimal loanBalanceAmt;
    // 贷款金额
    private BigDecimal loanAmt;
    // 还款总额
    private BigDecimal totalRepayAmt;
    // 还款本金
    private BigDecimal actPrcpAmt;
    // 利息
    private BigDecimal actIntAmt;
    // 罚息
    private BigDecimal actFineAmt;
    // 手续费
    private BigDecimal actFeeAmt;
    // 息差
    private BigDecimal actSpreadsAmt;
    // 逾期90+
    private BigDecimal overdue90Amt;
    // 逾期60+
    private BigDecimal overdue60Amt;

    public Boolean ifNotEmpty(){
        return !ifEmpty();
    }
    public Boolean ifEmpty(){
        //本日内是否有数据
        return  null == this.overdue90Amt
                && null == this.overdue60Amt
                && null == this.loanBalanceAmt
                && null == this.loanAmt
                && null == this.totalRepayAmt
                && null == this.actPrcpAmt
                && null == this.actIntAmt
                && null == this.actFineAmt
                && null == this.actFeeAmt
                && null == this.actSpreadsAmt;
    }

    public BigDecimal getTotalRepayAmt() {
        return totalRepayAmt;
    }

    public void setTotalRepayAmt(BigDecimal totalRepayAmt) {
        this.totalRepayAmt = totalRepayAmt;
    }

    public BigDecimal getActPrcpAmt() {
        return actPrcpAmt;
    }

    public void setActPrcpAmt(BigDecimal actPrcpAmt) {
        this.actPrcpAmt = actPrcpAmt;
    }

    public BigDecimal getActIntAmt() {
        return actIntAmt;
    }

    public void setActIntAmt(BigDecimal actIntAmt) {
        this.actIntAmt = actIntAmt;
    }

    public BigDecimal getActFineAmt() {
        return actFineAmt;
    }

    public void setActFineAmt(BigDecimal actFineAmt) {
        this.actFineAmt = actFineAmt;
    }

    public BigDecimal getActFeeAmt() {
        return actFeeAmt;
    }

    public void setActFeeAmt(BigDecimal actFeeAmt) {
        this.actFeeAmt = actFeeAmt;
    }

    public BigDecimal getActSpreadsAmt() {
        return actSpreadsAmt;
    }

    public void setActSpreadsAmt(BigDecimal actSpreadsAmt) {
        this.actSpreadsAmt = actSpreadsAmt;
    }

    public String getStaticTime() {
        return staticTime;
    }

    public void setStaticTime(String staticTime) {
        this.staticTime = staticTime;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public BigDecimal getLoanBalanceAmt() {
        return loanBalanceAmt;
    }

    public void setLoanBalanceAmt(BigDecimal loanBalanceAmt) {
        this.loanBalanceAmt = loanBalanceAmt;
    }

    public BigDecimal getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(BigDecimal loanAmt) {
        this.loanAmt = loanAmt;
    }

    public BigDecimal getOverdue90Amt() {
        return overdue90Amt;
    }

    public void setOverdue90Amt(BigDecimal overdue90Amt) {
        this.overdue90Amt = overdue90Amt;
    }

    public BigDecimal getOverdue60Amt() {
        return overdue60Amt;
    }

    public void setOverdue60Amt(BigDecimal overdue60Amt) {
        this.overdue60Amt = overdue60Amt;
    }
}
