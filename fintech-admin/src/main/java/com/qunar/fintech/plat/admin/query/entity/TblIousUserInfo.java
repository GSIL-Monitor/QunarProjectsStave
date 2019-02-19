package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.fintech.plat.admin.query.enums.SignStatusEnum;
import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述：</b>TblIousUserInfo:用户合同信息表<br>
 *
 * @author baron.jiang
 * @since：2015年02月02日 16时03分45秒 星期一
 */
public class TblIousUserInfo extends ToString {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户编码
     */
    private String userId;
    /**
     * 签约银行
     */
    private String productNo;
    /**
     * 签约银行
     */
    private String tppCode;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 证件类型
     */
    private String identityType;
    /**
     * 证件号
     */
    private String identityCode;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 状态 0:初始状态 1： 申请成功 2：签约成功，9 银行拒绝申请
     */
    private SignStatusEnum status;
    /**
     * 开始时间
     */
    private Date iousBeginTime;
    /**
     * 结束时间
     */
    private Date iousEndTime;
    /**
     * 合同编号
     */
    private String contractNo;
    /**
     * 合同授信额度
     */
    private BigDecimal creditAmount;
    /**
     * 生成时间
     */
    private Date createTime;
    /**
     * 申请成功时间
     */
    private Date applyTime;
    /**
     * 签约成功时间
     */
    private Date signTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 平台手机号
     */
    private String platMobile;
    /**
     * 平台证件类型
     */
    private String platIdentityType;
    /**
     * 平台证件号
     */
    private String platIdentityCode;
    /**
     * 平台姓名
     */
    private String platUserName;

    /**
     * 信任付签约合同号
     */
    private String iousSignAgreeNo;

    /**
     * 贷款年利率
     */
    private BigDecimal annualRate;

    /**
     * 6期利率
     */
    private BigDecimal rate6;

    /**
     * 9期利率
     */
    private BigDecimal rate9;

    /**
     * 12期利率
     */
    private BigDecimal rate12;

    /**
     * 是否为主通道
     */
    private Integer useStatus;

    /**
     * 是否绑定自动还款
     */
    private String autoRepaySignStatus;

    /**
     *  激活来源
     */
    private String reqChannel;

    private String channel;

    /**
     * 支付开关 0:关闭；1:开启
     */
    private Integer paySwitch;

    /**
     * 合同状态 0:不可用；1:可用
     */
    private Integer contractStatus;

    /**
     * 欠款金额
     */
    private BigDecimal debtAmountAll;

    /**
     * 欠款笔数
     */
    private Integer debtCount;

    /**
     * 逾期金额
     */
    private BigDecimal currentOverdueAmount;

    /**
     * 当前逾期订单数量
     */
    private Integer currentOverdueCount;
    /**
     * 修改类型
     */
    private Integer changeType;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPlatMobile() {
        return platMobile;
    }

    public void setPlatMobile(String platMobile) {
        this.platMobile = platMobile;
    }

    public String getPlatIdentityType() {
        return platIdentityType;
    }

    public void setPlatIdentityType(String platIdentityType) {
        this.platIdentityType = platIdentityType;
    }

    public String getPlatIdentityCode() {
        return platIdentityCode;
    }

    public void setPlatIdentityCode(String platIdentityCode) {
        this.platIdentityCode = platIdentityCode;
    }

    public String getPlatUserName() {
        return platUserName;
    }

    public void setPlatUserName(String platUserName) {
        this.platUserName = platUserName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTppCode() {
        return this.tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdentityType() {
        return this.identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityCode() {
        return this.identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SignStatusEnum getStatus() {
        return this.status;
    }

    public void setStatus(SignStatusEnum status) {
        this.status = status;
    }

    public Date getIousBeginTime() {
        return this.iousBeginTime;
    }

    public void setIousBeginTime(Date iousBeginTime) {
        this.iousBeginTime = iousBeginTime;
    }

    public Date getIousEndTime() {
        return this.iousEndTime;
    }

    public void setIousEndTime(Date iousEndTime) {
        this.iousEndTime = iousEndTime;
    }

    public String getContractNo() {
        return this.contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public BigDecimal getCreditAmount() {
        return this.creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getIousSignAgreeNo() {
        return iousSignAgreeNo;
    }

    public void setIousSignAgreeNo(String iousSignAgreeNo) {
        this.iousSignAgreeNo = iousSignAgreeNo;
    }

    public BigDecimal getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(BigDecimal annualRate) {
        this.annualRate = annualRate;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public String getAutoRepaySignStatus() {
        return autoRepaySignStatus;
    }

    public void setAutoRepaySignStatus(String autoRepaySignStatus) {
        this.autoRepaySignStatus = autoRepaySignStatus;
    }

    public String getReqChannel() {
        return reqChannel;
    }

    public void setReqChannel(String reqChannel) {
        this.reqChannel = reqChannel;
    }

    public Integer getPaySwitch() {
        return paySwitch;
    }

    public void setPaySwitch(Integer paySwitch) {
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

    public Integer getDebtCount() {
        return debtCount;
    }

    public void setDebtCount(Integer debtCount) {
        this.debtCount = debtCount;
    }

    public BigDecimal getCurrentOverdueAmount() {
        return currentOverdueAmount;
    }

    public void setCurrentOverdueAmount(BigDecimal currentOverdueAmount) {
        this.currentOverdueAmount = currentOverdueAmount;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }
}
