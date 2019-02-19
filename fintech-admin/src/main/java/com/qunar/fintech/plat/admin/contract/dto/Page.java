package com.qunar.fintech.plat.admin.contract.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-06
 * Time: 20:37
 */
public class Page extends ToString {

    // 每页显示的条数
    protected int pageSize = 10;

    // 当前页码
    protected int pageNum = 0;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
