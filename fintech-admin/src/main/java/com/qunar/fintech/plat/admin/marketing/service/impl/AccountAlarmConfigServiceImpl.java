package com.qunar.fintech.plat.admin.marketing.service.impl;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qunar.fintech.monitor.core.Metrics;
import com.qunar.fintech.plat.admin.marketing.dto.AccountAlarmDto;
import com.qunar.fintech.plat.admin.marketing.enums.AccountAlertModeEnum;
import com.qunar.fintech.plat.admin.marketing.monitor.MetricsName;
import com.qunar.fintech.plat.admin.marketing.monitor.MonitorMertricsGroup;
import com.qunar.fintech.plat.admin.marketing.service.AccountAlarmConfigService;
import com.qunar.fintech.plat.admin.query.entity.AccountAlarmResp;
import com.qunar.fintech.plat.admin.query.utils.HttpCommUtil;
import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import com.qunar.fintech.plat.admin.support.config.AlarmQconfig;
import com.qunar.pay.external.QunarPayHelper;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.exception.CommonApiErrorCodes;
import com.qunar.pay.g2.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.TreeMap;

/**
 * @author qun.shi
 * @since 2019-01-09 10:24 PM
 */
@Service
public class AccountAlarmConfigServiceImpl implements AccountAlarmConfigService {

    @Override
    public void addActivityAmtAlarmConfig(AccountAlarmDto configDto) {
        // check
        checkAddAlarmReq(configDto);
        // 创建配置操作
        TreeMap<String, String> paramMap = buildAddOrUpdateAlarmReq(configDto,CREATE_OPER_TYPE);

        String result = null;
        try {
            result = HttpCommUtil.doPost(addAccountMonitorUrl, paramMap);
        } catch (Exception e) {
            Metrics.sum(MonitorMertricsGroup.ALARM_FAIL_COUNT, MetricsName.ACCOUNT_ALARM_ADD);
            logger.error("新增子账务报警失败，paramMap={},e={}",paramMap,e);
        }

        AccountAlarmResp resp = parseAccountAlarmResp(result);
        if(resp == null || !resp.getRet()){
            Metrics.sum(MonitorMertricsGroup.ALARM_FAIL_COUNT, MetricsName.ACCOUNT_ALARM_ADD);
            throw new BusiException(CommonApiErrorCodes.BUSI_ERROR, "子账户查询失败，paramMap="+paramMap+",result="+result);
        }

        Metrics.sum(MonitorMertricsGroup.ALARM_SUCC_COUNT, MetricsName.ACCOUNT_ALARM_ADD);
    }

    @Override
    public void updateActivityAmtAlarmConfig(AccountAlarmDto configDto) {
        // check
        checkUpdateAlarmReq(configDto);

        // 创建配置操作
        TreeMap<String, String> paramMap = buildAddOrUpdateAlarmReq(configDto,UPDATE_OPER_TYPE);

        String result = null;
        try {
            result = HttpCommUtil.doPost(editAccountMonitorUrl, paramMap);
        } catch (Exception e) {
            Metrics.sum(MonitorMertricsGroup.ALARM_FAIL_COUNT, MetricsName.ACCOUNT_ALARM_UPDATE);
            logger.error("更新子账务报警失败，paramMap={},e={}",paramMap,e);
            throw new BusiException(CommonApiErrorCodes.BUSI_ERROR,"调账务更新接口失败，paramMap="+paramMap);
        }

       AccountAlarmResp resp = parseAccountAlarmResp(result);
        if(resp == null || !resp.getRet()){
            Metrics.sum(MonitorMertricsGroup.ALARM_FAIL_COUNT, MetricsName.ACCOUNT_ALARM_UPDATE);
            throw new BusiException(CommonApiErrorCodes.BUSI_ERROR, "子账户查询失败，paramMap="+paramMap+",result="+result);
        }

        Metrics.sum(MonitorMertricsGroup.ALARM_SUCC_COUNT, MetricsName.ACCOUNT_ALARM_UPDATE);
    }

    @Override
    public AccountAlarmResp queryActivityAmtAlarmConfig(AccountAlarmDto configDto) {
        // check
        checkQueryAlarmReq(configDto);
        TreeMap<String, String> paramMap = buildQueryAlarmReq(configDto);

        String result = null;
        try {
            result = HttpCommUtil.doPost(queryAccountMonitorUrl, paramMap);
        } catch (Exception e) {
            logger.error("更新子账务报警失败，paramMap={},e={}",paramMap,e);
        }

        AccountAlarmResp resp = parseAccountAlarmResp(result);
        if(resp == null || !resp.getRet()){
            Metrics.sum(MonitorMertricsGroup.ALARM_FAIL_COUNT, MetricsName.ACCOUNT_ALARM_QUERY);
            throw new BusiException(CommonApiErrorCodes.BUSI_ERROR, "子账户查询失败，paramMap=" + paramMap + ",result=" + result);
        }

        Metrics.sum(MonitorMertricsGroup.ALARM_SUCC_COUNT, MetricsName.ACCOUNT_ALARM_QUERY);
        return resp;
    }

    /**
     * 获取结果状态
     * @param str 请求结果JSON格式
     */
    public static AccountAlarmResp parseAccountAlarmResp(String str) {
        if (StringUtils.isNotBlank(str)) {
            try {
                AccountAlarmResp accountAlarmResp = gson.fromJson(str,AccountAlarmResp.class);
                return accountAlarmResp;
            } catch (Exception e) {
                logger.error("请求账务报警HTTP接口，解析JSON失败，str={},e={}", str,e);
                return null;
            }
        }
        return null;
    }

