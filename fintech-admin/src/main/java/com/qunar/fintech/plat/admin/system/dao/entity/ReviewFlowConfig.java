package com.qunar.fintech.plat.admin.system.dao.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;

/**
 * @author qun.shi
 * @since 2019-02-11 3:55 PM
 */
public class ReviewFlowConfig extends ToString {
    private static final long serialVersionUID = 5722945995942493252L;

    private Long id;

    /**
     * 审核流水配置no
     */
    private String flowNo;

    /**
     * 发起审核的菜单id
     */
    private String reviewMenuId;

    /**
     * 查询审核记录的菜单id
     */
    private String queryMenuId;

    /**
     * 发起审核的菜单名称
     */
    private String reviewMenuName;

    /**
     * 匹配规则
     */
    private String matchRule;

    /**
     * 审核流配置
     */
    private String flowDesc;

    /**
     * 审核流中包含的角色id，以逗号隔开
     */
    private String flowNodeRole;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    public String getReviewMenuId() {
        return reviewMenuId;
    }

    public void setReviewMenuId(String reviewMenuId) {
        this.reviewMenuId = reviewMenuId;
    }

    public String getQueryMenuId() {
        return queryMenuId;
    }

    public void setQueryMenuId(String queryMenuId) {
        this.queryMenuId = queryMenuId;
    }

    public String getReviewMenuName() {
        return reviewMenuName;
    }

    public void setReviewMenuName(String reviewMenuName) {
        this.reviewMenuName = reviewMenuName;
    }

    public String getMatchRule() {
        return matchRule;
    }

    public void setMatchRule(String matchRule) {
        this.matchRule = matchRule;
    }

    public String getFlowDesc() {
        return flowDesc;
    }

    public void setFlowDesc(String flowDesc) {
        this.flowDesc = flowDesc;
    }

    public String getFlowNodeRole() {
        return flowNodeRole;
    }

    public void setFlowNodeRole(String flowNodeRole) {
        this.flowNodeRole = flowNodeRole;
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
