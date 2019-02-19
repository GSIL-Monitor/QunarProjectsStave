package com.qunar.fintech.plat.admin.system.dao.enums;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-03
 * Time: 23:37
 */
public enum ProcStatusEnum {

    INIT(0, "初始"),

    PROCESSING(1, "处理中"),

    SUC(2, "成功"),

    FAIL(3, "失败");

    private Integer code;

    private String msg;

    ProcStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private static Map<Integer, ProcStatusEnum> statusMap = Maps.newHashMap();

    static {
        for (ProcStatusEnum typeEnum : ProcStatusEnum.values()) {
            statusMap.put(typeEnum.code, typeEnum);
        }
    }

    public static ProcStatusEnum toEnum(int code) {
        if (statusMap.containsKey(code)) {
            return statusMap.get(code);
        }
        return null;
    }

    /**
     * 获取终态
     */
    public static List<ProcStatusEnum> getFinishList() {
        return Lists.newArrayList(ProcStatusEnum.FAIL, ProcStatusEnum.SUC);
    }

    /**
     * 是否终态
     * @param procStatus
     */
    public static boolean inFinish(ProcStatusEnum procStatus) {
        return getFinishList().contains(procStatus);
    }

    /**
     * 是否是处理中
     * @param procStatus
     * @return
     */
    public static boolean inProc(ProcStatusEnum procStatus) {
        return PROCESSING.equals(procStatus);
    }

    /**
     * 是否是处理中
     * @param code
     * @return
     */
    public static boolean inProc(Integer code) {
        return PROCESSING.getCode().equals(code);
    }

    /**
     * 是否是成功
     * @param procStatus
     * @return
     */
    public static boolean inSuc(ProcStatusEnum procStatus) {
        return SUC.equals(procStatus);
    }

    /**
     * 是否是成功
     * @param code
     * @return
     */
    public static boolean inSuc(Integer code) {
        return SUC.getCode().equals(code);
    }

    /**
     * 是否是成功
     * @param procStatus
     * @return
     */
    public static boolean inFail(ProcStatusEnum procStatus) {
        return FAIL.equals(procStatus);
    }

    /**
     * 是否是成功
     * @param code
     * @return
     */
    public static boolean inFail(Integer code) {
        return FAIL.getCode().equals(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String toString() {
        return this.name();
    }
}
