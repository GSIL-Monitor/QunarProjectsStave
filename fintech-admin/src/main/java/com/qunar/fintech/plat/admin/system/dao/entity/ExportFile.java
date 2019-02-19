package com.qunar.fintech.plat.admin.system.dao.entity;

import java.util.Date;

public class ExportFile {
    private Long id;

    private Long userId;

    private String fileSeq;

    private Integer exportTaskType;

    private String exportTaskDesc;

    private String url;

    private String attachmentTitle;

    private String extend;

    private Integer exportStatus;

    private String exportStatusDesc;

    private Date startTime;

    private Date endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFileSeq() {
        return fileSeq;
    }

    public void setFileSeq(String fileSeq) {
        this.fileSeq = fileSeq == null ? null : fileSeq.trim();
    }

    public Integer getExportTaskType() {
        return exportTaskType;
    }

    public void setExportTaskType(Integer exportTaskType) {
        this.exportTaskType = exportTaskType;
    }

    public String getExportTaskDesc() {
        return exportTaskDesc;
    }

    public void setExportTaskDesc(String exportTaskDesc) {
        this.exportTaskDesc = exportTaskDesc == null ? null : exportTaskDesc.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getAttachmentTitle() {
        return attachmentTitle;
    }

    public void setAttachmentTitle(String attachmentTitle) {
        this.attachmentTitle = attachmentTitle == null ? null : attachmentTitle.trim();
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend == null ? null : extend.trim();
    }

    public Integer getExportStatus() {
        return exportStatus;
    }

    public void setExportStatus(Integer exportStatus) {
        this.exportStatus = exportStatus;
    }

    public String getExportStatusDesc() {
        return exportStatusDesc;
    }

    public void setExportStatusDesc(String exportStatusDesc) {
        this.exportStatusDesc = exportStatusDesc == null ? null : exportStatusDesc.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}