package com.qunar.fintech.plat.admin.hank.entity;

import java.io.Serializable;

public class MultiTableRelate implements Serializable {

    private static final long serialVersionUID = -3801201533113081061L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 源表
     */
    private String sourceTable;

    /**
     * 目标表
     */
    private String targetTable;

    /**
     * 关联域
     */
    private String multiField;

    /**
     * 查询条件
     */
    private String queryTerms;

    /**
     * 关联关系 1一对一 2一对多
     */
    private Integer matchRelate;

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

    public String getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable.trim();
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable.trim();
    }

    public String getMultiField() {
        return multiField;
    }

    public void setMultiField(String multiField) {
        this.multiField = multiField.trim();
    }

    public String getQueryTerms() {
        return queryTerms;
    }

    public void setQueryTerms(String queryTerms) {
        this.queryTerms = queryTerms.trim();
    }

    public Integer getMatchRelate() {
        return matchRelate;
    }

    public void setMatchRelate(Integer matchRelate) {
        this.matchRelate = matchRelate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MultiTableRelate{");
        sb.append("id=").append(id);
        sb.append(", taskType='").append(taskType).append('\'');
        sb.append(", sourceTable='").append(sourceTable).append('\'');
        sb.append(", targetTable='").append(targetTable).append('\'');
        sb.append(", multiField='").append(multiField).append('\'');
        sb.append(", queryTerms='").append(queryTerms).append('\'');
        sb.append(", matchRelate=").append(matchRelate);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}