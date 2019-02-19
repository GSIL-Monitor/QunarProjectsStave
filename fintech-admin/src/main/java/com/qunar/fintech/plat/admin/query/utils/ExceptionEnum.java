package com.qunar.fintech.plat.admin.query.utils;

/**
 * @author dw.wang
 * @since 2016-11-01
 */
public enum ExceptionEnum {

    SUCCESS("000000", "SUCCESS", "成功"),
    FAILED("999999", "FAILED", "失败"),

    /**
     * 10 系统、参数、数据库操作相关异常类
     */
    SYS_ERROR("100001","SYS_ERROR","系统异常"),
    ARGUMENT_ERROR("100002","ARGUMENT_ERROR","参数错误"),
    DATE_FORMAT_ERROR("100003","DATE_FORMAT_ERROR","日期格式错误"),
    AMOUNT_NOT_ENOUGH("100004","AMOUNT_NOT_ENOUGH","金额不足"),
    AMOUNT_INVALID("100005", "AMOUNT_INVALID", "金额有误"),
    GENERATE_QUNARTRADENO_ERROR("100006", "GENERATE_QUNARTRADENO_ERROR", "生成qunar_trade_no错误"),
    NULL_PARAMETER("100007", "NULL_PARAMETER", "参数为空"),
    RECORD_STATUS_ERROR("100008", "RECORD_STATUS_ERROR", "数据库记录的状态错误"),

    DATABASE_SELECT_ERROR("100008", "DATABASE_SELECT_ERROR", "查询数据库异常"),
    DATABASE_UPDATE_ERROR("100009", "DATABASE_UPDATE_ERROR", "更新数据库异常"),
    DATABASE_INSERT_ERROR("100010", "DATABASE_INSERT_ERROR", "插入数据库异常"),
    DATABASE_ERROR("100011", "DATABASE_ERROR", "插入数据库异常"),

    DATE_FOMART_ERROR("100012", "DATE_FOMART_ERROR", "日期转换错误"),
    HMAC_CHECK_ERROR("100013", "HMAC_CHECK_ERROR", "hmac错误，鉴签失败"),
    NOT_EQUAL_ERROR("100014", "NOT_EQUAL_ERROR", "参数不等值"),
    BASE64_ENCODE_ERROR("100015", "BASE64_ENCODE_ERROR", "base64加密出错"),
    BASE64_DECODE_ERROR("100016", "BASE64_DECODE_ERROR", "base64解密出错"),
    STORE_DECODE_ERROR("100017", "STORE_DECODE_ERROR", "安全存储解密出错"),
    VACATION_DATE_INVALID("100018", "VACATION_DATE_INVALID", "该日期不在假期数据中或出错"),
    NETWORK_ERROR("100019", "NETWORK_ERROR", "网络异常"),
    HMAC_SIGN_ERROR("100020", "HMAC_SIGN_ERROR", "hmac错误，加签失败"),
    Q_CONFIG_ERROR("100021", "Q_CONFIG_ERROR", "QConfig配置错误"),

    /**
     * 账务交互相关异常
     */
    ACCOUNT_SYS_ERROR("900001", "ACCOUNT_SYS_ERROR", "账务系统异常，无数据返回"),
    ACCOUNT_ERROR("900002", "ACCOUNT_ERROR", "账务请求结果为失败"),

    DUBBO_ERROR("2700003", "DUBBO_ERROR", "调用dubbo接口异常"),

    /**
     * 贷后交互相关
     */
    REFUND_ERROR("300001,", "REFUND_ERROR", "退款异常"),
    ;

    private String errCode;
    private String errMsg;
    private String errChineseMsg;

    public String getErrCode() {
        return errCode;
    }
    public String getErrMsg() {
        return errMsg;
    }
    public String getErrChineseMsg() {
        return errChineseMsg;
    }

    private ExceptionEnum(String errCode, String errMsg, String errChineseMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.errChineseMsg = errChineseMsg;
    }

}

