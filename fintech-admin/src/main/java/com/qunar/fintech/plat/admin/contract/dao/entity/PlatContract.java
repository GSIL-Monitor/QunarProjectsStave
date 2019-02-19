package com.qunar.fintech.plat.admin.contract.dao.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/21
 * @Despcription:
 */


public class PlatContract extends ToString {

    private Long id;

    /**
     * 平台合同编号
     */
    private String platContractNo;

    /**
     * 金融 customId
     */
    private String customId;

    /**
     * 产品线
     */
    private String orgChannel;

    /**
     * 产品
     *
     * @see com.qunar.pay.finance.qf.commons.api.enums.ProductEnum
     */
    private String productNo;

    /**
     * 产品组
     *
     * @see com.qunar.pay.finance.qf.commons.api.enums.UserGroupEnum
     */
    private String userGroup;

    /**
     * 平台状态
     * @see com.qunar.fintech.contract.api.constant.PlatStatus
     */
    private int platStatus;

    /**
     * 授信状态
     *
     * @see com.qunar.fintech.contract.api.constant.SignStatus
     */
    private int procStatus;

    /**
     * 支付开关
     * @see com.qunar.fintech.contract.api.constant.PaySwitchStatus
     */
    private int paySwitch;

    /**
     * 自动还款开关
     * @see com.qunar.fintech.contract.api.constant.AutoRepaySwitch
     */
    private int autoRepaySwitch;

    /**
     * 用户固定还款日
     */
    private int fixedDueDate;

    /**
     * 用户外露通道
     */
    private String tppCode;

    /**
     * 姓名
     */
    private String customName;

    /**
     * 身份证类型
     */
    private String identityType;

    /**
     * 身份证号
     */
    private String identityCode;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户额度
     */
    private BigDecimal userAmount;

    /**
     * 最大额度
     */
    private BigDecimal maxAmount;

    /**
     * 利率类型
     */
    private Integer rateType;

    /**
     * 三期利率(六位小数)
     */
    private BigDecimal threeTermRate;

    /**
     * 六期利率(六位小数)
     */
    private BigDecimal sixTermRate;

    /**
     * 九期利率(六位小数)
     */
    private BigDecimal nineTermRate;

    /**
     * 十二期利率(六位小数)
     */
    private BigDecimal twelveTermRate;

    /**
     * 二十四期利率(六位小数)
     */
    private BigDecimal twentyFourTermRate;

    /**
     * 激活完成时间
     */
    private Date finishTime;

    /**
     * 有效期开始时间
     */
    private Date effStartTime;

    /**
     * 有效期结束时间
     */
    private Date effEndTime;

    /**
     * 关闭/开启支付开关合作方
     */
    private String closePaySwitchPartner;

    private int version;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatContractNo() {
        return platContractNo;
    }

    public void setPlatContractNo(String platContractNo) {
        this.platContractNo = platContractNo;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
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

    public int getPlatStatus() {
        return platStatus;
    }

    public void setPlatStatus(int platStatus) {
        this.platStatus = platStatus;
    }

    public int getProcStatus() {
        return procStatus;
    }

    public void setProcStatus(int procStatus) {
        this.procStatus = procStatus;
    }

    public int getPaySwitch() {
        return paySwitch;
    }

    public void setPaySwitch(int paySwitch) {
        this.paySwitch = paySwitch;
    }

    public int getAutoRepaySwitch() {
        return autoRepaySwitch;
    }

    public void setAutoRepaySwitch(int autoRepaySwitch) {
        this.autoRepaySwitch = autoRepaySwitch;
    }

    public int getFixedDueDate() {
        return fixedDueDate;
    }

    public void setFixedDueDate(int fixedDueDate) {
        this.fixedDueDate = fixedDueDate;
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

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
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

    public BigDecimal getUserAmount() {
        return userAmount;
    }

    public void setUserAmount(BigDecimal userAmount) {
        this.userAmount = userAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getRateType() {
        return rateType;
    }

    public void setRateType(Integer rateType) {
        this.rateType = rateType;
    }

    public BigDecimal getThreeTermRate() {
        return threeTermRate;
    }

    public void setThreeTermRate(BigDecimal threeTermRate) {
        this.threeTermRate = threeTermRate;
    }

    public BigDecimal getSixTermRate() {
        return sixTermRate;
    }

    public void setSixTermRate(BigDecimal sixTermRate) {
        this.sixTermRate = sixTermRate;
    }

    public BigDecimal getNineTermRate() {
        return nineTermRate;
    }

    public void setNineTermRate(BigDecimal nineTermRate) {
        this.nineTermRate = nineTermRate;
    }

    public BigDecimal getTwelveTermRate() {
        return twelveTermRate;
    }

    public void setTwelveTermRate(BigDecimal twelveTermRate) {
        this.twelveTermRate = twelveTermRate;
    }

    public BigDecimal getTwentyFourTermRate() {
        return twentyFourTermRate;
    }

    public void setTwentyFourTermRate(BigDecimal twentyFourTermRate) {
        this.twentyFourTermRate = twentyFourTermRate;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getEffStartTime() {
        return effStartTime;
    }

    public void setEffStartTime(Date effStartTime) {
        this.effStartTime = effStartTime;
    }

    public Date getEffEndTime() {
        return effEndTime;
    }

    public void setEffEndTime(Date effEndTime) {
        this.effEndTime = effEndTime;
    }

    public String getClosePaySwitchPartner() {
        return closePaySwitchPartner;
    }

    public void setClosePaySwitchPartner(String closePaySwitchPartner) {
        this.closePaySwitchPartner = closePaySwitchPartner;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
