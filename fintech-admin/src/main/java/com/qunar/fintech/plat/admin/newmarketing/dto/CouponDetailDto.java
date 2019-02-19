package com.qunar.fintech.plat.admin.newmarketing.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qunar.fintech.marketing.api.admin.enums.SwitchEnum;
import com.qunar.fintech.marketing.api.admin.model.TblActivityDto;
import com.qunar.fintech.marketing.api.admin.model.TblCouponDto;
import com.qunar.fintech.marketing.api.enums.CouponTypeEnum;
import com.qunar.fintech.plat.admin.newmarketing.constants.CouponConstants;
import com.qunar.fintech.plat.admin.system.dao.entity.ReviewInfo;
import com.qunar.fintech.plat.admin.query.utils.JSONUtils;
import com.qunar.pay.finance.qf.commons.api.enums.UidType;
import com.qunar.pay.finance.qf.commons.api.model.ToString;
import com.qunar.pay.finance.qf.commons.utils.base.StringUtils;

import java.math.BigDecimal;

/**
 * @author qun.shi
 * @since 2019-01-28 1:52 PM
 */
public class CouponDetailDto extends ToString {
    private static final long serialVersionUID = 3517373359233349333L;

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
     * 是否新账务
     */
    private String newAccount;

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
     * 发放渠道：CTRIP、QUNAR、FINANCE
     */
    private String supportOrgChannelList;

