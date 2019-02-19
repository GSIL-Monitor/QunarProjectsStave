/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package com.qunar.fintech.plat.admin.query.enums;


//--------------------- Change Logs----------------------
// <p>@author yth Initial Created at 2015年11月3日<p>
//-------------------------------------------------------
public enum IdentityTypeEnum {
    IDENTITYCARD("IDENTITYCARD", "身份证"),
    PASSPORT("PASSPORT", "护照"),
    OFFICERCERT("OFFICERCERT", "军官证"),
    HKMPASSER("HKMPASSER", "港澳通行证"),
    UNKOWN("UNKNOWN","未知");

    
    private String code;
    private String desc;

    
    public String getCode() {
        return code;
    }

    
    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    IdentityTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static IdentityTypeEnum toEnum(String code) {
        for (IdentityTypeEnum item : IdentityTypeEnum.values()) {
            if (item.code.equals(code) ) {
                return item;
            }
        }
        return IdentityTypeEnum.UNKOWN;
    }
}
