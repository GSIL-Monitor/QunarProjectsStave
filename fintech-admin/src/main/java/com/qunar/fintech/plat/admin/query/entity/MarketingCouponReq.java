package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.fintech.marketing.api.admin.model.TblCouponDto;
import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: shiqun
 * Date: 2018-11-17
 * Time: 4:50 PM
 */
public class MarketingCouponReq extends ToString {

    /**
     * 操作人
     */
    private String adminName;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
