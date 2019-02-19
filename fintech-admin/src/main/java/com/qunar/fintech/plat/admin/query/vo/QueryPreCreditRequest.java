package com.qunar.fintech.plat.admin.query.vo;

import java.io.Serializable;

/**
 * 信任付订单查询请求类
 *
 * Created by shining.cui 2015/08/25
 */
public class QueryPreCreditRequest implements Serializable {

    private static final long serialVersionUID = 4034792225155434281L;
    /* 用户ID */
    private String userId;

    /* 手机号 */
    private String mobile;

    /* 姓名 */
    private String userName;

    /* 产品编码 */
    private String productNo;

    /* 贷款提供商 */
    private String tppCode;

    /* 预授信状态*/
    private Integer reqStatus;

    /* 预授信来源 */
    private String creditSrc;
    /**渠道*/
    private String orgChannel;
    
    public String getOrgChannel() {
		return orgChannel;
	}

	public void setOrgChannel(String orgChannel) {
		this.orgChannel = orgChannel;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
    }

    public String getCreditSrc() {
        return creditSrc;
    }

    public void setCreditSrc(String creditSrc) {
        this.creditSrc = creditSrc;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QueryPreCreditRequest{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", productNo='").append(productNo).append('\'');
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", reqStatus=").append(reqStatus);
        sb.append(", creditSrc='").append(creditSrc).append('\'');
        sb.append(", orgChannel='").append(orgChannel).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

