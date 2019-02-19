package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

public class QueryFaceDto extends ToString {

    /**
     * 请求token
     */
    private String token;

    /**
     * 平台ID
     */
    private String platOpenId;

    /**
     * 身份证号
     */
    private String idCode;

    /**
     * 场景来源
     */
    private String source;

    /**
     * 状态 ==一致性结果
     */
    private String status;

    /**
     * 创建时间
     */
    private String translateTime;

    /**
     * 验证类型
     */
    private String faceType;

    /**
     * 识别返回码
     */
    private String errorCode;

    /**
     * 识别返回内容
     */
    private String errorMsg;

    /**
     * 评分值
     */
    private String confidence;

    private String auditResult;

    private String idName;

    private String checkChannel;

    private String auditTime;

    private String auditRemark;

    private String auditUser;

    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public String getCheckChannel() {
        return checkChannel;
    }

    public void setCheckChannel(String checkChannel) {
        this.checkChannel = checkChannel;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getTranslateTime() {
        return translateTime;
    }

    public void setTranslateTime(String translateTime) {
        this.translateTime = translateTime;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPlatOpenId() {
        return platOpenId;
    }

    public void setPlatOpenId(String platOpenId) {
        this.platOpenId = platOpenId;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getFaceType() {
        return faceType;
    }

    public void setFaceType(String faceType) {
        this.faceType = faceType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }
}
