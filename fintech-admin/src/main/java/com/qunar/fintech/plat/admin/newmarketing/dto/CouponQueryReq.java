package com.qunar.fintech.plat.admin.newmarketing.dto;

import com.qunar.fintech.util.simple.DateUtils;
import com.qunar.pay.finance.qf.commons.api.model.ToString;
import com.qunar.pay.finance.qf.commons.utils.base.StringUtils;

import java.math.BigDecimal;

/**
 * @author qun.shi
 * @since 2019-01-30 9:15 PM
 */
public class CouponQueryReq extends ToString {
    private static final long serialVersionUID = 6145322932213650166L;

    /**
     * 活动code
     */
    private String couponCode;

    /**
     * 业务线
     */
    private String productionNo;

    /**
     * 券类型
     * @see com.qunar.fintech.marketing.api.enums.CouponTypeEnum
     */
    private String couponType;

    /**
     * 券的开始时间
     */
    private String couponStartTime;

    /**
     * 券的结束时间
     */
    private String couponEndTime;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 券负责人
     */
    private String couponOwner;

    /**
     * 活动最小总金额
     */
    private BigDecimal activityMinTotalAmt;

    /**
     * 活动最大总金额
     */
    private BigDecimal activityMaxTotalAmt;

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getProductionNo() {
        return productionNo;
    }

    public void setProductionNo(String productionNo) {
        this.productionNo = productionNo;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getCouponStartTime() {
        return couponStartTime;
    }

    public void setCouponStartTime(String couponStartTime) {
        this.couponStartTime = couponStartTime;
    }

    public String getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(String couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCouponOwner() {
        return couponOwner;
    }

    public void setCouponOwner(String couponOwner) {
        this.couponOwner = couponOwner;
    }

    public BigDecimal getActivityMinTotalAmt() {
        return activityMinTotalAmt;
    }

    public void setActivityMinTotalAmt(BigDecimal activityMinTotalAmt) {
        this.activityMinTotalAmt = activityMinTotalAmt;
    }

    public BigDecimal getActivityMaxTotalAmt() {
        return activityMaxTotalAmt;
    }

    public void setActivityMaxTotalAmt(BigDecimal activityMaxTotalAmt) {
        this.activityMaxTotalAmt = activityMaxTotalAmt;
    }

    public boolean match(CouponDetailDto couponDetailDto){
        if(StringUtils.isNotBlank(this.couponCode)){
            if(!this.couponCode.equals(couponDetailDto.getCouponCode())){
                return false;
            }
        }

        if(StringUtils.isNotBlank(this.activityName)){
            if(!this.activityName.equals(couponDetailDto.getActivityName())){
                return false;
            }
        }

        if(StringUtils.isNotBlank(this.couponOwner)){
            if(!this.couponOwner.equals(couponDetailDto.getOwner())){
                return false;
            }
        }

        if(StringUtils.isNotBlank(this.productionNo)){
            if(!this.productionNo.equals(couponDetailDto.getSupportProductNoList())){
                return false;
            }
        }

        if(StringUtils.isNotBlank(this.couponType)){
            if(!Integer.valueOf(this.couponType).equals(couponDetailDto.getCouponType())){
                return false;
            }
        }

        if(StringUtils.isNotBlank(this.getCouponStartTime())){
            if(DateUtils.firstBeforeSecond(DateUtils.str2date(this.getCouponStartTime(), DateUtils.FORMATTYPE1),
                    DateUtils.str2date(couponDetailDto.getCouponStartTime(),DateUtils.FORMATTYPE1))){
                return false;
            }
        }

        if(StringUtils.isNotBlank(this.getCouponEndTime())){
            if(DateUtils.firstBeforeSecond(DateUtils.str2date(couponDetailDto.getCouponEndTime(),
                    DateUtils.FORMATTYPE1), DateUtils.str2date(this.getCouponEndTime(),DateUtils.FORMATTYPE1))){
                return false;
            }
        }

        return true;
    }

    public boolean match(ReviewInfoDetailDto reviewInfoDetailDto){
        if(StringUtils.isNotBlank(this.couponCode)){
            if(!this.couponCode.equals(reviewInfoDetailDto.getCouponCode())){
                return false;
            }
        }

        if(StringUtils.isNotBlank(this.activityName)){
            if(!this.activityName.equals(reviewInfoDetailDto.getActivityName())){
                return false;
            }
        }

        if(StringUtils.isNotBlank(this.couponOwner)){
            if(!this.couponOwner.equals(reviewInfoDetailDto.getOwner())){
                return false;
            }
        }

        if(StringUtils.isNotBlank(this.productionNo)){
            if(!this.productionNo.equals(reviewInfoDetailDto.getSupportProductNoList())){
                return false;
            }
        }

        if(StringUtils.isNotBlank(this.couponType)){
            if(!Integer.valueOf(this.couponType).equals(reviewInfoDetailDto.getCouponType())){
                return false;
            }
        }

        if(StringUtils.isNotBlank(this.getCouponStartTime())){
            if(DateUtils.firstBeforeSecond(DateUtils.str2date(this.getCouponStartTime(), DateUtils.FORMATTYPE1),
                    DateUtils.str2date(reviewInfoDetailDto.getCouponStartTime(),DateUtils.FORMATTYPE1))){
                return false;
            }
        }

        if(StringUtils.isNotBlank(this.getCouponEndTime())){
            if(DateUtils.firstBeforeSecond(DateUtils.str2date(reviewInfoDetailDto.getCouponEndTime(),
                    DateUtils.FORMATTYPE1), DateUtils.str2date(this.getCouponEndTime(),DateUtils.FORMATTYPE1))){
                return false;
            }
        }

        return true;
    }
}
