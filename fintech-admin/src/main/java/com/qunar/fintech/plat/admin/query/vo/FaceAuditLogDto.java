package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * 人工审核部分
 * Created by zengjing on 18-10-30.
 */
public class FaceAuditLogDto extends ToString {

    private String token;

    private String auditResult;

    private String auditTime;

    private String auditRemark;

    private String auditUser;

    private String base64IdPositiveImg;

    private String base64IdNegativeImg;

    private String base64HoldIdImg;

    private String holdIdImgName;

    private String idNegativeImgName;

    private String idPositiveImgName;

    private String height;

    private String width;

    private String x;

    private String y;

    private String x2;

    private String idCode;

    private String y2;

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getX2() {
        return x2;
    }

    public void setX2(String x2) {
        this.x2 = x2;
    }

    public String getY2() {
        return y2;
    }

    public void setY2(String y2) {
        this.y2 = y2;
    }

    public String getBase64IdNegativeImg() {
        return base64IdNegativeImg;
    }

    public void setBase64IdNegativeImg(String base64IdNegativeImg) {
        this.base64IdNegativeImg = base64IdNegativeImg;
    }

    public String getBase64HoldIdImg() {
        return base64HoldIdImg;
    }

    public void setBase64HoldIdImg(String base64HoldIdImg) {
        this.base64HoldIdImg = base64HoldIdImg;
    }

    public String getHoldIdImgName() {
        return holdIdImgName;
    }

    public void setHoldIdImgName(String holdIdImgName) {
        this.holdIdImgName = holdIdImgName;
    }

    public String getIdNegativeImgName() {
        return idNegativeImgName;
    }

    public void setIdNegativeImgName(String idNegativeImgName) {
        this.idNegativeImgName = idNegativeImgName;
    }

    public String getIdPositiveImgName() {
        return idPositiveImgName;
    }

    public void setIdPositiveImgName(String idPositiveImgName) {
        this.idPositiveImgName = idPositiveImgName;
    }

    public String getBase64IdPositiveImg() {
        return base64IdPositiveImg;
    }

    public void setBase64IdPositiveImg(String base64IdPositiveImg) {
        this.base64IdPositiveImg = base64IdPositiveImg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
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
}
