package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Map;

public enum UserLimitStatusEnum {
    FORBID(0, "禁止"),
    Allow(1, "允许"),
    ;
    /* 状态 */
    private Integer code;
    /* 备注 */
    private String comment;

    private static Map<Integer, UserLimitStatusEnum> map = Maps.newHashMap();

    static {
        for (UserLimitStatusEnum code : UserLimitStatusEnum.values()) {
            map.put(code.getCode(), code);
        }
    }

    public static UserLimitStatusEnum valueOf(Integer state) {
        if (state == null || !map.containsKey(state)) {
            return null;
        }
        return map.get(state);
    }

    UserLimitStatusEnum(Integer code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserLimitStatusEnum{");
        sb.append("code=").append(code);
        sb.append(", comment='").append(comment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
