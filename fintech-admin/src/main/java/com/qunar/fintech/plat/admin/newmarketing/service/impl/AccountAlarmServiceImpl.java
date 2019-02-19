package com.qunar.fintech.plat.admin.newmarketing.service.impl;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qunar.fintech.monitor.core.Metrics;

import com.qunar.fintech.plat.admin.newmarketing.dto.AccountAlarmDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.AccountAlarmQueryReq;
import com.qunar.fintech.plat.admin.newmarketing.enums.AccountAlertModeEnum;
import com.qunar.fintech.plat.admin.newmarketing.monitor.MonitorMertricsGroup;
import com.qunar.fintech.plat.admin.newmarketing.constants.CouponConstants;
import com.qunar.fintech.plat.admin.newmarketing.dto.AccountAlarmResp;
import com.qunar.fintech.plat.admin.newmarketing.service.AccountAlarmService;
import com.qunar.fintech.plat.admin.query.utils.HttpCommUtil;
import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import com.qunar.fintech.plat.admin.support.config.AlarmQconfig;
import com.qunar.pay.external.QunarPayHelper;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.g2.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

import static com.qunar.fintech.plat.admin.newmarketing.monitor.MetricsName.ACCOUNT_ALARM_ADD;
import static com.qunar.fintech.plat.admin.newmarketing.monitor.MetricsName.ACCOUNT_ALARM_QUERY;
import static com.qunar.fintech.plat.admin.newmarketing.monitor.MetricsName.ACCOUNT_ALARM_UPDATE;

/**
 * @author qun.shi
 * @since 2019-01-09 10:24 PM
 */
@Service
public class AccountAlarmServiceImpl implements AccountAlarmService {

    @Override
    public AccountAlarmResp addActivityAmtAlarmConfig(AccountAlarmDetailDto configDto) {
        logger.info("开始新增子账务报警，configDto={}",configDto);
        checkAddAlarmReq(configDto);

        // 创建配置操作
        TreeMap<String, String> paramMap = buildAddOrUpdateAlarmReq(configDto,
                CouponConstants.CREATE_ACCOUNT_ALARM_OPER_TYPE);

        String result = null;
        try {
            result = HttpCommUtil.doPost(addAccountMonitorUrl, paramMap);
        } catch (Exception e) {
            logger.error("新增子账务报警失败，paramMap={},e={}",paramMap,e.getMessage());
            throw new BusiException(ACCOUNT_ALARM_ADD, e.getMessage());
        }

        AccountAlarmResp resp = parseAccountAlarmResp(result);
        if (resp != null && !resp.getRet()) {
            Metrics.sum(MonitorMertricsGroup.ALARM_FAIL_COUNT, ACCOUNT_ALARM_ADD);
            logger.error("创建账务报警失败，couponInfoDto={},resp={} ", configDto, resp);
            throw new BusiException(ACCOUNT_ALARM_ADD, resp.getErrmsg());
        }

        logger.info("成功新增子账务报警，result=",result);
        return resp;
    }

    @Override
    public void updateActivityAmtAlarmConfig(AccountAlarmDetailDto configDto) {
        logger.info("开始新增子账务报警，configDto={}",configDto);
        checkUpdateAlarmReq(configDto);

        // 创建配置操作
        TreeMap<String, String> paramMap
                = buildAddOrUpdateAlarmReq(configDto,CouponConstants.UPDATE_ACCOUNT_ALARM_OPER_TYPE);

        String result = null;
        try {
            result = HttpCommUtil.doPost(editAccountMonitorUrl, paramMap);
        } catch (Exception e) {
            logger.error("更新子账务报警失败，paramMap={},e={}",paramMap,e);
            throw new BusiException(ACCOUNT_ALARM_UPDATE,e.getMessage());
        }

       AccountAlarmResp resp = parseAccountAlarmResp(result);
        if(resp != null && !resp.getRet()){
            Metrics.sum(MonitorMertricsGroup.ALARM_FAIL_COUNT, ACCOUNT_ALARM_UPDATE);
            throw new BusiException(ACCOUNT_ALARM_UPDATE, resp.getErrmsg());
        }

        logger.info("成功更新子账务报警，result={}",result);
    }

