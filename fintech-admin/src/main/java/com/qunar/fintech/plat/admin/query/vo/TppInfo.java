package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * Created by bob.li on 2016/6/2.
 */
public class TppInfo extends ToString {

    private String tppCode;

    private String tppName;

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getTppName() {
        return tppName;
    }

    public void setTppName(String tppName) {
        this.tppName = tppName;
    }
}
