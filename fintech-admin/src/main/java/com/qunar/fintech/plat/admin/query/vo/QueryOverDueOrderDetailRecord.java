package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.fintech.plat.admin.query.enums.AuditStatusEnum;
import com.qunar.fintech.plat.admin.query.enums.RepayStatusEnum;
import com.qunar.fintech.plat.admin.query.enums.WithholdStatusEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bob.li on 2015/9/14.
 */
public class QueryOverDueOrderDetailRecord implements Serializable {
    private static final long serialVersionUID = 8116510874791395025L;

    /* 订单时间 */
    private Date orderTime;
    /* 还款期数 */
    private Integer repayIndex;
    /* 到期还款日 */
    private Date dueDate;
    /* 逾期天数 */
    private Integer overDueDay;
    /*  违约金 */
    private BigDecimal penalty;
    /* 本期应还金额 */
    private BigDecimal repayAmtCurrentTerm;
    /* 还款状态 */
    private RepayStatusEnum repayStatus;
    /*  扣款状态 */
    private WithholdStatusEnum withHoldStatus;
    /* 审核状态 */
    private AuditStatusEnum auditStatus;
    /* 实际扣款金额 */
    private BigDecimal withHoldAmt;
    private String productNo;

    /* 操作，1.强制扣款，2.取消扣款，3.已扣款，4.扣款中 */
    private Integer operate;

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getRepayIndex() {
        return repayIndex;
    }

    public void setRepayIndex(Integer repayIndex) {
        this.repayIndex = repayIndex;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getOverDueDay() {
        return overDueDay;
    }

    public void setOverDueDay(Integer overDueDay) {
        this.overDueDay = overDueDay;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }

    public BigDecimal getRepayAmtCurrentTerm() {
        return repayAmtCurrentTerm;
    }

    public void setRepayAmtCurrentTerm(BigDecimal repayAmtCurrentTerm) {
        this.repayAmtCurrentTerm = repayAmtCurrentTerm;
    }

    public RepayStatusEnum getRepayStatus() {
        return repayStatus;
    }

    public void setRepayStatus(RepayStatusEnum repayStatus) {
        this.repayStatus = repayStatus;
    }

    public WithholdStatusEnum getWithHoldStatus() {
        return withHoldStatus;
    }

    public void setWithHoldStatus(WithholdStatusEnum withHoldStatus) {
        this.withHoldStatus = withHoldStatus;
    }

    public AuditStatusEnum getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatusEnum auditStatus) {
        this.auditStatus = auditStatus;
    }

    public BigDecimal getWithHoldAmt() {
        return withHoldAmt;
    }

    public void setWithHoldAmt(BigDecimal withHoldAmt) {
        this.withHoldAmt = withHoldAmt;
    }

    public Integer getOperate() {
        return operate;
    }

    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	@Override
    public String toString() {
        return "QueryOverDueOrderDetailRecord{" +
                "orderTime=" + orderTime +
                ", repayIndex=" + repayIndex +
                ", dueDate=" + dueDate +
                ", overDueDay=" + overDueDay +
                ", penalty=" + penalty +
                ", repayAmtCurrentTerm=" + repayAmtCurrentTerm +
                ", repayStatus=" + repayStatus +
                ", withHoldStatus=" + withHoldStatus +
                ", auditStatus=" + auditStatus +
                ", withHoldAmt=" + withHoldAmt +
                ", operate=" + operate +
                ", productNo=" + productNo +
                '}';
    }
}
