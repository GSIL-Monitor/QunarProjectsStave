package com.qunar.fintech.plat.admin.query.vo;

import java.io.Serializable;

/**
 * 查询金融路由扩展开关信息记录
 *
 * Created by bob.li on 2015/8/11.
 */
public class QueryIousFinancialRouteExtSwitchRecord implements Serializable {

    /* 指标名称 */
    private String indexKey;

    /* 指标值 */
    private String indexValue;

    /* 描述信息 */
    private String descInfo;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QueryIousFinancialRouteExtSwitchRecord{");
        sb.append(", indexKey='").append(indexKey).append('\'');
        sb.append(", indexValue='").append(indexValue).append('\'');
        sb.append(", descInfo='").append(descInfo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
