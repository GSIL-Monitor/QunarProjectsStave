/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


//--------------------- Change Logs----------------------
// <p>@author yth Initial Created at 2015年11月1日<p>
//-------------------------------------------------------
public enum CreditActivateStatusEnum {
    INIT(0,"初始状态"),ACTIVATING(1, "处理中"),SUCCESS(2, "成功"),FAILED(3, "失败");
    private Integer code;
    private String msg;
    private static Map<Integer, CreditActivateStatusEnum> typeMap = Maps.newHashMap();

    static {
        for (CreditActivateStatusEnum typeEnum : CreditActivateStatusEnum.values()) {
            typeMap.put(typeEnum.code, typeEnum);
        }
    }
    public static List<CreditActivateStatusEnum> getStatusCanUpSucc(){
    	return Arrays.asList(CreditActivateStatusEnum.INIT,
    			CreditActivateStatusEnum.ACTIVATING,CreditActivateStatusEnum.FAILED);
    }
    
    private CreditActivateStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static CreditActivateStatusEnum toEnum(int val) {
        if (typeMap.containsKey(val)) {
            return typeMap.get(val);
        }
        return null;
    }
    public Boolean isSuccess(){
        return this.equals(SUCCESS);
    }

    public boolean isFailed() {
        return FAILED.equals(this);
    }
}
