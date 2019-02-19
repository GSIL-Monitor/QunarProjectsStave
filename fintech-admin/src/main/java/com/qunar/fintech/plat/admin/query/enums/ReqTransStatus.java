package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 请求迁移状态
 * Author: sujie.liu
 * Time: 2016/1/12 21:10
 */
public enum ReqTransStatus {

    INIT(0,"初始"),
    SUCCESS(1,"成功"),
    FAIL(2,"失败"),
    PROCESSING(3,"处理中"),
    ;

    /* 状态可达Map */
    private static final Map<ReqTransStatus,List<ReqTransStatus>> accessibleMap = Maps.newHashMap();

    public static final List<ReqTransStatus> ACCESS_INIT =
            Arrays.asList(INIT);
    public static final List<ReqTransStatus> ACCESS_FAIL =
            Arrays.asList(INIT,PROCESSING,FAIL);
    public static final List<ReqTransStatus> ACCESS_PROCESSING =
            Arrays.asList(INIT,PROCESSING);
    public static final List<ReqTransStatus> ACCESS_SUCCESS =
            Arrays.asList(INIT,PROCESSING,FAIL,SUCCESS);


    /* 状态 */
    private Integer code;
    /* 备注 */
    private String msg;

    private static Map<Integer, ReqTransStatus> map = Maps.newHashMap();

    static {
        for (ReqTransStatus status : ReqTransStatus.values()) {
            map.put(status.getCode(), status);
        }
        accessibleMap.put(INIT, ACCESS_INIT);
        accessibleMap.put(FAIL, ACCESS_FAIL);
        accessibleMap.put(PROCESSING, ACCESS_PROCESSING);
        accessibleMap.put(SUCCESS, ACCESS_SUCCESS);
    }

    public static ReqTransStatus valueOf(Integer code) {
        if (code == null || !map.containsKey(code)) {
            return null;
        }
        return map.get(code);
    }

    public static ReqTransStatus toEnum(int code) {
        if (map.containsKey(code)) {
            return map.get(code);
        } else {
            return null;
        }
    }

    ReqTransStatus(Integer code, String msg) {
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

    public static final boolean ifSuccess(ReqTransStatus transStatus){
        return SUCCESS.equals(transStatus);
    }

    /**
     * 获取可达状态
     * @param retStatus
     * @return
     */
    public static final List<ReqTransStatus> getAccessibleStatus(ReqTransStatus retStatus){
        return accessibleMap.get(retStatus);
    }

    /**
     * 判断是否可达
     * @param resStatus
     * @param retStatus
     * @return
     */
    public static final boolean isAccess(ReqTransStatus resStatus,ReqTransStatus retStatus){
        Preconditions.checkNotNull(resStatus, "resStatus is null");
        Preconditions.checkNotNull(retStatus, "retStatus is null");
        return Sets.newHashSet(accessibleMap.get(retStatus)).contains(resStatus);
    }

}
