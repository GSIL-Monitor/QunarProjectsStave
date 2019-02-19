package com.qunar.fintech.plat.admin.system.dao.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;


/**
 * 部门管理
 *
 * @author huanyunz
 * @email huanyun.zhou@qunar.com
 * @date 2017-09-27 14:28:36
 */
public class DeptDO extends ToString {

    private static final long serialVersionUID = 1L;

    private Long id;
    //部门id
    private Long deptId;

    //上级部门ID，一级部门为0
    private Long parentId;

    //部门名称
    private String name;

    //排序
    private Integer orderNum;

    //是否删除  -1：已删除  0：正常
    private Integer delFlag;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    /**
     * 获取：
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 设置：
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取：上级部门ID，一级部门为0
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置：上级部门ID，一级部门为0
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：部门名称
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * 获取：是否删除  -1：已删除  0：正常
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置：是否删除  -1：已删除  0：正常
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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
