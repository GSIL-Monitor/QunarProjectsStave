package com.qunar.fintech.plat.admin.contract.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/21
 * @Despcription:
 */
public class QueryChannelContractRecord extends ToString{

    /**
     * 前端展示用：合同类型
     */
    private String contractType = "通道合同";

    /**
     * 金融customId
     */
    private String customId;

    /**
     * 用户platId
     */
    private String platId;

    /**
     * 业务线
     */
    private String orgChannel;

    /**
     *  产品线
     */
    private String productNo;

    /**
     * 用户组
     */
    private String userGroup;

    /**
     * 渠道方
     */
    private String tppCode;

    /**
     * 激活/授信状态
     */
    private Integer procStatus;

    /**
     * 通道合同状态
     */
    private Integer channelStatus;

    /**
     * 总额度
     */
    private BigDecimal totalAmount;

    /**
     * 已用额度
     */
    private BigDecimal usedAmount;

    /**
     * 冻结额度
     */
    private BigDecimal freezeAmount;

    /**
     * 可用额度
     */
    private BigDecimal balanceAmount;

    /**
     * 授信开始时间
     */
    private Date creditStartTime;

    /**
     * 授信结束时间
     */
    private Date creditEndTime;

    /**
     * 授信完成时间
     */
    private Date creditFinishTime;

    /**
     * 激活开始时间
     */
    private Date activateStartTime;

    /**
     * 激活结束时间
     */
    private Date activateEndTime;

    /**
     * 激活完成时间
     */
    private Date activateFinishTime;

    /**
     * 是否是主通道
     */
    private boolean mainChannel;
    /**
     * 合同操作按钮预留表格位
     */
    private String operate;

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

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public Integer getProcStatus() {
        return procStatus;
    }

    public void setProcStatus(Integer procStatus) {
        this.procStatus = procStatus;
    }

    public Integer getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(Integer channelStatus) {
        this.channelStatus = channelStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(BigDecimal usedAmount) {
        this.usedAmount = usedAmount;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Date getCreditStartTime() {
        return creditStartTime;
    }

    public void setCreditStartTime(Date creditStartTime) {
        this.creditStartTime = creditStartTime;
    }

    public Date getCreditEndTime() {
        return creditEndTime;
    }

    public void setCreditEndTime(Date creditEndTime) {
        this.creditEndTime = creditEndTime;
    }

    public Date getCreditFinishTime() {
        return creditFinishTime;
    }

    public void setCreditFinishTime(Date creditFinishTime) {
        this.creditFinishTime = creditFinishTime;
    }

    public Date getActivateStartTime() {
        return activateStartTime;
    }

    public void setActivateStartTime(Date activateStartTime) {
        this.activateStartTime = activateStartTime;
    }

    public Date getActivateEndTime() {
        return activateEndTime;
    }

    public void setActivateEndTime(Date activateEndTime) {
        this.activateEndTime = activateEndTime;
    }

    public Date getActivateFinishTime() {
        return activateFinishTime;
    }

    public void setActivateFinishTime(Date activateFinishTime) {
        this.activateFinishTime = activateFinishTime;
    }

    public boolean isMainChannel() {
        return mainChannel;
    }

    public void setMainChannel(boolean mainChannel) {
        this.mainChannel = mainChannel;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
}
