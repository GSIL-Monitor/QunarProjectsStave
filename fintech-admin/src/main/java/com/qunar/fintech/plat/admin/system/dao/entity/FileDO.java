package com.qunar.fintech.plat.admin.system.dao.entity;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;

/**
 * 文件上传
 *
 * @author huanyunz
 * @email huanyun.zhou@qunar.com
 * @date 2017-09-19 16:02:20
 */
public class FileDO extends ToString {

    private static final long serialVersionUID = 1L;

    public FileDO() {
        super();
    }

    public FileDO(Integer type, String url, Date createTime) {
        super();
        this.type = type;
        this.url = url;
        this.createTime = createTime;
    }

    //
    private Long id;

    // 文件类型
    private Integer type;

    // URL地址
    private String url;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;


    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：文件类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：文件类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：URL地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：URL地址
     */
    public void setUrl(String url) {
        this.url = url;
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
