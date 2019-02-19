package com.qunar.fintech.plat.admin.contract.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;

public class QueryUserProductChangeRecord extends ToString{

    /**
     * 金融customId
     */
    private String customId;

    /**
     * 金融平台Id
     */
    private String platId;

    /**
     *  产品线
     */
    private String productNo;

    /**
     * 绑定渠道
     */
    private String bindOrgChannel;

    /**
     * 激活渠道
     */
    private String activateOrgChannel;

    /**
     * 支付开关
     */
    private Integer paySwitch;

    /**
     * 处理状态
     */
    private Integer procStatus;

    /**
     * 平台合同状态
     * @see
     */
    private Integer platStatus;

    /**
     * 绑定状态
     */
    private Integer bindStatus;

    /**
     * 绑定来源
     */
    private String bindSrc;

    /**
     * 绑定时间
     */
    private Date bindTime;

    /**
     * 解绑时间
     */
    private Date unBindTime;

    /**
     * 创建时间
     */
    private  Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

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

    public String getBindOrgChannel() {
        return bindOrgChannel;
    }

    public void setBindOrgChannel(String bindOrgChannel) {
        this.bindOrgChannel = bindOrgChannel;
    }

    public String getActivateOrgChannel() {
        return activateOrgChannel;
    }

    public void setActivateOrgChannel(String activateOrgChannel) {
        this.activateOrgChannel = activateOrgChannel;
    }

    public Integer getPaySwitch() {
        return paySwitch;
    }

    public void setPaySwitch(Integer paySwitch) {
        this.paySwitch = paySwitch;
    }

    public Integer getProcStatus() {
        return procStatus;
    }

    public void setProcStatus(Integer procStatus) {
        this.procStatus = procStatus;
    }

    public Integer getPlatStatus() {
        return platStatus;
    }

    public void setPlatStatus(Integer platStatus) {
        this.platStatus = platStatus;
    }

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }

    public String getBindSrc() {
        return bindSrc;
    }

    public void setBindSrc(String bindSrc) {
        this.bindSrc = bindSrc;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public Date getUnBindTime() {
        return unBindTime;
    }

    public void setUnBindTime(Date unBindTime) {
        this.unBindTime = unBindTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