    /**
     * 券类型 0 free 2 cash 3 reduce
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
    private String msgContent;

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

    public String getRequestMenuId() {
        return requestMenuId;
    }

    public void setRequestMenuId(String requestMenuId) {
        this.requestMenuId = requestMenuId;
    }

    public String getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(String reviewNo) {
        this.reviewNo = reviewNo;
    }

    public String getCouponSwitchStatus() {
        return couponSwitchStatus;
    }

    public void setCouponSwitchStatus(String couponSwitchStatus) {
        this.couponSwitchStatus = couponSwitchStatus;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public String getCouponExpectedUsedNum() {
        return couponExpectedUsedNum;
    }

    public void setCouponExpectedUsedNum(String couponExpectedUsedNum) {
        this.couponExpectedUsedNum = couponExpectedUsedNum;
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

    public String getMaxFreeAmt() {
        return maxFreeAmt;
    }

    public void setMaxFreeAmt(String maxFreeAmt) {
        this.maxFreeAmt = maxFreeAmt;
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

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
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

    public String getActivityRemainAmt() {
        return activityRemainAmt;
    }

    public void setActivityRemainAmt(String activityRemainAmt) {
        this.activityRemainAmt = activityRemainAmt;
    }

    public String getCouponDesc() {
        return couponDesc;
    }

    public void setCouponDesc(String couponDesc) {
        this.couponDesc = couponDesc;
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

    public String getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(String newAccount) {
        this.newAccount = newAccount;
    }

    /**
     * 组装券详情信息
     */
    public static CouponDetailDto buildCouponInfoDto(TblActivityDto tblActivityDto,
                                                     TblCouponDto tblCouponDto, AccountAlarmDetailDto alarmDto, ReviewInfo reviewInfo){
        CouponDetailDto couponDetailDto = new CouponDetailDto();
        //  券信息
        couponDetailDto.setCouponId(StringUtils.valueOf(tblCouponDto.getId()));
        couponDetailDto.setCouponType(String.valueOf(tblCouponDto.getCouponType().getCode()));
        couponDetailDto.setCouponCode(tblCouponDto.getCouponCode());
        couponDetailDto.setCouponName(tblCouponDto.getCouponName());
        couponDetailDto.setCouponDesc(tblCouponDto.getCouponDesc());
        couponDetailDto.setCouponUseUrl(tblCouponDto.getUrl());
        couponDetailDto.setDiscountRate(String.valueOf(tblCouponDto.getDiscountRate()));
        couponDetailDto.setCouponAmount(String.valueOf(tblCouponDto.getCouponAmount()));
        couponDetailDto.setCouponCode(tblCouponDto.getCouponCode());
        couponDetailDto.setAvailableDays(String.valueOf(tblCouponDto.getAvailableHours()));
        couponDetailDto.setCouponStartTime(tblCouponDto.getStartTime());
        couponDetailDto.setCouponEndTime(tblCouponDto.getEndTime());
        couponDetailDto.setInstructions(tblCouponDto.getInstructions());
        couponDetailDto.setNoticeUserByMsg(String.valueOf(tblCouponDto.getUserPercept()));

        if(StringUtils.isNotBlank(tblCouponDto.getExt())){
            JSONObject jsonObject = JSON.parseObject(tblCouponDto.getExt());
            couponDetailDto.setRuleTips((String)jsonObject.get("ruleTips"));
            couponDetailDto.setMsgUrl((String)jsonObject.get("msgUrl"));
            couponDetailDto.setMsgContent((String)jsonObject.get("couponContent"));
        }

        if(StringUtils.isNotBlank(tblCouponDto.getRuleParams())){
            JSONObject jsonObject = JSON.parseObject(tblCouponDto.getRuleParams());
            couponDetailDto.setUserGrantNumLimit(String.valueOf(jsonObject.get("userGrantMax")));
            couponDetailDto.setRepayIndexList((String)jsonObject.get("freePeriod"));
            couponDetailDto.setDayGrantNumLimit(String.valueOf(jsonObject.get("activityDayGrantMax")));
            couponDetailDto.setUserUseNumLimit(String.valueOf(jsonObject.get("userUseMax")));
        }

        couponDetailDto.setDelayDays(String.valueOf(tblCouponDto.getDelayHours()));
        couponDetailDto.setFreeIntDays(String.valueOf(tblCouponDto.getFreeIntDay()));
        couponDetailDto.setActivityTotalAmt(String.valueOf(tblActivityDto.getTotalAmt()));

        couponDetailDto.setCouponTotalAmt(String.valueOf(tblCouponDto.getTotalAmt()));
        couponDetailDto.setCouponTotalNum(String.valueOf(tblCouponDto.getTotalNum()));
        couponDetailDto.setCouponGrantedAmt(String.valueOf(tblCouponDto.getGrantedAmt()));
        couponDetailDto.setCouponGrantedNum(String.valueOf(tblCouponDto.getGrantedNum()));

        // 活动信息
        couponDetailDto.setActivityDesc(tblActivityDto.getActivityDesc());
        couponDetailDto.setActivityAccountName(tblActivityDto.getMerchantCode());
        couponDetailDto.setActivityId(StringUtils.valueOf(tblActivityDto.getId()));
        couponDetailDto.setActivityCode(tblActivityDto.getActivityCode());
        couponDetailDto.setActivityName(tblActivityDto.getActivityName());
        couponDetailDto.setActivityTotalAmt(String.valueOf(tblActivityDto.getTotalAmt()));
        couponDetailDto.setActivityRemainAmt(String.valueOf(tblActivityDto.getActivityAccount()));
        couponDetailDto.setCouponSwitchStatus(String.valueOf(tblActivityDto.getSwitchStatus().getCode()));
        couponDetailDto.setNewAccount(String.valueOf(tblActivityDto.getNewAccount()));

        // 报警信息
        if(alarmDto != null) {
            couponDetailDto.setAccountAlarmReceiver(alarmDto.getReceiver());
            couponDetailDto.setAccountAlarmNoticeMethod(String.valueOf(alarmDto.getAlertMode()));
            couponDetailDto.setAccountAlarmExtEmailReceiver(alarmDto.getExtEmailReceiver());
            couponDetailDto.setAccountAlarmExtMobileReceiver(alarmDto.getExtMobileReceiver());
            couponDetailDto.setAccountAlarmNotifyInterval(String.valueOf(alarmDto.getNotifyInterval()));
            couponDetailDto.setAccountAlarmStartTime(alarmDto.getStartTime());
            couponDetailDto.setAccountAlarmEndTime(alarmDto.getEndTime());
            couponDetailDto.setAccountAlarmModel(alarmDto.getModel());
            couponDetailDto.setAccountAlarmAmount(String.valueOf(alarmDto.getAmount()));
            couponDetailDto.setAccountAlarmRemark(alarmDto.getRemark());
            couponDetailDto.setAccountAlarmUseStatus(alarmDto.getUseStatus());
        }

        // 辅助信息
        if(reviewInfo != null && StringUtils.isNotBlank(reviewInfo.getContentValue())) {
            CouponDetailDto reviewContent
                    = JSONUtils.getGson().fromJson(reviewInfo.getContentValue(), CouponDetailDto.class);
            couponDetailDto.setUserDesc(reviewContent.getUserDesc());
            couponDetailDto.setUserFeature(reviewContent.getUserFeature());
            couponDetailDto.setOwner(reviewContent.getOwner());
        }

        return couponDetailDto;
    }

