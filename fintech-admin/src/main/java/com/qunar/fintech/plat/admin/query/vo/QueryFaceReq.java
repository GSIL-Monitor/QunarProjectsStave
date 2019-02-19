package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 人工审核查询请求
 * Created by zengjing on 18-10-17
 */
public class QueryFaceReq extends ToString {
    /**
     * 业务线
     */
    private String source;

    /**
     * 一致性结果
     */
    private String status;

    /**
     * 人脸识别方式
     */
    private String faceType;

    /**
     * 请求号
     */
    private String token;

    /**
     * 用户id
     */
    private String platOpenId;

    /**
     * 身份证号
     */
    private String idCode;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String pageSize;

    private String pageIndex;

    private String checkChannel;

    public String getCheckChannel() {
        return checkChannel;
    }

    public void setCheckChannel(String checkChannel) {
        this.checkChannel = checkChannel;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
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


