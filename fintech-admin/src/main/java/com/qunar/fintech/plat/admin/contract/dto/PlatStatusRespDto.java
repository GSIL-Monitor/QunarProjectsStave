package com.qunar.fintech.plat.admin.contract.dto;

public class PlatStatusRespDto extends BaseRespDto{

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
