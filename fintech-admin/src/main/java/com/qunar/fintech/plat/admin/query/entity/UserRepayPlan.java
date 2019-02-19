package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.fintech.plat.admin.query.enums.GatewayRepayStatusEnum;
import com.qunar.fintech.plat.admin.query.enums.IntStatusEnum;
import com.qunar.fintech.plat.admin.query.enums.UserRepayPlanLockStatusEnum;
import com.qunar.fintech.plat.admin.query.enums.UserRepayPlanStatusEnum;
import com.qunar.pay.finance.qf.commons.utils.base.CalcUtil;
import com.qunar.pay.finance.qf.commons.utils.base.DateUtil;
import com.qunar.pay.g2.utils.persistence.Entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户还款计划 entity
 *
 * Created by shuaifeng.gao on 16-9-20.
 */
public class UserRepayPlan implements Entity<Long> {
    private Long id;

    private String userId;

    private String productNo;

    private String tppCode;

    private String orgChannel;

    private String loanProvideNo;

    private Date dueDate;

    private Integer repayIndex;

    private BigDecimal prcpAmt;

    private BigDecimal setlPrcpAmt;

    private BigDecimal intAmt;

    private BigDecimal setlIntAmt;

    private BigDecimal spreadsAmt;

    private BigDecimal setlSpreadsAmt;

    private BigDecimal fineAmt;

    private BigDecimal setlFineAmt;

    /**
     * @see UserRepayPlanStatusEnum
     */
    private Integer status;

    /**
     * @see UserRepayPlanLockStatusEnum
     */
    private Integer lockStatus;

    /**
     * @see GatewayRepayStatusEnum
     */
    private Integer gateStatus;

    /**
     * @see UserRepayFlagEnum
     */
    private String repayFlag;

    private Date actRepayDate;

    private Date lastSyncTime;

    private Date lockTime;

    private Date createTime;

    private Date updateTime;

    /**
     * 获取用户账单已还金额
     */
    public BigDecimal gettSetlTotalAmount(){
        return com.qunar.pay.g2.api.ious.util.CalcUtil.addAll(this.getSetlPrcpAmt(), this.getSetlIntAmt(),
                this.getSetlFineAmt(), this.getSetlSpreadsAmt());
    }

    /**
     * 计算服务费总额
     */
    public BigDecimal calTotalFeeAmount(){
        return CalcUtil.addAll(intAmt, spreadsAmt, fineAmt);
    }

    /**
     * 是否可发起还款
     * @return
     */
    public boolean isCanRepay(){
        return UserRepayPlanStatusEnum.INITED.getCode().equals(this.status) && UserRepayPlanLockStatusEnum.INIT.getCode().equals(this.lockStatus);
    }

    /**
     * 还款处理中
     * @return
     */
    public boolean isUserRepayIng(){
        return !UserRepayPlanStatusEnum.REPAY_SUCCESS.getCode().equals(this.status) && !UserRepayPlanLockStatusEnum.INIT.getCode().equals(this.lockStatus);
    }

    /**
     * 是否为还款成功
     * @return
     */
    public boolean isUserRepaySucc(){
        return UserRepayPlanStatusEnum.REPAY_SUCCESS.getCode().equals(this.status);
    }

    /**
     * 是否为通道方还款成功
     * @return
     */
    public boolean isGateRepaySucc(){
        return GatewayRepayStatusEnum.isGateSucc(this.gateStatus);
    }

    /**
     * 获取第三方账单应还总金额
     */
    public BigDecimal getTppRepayTotalAmount(){
        return CalcUtil.addAll(this.getActRepayPrcpAmount(), this.getActRepayIntAmount(),
                this.getActRepayFineAmount());
    }

    /**
     * 获取用户账单应还总金额
     */
    public BigDecimal getUserRepayTotalAmount(){
        return CalcUtil.addAll(this.getActRepayPrcpAmount(), this.getActRepayIntAmount(),
                this.getActRepayFineAmount(), this.getActRepaySpreadsAmount());
    }

    /**
     * 获取真实待还本金
     */
    public BigDecimal getActRepayPrcpAmount(){
        /* 实际应还本金需要减去已还本金 */
        return CalcUtil.sub(this.getPrcpAmt(), this.getSetlPrcpAmt());
    }

    /**
     *  获取第三方待还利息
     */
    public BigDecimal getActRepayIntAmount(){
        /* 实际应还利息需要减去已还利息 */
        return CalcUtil.sub(this.getIntAmt(), this.getSetlIntAmt());
    }

    /**
     * 获得实际待还违约金
     */
    public BigDecimal getActRepayFineAmount(){
        return CalcUtil.sub(this.getFineAmt(), this.getSetlFineAmt());
    }

    /**
     *  获取真实待还利差
     */
    public BigDecimal getActRepaySpreadsAmount(){
        /* 实际应还利差需要减去已还利差 */
        return CalcUtil.sub(this.getSpreadsAmt(), this.getSetlSpreadsAmt());
    }

    /**
     * 是否逾期
     * @return true:逾期 false:未逾期
     */
    public boolean isDue(){
        return this.getDueDate().before(DateUtil.getZenoByToDays());
    }

