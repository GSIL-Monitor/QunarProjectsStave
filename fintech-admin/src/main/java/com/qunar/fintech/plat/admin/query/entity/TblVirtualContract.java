package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.g2.utils.persistence.Entity;

/**
 * @author cheng.she
 * @since 2016-02-24
 */
public class TblVirtualContract implements Entity<Long> {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 手机号
     */
    private String userName;

    /**
     * 产品码
     */
    private String productNo;
    /**
     * 身份证号（加密）
     */
    private String identityCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TblVirtualContract{");
        sb.append("id=").append(id);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", productNo='").append(productNo).append('\'');
        sb.append(", identityCode='").append(identityCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
