package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dw.wang
 * @since 2016-03-16.
 */
public class UserRepayExt extends ToString {
    private static final long serialVersionUID = 7644694150444793865L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 操作流水号
     */
    private String serialNo;
    /**
     * 操作类型
     */
    private Integer operType;
    /**
     * 贷款流水号
     */
    private String loanProvideNo;
    /**
     * 逾期日
     */
    private String dueDate;

    /**
     * 还款金额
     */
    private BigDecimal repayAmt;
    /**
     * 还款成功金额
     */
    private BigDecimal sucAmount;
    /**
     * 还款失败金额
     */
    private BigDecimal failAmount;

    private BigDecimal repayPrcpAmt;
    /**
     * 还款状态
     */
    private Integer status;
    /**
     * 完成时间
     */
    private Date finishTime;
    /**
     * 错误码
     */
    private String errCode;
    /**
     * 错误信息
     */
    private String errMsg;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 过期日期
     */
    private Date dueDateTime;
    /**
     * 操作类型描述
     */
    private String operTypeStr;

    /**
     * tppCode
     */
    private String tppCode;

    private String statusDesc;

    public Long getId() {
        return id;
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

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public BigDecimal getRepayAmt() {
        return repayAmt;
    }

    public void setRepayAmt(BigDecimal repayAmt) {
        this.repayAmt = repayAmt;
    }

    public BigDecimal getSucAmount() {
        return sucAmount;
    }

    public void setSucAmount(BigDecimal sucAmount) {
        this.sucAmount = sucAmount;
    }

    public BigDecimal getFailAmount() {
        return failAmount;
    }

    public void setFailAmount(BigDecimal failAmount) {
        this.failAmount = failAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
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

    public Date getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(Date dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public String getOperTypeStr() {
        return operTypeStr;
    }

    public void setOperTypeStr(String operTypeStr) {
        this.operTypeStr = operTypeStr;
    }

    public BigDecimal getRepayPrcpAmt() {
        return repayPrcpAmt;
    }

    public void setRepayPrcpAmt(BigDecimal repayPrcpAmt) {
        this.repayPrcpAmt = repayPrcpAmt;
    }


    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

}

