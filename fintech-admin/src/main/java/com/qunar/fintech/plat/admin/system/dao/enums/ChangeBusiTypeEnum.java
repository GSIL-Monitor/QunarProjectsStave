package com.qunar.fintech.plat.admin.system.dao.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Description: 业务变更类型
 * User: rengang.pei
 * Date: 2018-11-03
 * Time: 23:46
 */
public enum ChangeBusiTypeEnum {

    PLAT_STATUS_OPEN(1, "平台合同开启"),

    PLAT_STATUS_CLOSE(2, "平台合同关闭"),

    PAY_SWITCH_OPEN(3, "合同支付开关开启"),

    PAY_SWITCH_CLOSE(4, "合同支付开关关闭"),

    PLAT_BIND(5, "合同绑定");

    private Integer code;

    private String msg;

    ChangeBusiTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private static Map<Integer, ChangeBusiTypeEnum> enumMap = Maps.newHashMap();

    static {
        for (ChangeBusiTypeEnum item : ChangeBusiTypeEnum.values()) {
            enumMap.put(item.code, item);
        }
    }

    public static ChangeBusiTypeEnum toEnum(int code) {
        if (enumMap.containsKey(code)) {
            return enumMap.get(code);
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
