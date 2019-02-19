package com.qunar.fintech.plat.admin.hank.entity;


import java.io.Serializable;

public class TaskConfig implements Serializable {

    private static final long serialVersionUID = 8031140532787353821L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 基础表
     */
    private String baseTable;

    /**
     * 查询模式
     */
    private Integer queryMode;

    /**
     * 配置状态 1有效 2无效
     */
    private Integer status = 1;

    /**
     * 任务描述
     */
    private String taskDesc;

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

    public String getBaseTable() {
        return baseTable;
    }

    public void setBaseTable(String baseTable) {
        this.baseTable = baseTable.trim();
    }

    public Integer getQueryMode() {
        return queryMode;
    }

    public void setQueryMode(Integer queryMode) {
        this.queryMode = queryMode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTaskDesc() {
        return taskDesc.trim();
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TaskConfig{");
        sb.append("id=").append(id);
        sb.append(", taskType='").append(taskType).append('\'');
        sb.append(", baseTable='").append(baseTable).append('\'');
        sb.append(", queryMode=").append(queryMode);
        sb.append(", status=").append(status).append('\'');
        sb.append(", taskDesc=").append(taskDesc);
        sb.append('}');
        return sb.toString();
    }
}