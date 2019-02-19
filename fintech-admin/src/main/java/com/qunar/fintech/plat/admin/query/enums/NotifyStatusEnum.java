package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Author: ruijie.zheng
 * Date: 2017-12-08
 */
public enum NotifyStatusEnum {
    INIT(0, "初始"),
    SUCCESS(1, "通知成功"),
    FAIL(2, "通知失败"),
    PARTNER_SETTLE(3, "第三方已结清"),   //状态来源:用户在中行app/柜台等非qunar渠道还款,此时要给用户在qunar退款
    PARTNER_ERROR(4, "第三方内部异常"),  //状态来源:中行放款失败,没有生成还款计划,导致所有接口都无法处理,此时我方流程无法处理,也无需重试,需人工介入
    QUNAR_REFUND(5, "已还款退款"),       //状态来源:第三方已经结清的单子，从FPP发起还款退款.
    FPP_HANDLED(6, "运营已处理")         //状态来源:第三方
    ;

    private Integer code;
    private String desc;
    public static NotifyStatusEnum toEnum(Integer code){
        return enumMap.get(code);
    }

    public static final Integer EXCEPTION_NOTIFY_STATUS = 7;
    public static List<Integer> getExceptionStatusList() {
        return Lists.newArrayList(PARTNER_SETTLE.getCode(), PARTNER_ERROR.getCode());
    }

    public static boolean isExcptionStatus(Integer status) {
        return EXCEPTION_NOTIFY_STATUS.equals(status);
    }

    NotifyStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static Map<Integer, NotifyStatusEnum> enumMap = Maps.newHashMap();
    static {
        for (NotifyStatusEnum notifyStatusEnum : NotifyStatusEnum.values()) {
            enumMap.put(notifyStatusEnum.getCode(), notifyStatusEnum);
        }
    }

}
