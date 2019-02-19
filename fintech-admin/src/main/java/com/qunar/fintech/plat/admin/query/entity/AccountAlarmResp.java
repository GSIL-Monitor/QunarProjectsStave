package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.fintech.plat.admin.marketing.dto.AccountAlarmDto;
import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.List;

/**
 * @author qun.shi
 * @since 2019-01-11 5:17 PM
 */
public class AccountAlarmResp extends ToString {
    private static final long serialVersionUID = 2427656722381259264L;

    private boolean ret;
    private String errcode;
    private String errmsg;
    private List<AccountAlarmDto> data;

    public boolean getRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<AccountAlarmDto> getData() {
        return data;
    }

    public void setData(List<AccountAlarmDto> data) {
        this.data = data;
    }
}
