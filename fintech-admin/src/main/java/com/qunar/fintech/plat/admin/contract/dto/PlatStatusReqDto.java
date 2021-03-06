package com.qunar.fintech.plat.admin.contract.dto;

import com.qunar.fintech.plat.admin.contract.dao.enums.PlatStatusEnum;
import com.qunar.pay.finance.qf.commons.api.model.ToString;

public class PlatStatusReqDto extends ToString{

    /**
     * 金融平台id
     */
    private String customId;

    /**
     * 产品线
     */
    private String productNo;

    /**
     * 渠道方
     */
    private String tppCode;

    /**
     * 渠道
     */
    private String orgChannel;

    /**
     * 开关目标状态
     * @see PlatStatusEnum
     */
    private Integer tarStatus;

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

    public Integer getTarStatus() {
        return tarStatus;
    }

    public void setTarStatus(Integer tarStatus) {
        this.tarStatus = tarStatus;
    }
}
