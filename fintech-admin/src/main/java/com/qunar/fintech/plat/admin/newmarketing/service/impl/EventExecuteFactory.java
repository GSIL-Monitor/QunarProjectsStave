package com.qunar.fintech.plat.admin.newmarketing.service.impl;

import com.google.common.collect.Maps;
import com.qunar.fintech.plat.admin.newmarketing.dto.CouponDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.EventExecuteResult;
import com.qunar.fintech.plat.admin.newmarketing.enums.MenuTypeEnum;
import com.qunar.fintech.plat.admin.newmarketing.service.EventExecuteEngine;
import com.qunar.fintech.plat.admin.newmarketing.service.MarketingCouponService;
import com.qunar.fintech.plat.admin.query.utils.JSONUtils;
import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.utils.base.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

import static com.qunar.fintech.plat.admin.newmarketing.exception.MarketingExceptionCode.*;

/**
 * @author qun.shi
 * @since 2019-02-02 2:03 PM
 */
@Service
public class EventExecuteFactory implements InitializingBean {

    /**
     * 事件执行方法集合
     */
    private Map<MenuTypeEnum, EventExecuteEngine> eventExecuteEngineHashMap = Maps.newHashMap();

    public EventExecuteResult execute(MenuTypeEnum menuTypeEnum, String content) {
        // check 参数 menuTypeEnum content
        if (StringUtils.isBlank(content)) {
            LOGGER.error("没有对应的事件处理内容，menuTypeEnum={}，content={}", menuTypeEnum, content);
            throw new BusiException(NO_EVENT_EXECUTE_CONTENT);
        }

        EventExecuteEngine engine = eventExecuteEngineHashMap.get(menuTypeEnum);
        if (engine == null) {
            LOGGER.error("没有对应的事件处理引擎，menuTypeEnum={}，content={}", menuTypeEnum, content);
            throw new BusiException(NO_EVENT_EXECUTE_ENGINE);
        }

        // 执行
        EventExecuteResult result = engine.execute(content);
        return result;
    }

    @Override
    public void afterPropertiesSet() {
        eventExecuteEngineHashMap.put(MenuTypeEnum.CREATE_COUPON, new EventExecuteEngine() {
            @Override
            public EventExecuteResult execute(String content) {
                try {
                    ParamChecker.notBlank(content,"优惠券信息不能为空！");
                    marketingCouponService.add(JSONUtils.getGson().fromJson(content, CouponDetailDto.class));
                    return EventExecuteResult.createSucc();
                } catch (Exception e) {
                    LOGGER.error("创建优惠券失败，couponInfoDto={}，msg={}", content, e.getMessage());
                    throw new BusiException(EVENT_EXECUTE_FAIL);
                }
            }
        });

        eventExecuteEngineHashMap.put(MenuTypeEnum.UPDATE_COUPON, new EventExecuteEngine() {
            @Override
            public EventExecuteResult execute(String content) {
                try {
                    ParamChecker.notBlank(content,"优惠券信息不能为空！");
                    marketingCouponService.update(JSONUtils.getGson().fromJson(content, CouponDetailDto.class));
                    return EventExecuteResult.createSucc();
                } catch (Exception e) {
                    LOGGER.error("更新优惠券失败，couponInfoDto={}，msg={}", content, e.getMessage());
                    throw new BusiException(EVENT_EXECUTE_FAIL);
                }
            }
        });
    }

    @Resource
    MarketingCouponService marketingCouponService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EventExecuteFactory.class);
}
