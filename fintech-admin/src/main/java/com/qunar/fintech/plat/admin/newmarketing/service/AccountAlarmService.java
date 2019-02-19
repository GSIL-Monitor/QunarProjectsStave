package com.qunar.fintech.plat.admin.newmarketing.service;

import com.qunar.fintech.plat.admin.newmarketing.dto.AccountAlarmDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.AccountAlarmQueryReq;
import com.qunar.fintech.plat.admin.newmarketing.dto.AccountAlarmResp;

/**
 * @author qun.shi
 * @since 2019-01-09 10:22 PM
 * 账务金额报警配置接口：http://wiki.corp.qunar.com/confluence/pages/viewpage.action?pageId=231879191
 */
public interface AccountAlarmService {

    /**
     * 新增活动剩余金额报警配置
     * @param accountAlarmQueryReq
     */
    AccountAlarmResp addActivityAmtAlarmConfig(AccountAlarmDetailDto accountAlarmQueryReq);

    /**
     * 更新活动金额报警配置
     * @param accountAlarmQueryReq
     */
    void updateActivityAmtAlarmConfig(AccountAlarmDetailDto accountAlarmQueryReq);

    /**
     * 查询活动金额报警配置
     * @param accountAlarmQueryReq
     */
    AccountAlarmDetailDto queryActivityAmtAlarmConfig(AccountAlarmQueryReq accountAlarmQueryReq);
}
