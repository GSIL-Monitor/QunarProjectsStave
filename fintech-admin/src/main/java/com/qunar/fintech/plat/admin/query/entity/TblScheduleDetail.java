package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;
import com.qunar.pay.finance.qf.commons.utils.base.CalcUtil;
import com.qunar.pay.finance.qf.commons.utils.base.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述：</b>TblScheduleDetail:还款计划明细表<br>
 * @author baron.jiang
 * @since：2015年02月04日 10时43分45秒 星期三
 */
public class TblScheduleDetail extends ToString {
	private static final long serialVersionUID = 1L;
	
	/**
	 *主键
	 */
	private Long id;
	/**
	 *还款计划总表id
	 */
	private Long repaymentScheduleId;
	/**
	 *用户id
	 */
	private String userId;
    /**
     *请求id
     */
    private String reqUserId;
	/**
	 *签约银行
	 */
	private String productNo;

    /**
     * 渠道号
     */
    private String orgChannel;
	/**
	 *签约银行
	 */
	private String tppCode;
	/**
	 *贷款流水
	 */
	private String loanProvideNo;
	/**
	 *到期日
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dueDate;
	/**
	 *执行利率
	 */
	private BigDecimal intRate;
	/**
	 *应还本金
	 */
	private BigDecimal prcpAmount;
	/**
	 *应还利息
	 */
	private BigDecimal intAmount;
	/**
	 *应还本息合计
	 */
	private BigDecimal totalAmount;
	/**
	 *已还本金
	 */
	private BigDecimal setlPrcpAmount;
	/**
	 *已还利息
	 */
	private BigDecimal payedAmount;
	/**
	 *剩余本金
	 */
	private BigDecimal schedPrcpAmount;
	/**
	 *本金状态
	 */
	private String prcpStatus;
	/**
	 *上次还款日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastSetlDate;
	/**
	 *还款状态：0 未还款 ,1还款中,    2 银行已受理,    3银行已还款
	 */
	private Integer status;
	/**
	 *生成时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 *更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	/**
	 * 拖欠罚息金额
	 */
	private BigDecimal odAmount;
	/**
	 * 当前罚息
	 */
	private BigDecimal curOdAmount;

	/**
	 * 已结算罚息
	 */
	private BigDecimal setlOdAmount;
	/**
	 * 拖欠本金金额
	 */
	private BigDecimal odPrcpAmount;
	/**
	 * 拖欠利息金额
	 */
	private BigDecimal odIntAmount;
	/**
	 * 实际还款日 暂时与本期还款到期日一样
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date actRepayDate;
	/**
	 * 利息状态，标识还款详情正常/逾期
	 */
	private String intStatus;
	/**
	 *还款期序
	 */
	private Integer repayIndex;

	/**
	 * 应还利差
	 */
	private BigDecimal spreadsAmount;

	/**
	 * 已还利差
	 */
	private BigDecimal payedSpreadsAmount;

	private String statusName;

	/**
	 * 逾期天数
	 */
	public Integer dueDay;
	/**
	 * 是否逾期
	 * @return true:逾期 false:未逾期
	 */
	public boolean isDue(){
		return this.getDueDate().before(DateUtil.getZenoByToDays());
	}

	public BigDecimal getSetlOdAmount() {
		return setlOdAmount;
	}
	public void setSetlOdAmount(BigDecimal setlOdAmount) {
		this.setlOdAmount = setlOdAmount;
	}
	public Long getId() {
	    return this.id;
	}
	public void setId(Long id) {
	    this.id=id;
	}
	public Long getRepaymentScheduleId() {
	    return this.repaymentScheduleId;
	}
	public void setRepaymentScheduleId(Long repaymentScheduleId) {
	    this.repaymentScheduleId=repaymentScheduleId;
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
	    this.tppCode=tppCode;
	}
	public String getLoanProvideNo() {
	    return this.loanProvideNo;
	}
	public void setLoanProvideNo(String loanProvideNo) {
	    this.loanProvideNo=loanProvideNo;
	}
	public Date getDueDate() {
	    return this.dueDate;
	}
	public void setDueDate(Date dueDate) {
	    this.dueDate=dueDate;
	}
	public BigDecimal getIntRate() {
	    return this.intRate;
	}
	public void setIntRate(BigDecimal intRate) {
	    this.intRate=intRate;
	}
	public BigDecimal getPrcpAmount() {
	    return this.prcpAmount;
	}
	public void setPrcpAmount(BigDecimal prcpAmount) {
	    this.prcpAmount=prcpAmount;
	}
	public BigDecimal getIntAmount() {
	    return this.intAmount;
	}
	public void setIntAmount(BigDecimal intAmount) {
	    this.intAmount=intAmount;
	}
	public BigDecimal getTotalAmount() {
	    return this.totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
	    this.totalAmount=totalAmount;
	}
	public BigDecimal getSetlPrcpAmount() {
	    return this.setlPrcpAmount;
	}
	public void setSetlPrcpAmount(BigDecimal setlPrcpAmount) {
	    this.setlPrcpAmount=setlPrcpAmount;
	}
	public BigDecimal getPayedAmount() {
	    return this.payedAmount;
	}
	public void setPayedAmount(BigDecimal payedAmount) {
	    this.payedAmount=payedAmount;
	}
	public BigDecimal getSchedPrcpAmount() {
	    return this.schedPrcpAmount;
	}
	public void setSchedPrcpAmount(BigDecimal schedPrcpAmount) {
	    this.schedPrcpAmount=schedPrcpAmount;
	}
	public String getPrcpStatus() {
	    return this.prcpStatus;
	}
	public void setPrcpStatus(String prcpStatus) {
	    this.prcpStatus=prcpStatus;
	}
	public Date getLastSetlDate() {
	    return this.lastSetlDate;
	}
	public void setLastSetlDate(Date lastSetlDate) {
	    this.lastSetlDate=lastSetlDate;
	}
	public Integer getStatus() {
	    return this.status;
	}
	public void setStatus(Integer status) {
	    this.status=status;
	}
	public Date getCreateTime() {
	    return this.createTime;
	}
	public void setCreateTime(Date createTime) {
	    this.createTime=createTime;
	}
	public Date getUpdateTime() {
	    return this.updateTime;
	}
	public void setUpdateTime(Date updateTime) {
	    this.updateTime=updateTime;
	}
	public BigDecimal getOdAmount() {
		return odAmount;
	}
	public void setOdAmount(BigDecimal odAmount) {
		this.odAmount = odAmount;
	}
	public BigDecimal getCurOdAmount() {
		return curOdAmount;
	}
	public void setCurOdAmount(BigDecimal curOdAmount) {
		this.curOdAmount = curOdAmount;
	}