    @Override
    public AccountAlarmDetailDto queryActivityAmtAlarmConfig(AccountAlarmQueryReq configDto) {
        logger.info("开始查询子账务报警配置，configDto={}",configDto);
        checkQueryAlarmReq(configDto);
        TreeMap<String, String> paramMap = buildQueryAlarmReq(configDto);

        String result = null;
        try {
            result = HttpCommUtil.doPost(queryAccountMonitorUrl, paramMap);
        } catch (Exception e) {
            logger.error("查询账务报警配置失败，configDto={},e={}",configDto,e);
            throw new BusiException(ACCOUNT_ALARM_QUERY, e.getMessage());
        }

        AccountAlarmResp resp = parseAccountAlarmResp(result);
        if(StringUtils.isBlank(result) || !resp.getRet() || resp.getData() == null || resp.getData().size() == 0){
            logger.error("查询账务报警配置失败，msg={}",result);
            Metrics.sum(MonitorMertricsGroup.ALARM_FAIL_COUNT, ACCOUNT_ALARM_QUERY);
            return  null;
        }

        logger.info("成功查询子账务报警配置，result={}",result);
        return resp.getData().get(0);
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
    private void checkAddAlarmReq(AccountAlarmDetailDto configDto){
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
    private void checkUpdateAlarmReq(AccountAlarmDetailDto configDto){
        Preconditions.checkNotNull(configDto.getActivityId(),"活动id不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(configDto.getCustomerNo()),"账户商户号不能为空");
        Preconditions.checkArgument(AccountAlertModeEnum.supportModel(Integer.valueOf(configDto.getAlertMode())),"不支持此报警模式");
    }

    /**
     * check 请求参数
     */
    private void checkQueryAlarmReq(AccountAlarmQueryReq configDto){
        Preconditions.checkNotNull(configDto.getActivityId(),"活动id不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(configDto.getCustomerNo()),"账户商户号不能为空");
    }

    /**
     * 构造添加账务监控接口参数
     */
    private TreeMap<String, String> buildAddOrUpdateAlarmReq(AccountAlarmDetailDto configDto, Integer operType){
        TreeMap<String, String> paramMap = new TreeMap<String, String>();

        paramMap.put("version",ACCOUNT_ALARM_INTERFACE_VERSION );
        paramMap.put("customerNo", configDto.getCustomerNo());
        paramMap.put("activityId", String.valueOf(configDto.getActivityId()));
        paramMap.put("receiver", configDto.getReceiver());
        paramMap.put("alertMode", String.valueOf(configDto.getAlertMode()));
        paramMap.put("extEmailReceiver", configDto.getExtEmailReceiver());
        paramMap.put("extMobileReceiver", configDto.getExtMobileReceiver());
        paramMap.put("notifyInterval", String.valueOf(configDto.getNotifyInterval()));
        paramMap.put("startTime", configDto.getStartTime());
        paramMap.put("endTime", configDto.getEndTime());
        paramMap.put("mode", configDto.getModel());
        paramMap.put("amount", String.valueOf(configDto.getAmount()));
        paramMap.put("remark", configDto.getRemark());

        /**
         * 更新操作，可以更新监控状态，以关闭监控
         * 调用更新接口需要传useStatus，加签
         */
        if(CouponConstants.UPDATE_ACCOUNT_ALARM_OPER_TYPE.equals(operType)){
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
    private TreeMap<String, String> buildQueryAlarmReq(AccountAlarmQueryReq configDto){
        TreeMap<String, String> paramMap = new TreeMap<String, String>();
        paramMap.put("version",ACCOUNT_ALARM_INTERFACE_VERSION );
        paramMap.put("customerNo", configDto.getCustomerNo());
        paramMap.put("activityId", String.valueOf(configDto.getActivityId()));
        setSignInfo(paramMap,configDto.getCustomerNo());
        return paramMap;
    }

    private static final Logger logger = LoggerFactory.getLogger(AccountAlarmServiceImpl.class);

    /**
     * 账务金额监控添加报警http接口
     */
    @Value("${addCoupon.account.monitor.url}")
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
     * GSON 线程安全
     */
    private static final Gson gson = new GsonBuilder().create();

    @Autowired
    private AlarmQconfig alarmQconfig;

    private static final String ACCOUNT_ALARM_INTERFACE_VERSION = "20140808";
}
