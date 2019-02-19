package com.qunar.fintech.plat.admin.query.enums;

/**
 * Created by bob.li on 2015/9/18.
 */
public enum AuditStatusEnum {
    INITIAL_STATUS(0, "初始状态"),
    AUDIT_SUCC(1, "审核通过"),
    AUDIT_FAIL(2, "审核拒绝"),
    CANCELLED(3, "主动取消");

    private Integer code;

    private String name;

    private AuditStatusEnum(Integer code, String name) {
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

    public static AuditStatusEnum toEnum(int code) {
        switch (code) {
            case 0:
                return INITIAL_STATUS;
            case 1:
                return AUDIT_SUCC;
            case 2:
                return AUDIT_FAIL;
            case 3:
                return CANCELLED;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "AuditStatusEnum{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
