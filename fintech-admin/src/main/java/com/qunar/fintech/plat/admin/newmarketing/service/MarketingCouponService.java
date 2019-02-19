package com.qunar.fintech.plat.admin.newmarketing.service;

import com.qunar.fintech.plat.admin.newmarketing.dto.CouponDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.CouponQueryReq;

import java.util.List;

/**
 * @author qun.shi
 * @since 2019-01-30 1:30 AM
 */
public interface MarketingCouponService {
    /**
     * 创建优惠券：
     * 1、调营销创建活动信息 2、调营销创建优惠券信息 3、调账务创建报警配置
     */
    void add(CouponDetailDto couponDetailDto);

    /**
     * 更新优惠券
     * 1、调营销更新活动信息 2、调营销更新优惠券信息 3、调账务更新报警配置
     */
    void update(CouponDetailDto couponDetailDto);

    /**
     * 查询优惠券详情
     * 1、调营销查询活动信息 2、调营销查询优惠券信息 3、调账务查询报警配置
     */
    CouponDetailDto queryDetail(CouponDetailDto couponDetailDto);

    /**
     * 查询营销所有的优惠券
     * 1、调营销查询活动信息、券信息 2、调账务查询报警配置 3、组装优惠券信息，按条件过过滤
     */
    List<CouponDetailDto> getCouponList(CouponQueryReq couponQueryReq);
}
