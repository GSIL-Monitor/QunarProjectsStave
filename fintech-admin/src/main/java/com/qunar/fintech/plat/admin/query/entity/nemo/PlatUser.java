package com.qunar.fintech.plat.admin.query.entity.nemo;

import java.util.Date;

public class PlatUser {
    private Long id;

    private String platOpenid;

    private String originUserid;

    private String orgChannel;

    private String realSource;

    private String customerId;

    private Byte accType;

    private Date createTime;

    private Date updateTime;

    private String ext;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatOpenid() {
        return platOpenid;
    }

    public void setPlatOpenid(String platOpenid) {
        this.platOpenid = platOpenid == null ? null : platOpenid.trim();
    }

    public String getOriginUserid() {
        return originUserid;
    }

    public void setOriginUserid(String originUserid) {
        this.originUserid = originUserid == null ? null : originUserid.trim();
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel == null ? null : orgChannel.trim();
    }

    public String getRealSource() {
        return realSource;
    }

    public void setRealSource(String realSource) {
        this.realSource = realSource == null ? null : realSource.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public Byte getAccType() {
        return accType;
    }

    public void setAccType(Byte accType) {
        this.accType = accType;
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

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }
}