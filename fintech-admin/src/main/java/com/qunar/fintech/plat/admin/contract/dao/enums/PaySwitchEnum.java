/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package com.qunar.fintech.plat.admin.contract.dao.enums;

import com.google.common.collect.Maps;

import java.util.Map;

public enum PaySwitchEnum {

    CLOSE(0,"关闭"),

    OPEN(1,"开启");

    private static Map<Integer,PaySwitchEnum> contractStatuss = Maps.newHashMap();

    static {
        for(PaySwitchEnum contractStatus : PaySwitchEnum.values()){
            contractStatuss.put(contractStatus.getCode(), contractStatus);
        }
    }

    private Integer code;

    private String msg;

    private PaySwitchEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static PaySwitchEnum toEnum(Integer code){
        PaySwitchEnum paySwitchEnum = contractStatuss.get(code);
        return paySwitchEnum;
    }

    /**
     * <p>是否打开</p>
     * @return boolean
     */
    public boolean isOpen() {
    	return this.equals(OPEN);
    }

    /**
     * <p>是否打开</p>
     * @return boolean
     */
    public boolean isClose() {
    	return this.equals(CLOSE);
    }
}
