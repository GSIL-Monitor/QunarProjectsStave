package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

/**
 * 处理还款计划锁状态
 * Created by sujie.liu on 2016/8/4.
 */
public enum RepayPlanLockProcessStatus {

    INIT(0,"初始"),
    LOCKING(1,"加锁处理中"),// 需要先加锁再解锁
    LOCKED(2,"加锁成功"),// 终态
    LOCK_FAIL(3,"加锁失败"),// 终态
    UNLOCKING(4,"待解锁"),// 分期失败时先置为带解锁，再发起解锁（跨网络调用原因）
    UNLOCK_FAILED(5,"解锁失败"),// 非终态
    UNLOCKED(6,"解锁成功"),// 终态
    ;


    private Integer code;
    private String desc;

    private RepayPlanLockProcessStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static Map<Integer, RepayPlanLockProcessStatus> statusMap = Maps.newHashMap();


    public static Set<RepayPlanLockProcessStatus> CAN_UNLOCK_STATUS_SET = Sets.newHashSet();

    static {
        for (RepayPlanLockProcessStatus statusEnum : RepayPlanLockProcessStatus.values()) {
            statusMap.put(statusEnum.getCode(), statusEnum);
        }

        /* 加锁中、解锁中、解锁失败、加锁成功状态可解锁（业务判断时需要加上失败状态） */
        CAN_UNLOCK_STATUS_SET.add(LOCKING);
        CAN_UNLOCK_STATUS_SET.add(UNLOCKING);
        CAN_UNLOCK_STATUS_SET.add(UNLOCK_FAILED);
        CAN_UNLOCK_STATUS_SET.add(LOCKED);
    }

    public static RepayPlanLockProcessStatus toEnum(Integer code) {
        return statusMap.get(code);
    }

    /**
     * 判断是否可以解锁还款计划
     * @return
     */
    public static boolean canUnlock(RepayPlanLockProcessStatus status){
        return CAN_UNLOCK_STATUS_SET.contains(status);
    }


    /**
     * 只需要解锁的状态
     * 加锁中状态，由于加锁状态未知，需要先加锁在解锁
     * @return
     */
    public static boolean justUnlock(RepayPlanLockProcessStatus status){
        return UNLOCKING.equals(status) || UNLOCK_FAILED.equals(status) || LOCKED.equals(status);
    }

}
