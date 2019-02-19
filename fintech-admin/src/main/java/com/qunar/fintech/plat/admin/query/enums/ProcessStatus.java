package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 转分期处理状态--业务系统
 * Author: sujie.liu
 * Time: 2016/1/12 17:29
 */
public enum ProcessStatus {

    INIT(0,"初始"),
    SUCCESS(1,"成功"),
    FAIL(2,"失败"),
    PROCESSING(3,"处理中"),
    ;

    /* 状态 */
    private Integer code;
    /* 备注 */
    private String msg;

    private static Map<Integer, ProcessStatus> map = Maps.newHashMap();

    /* 状态可达Map */
    private static final Map<ProcessStatus,List<ProcessStatus>> accessibleMap = Maps.newHashMap();

    public static final List<ProcessStatus> ACCESS_INIT =
            Arrays.asList(INIT);
    public static final List<ProcessStatus> ACCESS_FAIL =
            Arrays.asList(INIT,PROCESSING,FAIL);
    public static final List<ProcessStatus> ACCESS_PROCESSING =
            Arrays.asList(INIT,PROCESSING);
    public static final List<ProcessStatus> ACCESS_SUCCESS =
            Arrays.asList(INIT,PROCESSING,FAIL,SUCCESS);

    static {
        for (ProcessStatus status : ProcessStatus.values()) {
            map.put(status.getCode(), status);
        }
        accessibleMap.put(INIT, ACCESS_INIT);
        accessibleMap.put(FAIL, ACCESS_FAIL);
        accessibleMap.put(PROCESSING, ACCESS_PROCESSING);
        accessibleMap.put(SUCCESS, ACCESS_SUCCESS);
    }

    public static ProcessStatus valueOf(Integer code) {
        if (code == null || !map.containsKey(code)) {
            return null;
        }
        return map.get(code);
    }

    public static ProcessStatus toEnum(int code) {
        if (map.containsKey(code)) {
            return map.get(code);
        } else {
            return null;
        }
    }

    ProcessStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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

    public static boolean ifSuccess(ProcessStatus status){
        return SUCCESS.equals(status);
    }


    /**
     * 获取可达状态
     * @param retStatus
     * @return
     */
    public static final List<ProcessStatus> getAccessibleStatus(ProcessStatus retStatus){
        return accessibleMap.get(retStatus);
    }


    /**
     * 判断是否可达
     * @param resStatus
     * @param retStatus
     * @return
     */
    public static final boolean isAccess(ProcessStatus resStatus,ProcessStatus retStatus){
        Preconditions.checkNotNull(resStatus, "resStatus is null");
        Preconditions.checkNotNull(retStatus, "retStatus is null");
        return Sets.newHashSet(accessibleMap.get(retStatus)).contains(resStatus);
    }

}
