package com.qunar.fintech.plat.admin.hank.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;

/**
 * Created by tongda.sun on 2018/12/14  17:08.
 * Description: 处理对账任务实体类
 */
public class AccountFileLaunch extends ToString {

    /**
     * 主键
     */
    private Long id;

    /**
     * 请求用户
     */
    private String reqUser;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 文件接收方
     */
    private String receiver;

    /**
     * 账务日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date accountDate;
    /**
     * 执行时间
     */
    private Date runTime;

    /**
     * 上次执行时间
     */
    private Date lastRunTime;

    /**
     * 执行次数
     */
    private Integer reqNums;

    /**
     * 执行状态
     */
    private Integer status;

    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReqUser() {
        return reqUser;
    }

    public void setReqUser(String reqUser) {
        this.reqUser = reqUser;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }

    public Date getLastRunTime() {
        return lastRunTime;
    }

    public void setLastRunTime(Date lastRunTime) {
        this.lastRunTime = lastRunTime;
    }

    public Integer getReqNums() {
        return reqNums;
    }

    public void setReqNums(Integer reqNums) {
        this.reqNums = reqNums;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
