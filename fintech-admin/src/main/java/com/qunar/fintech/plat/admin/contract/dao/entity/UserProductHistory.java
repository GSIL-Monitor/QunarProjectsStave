package com.qunar.fintech.plat.admin.contract.dao.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;

public class UserProductHistory extends ToString {

    private Long id;

    /**
     * 引起变更的 流水号
     */
    private String changeNo;

    /**
     * 引起变更的来源: 绑定和解绑
     * @see com.qunar.fintech.plat.admin.contract.dao.enums.ChangeSrcEnum
     */
    private String changeSrc;

    /**
     * 平台id
     */
    private String platId;

    /**
     * 客户id
     */
    private String customId;

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
     * 绑定信息
     */
    private String userProductInfo;

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

    public String getChangeNo() {
        return changeNo;
    }

    public void setChangeNo(String changeNo) {
        this.changeNo = changeNo;
    }

    public String getChangeSrc() {
        return changeSrc;
    }

    public void setChangeSrc(String changeSrc) {
        this.changeSrc = changeSrc;
    }

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

    public String getUserProductInfo() {
        return userProductInfo;
    }

    public void setUserProductInfo(String userProductInfo) {
        this.userProductInfo = userProductInfo;
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