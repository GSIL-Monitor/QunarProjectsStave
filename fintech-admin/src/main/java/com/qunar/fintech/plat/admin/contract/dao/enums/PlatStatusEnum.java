package com.qunar.fintech.plat.admin.contract.dao.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 平台状态
 */
public enum PlatStatusEnum {
    INVALID(0, "不可用"),

    VALID(1, "可用"),

    CLOSE(2,"关闭"),

    CANCEL(9,"作废");

    public static PlatStatusEnum toEnum(int val) {
        if (typeMap.containsKey(val)) {
            return typeMap.get(val);
        }
        return null;
    }

    PlatStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;

    private static Map<Integer, PlatStatusEnum> typeMap = Maps.newHashMap();

    static {
        for (PlatStatusEnum typeEnum : PlatStatusEnum.values()) {
            typeMap.put(typeEnum.code, typeEnum);
        }
    }

    public static boolean isValid(Integer code) {
        if (code == null) {
            return false;
        }
        return VALID.getCode().equals(code);
    }

    public static boolean isClose(Integer code) {
        if (code == null) {
            return false;
        }
        return CLOSE.getCode().equals(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String toString() {
        return this.name();
    }
}
