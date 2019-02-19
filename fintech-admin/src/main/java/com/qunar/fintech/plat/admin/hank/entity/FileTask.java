package com.qunar.fintech.plat.admin.hank.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qunar.fintech.util.simple.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class FileTask implements Serializable {

    private static final long serialVersionUID = 7700188995085147286L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 查询模式
     */
//    private Integer queryMode;

    /**
     * 对账日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date accountTime;

    /**
     * 起始id
     */
    private Long startId;

    /**
     * 结束id
     */
    private Long endId;

    /**
     * id总量
     */
//    private Long total;

    /**
     * 时间域
     */
//    private String timeField;

    /**
     * 起始时间
     */
//    private Date startTime;

    /**
     * 结束时间
     */
//    private Date endTime;

    /**
     * 起始时间
     */
//    private String startTimeStr;

    /**
     * 结束时间
     */
//    private String endTimeStr;

    /**
     * 步长
     */
//    private Integer stepLength;

    /**
     * 状态 0未开始 1运行中 2结束
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType.trim();
    }

    public Date getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(Date accountTime) {
        this.accountTime = accountTime;
    }

    public Long getStartId() {
        return startId;
    }

    public void setStartId(Long startId) {
        this.startId = startId;
    }

    public Long getEndId() {
        return endId;
    }

    public void setEndId(Long endId) {
        this.endId = endId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String formatTime(Date date){
        return DateUtils.date2str(date,DateUtils.FORMATTYPE9);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FileTask{");
        sb.append("id=").append(id);
        sb.append(", taskType='").append(taskType).append('\'');
//        sb.append(", queryMode=").append(queryMode);
        sb.append(", accountTime=").append(accountTime);
        sb.append(", startId=").append(startId);
        sb.append(", endId=").append(endId);
//        sb.append(", total=").append(total);
//        sb.append(", timeField='").append(timeField).append('\'');
//        sb.append(", startTime=").append(startTime);
//        sb.append(", endTime=").append(endTime);
//        sb.append(", startTimeStr='").append(startTimeStr).append('\'');
//        sb.append(", endTimeStr='").append(endTimeStr).append('\'');
//        sb.append(", stepLength=").append(stepLength);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}