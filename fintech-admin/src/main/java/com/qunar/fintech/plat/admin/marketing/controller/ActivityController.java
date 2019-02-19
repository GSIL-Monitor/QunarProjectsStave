package com.qunar.fintech.plat.admin.marketing.controller;

import com.alibaba.fastjson.JSONObject;
import com.qunar.fintech.marketing.api.admin.facade.ActivityFacade;
import com.qunar.fintech.marketing.api.admin.model.ActivityAccountDto;
import com.qunar.fintech.marketing.api.admin.model.ActivityQueryDto;
import com.qunar.fintech.marketing.api.admin.model.ActivityResponseDto;
import com.qunar.fintech.marketing.api.admin.model.TblActivityDto;
import com.qunar.fintech.plat.admin.marketing.dto.AccountAlarmDto;
import com.qunar.fintech.plat.admin.marketing.service.AccountAlarmConfigService;
import com.qunar.fintech.plat.admin.query.entity.AccountAlarmResp;
import com.qunar.fintech.plat.admin.support.exception.MarketErrorCodes;
import com.qunar.fintech.plat.admin.support.security.AccessLog;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.util.simple.BigDecimalUtil;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.exception.CommonApiErrorCodes;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import com.qunar.pay.g2.api.account.dto.AccountQueryParam;
import com.qunar.pay.g2.api.account.dto.AccountQueryResultDto;
import com.qunar.pay.g2.api.account.service.MerchantAccountQueryFacade;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/marketing/activity")
@Controller
public class ActivityController {

    @GetMapping()
    String list() {
        return prefix + "/list";
    }

    @AccessLog("编辑活动金额报警配置")
    @RequiresPermissions("market:activity:alarm:edit")
    @GetMapping("/editAlarmConfig/{id}")
    String editAlarmConfig(Model model, @PathVariable("id") Long id) {
        model.addAttribute("id", id);
        return "marketing/activity/editAlarmConfig";
    }

    @AccessLog("查询活动金额报警配置")
    @ResponseBody
    @RequiresPermissions("market:activity:alarm:edit")
    @PostMapping("/queryAlarmConfig")
    R queryAlarmConfig(AccountAlarmDto alarmDto) {
        ParamChecker.notNull(alarmDto.getActivityId(), "活动id不能为空");
        alarmDto.setCustomerNo(getActivityCustomerNo(alarmDto));
        AccountAlarmResp result = accountAlarmConfigService.queryActivityAmtAlarmConfig(alarmDto);
        if(result != null && result.getRet() && result.getData()!=null){
            // 账务报警已经配置存在，提示修改，部分可以修改，按钮变为更新
            return R.ok("账务报警已经存在！").put("data", JSONObject.toJSONString(result.getData().get(0)));
        }else if(result != null && result.getRet() && result.getData()==null){
            return R.error("账务报警配置不存在！");
        }else{
            return R.error("查询账务报警配置异常！");
        }
    }

    @AccessLog("更新活动金额报警配置")
    @RequiresPermissions("market:activity:alarm:edit")
    @ResponseBody
    @PostMapping("/addOrUpdateAlarmConfig")
    R addOrUpdateAccountAlarmConfig(AccountAlarmDto alarmDto) {
        ParamChecker.notNull(alarmDto.getActivityId(), "活动id不能为空");
        alarmDto.setCustomerNo(getActivityCustomerNo(alarmDto));

        if(alarmDto.getOperType().equals(CREATE_OPER_TYPE)) {
            accountAlarmConfigService.addActivityAmtAlarmConfig(alarmDto);
            return R.ok("新增报警成功！");
        }else if(alarmDto.getOperType().equals(UPDATE_OPER_TYPE)){
            accountAlarmConfigService.updateActivityAmtAlarmConfig(alarmDto);
            return R.ok("更新报警成功！");
        }else{
            throw new BusiException(CommonApiErrorCodes.PARAM_ERROR,"不支持此操作！,operType="+alarmDto.getOperType());
        }
    }

