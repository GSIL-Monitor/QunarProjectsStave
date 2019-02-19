package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 转债查询
 * @author xi.cheng
 * @since 2018-02-02.
 */
public class MinRepayReqVo extends ToString {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 贷款提供商
     */
    private String minRepayTpp;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 转债日期--起始
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    /**
     * 转债日期--终止
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    /**
     * 催收状态
     */
    private Integer processStatus;

    private Integer pageSize;

    private Integer pageIndex;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMinRepayTpp() {
        return minRepayTpp;
    }

    public void setMinRepayTpp(String minRepayTpp) {
        this.minRepayTpp = minRepayTpp;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}
