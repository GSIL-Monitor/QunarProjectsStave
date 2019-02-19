package com.qunar.fintech.plat.admin.query.enums;

/**
 * Created by bob.li on 2015/9/18.
 */
public enum WithholdStatusEnum {
    INITIAL_STATUS(0, "初始状态"),
    OPERATING(1, "处理中"),
    WITHHOLD_SUCC(2, "成功"),
    WITHHOLD_FAIL(3, "失败"),
    WITHHOLD_PART_SUCC(4, "部分成功"),
    OVERRUN(5,"扣款金额超限"),
    NOASSET(6,"无资产可扣");

    private Integer code;

    private String name;

    private WithholdStatusEnum(Integer code, String name) {
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

    public static WithholdStatusEnum toEnum(int code) {
        switch (code) {
            case 0:
                return INITIAL_STATUS;
            case 1:
                return OPERATING;
            case 2:
                return WITHHOLD_SUCC;
            case 3:
                return WITHHOLD_FAIL;
            case 4:
                return WITHHOLD_PART_SUCC;
            case 5:
                return OVERRUN;
            case 6:
                return NOASSET;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "WithholdStatusEnum{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
