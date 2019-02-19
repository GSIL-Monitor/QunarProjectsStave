package com.qunar.fintech.plat.admin.query.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bob.li on 2015/12/22.
 */
public class QueryPreCreditResp {
    /**
     * 授信流水号
     */
    private String creditNo;

    /**
     * 征信查询流水号
     */
    private String qcreditNo;

    /**
     * 申请网关流水号
     */
    private String applyNo;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 贷款提供商编码
     */
    private String productNo;
    /**
     * 贷款提供商编码
     */
    private String tppCode;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 授信请求状态
     */
    private Integer reqStatus;

    /**
     * 利率
     */
    private BigDecimal rate;

    /**
     * 利率类型(0 月利率 1.年利率)
     */
    private Integer rateType;

    /**
     * 授信额度
     */
    private BigDecimal creditAmt;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 授信来源
     */
    private String creditSrc;

    /**
     * 实际申请流水号
     */
    private String actApplyNo;

    /**
     * 后决策评分
     */
    private BigDecimal scoreAfterJudge;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    public String getCreditNo() {
        return creditNo;
    }

    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo;
    }

    public String getQcreditNo() {
        return qcreditNo;
    }

    public void setQcreditNo(String qcreditNo) {
        this.qcreditNo = qcreditNo;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
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

    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = creditAmt;
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

    public String getCreditSrc() {
        return creditSrc;
    }

    public void setCreditSrc(String creditSrc) {
        this.creditSrc = creditSrc;
    }

    public String getActApplyNo() {
        return actApplyNo;
    }

    public void setActApplyNo(String actApplyNo) {
        this.actApplyNo = actApplyNo;
    }

    public BigDecimal getScoreAfterJudge() {
        return scoreAfterJudge;
    }

    public void setScoreAfterJudge(BigDecimal scoreAfterJudge) {
        this.scoreAfterJudge = scoreAfterJudge;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QueryPreCreditResp{");
        sb.append("creditNo='").append(creditNo).append('\'');
        sb.append(", qcreditNo='").append(qcreditNo).append('\'');
        sb.append(", applyNo='").append(applyNo).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", productNo='").append(productNo).append('\'');
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", reqStatus=").append(reqStatus);
        sb.append(", rate=").append(rate);
        sb.append(", rateType=").append(rateType);
        sb.append(", creditAmt=").append(creditAmt);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", creditSrc='").append(creditSrc).append('\'');
        sb.append(", actApplyNo='").append(actApplyNo).append('\'');
        sb.append(", scoreAfterJudge=").append(scoreAfterJudge);
        sb.append(", errorCode='").append(errorCode).append('\'');
        sb.append(", errorMsg='").append(errorMsg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
