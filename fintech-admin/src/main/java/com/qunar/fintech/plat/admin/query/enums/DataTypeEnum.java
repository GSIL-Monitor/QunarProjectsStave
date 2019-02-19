package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 证件类型枚举
 * @author zhongxing.wu
 * @since 2015-11-02
 */
public enum DataTypeEnum {
    IDENTITY_CARD(0, "IDENTITYCARD", "身份证"),

    PASSPORT(1, "PASSPORT", "护照"),

    OFFICER_CERT(2, "OFFICERCERT", "军官证"),

    HKM_PASSER(3, "HKMPASSER", "港澳通行证"),

    CARD_TYPE(4, "CARD", "银行卡"),

    MOBILE_TYPE(5, "MOBILE", "手机号"),

    EMAIL_TYPE(6, "EMAIL", "电子邮箱"),

    QUID_TYPE(7, "QUID", "QUNAR用户id"),

    OTHER_TYPE(99, "OTHER", "其他类型"), ;

    private static final Map<String, DataTypeEnum> typeMap = Maps.newHashMap();
    static {
        for (DataTypeEnum dtype : DataTypeEnum.values()) {
            typeMap.put(dtype.getMsg(), dtype);
        }
    }

    private int code;

    private String msg;

    private String desc;

    private DataTypeEnum(int code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    public String getMsg() {
        return msg;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }

    public static DataTypeEnum valueOf(Integer val) {
    	if(val==null) {
    		return IDENTITY_CARD;
    	}
        switch (val) {
            case 7:
                return QUID_TYPE;
            case 6:
                return EMAIL_TYPE;
            case 5:
                return MOBILE_TYPE;
            case 4:
                return CARD_TYPE;
            case 3:
                return HKM_PASSER;
            case 2:
                return OFFICER_CERT;
            case 1:
                return PASSPORT;
            case 0:
                return IDENTITY_CARD;
            default:
                return OTHER_TYPE;
        }
    }

    public static DataTypeEnum valueByStr(String val) {
        if (typeMap.containsKey(val)) {
            return typeMap.get(val);
        }
        return DataTypeEnum.IDENTITY_CARD;
    }

    public static DataTypeEnum idType2DataType(IdentityTypeEnum val) {
        return (val == null ? OTHER_TYPE : valueByStr(val.getCode()));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DateType{");
        sb.append("code=").append(code);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
