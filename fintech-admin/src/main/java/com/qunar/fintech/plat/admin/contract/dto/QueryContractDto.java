package com.qunar.fintech.plat.admin.contract.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/21
 * @Despcription: 账户查询数据类
 */
public class QueryContractDto extends ToString{

    /**
     * 自然人customId
     */
    private String customId;


    /**
     * 产品线
     */
    private String productNo;

    /**
     * 贷款提供方
     */
    private String tppCode;

    private Integer pageSize;

    private Integer pageIndex;

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}
