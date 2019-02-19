package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created with Intellij IDEA.
 * Description:
 * User: weiduan
 * Date: 2018-01-05
 */
public class QueryDto extends ToString {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 身份证号
     */
    private String identity;

    /**
     * 用户uid
     */
    private String userId;

    /**
     * 业务来源
     */
    private String orgChannel;

    /**
     * 产品线
     */
    private String productNo;

    /**
     * 签约开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 签约结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 贷款提供方
     */
    private String tppCode;

    /**
     * 主渠道
     */
    private String mainChannel;

    private Integer pageSize;

    private Integer pageIndex;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
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

    public String getMainChannel() {
        return mainChannel;
    }

    public void setMainChannel(String mainChannel) {
        this.mainChannel = mainChannel;
    }
}
