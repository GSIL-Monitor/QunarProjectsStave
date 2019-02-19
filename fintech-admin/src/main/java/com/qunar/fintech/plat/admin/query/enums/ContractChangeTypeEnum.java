package com.qunar.fintech.plat.admin.query.enums;

/**
 * Created by zhanghe.zhang on 2017/12/8.
 */
public enum  ContractChangeTypeEnum {
    EXTEND_TERM(0, "展期变更"),
    AMT_RASIED(1, "提额变更"),
    CHANGE_MAIN_AMT(2,"更改用户额度"),
    FPP_CHANGE_STATUS(3,"fpp更改合同状态")
    ;
    private Integer code;
    private String desc;
    ContractChangeTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public Integer getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
    /**
     * 转枚举
     * @param code
     * @return
     */
    public static ContractChangeTypeEnum toEnum(Integer code) {
        for (ContractChangeTypeEnum s : ContractChangeTypeEnum.values()) {
            if (s.code.equals(code)) {
                return s;
            }
        }
        throw new IllegalArgumentException(String.format("the argument of %s have no correspondence enums!", code));
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContractChangeTypeEnum[");
        sb.append("code=").append(code);
        sb.append(", desc='").append(desc);
        sb.append(']');
        return sb.toString();
    }
}
