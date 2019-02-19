package com.qunar.fintech.plat.admin.newmarketing.service.impl;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.io.CharStreams;
import com.qunar.fintech.plat.admin.newmarketing.dto.GrantCouponReq;
import com.qunar.fintech.plat.admin.newmarketing.facade.MarketingFacadeProxy;
import com.qunar.fintech.plat.admin.newmarketing.service.GrantCouponService;
import com.qunar.fintech.plat.admin.support.exception.MarketErrorCodes;
import com.qunar.fintech.plat.admin.support.lock.BusiLocker;
import com.qunar.fintech.util.lock.BusiCallable;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author qun.shi
 * @since 2019-02-12 7:34 PM
 */
@Service
public class GrantCouponServiceImpl implements GrantCouponService {

    /**
     * 后台发券
     */
    @Override
    public void grant(final GrantCouponReq req,final MultipartFile[] userInfoFiles){

        // check 文件格式
        int expiredCnt = 0;
        for (MultipartFile file:userInfoFiles) {
            expiredCnt += checkFileData(file);
        }

        if (expiredCnt <= 0) {
            return;
        }

        // 默认过期时长
        final int expiredSecs = new Double(0.15 * expiredCnt + 1).intValue();
        final String lockKey = genLockKey("grant", req.getCouponCode());

        LOGGER.info("tryGrant - expiredSecs: {}, lockKey: {}", expiredSecs, lockKey);
        //异步调用发券接口
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 尝试执行
                    busiLocker.lockedExecute(lockKey, expiredSecs, new BusiCallable<Integer>() {
                        @Override
                        public Integer executeBusi() throws Exception {
                            LOGGER.debug("tryGrant - begin");
                            return marketingFacadeProxy.grantCoupon(userInfoFiles,req);
                        }
                    });
                    // 释放锁
                    LOGGER.debug("excute busi finish, try release lock.");
                } catch (BusiException e) {
                    // 业务异常，直接抛出
                    throw e;

                } catch (Exception e) {
                    // 尝试加锁异常, 任务终止
                    LOGGER.error("当前有正在执行的发券任务: {}, err:{} ", req.getCouponCode(), e);
                } finally {
                    busiLocker.releaseLock(lockKey);
                    LOGGER.debug("tryGrant - release lock succ: {}", lockKey);
                }
            }
        });
        thread.start();
    }

    private String genLockKey(String prefix, String couponCode) {
        return Joiner.on("-").join(prefix, couponCode);
    }

    /**
     * 校验文件合理性
     */
    private int checkFileData(MultipartFile couponFile) {
        // 校验文件合理性
        try {
            List<String> stringList = CharStreams.readLines(new InputStreamReader(couponFile.getInputStream()));
            for (String str : stringList) {
                //遍历集合的每一个元素，把它用“，”分割成两部分
                List<String> uidMobile = Splitter.on(",").splitToList(str);
                if (uidMobile.size() < 2) {
                    throw new BusiException(MarketErrorCodes.PARAM_ERROR, "uid或mobile为空");
                }

                if (StringUtils.isEmpty(uidMobile.get(0)) || StringUtils.isEmpty(uidMobile.get(1))) {
                    throw new BusiException(MarketErrorCodes.PARAM_ERROR, "uid或mobile为空");
                }
            }
            return stringList.size();
        } catch (BusiException e) {
            LOGGER.error("checkFileData - err: {}", e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("checkFileData - err: {}", e);
            throw new BusiException(MarketErrorCodes.UNKOWN_ERROR);
        }
    }

    @Autowired
    private MarketingFacadeProxy marketingFacadeProxy;

    @Resource
    private BusiLocker busiLocker;

    private static final Logger LOGGER = LoggerFactory.getLogger(GrantCouponServiceImpl.class);
}
