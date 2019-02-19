package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.fintech.plat.admin.query.entity.TblRouteLenderExt;
import com.qunar.pay.g2.utils.persistence.Entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 查询金融路由开关信息记录
 *
 * Created by bob.li on 2015/8/11.
 */
public class QueryIousFinancialRouteSwitchRecord implements Entity<Long> {

    /**
     *主键
     */
    private Long id;

    /**
     * 贷款提供商代码
     */
    private String tppCode;

    /**
     * 贷款提供商名称
     */
    private String tppName;

    /**
     * 月利率%
     */
    private BigDecimal monthRate;

    /**
     * 年利率%
     */
    private BigDecimal annualRate;

    /**
     * 日息利率%
     */
    private BigDecimal dayRate;

    /**
     * 总预授信额度
     */
    private BigDecimal totalCreditAmt;

    /**
     * 总放款金额
     */
    private BigDecimal totalLoanAmt;

    /**
     * 每日放款笔数
     */
    private Integer loanTimesLimit;

    /**
     * 每日授信请求次数上限
     */
    private Integer dayCreditReqTimesLimit;

    /**
     * 每日激活请求次数上线
     */
    private Integer dayRepairActivateTimesLimit;
    
    /**
     * 支付开关(0:关闭 ； 1:开启)
     */
    private Integer paySwitch;

    /**
     * 预授信开关(0:关闭 ； 1:开启)
     */
    private Integer authSwitch;

    /**
     * 逾期强扣开关 ，0关闭，1开启
     */
    private Integer forcedWithholdSwitch;

    /**
     * 激活开关，0关闭，1开启
     */
    private Integer activationSwitch;

    /**
     * 订单决策开关，0关闭，1开启
     */
    private Integer orderStrategicSwitch;

    /**
     * 退款开关, 0关闭, 1开启
     */
    private Integer refundSwitch;

    /**
     * 日补授信次数上限,-1为不限制,0为关闭
     */
    private Integer dayRepairCreditTimesLimit;

    /**
     * 日授信开始时间(HH:mm:ss)
     */
    private String dayCreditStartTime;

    /**
     * 还款关闭开始时间(HHmmss 24小时制)
     */
    private String repayCloseStart;

    /**
     * 还款关闭结束时间(HHmmss 24小时制)
     */
    private String repayCloseEnd;

    /**
     * 还款结束时间是否跨天（不跨天 0，跨天 1）
     */
    private String isSpanDay;

    /**
     * 还款关闭期间提示文案
     */
    private String reminderMsg;

    /**
     * 还款至第三方的开关, 0关闭, 1开启
     */
    private Integer servRepaySwitch;

    /**
     * 产品编码,{@link ProductEnum}
     */
    private String productNo;

    private String productNoName;

    /**
     * qunar渠道扩展开关 输入参数
     */
    private List<QueryIousFinancialRouteExtSwitchRecord> qunarExtSwitchs;

    private List<QueryIousFinancialRouteExtSwitchRecord> ctripExtSwitchs;

    /**
     * 分渠道控制开关输出对象
     * key = orgChannel_indexKey
     */
    private Map<String, TblRouteLenderExt> extSwitchOut;

    public QueryIousFinancialRouteSwitchRecord() {
    }

    public Integer getOrderStrategicSwitch() {
        return orderStrategicSwitch;
    }

    public void setOrderStrategicSwitch(Integer orderStrategicSwitch) {
        this.orderStrategicSwitch = orderStrategicSwitch;
    }

    public Integer getActivationSwitch() {
        return activationSwitch;
    }

    public void setActivationSwitch(Integer activationSwitch) {
        this.activationSwitch = activationSwitch;
    }

    public Integer getForcedWithholdSwitch() {
        return forcedWithholdSwitch;
    }

