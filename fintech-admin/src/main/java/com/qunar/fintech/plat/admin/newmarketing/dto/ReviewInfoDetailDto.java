package com.qunar.fintech.plat.admin.newmarketing.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author qun.shi
 * @since 2019-02-09 5:35 PM
 */
public class ReviewInfoDetailDto extends ToString {

    private static final long serialVersionUID = 1402725117395022205L;

    /**
     * 审核id
     */
    private String reviewNo;

    /**
     * 活动id
     */
    private String activityId;

    /**
     * 券id
     */
    private String couponId;

    /**
     * 活动code：前端生成
     */
    private String activityCode;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动描述
     */
    private String activityDesc;

    /**
     * 客户群体描述
     */
    private String userDesc;

    /**
     * 用户特征
     */
    private String userFeature;

    /**
     * 活动总金额，营销账户金额
     */
    private String activityTotalAmt;

    /**
     * 活动剩余金额
     */
    private String activityRemainAmt;

    /**
     * 优惠券总金额
     */
    private String couponTotalAmt;

    /**
     * 优惠券发放数量
     */
    private String couponTotalNum;

    /**
     * 券已发放数量
     */
    private String couponGrantedAmt;

    /**
     * 券已发放数量
     */
    private String couponGrantedNum;

    /**
     * 优惠券预计使用数量
     */
    private String couponExpectedUsedNum;

    /**
     * 营销账户名称
     */
    private String activityAccountName;

    /**
     * 券的开始时间
     */
    private String couponStartTime;

    /**
     * 券的结束时间
     */
    private String couponEndTime;

    /**
     * 券code
     */
    private String couponCode;

    /**
     * 券名称
     */
    private String couponName;

    /**
     * 券描述
     */
    private String couponDesc;

    /**
     * 券说明
     */
    private String instructions;

    /**
     * 券适用于规则
     */
    private String ruleTips;

    /**
     * 券使用链接
     */
    private String couponUseUrl;

    /**
     * 业务线
     */
    private String supportProductNoList;

    /**
     * 支持的还款期序，首期：1，整期：ALL
     */
    private String repayIndexList;

    /**
     * 券维度：平台维度：PLAT，自然人维度：CUSTOMER
     */
    private String couponDimon;

    /**
     * 发放渠道：CTRIP、QUNAR、FINANCE，以逗号隔开
     */
    private String supportOrgChannelList;

    /**
     * 券类型 0:还款N元免息券 1:还款N折免息券 2:还款N天免息券 3:贷款随机立减券 4:贷款固额立减券 5:还款固额立减券
     */
    private String couponType;

    /**
     * 免息天数
     */
    private String freeIntDays;

    /**
     * 免息折扣率，7折，0.7
     */
    private String discountRate;

    /**
     * 券面额
     */
    private String couponAmount;

    /**
     * 贷款立减券的优惠金额配置
     * maxReduceAmount：最大优惠金额
     * minReduceAmount：最小优惠金额
     * avgReduceAmount：平均优惠金额
     * reduceRate：优惠金额的精度
     * 如果 maxReduceAmount = minReduceAmount = avgReduceAmount，那么就是固定立减 minReduceAmount
     * 否则是随机立减，随机生成小于maxReduceAmount，大于minReduceAmount的优惠金额
     */
    private String maxReduceAmount;

    private String minReduceAmount;

    private String avgReduceAmount;

    private String reduceRate;

    /**
     * 最大免息金额限制
     */
    private String maxFreeAmt;

    /**
     * 延迟时间（天）
     */
    private String delayDays;

    /**
     * 有效时间（天）
     */
    private String availableDays;

    /**
     * 当天发放数量限制
     */
    private String dayGrantNumLimit;

    /**
     * 用户发券次数限制
     */
    private String userGrantNumLimit;

    /**
     * 用户用券数量限制
     */
    private String userUseNumLimit;

    /**
     * 是否通过短信通知用户，0：通知，1：不通知
     */
    private String noticeUserByMsg;

