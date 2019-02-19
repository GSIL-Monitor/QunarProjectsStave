package com.qunar.fintech.plat.admin.bruce.enums;

/**
 * Created with IntelliJ IDEA
 * Description: statistic_type 对应的状态
 * User: xian.cheng
 * Date: 2019-01-13
 * Time: 17:47
 */
public enum StatisticTypeEnum {
    DAILY_LOAN_BALANCE(0, "贷款金额"),
    DAILY_REPAYMENT_BALANCE(1, "还款金额"),
    LOAN_BALANCE(2, "在贷余额");

    private Integer code;

    private String name;

    private StatisticTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static StatisticTypeEnum toEnum(int code) {
        switch (code) {
            case 0:
                return DAILY_LOAN_BALANCE;
            case 1:
                return DAILY_REPAYMENT_BALANCE;
            case 2:
                return LOAN_BALANCE;
            default:
                return null;
        }
    }
}
