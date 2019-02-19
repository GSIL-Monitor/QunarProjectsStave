package com.qunar.fintech.plat.admin.newmarketing.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.List;

/**
 * @author qun.shi
 * @since 2019-02-02 5:03 PM
 */
public class ReviewQueryReq extends ToString {

    private static final long serialVersionUID = -2965075621874284323L;

    /**
     * 决定如何过滤审核记录
     * @see com.qunar.fintech.plat.admin.newmarketing.enums.ReviewFilterTypeEnum
     */
    private Integer filterType;

    /**
     * 审核流水号
     */
    private List<String> reviewNos;

    /**
     * 当前登录用户
     */
    private String curLoginUserId;

    /**
     * 过滤审核具体内容
     */
    private CouponQueryReq couponQueryReq;

    public List<String> getReviewNos() {
        return reviewNos;
    }

    public void setReviewNos(List<String> reviewNos) {
        this.reviewNos = reviewNos;
    }

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public CouponQueryReq getCouponQueryReq() {
        return couponQueryReq;
    }

    public void setCouponQueryReq(CouponQueryReq couponQueryReq) {
        this.couponQueryReq = couponQueryReq;
    }

    public String getCurLoginUserId() {
        return curLoginUserId;
    }

    public void setCurLoginUserId(String curLoginUserId) {
        this.curLoginUserId = curLoginUserId;
    }
}
