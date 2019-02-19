package com.qunar.fintech.plat.admin.newmarketing.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * @author qun.shi
 * @since 2019-02-12 7:18 PM
 */
public class GrantCouponReq extends ToString {
    private static final long serialVersionUID = 6393310363280254550L;

    /**
     * 券面额
     */
    private BigDecimal couponAmount;

    /**
     * 券code
     */
    private String couponCode;

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

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
