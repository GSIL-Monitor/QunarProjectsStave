package com.qunar.fintech.plat.admin.query.vo;

import java.io.Serializable;

/**
 * Created by bob.li on 2015/9/22.
 */
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 6641633980307519149L;

    /**
     * 请求响应成功的返回码
     */
    public static final String SUCCESS = "000000";

    /**
     * 响应失败的返回码
     */
    public static final String FAIL = "999999";

    protected String returnCode; // 请求响应码 字符型 如果返回码为000000则表示成功
    protected String returnMsg; // 请求响应消息 字符型

    protected String errorCode;
    protected String errorMsg;

    /**
     * 判断是否请求正常（加签，调用第三方接口网络异常会在returnCode和returnMsg体现）
     */
    public boolean isReqSuccess() {
        return SUCCESS.equals(returnCode);
    }

    /**
     * 判断业务是否成功（如：某银行通道是否可用）
     */
    public boolean isRespSuccess() {
        return SUCCESS.equals(errorCode);
    }

    /**
     * 一次性判断请求成果是否可用
     */
    public boolean isSucess() {
        return SUCCESS.equals(this.getReturnCode());
    }

    public static BaseResponse newFail(String errorMsg){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setReturnCode(FAIL);
        baseResponse.setReturnMsg(errorMsg);
        return baseResponse;
    }

    public static BaseResponse newSucc(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setReturnCode(SUCCESS);
        return baseResponse;
    }

    public void setSuccessReq() {
        this.returnCode = SUCCESS;
    }

    public void setFailReq() {
        this.returnCode = FAIL;
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

    @Override
    public String toString() {
        return "IousBaseRespDto [returnCode=" + returnCode + ", returnMsg="
                + returnMsg + ", errorCode=" + errorCode + ", errorMsg="
                + errorMsg + "]";
    }
}

