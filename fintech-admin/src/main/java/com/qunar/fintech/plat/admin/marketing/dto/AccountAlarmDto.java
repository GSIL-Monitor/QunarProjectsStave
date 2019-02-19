package com.qunar.fintech.plat.admin.marketing.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * @author qun.shi
 * @since 2019-01-09 10:28 PM
 */
public class AccountAlarmDto extends ToString {
    private static final long serialVersionUID = -5900524790331474741L;

    /**
     * 营销子账户：现金户名称 CIOUS2018Sale99
     */
    private String customerNo;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * qtalk ids :xx, xxx, xx
     */
    private String receiver;

    /**
     * 报警通知方式：AccountAlertModeEnum
     */
    private String alertMode;

    /**
     * email:  格式 xxx@163.com,YYY@qq.com
     */
    private String extEmailReceiver;

    /**
     * 外部手机号：18511112222,13301234567
     */
    private String extMobileReceiver;

    /**
     * 报警时间间隔：单位分钟
     */
    private String notifyInterval;

    /**
     * 报警时间的起始 05:00
     */
    private String startTime;

    /**
     * 报警时间的结束 22:00
     */
    private String endTime;

    /**
     * 报警模式 大于 MAX 还是 小于 MIN
     */
    private String model;

    /**
     * 报警金额阈值
     */
    private String amount;

    /**
     * 说明
     */
    private String remark;

    /**
     * 报警关闭：UNUSED，报警开启：USED
     */
    private String useStatus;

    /**
     * 业务状态
     */
    private String status;

    /**
     * 业务错误错误代码
     */
    private String errCode;

    /**
     * 业务错误消息
     */
    private String errMsg;

    /**
     * 签名信息
     */
    private String HMAC;

    private Integer operType;

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public AccountAlarmDto() {
    }

    public String getHMAC() {
        return HMAC;
    }

    public void setHMAC(String HMAC) {
        this.HMAC = HMAC;
    }

    public String getAlertMode() {
        return alertMode;
    }

    public void setAlertMode(String alertMode) {
        this.alertMode = alertMode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getNotifyInterval() {
        return notifyInterval;
    }

    public void setNotifyInterval(String notifyInterval) {
        this.notifyInterval = notifyInterval;
    }

    public String getExtMobileReceiver() {
        return extMobileReceiver;
    }

    public void setExtMobileReceiver(String extMobileReceiver) {
        this.extMobileReceiver = extMobileReceiver;
    }

    public String getExtEmailReceiver() {
        return extEmailReceiver;
    }

    public void setExtEmailReceiver(String extEmailReceiver) {
        this.extEmailReceiver = extEmailReceiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }
}
