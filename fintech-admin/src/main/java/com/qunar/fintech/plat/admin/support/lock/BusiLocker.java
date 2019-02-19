package com.qunar.fintech.plat.admin.support.lock;

import com.google.common.base.Preconditions;
import com.qunar.fintech.util.lock.BusiCallable;
import com.qunar.fintech.util.lock.CallableResult;
import com.qunar.fintech.util.lock.LockedExecutorTemplate;
import com.qunar.fintech.util.model.ObjectContainer;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.exception.CommonApiErrorCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * Author: ruijie.zheng
 * Date: 2018-09-12
 */
public class BusiLocker implements InitializingBean {

    /**
     * 锁的超时时间可以自己进行设置
     *
     * @param lockKey
     * @param expiredSecs 建议设置时间长一点，默认可以指定15s，对于涉及外部的调用，可以再长点，比如支付的超时时间30s
     * @param runnable
     * @param <T>
     * @return
     */
    public <T> T lockedExecute(String lockKey, int expiredSecs, final BusiCallable<T> runnable) {
        Assert.hasLength(lockKey, "lockKey must provided");
        Preconditions.checkNotNull(runnable, "runnable must be provided");
        Preconditions.checkArgument(expiredSecs > 0, "expired seconds must > 0");

        CallableResult<T> result = null;
        final ObjectContainer<RuntimeException> eContainer = new ObjectContainer<RuntimeException>();
        try {
            result = lockTemplate.executeBusi(LOGGER, lockKey, expiredSecs, new BusiCallable<T>() {
                @Override
                public T executeBusi() throws Exception {
                    try {
                        return runnable.executeBusi();
                    } catch (RuntimeException e) {
                        //业务处理发生异常，业务不出现Checked Exception
                        eContainer.setObj(e);
                        return null;
                    }
                }
            });
        } catch (BusiException e) {
            // 业务处理返回异常，直接往外抛出
            throw e;
        } catch (Exception e) {
            //锁系统发生的异常，直接转换为系统异常抛出
            LOGGER.error("锁系统发生异常：" + e.getMessage(), e);
            throw new BusiException(CommonApiErrorCodes.SYSTEM_ERROR, "锁系统发生异常");
        }
        //业务抛出的异常，直接跑出去
        if (eContainer.getObj() != null) {
            throw eContainer.getObj();
        }
        if (result == null) {
            //正常不会走到这里
            throw new BusiException(CommonApiErrorCodes.SYSTEM_ERROR, "锁系统发生异常");
        }
        if (result.isBlocked()) {
            throw new BusiException(CommonApiErrorCodes.CONCURRENT_ERROR);
        } else {
            return result.getResult();
        }
    }

    public boolean releaseLock(String lockKey) {
        return lockTemplate.releaseLock(LOGGER, lockKey);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(lockTemplate);
    }
    private LockedExecutorTemplate lockTemplate;

    public void setLockTemplate(LockedExecutorTemplate lockTemplate) {
        this.lockTemplate = lockTemplate;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(BusiLocker.class);

}
