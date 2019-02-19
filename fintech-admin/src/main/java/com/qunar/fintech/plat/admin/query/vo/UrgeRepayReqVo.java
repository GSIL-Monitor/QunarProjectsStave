package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * @author dw.wang
 * @since 2017-03-02.
 */
public class UrgeRepayReqVo extends ToString {
    private static final long serialVersionUID = 6202216073509053570L;

    /**
     * 用户id
     */
    private String userId;
//    /**
//     * 贷款提供商
//     */
//    private String tppCode;
    /**
     * 贷款流水号
     */
    private String loanProvideNo;
    /**
     * 到期日
     */
    private String dueDate;
    /**
     * 产品码
     */
    private String productNo;
    /**
     * 渠道号
     */
    private String orgChannel;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

}
