package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.fintech.plat.admin.query.enums.PaySwitchEnum;
import com.qunar.pay.finance.qf.commons.api.model.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 信任付账户查询单条记录
 *
 * Created by baron.jiang on 2015/2/4.
 */
public class QueryAccountRecord extends ToString{

    /* 用户ID */
    private String userId;

    /* 请求ID */
    private String reqUserId;

    /* 手机号 */
    private String mobile;

    /* 姓名 */
    private String userName;

    /* 身份证号 */
    private String identity;

    /* 业务来源 */
    private String orgChannel;

    /* 产品线 */
    private String productNo;

    /* 贷款提供方 */
    private String tppCode;

    /* 合同开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date iousBeginTime;

    /* 合同截止时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date iousEndTime;

    /* 合同状态 */
    private String status;

    /* 总授信额度 */
    private BigDecimal creditAmount;

    /* 客户端展示额度 */
    private BigDecimal mainAmount;

    /* 签约时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date signTime;

    /* 贷款利率 */
    private BigDecimal annualRate;

    /* 6期利率 */
    private BigDecimal rate6;

    /* 9期利率 */
    private BigDecimal rate9;

    /* 12期利率 */
    private BigDecimal rate12;

    /* 更新时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /* 是否为主通道 */
    private Integer useStatus;

    /* 是否绑定自动还款 */
    private String autoRepaySignStatus;

    /* 激活来源 */
    private String reqChannel;

    private String channel;

    /* 已用额度 */
    private BigDecimal usedAmount;

    /* 有效期 */
    private Integer validDay;

    /**
     * 支付开关 0:关闭；1:开启
     */
    private PaySwitchEnum paySwitch;

    /**
     * 欠款总金额
     */
    private BigDecimal debtAmountAll;

    /**
     * 逾期总金额
     */
    private BigDecimal overDueAmountAll;

    /**
     * 当前欠款订单数量
     */
    private Integer currentDebtCount;

    /**
     * 当前逾期订单数量
     */
    private Integer currentOverdueCount;

    /**
     * 授信方式
     */
    private String creditType;

    /**
     * 授信状态
     */
    private Integer reqStatus;

    /**
     * 合同状态
     */
    private Integer contractStatus;

    private Integer total;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public Date getIousBeginTime() {
        return iousBeginTime;
    }

    public void setIousBeginTime(Date iousBeginTime) {
        this.iousBeginTime = iousBeginTime;
    }

    public Date getIousEndTime() {
        return iousEndTime;
    }

    public void setIousEndTime(Date iousEndTime) {
        this.iousEndTime = iousEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public BigDecimal getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(BigDecimal annualRate) {
        this.annualRate = annualRate;
    }

    public String getReqChannel() {
        return reqChannel;
    }

    public void setReqChannel(String reqChannel) {
        this.reqChannel = reqChannel;
    }

    public String getAutoRepaySignStatus() {
        return autoRepaySignStatus;
    }

    public void setAutoRepaySignStatus(String autoRepaySignStatus) {
        this.autoRepaySignStatus = autoRepaySignStatus;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public PaySwitchEnum getPaySwitch() {
        return paySwitch;
    }

    public void setPaySwitch(PaySwitchEnum paySwitch) {
        this.paySwitch = paySwitch;
    }

    public BigDecimal getDebtAmountAll() {
        return debtAmountAll;
    }

    public void setDebtAmountAll(BigDecimal debtAmountAll) {
        this.debtAmountAll = debtAmountAll;
    }

    public Integer getCurrentOverdueCount() {
        return currentOverdueCount;
    }

    public void setCurrentOverdueCount(Integer currentOverdueCount) {
        this.currentOverdueCount = currentOverdueCount;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getReqUserId() {
        return reqUserId;
    }

    public void setReqUserId(String reqUserId) {
        this.reqUserId = reqUserId;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public BigDecimal getMainAmount() {
        return mainAmount;
    }

    public void setMainAmount(BigDecimal mainAmount) {
        this.mainAmount = mainAmount;
    }

    public BigDecimal getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(BigDecimal usedAmount) {
        this.usedAmount = usedAmount;
    }

    public Integer getValidDay() {
        return validDay;
    }

    public void setValidDay(Integer validDay) {
        this.validDay = validDay;
    }

    public BigDecimal getRate6() {
        return rate6;
    }

    public void setRate6(BigDecimal rate6) {
        this.rate6 = rate6;
    }

    public BigDecimal getRate9() {
        return rate9;
    }

    public void setRate9(BigDecimal rate9) {
        this.rate9 = rate9;
    }

    public BigDecimal getRate12() {
        return rate12;
    }

    public void setRate12(BigDecimal rate12) {
        this.rate12 = rate12;
    }

    public BigDecimal getOverDueAmountAll() {
        return overDueAmountAll;
    }

    public void setOverDueAmountAll(BigDecimal overDueAmountAll) {
        this.overDueAmountAll = overDueAmountAll;
    }

    public Integer getCurrentDebtCount() {
        return currentDebtCount;
    }

    public void setCurrentDebtCount(Integer currentDebtCount) {
        this.currentDebtCount = currentDebtCount;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }
}
