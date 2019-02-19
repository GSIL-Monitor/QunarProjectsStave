package com.qunar.fintech.plat.admin.query.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author cheng.she
 * @since 2016-02-23
 */
public class OverDueWithholdReq implements Serializable {

    private static final long serialVersionUID = -5951921498015580066L;

    /**
     * 用户uid
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;


    /**
     * 贷款提供商
     */
    private String productNo;

    /**
     * 贷款提供商
     */
    private String tppCode;

    /**
     * 用户最大逾期天数
     */
    private String minOverDueNum;

    /**
     * 用户最大逾期天数
     */
    private String overDueNum;

    /**
     * 用户最大逾期日期字符串
     */
    private String maxOverDueDateStr;

    /**
     * 用户最大逾期日期
     */
    private Date userMinOverDueDate;

    /**
     * 用户最大逾期日期
     */
    private Date userMaxOverDueDate;

    /**
     * 借据最大逾期日期
     */
    private Date minOverDueDate;

    /**
     * 借据最大逾期日期
     */
    private Date maxOverDueDate;

    private List<String> userIdList;
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

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getOverDueNum() {
        return overDueNum;
    }

    public void setOverDueNum(String overDueNum) {
        this.overDueNum = overDueNum;
    }

    public Date getMaxOverDueDate() {
        return maxOverDueDate;
    }

    public void setMaxOverDueDate(Date maxOverDueDate) {
        this.maxOverDueDate = maxOverDueDate;
    }

    public Date getUserMaxOverDueDate() {
        return userMaxOverDueDate;
    }

    public void setUserMaxOverDueDate(Date userMaxOverDueDate) {
        this.userMaxOverDueDate = userMaxOverDueDate;
    }

    public String getMaxOverDueDateStr() {
        return maxOverDueDateStr;
    }

    public void setMaxOverDueDateStr(String maxOverDueDateStr) {
        this.maxOverDueDateStr = maxOverDueDateStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getMinOverDueNum() {
        return minOverDueNum;
    }

    public void setMinOverDueNum(String minOverDueNum) {
        this.minOverDueNum = minOverDueNum;
    }

    public Date getUserMinOverDueDate() {
        return userMinOverDueDate;
    }

    public void setUserMinOverDueDate(Date userMinOverDueDate) {
        this.userMinOverDueDate = userMinOverDueDate;
    }

    public Date getMinOverDueDate() {
        return minOverDueDate;
    }

    public void setMinOverDueDate(Date minOverDueDate) {
        this.minOverDueDate = minOverDueDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OverDueWithholdReq{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", productNo='").append(productNo).append('\'');
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", minOverDueNum='").append(minOverDueNum).append('\'');
        sb.append(", overDueNum='").append(overDueNum).append('\'');
        sb.append(", maxOverDueDateStr='").append(maxOverDueDateStr).append('\'');
        sb.append(", userMinOverDueDate=").append(userMinOverDueDate);
        sb.append(", userMaxOverDueDate=").append(userMaxOverDueDate);
        sb.append(", maxOverDueDate=").append(maxOverDueDate);
        sb.append(", userIdList=").append(userIdList);
        sb.append(", orgChannel='").append(orgChannel).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
