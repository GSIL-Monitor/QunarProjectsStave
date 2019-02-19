package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.fintech.plat.admin.query.utils.HiddenUtil;

import java.io.Serializable;

/**
 * Created by bob.li on 2015/9/17.
 */
public class OverDueOrderAuditReq implements Serializable{
    private static final long serialVersionUID = 3946579421788278959L;

    /* 业务订单号 */
    private String busiOrderNo;

    /* 订单日期开始时间 */
    private String startTime;

    /* 订单日期截止时间 */
    private String endTime;

    /* 审核状态 */
    private Integer auditStatus;

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

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
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

    @Override
    public String toString() {
        return "OverDueOrderAuditReq{" +
                "busiOrderNo='" + busiOrderNo + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", auditStatus=" + auditStatus +
                ", mobile='" + HiddenUtil.hiddenMobile(mobile) + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
