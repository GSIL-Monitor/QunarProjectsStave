package com.qunar.fintech.plat.admin.newmarketing.enums;

/**
 * @author qun.shi
 * @since 2019-02-09 5:54 PM
 */
public enum ReviewNodeStatusEnum {
    INIT("INIT","初始"),

    REVIEWING("REVIEWING","审核中"),

    PASS("PASS","审核通过"),

    REJECT("REJECT","审核拒绝");

    private String code;
    private String desc;

    ReviewNodeStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
