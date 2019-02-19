package com.qunar.fintech.plat.admin.marketing.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * Created with Intellij IDEA.
 * Description:
 * User: weiduan
 * Date: 2017-12-14
 */
public class ExportFileDto extends ToString{

    /**
     * 活动code
     */
    private String activityCode;

    /**
     * 查询类型：PAY,REFUND
     */
    private String type;

    /**
     * 渠道类型：QUNAR,CTRIP,QUICKPASS_SH
     */
    private String channel;

    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 终止时间
     */
    private String endTime;

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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
}
