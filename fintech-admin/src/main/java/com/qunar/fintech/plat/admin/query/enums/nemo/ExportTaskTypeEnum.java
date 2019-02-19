package com.qunar.fintech.plat.admin.query.enums.nemo;


public enum ExportTaskTypeEnum {
    PLATID_FIND_USER(1, "平台查找用户ID"),
    USER_FIND_PLATID(2, "用户ID查找平台"),
    CUSTOMERID_FIND_TPPID(3, "会员ID查找TppId"),
    TPPID_FIND_CUSTOMERID(4, "TppId查找会员ID");

    private Integer key;
    private String value;

    ExportTaskTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static ExportTaskTypeEnum toEnum(Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("argument must be not null!");
        }
        for (ExportTaskTypeEnum item : ExportTaskTypeEnum.values()) {
            if (item.getKey().equals(code)) {
                return item;
            }
        }
        throw new IllegalArgumentException("the key of " + code + "has no correspondence!");
    }


}
