package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * 页信息
 *
 * Created by lori.zhang on 2015/1/19.
 */
public class Page extends ToString {
    /* 每页显示的条数 */
    private int pageSize = 10;

    private int currentIndex=0;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
}
