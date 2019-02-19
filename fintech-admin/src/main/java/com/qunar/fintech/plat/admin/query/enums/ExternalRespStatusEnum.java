package com.qunar.fintech.plat.admin.query.enums;


/**
 * Created by yth on 16/2/17.
 */
public enum ExternalRespStatusEnum {
    /**
     * 没有请求第三方
     */
    NOREQ(0, "未请求第三方"),
    /**
     * 有响应
     */
    HAVERESP(1, "有响应");

    Integer code;
    String msg;

    ExternalRespStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ExternalRespStatusEnum toEnum(Integer code) {
        switch (code) {
            case 0:
                return ExternalRespStatusEnum.NOREQ;
            case 1:
                return ExternalRespStatusEnum.HAVERESP;
            default:
                return ExternalRespStatusEnum.NOREQ;
        }

    }

}
