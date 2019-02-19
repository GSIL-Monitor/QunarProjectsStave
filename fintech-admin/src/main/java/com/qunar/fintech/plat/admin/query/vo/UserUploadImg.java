package com.qunar.fintech.plat.admin.query.vo;


import com.qunar.pay.finance.qf.commons.api.model.ToString;

public class UserUploadImg extends ToString {
    /**
     * 身份证正反面类型
     */
    private String type;

    /**
     * 存储地址
     */
    private String imgUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}