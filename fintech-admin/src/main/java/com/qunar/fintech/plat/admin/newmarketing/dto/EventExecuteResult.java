package com.qunar.fintech.plat.admin.newmarketing.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;

/**
 * @author qun.shi
 * @since 2019-02-02 1:54 PM
 */
public class EventExecuteResult extends ToString {
    private static final long serialVersionUID = -1682297126205053156L;
    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    public static EventExecuteResult createResult(String errCode, String errMsg) {
        EventExecuteResult eventExecuteResult = new EventExecuteResult();
        eventExecuteResult.setErrCode(errCode);
        eventExecuteResult.setErrMsg(errMsg);
        return eventExecuteResult;
    }

    /**
     * 是否发布成功
     */
    public boolean isSuccess() {
        return SUCCESS.equals(this.errCode);
    }

    /**
     * 发布失败
     */
    public static EventExecuteResult createFail(String errMsg) {
        return createResult(FAIL,errMsg);
    }

    /**
     * 发布成功
     */
    public static EventExecuteResult createSucc() {
        return createResult(SUCCESS, "成功");
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
