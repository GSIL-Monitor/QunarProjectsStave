package com.qunar.fintech.plat.admin.newmarketing.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * @author qun.shi
 * @since 2019-02-13 11:52 PM
 */
public class ReviewUpdateReq extends ToString {
    private static final long serialVersionUID = 6958641356039649535L;

    /**
     * 老的审核流水no
     */
    private String oldReviewNo;

    /**
     * 新的审核no
     */
    private String newReviewNo;

    /**
     * 审核内容
     */
    private String reviewContentValue;

    /**
     * 审核内容主键标识
     */
    private String reviewContentKey;

    public String getNewReviewNo() {
        return newReviewNo;
    }

    public void setNewReviewNo(String newReviewNo) {
        this.newReviewNo = newReviewNo;
    }

    public String getOldReviewNo() {
        return oldReviewNo;
    }

    public void setOldReviewNo(String oldReviewNo) {
        this.oldReviewNo = oldReviewNo;
    }

    public String getReviewContentValue() {
        return reviewContentValue;
    }

    public void setReviewContentValue(String reviewContentValue) {
        this.reviewContentValue = reviewContentValue;
    }

    public String getReviewContentKey() {
        return reviewContentKey;
    }

    public void setReviewContentKey(String reviewContentKey) {
        this.reviewContentKey = reviewContentKey;
    }
}
