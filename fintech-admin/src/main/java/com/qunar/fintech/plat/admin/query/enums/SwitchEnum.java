package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 开关枚举
 * @author sujie.liu
 *
 */
public enum SwitchEnum {

	CLOSE(0, "关闭"),
	OPEN(1,"开启");
	private Integer code;

    private String msg;

    private static final Map<Integer, SwitchEnum> enumMap = Maps.newHashMap();

    static {
        for (SwitchEnum statusEnum : SwitchEnum.values()) {
            enumMap.put(statusEnum.getCode(), statusEnum);
        }
    }

    SwitchEnum(Integer code, String msg) {
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

    public static SwitchEnum toEnum(int code) {
    	SwitchEnum statusEnum = enumMap.get(code);
        return statusEnum;
    }	
	
}
