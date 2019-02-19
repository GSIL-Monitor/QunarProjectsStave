package com.qunar.fintech.plat.admin.query.entity.nemo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;

public class TppUser extends ToString {
    private Long id;

    private String tppOpenid;

    private String tppCode;

    private String customerId;

    private Date createTime;

    private String ext;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTppOpenid() {
        return tppOpenid;
    }

    public void setTppOpenid(String tppOpenid) {
        this.tppOpenid = tppOpenid == null ? null : tppOpenid.trim();
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode == null ? null : tppCode.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }
}