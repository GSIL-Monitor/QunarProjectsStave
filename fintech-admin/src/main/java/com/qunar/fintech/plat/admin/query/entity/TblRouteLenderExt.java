package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.finance.preloan.api.enums.ProductEnum;
import com.qunar.pay.g2.utils.persistence.Entity;

import java.util.Date;

/**
 * Created by baron.jiang on 16/8/17.
 */
public class TblRouteLenderExt implements Entity<Long> {
    private static final long serialVersionUID = -3355323855631984677L;

    /* 主键ID */
    private Long id;

    /* 产品类型 */
    private ProductEnum productNo;

    /* 通道编码 */
    private String tppCode;

    /**
     * 渠道编码
     * <p>
     *    可空
     * </p>
     */
    private String orgChannel;

    /* 指标名称 */
    private String indexKey;

    /* 指标值 */
    private String indexValue;

    /* 描述信息 */
    private String descInfo;

    /* 创建时间 */
    private Date createTime;

    /* 更新时间 */
    private Date updateTime;
    
    /**
     * 值类型
     */
    private String keyType;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public ProductEnum getProductNo() {
        return productNo;
    }

    public void setProductNo(ProductEnum productNo) {
        this.productNo = productNo;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getIndexKey() {
        return indexKey;
    }

    public void setIndexKey(String indexKey) {
        this.indexKey = indexKey;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue;
    }

    public String getDescInfo() {
        return descInfo;
    }

    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
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

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    @Override
    public String toString() {
        return "TblRouteLenderExt{" +
                "id=" + id +
                ", productNo=" + productNo +
                ", tppCode='" + tppCode + '\'' +
                ", orgChannel='" + orgChannel + '\'' +
                ", keyType='" + keyType + '\'' +
                ", indexKey='" + indexKey + '\'' +
                ", indexValue='" + indexValue + '\'' +
                ", descInfo='" + descInfo + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