    public void setForcedWithholdSwitch(Integer forcedWithholdSwitch) {
        this.forcedWithholdSwitch = forcedWithholdSwitch;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getTppName() {
        return tppName;
    }

    public void setTppName(String tppName) {
        this.tppName = tppName;
    }

    public BigDecimal getMonthRate() {
        return monthRate;
    }

    public void setMonthRate(BigDecimal monthRate) {
        this.monthRate = monthRate;
    }

    public BigDecimal getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(BigDecimal annualRate) {
        this.annualRate = annualRate;
    }

    public BigDecimal getDayRate() {
        return dayRate;
    }

    public void setDayRate(BigDecimal dayRate) {
        this.dayRate = dayRate;
    }

    public BigDecimal getTotalCreditAmt() {
        return totalCreditAmt;
    }

    public void setTotalCreditAmt(BigDecimal totalCreditAmt) {
        this.totalCreditAmt = totalCreditAmt;
    }

    public BigDecimal getTotalLoanAmt() {
        return totalLoanAmt;
    }

    public void setTotalLoanAmt(BigDecimal totalLoanAmt) {
        this.totalLoanAmt = totalLoanAmt;
    }

    public Integer getLoanTimesLimit() {
        return loanTimesLimit;
    }

    public void setLoanTimesLimit(Integer loanTimesLimit) {
        this.loanTimesLimit = loanTimesLimit;
    }

    public Integer getPaySwitch() {
        return paySwitch;
    }

    public void setPaySwitch(Integer paySwitch) {
        this.paySwitch = paySwitch;
    }

    public Integer getAuthSwitch() {
        return authSwitch;
    }

    public void setAuthSwitch(Integer authSwitch) {
        this.authSwitch = authSwitch;
    }

    public Integer getDayCreditReqTimesLimit() {
        return dayCreditReqTimesLimit;
    }

    public void setDayCreditReqTimesLimit(Integer dayCreditReqTimesLimit) {
        this.dayCreditReqTimesLimit = dayCreditReqTimesLimit;
    }

    public Integer getDayRepairCreditTimesLimit() {
        return dayRepairCreditTimesLimit;
    }

    public void setDayRepairCreditTimesLimit(Integer dayRepairCreditTimesLimit) {
        this.dayRepairCreditTimesLimit = dayRepairCreditTimesLimit;
    }

    public String getDayCreditStartTime() {
        return dayCreditStartTime;
    }

    public void setDayCreditStartTime(String dayCreditStartTime) {
        this.dayCreditStartTime = dayCreditStartTime;
    }

    /**
     * @return the dayRepairActivateTimesLimit
     */
    public Integer getDayRepairActivateTimesLimit() {
        return dayRepairActivateTimesLimit;
    }

    
    /**
     * @param dayRepairActivateTimesLimit the dayRepairActivateTimesLimit to set
     */
    public void setDayRepairActivateTimesLimit(Integer dayRepairActivateTimesLimit) {
        this.dayRepairActivateTimesLimit = dayRepairActivateTimesLimit;
    }

    public Integer getRefundSwitch() {
        return refundSwitch;
    }

    public void setRefundSwitch(Integer refundSwitch) {
        this.refundSwitch = refundSwitch;
    }

    public Integer getServRepaySwitch() {
        return servRepaySwitch;
    }

    public void setServRepaySwitch(Integer servRepaySwitch) {
        this.servRepaySwitch = servRepaySwitch;
    }

    public String getRepayCloseStart() {
        return repayCloseStart;
    }

    public void setRepayCloseStart(String repayCloseStart) {
        this.repayCloseStart = repayCloseStart;
    }

    public String getRepayCloseEnd() {
        return repayCloseEnd;
    }

    public void setRepayCloseEnd(String repayCloseEnd) {
        this.repayCloseEnd = repayCloseEnd;
    }

    public String getReminderMsg() {
        return reminderMsg;
    }

    public void setReminderMsg(String reminderMsg) {
        this.reminderMsg = reminderMsg;
    }

    public String getIsSpanDay() {
        return isSpanDay;
    }

    public void setIsSpanDay(String isSpanDay) {
        this.isSpanDay = isSpanDay;
    }


    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public List<QueryIousFinancialRouteExtSwitchRecord> getQunarExtSwitchs() {
        return qunarExtSwitchs;
    }

    public void setQunarExtSwitchs(List<QueryIousFinancialRouteExtSwitchRecord> qunarExtSwitchs) {
        this.qunarExtSwitchs = qunarExtSwitchs;
    }

    public List<QueryIousFinancialRouteExtSwitchRecord> getCtripExtSwitchs() {
        return ctripExtSwitchs;
    }

    public void setCtripExtSwitchs(List<QueryIousFinancialRouteExtSwitchRecord> ctripExtSwitchs) {
        this.ctripExtSwitchs = ctripExtSwitchs;
    }

    public Map<String, TblRouteLenderExt> getExtSwitchOut() {
        return extSwitchOut;
    }

    public void setExtSwitchOut(Map<String, TblRouteLenderExt> extSwitchOut) {
        this.extSwitchOut = extSwitchOut;
    }

    public String getProductNoName() {
        return productNoName;
    }

    public void setProductNoName(String productNoName) {
        this.productNoName = productNoName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QueryIousFinancialRouteSwitchRecord{");
        sb.append("id=").append(id);
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", tppName='").append(tppName).append('\'');
        sb.append(", monthRate=").append(monthRate);
        sb.append(", annualRate=").append(annualRate);
        sb.append(", dayRate=").append(dayRate);
        sb.append(", totalCreditAmt=").append(totalCreditAmt);
        sb.append(", totalLoanAmt=").append(totalLoanAmt);
        sb.append(", loanTimesLimit=").append(loanTimesLimit);
        sb.append(", dayCreditReqTimesLimit=").append(dayCreditReqTimesLimit);
        sb.append(", dayRepairActivateTimesLimit=").append(dayRepairActivateTimesLimit);
        sb.append(", paySwitch=").append(paySwitch);
        sb.append(", authSwitch=").append(authSwitch);
        sb.append(", forcedWithholdSwitch=").append(forcedWithholdSwitch);
        sb.append(", activationSwitch=").append(activationSwitch);
        sb.append(", orderStrategicSwitch=").append(orderStrategicSwitch);
        sb.append(", refundSwitch=").append(refundSwitch);
        sb.append(", dayRepairCreditTimesLimit=").append(dayRepairCreditTimesLimit);
        sb.append(", dayCreditStartTime='").append(dayCreditStartTime).append('\'');
        sb.append(", repayCloseStart='").append(repayCloseStart).append('\'');
        sb.append(", repayCloseEnd='").append(repayCloseEnd).append('\'');
        sb.append(", isSpanDay='").append(isSpanDay).append('\'');
        sb.append(", reminderMsg='").append(reminderMsg).append('\'');
        sb.append(", servRepaySwitch=").append(servRepaySwitch);
        sb.append(", productNo='").append(productNo).append('\'');
        sb.append(", productNoName='").append(productNoName).append('\'');
        sb.append(", qunarExtSwitchs=").append(qunarExtSwitchs);
        sb.append(", ctripExtSwitchs=").append(ctripExtSwitchs);
        sb.append(", extSwitchOut=").append(extSwitchOut);
        sb.append('}');
        return sb.toString();
    }
}