    /**
     * 短信中的链接
     */
    private String msgUrl;

    /**
     * 短信内容
     */
    private String msgCotent;

    /**
     * 是否在quanr公共的代金券里面展示， 0：通知 1：不通知
     */
    private String noticeQunarPublic;

    /**
     * 账务报警：报警接受人
     */
    private String accountAlarmReceiver;

    /**
     * 账务报警：报警模式
     * @see com.qunar.fintech.plat.admin.newmarketing.enums.AccountAlertModeEnum
     */
    private String accountAlarmNoticeMethod;

    /**
     * 账务报警：公司外部邮件
     */
    private String accountAlarmExtEmailReceiver;

    /**
     * 账务报警：公司外部手机号
     */
    private String accountAlarmExtMobileReceiver;

    /**
     * 账务通知间隔（分钟）
     */
    private String accountAlarmNotifyInterval;

    /**
     * 账务报警起始时间 00:00 在00:00 ~ 23:00 时间之内会报警
     */
    private String accountAlarmStartTime;

    /**
     * 账务报警起始时间 00:00 在00:00 ~ 23:00 时间之内会报警
     */
    private String accountAlarmEndTime;

    /**
     * 账务报警模式, MAX MIN 大于或小于amount就报警
     */
    private String accountAlarmModel;

    /**
     * 账务报警金额阈值，数值
     */
    private String accountAlarmAmount;

    /**
     * 账务报警说明
     */
    private String accountAlarmRemark;

    /**
     * 账务报警，开启：USED 关闭：UNUSED
     */
    private  String accountAlarmUseStatus;

    /**
     * 券的负责人
     */
    private String owner;

    /**
     * 活动开关状态：开启或者关闭
     */
    private String couponSwitchStatus;

    /**
     * 请求的菜单id
     */
    private String requestMenuId;

    /**
     * 提交人
     */
    private String submitter;

    /**
     * 当前审核状态
     * @see
     */
    private String status;

    /**
     * 当前审核节点名称
     */
    private String curNodeName;

    /**
     * 当前审核角色id
     */
    private String curRoleId;

    /**
     * 这次发起审核的菜单id
     */
    private String requestMenuid;

