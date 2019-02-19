package com.qunar.fintech.plat.admin.contract.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * Description: 修改合同支付开关
 * User: rengang.pei
 * Date: 2018-11-05
 * Time: 14:01
 */
public class PaySwitchReqDto extends ToString{

    /**
     * 金融customId
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
     * @see com.qunar.fintech.contract.api.constant.PaySwitchStatus
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
