package com.qunar.fintech.plat.admin.query.enums;

/**
 * 现金贷请求状态
 * Created by bob.li on 2016/7/28.
 */
public enum LoanCashReqStatusEnum {
    // 0:初始,1:成功,2:失败,3:处理中
    INIT(0,"初始"),
    SUCC(1,"成功"),
    FAIL(2,"失败"),
    PROCESSING(3,"处理中"),
    ;


    private Integer code;
    private String desc ;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    LoanCashReqStatusEnum(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public static boolean isSuccess(LoanCashReqStatusEnum status) {
        return SUCC.equals(status);
    }

    public static boolean isFaile(LoanCashReqStatusEnum status) {
        return FAIL.equals(status);
    }

    public static boolean isProcessing(LoanCashReqStatusEnum status) {
        return PROCESSING.equals(status);
    }

    public static boolean isFinalStatus(LoanCashReqStatusEnum status) {
        return SUCC.equals(status) || FAIL.equals(status);
    }

    public static LoanCashReqStatusEnum toEnum(Integer code) {
        if (code != null) {
            for (LoanCashReqStatusEnum statusEnum : LoanCashReqStatusEnum.values()) {
                if (statusEnum.getCode().compareTo(code) == 0)
                    return statusEnum;
            }
        }
        return null;
    }
}
