package com.qunar.fintech.plat.admin.hank.entity;

import java.io.Serializable;

public class QueryTerm implements Serializable {

    private static final long serialVersionUID = 6468465869790890966L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 查询模式 1ID 2TIME
     */
    private Integer queryMode;

    /**
     * 查询组别
     */
    private Integer queryGroup;

    /**
     * 组序
     */
    private Integer groupOrder;

    /**
     * 查询域
     */
    private String queryField;

    /**
     * 域命令
     */
    private String queryCommand;

    /**
     * 命令格式
     */
    private String queryValue;

    /**
     * 步长
     */
    private Integer stepLength;

    /**
     * 配置状态 1有效 2无效
     */
    private Integer status = 1;

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

    public Integer getQueryMode() {
        return queryMode;
    }

    public void setQueryMode(Integer queryMode) {
        this.queryMode = queryMode;
    }

    public String getQueryField() {
        return queryField;
    }

    public void setQueryField(String queryField) {
        this.queryField = queryField.trim();
    }

    public String getQueryCommand() {
        return queryCommand;
    }

    public void setQueryCommand(String queryCommand) {
        this.queryCommand = queryCommand.trim();
    }

    public String getQueryValue() {
        return queryValue;
    }

    public void setQueryValue(String queryValue) {
        this.queryValue = queryValue.trim();
    }

    public Integer getStepLength() {
        return stepLength;
    }

    public void setStepLength(Integer stepLength) {
        this.stepLength = stepLength;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getQueryGroup() {
        return queryGroup;
    }

    public void setQueryGroup(Integer queryGroup) {
        this.queryGroup = queryGroup;
    }

    public Integer getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(Integer groupOrder) {
        this.groupOrder = groupOrder;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QueryTerm{");
        sb.append("id=").append(id);
        sb.append(", taskType='").append(taskType).append('\'');
        sb.append(", queryMode=").append(queryMode);
        sb.append(", queryGroup=").append(queryGroup);
        sb.append(", groupOrder=").append(groupOrder);
        sb.append(", queryField='").append(queryField).append('\'');
        sb.append(", queryCommand='").append(queryCommand).append('\'');
        sb.append(", queryValue='").append(queryValue).append('\'');
        sb.append(", stepLength=").append(stepLength);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}