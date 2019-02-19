package com.qunar.fintech.plat.admin.query.enums;

/**
 * Created by guangtian on 17-9-8.
 */
public enum UserGroupEnum {
    NORMAL("NORMAL", 0, "普通用户"),
    STUDENT("STUDENT", 1, "校园用户"),
    JUNIOR("JUNIOR", 2, "初级版拿去花"),
    ;
    private String code;
    private String desc;
    private Integer dbValue;
    private UserGroupEnum(String code, Integer dbValue, String desc) {
        this.code = code;
        this.dbValue = dbValue;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }

    public Integer getDbValue() {
        return dbValue;
    }
    public static UserGroupEnum toEnum(String code) {
        for (UserGroupEnum e : UserGroupEnum.values()) {
            if (e.code.equals(code)) {
                return e;
            }
        }
        return UserGroupEnum.NORMAL;
    }

    public static UserGroupEnum toEnum(Integer dbValue) {
        for (UserGroupEnum e : UserGroupEnum.values()) {
            if (e.dbValue.equals(dbValue)) {
                return e;
            }
        }
        return UserGroupEnum.NORMAL;
    }

    /**
     * 是否是学生用户
     * @return
     */
    public boolean isStudent() {
        return UserGroupEnum.STUDENT.equals(this);
    }

    /**
     * 是否是初级版
     * @return
     */
    public boolean isJunior() {
        return UserGroupEnum.JUNIOR.equals(this);
    }

    /**
     * 是否是普通用户
     * @return
     */
    public boolean isNormal() {
        return UserGroupEnum.NORMAL.equals(this);
    }

    public String toString() {
        return this.code;
    }
}
