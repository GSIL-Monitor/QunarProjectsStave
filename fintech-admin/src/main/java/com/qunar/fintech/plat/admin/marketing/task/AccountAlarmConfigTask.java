//package com.qunar.fintech.plat.admin.marketing.task;
//
//import com.qunar.fintech.plat.admin.marketing.dto.AccountAlarmDto;
//import com.qunar.fintech.plat.admin.marketing.service.AccountAlarmConfigService;
//import com.qunar.fintech.plat.admin.support.constant.TaskNameConstants;
//import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
//import com.qunar.pay.finance.qf.commons.api.exception.CommonApiErrorCodes;
//import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import qunar.tc.qschedule.config.QSchedule;
//
///**
// * @author qun.shi
// * @since 2019-01-11 8:44 PM
// */
//@Component
//public class AccountAlarmConfigTask {
//
//    /**
//     * 新增账户报警配置
//     * @param parameter
//     */
//    @QSchedule(TaskNameConstants.ADD_OR_UPDTAE_ACCOUNT_ALAM_CONFIG_TASK)
//    public void addOrUpdateAccountAlarmConfig(qunar.tc.schedule.Parameter parameter) {
//        AccountAlarmDto alarmDto = new AccountAlarmDto();
//
//        // 业务线名称：CASH、IOUS
//        String productNo = parameter.getString("productNo");
//        ParamChecker.notBlank(productNo, "业务线名称不为空");
//        alarmDto.setProductNo(productNo);
//
//        // 账户的商户号：如CIOUS2018Sale99
//        String customerNo = parameter.getString("customerNo");
//        ParamChecker.notBlank(customerNo, "商户号不为空");
//        alarmDto.setCustomerNo(customerNo);
//
//        // 活动id
//        String activityId = parameter.getString("activityId");
//        ParamChecker.notBlank(activityId, "活动id不为空");
//        alarmDto.setActivityId(Integer.valueOf(activityId));
//
//        // 报警接收人：qtalk ids xxx,xxx
//        String receiver = parameter.getString("receiver");
//        ParamChecker.notBlank(receiver, "报警接收人不能为空");
//        alarmDto.setReceiver(receiver);
//
//        // 报警模式：1:qtalk提醒, 2:短信, 4:qmq消息,8:邮件
//        String alertMode = parameter.getString("alertMode");
//        ParamChecker.notBlank(alertMode, "报警模式不能为空");
//        alarmDto.setAlertMode(Integer.valueOf(alertMode));
//
//        // 非必须：邮件地址,用于外部邮箱
//        String extEmailReceiver = parameter.getString("extEmailReceiver");
//        alarmDto.setExtEmailReceiver(extEmailReceiver);
//
//        // 非必须：手机号,用于外部手机号码
//        String extMobileReceiver = parameter.getString("extMobileReceiver");
//        alarmDto.setExtMobileReceiver(extMobileReceiver);
//
//        // 报警间隙,时间单位:分钟
//        String notifyInteval = parameter.getString("notifyInteval");
//        ParamChecker.notBlank(notifyInteval, "报警时间间隔不能为空");
//        alarmDto.setNotifyInterval(notifyInteval);
//
//        // 报警时间的起始 05:00
//        String startTime = parameter.getString("startTime");
//        ParamChecker.notBlank(startTime, "报警开始时间不能为空");
//        alarmDto.setStartTime(startTime);
//
//        // 报警时间的结束 22:00
//        String endTime = parameter.getString("endTime");
//        ParamChecker.notBlank(endTime, "报警结束时间不能为空");
//        alarmDto.setEndTime(endTime);
//
//        // 报警模式
//        String mode = parameter.getString("mode");
//        ParamChecker.notBlank(mode, "报警模式不能为空");
//        alarmDto.setModel(mode);
//
//        // 报警金额阈值
//        String amount = parameter.getString("amount");
//        ParamChecker.notBlank(amount, "报警金额阈值不能为空");
//        alarmDto.setAmount(amount);
//
//        // 非必须：说明
//        String remark = parameter.getString("remark");
//        alarmDto.setRemark(remark);
//
//        // 操作类型，新增：0，更新：1
//        String operType = parameter.getString("operType");
//        ParamChecker.notBlank(operType, "操作类型不能为空，0：新增，1：更新");
//
//        if (operType.equals(ADD_OPERATION)) {
//            accountAlarmConfigService.addActivityAmtAlarmConfig(alarmDto);
//        } else if (operType.equals(UPDATE_OPERATION)) {
//            accountAlarmConfigService.updateActivityAmtAlarmConfig(alarmDto);
//        }else{
//            throw new BusiException(CommonApiErrorCodes.PARAM_ERROR, "操作类型参数错误，operType = " + operType);
//        }
//    }
//
//
//    /**
//     * 查询账户报警配置
//     * @param parameter
//     */
//    @QSchedule(TaskNameConstants.QUERY_ACCOUNT_ALAM_CONFIG_TASK)
//    public void queryAccountAlarmConfig(qunar.tc.schedule.Parameter parameter) {
//        AccountAlarmDto alarmDto = new AccountAlarmDto();
//
//        // 业务线名称：CASH、IOUS
//        String productNo = parameter.getString("productNo");
//        ParamChecker.notBlank(productNo, "业务线名称不为空");
//        alarmDto.setProductNo(productNo);
//
//        // 账户的商户号：如CIOUS2018Sale99
//        String customerNo = parameter.getString("customerNo");
//        ParamChecker.notBlank(customerNo, "商户号不为空");
//        alarmDto.setCustomerNo(customerNo);
//
//        // 活动id
//        String activityId = parameter.getString("activityId");
//        ParamChecker.notBlank(activityId, "活动id不为空");
//        alarmDto.setActivityId(Integer.valueOf(activityId));
//
//        // 查询账务报警配置信息
//        AccountAlarmDto accountAlarmDto = accountAlarmConfigService.queryActivityAmtAlarmConfig(alarmDto);
//        logger.info("查询账务报警配置信息，accountAlarmDto = {}",accountAlarmDto);
//    }
//
//    private static final Logger logger = LoggerFactory.getLogger(AccountAlarmConfigTask.class);
//
//    @Autowired
//    AccountAlarmConfigService accountAlarmConfigService;
//
//    /**
//     * 新增报警
//     */
//    private final static Integer ADD_OPERATION = 0;
//
//    /**
//     * 更新报警
//     */
//    private final static Integer UPDATE_OPERATION = 1;
//}
