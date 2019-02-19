package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;
import com.qunar.pay.finance.qf.commons.utils.base.CalcUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dw.wang
 * @since 2016-03-07
 */
public class UserRepayReq extends ToString {
    private static final long serialVersionUID = 472489378532045549L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户Id
     */
    private String productNo;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 请求userId
     */
    private String reqUserId;

    /**
     * 身份证
     */
    private String identity;

    /**
     * 还款请求流水
     */
    private String serialNo;
    /**
     * 账务统一流水号
     */
    private String qunarTradeNo;
    /**
     * 应还总金额
     */
    private BigDecimal repayAmt;
    /**
     * 还款本金
     */
    private BigDecimal repayPrcpAmt;
    /**
     * 扣款成功金额
     */
    private BigDecimal withholdAmt;
    /**
     * 还款发起金额
     */
    private BigDecimal repayReqAmt;
    /**
     * 还款成功金额
     */
    private BigDecimal repaySuccAmt;
    /**
     * 还款失败金额
     */
    private BigDecimal repayFailedAmt;
    /**
     * 退款金额
     */
    private BigDecimal refundAmt;
    /**
     * 请求发起时间
     */
    private Date repayLaunchTime;
    /**
     * 请求次数
     */
    private Integer reqNum;
    /**
     * 订单状态 0:初始,1:扣款中,2:还款中,3:还款结束,4:流程结束
     */
    private Integer status;
    /**
     * 通道 PC:PC端 WL:无线端 SYS:系统（定时任务等）
     */
    private String channel;
    /**
     * 还款类型 SEVEN_DAY: 七日待还 REPAID: 快速还款 REPAY_ALL: 全部待还 LOAN: 借据内还款  ADVANCE: 提前还款 WITHHOLD:代扣'
     */
    private String repayEntry;
    /**
     * 无线客户端硬件id
     */
    private String clientMac;
    /**
     * 客户经纬度
     */
    private String clientGps;
    /**
     * 信息发送状态：0无需发短信，1可发送，2发送成功，3发送失败
     */
    private Integer smsStatus;
    /**
     * 还款场景
     */
    private String payScene;
    /**
     * 生成时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 姓名
     */
    private String userName;

    /**
     * 还能发起的还款金额
     */
    private BigDecimal canRepayAmt;

    /**
     * 机构通道
     */
    private String orgChannel;

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
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

    public BigDecimal getWithholdAmt() {
        return withholdAmt;
    }

    public void setWithholdAmt(BigDecimal withholdAmt) {
        this.withholdAmt = withholdAmt;
    }

    public BigDecimal getRepayReqAmt() {
        return repayReqAmt;
    }

    public void setRepayReqAmt(BigDecimal repayReqAmt) {
        this.repayReqAmt = repayReqAmt;
    }

    public BigDecimal getRepaySuccAmt() {
        return repaySuccAmt;
    }

    public void setRepaySuccAmt(BigDecimal repaySuccAmt) {
        this.repaySuccAmt = repaySuccAmt;
    }

    public BigDecimal getRepayFailedAmt() {
        return repayFailedAmt;
    }

    public void setRepayFailedAmt(BigDecimal repayFailedAmt) {
        this.repayFailedAmt = repayFailedAmt;
    }

    public BigDecimal getRefundAmt() {
        return refundAmt;
    }

    public void setRefundAmt(BigDecimal refundAmt) {
        this.refundAmt = refundAmt;
    }

    public Date getRepayLaunchTime() {
        return repayLaunchTime;
    }

    public void setRepayLaunchTime(Date repayLaunchTime) {
        this.repayLaunchTime = repayLaunchTime;
    }

    public Integer getReqNum() {
        return reqNum;
    }

    public void setReqNum(Integer reqNum) {
        this.reqNum = reqNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getClientMac() {
        return clientMac;
    }

    public void setClientMac(String clientMac) {
        this.clientMac = clientMac;
    }

    public String getClientGps() {
        return clientGps;
    }

    public void setClientGps(String clientGps) {
        this.clientGps = clientGps;
    }

    public Integer getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(Integer smsStatus) {
        this.smsStatus = smsStatus;
    }

    public String getPayScene() {
        return payScene;
    }

    public void setPayScene(String payScene) {
        this.payScene = payScene;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取能发起还款的金额
     * @return
     */
    public BigDecimal getCanRepayAmt(){
        return CalcUtil.add(CalcUtil.subAll(this.withholdAmt, this.refundAmt, this.repayReqAmt),//剩余支付金额
                this.repayFailedAmt);//还款失败金额
    }

    public void setCanRepayAmt(BigDecimal canRepayAmt) {
        this.canRepayAmt = canRepayAmt;
    }
    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public BigDecimal getRepayPrcpAmt() {
        return repayPrcpAmt;
    }

    public void setRepayPrcpAmt(BigDecimal repayPrcpAmt) {
        this.repayPrcpAmt = repayPrcpAmt;
    }

    public String getReqUserId() {
        return reqUserId;
    }

    public void setReqUserId(String reqUserId) {
        this.reqUserId = reqUserId;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
