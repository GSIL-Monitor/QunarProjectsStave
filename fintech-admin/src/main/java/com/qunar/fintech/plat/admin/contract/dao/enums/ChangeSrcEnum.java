package com.qunar.fintech.plat.admin.contract.dao.enums;

/**
 * Description: 变更来源类型
 * User: rengang.pei
 * Date: 2018-10-26
 * Time: 11:37
 */
public enum ChangeSrcEnum {

    USER_BIND("USER_BIND","用户首页发起绑定"),

    PAY_BIND("PAY_BIND","收银台绑定并支付"),

    SYS_BIND("SYS_BIND","系统操作"),

    SYNC_DATA_BIND("SYNC_DATA_BIND","同步数据绑定"),

    UP_NAME("UP_NAME","修改用户名"),

    UP_MOBILE("UP_MOBILE","修改手机号"),

    UP_IDCODE("UP_IDCODE","修改身份证号"),

    RM_USER_INFO("RM_USER_INFO","抹除实名");

    private String code;

    private String desc;

    ChangeSrcEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ChangeSrcEnum toEnum(String code){
        for (ChangeSrcEnum item : values()) {
            if(item.code.equals(code)){
                return item;
            }
        }
        return null;
    }

    public static boolean isUnbindChangeSrc(String code) {
        return RM_USER_INFO.getCode().equals(code) || UP_IDCODE.getCode().equals(code);
    }

    public static boolean isBindChangeSrc(String code) {
        return USER_BIND.getCode().equals(code) || PAY_BIND.getCode().equals(code)
                || SYS_BIND.getCode().equals(code) || SYNC_DATA_BIND.getCode().equals(code);
    }
}
