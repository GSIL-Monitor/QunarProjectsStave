package com.qunar.fintech.plat.admin.query.vo;

import java.util.List;

/**
 * 信任付账户查询响应类
 *
 * Created by baron.jiang on 2015/2/4.
 */
public class QueryIousAccountResponse {

    private List<QueryAccountRecord> queryAccountRecords;

    private Page page;

    public List<QueryAccountRecord> getQueryAccountRecords() {
        return queryAccountRecords;
    }

    public void setQueryAccountRecords(List<QueryAccountRecord> queryAccountRecords) {
        this.queryAccountRecords = queryAccountRecords;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "QueryIousAccountResponse{" +
                "queryAccountRecords=" + queryAccountRecords +
                ", page=" + page +
                '}';
    }
}
