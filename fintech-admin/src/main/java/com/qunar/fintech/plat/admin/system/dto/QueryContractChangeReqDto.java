package com.qunar.fintech.plat.admin.system.dto;

import com.qunar.fintech.plat.admin.contract.dto.Page;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-04
 * Time: 12:51
 */
public class QueryContractChangeReqDto extends Page {

    /**
     * 流水号
     */
    private String reqNo;

    /**
     * 客户id
     */
    private String customId;

    /**
     * 产品
     *
     * @see com.qunar.pay.finance.qf.commons.api.enums.ProductEnum
     */
    private String productNo;

    /**
     * 邮箱
     */
    private String email;

    private String startTime;

    private String endTime;

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