    @AccessLog("显示活动")
    @RequiresPermissions("market:activity:list")
    @ResponseBody
    @GetMapping("/list")
    List<TblActivityDto> showActivity() {
        return activityFacade.queryAllActivity(new ActivityQueryDto()).getData();
    }

    @AccessLog("添加活动")
    @RequiresPermissions("market:activity:add")
    @GetMapping("/add")
    String addActivity() {
        return "marketing/activity/add";
    }

    @AccessLog("保存活动")
    @RequiresPermissions("market:activity:add")
    @ResponseBody
    @PostMapping("/save")
    R saveActivity(TblActivityDto activityDto) {
        logger.info("save parameter activity is :{}", activityDto);
        checkParam(activityDto);
        try {
            QResponse<ActivityResponseDto> qResponse = activityFacade.addActivity(activityDto);
            if (qResponse.isSuccess() && qResponse.getData() != null) {
                return R.ok();
            } else {
                return R.error(1, qResponse.getReturnMsg());
            }
        } catch (Exception e) {
            logger.error("活动添加失败", e);
            return R.error(2, "创建活动失败");
        }
    }

    @AccessLog("编辑活动")
    @RequiresPermissions("market:activity:edit")
    @GetMapping("/edit/{id}")
    String editActivity(Model model, @PathVariable("id") Long id) {
        ParamChecker.notNull(id, "活动id不能为空");
        ActivityQueryDto queryDto = new ActivityQueryDto();
        queryDto.setId(id);
        model.addAttribute("activity", activityFacade.queryActivityById(queryDto).getData());
        return "marketing/activity/edit";
    }

    @AccessLog("更新活动")
    @RequiresPermissions("market:activity:edit")
    @ResponseBody
    @PostMapping("/update")
    R updateActivity(TblActivityDto activityDto) {
        logger.info("update parameter activity is :{}", activityDto);
        checkParam(activityDto);
        ParamChecker.notNull(activityDto.getId(), "id不能为空");
        try {
            if (activityFacade.updateActivity(activityDto).isSuccess()) {
                return R.ok();
            } else {
                return R.error(1, "更新活动失败");
            }
        } catch (Exception e) {
            logger.error("活动更新失败", e);
            return R.error(2, "活动更新失败");
        }
    }

    @AccessLog("转入金额")
    @RequiresPermissions("market:activity:edit")
    @GetMapping("/accountIn")
    String accountIn(Model model, Long id, String merchantCode) {
        ParamChecker.notNull(id, "活动id不能为空");
        ParamChecker.notBlank(merchantCode, "商户号不能为空");
        ActivityQueryDto queryDto = new ActivityQueryDto();
        queryDto.setId(id);

        BigDecimal cash = BigDecimal.ZERO;
        try{
            cash = queryCashAccount(merchantCode);
        } catch (BusiException e){
            logger.error("查询现金户出错：{}",e.getErrMsg(),e);
            return "error";
        }
        model.addAttribute("id",id);
        model.addAttribute("cash",cash);
        return "marketing/activity/accountIn";
    }

    @AccessLog("转入金额")
    @RequiresPermissions("market:activity:edit")
    @ResponseBody
    @PostMapping("/account-in")
    R transIn(ActivityAccountDto accountDto) {
        logger.info("transIn param is :{}", accountDto);
        ParamChecker.notNull(accountDto, "accountDto不能为空");
        ParamChecker.notNull(accountDto.getActivityId(), "活动id不能为空");
        ParamChecker.notNull(accountDto.getAmount(), "转入金额不能为空");
        try {
            if (activityFacade.accountIn(accountDto).isSuccess()) {
                return R.ok();
            } else {
                return R.error(1, "转入金额失败");
            }
        } catch (Exception e) {
            logger.error("转入金额失败", e);
            return R.error(2, "转入金额失败");
        }
    }

    @AccessLog("转出金额")
    @RequiresPermissions("market:activity:edit")
    @GetMapping("/accountOut")
    String accountOut(Model model, Long id, BigDecimal activityAccount) {
        ParamChecker.notNull(id, "活动id不能为空");
        ParamChecker.notNull(activityAccount, "活动剩余金额不能为空");
        model.addAttribute("id",id);
        model.addAttribute("activityAccount",activityAccount);
        return "marketing/activity/accountOut";
    }

