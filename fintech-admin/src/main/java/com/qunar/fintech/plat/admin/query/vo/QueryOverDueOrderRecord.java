package com.qunar.fintech.plat.admin.query.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bob.li on 2015/9/14.
 */
public class QueryOverDueOrderRecord implements Serializable {
    private static final long serialVersionUID = -1742528201533729174L;

    /* 用户id */
    private String userId;

    /* 姓名 */
    private String userName;

    /* 手机号 */
    private String mobile;

    /* 产品编码 */
    private String productNo;
    /* 贷款提供商 */
    private String tppCode;

    /* 业务订单号 */
    private String busiOrderNo;

    /* 商户id */
    private String merchantCode;

    /* 订单日期 */
    private Date orderTime;

    /* 最大逾期天数 */
    private Integer maxOverDueDay;

    /* 订单金额 */
    private BigDecimal orderAmt;

    /* 贷款周期 */
    private Integer loanTerm;

    /* 贷款金额 */
    private BigDecimal loanAmt;

    /* 总手续费 */
    private BigDecimal totalHandingCharge;

    /* 贷款流水 */
    private String loanProvideNo;

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

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getMaxOverDueDay() {
        return maxOverDueDay;
    }

    public void setMaxOverDueDay(Integer maxOverDueDay) {
        this.maxOverDueDay = maxOverDueDay;
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public BigDecimal getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(BigDecimal loanAmt) {
        this.loanAmt = loanAmt;
    }

    public BigDecimal getTotalHandingCharge() {
        return totalHandingCharge;
    }

    public void setTotalHandingCharge(BigDecimal totalHandingCharge) {
        this.totalHandingCharge = totalHandingCharge;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QueryOverDueOrderRecord{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", productNo='").append(productNo).append('\'');
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", busiOrderNo='").append(busiOrderNo).append('\'');
        sb.append(", merchantCode='").append(merchantCode).append('\'');
        sb.append(", orderTime=").append(orderTime);
        sb.append(", orderAmt=").append(orderAmt);
        sb.append(", loanTerm=").append(loanTerm);
        sb.append(", loanAmt=").append(loanAmt);
        sb.append(", totalHandingCharge=").append(totalHandingCharge);
        sb.append(", maxOverDueDay=").append(maxOverDueDay);
        sb.append(", loanProvideNo='").append(loanProvideNo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
