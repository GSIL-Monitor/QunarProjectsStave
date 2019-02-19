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
public enum CreditReqStatusEnum {
    CREDITING(0, "授信中"),
    SUCCESS(1, "成功"), 
    FAILED(2, "失败"), 
    ;
    private Integer code;
    private String msg;
    private static Map<Integer, CreditReqStatusEnum> typeMap = Maps.newHashMap();

    static {
        for (CreditReqStatusEnum typeEnum : CreditReqStatusEnum.values()) {
            typeMap.put(typeEnum.code, typeEnum);
        }
    }

    private CreditReqStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static CreditReqStatusEnum toEnum(int val) {
        if (typeMap.containsKey(val)) {
            return typeMap.get(val);
        }
        return null;
    }

    public  boolean isSuccess(){
        return this.equals(SUCCESS);
    }

    public  boolean isFailed(){
        return this.equals(FAILED);
    }

    /**
     * 授信处理中
     *
     * @return
     */
    public  boolean isCrediting(){
        return this.equals(CREDITING);
    }
}
