package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 信任付账户查询单条记录
 *
 * Created by baron.jiang on 2015/2/4.
 */
public class QueryRefundRecord extends ToString{

    /* 用户ID */
    private String userId;

    /* 请求ID */
    private String reqUserId;

    /* 手机号 */
    private String mobile;

    /* 姓名 */
    private String userName;

    /* 身份证号 */
    private String identity;

    /* 产品线 */
    private String productNo;

    /* 订单金额 */
    private BigDecimal orderAmount;

    /* 业务线订单号 */
    private String busiOrderNo;

    /* 业务线 */
    private String busiName;

    /* 消费时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /* 退款状态  0:初始,1:成功,2:失败,3:处理中 */
    private Integer status;

    /* 退款发起时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date requestTime;

    /* 退款完成时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date refundTime;

    /* 实际贷款金额 */
    private BigDecimal iousPayAmount;

    /* 退款总金额 */
    private BigDecimal refundAmount;

    /* 退款去向 */
    private String refundDetail;

    /* 错误信息 */
    private String errorMsg;

    /* 错误码 */
    private String errorCode;

    /* 建议话术 */
    private String suggest;

    /* 借据号 */
    private String loanProvideNo;

    /* 业务来源 */
    private String orgChannel;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReqUserId() {
        return reqUserId;
    }

    public void setReqUserId(String reqUserId) {
        this.reqUserId = reqUserId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getBusiOrderNo() {
        return busiOrderNo;
    }

    public void setBusiOrderNo(String busiOrderNo) {
        this.busiOrderNo = busiOrderNo;
    }

    public String getBusiNo() {
        return busiName;
    }

    public void setBusiNo(String busiName) {
        this.busiName = busiName;
    }

    public Date getPeyTime() {
        return payTime;
    }

    public void setPeyTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundDetail() {
        return refundDetail;
    }

    public void setRefundDetail(String refundDetail) {
        this.refundDetail = refundDetail;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public BigDecimal getIousPayAmount() {
        return iousPayAmount;
    }

    public void setIousPayAmount(BigDecimal iousPayAmount) {
        this.iousPayAmount = iousPayAmount;
    }

    public String getBusiName() {
        return busiName;
    }

    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }
}
