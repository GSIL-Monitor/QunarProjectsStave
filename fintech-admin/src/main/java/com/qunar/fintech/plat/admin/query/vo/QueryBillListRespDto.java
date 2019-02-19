package com.qunar.fintech.plat.admin.query.vo;

import java.io.Serializable;

/**
 * Created by libo on 2018/2/3.
 */
public class QueryBillListRespDto implements Serializable {

    private String billNo;
    private String billStatus;
    private String billDate;
    private String updateTime;
    private String repayDate;
    private String userName;
    private String userId;
    private String mobile;
    private String billPrcpAmt;
    private String billIntAmt;
    private String billFineAmt;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBillPrcpAmt() {
        return billPrcpAmt;
    }

    public void setBillPrcpAmt(String billPrcpAmt) {
        this.billPrcpAmt = billPrcpAmt;
    }

    public String getBillIntAmt() {
        return billIntAmt;
    }

    public void setBillIntAmt(String billIntAmt) {
        this.billIntAmt = billIntAmt;
    }

    public String getBillFineAmt() {
        return billFineAmt;
    }

    public void setBillFineAmt(String billFineAmt) {
        this.billFineAmt = billFineAmt;
    }

    @Override
    public String toString() {
        return "QueryBillListRespDto{" +
                "billNo='" + billNo + '\'' +
                ", billStatus='" + billStatus + '\'' +
                ", billDate='" + billDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", repayDate='" + repayDate + '\'' +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", billPrcpAmt='" + billPrcpAmt + '\'' +
                ", billIntAmt='" + billIntAmt + '\'' +
                ", billFineAmt='" + billFineAmt + '\'' +
                '}';
    }
}
