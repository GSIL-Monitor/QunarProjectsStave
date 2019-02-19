package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * Created by guangtian on 17-4-12.
 */
public class QueryUserIdReqDto extends ToString {

    /* 手机号(必填) */
    private String mobile;

    /* 用户姓名 */
    private String userName;

    /* 通道编号 */
    private String productNo;

    /* 货款提供商 */
    private String tppCode;

    /* 渠道 */
    private String orgChannel;

    /* 身份证号 */
    private String identityCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }
}
