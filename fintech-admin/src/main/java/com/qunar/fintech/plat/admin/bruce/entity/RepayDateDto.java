package com.qunar.fintech.plat.admin.bruce.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: xian.cheng
 * Date: 2019-01-16
 * Time: 17:44
 */
public class RepayDateDto extends ToString {
    //还款本金
    private BigDecimal actPrcpAmt;
    //利息
    private BigDecimal actIntAmt;
    //罚息
    private BigDecimal actFineAmt;
    //手续费
    private BigDecimal actFeeAmt;
    //息差
    private BigDecimal actSpreadsAmt;

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
}
