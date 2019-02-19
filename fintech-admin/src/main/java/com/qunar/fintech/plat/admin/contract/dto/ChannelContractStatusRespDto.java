package com.qunar.fintech.plat.admin.contract.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * Description: 通道合同状态修改结果
 * User: rengang.pei
 * Date: 2018-11-13
 * Time: 16:09
 */
public class ChannelContractStatusRespDto extends BaseRespDto{

    /**
     * 操作结果: true-成功; false-失败
     */
    private boolean result = false;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
