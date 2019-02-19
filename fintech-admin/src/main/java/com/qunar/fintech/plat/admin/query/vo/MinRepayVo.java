package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xi.cheng on 2018/2/5.
 */
public class MinRepayVo extends ToString{


    private String serialNo;

    /**
     * CommonStatus
     */
    private Integer processStatus;


    private Date createTime;

    private String userName;

    private String userId;
    private String mobile;


    /* 总转债金额 */
    private BigDecimal totalTransAmount;

    private String newTppcode;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public BigDecimal getTotalTransAmount() {
        return totalTransAmount;
    }

    public void setTotalTransAmount(BigDecimal totalTransAmount) {
        this.totalTransAmount = totalTransAmount;
    }

    public String getNewTppcode() {
        return newTppcode;
    }

    public void setNewTppcode(String newTppcode) {
        this.newTppcode = newTppcode;
    }

}
