package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.List;

/**
 * Created with Intellij IDEA.
 * Description:
 * User: weiduan
 */
public class QueryResponse <T> extends ToString {

    private List<T> rows;

    private int total;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
