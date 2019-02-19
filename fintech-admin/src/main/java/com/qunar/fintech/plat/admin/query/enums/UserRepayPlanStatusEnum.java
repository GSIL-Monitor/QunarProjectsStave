package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by shuaifeng.gao on 16-9-20.
 */
public enum UserRepayPlanStatusEnum {
    INITED(0, "初始状态"),
    REPAY_SUCCESS(3, "还款成功"),
    ;

    private Integer code;
    private String name;

    public static boolean isNeedRepay(Integer status){
        return UserRepayPlanStatusEnum.INITED.getCode().equals(status);
    }

    public static boolean isRepaySucc(Integer status){
        return UserRepayPlanStatusEnum.REPAY_SUCCESS.getCode().equals(status);
    }

    public static boolean isRepaySucc(UserRepayPlanStatusEnum statusEnum){
        return UserRepayPlanStatusEnum.REPAY_SUCCESS.equals(statusEnum);
    }

    private static final Map<Integer, UserRepayPlanStatusEnum> enumMap = Maps.newHashMap();
    static{
        for(UserRepayPlanStatusEnum srcEnum : UserRepayPlanStatusEnum.values()){
            enumMap.put(srcEnum.getCode(), srcEnum);
        }
    }

    public static List<UserRepayPlanStatusEnum> obtainNotRepayStatus() {
        return Lists.newArrayList(INITED);
    }

    public static List<UserRepayPlanStatusEnum> obtainCanUpdateStatus() {
        return Lists.newArrayList(INITED);
    }

    public static UserRepayPlanStatusEnum toEnum(Integer code) {
        return enumMap.get(code);
    }

    UserRepayPlanStatusEnum(Integer code, String name) {
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
        return "UserRepayPlanStatusEnum{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
