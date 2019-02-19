package com.qunar.fintech.plat.admin.query.enums;/*
 *@(#)OperateEnum.java  1.0  2016-02-26 15:21  cheng.she 
 *
 * Copyright (c) 2016-2020 Qunar.com. All Rights Reserved.
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum OperateEnum {

    AUDIT_PASS(0, "审核通过"),

    AUDIT_FAIL(1,"审核不通过");

    private final Integer code;

    private final String desc;

    private static final Map<Integer, OperateEnum> refers;

    static {
        Map<Integer, OperateEnum> temp = new HashMap<Integer, OperateEnum>();
        for (OperateEnum item : OperateEnum.values()) {
            temp.put(item.getCode(), item);
        }
        refers = Collections.unmodifiableMap(temp);
    }

    OperateEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static OperateEnum toEnum(int code) {
        return refers.get(code);
    }
}
