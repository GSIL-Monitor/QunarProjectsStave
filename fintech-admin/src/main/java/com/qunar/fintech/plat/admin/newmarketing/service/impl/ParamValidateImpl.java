package com.qunar.fintech.plat.admin.newmarketing.service.impl;

import com.qunar.fintech.plat.admin.newmarketing.constants.CouponConstants;
import com.qunar.fintech.plat.admin.newmarketing.dto.CouponDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.service.ParamValidate;
import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import org.springframework.stereotype.Service;

/**
 * @author qun.shi
 * @since 2019-01-30 2:53 AM
 */
@Service
public class ParamValidateImpl implements ParamValidate {

    /**
     * check 活动配置
     */
    @Override
    public void checkActivityConfig(CouponDetailDto couponDetailDto){
        ParamChecker.notBlank(couponDetailDto.getActivityCode(), "活动code不能为空");
        ParamChecker.notBlank(couponDetailDto.getActivityName(), "活动名称不能为空");
        ParamChecker.notBlank(couponDetailDto.getActivityDesc(), "活动描述不能为空");
        ParamChecker.notBlank(couponDetailDto.getActivityTotalAmt(), "活动总金额不能为空");
        ParamChecker.notBlank(couponDetailDto.getActivityAccountName(), "营销账户不能为空");
        ParamChecker.notBlank(couponDetailDto.getCouponStartTime(), "优惠券开始时间不能为空");
        ParamChecker.notBlank(couponDetailDto.getCouponEndTime(), "优惠券结束时间不能为空");
    }

    /**
     * 校验券的配置信息
     */
    @Override
    public void checkCouponConfig(CouponDetailDto couponDetailDto){
        ParamChecker.notBlank(couponDetailDto.getCouponCode(),"券code不能为空！");
        ParamChecker.notBlank(couponDetailDto.getCouponName(),"券名称不能为空！");
        ParamChecker.notBlank(couponDetailDto.getUserFeature(),"用户特征不能为空！");
        ParamChecker.notBlank(couponDetailDto.getUserDesc(),"用户描述不能为空！");
        ParamChecker.notBlank(couponDetailDto.getInstructions(),"券使用说明不能为空！");

        ParamChecker.notBlank(couponDetailDto.getCouponEndTime(),"优惠券开始时间不能为空！");
        ParamChecker.notBlank(couponDetailDto.getCouponStartTime(),"优惠券结束时间不能为空！");

        ParamChecker.notBlank(couponDetailDto.getAvailableDays(),"券有效时长不能为空！");
        ParamChecker.notBlank(couponDetailDto.getRepayIndexList(),"可用期序不能为空！");
        ParamChecker.notBlank(couponDetailDto.getCouponDimon(),"券维度不能为空！");
        ParamChecker.notBlank(couponDetailDto.getCouponExpectedUsedNum(),"券预计使用数不能为空！");

        ParamChecker.notBlank(couponDetailDto.getCouponTotalAmt(),"券总金额不能为空！");
        ParamChecker.notBlank(couponDetailDto.getCouponTotalNum(),"券总数量不能为空！");

        ParamChecker.notBlank(couponDetailDto.getSupportProductNoList(),"业务线不能为空！");
        ParamChecker.notBlank(couponDetailDto.getSupportOrgChannelList(),"通道不能为空！");

        // 短信通知情况下，NoticeUserByMsg = 0，msgUrl和msgContent不能为空
        ParamChecker.notNull(couponDetailDto.getNoticeUserByMsg(),"是否短信通知用户不能为空！");
        if(couponDetailDto.getNoticeUserByMsg().equals(CouponConstants.USE_SMS_NOTICE_USER)){
            ParamChecker.notBlank(couponDetailDto.getMsgUrl(),"短信链接不能为空！");
            ParamChecker.notBlank(couponDetailDto.getMsgContent(),"短信内容不能为空！");
        }

        ParamChecker.notBlank(couponDetailDto.getNoticeQunarPublic(),"是否通知公共代金券不能为空！");
    }
}
