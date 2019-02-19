package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by libo on 2017/4/17.
 */
public class TblOperRecord extends ToString {
    private Long id;

    /**
     * 变动金额
     */
    private BigDecimal amount;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 贷款流水号
     */
    private String loanProvideNo;

    /**
     * 期数
     */
    private Integer repayIndex;

    /**
     * 总期数
     */
    private Integer totalRepayIndex;

    /**
     * 记录类型
     */
    private Integer recordType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作时间
     */
    private Date operTime;

    /**
     * 违约金
     */
    private BigDecimal fineAmount;

    /**
     * 还款方式（提前还款:TIQIAN 正常还款:NORMAL,逾期:DUE）
     */
    private String repayType;

    /**
     * 支付路径
     */
    private String paymentWayName;
    /**
     * 卡号后4位
     */
    private String showCardNo;
    /**
     * 支付渠道（信用卡）
     */
    private String catalogName;
    /**
     * 退款状态
     */
    private String operStatus;
    /**
     * 退款类别
     */
    private String refundCategory;
    /**
     * 退款请求流水(user_repay_req.serial_no)
     */
    private String reqSerialNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public Integer getRepayIndex() {
        return repayIndex;
    }

    public void setRepayIndex(Integer repayIndex) {
        this.repayIndex = repayIndex;
    }

    public Integer getTotalRepayIndex() {
        return totalRepayIndex;
    }

    public void setTotalRepayIndex(Integer totalRepayIndex) {
        this.totalRepayIndex = totalRepayIndex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public String getPaymentWayName() {
        return paymentWayName;
    }

    public void setPaymentWayName(String paymentWayName) {
        this.paymentWayName = paymentWayName;
    }

    public String getShowCardNo() {
        return showCardNo;
    }

    public void setShowCardNo(String showCardNo) {
        this.showCardNo = showCardNo;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getReqSerialNo() {
        return reqSerialNo;
    }

    public void setReqSerialNo(String reqSerialNo) {
        this.reqSerialNo = reqSerialNo;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public String getOperStatus() {
        return operStatus;
    }

    public void setOperStatus(String operStatus) {
        this.operStatus = operStatus;
    }

    public String getRefundCategory() {
        return refundCategory;
    }

    public void setRefundCategory(String refundCategory) {
        this.refundCategory = refundCategory;
    }
}
