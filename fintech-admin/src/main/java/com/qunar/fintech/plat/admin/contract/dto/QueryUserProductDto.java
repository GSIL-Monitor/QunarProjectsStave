package com.qunar.fintech.plat.admin.contract.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-10-30
 * Time: 20:14
 */
public class QueryUserProductDto extends Page{

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

    public String getPlatId() {
        return platId;
    }

    public void setPlatId(String platId) {
        this.platId = platId;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
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

}
