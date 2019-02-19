package com.qunar.fintech.plat.admin.contract.dao.entity;

import com.qunar.fintech.plat.admin.contract.dao.enums.BindStatusEnum;
import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;

public class UserProduct extends ToString {

    private Long id;

    /**
     * 客户id
     */
    private String customId;

    /**
     * 平台id
     */
    private String platId;

    /**
     * 产品编码
     * @see com.qunar.pay.finance.qf.commons.api.enums.ProductEnum
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
     * 备注
     */
    private String remark;

    /**
     * 绑定状态
     */
    private BindStatusEnum bindStatus;

    /**
     * 绑定时间
     */
    private Date bindTime;

    /**
     * 绑定来源
     * @see com.qunar.fintech.contract.api.enums.BindSrcEnum
     */
    private String bindSrc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BindStatusEnum getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(BindStatusEnum bindStatus) {
        this.bindStatus = bindStatus;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getBindSrc() {
        return bindSrc;
    }

    public void setBindSrc(String bindSrc) {
        this.bindSrc = bindSrc;
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