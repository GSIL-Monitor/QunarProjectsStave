package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 转分期处理类型枚举
 * Author: sujie.liu
 * Time: 2016/1/12 17:34
 */
public enum TransProcessType {

    TRANSFER(0,"转分期", "TF"),
    LOAN(1,"贷款--债权转让","LN"),
    REPAY(2,"还款--债权转让", "RP"),
    ;

    /* 状态 */
    private Integer code;
    /* 备注 */
    private String msg;

    /* 简写(最大长度为两位)，暂用与生成分期处理流水 */
    private String shortName;

    private static Map<Integer, TransProcessType> map = Maps.newHashMap();

    static {
        for (TransProcessType status : TransProcessType.values()) {
            map.put(status.getCode(), status);
        }
    }

    /**
     * 判断是否债权转让类型
     * @return
     */
    public static boolean isLoan(TransProcessType processType){
        return LOAN.equals(processType);
    }


    public static boolean isRepay(TransProcessType processType){
        return REPAY.equals(processType);
    }

    public static TransProcessType valueOf(Integer code) {
        if (code == null || !map.containsKey(code)) {
            return null;
        }
        return map.get(code);
    }

    public static TransProcessType toEnum(int code) {
        if (map.containsKey(code)) {
            return map.get(code);
        } else {
            return null;
        }
    }

    TransProcessType(Integer code, String msg, String shortName) {
        this.code = code;
        this.msg = msg;
        this.shortName = shortName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
