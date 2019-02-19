package com.qunar.fintech.plat.admin.support.exception;

import com.qunar.pay.finance.qf.commons.api.exception.ApiErrorCode;

/**
 * Created with Intellij IDEA.
 * Description:
 * User: weiduan
 * Date: 2017-12-13
 * Time: 下午2:24
 */
public class MarketErrorCodes {

    public final static ApiErrorCode QUERY_ACCOUNT_ERROR = new ApiErrorCode("QUERY_ACCOUNT_ERROR", "获取现金账户金额错误");

    public final static ApiErrorCode QUERY_ACCOUNT_EXCE = new ApiErrorCode("QUERY_ACCOUNT_EXCE", "查询资金处理平台异常");

    public final static ApiErrorCode PARAM_ERROR = new ApiErrorCode("PARAM_ERROR", "参数错误");

    public final static ApiErrorCode UNKOWN_ERROR = new ApiErrorCode("UNKOWN_ERROR", "未知异常");
}
