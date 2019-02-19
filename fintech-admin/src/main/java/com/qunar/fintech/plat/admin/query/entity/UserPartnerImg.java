package com.qunar.fintech.plat.admin.query.entity;

import com.google.common.base.MoreObjects;
import com.qunar.pay.finance.qf.commons.api.model.ToString;

public class UserPartnerImg extends ToString{

    private Long id;

    /**
     * 身份证号
     */
    private String idCode;

    /**
     * 动态密钥
     */
    private String secKey;

    /**
     * 证件类型
     */
    private String sourceType;

    /**
     * 存储地址
     */
    private String imgUrl;

    /**
     * 创建时间
     */
    private String createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getSecKey() {
        return secKey;
    }

    public void setSecKey(String secKey) {
        this.secKey = secKey;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}