package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述：</b>TblLoanInfo:信用支付订单表<br>
 * 
 * @author baron.jiang
 * @since：2015年02月04日 10时43分45秒 星期三
 */
public class TblLoanInfo extends ToString {
    private static final Integer NO_TRANS = 0;
    public static final Integer SETTLE = 1;    // 已结清
    public static final Integer UNSETTLE = 0;  // 未结清
    private static final long serialVersionUID = 2203875076592349181L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 业务线ID
     */
    private String busiTypeId;
    /**
     * 业务线名称
     */
    private String busiTypeName;

    /**
     * 代理商ID
     */
    private String merchantCode;
    /**
     * 发起流水
     */
    private String orderNo;
    /**
     * 统一流水号
     */
    private String qunarTradeNo;
    /**
     * 订单支付总金额
     */
    private BigDecimal payAmount;
    /**
     * 信任付支付金额
     */
    private BigDecimal iousPayAmount;
    /**
     * 已退款信任付金额
     */
    private BigDecimal refundedAmount;
    /**
     * 网关流水
     */
    private String payId;
    /**
     * 支付流水
     */
    private String loanProvideNo;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 请求id
     */
    private String reqUserId;
    /**
     * 产品编号
     */
    private String productNo;
    /**
     * 签约银行
     */
    private String tppCode;
    /**
     * 支付完成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;
    /**
     * 支付状态
     */
    private String payStatus;

    /**
     * 贷款状态
     */
    private Integer loanStatus;
    /**
     * 贷款周期，目前支持3，6，9，12
     */
    private Integer loanTerm;

    /**
     * 当前欠款金额（这笔订单的当前欠款金额）
     */
    private BigDecimal debtAmountAll;

    /**
     * 逾期金额
     */
    private BigDecimal overDueAmount;

    /**
     * 当前逾期订单数量
     */
    private Integer currentOverdueCount;
    /**
     * 还款方式
     */
    private String repayType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 业务线订单号
     */
    private String busiOrderNo;
    /**
     * 生成时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 执行利率
     */
    private BigDecimal intRate;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 身份证号
     */
    private String identity;

    /**
     * 银行流水号
     */
    private String bankSerialNo;

    /**
     * 来自转分期的类型
     */
    private Integer transFrom;

    /**
     * 贷款到期日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    /**
     * 订单时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    private String orgChannel;

    private Integer settleStatus;

    private String productName;

    public boolean whetherSettle(){
        return this.settleStatus.equals(SETTLE);
    }

    /**
     * 是否来自转分期
     * @return
     */
    public boolean isFromTrans(){
        return !NO_TRANS.equals(this.transFrom);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusiTypeId() {
        return this.busiTypeId;
    }

    public void setBusiTypeId(String busiTypeId) {
        this.busiTypeId = busiTypeId;
    }

    public String getBusiTypeName() {
        return busiTypeName;
    }

    public void setBusiTypeName(String busiTypeName) {
        this.busiTypeName = busiTypeName;
    }

    public String getMerchantCode() {
        return this.merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getQunarTradeNo() {
        return this.qunarTradeNo;
    }

    public void setQunarTradeNo(String qunarTradeNo) {
        this.qunarTradeNo = qunarTradeNo;
    }

    public BigDecimal getPayAmount() {
        return this.payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getIousPayAmount() {
        return this.iousPayAmount;
    }

    public void setIousPayAmount(BigDecimal iousPayAmount) {
        this.iousPayAmount = iousPayAmount;
    }

    public BigDecimal getRefundedAmount() {
        return refundedAmount;
    }

    public void setRefundedAmount(BigDecimal refundedAmount) {
        this.refundedAmount = refundedAmount;
    }

    public String getPayId() {
        return this.payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getLoanProvideNo() {
        return this.loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTppCode() {
        return this.tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public Date getPayTime() {
        return this.payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }


    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(Integer loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Integer getLoanTerm() {
        return this.loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getRepayType() {
        return this.repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getIntRate() {
        return intRate;
    }

    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    public String getBusiOrderNo() {
        return busiOrderNo;
    }

    public void setBusiOrderNo(String busiOrderNo) {
        this.busiOrderNo = busiOrderNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBankSerialNo() {
        return bankSerialNo;
    }

    public void setBankSerialNo(String bankSerialNo) {
        this.bankSerialNo = bankSerialNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getReqUserId() {
        return reqUserId;
    }

    public void setReqUserId(String reqUserId) {
        this.reqUserId = reqUserId;
    }

    public Integer getTransFrom() {
        return transFrom;
    }

    public void setTransFrom(Integer transFrom) {
        this.transFrom = transFrom;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public Integer getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(Integer settleStatus) {
        this.settleStatus = settleStatus;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getDebtAmountAll() {
        return debtAmountAll;
    }

    public void setDebtAmountAll(BigDecimal debtAmountAll) {
        this.debtAmountAll = debtAmountAll;
    }

    public BigDecimal getOverDueAmount() {
        return overDueAmount;
    }

    public void setOverDueAmount(BigDecimal overDueAmount) {
        this.overDueAmount = overDueAmount;
    }

    public Integer getCurrentOverdueCount() {
        return currentOverdueCount;
    }

    public void setCurrentOverdueCount(Integer currentOverdueCount) {
        this.currentOverdueCount = currentOverdueCount;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
