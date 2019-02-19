package com.qunar.fintech.plat.admin.newmarketing.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;

/**
 * @author qun.shi
 * @since 2019-02-09 6:50 PM
 */
public class ReviewNodeDetailDto extends ToString {

    private static final long serialVersionUID = -7275827884793938203L;

    private Long id;

    /**
     * 审核流水
     */
    private String reviewNo;

    /**
     * 审核人
     */
    private String reviewer;

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

    /**
     * 审核时间
     */
    private Date time;

    /**
     * 节点顺序
     */
    private Integer order;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
