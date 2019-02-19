package com.qunar.fintech.plat.admin.query.vo;


import java.io.Serializable;
import java.util.List;

/**
 * 查询现金贷打款信息的响应
 *
 * Created by xi.cheng on 2016/9/19.
 */
public class CashRemitResponse implements Serializable {
    private List<CashRemit> cashRemitList;

    private Page page;

    public List<CashRemit> getCashRemitList() {
        return cashRemitList;
    }

    public void setCashRemitList(List<CashRemit> cashRemitList) {
        this.cashRemitList = cashRemitList;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CashPlayMoneyResponse{");
        sb.append("cashPlayMoneyDtoList=").append(cashRemitList);
        sb.append(", page=").append(page);
        sb.append('}');
        return sb.toString();
    }
}
