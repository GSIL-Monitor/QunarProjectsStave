package com.qunar.fintech.plat.admin.contract.dto;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-05
 * Time: 14:27
 */
public class PaySwitchRespDto extends BaseRespDto{

    /**
     * 操作结果：true-成功;false-失败
     */
    private boolean result = false;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
