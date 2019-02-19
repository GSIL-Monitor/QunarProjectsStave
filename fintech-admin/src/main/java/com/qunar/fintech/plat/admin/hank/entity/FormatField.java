package com.qunar.fintech.plat.admin.hank.entity;

import java.io.Serializable;

public class FormatField implements Serializable {

    private static final long serialVersionUID = -5449691446664581644L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 文件接收方
     */
    private String receiver;

    /**
     * 域输出序
     */
    private Integer outOrder;

    /**
     * JSON 输出Key
     */
    private String outKey;

    /**
     * 输出项含义
     */
    private String outRemark;

    /**
     * 操作域
     */
    private String operateField;

    /**
     * 操作命令
     */
    private String operateCommand;

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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver.trim();
    }

    public String getOperateField() {
        return operateField;
    }

    public void setOperateField(String operateField) {
        this.operateField = operateField.trim();
    }

    public Integer getOutOrder() {
        return outOrder;
    }

    public void setOutOrder(Integer outOrder) {
        this.outOrder = outOrder;
    }

    public String getOperateCommand() {
        return operateCommand;
    }

    public void setOperateCommand(String operateCommand) {
        this.operateCommand = operateCommand.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOutRemark() {
        return outRemark;
    }

    public void setOutRemark(String outRemark) {
        this.outRemark = outRemark.trim();
    }

    public String getOutKey() {
        return outKey;
    }

    public void setOutKey(String outKey) {
        this.outKey = outKey.trim();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FormatField{");
        sb.append("id=").append(id);
        sb.append(", taskType='").append(taskType).append('\'');
        sb.append(", receiver='").append(receiver).append('\'');
        sb.append(", operateField='").append(operateField).append('\'');
        sb.append(", outOrder=").append(outOrder);
        sb.append(", outKey='").append(outKey).append('\'');
        sb.append(", outRemark='").append(outRemark).append('\'');
        sb.append(", operateCommand='").append(operateCommand).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}