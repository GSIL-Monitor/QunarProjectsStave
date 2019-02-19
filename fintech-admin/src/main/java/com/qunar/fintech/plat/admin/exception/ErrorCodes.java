package com.qunar.fintech.plat.admin.exception;

import com.qunar.pay.finance.qf.commons.api.exception.ApiErrorCode;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-04
 * Time: 18:18
 */
public class ErrorCodes {

    /*------------------  公共错误码  ------------------*/
    public final static ApiErrorCode UNKNOWN_EXCEPTION = new ApiErrorCode("UNKNOWN_EXCEPTION", "未知异常");

    public final static ApiErrorCode PARAM_EXCEPTION = new ApiErrorCode("PARAM_EXCEPTION", "参数异常");

    public final static ApiErrorCode CALL_DUBBO_ERROR = new ApiErrorCode("CALL_DUBBO_ERROR", "外部系统调用失败");
}
