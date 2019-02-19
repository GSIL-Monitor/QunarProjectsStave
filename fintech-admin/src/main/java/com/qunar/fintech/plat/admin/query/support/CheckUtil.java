package com.qunar.fintech.plat.admin.query.support;

import com.qunar.fintech.util.http.HttpRes;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.exception.CommonApiErrorCodes;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import org.apache.commons.lang.StringUtils;

/**
 * Created by lori.zhang on 2015/3/15.
 */
public class CheckUtil {

    public static void checkDubboResponse(QResponse response) {
        if (response == null || !response.isSuccess()) {
            throw new BusiException(CommonApiErrorCodes.SYSTEM_ERROR);
        }
    }

    public static void validDubboResponse(QResponse response) {
        if (response == null) {
            throw new BusiException(CommonApiErrorCodes.SYSTEM_ERROR);
        }
        if (!response.isSuccess()) {
            String errCode = response.getReturnCode();
            String errMsg = response.getReturnMsg();
            errCode = StringUtils.isEmpty(errCode) ? CommonApiErrorCodes.SYSTEM_ERROR.getErrCode() : errCode;
            errMsg = StringUtils.isEmpty(errMsg) ? CommonApiErrorCodes.SYSTEM_ERROR.getErrMsg() : errMsg;
            throw new BusiException(errCode, errMsg);
        }
    }

    public static void validHttpResponse(HttpRes httpRes) {
        if (httpRes == null) {
            throw new BusiException(CommonApiErrorCodes.SYSTEM_ERROR);
        }
        if (!httpRes.isSucc()) {
            String errCode = httpRes.getErrorCode();
            String errMsg = httpRes.getErrorMsg();
            errCode = StringUtils.isEmpty(errCode) ? CommonApiErrorCodes.SYSTEM_ERROR.getErrCode() : errCode;
            errMsg = StringUtils.isEmpty(errMsg) ? CommonApiErrorCodes.SYSTEM_ERROR.getErrMsg() : errMsg;
            throw new BusiException(errCode, errMsg);
        }
    }
}
