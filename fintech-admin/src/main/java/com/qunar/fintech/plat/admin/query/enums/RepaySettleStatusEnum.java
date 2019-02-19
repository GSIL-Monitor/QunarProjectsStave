package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by baron.jiang on 17/3/10.
 */
public enum RepaySettleStatusEnum {
    INIT(0, "初始"),        //未调结余试算
    SETTLING(1, "待结余"),  //调完结余试算,未调结余
    PROCESS(2, "处理中"),   //调完结余
    SUCCESS(3, "成功"),
    FAIL(4, "失败"),
    UNNECESSARY(5, "无需结余");

    private static final Map<Integer, RepaySettleStatusEnum> enumMap = Maps.newHashMap();

    static {
        for (RepaySettleStatusEnum typeEnum : RepaySettleStatusEnum.values()) {
            enumMap.put(typeEnum.getCode(), typeEnum);
        }
    }

    public static RepaySettleStatusEnum toEnum(Integer code) {
        return enumMap.get(code);
    }



    private Integer code;
    private String msg;

    RepaySettleStatusEnum(Integer code, String msg) {
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
}
