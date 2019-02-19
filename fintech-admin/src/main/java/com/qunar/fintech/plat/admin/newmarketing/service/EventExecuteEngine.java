package com.qunar.fintech.plat.admin.newmarketing.service;

import com.qunar.fintech.plat.admin.newmarketing.dto.EventExecuteResult;

/**
 * @author qun.shi
 * @since 2019-02-02 1:48 PM
 */
public interface EventExecuteEngine {
    /**
     *  审核通过，执行审核的事件
     *  content：事件内容
     */
    EventExecuteResult execute(String content);
}
