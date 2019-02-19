package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by bob.li on 2015/11/12.
 */
public enum EvaluateScoreKeyEnum {
    OPERAT_CASE_SCORE("OPERAT_CASE_SCORE", "运营情况评分"),
    INSTALLMENT_PROFITS_SCORE("INSTALLMENT_PROFITS_SCORE", "分期分成评分"),
    ACTIVATE_PROFITS_SCORE("ACTIVATE_PROFITS_SCORE", "激活用户付费评分"),
    ACTIVITY_SCORE("ACTIVITY_SCORE", "营销活动分");

    private String code;
    private String name;

    private static Map<String,EvaluateScoreKeyEnum> enumMap = Maps.newHashMap();

    static {
        for (EvaluateScoreKeyEnum statusEnum : EvaluateScoreKeyEnum.values()) {
            enumMap.put(statusEnum.getCode(), statusEnum);
        }
    }

    private EvaluateScoreKeyEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static EvaluateScoreKeyEnum toEnum(String code) {
        if (Strings.isNullOrEmpty(code)){
            return null;
        }
        return enumMap.get(code);
    }

    @Override
    public String toString() {
        return "AuditStatusEnum{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
