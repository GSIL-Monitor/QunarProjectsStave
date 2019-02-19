package com.qunar.fintech.plat.admin.marketing.service.impl;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.qunar.fintech.marketing.api.admin.facade.CouponFacade;
import com.qunar.fintech.marketing.api.admin.model.CouponResponseDto;
import com.qunar.fintech.marketing.api.admin.model.TblCouponDto;
import com.qunar.fintech.plat.admin.marketing.dto.CouponReviewDto;
import com.qunar.fintech.plat.admin.marketing.service.MarketingReviewService;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.plat.admin.system.dao.entity.UserDO;
import com.qunar.fintech.plat.admin.system.dao.mapper.ReviewMapper;
import com.qunar.fintech.plat.admin.query.entity.MarketingCouponReq;
import com.qunar.fintech.plat.admin.system.dao.entity.ReviewDO;
import com.qunar.fintech.plat.admin.query.enums.CouponOperEnum;
import com.qunar.fintech.plat.admin.query.enums.MarketingReviewEnum;
import com.qunar.fintech.plat.admin.system.service.UserService;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qunar.fintech.plat.admin.query.utils.ExceptionEnum.DATABASE_ERROR;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: shiqun
 * Date: 2018-11-17
 * Time: 3:55 PM
 */
@Service
public class MarketingReviewServiceimpl implements MarketingReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketingReviewServiceimpl.class);

    @Autowired
    private CouponFacade couponFacade;

    @Autowired
    UserService userService;

    /**
     * 营销终审的角色id：初审角色
     */
    private static final Long MARKETING_FIRST_REVIEW_ROLE = 5L;

    /**
     * 营销初审的角色id：终审角色
     */
    private static final Long MARKETING_LAST_REVIEW_ROLE = 6L;

    @Override
    public R reject(String adminName,Long reviewId,String userId){
        LOGGER.info("MarketingReviewServiceimpl#reject adminName={},reviewId={},userId={}",adminName,reviewId,userId);

        // 用户包含的权限
        UserDO userDOWithRoles = queryUserRoles(userId);
        LOGGER.info("MarketingReviewServiceimpl#reject userDOWithRoles={}",userDOWithRoles);


        // 必须要拥有营销初审和营销终审的权限，才能拒绝的权利
        if(userDOWithRoles.getroleIds().contains(MARKETING_FIRST_REVIEW_ROLE)
                ||userDOWithRoles.getroleIds().contains(MARKETING_LAST_REVIEW_ROLE) ) {

            CouponReviewDto reviewer = queryReviewById(reviewId);

            // 更新审核表中的状态：不通过
            updateReviewStatus(reviewId, reviewer.getStatus(), MarketingReviewEnum.FAIL.getCode(), adminName);
            LOGGER.info("拒绝成功！！！");
            return R.ok("拒绝成功！！！");
        }

        LOGGER.info("你没有权限操作此流程！！！");
        return R.error(1,"你没有权限操作此流程！！！");
    }

    /**
     * 审核流程：包含初审和终审
     * @param adminName 审核人
     * @param reviewId 审核记录的id
     * @param userId 审核人 id
     * @return 审核结果
     */
    @Override
    public R pass(String adminName, Long reviewId, String userId) {
        LOGGER.info("MarketingReviewServiceimpl#pass adminName={},reviewId={},userId={}",adminName,reviewId,userId);

        // 没有获取到用户的userid
        if (userId == null) {
            return R.error(1, "你没有权限执行此操作！！！！");
        }

        // 用户包含的权限
        UserDO userDOWithRoles = queryUserRoles(userId);

        // 需要审核的数据
        CouponReviewDto reviewer = queryReviewById(reviewId);

        // 拥有初审的用户只能去更改审核状态（通过初审），但是不能生成券或者更新券
        if (userDOWithRoles.getroleIds().contains(MARKETING_FIRST_REVIEW_ROLE)) {
            if (!reviewer.getStatus().equals(MarketingReviewEnum.CHECKED.getCode())) {
                return firstReview(adminName, reviewId);
            }
        }

        // 用户终审的权限的用户，最终生成数据
        if (userDOWithRoles.getroleIds().contains(MARKETING_LAST_REVIEW_ROLE)) {
            if(reviewer.getStatus().equals(MarketingReviewEnum.CHECKED.getCode())) {
                return lastReview(adminName, reviewId, reviewer);
            }else{
                LOGGER.info("必须先通过初审！！！！");
                return R.error(1, "必须先通过初审！！！！");
            }
        }

        // 没有权限：有权直接处理，返回结果
        LOGGER.info("你没有权限执行此操作！！！！");
        return R.error(1, "你没有权限执行此操作！！！！");
    }

    @Override
    public void addCouponNeedToReview(CouponOperEnum operType,TblCouponDto couponDto,MarketingCouponReq req) {
        LOGGER.info("MarketingReviewServiceimpl#addReview CouponOperEnum={},TblCouponDto={},MarketingCouponReq={}",
                operType,couponDto,req);

        ReviewDO review = buildMarketingReview(operType,couponDto,req.getAdminName());
        try {
            if(reviewMapper.insert(review) < 1){
                LOGGER.error("插入一条待审核请求失败，key={}",couponDto.getCouponCode());
                throw new BusiException(DATABASE_ERROR.getErrCode(),"插入一条待审核请求失败");
            }
        }catch (Exception e){
            LOGGER.error("插入一条待审核请求失败，key={},msg={}",couponDto.getCouponCode(),e.getMessage());
            throw new BusiException(DATABASE_ERROR.getErrCode(),e.getMessage());
        }

        LOGGER.info("MarketingReviewServiceimpl#addReview, review={}",review);
    }

    /**
     * 初次审核
     * @param adminName 审核人
     * @param reviewId 审核记录ID
     * @return 初审结果
     */
    private R firstReview(String adminName, Long reviewId) {
        LOGGER.info("MarketingReviewServiceimpl#firstReview，adminName={},reviewId={}",adminName,reviewId);

        // 更新审核表中的状态：初审通过
        try {
            updateReviewStatus(reviewId, MarketingReviewEnum.INIT.getCode(), MarketingReviewEnum.CHECKED.getCode(), adminName);
        } catch (Exception e) {
            LOGGER.error("初审失败，e={}",e);
            return R.error(1, "初审失败");
        }

        LOGGER.info("初审成功！！！");
        return R.ok("初审成功");
    }

    /**
     * 终审流程：必须先初审通过，才能终审
     * @param adminName
     * @param reviewId
     * @return
     */
    private R lastReview(String adminName,Long reviewId,CouponReviewDto couponReviewDto){
        LOGGER.info("MarketingReviewServiceimpl#lastReview#adminName={},reviewId={},CouponReviewDto={}",adminName,reviewId,couponReviewDto);

        Gson gson = new Gson();
        TblCouponDto tblCouponDto = gson.fromJson(couponReviewDto.getContentValue(), TblCouponDto.class);

        // 当前需要审核记录的信息
        CouponReviewDto reviewer = queryReviewById(reviewId);

        // 初审人
        String reviewName = reviewer.getReviewUser();
        // 将初审的人和终审的人一起保存到数据库
        reviewName += "，" + adminName;

        QResponse<CouponResponseDto> response = null;
        try {
            // 将审核表中的信息更新到券表中
            if (couponReviewDto.getOperType().equals(0)) {
                //  如果是创建券请求，审核通过，生成一张券
                response = couponFacade.addCouponInfo(tblCouponDto);
            } else {
                // 如果是更新券请求，审核通过，更新券
                response = couponFacade.updateCouponInfo(tblCouponDto);
            }
        } catch (Exception e) {
            LOGGER.error("券更新失败，code={},msg={}", tblCouponDto.getCouponCode(), e.getMessage());
            return R.error(1, "券更新失败");
        }

        if (response != null && response.isSuccess()) {
            // 更新审核表中的状态：通过
            updateReviewStatus(reviewId, MarketingReviewEnum.CHECKED.getCode(), MarketingReviewEnum.SUCC.getCode(), reviewName);

            LOGGER.info("券更新成功,券NO={}",tblCouponDto.getCouponCode());
            return R.ok("券更新成功");
        } else {
            LOGGER.error("券更新失败，code={},msg={}", tblCouponDto.getCouponCode(), response.getReturnMsg());
            return R.error(1, response.getReturnMsg());
        }
    }

    /**
     * 更新审核状态：初审通过、终审通过、拒绝
     * @param id 审核记录id
     * @param sourceStatus 源状态
     * @param targetStatus 目标状态
     * @param reviewUser 审核人
     */
    @Override
    public void updateReviewStatus(Long id, Integer sourceStatus, Integer targetStatus,String reviewUser){
        LOGGER.info("updateReviewStatus, id = {},sourceStatus={},targetStatus={},reviewUser={}",
                id,sourceStatus,targetStatus,reviewUser);

        try {
            if(reviewMapper.updateReviewStatus(id,sourceStatus,targetStatus,reviewUser) < 1){
                LOGGER.error("更新审核状态失败，id={}",id);
                throw new BusiException(DATABASE_ERROR.getErrCode(),"更新审核状态失败");
            }
        }catch (Exception e){
            LOGGER.error("更新审核状态失败，id={}",id);
            throw new BusiException(DATABASE_ERROR.getErrCode(),e.getMessage());
        }

        LOGGER.info("updateReviewStatus 更新成功");
    }

    /**
     * 查询所有的任何记录
     * @return
     */
    @Override
    public List<CouponReviewDto> queryAllReviews(){
        List<ReviewDO> reviews = Lists.newArrayList();
        try{
            reviews = reviewMapper.selectAll();
        }catch (Exception e){
            LOGGER.error("查询所有审核信息失败，e ={}",e);
            throw new BusiException(DATABASE_ERROR.getErrCode(),e.getMessage());
        }

        LOGGER.info("reviews={}",reviews);
        return buildCouponReviewDtoList(reviews);
    }

    /**
     * 查询审核记录 by id
     * @param id
     * @return
     */
    @Override
    public CouponReviewDto queryReviewById(Long id){
        ReviewDO review = null;
        try{
            review = reviewMapper.queryReviewById(id);
        }catch (Exception e){
            LOGGER.error("查询某条审核记录失败，id={}",id);
            throw new BusiException(DATABASE_ERROR.getErrCode(),e.getMessage());
        }
        return buildCouponReviewDto(review);
    }

    /**
     * 根据用户的ID查询用户的权限
     * @param userId 用户id
     * @return 用户权限
     */
    private UserDO queryUserRoles(String userId){
        // 根据userid中查询用户的信息
        Map<String, Object> map = new HashMap<>();
        map.put("username", userId);
        List<UserDO> users = userService.list(map);

        // 获取当前用户
        UserDO userDOWithoutRoles = users.get(0);

        // 查询此用户的权限
        return userService.get(userDOWithoutRoles.getUserId());
    }

    private CouponReviewDto buildCouponReviewDto(ReviewDO review){
        CouponReviewDto couponReviewDto = new CouponReviewDto();
        couponReviewDto.setComment(review.getComment());
        couponReviewDto.setCommitUser(review.getCommitUser());
        couponReviewDto.setReviewUser(review.getReviewUser());
        couponReviewDto.setContentKey(review.getContentKey());
        couponReviewDto.setContentValue(review.getContentValue());
        couponReviewDto.setCreateTime(review.getCreateTime());
        couponReviewDto.setUpdateTime(review.getUpdateTime());
        couponReviewDto.setStatus(review.getStatus());
        couponReviewDto.setId(review.getId());
        couponReviewDto.setOperType(review.getOperType());
        return couponReviewDto;
    }

    private List<CouponReviewDto> buildCouponReviewDtoList(List<ReviewDO> reviews){
        List<CouponReviewDto> list = Lists.newArrayList();
        for (ReviewDO review:reviews) {
            CouponReviewDto couponReviewDto = buildCouponReviewDto(review);
            list.add(couponReviewDto);
        }
        return list;
    }

    /**
     * 构造一条券审核请求
     * @param couponDto
     * @param commitUser
     * @return
     */
    private ReviewDO buildMarketingReview(CouponOperEnum operType,TblCouponDto couponDto, String commitUser) {
        ReviewDO review = new ReviewDO();
        review.setContentKey(couponDto.getCouponCode());

        // 券详细信息
        Gson gson = new Gson();
        String jsonStr = gson.toJson(couponDto);
        review.setContentValue(jsonStr);

        // 待审核
        review.setStatus(MarketingReviewEnum.INIT.getCode());
        review.setCommitUser(commitUser);

        // 新建券
        review.setOperType(operType.getCode());
        return review;
    }
}
