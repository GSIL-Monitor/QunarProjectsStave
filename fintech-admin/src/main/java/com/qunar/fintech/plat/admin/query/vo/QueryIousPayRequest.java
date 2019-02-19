package com.qunar.fintech.plat.admin.query.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hongyang.gao on 2015/9/23.
 */
public class QueryIousPayRequest implements Serializable{
    private static final long serialVersionUID = 4823065912778434322L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 支付订单号
     */
    private String orderNo;
    /**
     * 业务线订单号
     */
    private String busiOrderNo;
    /**
     * 产品编码
     */
    private String productNo;
    /**
     * 贷款提供商
     */
    private String tppCode;
    /**
     * 发送给网关,网关发送给服务商的贷款流水
     */
    private String loanProvideNo;
    /**
     * 订单日期
     */
    private Date orderTime;
    /**
     * 订单日期查询开始时间
     */
    private String orderTimeStartTime;
    /**
     * 订单日期查询结束时间
     */
    private String orderTimeEndTime;
    /**
     * 业务线ID
     */
    private String busiTypeId;
    /**
     * 代理商ID
     */
    private String merchantCode;
    /**
     * 状态 0:初始,1:成功,2:失败,3:处理中
     */
    private Integer status;
    /**
     * 支付流水,同时作为贷款流水号
     */
    private String payId;
    /**渠道*/
    private String orgChannel;
    
    public String getOrgChannel() {
		return orgChannel;
	}

    /**
     * 网关支付流水号（即贷款流水/借据号）
     */
    private String gwPayId;

	public void setOrgChannel(String orgChannel) {
		this.orgChannel = orgChannel;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBusiOrderNo() {
        return busiOrderNo;
    }

    public void setBusiOrderNo(String busiOrderNo) {
        this.busiOrderNo = busiOrderNo;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date payTime) {
        this.orderTime = orderTime;
    }

    public String getBusiTypeId() {
        return busiTypeId;
    }

    public void setBusiTypeId(String busiTypeId) {
        this.busiTypeId = busiTypeId;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderTimeEndTime() {
        return orderTimeEndTime;
    }

    public void setOrderTimeEndTime(String orderTimeEndTime) {
        this.orderTimeEndTime = orderTimeEndTime;
    }

    public String getOrderTimeStartTime() {
        return orderTimeStartTime;
    }

    public void setOrderTimeStartTime(String orderTimeStartTime) {
        this.orderTimeStartTime = orderTimeStartTime;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getGwPayId() {
        return gwPayId;
    }

    public void setGwPayId(String gwPayId) {
        this.gwPayId = gwPayId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QueryIousPayRequest{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", orderNo='").append(orderNo).append('\'');
        sb.append(", busiOrderNo='").append(busiOrderNo).append('\'');
        sb.append(", productNo='").append(productNo).append('\'');
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", loanProvideNo='").append(loanProvideNo).append('\'');
        sb.append(", orderTime=").append(orderTime);
        sb.append(", orderTimeStartTime='").append(orderTimeStartTime).append('\'');
        sb.append(", orderTimeEndTime='").append(orderTimeEndTime).append('\'');
        sb.append(", busiTypeId='").append(busiTypeId).append('\'');
        sb.append(", merchantCode='").append(merchantCode).append('\'');
        sb.append(", status=").append(status);
        sb.append(", payId='").append(payId).append('\'');
        sb.append(", orgChannel='").append(orgChannel).append('\'');
        sb.append(", gwPayId='").append(gwPayId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
