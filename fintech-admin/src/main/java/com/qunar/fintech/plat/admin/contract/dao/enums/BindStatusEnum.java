package com.qunar.fintech.plat.admin.contract.dao.enums;

import com.google.common.collect.Maps;

import java.util.Map;

public enum BindStatusEnum {

    UNBIND(0, "未绑定"),

    BIND(1, "已绑定");

	private Integer code;

    private String msg;

    private static final Map<Integer, BindStatusEnum> enumMap = Maps.newHashMap();

    static {
        for (BindStatusEnum statusEnum : BindStatusEnum.values()) {
            enumMap.put(statusEnum.getCode(), statusEnum);
        }
    }

    BindStatusEnum(Integer code, String msg) {
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

    public static BindStatusEnum toEnum(int code) {
    	BindStatusEnum statusEnum = enumMap.get(code);
        return statusEnum;
    }

    public boolean isBindSucc() {
        return BIND.equals(this);
    }

}
