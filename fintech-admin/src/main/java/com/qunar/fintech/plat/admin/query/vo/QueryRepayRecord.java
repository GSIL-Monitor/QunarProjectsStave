package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 还款记录
 */
public class QueryRepayRecord extends ToString{

    /* 用户ID */
    private String userId;

    /* 请求ID */
    private String reqUserId;

    private String reqSerialNo;

    /* 手机号 */
    private String mobile;

    /* 业务来源 */
    private String orgChannel;

    /* 姓名 */
    private String userName;

    /* 身份证号 */
    private String identity;

    /* 产品线 */
    private String productNo;

    /* 还款金额 */
    private BigDecimal repayAmount;

    /* 业务线订单号 */
    private String busiOrderNo;

    /* 还款方式 */
    private String repayType;

    /* 还款来源 */
    private String channel;

    /* 还款状态 */
    private Integer status;

    private String statusName;

    /* 还款模式 */
    private String repayWithholdMode;

    /* 还款时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date repayTime;

    /* 还款期序（第几期）提前还款时为0 */
    private Integer repayIndex;

    /* 贷款到期日 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    /* 贷款提供商 */
    private String tppCode;

    /* 错误信息 */
    private String errorMsg;

    /* 错误码 */
    private String errorCode;

    /* 建议话术 */
    private String suggest;

    private String loanProvideNo;

    private String orderNo;

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

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public String getBusiOrderNo() {
        return busiOrderNo;
    }

    public void setBusiOrderNo(String busiOrderNo) {
        this.busiOrderNo = busiOrderNo;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public Integer getRepayIndex() {
        return repayIndex;
    }

    public void setRepayIndex(Integer repayIndex) {
        this.repayIndex = repayIndex;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public String getReqSerialNo() {
        return reqSerialNo;
    }

    public void setReqSerialNo(String reqSerialNo) {
        this.reqSerialNo = reqSerialNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String transRepayStatus(Integer status) {
        switch (status) {
            case 0:
                return "初始状态";
            case 1:
                return "还款中";
            case 2:
                return "银行已受理";
            case 3:
                return "还款成功";
            case 4:
                return "重复支付";
            case 5:
                return "订单关闭";
            case 6:
                return "主动撤销";
            case 7:
                return "还款失败";
            default:
                return "";
        }
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public String getRepayWithholdMode() {
        return repayWithholdMode;
    }

    public void setRepayWithholdMode(String repayWithholdMode) {
        this.repayWithholdMode = repayWithholdMode;
    }
}
