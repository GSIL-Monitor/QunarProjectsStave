package com.qunar.fintech.plat.admin.query.enums;

/**
 * 现金贷汇款状态枚举
 * Created by bob.li on 2016/7/28.
 */
public enum LoanCashRemitStatusEnum {
    // 0:初始,1:成功,2:失败,3:处理中
    INIT(0,"初始"),
    SUCC(1,"成功"),
    FAIL(2,"失败"),
    PROCESSING(3,"处理中"),
    LAUCHING(4,"待发起"),
    ;


    private Integer code;
    private String desc ;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    LoanCashRemitStatusEnum(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public static boolean isInit(LoanCashRemitStatusEnum status) {
        return INIT.equals(status);
    }

    public static boolean isSuccess(LoanCashRemitStatusEnum status) {
        return SUCC.equals(status);
    }

    public static boolean isFaile(LoanCashRemitStatusEnum status) {
        return FAIL.equals(status);
    }

    public static LoanCashRemitStatusEnum toEnum(Integer code) {
        if (code != null) {
            for (LoanCashRemitStatusEnum statusEnum : LoanCashRemitStatusEnum.values()) {
                if (statusEnum.getCode().compareTo(code) == 0)
                    return statusEnum;
            }
        }
        return null;
    }
}
