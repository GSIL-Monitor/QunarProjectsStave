package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hongyang.gao on 2015/9/23.
 */
public class PayDetail extends ToString{

    private static final long serialVersionUID = 4823065912778434322L;

    /**
     * 支付发起时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 支付结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 银行
     */
    private String bankName;

    /**
     * 银行卡号
     */
    private String cardNo;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 支付状态
     */
    private Integer status;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 退款发起时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date refundStartTime;

    /**
     * 退款结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date refundEndTime;

    private String statusName;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Date getRefundStartTime() {
        return refundStartTime;
    }

    public void setRefundStartTime(Date refundStartTime) {
        this.refundStartTime = refundStartTime;
    }

    public Date getRefundEndTime() {
        return refundEndTime;
    }

    public void setRefundEndTime(Date refundEndTime) {
        this.refundEndTime = refundEndTime;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
