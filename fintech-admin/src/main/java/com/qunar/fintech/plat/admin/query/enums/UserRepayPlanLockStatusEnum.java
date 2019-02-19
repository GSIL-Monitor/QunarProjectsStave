package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by shuaifeng.gao on 16-9-20.
 */
public enum UserRepayPlanLockStatusEnum {
    INIT(0, "未锁定"),
    REPAY_LOCK(1, "还款锁定"),
    REFUND_LOCK(2, "退款锁定"),
    TRANSFERRED_LOCK(3,"转分期锁定"),
    SAFE_REPAY_LOCK(4,"保还锁定"),
    ADVANCE_REPAY_LOCK(5,"提前还款锁定"),
            ;

    private Integer code;
    private String name;

    private static final Map<Integer,UserRepayPlanLockStatusEnum> enumMap = Maps.newHashMap();
    static{
        for(UserRepayPlanLockStatusEnum srcEnum : UserRepayPlanLockStatusEnum.values()){
            enumMap.put(srcEnum.getCode(), srcEnum);
        }
    }

    public static UserRepayPlanLockStatusEnum toEnum(Integer code) {
        return enumMap.get(code);
    }

    UserRepayPlanLockStatusEnum(Integer code, String name) {
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
        return "UserRepayPlanLockStatusEnum{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }

    public static boolean isLockStatus(Integer lockStatus) {
        return !isUnLock(lockStatus);
    }

    public static boolean isUnLock(Integer lockStatus) {
        return INIT.getCode().equals(lockStatus);
    }

    public static boolean isUnLock(UserRepayPlanLockStatusEnum lockStatus) {
        return INIT.equals(lockStatus);
    }
}
