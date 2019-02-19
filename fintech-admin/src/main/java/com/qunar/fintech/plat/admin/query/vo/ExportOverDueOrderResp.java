package com.qunar.fintech.plat.admin.query.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bob.li on 2015/12/21.
 */
public class ExportOverDueOrderResp {

    /* 订单类型 */
    private String busiTypeName;

    /* 用户id */
    private String userId;

    /* 姓名 */
    private String userName;

    /* 用户身份证号 */
    private String identCard;

    /* 业务订单号 */
    private String busiOrderNo;

    /* 贷款网关订单号 */
    private String loanProvideNo;

    /* 消费时间 */
    private Date payTime;

    /* 手机号 */
    private String mobile;

    /* 订单金额 */
    private BigDecimal orderAmt;

    /* 贷款金额 */
    private BigDecimal loanAmt;

    /* 贷款周期 */
    private Integer loanTerm;

    /* 逾期金额 */
    private BigDecimal overDueAmt;

    /* 逾期罚息 */
    private BigDecimal overDueFineAmt;

    /* 应还款时间 */
    private Date dueDate;

    /* 订单详情信息 */
    private String orderDetail;

    /* 逾期天数 */
    private Integer overDueDay;

    /* 剩余应还款总金额 */
    private BigDecimal residueRepayTotalAmt;

    /* 剩余应还本金 */
    private BigDecimal residueRepayPrcpAmt;

    /* 剩余应还手续费金额 */
    private BigDecimal residueRepayFeeAmt;

    /* 剩余应还逾期罚金 */
    private BigDecimal residueRepayFineAmt;


    /* 本期应还款总金额 */
    private BigDecimal currRepayTotalAmt;

    /* 本期应还本金 */
    private BigDecimal currRepayPrcpAmt;

    /* 本期应还手续费金额 */
    private BigDecimal currRepayFeeAmt;

    /* 本期应还逾期罚金 */
    private BigDecimal currRepayFineAmt;

    /* 本期已还款总金额 */
    private BigDecimal currRepayedTotalAmt;

    /* 本期已还本金 */
    private BigDecimal currRepayedPrcpAmt;

    /* 本期已还手续费金额 */
    private BigDecimal currRepayedFeeAmt;

    /* 本期已还逾期罚金 */
    private BigDecimal currRepayedFineAmt;


    /* 干系人类别(与联系人的亲密程度) */
    private String stakeholdersType;

    /* 干系人姓名（多个） */
    private String stakeholdersName;

    /* 干系人地址 */
    private String stakeholdersAddress;

    /* 干系人邮箱 */
    private String stakeholdersEmail;

    /* 干系人手机号（多个）  */
    private String stakeholdersPhone;

    /* 支付订单号 */
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBusiTypeName() {
        return busiTypeName;
    }

    public void setBusiTypeName(String busiTypeName) {
        this.busiTypeName = busiTypeName;
    }

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

    public String getIdentCard() {
        return identCard;
    }

    public void setIdentCard(String identCard) {
        this.identCard = identCard;
    }

    public String getBusiOrderNo() {
        return busiOrderNo;
    }

