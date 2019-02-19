package com.qunar.fintech.plat.admin.contract.exception;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/20
 * @Despcription:
 */
public class ContractException extends RuntimeException{

    private String errorCode;
    private String errorMsg;

    private static final long serialVersionUID = 7556571947462996181L;

    public ContractException(Throwable cause) {
        super(cause);
    }

    public ContractException(String message) {
        super(message);
    }

    public ContractException(ContractExceptionCode contractExceptionCode) {
        super(contractExceptionCode.getErrorMsg());
        this.errorCode = contractExceptionCode.getErrorCode();
        this.errorMsg = contractExceptionCode.getErrorMsg();
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