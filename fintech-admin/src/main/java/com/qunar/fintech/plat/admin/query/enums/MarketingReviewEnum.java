package com.qunar.fintech.plat.admin.query.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: shiqun
 * Date: 2018-11-17
 * Time: 4:11 PM
 */
public enum MarketingReviewEnum {

    INIT(0, "待审核"),

    SUCC(1,"审核通过，生成数据或者更新数据"),

    FAIL(2, "审核不通过"),

    CHECKED(3,"通过初审");

    private final Integer code;

    private final String desc;

    private static final Map<Integer, OperateEnum> refers;

    static {
        Map<Integer, OperateEnum> temp = new HashMap<Integer, OperateEnum>();
        for (OperateEnum item : OperateEnum.values()) {
            temp.put(item.getCode(), item);
        }
        refers = Collections.unmodifiableMap(temp);
    }

    MarketingReviewEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static OperateEnum toEnum(int code) {
        return refers.get(code);
    }
}
