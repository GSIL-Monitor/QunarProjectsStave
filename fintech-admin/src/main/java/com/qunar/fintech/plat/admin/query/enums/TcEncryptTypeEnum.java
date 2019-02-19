package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author dw.wang
 * @since 2016-10-27
 */
public enum TcEncryptTypeEnum {
    IDENTITYNO("identityNo", "身份证"),
    CARDID("cardId", "卡号"),
    MOBILE("mobile", "手机号"),
    MAIL("mail", "邮箱"),
    PASSPORT("passport", "护照等"),
    ;
    private String key;
    private String desc;

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    private static Map<String, TcEncryptTypeEnum> map = Maps.newHashMap();

    static {
        for (TcEncryptTypeEnum status : TcEncryptTypeEnum.values()) {
            map.put(status.getKey(), status);
        }
    }
    /**
     * 判断errCode是否存在，是否可直接页面展示
     * @param errCode
     * @return
     */
    public static boolean isErrorExist(String errCode){
        return map.get(errCode)!=null;
    }

    private TcEncryptTypeEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}

