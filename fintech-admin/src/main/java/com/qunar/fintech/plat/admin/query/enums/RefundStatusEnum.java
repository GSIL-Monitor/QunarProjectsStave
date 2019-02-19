package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 退款状态枚举
 * @author wei.duan
 *
 */
public enum RefundStatusEnum {

	INIT(0, "初始"),
	SUCCESS(1,"成功"),
    FAILED(2,"失败"),
    PROCESS(3,"处理中"),
    WAIT(4,"待发起");
	private Integer code;

    private String msg;

    private static final Map<Integer, RefundStatusEnum> enumMap = Maps.newHashMap();

    static {
        for (RefundStatusEnum statusEnum : RefundStatusEnum.values()) {
            enumMap.put(statusEnum.getCode(), statusEnum);
        }
    }

    private RefundStatusEnum(Integer code, String msg) {
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

    public static RefundStatusEnum toEnum(Integer code) {
        if(code == null)return null;
        return enumMap.get(code);
    }	
	
}
