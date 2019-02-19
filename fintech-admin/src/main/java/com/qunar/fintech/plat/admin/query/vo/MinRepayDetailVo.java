package com.qunar.fintech.plat.admin.query.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xi.cheng on 2018/2/5.
 */
public class MinRepayDetailVo {


    private Integer index;
    private String productName;

    private String tppCode;
    /**
     * 订单时间
     */
    private Date payTime;
    /**
     * 订单金额
     */
    private BigDecimal iousPayAmount;
    /**
     * 借据号
     */
    private String loanProvideNo;
    /**
     * 已还金额
     */
    private BigDecimal repaidAmount;
    /**
     * 待还金额
     */
    private BigDecimal needRepayAmount;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getIousPayAmount() {
        return iousPayAmount;
    }

    public void setIousPayAmount(BigDecimal iousPayAmount) {
        this.iousPayAmount = iousPayAmount;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public BigDecimal getRepaidAmount() {
        return repaidAmount;
    }

    public void setRepaidAmount(BigDecimal repaidAmount) {
        this.repaidAmount = repaidAmount;
    }

    public BigDecimal getNeedRepayAmount() {
        return needRepayAmount;
    }

    public void setNeedRepayAmount(BigDecimal needRepayAmount) {
        this.needRepayAmount = needRepayAmount;
    }

    @Override
    public String toString() {
        return "MinRepayDetailVo{" +
                " index=" + index +
                ",productName='" + productName + '\'' +
                ", tppCode='" + tppCode + '\'' +
                ", payTime=" + payTime +
                ", iousPayAmount=" + iousPayAmount +
                ", loanProvideNo='" + loanProvideNo + '\'' +
                ", repaidAmount=" + repaidAmount +
                ", needRepayAmount=" + needRepayAmount +
                '}';
    }
}
