package com.qunar.fintech.plat.admin.system.dto;

import com.qunar.fintech.plat.admin.system.dao.entity.MenuDO;
import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.List;

/**
 * Created with Intellij IDEA.
 * Description:
 * User: weiduan
 * Date: 2017-11-29
 */
public class MenuDto extends ToString {

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

    private List<Long> role;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public List<Long> getRole() {
        return role;
    }

    public void setRole(List<Long> role) {
        this.role = role;
    }

    public MenuDO getMenu(){
        MenuDO menu = new MenuDO();
        menu.setParentId(parentId);
        menu.setName(name);
        menu.setUrl(url);
        menu.setPerms(perms);
        menu.setType(type);
        menu.setIcon(icon);
        menu.setOrderNum(orderNum);
        return menu;
    }
}
