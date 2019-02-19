package com.qunar.fintech.plat.admin.contract.dto;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/20
 * @Despcription:
 */
public class QueryContractVo extends Page {

    /**
     * 自然人customId
     */
    private String customId;

    /**
     * 金融平台platId
     */
    private String platId;

    /**
     * 产品编码
     *
     * @see com.qunar.pay.finance.qf.commons.api.enums.ProductEnum
     */
    private String productNo;

    /**
     * 渠道编码
     *
     * @see com.qunar.pay.finance.qf.commons.api.enums.OrgChannelEnum
     */
    private String orgChannel;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 身份证号
     */
    private String identityCode;

    /**
     * 贷款提供方
     */
    private String tppCode;

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getPlatId() {
        return platId;
    }

    public void setPlatId(String platId) {
        this.platId = platId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }
}
