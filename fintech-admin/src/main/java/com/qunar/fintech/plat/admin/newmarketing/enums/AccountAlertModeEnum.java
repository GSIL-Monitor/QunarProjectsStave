package com.qunar.fintech.plat.admin.newmarketing.enums;

/**
 * @author qun.shi
 * @since 2019-01-10 10:17 PM
 */
public enum AccountAlertModeEnum {
    QTALK(1, "qtalk通知"),

    SMS(2, "短信"),

    QMQ(4, "QMQ通知"),

    EMAIL(8, "邮件通知"),

    QTALK_SMS(3,"qtalk和短信"),

    QTALK_QMQ(5,"qtalk和qmq"),

    SMS_QMQ(6,"短息和qmq"),

    QTALK_SMS_QMQ(7,"qtalk、短信和qmq"),

    QTALK_EMAIL(9,"qtalk和email"),

    SMS_EMAIL(10,"短信和email"),

    QTALK_SMS_EMAIL(11,"qtalk、短信和邮件"),

    QMQ_EMAIL(12,"qmq和email"),

    QTALK_QMQ_EMAIL(13,"qtalk、qmq和邮件"),

    SMS_QMQ_EMAIL(14,"短信、qmq和邮件"),

    QTALK_SMS_QMQ_SMS(15,"全部");

    private Integer code;

    private String name;

    AccountAlertModeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 判断上层传的模式是否支持
     * @param code
     * @return
     */
    public static boolean supportModel(Integer code) {
        for (AccountAlertModeEnum s : AccountAlertModeEnum.values()) {
            if (s.code.equals(code)) {
                return true;
            }
        }
        return false;
    }
}