    /**
     * 构造创券请求参数
     */
    public static TblCouponDto buildTblCouponDto(CouponDetailDto couponDetailDto) {
        TblCouponDto couponDto = new TblCouponDto();
        // code
        couponDto.setCouponCode(couponDetailDto.getCouponCode());
        couponDto.setActivityCode(couponDetailDto.getActivityCode());

        // 属性
        Integer couponType = Integer.valueOf(couponDetailDto.getCouponType());
        CouponTypeEnum couponTypeEnum = CouponTypeEnum.toEnum(couponType);
        couponDto.setCouponType(couponTypeEnum);
        couponDto.setCouponName(couponDetailDto.getCouponName());
        couponDto.setCouponDesc(couponDetailDto.getCouponDesc());
        couponDto.setUrl(couponDetailDto.getCouponUseUrl());
        couponDto.setInstructions(couponDetailDto.getInstructions());

        if(couponType.equals(CouponTypeEnum.RANDOM_REDUCE.getCode())){
            couponDto.setCouponScene("LOAN");
        }

        if(couponType.equals(CouponTypeEnum.CASH.getCode())){
            couponDto.setCouponScene("REPAY");
        }

        if(couponType.equals(CouponTypeEnum.FREE_INT.getCode())){
            couponDto.setCouponScene("REPAY");
        }

        // 金额
        if(StringUtils.isNotBlank(couponDetailDto.getDiscountRate())) {
            couponDto.setDiscountRate(new BigDecimal(couponDetailDto.getDiscountRate()));
        }

        if(StringUtils.isNotBlank(couponDetailDto.getCouponAmount())) {
            couponDto.setCouponAmount(new BigDecimal(couponDetailDto.getCouponAmount()));
        }

        if(StringUtils.isNotBlank(couponDetailDto.getFreeIntDays())) {
            couponDto.setFreeIntDay(Integer.valueOf(couponDetailDto.getFreeIntDays()));
        }


        couponDto.setTotalAmt(new BigDecimal(couponDetailDto.getCouponTotalAmt()));
        couponDto.setTotalNum(Integer.valueOf(couponDetailDto.getCouponTotalNum()));


        // 时间
        couponDto.setAvailableHours(Integer.valueOf(couponDetailDto.getAvailableDays()));
        couponDto.setStartTime(couponDetailDto.getCouponStartTime());
        couponDto.setEndTime(couponDetailDto.getCouponEndTime());


        // 通知与撤销
        couponDto.setUserPercept(Integer.valueOf(couponDetailDto.getNoticeUserByMsg()));
        couponDto.setAutoUnUse(CouponConstants.DEFAULT_NOT_AUTO_UNUSE);

        // ext
        JSONObject extObject = new JSONObject();
        extObject.put("msgUrl", couponDetailDto.getMsgUrl());
        extObject.put("couponContent", couponDetailDto.getMsgContent());
        extObject.put("ruleTips", couponDetailDto.getRuleTips());
        couponDto.setExt(extObject.toString());

        // 规则
        JSONObject ruleObject = new JSONObject();
        if(couponDetailDto.getUserGrantNumLimit()!=null) {
            ruleObject.put("userCouponNum", couponDetailDto.getUserGrantNumLimit());
        }

        if(couponDetailDto.getDayGrantNumLimit()!=null){
            ruleObject.put("activityCouponNum", couponDetailDto.getDayGrantNumLimit());
        }

        if(couponDetailDto.getUserUseNumLimit()!=null){
            ruleObject.put("userUseNum", couponDetailDto.getUserUseNumLimit());
        }

        if(couponDetailDto.getRepayIndexList()!=null){
            ruleObject.put("freePeriod", couponDetailDto.getRepayIndexList());
        }

        couponDto.setCouponDimon(UidType.toEnum(couponDetailDto.getCouponDimon()));
        couponDto.setRuleParams(ruleObject.toString());
        return couponDto;
    }

    /**
     * 构造请求营销创建活动参数
     */
    public static TblActivityDto buildTblActivityDto(CouponDetailDto couponDetailDto) {
        TblActivityDto activityDto = new TblActivityDto();
        activityDto.setActivityCode(couponDetailDto.getActivityCode());
        activityDto.setActivityName(couponDetailDto.getActivityName());
        activityDto.setActivityDesc(couponDetailDto.getActivityDesc());
        if(couponDetailDto.getActivityTotalAmt() !=null) {
            activityDto.setTotalAmt(new BigDecimal(couponDetailDto.getActivityTotalAmt()));
        }
        activityDto.setMerchantCode(couponDetailDto.getActivityAccountName());
        activityDto.setStartTime(couponDetailDto.getCouponStartTime());
        activityDto.setEndTime(couponDetailDto.getCouponEndTime());
        activityDto.setSwitchStatus(SwitchEnum.toEnum(Integer.valueOf(couponDetailDto.getCouponSwitchStatus())));
        return activityDto;
    }
}
