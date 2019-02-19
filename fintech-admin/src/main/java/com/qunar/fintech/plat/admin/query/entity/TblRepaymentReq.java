package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.g2.utils.persistence.Entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bob.li on 2016/5/26.
 */
public class TblRepaymentReq implements Entity<Long> {
    /**
     *主键
     */
    private Long id;
    /**
     *还款计划总表ID
     */
    private Long scheduleId;
    /**
     *签约银行
     */
    private String tppCode;
    /**
     *统一流水号
     */
    private String qunarTradeNo;
    /**
     *用户id
     */
    private String userId;
    /**
     *贷款流水
     */
    private String loanProvideNo;

    /**
     * 还款请求网关流水号
     */
    private String repayReqNo;

    /**
     * 还款订单流水号
     */
    private String repayOrderNo;

    /**
     * 还款方式 提前还款:TIQIAN 正常还款:NORMAL
     */
    private String repayType;
    /**
     * 还款扣款模式
     */
    private String repayWithholdMode;

    /**
     *业务线ID
     */
    private String busiTypeId;
    /**
     *代理商ID
     */
    private String merchantCode;
    /**
     *还款订单金额
     */
    private BigDecimal repayAmount;
    /**
     *发起时间
     */
    private Date orderTime;
    /**
     *请求网关时间
     */
    private Date canReqTime;
    /**
     *请求网关次数
     */
    private Integer reqSum;
    /**
     *完成时间
     */
    private Date finishTime;
    /**
     *订单状态
     */
    private Integer status;
    /**
     * 网关返回错误码
     */
    private String errorCode;
    /**
     * 网关返回错误信息
     */
    private String errorMsg;
    /**
     *生成时间
     */
    private Date createTime;
    /**
     *更新时间
     */
    private Date updateTime;

    /* 到期日 */
    private Date dueDate;

    /**
     * 贷款方返回的还款流水号
     */
    private String servLoanRepayId;

    /**
     *网关系统还款流水
     */
    private String gwLoanRepayId;

    /**
     * 还款总本金
     */
    private BigDecimal repayPrcpAmount;

    /**
     * 还款利息
     */
    private BigDecimal repayIntAmount;

    /**
     * 还款手续费
     */
    private BigDecimal repayFeeAmount;

    /**
     * 还款罚金
     */
    private BigDecimal repayFineAmount;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getQunarTradeNo() {
        return qunarTradeNo;
    }

    public void setQunarTradeNo(String qunarTradeNo) {
        this.qunarTradeNo = qunarTradeNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public String getRepayReqNo() {
        return repayReqNo;
    }

    public void setRepayReqNo(String repayReqNo) {
        this.repayReqNo = repayReqNo;
    }

    public String getRepayOrderNo() {
        return repayOrderNo;
    }

    public void setRepayOrderNo(String repayOrderNo) {
        this.repayOrderNo = repayOrderNo;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public String getRepayWithholdMode() {
        return repayWithholdMode;
    }

    public void setRepayWithholdMode(String repayWithholdMode) {
        this.repayWithholdMode = repayWithholdMode;
    }

    public String getBusiTypeId() {
        return busiTypeId;
    }

    public void setBusiTypeId(String busiTypeId) {
        this.busiTypeId = busiTypeId;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getCanReqTime() {
        return canReqTime;
    }

    public void setCanReqTime(Date canReqTime) {
        this.canReqTime = canReqTime;
    }

    public Integer getReqSum() {
        return reqSum;
    }

    public void setReqSum(Integer reqSum) {
        this.reqSum = reqSum;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getServLoanRepayId() {
        return servLoanRepayId;
    }

    public void setServLoanRepayId(String servLoanRepayId) {
        this.servLoanRepayId = servLoanRepayId;
    }

    public String getGwLoanRepayId() {
        return gwLoanRepayId;
    }

    public void setGwLoanRepayId(String gwLoanRepayId) {
        this.gwLoanRepayId = gwLoanRepayId;
    }

    public BigDecimal getRepayPrcpAmount() {
        return repayPrcpAmount;
    }

    public void setRepayPrcpAmount(BigDecimal repayPrcpAmount) {
        this.repayPrcpAmount = repayPrcpAmount;
    }

    public BigDecimal getRepayIntAmount() {
        return repayIntAmount;
    }

    public void setRepayIntAmount(BigDecimal repayIntAmount) {
        this.repayIntAmount = repayIntAmount;
    }

    public BigDecimal getRepayFeeAmount() {
        return repayFeeAmount;
    }

    public void setRepayFeeAmount(BigDecimal repayFeeAmount) {
        this.repayFeeAmount = repayFeeAmount;
    }

    public BigDecimal getRepayFineAmount() {
        return repayFineAmount;
    }

    public void setRepayFineAmount(BigDecimal repayFineAmount) {
        this.repayFineAmount = repayFineAmount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TblRepaymentReq{");
        sb.append("id=").append(id);
        sb.append(", scheduleId=").append(scheduleId);
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", qunarTradeNo='").append(qunarTradeNo).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", loanProvideNo='").append(loanProvideNo).append('\'');
        sb.append(", repayReqNo='").append(repayReqNo).append('\'');
        sb.append(", repayOrderNo='").append(repayOrderNo).append('\'');
        sb.append(", repayType='").append(repayType).append('\'');
        sb.append(", repayWithholdMode=").append(repayWithholdMode);
        sb.append(", busiTypeId='").append(busiTypeId).append('\'');
        sb.append(", merchantCode='").append(merchantCode).append('\'');
        sb.append(", repayAmount=").append(repayAmount);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", canReqTime=").append(canReqTime);
        sb.append(", reqSum=").append(reqSum);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", status=").append(status);
        sb.append(", errorCode='").append(errorCode).append('\'');
        sb.append(", errorMsg='").append(errorMsg).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", dueDate=").append(dueDate);
        sb.append(", servLoanRepayId='").append(servLoanRepayId).append('\'');
        sb.append(", gwLoanRepayId='").append(gwLoanRepayId).append('\'');
        sb.append(", repayPrcpAmount=").append(repayPrcpAmount);
        sb.append(", repayIntAmount=").append(repayIntAmount);
        sb.append(", repayFeeAmount=").append(repayFeeAmount);
        sb.append(", repayFineAmount=").append(repayFineAmount);
        sb.append('}');
        return sb.toString();
    }
}
