package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author dw.wang
 * @since 2016-10-26
 */
public enum FundFileTypeEnum {

    APPLY("APPLY", "募集文件"),
    PROFILE("PROFILE", "收益文件"),
    PRICE("PRICE", "行情文件");

    private String code;
    private String msg;

    private static Map<String, FundFileTypeEnum> typeMap = Maps.newHashMap();
    static {
        for (FundFileTypeEnum typeEnum : FundFileTypeEnum.values()) {
            typeMap.put(typeEnum.code, typeEnum);
        }
    }

    private FundFileTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static FundFileTypeEnum valueOf(int val) {
        if (typeMap.containsKey(val)) {
            return typeMap.get(val);
        }
        return null;
    }

    public static boolean isExportApplyFile(String fileType) {
        return FundFileTypeEnum.APPLY.getCode().equals(fileType);
    }
}
