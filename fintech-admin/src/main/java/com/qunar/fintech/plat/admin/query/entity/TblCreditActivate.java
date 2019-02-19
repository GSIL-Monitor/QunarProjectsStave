package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.g2.utils.persistence.Entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author shaohua.zeng
 * @since 2017-01-12
 */
// 授信激活表
public class TblCreditActivate implements Entity<Long> {
    // 主键
    private long id;
    // 授信流水
    private String creditNo;
    // 申请流水号
    private String applyNo;
    // 用户编码
    private String userId;
    // 渠道号
    private String orgChannel;
    // 签约银行
    private String tppCode;
    // 姓名
    private String userName;
    // 证件类型 IDENTITYCARD：身份证 PASSPORT：护照 OFFICERCERT：军官证
    private String identityType;
    // 证件号,安全索引
    private String identityCode;
    // 掩码后证件号
    private String mosaicIdentCode;
    // 银行卡号索引
    private String bankCardNo;
    // 卡类型 0 借记卡 1 信用卡 99 未知卡类型
    private Integer cardType;
    // 手机号
    private String mobile;
    // 激活状态 0 初始状态 1.处理中 2.激活成功 3. 激活失败
    private Integer activateStatus;
    // 三期利率，兼容老数据则认为是年利率
    private BigDecimal rate;
    // 贷款利率 0 月利率 1.年利率 2 日利率
    private Integer rateType;
    // 预授信额度
    private BigDecimal activateAmt;
    // 激活生效开始时间
    private Date startEffTime;
    // 激活生效结束时间
    private Date endEffTime;
    // 完成时间
    private Date finishTime;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date updateTime;
    // 状态 0:无需入账,1:处理中,2:成功,3:失败
    private Integer accountingStatus;
    // 错误码
    private String errorCode;
    // 错误信息
    private String errorMsg;
    // 合同号
    private String contractNo;
    // 网关发给服务商流水
    private String servActivateNo;
    // 授信来源
    private String creditSrc;
    // 激活方式
    private String reqChannel;
    // 六期利率
    private BigDecimal termSixRate;
    // 九期利率
    private BigDecimal termNineRate;
    // 十二期利率
    private BigDecimal termTwelveRate;
    // 产品号
    private String productNo;
    // 日利率
    private BigDecimal dayRate;
    // 授信激活类型，0.默认类型，1.主动激活, 2.补激活,3.定时补激活
    private Integer creditType;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }

    public String getCreditNo() {
        return creditNo;
    }

    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getMosaicIdentCode() {
        return mosaicIdentCode;
    }

    public void setMosaicIdentCode(String mosaicIdentCode) {
        this.mosaicIdentCode = mosaicIdentCode;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getActivateStatus() {
        return activateStatus;
    }

    public void setActivateStatus(Integer activateStatus) {
        this.activateStatus = activateStatus;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getRateType() {
        return rateType;
    }

    public void setRateType(Integer rateType) {
        this.rateType = rateType;
    }

    public BigDecimal getActivateAmt() {
        return activateAmt;
    }

    public void setActivateAmt(BigDecimal activateAmt) {
        this.activateAmt = activateAmt;
    }

    public Date getStartEffTime() {
        return startEffTime;
    }

    public void setStartEffTime(Date startEffTime) {
        this.startEffTime = startEffTime;
    }

    public Date getEndEffTime() {
        return endEffTime;
    }

    public void setEndEffTime(Date endEffTime) {
        this.endEffTime = endEffTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
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

    public Integer getAccountingStatus() {
        return accountingStatus;
    }

    public void setAccountingStatus(Integer accountingStatus) {
        this.accountingStatus = accountingStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getServActivateNo() {
        return servActivateNo;
    }

    public void setServActivateNo(String servActivateNo) {
        this.servActivateNo = servActivateNo;
    }

    public String getCreditSrc() {
        return creditSrc;
    }

    public void setCreditSrc(String creditSrc) {
        this.creditSrc = creditSrc;
    }

    public String getReqChannel() {
        return reqChannel;
    }

    public void setReqChannel(String reqChannel) {
        this.reqChannel = reqChannel;
    }

    public BigDecimal getTermSixRate() {
        return termSixRate;
    }

    public void setTermSixRate(BigDecimal termSixRate) {
        this.termSixRate = termSixRate;
    }

    public BigDecimal getTermNineRate() {
        return termNineRate;
    }

    public void setTermNineRate(BigDecimal termNineRate) {
        this.termNineRate = termNineRate;
    }

    public BigDecimal getTermTwelveRate() {
        return termTwelveRate;
    }

    public void setTermTwelveRate(BigDecimal termTwelveRate) {
        this.termTwelveRate = termTwelveRate;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public BigDecimal getDayRate() {
        return dayRate;
    }

    public void setDayRate(BigDecimal dayRate) {
        this.dayRate = dayRate;
    }

    public Integer getCreditType() {
        return creditType;
    }

    public void setCreditType(Integer creditType) {
        this.creditType = creditType;
    }
}
