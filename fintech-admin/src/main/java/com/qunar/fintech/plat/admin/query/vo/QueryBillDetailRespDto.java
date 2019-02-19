package com.qunar.fintech.plat.admin.query.vo;

import java.io.Serializable;

/**
 * Created by libo on 2018/2/3.
 */
public class QueryBillDetailRespDto implements Serializable {
    private String loanProvideNo;
    private String busiOrderNo;
    private String merchantNo;
    private String repayAmt;
    private String termInfo;
    private String tppCode;
    private String prcpAmt;
    private String intAmt;
    private String fineAmt;
    private String loanDate;

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public String getBusiOrderNo() {
        return busiOrderNo;
    }

    public void setBusiOrderNo(String busiOrderNo) {
        this.busiOrderNo = busiOrderNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getRepayAmt() {
        return repayAmt;
    }

    public void setRepayAmt(String repayAmt) {
        this.repayAmt = repayAmt;
    }

    public String getTermInfo() {
        return termInfo;
    }

    public void setTermInfo(String termInfo) {
        this.termInfo = termInfo;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getPrcpAmt() {
        return prcpAmt;
    }

    public void setPrcpAmt(String prcpAmt) {
        this.prcpAmt = prcpAmt;
    }

    public String getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(String intAmt) {
        this.intAmt = intAmt;
    }

    public String getFineAmt() {
        return fineAmt;
    }

    public void setFineAmt(String fineAmt) {
        this.fineAmt = fineAmt;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    @Override
    public String toString() {
        return "QueryBillDetailRespDto{" +
                "busiOrderNo='" + busiOrderNo + '\'' +
                ", loanProvideNo='" + loanProvideNo + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", repayAmt='" + repayAmt + '\'' +
                ", termInfo='" + termInfo + '\'' +
                ", tppCode='" + tppCode + '\'' +
                ", prcpAmt='" + prcpAmt + '\'' +
                ", intAmt='" + intAmt + '\'' +
                ", fineAmt='" + fineAmt + '\'' +
                ", loanDate='" + loanDate + '\'' +
                '}';
    }
}