    public IntStatusEnum getDueAndNomal(){
        return this.isDue()? IntStatusEnum.OVERDUE: IntStatusEnum.NORMAL;
    }

    /**
     *  获取用户待还利息(利息和利差和)
     * @return
     */
    public BigDecimal getUserActRepayIntAmount(){
        /* 实际应还利息需要减去已还利息 */
        return CalcUtil.add(this.getActRepayIntAmount(), this.getActRepaySpreadsAmount());
    }

    /**
     * 获取利息和罚息和利差
     * @return
     */
    public BigDecimal getUserRepayIntAndFineAmount(){
        return CalcUtil.addAll(this.getActRepayIntAmount(), this.getActRepayFineAmount(), this.getActRepaySpreadsAmount());
    }

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

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getRepayIndex() {
        return repayIndex;
    }

    public void setRepayIndex(Integer repayIndex) {
        this.repayIndex = repayIndex;
    }

    public BigDecimal getPrcpAmt() {
        return prcpAmt;
    }

    public void setPrcpAmt(BigDecimal prcpAmt) {
        this.prcpAmt = prcpAmt;
    }

    public BigDecimal getSetlPrcpAmt() {
        return setlPrcpAmt;
    }

    public void setSetlPrcpAmt(BigDecimal setlPrcpAmt) {
        this.setlPrcpAmt = setlPrcpAmt;
    }

    public BigDecimal getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    public BigDecimal getSetlIntAmt() {
        return setlIntAmt;
    }

    public void setSetlIntAmt(BigDecimal setlIntAmt) {
        this.setlIntAmt = setlIntAmt;
    }

    public BigDecimal getSpreadsAmt() {
        return spreadsAmt;
    }

    public void setSpreadsAmt(BigDecimal spreadsAmt) {
        this.spreadsAmt = spreadsAmt;
    }

    public BigDecimal getSetlSpreadsAmt() {
        return setlSpreadsAmt;
    }

    public void setSetlSpreadsAmt(BigDecimal setlSpreadsAmt) {
        this.setlSpreadsAmt = setlSpreadsAmt;
    }

    public BigDecimal getFineAmt() {
        return fineAmt;
    }

    public void setFineAmt(BigDecimal fineAmt) {
        this.fineAmt = fineAmt;
    }

    public BigDecimal getSetlFineAmt() {
        return setlFineAmt;
    }

    public void setSetlFineAmt(BigDecimal setlFineAmt) {
        this.setlFineAmt = setlFineAmt;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Integer getGateStatus() {
        return gateStatus;
    }

    public void setGateStatus(Integer gateStatus) {
        this.gateStatus = gateStatus;
    }

    public String getRepayFlag() {
        return repayFlag;
    }

    public void setRepayFlag(String repayFlag) {
        this.repayFlag = repayFlag;
    }

    public Date getActRepayDate() {
        return actRepayDate;
    }

    public void setActRepayDate(Date actRepayDate) {
        this.actRepayDate = actRepayDate;
    }

    public Date getLastSyncTime() {
        return lastSyncTime;
    }

    public void setLastSyncTime(Date lastSyncTime) {
        this.lastSyncTime = lastSyncTime;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
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

    public String getProductNo() {
        return productNo;
    }


    public String toAllString() {
        final StringBuilder sb = new StringBuilder("UserRepayPlan{");
        sb.append("id=").append(id);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", productNo=").append(productNo);
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", orgChannel='").append(orgChannel).append('\'');
        sb.append(", loanProvideNo='").append(loanProvideNo).append('\'');
        sb.append(", dueDate=").append(dueDate);
        sb.append(", repayIndex=").append(repayIndex);
        sb.append(", prcpAmt=").append(prcpAmt);
        sb.append(", setlPrcpAmt=").append(setlPrcpAmt);
        sb.append(", intAmt=").append(intAmt);
        sb.append(", setlIntAmt=").append(setlIntAmt);
        sb.append(", spreadsAmt=").append(spreadsAmt);
        sb.append(", setlSpreadsAmt=").append(setlSpreadsAmt);
        sb.append(", fineAmt=").append(fineAmt);
        sb.append(", setlFineAmt=").append(setlFineAmt);
        sb.append(", status=").append(status);
        sb.append(", lockStatus=").append(lockStatus);
        sb.append(", gateStatus=").append(gateStatus);
        sb.append(", repayFlag=").append(repayFlag);
        sb.append(", actRepayDate=").append(actRepayDate);
        sb.append(", lastSyncTime=").append(lastSyncTime);
        sb.append(", lockTime=").append(lockTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append('}');
        return sb.toString();
    }


    public String toString(){
        final StringBuilder sb = new StringBuilder("UserRepayPlan{");
        sb.append("id=").append(id);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", loanProvideNo='").append(loanProvideNo).append('\'');
        sb.append(", dueDate=").append(dueDate);
        sb.append(", repayIndex=").append(repayIndex);
        sb.append(", status=").append(status);
        sb.append(", lockStatus=").append(lockStatus);
        sb.append(", gateStatus=").append(gateStatus);
        sb.append('}');
        return sb.toString();
    }
}
