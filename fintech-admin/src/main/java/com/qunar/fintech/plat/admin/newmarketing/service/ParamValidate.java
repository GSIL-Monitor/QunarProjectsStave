package com.qunar.fintech.plat.admin.newmarketing.service;

import com.qunar.fintech.plat.admin.newmarketing.dto.CouponDetailDto;

/**
 * @author qun.shi
 * @since 2019-01-30 2:53 AM
 */
public interface ParamValidate {

    /**
     * 校验券的配置信息
     */
    void checkCouponConfig(CouponDetailDto couponDetailDto);

    /**
     * check 活动配置
     */
    void checkActivityConfig(CouponDetailDto couponDetailDto);
}
