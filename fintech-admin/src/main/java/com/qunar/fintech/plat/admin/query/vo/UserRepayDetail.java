package com.qunar.fintech.plat.admin.query.vo;


import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户还款详情实体，还款计划加上业务订单号，方便运营人员查询订单
 */
public class UserRepayDetail extends ToString{
    /**
     * 主键id
     */
    private Long id;
    /**
     * 操作流水号
     */
    private String serialNo;
    /**
     * 贷款流水号
     */
    private String loanProvideNo;
    /**
     * 操作类型描述
     */
    private String operTypeStr;
    /**
     * 过期日期
     */
    private Date dueDateTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 完成时间
     */
    private Date finishTime;
    /**
     * 还款状态
     */
    private Integer status;
    /**
     * 还款成功金额
     */
    private BigDecimal sucAmount;
    /**
     * 还款失败金额
     */
    private BigDecimal failAmount;

    /**
     * 业务线流水号
     */
    private String busiOrderNo;

    /**
     * 失败信息
     */
    private String errMsg;

    /**
     * 通道
     */
    private String tppCode;

    private String statusDesc;

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public String getOperTypeStr() {
        return operTypeStr;
    }

    public void setOperTypeStr(String operTypeStr) {
        this.operTypeStr = operTypeStr;
    }

    public Date getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(Date dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getBusiOrderNo() {
        return busiOrderNo;
    }

    public void setBusiOrderNo(String busiOrderNo) {
        this.busiOrderNo = busiOrderNo;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
