package com.qunar.fintech.plat.admin.system.dao.entity;

import com.qunar.fintech.plat.admin.newmarketing.dto.CouponDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewInfoDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewUpdateReq;
import com.qunar.fintech.plat.admin.newmarketing.enums.ReviewInfoStatusEnum;
import com.qunar.fintech.plat.admin.query.utils.JSONUtils;
import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;

/**
 * @author qun.shi
 * @since 2019-01-30 1:31 AM
 */
public class ReviewInfo extends ToString {

    private static final long serialVersionUID = -7088694560475902046L;

    private Long id;

    /**
     * 审核流水
     */
    private String reviewNo;

    /**
     * 流配置id
     */
    private String flowNo;

    /**
     * 发起审核的菜单id
     */
    private String reviewMenuId;

    /**
     * 发起审核的菜单名称
     */
    private String reviewMenuName;

    /**
     * 查询的菜单id
     */
    private String queryMenuId;

    /**
     * 审核内容的key：例如券code
     */
    private String contentKey;

    /**
     * 审核内容具体信息：例如券的信息
     */
    private String contentValue;

    /**
     * 提交人
     */
    private String submitter;

    /**
     * 当前审核角色
     */
    private String curRoleId;

    /**
     * 审核状态 初态:INIT，审核中:REVIEWING，通过:PASS，拒绝:REJECT，
     * 发布成功，PUBLISH_SUCC, 发布失败, PUBLISH_FAIL
     * @see com.qunar.fintech.plat.admin.newmarketing.enums.ReviewInfoStatusEnum
     */
    private String status;

    /**
     * 发布失败的原因
     */
    private String errmsg;

    /**
     * 最后一次审核的时间
     */
    private Date lastReviewTime;

    private Date createTime;

    private Date updateTime;

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getContentKey() {
        return contentKey;
    }

    public void setContentKey(String contentKey) {
        this.contentKey = contentKey;
    }

    public String getContentValue() {
        return contentValue;
    }

    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastReviewTime() {
        return lastReviewTime;
    }

    public void setLastReviewTime(Date lastReviewTime) {
        this.lastReviewTime = lastReviewTime;
    }

    public String getReviewMenuId() {
        return reviewMenuId;
    }

    public void setReviewMenuId(String reviewMenuId) {
        this.reviewMenuId = reviewMenuId;
    }

    public String getReviewMenuName() {
        return reviewMenuName;
    }

    public void setReviewMenuName(String reviewMenuName) {
        this.reviewMenuName = reviewMenuName;
    }

    public String getQueryMenuId() {
        return queryMenuId;
    }

    public void setQueryMenuId(String queryMenuId) {
        this.queryMenuId = queryMenuId;
    }

    public String getCurRoleId() {
        return curRoleId;
    }

    public void setCurRoleId(String curRoleId) {
        this.curRoleId = curRoleId;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(String reviewNo) {
        this.reviewNo = reviewNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    public ReviewInfoDetailDto buildReviewQueryResp(){
        // 券信息
        ReviewInfoDetailDto reviewInfoDetailDto
                = JSONUtils.getGson().fromJson(this.getContentValue(), ReviewInfoDetailDto.class);

        reviewInfoDetailDto.setStatus(this.getStatus());
        reviewInfoDetailDto.setReviewNo(this.getReviewNo());
        reviewInfoDetailDto.setRequestMenuid(this.getReviewMenuId());
        return reviewInfoDetailDto;
    }

    public static ReviewInfo buildReviewInfo(CouponDetailDto req){
        ReviewInfo reviewInfo = new ReviewInfo();
        reviewInfo.setReviewNo(req.getReviewNo());
        reviewInfo.setContentKey(req.getCouponCode());
        reviewInfo.setReviewMenuId(req.getRequestMenuId());
        reviewInfo.setContentValue(JSONUtils.getGson().toJson(req));
        reviewInfo.setStatus(ReviewInfoStatusEnum.INIT.getCode());
        return reviewInfo;
    }

    public static ReviewInfo buildReviewInfo(ReviewUpdateReq req,ReviewInfo oldReviewInfo){
        ReviewInfo reviewInfo = new ReviewInfo();
        reviewInfo.setReviewNo(req.getNewReviewNo());
        reviewInfo.setContentKey(req.getReviewContentKey());
        reviewInfo.setContentValue(req.getReviewContentValue());
        reviewInfo.setReviewMenuId(oldReviewInfo.getReviewMenuId());
        reviewInfo.setQueryMenuId(oldReviewInfo.getQueryMenuId());
        reviewInfo.setStatus(ReviewInfoStatusEnum.INIT.getCode());
        return reviewInfo;
    }
}
