package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * 查询id相应结果
 */
public class QueryIdDto extends ToString {

    private String mobile;

    private String userId;

    private String openId;

    private String platOpenId;

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPlatOpenId() {
        return platOpenId;
    }

    public void setPlatOpenId(String platOpenId) {
        this.platOpenId = platOpenId;
    }

}
