package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

public class UserLivingImg extends ToString{

    private Long id;

    private String token;

    /**
     * 平台id
     */
    private String platOpenId;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 动作
     */
    private String action;

    /**
     * 动态密钥
     */
    private String secKey;

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSecKey() {
        return secKey;
    }

    public void setSecKey(String secKey) {
        this.secKey = secKey;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}