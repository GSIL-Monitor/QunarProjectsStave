package com.qunar.fintech.plat.admin.marketing.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;

public class InterestFreeCouponDto extends ToString {
    private static final long serialVersionUID = -5822476019694729874L;

    private Long id;
    /**
     * 渠道号
     *
     */
    private String orgChannel;
    /**
     * 产品号
     *
     */
    private String productNo;
    /**
     * 券面额
     */
    private BigDecimal couponAmount;
    /**
     * 活动code
     */
    private String activityCode;
    /**
     * 券编号code
     */
    private String couponCode;

    /**
     * 发券文件
     */
    private CommonsMultipartFile file;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }
}
