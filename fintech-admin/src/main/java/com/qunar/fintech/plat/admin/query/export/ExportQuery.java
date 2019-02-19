package com.qunar.fintech.plat.admin.query.export;

public class ExportQuery<Q> {

    private Q query;

    public Q getQuery() {
        return query;
    }

    public void setQuery(Q query) {
        this.query = query;
    }
}
