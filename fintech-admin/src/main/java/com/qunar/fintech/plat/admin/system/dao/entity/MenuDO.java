package com.qunar.fintech.plat.admin.system.dao.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;

public class MenuDO extends ToString {

    private static final long serialVersionUID = 1L;

    private Long id;
    //菜单id
    private Long menuId;

    // 父菜单ID，一级菜单为0
    private Long parentId;

    // 菜单名称
    private String name;

    // 菜单URL
    private String url;

    // 授权(多个用逗号分隔，如：user:list,user:create)
    private String perms;

    // 类型 0：目录 1：菜单 2：按钮
    private Integer type;

    // 菜单图标
    private String icon;

    // 排序
    private Integer orderNum;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;

    /**
     * 获取：
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * 设置：
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取：父菜单ID，一级菜单为0
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置：父菜单ID，一级菜单为0
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：菜单URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：菜单URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：授权(多个用逗号分隔，如：user:list,user:create)
     */
    public String getPerms() {
        return perms;
    }

    /**
     * 设置：授权(多个用逗号分隔，如：user:list,user:create)
     */
    public void setPerms(String perms) {
        this.perms = perms;
    }

    /**
     * 获取：类型 0：目录 1：菜单 2：按钮
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：类型 0：目录 1：菜单 2：按钮
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：菜单图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置：菜单图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取：排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置：排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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
}
