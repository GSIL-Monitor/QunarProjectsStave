package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.g2.utils.persistence.Entity;

import java.util.Date;

/**
 * @author dw.wang
 * @since 2016-12-12
 */
public class UserProductInfo implements Entity<Long> {
    private static final long serialVersionUID = 8168122171472775112L;

    /**
     * 主键id
     */
    private long id;

    /**
     * 证件类型
     */
    private String identityType;

    /**
     * 证件号码
     */
    private String identityCode;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 产品编号
     */
    private String productNo;
    /**
     * 用户编码
     */
    private String userId;
    /**
     * 用户所属机构
     */
    private String channelCode;
    /**
     * 主用户
     */
    private String mainUserId;
    /**
     * 主用户所属机构
     */
    private String mainChannelCode;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 账号类型
     *   NORMAL(0, "普通账号"), // 默认
     *   MAIN(1, "主账号"), // 表示含有子账号
     *   SUB(2,"子账号"), // 即交叉用户overlap
     */
    private Integer accType;

    /**
     * 账号绑定状态
     *   BIND_SUCC(0, "绑定成功"),
     *   UNBIND(1, "已解绑"),  // BindProcessStatusEnum 名称不能改变
     *   BIND_FAIL(2, "绑定失败"), // 收到Q后, 但与上次绑定的账号不是一个人
     */
    private Integer bindStatus;

    /**
     * 使用状态
     * CLOSED(0, "关闭"),
     * USING(1, "使用中"),
     * TEMP(78, "临时状态"),
     */
    private Integer useStatus;


    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long aLong) {

    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getMainUserId() {
        return mainUserId;
    }

    public void setMainUserId(String mainUserId) {
        this.mainUserId = mainUserId;
    }

    public String getMainChannelCode() {
        return mainChannelCode;
    }

    public void setMainChannelCode(String mainChannelCode) {
        this.mainChannelCode = mainChannelCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getAccType() {
        return accType;
    }

    public void setAccType(Integer accType) {
        this.accType = accType;
    }

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserProductInfo{");
        sb.append("id=").append(id);
        sb.append(", identityType='").append(identityType).append('\'');
        sb.append(", identityCode='").append(identityCode).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", productNo='").append(productNo).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", channelCode='").append(channelCode).append('\'');
        sb.append(", mainUserId='").append(mainUserId).append('\'');
        sb.append(", mainChannelCode='").append(mainChannelCode).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", accType=").append(accType);
        sb.append(", bindStatus=").append(bindStatus);
        sb.append(", useStatus=").append(useStatus);
        sb.append('}');
        return sb.toString();
    }
}
