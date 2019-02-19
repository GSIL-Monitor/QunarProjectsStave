package com.qunar.fintech.plat.admin.contract.exception;

import com.google.common.base.MoreObjects;
import org.elasticsearch.common.Strings;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/20
 * @Despcription: 异常错误码
 */
public class ContractExceptionCode {

    public static final ContractExceptionCode PARAM_INVALID
            = new ContractExceptionCode("PARAM_INVALID", "参数非法");
    public static final ContractExceptionCode SQL_EXECUTE_ERROR
            = new ContractExceptionCode("SQL_EXECUTE_ERROR", "SQL执行错误");
    public static final ContractExceptionCode EXTERNEL_SYSTEM_ERROR
            = new ContractExceptionCode("EXTERNEL_SYSTEM_ERROR", "外部系统错误");

    public static ContractExceptionCode getExternalErrorCode(String errorMsg){
        if(Strings.isNullOrEmpty(errorMsg)){
            errorMsg = "外部系统错误";
        }
        return new ContractExceptionCode.Builder().setErrorCode("EXTERNEL_SYSTEM_ERROR").setErrorMsg(errorMsg).build();
    }
    private String errorCode;

    private String errorMsg;

    public ContractExceptionCode(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    public ContractExceptionCode() {}

    public static final class Builder{

        public ContractExceptionCode build(){
            return this.code;
        }

        public Builder setErrorMsg(String msg){
            this.code.errorMsg = msg;
            return this;
        }

        public Builder setErrorCode(String code){
            this.code.errorCode = code;
            return this;
        }
        private Builder(){
            code = new ContractExceptionCode();
        }

        private ContractExceptionCode code;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMsg", errorMsg)
                .toString();
    }
}
