package com.qunar.fintech.plat.admin.system.dao.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;

public class RoleMenuDO extends ToString {

    private Long id;

    private Long roleId;

    private Long menuId;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    public RoleMenuDO(){

    }

    public RoleMenuDO(Long roleId,Long menuId){
        this.roleId = roleId;
        this.menuId = menuId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
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
