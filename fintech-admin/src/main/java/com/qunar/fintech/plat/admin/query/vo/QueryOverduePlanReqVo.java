package com.qunar.fintech.plat.admin.query.vo;

import java.io.Serializable;

/**
 * 催收页面--逾期单查询
 * @author dw.wang
 * @since 2017-03-06.
 */
public class QueryOverduePlanReqVo implements Serializable {
    private static final long serialVersionUID = 7420314713474814170L;

    /**
     * 用户id（必填）
     */
    private String userId;
    /**
     * 贷款提供商
     */
    private String tppCode;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 贷款日期--起始
     */
    private String startDate;
    /**
     * 贷款日期--终止
     */
    private String endDate;
    /**
     * 产品码
     */
    private String productNo;
    /**
     * 催收状态
     */
    private int urgeStatus;
    /**
     * 渠道
     */
    private String orgChannel;
    /**
     * 借据号
     */
    private String loanProvideNo;

    /**
     * 还款类型
     * REPAY 普通还款
     * ADVANCE 提前还款
     */
    private String repayType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public int getUrgeStatus() {
        return urgeStatus;
    }

    public void setUrgeStatus(int urgeStatus) {
        this.urgeStatus = urgeStatus;
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

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    @Override
    public String toString() {
        return "QueryOverduePlanReqVo{" +
                "userId='" + userId + '\'' +
                ", tppCode='" + tppCode + '\'' +
                ", mobile='" + mobile + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", productNo='" + productNo + '\'' +
                ", urgeStatus=" + urgeStatus +
                ", orgChannel='" + orgChannel + '\'' +
                ", loanProvideNo='" + loanProvideNo + '\'' +
                ", repayType='" + repayType + '\'' +
                '}';
    }
}
