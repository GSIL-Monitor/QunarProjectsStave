package com.qunar.fintech.plat.admin.query.vo;


import java.io.Serializable;
import java.util.List;

/**
 * 信任付订单查询请求类
 *
 * Created by baron.jiang on 2015/2/4.
 */
public class QueryIousOrderRequest implements Serializable {
    private static final long serialVersionUID = -733757439011786920L;

    /* 用户ID */
    private String userId;

    /* 手机号 */
    private String mobile;

    /* 交易支付流水 */
    private String orderNo;

    /* 业务订单号 */
    private String busiOrderNo;

    /* 贷款提供方 */
    private String productNo;
    /* 贷款提供方 */
    private String tppCode;

    /* 贷款周期 */
    private Integer loanTerm;

    /* 订单日期查询开始时间 */
    private String startTime;

    /* 订单日期查询截至时间 */
    private String endTime;

    /*页面输入的查询支付流水*/
    private String loanProvideNo;

    /*银行流水号*/
    private String bankSerialNo;
    /**渠道*/
    private String orgChannel;

    /* 查库所用的借据列表 */
    private List<String> loanNoList;


    public List<String> getLoanNoList() {
        return loanNoList;
    }

    public void setLoanNoList(List<String> loanNoList) {
        this.loanNoList = loanNoList;
    }

    public String getBankSerialNo() {
        return bankSerialNo;
    }

    public void setBankSerialNo(String bankSerialNo) {
        this.bankSerialNo = bankSerialNo;
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

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public String getOrgChannel() {
		return orgChannel;
	}

	public void setOrgChannel(String orgChannel) {
		this.orgChannel = orgChannel;
	}

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QueryIousOrderRequest{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", orderNo='").append(orderNo).append('\'');
        sb.append(", busiOrderNo='").append(busiOrderNo).append('\'');
        sb.append(", productNo='").append(productNo).append('\'');
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", loanTerm=").append(loanTerm);
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", endTime='").append(endTime).append('\'');
        sb.append(", loanProvideNo='").append(loanProvideNo).append('\'');
        sb.append(", bankSerialNo='").append(bankSerialNo).append('\'');
        sb.append(", orgChannel='").append(orgChannel).append('\'');
        sb.append(", loanNoList='").append(loanNoList).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
