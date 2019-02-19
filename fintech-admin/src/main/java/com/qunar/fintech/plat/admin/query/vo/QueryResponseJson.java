package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;


public class QueryResponseJson<T> extends ToString {

    private String rows;

    private int total;

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}