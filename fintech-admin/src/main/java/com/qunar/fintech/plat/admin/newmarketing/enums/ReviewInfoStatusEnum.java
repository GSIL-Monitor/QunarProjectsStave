package com.qunar.fintech.plat.admin.newmarketing.enums;

/**
 * @author qun.shi
 * @since 2019-01-31 8:02 PM
 */
public enum ReviewInfoStatusEnum {
    INIT("INIT","初始"),

    REVIEWING("REVIEWING","审核中"),

    PASS("PASS","审核通过"),

    REJECT("REJECT","审核拒绝"),

    PUBLISH_SUCC("PUBLISH_SUCC","发布成功"),

    PUBLISH_FAIL("PUBLISH_FAIL","发布失败");

    private String code;
    private String desc;

    ReviewInfoStatusEnum(String code, String desc) {
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
