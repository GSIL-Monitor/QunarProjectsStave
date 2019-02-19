package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 订单决策开关枚举类类
 *
 * Created by baron.jiang on 2015/12/11.
 */
public enum OrderStrategicSwitchEnum {

    OFF(0,"不参与订单决策"),
    ON(1,"参与订单决策");

    private Integer code;
    private String msg;
    private static final Map<Integer, OrderStrategicSwitchEnum> enumMap = Maps.newHashMap();

    static{
        for(OrderStrategicSwitchEnum orderStrategicSwitchEnum : OrderStrategicSwitchEnum.values()){
            enumMap.put(orderStrategicSwitchEnum.getCode(), orderStrategicSwitchEnum);
        }
    }

    private OrderStrategicSwitchEnum(int code, String msg){
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

    public static OrderStrategicSwitchEnum toEnum(Integer code){
        if(code == null)return null;
        return enumMap.get(code);
    }

}
