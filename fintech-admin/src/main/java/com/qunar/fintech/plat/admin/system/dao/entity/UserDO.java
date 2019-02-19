package com.qunar.fintech.plat.admin.system.dao.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;
import java.util.List;

public class UserDO extends ToString {

    private static final long serialVersionUID = 1L;

    public List<Long> getroleIds() {
        return roleIds;
    }

    public void setroleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    private Long id;

    //用户id
    private Long userId;

    // 用户名
    private String username;

    // 用户真实姓名
    private String name;

    // 密码
    private String password;

    // 部门
    private Long deptId;

    private String deptName;

    // 邮箱
    private String email;

    // 手机号
    private String mobile;

    // 状态 0:禁用，1:正常
    private Integer status;

    // 创建用户id
    private Long userIdCreate;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;

    //角色
    private List<Long> roleIds;

    /**
     * 获取：
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置：
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置：用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 获取：邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置：邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：状态 0:禁用，1:正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：状态 0:禁用，1:正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：创建用户id
     */
    public Long getUserIdCreate() {
        return userIdCreate;
    }

    /**
     * 设置：创建用户id
     */
    public void setUserIdCreate(Long userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("userId", userId)
                .add("username", username)
                .add("name", name)
                .add("password", "*****")
                .add("deptId", deptId)
                .add("deptName", deptName)
                .add("email", email)
                .add("mobile", mobile)
                .add("status", status)
                .add("userIdCreate", userIdCreate)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .add("roleIds", roleIds)
                .toString();
    }
}