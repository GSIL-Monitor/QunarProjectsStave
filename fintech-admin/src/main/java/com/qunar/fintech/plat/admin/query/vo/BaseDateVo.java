package com.qunar.fintech.plat.admin.query.vo;

import com.google.common.base.MoreObjects;

/**
 * @author dw.wang
 * @since 2016-03-09.
 */
public class BaseDateVo {

    private String startTime;

    private String endTime;


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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("startTime", startTime)
                .add("endTime", endTime)
                .toString();
    }
}
