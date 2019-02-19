package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 用户还款详情表 状态枚举
 *
 * @author yupei.wang
 * @since : 2016/1/6
 * @version 1.0
 */
public enum UserRepayExtStatusEnum {
    REPAY_INIT(0, "初始状态"),
    REPAY_SUCESS(1, "还款成功"),
    REPAY_FAIL(2, "还款失败"),
    REPAY_ING(3, "还款中"),
    ;

    private Integer code;
    private String msg;

    private static Map<Integer, UserRepayExtStatusEnum> typeMap = Maps.newHashMap();
    static {
        for (UserRepayExtStatusEnum typeEnum : UserRepayExtStatusEnum.values()) {
            typeMap.put(typeEnum.code, typeEnum);
        }
    }

    UserRepayExtStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static boolean isFailedStatus(UserRepayExtStatusEnum status){
        return UserRepayExtStatusEnum.REPAY_FAIL.equals(status);
    }
    public static boolean isFailedStatus(Integer status){
        return UserRepayExtStatusEnum.REPAY_FAIL.getCode().equals(status);
    }

    public static boolean isInitStatus(UserRepayExtStatusEnum status){
        return UserRepayExtStatusEnum.REPAY_INIT.equals(status);
    }

    public static boolean isInitStatus(Integer status){
        return UserRepayExtStatusEnum.REPAY_INIT.getCode().equals(status);
    }

    public static boolean isIngStatus(UserRepayExtStatusEnum status){
        return UserRepayExtStatusEnum.REPAY_ING.equals(status);
    }
    public static boolean isCanLaunchStatus(UserRepayExtStatusEnum status){
        return isInitStatus(status) || isFailedStatus(status);
    }

    public static boolean isCanLaunchStatus(Integer status){
        return isInitStatus(status) || isFailedStatus(status);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static UserRepayExtStatusEnum toEnum(Integer code) {
        return typeMap.get(code);
    }

}
