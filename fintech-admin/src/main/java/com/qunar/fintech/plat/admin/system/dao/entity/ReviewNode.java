package com.qunar.fintech.plat.admin.system.dao.entity;

import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewNodeDetailDto;
import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;

/**
 * @author qun.shi
 * @since 2019-02-09 5:46 PM
 */
public class ReviewNode extends ToString {
    private static final long serialVersionUID = -62656619427200447L;

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
     * 审核的顺序
     */
   private Integer reviewOrder;

    /**
     * 发起审核菜单ID
     */
    private String reviewMenuId;

    /**
     * 审核人
     */
    private String reviewer;

    /**
     * 当前审核角色ID
     */
    private String curRoleId;

    /**
     * 下一个审核角色ID
     */
    private String nextRoleId;

    /**
     * 审核人角色名称
     */
    private String roleName;

    /**
     * 审核状态 初态:INIT，审核中:REVIEWING，通过:PASS，拒绝:REJECT
     * @see com.qunar.fintech.plat.admin.newmarketing.enums.ReviewNodeStatusEnum
     */
    private String status;

    /**
     * 操作理由
     */
    private String comment;

    private Date createTime;

    private Date updateTime;

    public Integer getReviewOrder() {
        return reviewOrder;
    }

    public void setReviewOrder(Integer reviewOrder) {
        this.reviewOrder = reviewOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(String reviewNo) {
        this.reviewNo = reviewNo;
    }

    public String getReviewMenuId() {
        return reviewMenuId;
    }

    public void setReviewMenuId(String reviewMenuId) {
        this.reviewMenuId = reviewMenuId;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getCurRoleId() {
        return curRoleId;
    }

    public void setCurRoleId(String curRoleId) {
        this.curRoleId = curRoleId;
    }

    public String getNextRoleId() {
        return nextRoleId;
    }

    public void setNextRoleId(String nextRoleId) {
        this.nextRoleId = nextRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    public ReviewNodeDetailDto buildReviewNodeResp(){
        ReviewNodeDetailDto reviewNodeDetailDto = new ReviewNodeDetailDto();
        reviewNodeDetailDto.setComment(this.comment);
        reviewNodeDetailDto.setId(this.id);
        reviewNodeDetailDto.setReviewer(this.reviewer);
        reviewNodeDetailDto.setReviewNo(this.reviewNo);
        reviewNodeDetailDto.setRoleName(this.roleName);
        reviewNodeDetailDto.setStatus(this.status);
        reviewNodeDetailDto.setTime(this.getUpdateTime());
        reviewNodeDetailDto.setOrder(this.getReviewOrder());
        return reviewNodeDetailDto;
    }
}