	public BigDecimal getSpreadsAmount() {
		return spreadsAmount;
	}

	public void setSpreadsAmount(BigDecimal spreadsAmount) {
		this.spreadsAmount = spreadsAmount;
	}

	public BigDecimal getPayedSpreadsAmount() {
		return payedSpreadsAmount;
	}

	public void setPayedSpreadsAmount(BigDecimal payedSpreadsAmount) {
		this.payedSpreadsAmount = payedSpreadsAmount;
	}

//	public Boolean isRePaySuccess() {
//		return TblScheduleDetailStatusEnum.isRePaySuccess(status);
//	}
//
//	public Boolean isInit(){
//		return TblScheduleDetailStatusEnum.INITED.getCode().equals(status);
//	}

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    /**
	 * 获取真实待还本金
	 * @return
	 */
	public BigDecimal getActRepayPrcpAmount(){
        /* 实际应还本金需要减去已还本金 */
		return CalcUtil.sub(this.getPrcpAmount(), this.getSetlPrcpAmount());
	}

	/**
	 *  获取待还利息
	 * @return
	 */
	public BigDecimal getActRepayIntAmount(){
        /* 实际应还本金需要减去已还利息 */
		return CalcUtil.sub(this.getIntAmount(), this.getPayedAmount());
	}


	/**
	 * 获取应还本息合计(不包括罚息)
	 * @return
	 */
	public BigDecimal getActRepayTotalAmountWithoutRepayFineAmount(){
		return CalcUtil.add(this.getActRepayPrcpAmount(), this.getActRepayIntAmount());
	}

	/**
	 * 获取应还总金额(包括本金,利息,罚息)
	 * @return
	 */
	public BigDecimal getActRepayTotalAmount(){
		return CalcUtil.addAll(this.prcpAmount, this.intAmount, this.odAmount);
	}
	/**
	 *  获取真实待还利差
	 * @return
	 */
	public BigDecimal getActRepaySpreadsAmount(){
        /* 实际应还利差需要减去已还利差 */
		return CalcUtil.sub(this.getSpreadsAmount(), this.getPayedSpreadsAmount());
	}

	/**
	 * 获取账单应还总金额
	 * @return
	 */
	public BigDecimal getRepayTotalAmount(){
		return CalcUtil.addAll(this.getActRepayPrcpAmount(), this.getActRepayIntAmount(),
				this.getActRepayFineAmount(), this.getActRepaySpreadsAmount());
	}

	/**
	 * 获得实际待还违约金
	 * @return
	 */
	public BigDecimal getActRepayFineAmount(){
		return CalcUtil.sub(this.odAmount, this.setlOdAmount);
	}

	/**
	 * 获取利息和罚息
	 * @return
	 */
	public BigDecimal getRepayIntAndFineAmount(){
		return CalcUtil.add(this.getActRepayIntAmount(), this.getActRepayFineAmount());
	}
	public BigDecimal getOdPrcpAmount() {
		return odPrcpAmount;
	}

	public void setOdPrcpAmount(BigDecimal odPrcpAmount) {
		this.odPrcpAmount = odPrcpAmount;
	}

	public BigDecimal getOdIntAmount() {
		return odIntAmount;
	}

	public void setOdIntAmount(BigDecimal odIntAmount) {
		this.odIntAmount = odIntAmount;
	}

	public Date getActRepayDate() {
		return actRepayDate;
	}

	public void setActRepayDate(Date actRepayDate) {
		this.actRepayDate = actRepayDate;
	}

	public String getIntStatus() {
		return intStatus;
	}

	public void setIntStatus(String intStatus) {
		this.intStatus = intStatus;
	}

	public Integer getRepayIndex() {
		return repayIndex;
	}

	public void setRepayIndex(Integer repayIndex) {
		this.repayIndex = repayIndex;
	}

    public String getReqUserId() {
        return reqUserId;
    }

    public void setReqUserId(String reqUserId) {
        this.reqUserId = reqUserId;
    }

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getDueDay() {
		return dueDay;
	}

	public void setDueDay(Integer dueDay) {
		this.dueDay = dueDay;
	}
}

