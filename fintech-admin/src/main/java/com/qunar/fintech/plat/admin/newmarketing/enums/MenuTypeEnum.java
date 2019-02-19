package com.qunar.fintech.plat.admin.newmarketing.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author qun.shi
 * @since 2019-02-02 2:05 PM
 */
public enum MenuTypeEnum {
    CREATE_COUPON(121,"创建优惠券"),
    UPDATE_COUPON(122,"更新优惠券");

    private Integer meunId;
    private String menuName;

    private static Map<Integer, MenuTypeEnum> typeMap = Maps.newHashMap();

    static {
        for (MenuTypeEnum typeEnum : MenuTypeEnum.values()) {
            typeMap.put(typeEnum.meunId, typeEnum);
        }
    }

    public static MenuTypeEnum toEnum(Integer meunId) {
        if (typeMap.containsKey(meunId)) {
            return typeMap.get(meunId);
        }
        return null;
    }

    MenuTypeEnum(Integer meunId, String menuName) {
        this.meunId = meunId;
        this.menuName = menuName;
    }

    public Integer getMeunId() {
        return meunId;
    }

    public void setMeunId(Integer meunId) {
        this.meunId = meunId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
