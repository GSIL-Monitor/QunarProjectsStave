package com.qunar.fintech.plat.admin.system.dao.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: shiqun
 * Date: 2018-11-17
 * Time: 2:12 PM
 */
public class ReviewDO {
    private Long id;

    /**
     * 例如couponCode
     */
    private String contentKey;

    /**
     * 例如券的信息
     */
    private String contentValue;

    /**
     * 审核的状态
     */
    private Integer status;

    /**
     * 评语
     */
    private String comment;

    /**
     * 审核人
     */
    private String reviewUser;

    /**
     * 提交人
     */
    private String commitUser;

    private Date createTime;
    private Date updateTime;

    /**
     * 操作类型，0：创建，1：更新
     */
    private Integer operType;

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public String getContentKey() {
        return contentKey;
    }

    public void setContentKey(String contentKey) {
        this.contentKey = contentKey;
    }

    public String getContentValue() {
        return contentValue;
    }

    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReviewUser() {
        return reviewUser;
    }

    public void setReviewUser(String reviewUser) {
        this.reviewUser = reviewUser;
    }

    public String getCommitUser() {
        return commitUser;
    }

    public void setCommitUser(String commitUser) {
        this.commitUser = commitUser;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
