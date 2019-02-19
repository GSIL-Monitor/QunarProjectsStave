package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.math.BigDecimal;
import java.util.Date;

public class BillInfo extends ToString {

    private Long id;

    private String userId;

    private String productNo;

    private String userGroup;

    private String orgChannel;

    private Integer billStatus;

    /* 账单流水号 唯一标识一笔账单 */
    private String billNo;

    /* 出账日期 */
    private Date billDate;

    /* 出账时本金 */
    private BigDecimal billPrcpAmount;

    /* 出账时利息 */
    private BigDecimal billIntAmount;

    /* 借据到期日 */
    private Date dueDate;
    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public Integer getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public BigDecimal getBillPrcpAmount() {
        return billPrcpAmount;
    }

    public void setBillPrcpAmount(BigDecimal billPrcpAmount) {
        this.billPrcpAmount = billPrcpAmount;
    }

    public BigDecimal getBillIntAmount() {
        return billIntAmount;
    }

    public void setBillIntAmount(BigDecimal billIntAmount) {
        this.billIntAmount = billIntAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}