    /**
     * check 请求参数
     */
    private void checkAddAlarmReq(AccountAlarmDto configDto){
        Preconditions.checkNotNull(configDto.getActivityId(),"活动id不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(configDto.getCustomerNo()),"账户商户号不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(configDto.getReceiver()),"报警接受人（qtalk ids）不能为空");
        Preconditions.checkArgument(AccountAlertModeEnum.supportModel(Integer.valueOf(configDto.getAlertMode())),"不支持此报警模式");
        Preconditions.checkNotNull(configDto.getStartTime(),"报警开始时间不能为空");
        Preconditions.checkNotNull(configDto.getEndTime(),"报警结束时间不能为空");
        Preconditions.checkNotNull(configDto.getModel(),"报警模式不能为空");
        Preconditions.checkNotNull(configDto.getAmount(),"报警金额阈值不能为空");
    }

    /**
     * check 请求参数
     */
    private void checkUpdateAlarmReq(AccountAlarmDto configDto){
        Preconditions.checkNotNull(configDto.getActivityId(),"活动id不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(configDto.getCustomerNo()),"账户商户号不能为空");
        Preconditions.checkArgument(AccountAlertModeEnum.supportModel(Integer.valueOf(configDto.getAlertMode())),"不支持此报警模式");
    }

    /**
     * check 请求参数
     */
    private void checkQueryAlarmReq(AccountAlarmDto configDto){
        Preconditions.checkNotNull(configDto.getActivityId(),"活动id不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(configDto.getCustomerNo()),"账户商户号不能为空");
    }

    /**
     * 构造添加账务监控接口参数
     */
    private TreeMap<String, String> buildAddOrUpdateAlarmReq(AccountAlarmDto configDto,Integer operType){
        TreeMap<String, String> paramMap = new TreeMap<String, String>();

        paramMap.put("version",ACCOUNT_ALARM_INTERFACE_VERSION );
        paramMap.put("customerNo", configDto.getCustomerNo());
        paramMap.put("activityId", String.valueOf(configDto.getActivityId()));
        paramMap.put("receiver", configDto.getReceiver());
        paramMap.put("alertMode", String.valueOf(configDto.getAlertMode()));
        paramMap.put("extEmailReceiver", configDto.getExtEmailReceiver());
        paramMap.put("extMobileReceiver", configDto.getExtMobileReceiver());
        paramMap.put("notifyInterval", configDto.getNotifyInterval());
        paramMap.put("startTime", configDto.getStartTime());
        paramMap.put("endTime", configDto.getEndTime());
        paramMap.put("mode", configDto.getModel());
        paramMap.put("amount", configDto.getAmount());
        paramMap.put("remark", configDto.getRemark());

        /**
         * 更新操作，可以更新监控状态，以关闭监控
         * 调用更新接口需要传useStatus，加签
         */
        if(UPDATE_OPER_TYPE.equals(operType)){
            paramMap.put("useStatus", configDto.getUseStatus());
        }

        setSignInfo(paramMap,configDto.getCustomerNo());
        return paramMap;
    }

    /**
     * 设置验签需要的信息
     */
    private void setSignInfo(TreeMap<String, String> paramMap,String customerNo){
        String interfaceMerchantCode = alarmQconfig.getInterfaceMerchantCode(customerNo);
        ParamChecker.notBlank(interfaceMerchantCode,"Qconfig中没有配置接口商户号，customerNo"+customerNo);
        paramMap.put("interfaceMerchantCode", interfaceMerchantCode);

        String busiTypeId = alarmQconfig.getBusiTypeId(customerNo);
        ParamChecker.notBlank(interfaceMerchantCode,"Qconfig中没有配置业务线id，customerNo="+customerNo);
        paramMap.put("busiTypeId", busiTypeId);

        String signKey = alarmQconfig.getSignKey(customerNo);
        ParamChecker.notBlank(signKey,"Qconfig中没有配置加密密钥，customerNo="+customerNo);
        paramMap.put("HMAC", QunarPayHelper.sign(paramMap, signKey, "MD5"));
    }

    /**
     * 构造查询账务配置信息
     * @param configDto
     * @return
     */
    private TreeMap<String, String> buildQueryAlarmReq(AccountAlarmDto configDto){
        TreeMap<String, String> paramMap = new TreeMap<String, String>();
        paramMap.put("version",ACCOUNT_ALARM_INTERFACE_VERSION );
        paramMap.put("customerNo", configDto.getCustomerNo());
        paramMap.put("activityId", String.valueOf(configDto.getActivityId()));
        setSignInfo(paramMap,configDto.getCustomerNo());
        return paramMap;
    }

    private static final Logger logger = LoggerFactory.getLogger(AccountAlarmConfigServiceImpl.class);

    /**
     * 账务金额监控添加报警http接口
     */
    @Value("${add.account.monitor.url}")
    private String addAccountMonitorUrl;

    /**
     * 账务金额监控更新报警http接口
     */
    @Value("${edit.account.monitor.url}")
    private String editAccountMonitorUrl;

    /**
     * 账务金额监控查询具体配置信息
     */
    @Value("${query.account.monitor.url}")
    private String queryAccountMonitorUrl;

    /**
     * 请求结果成功状态值
     */
    private final String RESULT_SUCC = "SUCCESS";

    /**
     * GSON 线程安全
     */
    private static final Gson gson = new GsonBuilder().create();

    @Autowired
    private AlarmQconfig alarmQconfig;

    private static final Integer CREATE_OPER_TYPE = 0;

    private static final Integer UPDATE_OPER_TYPE = 1;

    private static final String ACCOUNT_ALARM_INTERFACE_VERSION = "20140808";
}
