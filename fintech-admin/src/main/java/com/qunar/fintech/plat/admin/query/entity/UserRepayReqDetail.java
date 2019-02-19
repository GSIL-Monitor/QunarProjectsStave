package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.g2.utils.persistence.Entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dw.wang
 * @since 2016-03-07
 */
public class UserRepayReqDetail implements Entity<Long> {
    private static final long serialVersionUID = -1719560310389311785L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 用户还款请求流水
     */
    private String reqSerialNo;
    /**
     * 用户还款发起流水
     */
    private String serialNo;
    /**
     * 账务统一流水号
     */
    private String qunarTradeNo;
    /**
     * 应还金额
     */
    private BigDecimal repayAmt;
    /**
     * 请求时间
     */
    private Date requestTime;
    /**
     * 生成时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 通道  PC:PC端 WL:无线端 SYS:系统（定时任务等）
     */
    private String channel;
    /**
     * 还款类型 SEVEN_DAY: 七日待还 REPAID: 快速还款 REPAY_ALL: 全部待还 LOAN: 借据内还款  ADVANCE: 提前还款 WITHHOLD:代扣
     */
    private String repayEntry;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReqSerialNo() {
        return reqSerialNo;
    }

    public void setReqSerialNo(String reqSerialNo) {
        this.reqSerialNo = reqSerialNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getQunarTradeNo() {
        return qunarTradeNo;
    }

    public void setQunarTradeNo(String qunarTradeNo) {
        this.qunarTradeNo = qunarTradeNo;
    }

    public BigDecimal getRepayAmt() {
        return repayAmt;
    }

    public void setRepayAmt(BigDecimal repayAmt) {
        this.repayAmt = repayAmt;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
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


    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getRepayEntry() {
        return repayEntry;
    }

    public void setRepayEntry(String repayEntry) {
        this.repayEntry = repayEntry;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserRepayReqDetail{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", reqSerialNo='").append(reqSerialNo).append('\'');
        sb.append(", serialNo='").append(serialNo).append('\'');
        sb.append(", qunarTradeNo='").append(qunarTradeNo).append('\'');
        sb.append(", repayAmt=").append(repayAmt);
        sb.append(", requestTime=").append(requestTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", repayEntry=").append(repayEntry);
        sb.append(", channel=").append(channel);
        sb.append('}');
        return sb.toString();
    }
}
