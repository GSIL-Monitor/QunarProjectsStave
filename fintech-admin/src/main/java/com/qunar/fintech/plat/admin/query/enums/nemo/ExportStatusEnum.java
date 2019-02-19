package com.qunar.fintech.plat.admin.query.enums.nemo;

public enum ExportStatusEnum {

    CREATE(0, "导出任务创建等待导出"),
    EXPORTING(1, "导出中"),
    SUCCESS(2, "导出成功"),
    FAIL(9, "导出失败"),
    INVALID(10,"文件失效");


    private Integer key;
    private String value;

    ExportStatusEnum(Integer key, String value) {
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

    public static ExportStatusEnum toEnum(Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("argument must be not null!");
        }
        for (ExportStatusEnum item : ExportStatusEnum.values()) {
            if (item.getKey().equals(code)) {
                return item;
            }
        }
        throw new IllegalArgumentException("the key of " + code + "has no correspondence!");
    }


}
