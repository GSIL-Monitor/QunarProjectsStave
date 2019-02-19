package com.qunar.fintech.plat.admin.newmarketing.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * @author qun.shi
 * @since 2019-01-09 10:28 PM
 */
public class AccountAlarmQueryReq extends ToString {
    private static final long serialVersionUID = -5900524790331474741L;

    /**
     * 营销子账户：现金户名称 CIOUS2018Sale99
     */
    private String customerNo;

    /**
     * 活动id
     */
    private Long activityId;

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
