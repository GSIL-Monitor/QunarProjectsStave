package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 支付开关枚举
 * @author sujie.liu
 *
 */
public enum PaySwitchEnum {

	OFF(0,"不可支付"),
	ON(1,"可支付");
	
	private Integer code;
	private String msg;
	private static final Map<Integer,PaySwitchEnum> enumMap = Maps.newHashMap();
	
	static{
		for(PaySwitchEnum statusEnum : PaySwitchEnum.values()){
			enumMap.put(statusEnum.getCode(), statusEnum);
		}
	}
	
	private PaySwitchEnum(int code, String msg){
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
		if(code == null)return null;
		return enumMap.get(code);
	}
	
}
