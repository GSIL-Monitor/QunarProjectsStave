package com.qunar.fintech.plat.admin.marketing.service;

import com.qunar.fintech.marketing.api.admin.model.TblCouponDto;
import com.qunar.fintech.plat.admin.marketing.dto.CouponReviewDto;
import com.qunar.fintech.plat.admin.query.entity.MarketingCouponReq;
import com.qunar.fintech.plat.admin.query.enums.CouponOperEnum;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.plat.admin.system.dao.entity.ReviewDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: shiqun
 * Date: 2018-11-17
 * Time: 3:56 PM
 */
public interface MarketingReviewService {

    /**
     * 插入一条待审核的数据
     * @param req
     * @return
     */
    void addCouponNeedToReview(CouponOperEnum operType,TblCouponDto couponDto, MarketingCouponReq req);

    /**
     * 查询所有的审核记录
     * @return
     */
    List<CouponReviewDto> queryAllReviews();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    CouponReviewDto queryReviewById(Long id);

    void updateReviewStatus(Long id, Integer sourceStatus, Integer targetStatus,String reviewUser);

    /**
     * 判断是否审核通过
     * @param adminName 审核人
     * @param reviewId 审核记录的id
     * @param userId 审核人 id
     * @return
     */
    R pass(String adminName,Long reviewId,String userId);

    /**
     * 判断是否审核通过
     * @param adminName 审核人
     * @param reviewId 审核记录的id
     * @param userId 审核人 id
     * @return
     */
    R reject(String adminName,Long reviewId,String userId);

}
