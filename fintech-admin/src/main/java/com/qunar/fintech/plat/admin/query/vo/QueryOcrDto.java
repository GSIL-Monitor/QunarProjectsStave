package com.qunar.fintech.plat.admin.query.vo;


import com.qunar.pay.finance.qf.commons.api.model.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created with Intellij IDEA.
 * Description:
 * User: zengjing
 * Date: 2018-10-16
 */
public class QueryOcrDto extends ToString {

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
     * 业务线
     */
    private String source;

    /**
     * 状态
     */
    private String status;

    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 状态变更原因
     */
    private String errorMsg;


    /**
     * 上传时间
     */
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date updateTime;

    public String getToken() {
        return token;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
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

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

