package com.qunar.fintech.plat.admin.hank.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TppConfig implements Serializable {

    private static final long serialVersionUID = -6266487778218891696L;

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
     * 扩展表
     */
    private String extendTable;

    /**
     * 本地路径
     */
    private String localPath;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件名日期格式
     */
    private String fileDateFormat;

    /**
     * 文件头信息
     */
    private String fileHeader;

    /**
     * 文件尾信息
     */
    private String fileTail;

    /**
     * 行头信息
     */
    private String lineHeader;

    /**
     * 域分隔符
     */
    private String splitField;

    /**
     * 空值占位符
     */
    private String nullField;

    /**
     * 加密秘钥
     */
    private String secretKey;

    /**
     * 配置状态 1有效 2无效
     */
    private Integer status = 1;

    public String getFileName(Date accountTime) {
        return fileName.replace(fileDateFormat, new SimpleDateFormat(fileDateFormat).format(accountTime));
    }

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

    public String getExtendTable() {
        return extendTable;
    }

    public void setExtendTable(String extendTable) {
        this.extendTable = extendTable.trim();
    }

    public String getNullField() {
        return nullField;
    }

    public void setNullField(String nullField) {
        this.nullField = nullField.trim();
    }

    public String getSplitField() {
        return splitField;
    }

    public void setSplitField(String splitField) {
        this.splitField = splitField.trim();
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath.trim();
    }

    public String getFileHeader() {
        return fileHeader;
    }

    public void setFileHeader(String fileHeader) {
        this.fileHeader = fileHeader.trim();
    }

    public String getFileTail() {
        return fileTail;
    }

    public void setFileTail(String fileTail) {
        this.fileTail = fileTail.trim();
    }

    public String getLineHeader() {
        return lineHeader;
    }

    public void setLineHeader(String lineHeader) {
        this.lineHeader = lineHeader.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName.trim();
    }

    public String getFileDateFormat() {
        return fileDateFormat;
    }

    public void setFileDateFormat(String fileDateFormat) {
        this.fileDateFormat = fileDateFormat.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey.trim();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TppConfig{");
        sb.append("id=").append(id);
        sb.append(", taskType='").append(taskType).append('\'');
        sb.append(", receiver='").append(receiver).append('\'');
        sb.append(", extendTable='").append(extendTable).append('\'');
        sb.append(", localPath='").append(localPath).append('\'');
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", fileDateFormat='").append(fileDateFormat).append('\'');
        sb.append(", fileHeader='").append(fileHeader).append('\'');
        sb.append(", fileTail='").append(fileTail).append('\'');
        sb.append(", lineHeader='").append(lineHeader).append('\'');
        sb.append(", splitField='").append(splitField).append('\'');
        sb.append(", nullField='").append(nullField).append('\'');
        sb.append(", secretKey='").append(secretKey).append('\'');
        sb.append(", status=").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}