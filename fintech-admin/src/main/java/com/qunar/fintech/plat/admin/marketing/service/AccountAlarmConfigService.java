package com.qunar.fintech.plat.admin.marketing.service;

import com.qunar.fintech.plat.admin.marketing.dto.AccountAlarmDto;
import com.qunar.fintech.plat.admin.query.entity.AccountAlarmResp;

import java.util.List;

/**
 * @author qun.shi
 * @since 2019-01-09 10:22 PM
 * 账务金额报警配置接口：http://wiki.corp.qunar.com/confluence/pages/viewpage.action?pageId=231879191
 */
public interface AccountAlarmConfigService {

    /**
     * 新增活动剩余金额报警配置
     * @param accountAlarmDto
     */
    void addActivityAmtAlarmConfig(AccountAlarmDto accountAlarmDto);

    /**
     * 更新活动金额报警配置
     * @param accountAlarmDto
     */
    void updateActivityAmtAlarmConfig(AccountAlarmDto accountAlarmDto);

    /**
     * 查询活动金额报警配置
     * @param accountAlarmDto
     */
    AccountAlarmResp queryActivityAmtAlarmConfig(AccountAlarmDto accountAlarmDto);
}
