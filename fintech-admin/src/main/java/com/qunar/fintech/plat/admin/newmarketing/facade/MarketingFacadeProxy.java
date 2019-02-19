package com.qunar.fintech.plat.admin.newmarketing.facade;

import com.qunar.fintech.marketing.api.admin.model.ActivityQueryDto;
import com.qunar.fintech.marketing.api.admin.model.CouponQueryDto;
import com.qunar.fintech.marketing.api.admin.model.TblActivityDto;
import com.qunar.fintech.marketing.api.admin.model.TblCouponDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.GrantCouponReq;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author qun.shi
 * @since 2019-01-31 1:02 AM
 */
public interface MarketingFacadeProxy {

    /**
     * 发券
     */
    int grantCoupon(MultipartFile[] userInfoFiles, GrantCouponReq req);

    /**
     * 创建营销活动
     */
    void addActivity(TblActivityDto tblActivityDto);

    /**
     * 创建优惠券
     */
    void addCoupon(TblCouponDto tblCouponDto);

    /**
     * 查询营销所有的活动
     */
    List<TblActivityDto> queryAllActivity();

    /**
     * 查询营销所有的活动
     */
    List<TblCouponDto> queryAllCouponInfo(String activityCode);

    /**
     * 根据活动id查询活动信息
     */
    TblActivityDto queryActivityById(ActivityQueryDto activityQueryDto);

    /**
     * 更新营销活动信息
     */
    void updateActivity(TblActivityDto tblActivityDto);

    /**
     * 更新券信息
     */
    void updateCoupon(TblCouponDto couponDto);

    /**
     * 根据id查询优惠券信息
     */
    TblCouponDto queryCouponInfoById(long id);
}
