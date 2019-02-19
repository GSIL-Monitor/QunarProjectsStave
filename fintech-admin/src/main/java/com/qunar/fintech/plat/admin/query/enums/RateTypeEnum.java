/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Map;


//--------------------- Change Logs----------------------
// <p>@author yth Initial Created at 2015年11月1日<p>
//-------------------------------------------------------
public enum RateTypeEnum {
    DEFAULT(-1, "默认值"),MONTH(0, "月利率"),YEAR(1, "年利率"), DAY(2, "日利率");
    private Integer code;
    private String msg;
    private static Map<Integer, RateTypeEnum> typeMap = Maps.newHashMap();

    static {
        for (RateTypeEnum typeEnum : RateTypeEnum.values()) {
            typeMap.put(typeEnum.code, typeEnum);
        }
    }

    private RateTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static RateTypeEnum toEnum(int val) {
        if (typeMap.containsKey(val)) {
            return typeMap.get(val);
        }
        return null;
    }
}
