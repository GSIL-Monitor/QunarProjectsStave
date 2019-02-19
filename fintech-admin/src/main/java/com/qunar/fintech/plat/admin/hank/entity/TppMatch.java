package com.qunar.fintech.plat.admin.hank.entity;

import java.io.Serializable;

public class TppMatch implements Serializable {

    private static final long serialVersionUID = 5605580508352985977L;

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
     * 组别
     */
    private Integer matchGroup;

    /**
     * 匹配序
     */
    private Integer matchOrder;

    /**
     * 过滤条件
     */
    private String matchKey;

    /**
     * 过滤条件
     */
    private String matchValue;

    /**
     * 过滤命令
     */
    private String matchCommand;

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

    public String getMatchKey() {
        return matchKey;
    }

    public void setMatchKey(String matchKey) {
        this.matchKey = matchKey.trim();
    }

    public String getMatchValue() {
        return matchValue;
    }

    public void setMatchValue(String matchValue) {
        this.matchValue = matchValue.trim();
    }

    public String getMatchCommand() {
        return matchCommand;
    }

    public void setMatchCommand(String matchCommand) {
        this.matchCommand = matchCommand.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMatchOrder() {
        return matchOrder;
    }

    public void setMatchOrder(Integer matchOrder) {
        this.matchOrder = matchOrder;
    }

    public Integer getMatchGroup() {
        return matchGroup;
    }

    public void setMatchGroup(Integer matchGroup) {
        this.matchGroup = matchGroup;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TppMatch{");
        sb.append("id=").append(id);
        sb.append(", taskType='").append(taskType).append('\'');
        sb.append(", receiver='").append(receiver).append('\'');
        sb.append(", matchGroup=").append(matchGroup);
        sb.append(", matchOrder=").append(matchOrder);
        sb.append(", matchKey='").append(matchKey).append('\'');
        sb.append(", matchValue='").append(matchValue).append('\'');
        sb.append(", matchCommand='").append(matchCommand).append('\'');
        sb.append(", status=").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}