package com.qunar.fintech.plat.admin.query.vo;

import java.io.Serializable;

/**
 * Created by bob.li on 2015/9/14.
 */
public class QueryOverDueOrderReq implements Serializable {
    private static final long serialVersionUID = -3612154745499233396L;

    /* 业务订单号 */
    private String busiOrderNo;

    /* 订单日期开始时间 */
    private String startTime;

    /* 订单日期截止时间 */
    private String endTime;

    /* 产品编码 */
    private String productNo;

    /* 贷款提供商 */
    private String tppCode;

    /* 手机号 */
    private String mobile;

    /* 用户uid */
    private String userId;

    /* 姓名 */
    private String userName;
    /**渠道*/
    private String orgChannel;
    
    public String getOrgChannel() {
		return orgChannel;
	}

	public void setOrgChannel(String orgChannel) {
		this.orgChannel = orgChannel;
	}

	public String getBusiOrderNo() {
        return busiOrderNo;
    }

    public void setBusiOrderNo(String busiOrderNo) {
        this.busiOrderNo = busiOrderNo;
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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QueryOverDueOrderReq{");
        sb.append("busiOrderNo='").append(busiOrderNo).append('\'');
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", endTime='").append(endTime).append('\'');
        sb.append(", productNo='").append(productNo).append('\'');
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", orgChannel='").append(orgChannel).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