    @AccessLog("转出金额")
    @RequiresPermissions("market:activity:edit")
    @ResponseBody
    @PostMapping("/account-out")
    R transOut(ActivityAccountDto accountDto) {
        logger.info("transOut param is :{}", accountDto);
        ParamChecker.notNull(accountDto, "accountDto不能为空");
        ParamChecker.notNull(accountDto.getActivityId(), "活动id不能为空");
        ParamChecker.notNull(accountDto.getAmount(), "转出金额不能为空");
        try {
            if (activityFacade.accountOut(accountDto).isSuccess()) {
                return R.ok();
            } else {
                return R.error(1, "转出金额失败");
            }
        } catch (Exception e) {
            logger.error("转出金额失败", e);
            return R.error(2, "转出金额失败");
        }
    }

    /**
     * 校验参数
     *
     * @param activityDto
     */
    private void checkParam(TblActivityDto activityDto) {
        ParamChecker.notNull(activityDto, "activityDto不能为空");
        ParamChecker.notBlank(activityDto.getActivityName(), "activityName不能为空");
        ParamChecker.notNull(activityDto.getTotalAmt(), "活动总金额不能为空");
        ParamChecker.notNull(activityDto.getStartTime(), "开始时间不能为空");
        ParamChecker.notNull(activityDto.getEndTime(), "结束时间不能为空");
        ParamChecker.notNull(activityDto.getSwitchStatus(), "开关状态不能为空");
        ParamChecker.isTrue(activityDto.getStartTime().compareTo(activityDto.getEndTime()) < 0, "活动开始时间不能大于结束时间");
        ParamChecker.isTrue(BigDecimalUtil.notLessThanZero(activityDto.getTotalAmt()), "活动总金额不能小于0");
    }

    /**
     * 查询现金账户
     */
    private BigDecimal queryCashAccount(String merchantCode){

        AccountQueryResultDto param = null;
        try{
            param = merchantAccountQueryFacade.queryAccount(merchantCode, "QUNAR");
        }catch(Exception e){
            logger.error("获取现金账户金额错误",e);
            throw new BusiException(MarketErrorCodes.QUERY_ACCOUNT_ERROR);
        }
        if(param == null){
            logger.info(" queryMarketingActivity resule is : null ");
            throw new BusiException(MarketErrorCodes.QUERY_ACCOUNT_ERROR);
        }
        logger.info(" queryMarketingActivity resule is : {}" , param.toString());
        if(!param.getStatus().equalsIgnoreCase(AccountQueryResultDto.STATUS_SUCCESS)){
            throw new BusiException(MarketErrorCodes.QUERY_ACCOUNT_EXCE);
        }
        AccountQueryParam paramObj = param.getAccountQueryParam();
        if(paramObj == null ){
            throw new BusiException(MarketErrorCodes.QUERY_ACCOUNT_EXCE);
        }
        logger.info(" queryMarketingActivity AccountQueryParam resule is : " + paramObj.toString());
        return paramObj.getValidBalance();
    }

    /**
     * 查询活动现金户名称
     * @param alarmDto
     * @return
     */
    private String getActivityCustomerNo(AccountAlarmDto alarmDto){
        ActivityQueryDto activityQueryDto = new ActivityQueryDto();
        activityQueryDto.setId(alarmDto.getActivityId());
        QResponse<TblActivityDto> response = activityFacade.queryActivityById(activityQueryDto);
        if(response == null || !response.isSuccess()){
            throw new BusiException(CommonApiErrorCodes.PARAM_ERROR,"活动不存在，id="+alarmDto.getActivityId());
        }
        return response.getData().getMerchantCode();
    }

    private static final String prefix = "marketing/activity";

    private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Resource
    private ActivityFacade activityFacade;

    @Resource
    private MerchantAccountQueryFacade merchantAccountQueryFacade;

    @Resource
    private AccountAlarmConfigService accountAlarmConfigService;

    private static final Integer CREATE_OPER_TYPE = 0;

    private static final Integer UPDATE_OPER_TYPE = 1;

}
