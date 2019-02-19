package com.qunar.fintech.plat.admin.query.enums;

/**
 * Created by bob.li on 2015/9/18.
 */
public enum RepayStatusEnum {
    NOT_REPAY(0, "未还款"),
    REPAYING(1, "还款中"),
    BANK_GOT(2, "银行已受理"),
    BANK_REPAYED(3, "银行已还款");

    private Integer code;

    private String name;

    private RepayStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RepayStatusEnum toEnum(int code) {
        switch (code) {
            case 0:
                return NOT_REPAY;
            case 1:
                return REPAYING;
            case 2:
                return BANK_GOT;
            case 3:
                return BANK_REPAYED;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "RepayStatusEnum{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
