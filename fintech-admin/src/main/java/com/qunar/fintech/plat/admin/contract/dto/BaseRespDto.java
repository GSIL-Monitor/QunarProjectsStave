package com.qunar.fintech.plat.admin.contract.dto;

import com.qunar.pay.finance.qf.commons.api.model.ToString;
import com.qunar.pay.g2.utils.common.StringUtils;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-05
 * Time: 14:37
 */
public class BaseRespDto extends ToString {

    /**
     * 请求响应成功的返回码
     */
    public static final String SUCCESS = "000000";

    /**
     * 请求响应失败的返回码
     */
    public static final String FAIL = "999999";

    /**
     * 请求响应码 字符型 如果返回码为000000则表示成功
     */
    protected String returnCode;

    /**
     * 请求响应消息 字符型
     */
    protected String returnMsg;

    protected String errorCode;

    protected String errorMsg;

    /**
     * 是否请求成功
     */
    public boolean isReqSuccess() {
        return StringUtils.equals(SUCCESS,returnCode);
    }

    /**
     * 判断业务是否成功（如：某银行通道是否可用）
     */
    public boolean isRespSuccess(){
        return StringUtils.equals(SUCCESS,errorCode);
    }

    /**
     * 一次性判断请求成果是否可用
     */
    public boolean isSucess() {
        return StringUtils.equals(SUCCESS,returnCode);
    }

    /**
     * 设置请求成功
     */
    public void setReqSuccess(){
        this.returnCode=SUCCESS;
    }

    /**
     * 设置请求失败
     */
    public void setReqFail(){
        this.returnCode=FAIL;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
