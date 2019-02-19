package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by shuaifeng.gao on 16-9-20.
 */
public enum GatewayRepayStatusEnum {
    INIT(0, "未还款"),
    GATEWAY_SUCCESS(3, "还款成功"),
    ;

    private Integer code;
    private String name;

    public static boolean isGateSucc(GatewayRepayStatusEnum statusEnum){
        return GatewayRepayStatusEnum.GATEWAY_SUCCESS.equals(statusEnum);
    }

    public static boolean isGateSucc(Integer statusEnum){
        return GatewayRepayStatusEnum.GATEWAY_SUCCESS.getCode().equals(statusEnum);
    }

    private static final Map<Integer,GatewayRepayStatusEnum> enumMap = Maps.newHashMap();
    static{
        for(GatewayRepayStatusEnum srcEnum : GatewayRepayStatusEnum.values()){
            enumMap.put(srcEnum.getCode(), srcEnum);
        }
    }

    public static GatewayRepayStatusEnum toEnum(Integer code) {
        return enumMap.get(code);
    }

    GatewayRepayStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GatewayRepayStatusEnum{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
