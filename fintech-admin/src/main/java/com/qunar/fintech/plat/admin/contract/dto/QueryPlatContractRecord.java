package com.qunar.fintech.plat.admin.contract.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/21
 * @Despcription:
 */
public class QueryPlatContractRecord extends ToString{


    /**
     * 前端展示用：合同类型
     */
    private String contractType = "平台合同";

    /**
     * 金融customId
     */
    private String customId;

    /**
     *  产品线
     */
    private String productNo;

    /**
     * 用户组
     */
    private String userGroup;

    /**
     * 激活/授信状态
     */
    private Integer procStatus;

    /**
     * 平台合同状态
     */
    private Integer platStatus;

    /**
     * 渠道方
     */
    private String tppCode;

    /**
     * 用户名
     */
    private String customName;

    /**
     * 身份证号
     */
    private String identityCode;

    /**
     * 手机号
     */
    private String mobile;

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
     * 有效开始时间
     */
    private Date startTime;

    /**
     * 有效结束时间
     */
    private Date endTime;

    /**
     * 支付开关
     */
    private Integer paySwitch;
    /**
     * 自动还款开关
     */
    private Integer autoPaySwitch;

    /**
     * 是否逾期
     */
    private boolean expired;

    /**
     * 是否激活
     */
    private boolean activated;

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

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPaySwitch() {
        return paySwitch;
    }

    public void setPaySwitch(Integer paySwitch) {
        this.paySwitch = paySwitch;
    }

    public Integer getAutoPaySwitch() {
        return autoPaySwitch;
    }

    public void setAutoPaySwitch(Integer autoPaySwitch) {
        this.autoPaySwitch = autoPaySwitch;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
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
