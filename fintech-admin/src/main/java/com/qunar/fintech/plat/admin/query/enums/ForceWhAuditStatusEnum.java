package com.qunar.fintech.plat.admin.query.enums;/*
 *@(#)ForceWhAuditStatusEnum.java  1.0  2016-02-26 12:13  cheng.she 
 *
 * Copyright (c) 2016-2020 Qunar.com. All Rights Reserved.
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ForceWhAuditStatusEnum {
    INIT_STATUS(0, "待审核"),

    AUDIT_SUCCESS(1, "扣款中"),

    AUDIT_FAIL(2,"审核不通过"),

    FORCE_WITHHOLD_SUCCESS(3,"强扣成功"),

    FORCE_WITHHOLD_FAIL(4,"强扣失败"),

    USER_REPAYED(5,"用户已还款"),
    ;

    private final Integer code;

    private final String desc;

    private static final Map<Integer, ForceWhAuditStatusEnum> refers;

    static {
        Map<Integer, ForceWhAuditStatusEnum> temp = new HashMap<Integer, ForceWhAuditStatusEnum>();
        for (ForceWhAuditStatusEnum item : ForceWhAuditStatusEnum.values()) {
            temp.put(item.getCode(), item);
        }
        refers = Collections.unmodifiableMap(temp);
    }

    ForceWhAuditStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ForceWhAuditStatusEnum toEnum(int code) {
        return refers.get(code);
    }
}