    public String getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(String reviewNo) {
        this.reviewNo = reviewNo;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getUserFeature() {
        return userFeature;
    }

    public void setUserFeature(String userFeature) {
        this.userFeature = userFeature;
    }

    public String getActivityTotalAmt() {
        return activityTotalAmt;
    }

    public void setActivityTotalAmt(String activityTotalAmt) {
        this.activityTotalAmt = activityTotalAmt;
    }

    public String getActivityRemainAmt() {
        return activityRemainAmt;
    }

    public void setActivityRemainAmt(String activityRemainAmt) {
        this.activityRemainAmt = activityRemainAmt;
    }

    public String getCouponTotalAmt() {
        return couponTotalAmt;
    }

    public void setCouponTotalAmt(String couponTotalAmt) {
        this.couponTotalAmt = couponTotalAmt;
    }

    public String getCouponTotalNum() {
        return couponTotalNum;
    }

    public void setCouponTotalNum(String couponTotalNum) {
        this.couponTotalNum = couponTotalNum;
    }

    public String getCouponGrantedAmt() {
        return couponGrantedAmt;
    }

    public void setCouponGrantedAmt(String couponGrantedAmt) {
        this.couponGrantedAmt = couponGrantedAmt;
    }

    public String getCouponGrantedNum() {
        return couponGrantedNum;
    }

    public void setCouponGrantedNum(String couponGrantedNum) {
        this.couponGrantedNum = couponGrantedNum;
    }

    public String getCouponExpectedUsedNum() {
        return couponExpectedUsedNum;
    }

    public void setCouponExpectedUsedNum(String couponExpectedUsedNum) {
        this.couponExpectedUsedNum = couponExpectedUsedNum;
    }

    public String getActivityAccountName() {
        return activityAccountName;
    }

    public void setActivityAccountName(String activityAccountName) {
        this.activityAccountName = activityAccountName;
    }

    public String getCouponStartTime() {
        return couponStartTime;
    }

    public void setCouponStartTime(String couponStartTime) {
        this.couponStartTime = couponStartTime;
    }

    public String getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(String couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponDesc() {
        return couponDesc;
    }

    public void setCouponDesc(String couponDesc) {
        this.couponDesc = couponDesc;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getRuleTips() {
        return ruleTips;
    }

    public void setRuleTips(String ruleTips) {
        this.ruleTips = ruleTips;
    }

    public String getCouponUseUrl() {
        return couponUseUrl;
    }

    public void setCouponUseUrl(String couponUseUrl) {
        this.couponUseUrl = couponUseUrl;
    }

    public String getSupportProductNoList() {
        return supportProductNoList;
    }

    public void setSupportProductNoList(String supportProductNoList) {
        this.supportProductNoList = supportProductNoList;
    }

    public String getRepayIndexList() {
        return repayIndexList;
    }

    public void setRepayIndexList(String repayIndexList) {
        this.repayIndexList = repayIndexList;
    }

    public String getCouponDimon() {
        return couponDimon;
    }

    public void setCouponDimon(String couponDimon) {
        this.couponDimon = couponDimon;
    }

    public String getSupportOrgChannelList() {
        return supportOrgChannelList;
    }

    public void setSupportOrgChannelList(String supportOrgChannelList) {
        this.supportOrgChannelList = supportOrgChannelList;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getFreeIntDays() {
        return freeIntDays;
    }

    public void setFreeIntDays(String freeIntDays) {
        this.freeIntDays = freeIntDays;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getMaxReduceAmount() {
        return maxReduceAmount;
    }

    public void setMaxReduceAmount(String maxReduceAmount) {
        this.maxReduceAmount = maxReduceAmount;
    }

    public String getMinReduceAmount() {
        return minReduceAmount;
    }

    public void setMinReduceAmount(String minReduceAmount) {
        this.minReduceAmount = minReduceAmount;
    }

    public String getAvgReduceAmount() {
        return avgReduceAmount;
    }

    public void setAvgReduceAmount(String avgReduceAmount) {
        this.avgReduceAmount = avgReduceAmount;
    }

    public String getReduceRate() {
        return reduceRate;
    }

    public void setReduceRate(String reduceRate) {
        this.reduceRate = reduceRate;
    }

    public String getMaxFreeAmt() {
        return maxFreeAmt;
    }

    public void setMaxFreeAmt(String maxFreeAmt) {
        this.maxFreeAmt = maxFreeAmt;
    }

    public String getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(String delayDays) {
        this.delayDays = delayDays;
    }

    public String getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(String availableDays) {
        this.availableDays = availableDays;
    }

    public String getDayGrantNumLimit() {
        return dayGrantNumLimit;
    }

    public void setDayGrantNumLimit(String dayGrantNumLimit) {
        this.dayGrantNumLimit = dayGrantNumLimit;
    }

    public String getUserGrantNumLimit() {
        return userGrantNumLimit;
    }

    public void setUserGrantNumLimit(String userGrantNumLimit) {
        this.userGrantNumLimit = userGrantNumLimit;
    }

    public String getUserUseNumLimit() {
        return userUseNumLimit;
    }

    public void setUserUseNumLimit(String userUseNumLimit) {
        this.userUseNumLimit = userUseNumLimit;
    }

    public String getNoticeUserByMsg() {
        return noticeUserByMsg;
    }

    public void setNoticeUserByMsg(String noticeUserByMsg) {
        this.noticeUserByMsg = noticeUserByMsg;
    }

    public String getMsgUrl() {
        return msgUrl;
    }

    public void setMsgUrl(String msgUrl) {
        this.msgUrl = msgUrl;
    }

    public String getMsgCotent() {
        return msgCotent;
    }

    public void setMsgCotent(String msgCotent) {
        this.msgCotent = msgCotent;
    }

    public String getNoticeQunarPublic() {
        return noticeQunarPublic;
    }

    public void setNoticeQunarPublic(String noticeQunarPublic) {
        this.noticeQunarPublic = noticeQunarPublic;
    }

    public String getAccountAlarmReceiver() {
        return accountAlarmReceiver;
    }

    public void setAccountAlarmReceiver(String accountAlarmReceiver) {
        this.accountAlarmReceiver = accountAlarmReceiver;
    }

    public String getAccountAlarmNoticeMethod() {
        return accountAlarmNoticeMethod;
    }

    public void setAccountAlarmNoticeMethod(String accountAlarmNoticeMethod) {
        this.accountAlarmNoticeMethod = accountAlarmNoticeMethod;
    }

    public String getAccountAlarmExtEmailReceiver() {
        return accountAlarmExtEmailReceiver;
    }

    public void setAccountAlarmExtEmailReceiver(String accountAlarmExtEmailReceiver) {
        this.accountAlarmExtEmailReceiver = accountAlarmExtEmailReceiver;
    }

    public String getAccountAlarmExtMobileReceiver() {
        return accountAlarmExtMobileReceiver;
    }

    public void setAccountAlarmExtMobileReceiver(String accountAlarmExtMobileReceiver) {
        this.accountAlarmExtMobileReceiver = accountAlarmExtMobileReceiver;
    }

    public String getAccountAlarmNotifyInterval() {
        return accountAlarmNotifyInterval;
    }

    public void setAccountAlarmNotifyInterval(String accountAlarmNotifyInterval) {
        this.accountAlarmNotifyInterval = accountAlarmNotifyInterval;
    }

    public String getAccountAlarmStartTime() {
        return accountAlarmStartTime;
    }

    public void setAccountAlarmStartTime(String accountAlarmStartTime) {
        this.accountAlarmStartTime = accountAlarmStartTime;
    }

    public String getAccountAlarmEndTime() {
        return accountAlarmEndTime;
    }

    public void setAccountAlarmEndTime(String accountAlarmEndTime) {
        this.accountAlarmEndTime = accountAlarmEndTime;
    }

    public String getAccountAlarmModel() {
        return accountAlarmModel;
    }

    public void setAccountAlarmModel(String accountAlarmModel) {
        this.accountAlarmModel = accountAlarmModel;
    }

    public String getAccountAlarmAmount() {
        return accountAlarmAmount;
    }

    public void setAccountAlarmAmount(String accountAlarmAmount) {
        this.accountAlarmAmount = accountAlarmAmount;
    }

    public String getAccountAlarmRemark() {
        return accountAlarmRemark;
    }

    public void setAccountAlarmRemark(String accountAlarmRemark) {
        this.accountAlarmRemark = accountAlarmRemark;
    }

    public String getAccountAlarmUseStatus() {
        return accountAlarmUseStatus;
    }

    public void setAccountAlarmUseStatus(String accountAlarmUseStatus) {
        this.accountAlarmUseStatus = accountAlarmUseStatus;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCouponSwitchStatus() {
        return couponSwitchStatus;
    }

    public void setCouponSwitchStatus(String couponSwitchStatus) {
        this.couponSwitchStatus = couponSwitchStatus;
    }

    public String getRequestMenuId() {
        return requestMenuId;
    }

    public void setRequestMenuId(String requestMenuId) {
        this.requestMenuId = requestMenuId;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurNodeName() {
        return curNodeName;
    }

    public void setCurNodeName(String curNodeName) {
        this.curNodeName = curNodeName;
    }

    public String getCurRoleId() {
        return curRoleId;
    }

    public void setCurRoleId(String curRoleId) {
        this.curRoleId = curRoleId;
    }

    public String getRequestMenuid() {
        return requestMenuid;
    }

    public void setRequestMenuid(String requestMenuid) {
        this.requestMenuid = requestMenuid;
    }
}