    public void setBusiOrderNo(String busiOrderNo) {
        this.busiOrderNo = busiOrderNo;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
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

    public BigDecimal getOverDueAmt() {
        return overDueAmt;
    }

    public void setOverDueAmt(BigDecimal overDueAmt) {
        this.overDueAmt = overDueAmt;
    }

    public BigDecimal getOverDueFineAmt() {
        return overDueFineAmt;
    }

    public void setOverDueFineAmt(BigDecimal overDueFineAmt) {
        this.overDueFineAmt = overDueFineAmt;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Integer getOverDueDay() {
        return overDueDay;
    }

    public void setOverDueDay(Integer overDueDay) {
        this.overDueDay = overDueDay;
    }

    public BigDecimal getResidueRepayTotalAmt() {
        return residueRepayTotalAmt;
    }

    public void setResidueRepayTotalAmt(BigDecimal residueRepayTotalAmt) {
        this.residueRepayTotalAmt = residueRepayTotalAmt;
    }

    public BigDecimal getResidueRepayPrcpAmt() {
        return residueRepayPrcpAmt;
    }

    public void setResidueRepayPrcpAmt(BigDecimal residueRepayPrcpAmt) {
        this.residueRepayPrcpAmt = residueRepayPrcpAmt;
    }

    public BigDecimal getResidueRepayFeeAmt() {
        return residueRepayFeeAmt;
    }

    public void setResidueRepayFeeAmt(BigDecimal residueRepayFeeAmt) {
        this.residueRepayFeeAmt = residueRepayFeeAmt;
    }

    public BigDecimal getResidueRepayFineAmt() {
        return residueRepayFineAmt;
    }

    public void setResidueRepayFineAmt(BigDecimal residueRepayFineAmt) {
        this.residueRepayFineAmt = residueRepayFineAmt;
    }

    public String getStakeholdersType() {
        return stakeholdersType;
    }

    public void setStakeholdersType(String stakeholdersType) {
        this.stakeholdersType = stakeholdersType;
    }

    public String getStakeholdersName() {
        return stakeholdersName;
    }

    public void setStakeholdersName(String stakeholdersName) {
        this.stakeholdersName = stakeholdersName;
    }

    public String getStakeholdersAddress() {
        return stakeholdersAddress;
    }

    public void setStakeholdersAddress(String stakeholdersAddress) {
        this.stakeholdersAddress = stakeholdersAddress;
    }

    public String getStakeholdersEmail() {
        return stakeholdersEmail;
    }

    public void setStakeholdersEmail(String stakeholdersEmail) {
        this.stakeholdersEmail = stakeholdersEmail;
    }

    public String getStakeholdersPhone() {
        return stakeholdersPhone;
    }

    public void setStakeholdersPhone(String stakeholdersPhone) {
        this.stakeholdersPhone = stakeholdersPhone;
    }

    public BigDecimal getCurrRepayTotalAmt() {
        return currRepayTotalAmt;
    }

    public void setCurrRepayTotalAmt(BigDecimal currRepayTotalAmt) {
        this.currRepayTotalAmt = currRepayTotalAmt;
    }

    public BigDecimal getCurrRepayPrcpAmt() {
        return currRepayPrcpAmt;
    }

    public void setCurrRepayPrcpAmt(BigDecimal currRepayPrcpAmt) {
        this.currRepayPrcpAmt = currRepayPrcpAmt;
    }

    public BigDecimal getCurrRepayFeeAmt() {
        return currRepayFeeAmt;
    }

    public void setCurrRepayFeeAmt(BigDecimal currRepayFeeAmt) {
        this.currRepayFeeAmt = currRepayFeeAmt;
    }

    public BigDecimal getCurrRepayFineAmt() {
        return currRepayFineAmt;
    }

    public void setCurrRepayFineAmt(BigDecimal currRepayFineAmt) {
        this.currRepayFineAmt = currRepayFineAmt;
    }

    public BigDecimal getCurrRepayedTotalAmt() {
        return currRepayedTotalAmt;
    }

    public void setCurrRepayedTotalAmt(BigDecimal currRepayedTotalAmt) {
        this.currRepayedTotalAmt = currRepayedTotalAmt;
    }

    public BigDecimal getCurrRepayedPrcpAmt() {
        return currRepayedPrcpAmt;
    }

    public void setCurrRepayedPrcpAmt(BigDecimal currRepayedPrcpAmt) {
        this.currRepayedPrcpAmt = currRepayedPrcpAmt;
    }

    public BigDecimal getCurrRepayedFeeAmt() {
        return currRepayedFeeAmt;
    }

    public void setCurrRepayedFeeAmt(BigDecimal currRepayedFeeAmt) {
        this.currRepayedFeeAmt = currRepayedFeeAmt;
    }

    public BigDecimal getCurrRepayedFineAmt() {
        return currRepayedFineAmt;
    }

    public void setCurrRepayedFineAmt(BigDecimal currRepayedFineAmt) {
        this.currRepayedFineAmt = currRepayedFineAmt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExportOverDueOrderResp{");
        sb.append("busiTypeName='").append(busiTypeName).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", identCard='").append(identCard).append('\'');
        sb.append(", busiOrderNo='").append(busiOrderNo).append('\'');
        sb.append(", loanProvideNo='").append(loanProvideNo).append('\'');
        sb.append(", payTime=").append(payTime);
        sb.append(", mobile='").append("hidden mobile").append('\'');
        sb.append(", orderAmt=").append(orderAmt);
        sb.append(", loanAmt=").append(loanAmt);
        sb.append(", loanTerm=").append(loanTerm);
        sb.append(", overDueAmt=").append(overDueAmt);
        sb.append(", overDueFineAmt=").append(overDueFineAmt);
        sb.append(", dueDate=").append(dueDate);
        sb.append(", orderDetail='").append(orderDetail).append('\'');
        sb.append(", overDueDay=").append(overDueDay);
        sb.append(", residueRepayAmt=").append(residueRepayTotalAmt);
        sb.append(", residueRepayPrcpAmt=").append(residueRepayPrcpAmt);
        sb.append(", residueRepayFeeAmt=").append(residueRepayFeeAmt);
        sb.append(", residueRepayFineAmt=").append(residueRepayFineAmt);
        sb.append(", currRepayTotalAmt=").append(currRepayTotalAmt);
        sb.append(", currRepayPrcpAmt=").append(currRepayPrcpAmt);
        sb.append(", currRepayFeeAmt=").append(currRepayFeeAmt);
        sb.append(", currRepayFineAmt=").append(currRepayFineAmt);
        sb.append(", currRepayedTotalAmt=").append(currRepayedTotalAmt);
        sb.append(", currRepayedPrcpAmt=").append(currRepayedPrcpAmt);
        sb.append(", currRepayedFeeAmt=").append(currRepayedFeeAmt);
        sb.append(", currRepayedFineAmt=").append(currRepayedFineAmt);
        sb.append(", stakeholdersType='").append(stakeholdersType).append('\'');
        sb.append(", stakeholdersName='").append(stakeholdersName).append('\'');
        sb.append(", stakeholdersAddress='").append(stakeholdersAddress).append('\'');
        sb.append(", stakeholdersEmail='").append(stakeholdersEmail).append('\'');
        sb.append(", stakeholdersPhone='").append(stakeholdersPhone).append('\'');
        sb.append(", orderNo='").append(orderNo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
