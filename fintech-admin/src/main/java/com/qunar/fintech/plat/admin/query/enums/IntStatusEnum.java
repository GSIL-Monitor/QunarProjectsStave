package com.qunar.fintech.plat.admin.query.enums;

/**
 * Created by shuaifeng.gao on 16-9-20.
 */
public enum IntStatusEnum {
    NORMAL("NM","正常"),
    OVERDUE("OL","逾期");
    private String code;
    private String msg;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    private IntStatusEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
    /**
     * 判断输入状态是否为正常状态
     * @param status
     * @return
     */
    public static boolean isNormal(String status){
        if(IntStatusEnum.NORMAL.getCode().equals(status)){
            return true;
        }
        return false;
    }

    /**
     * 判断输入状态是否为逾期状态
     * @param status
     * @return
     */
    public static boolean isDue(String status){
        if(IntStatusEnum.OVERDUE.getCode().equals(status)){
            return true;
        }
        return false;
    }
    /**
     * 根据状态，返回状态描述信息，默认正常状态
     * @return
     */
    public static String toEnum(String code){
        IntStatusEnum[] statusList = IntStatusEnum.values();
        for(IntStatusEnum p : statusList){
            if(p.getCode().equals(code)){
                return p.getMsg();
            }
        }
        return IntStatusEnum.NORMAL.getMsg();
    }
}
