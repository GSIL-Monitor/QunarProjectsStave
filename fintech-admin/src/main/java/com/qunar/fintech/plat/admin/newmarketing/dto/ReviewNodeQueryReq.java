package com.qunar.fintech.plat.admin.newmarketing.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * @author qun.shi
 * @since 2019-02-12 1:41 PM
 */
public class ReviewNodeQueryReq extends ToString {
    private static final long serialVersionUID = 1683153263188181333L;

    /**
     * 审核流水
     */
    private String reviewNo;

    /**
     * 审核人
     */
    private String reviewer;

    /**
     * 审核状态 初态:INIT，审核中:REVIEWING，通过:PASS，拒绝:REJECT
     * @see com.qunar.fintech.plat.admin.newmarketing.enums.ReviewNodeStatusEnum
     */
    private String status;

    /**
     * 操作理由
     */
    private String comment;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
