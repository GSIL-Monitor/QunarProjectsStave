package com.qunar.fintech.plat.admin.query.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.qunar.fintech.plat.admin.query.dao.repay.LoanInfoDao;
import com.qunar.fintech.plat.admin.query.dao.repay.UserRepayPlanDao;
import com.qunar.fintech.plat.admin.query.entity.TblLoanInfo;
import com.qunar.fintech.plat.admin.query.entity.TblVirtualContract;
import com.qunar.fintech.plat.admin.query.entity.UserProductInfo;
import com.qunar.fintech.plat.admin.query.entity.UserRepayPlan;
import com.qunar.fintech.plat.admin.query.enums.DataTypeEnum;
import com.qunar.fintech.plat.admin.query.exception.FppException;
import com.qunar.fintech.plat.admin.query.service.MessageService;
import com.qunar.fintech.plat.admin.query.service.RepayService;
import com.qunar.fintech.plat.admin.query.service.SecureStorageService;
import com.qunar.fintech.plat.admin.query.service.UserProductInfoService;
import com.qunar.fintech.plat.admin.query.service.VirtualContractService;
import com.qunar.fintech.plat.admin.query.vo.BaseResponse;
import com.qunar.fintech.plat.admin.query.vo.OverdueRepayPlan;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.fintech.plat.admin.query.vo.UrgeRepayReqVo;
import com.qunar.pay.finance.qf.commons.api.enums.OrgChannelEnum;
import com.qunar.pay.finance.qf.commons.api.enums.ProductEnum;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import com.qunar.pay.finance.qf.commons.utils.base.CalcUtil;
import com.qunar.pay.finance.qf.commons.utils.base.DateUtil;
import com.qunar.pay.finance.repaykernel.api.manage.dto.request.QueryUserRepayIndAgentReqReq;
import com.qunar.pay.finance.repaykernel.api.manage.dto.request.UserRepayIndAgentReqDto;
import com.qunar.pay.finance.repaykernel.api.manage.dto.response.QueryUserRepayIndAgentReqResp;
import com.qunar.pay.finance.repaykernel.api.manage.dto.response.UserRepayIndAgentResp;
import com.qunar.pay.finance.repaykernel.api.manage.facade.RepayKernelManageFacade;
import com.qunar.pay.finance.repaykernel.api.manage.facade.UserRepayIndAgentFacade;
import com.qunar.pay.finance.repaykernel.api.userrepay.dto.request.CalculateAdvanceRepayReqDto;
import com.qunar.pay.finance.repaykernel.api.userrepay.dto.request.UserRepayInfoDto;
import com.qunar.pay.finance.repaykernel.api.userrepay.dto.response.CalculateAdvanceRepayRespDto;
import com.qunar.pay.finance.repaykernel.api.userrepay.enums.RepayEntryEnum;
import com.qunar.pay.g2.api.message.dto.SendMessageRequestDto;
import com.qunar.pay.g2.api.message.dto.SendMessageResponseDto;
import com.qunar.pay.g2.api.message.enums.MessageType;
import com.qunar.pay.g2.api.message.enums.SendStatus;
import com.qunar.tc.core.info.api.KeyType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 * Description:
 * User: weiduan
 * Date: 2018-02-05
 * Time: 下午4:23
 */
@Controller
@RequestMapping("/finance/overDue")
public class OverDueMsgController {

    @RequiresPermissions("finance:query")
    @GetMapping("/list")
    String queryAccount() {
        return prefix + "/overDue";
    }

    /**
     *  逾期借据查询
     */
    @RequiresPermissions("finance:query")
    @RequestMapping(value = "/queryOverdueRepayPlan")
    @ResponseBody
    public QueryResponse<OverdueRepayPlan> queryOverdueRepayPlan(@RequestBody QueryDto queryDto) {
        logger.info("===queryOverdueRepayPlan param queryDto is {} ", queryDto);
        ParamChecker.notBlank(queryDto.getProductNo(),"productNo cannot be blank");
        ParamChecker.notBlank(queryDto.getOrgChannel(),"orgChannel cannot be blank");
        ParamChecker.isTrue(StringUtils.isNotBlank(queryDto.getUserId()) || StringUtils.isNotBlank(queryDto.getMobile()),"userId and mobile cannot be null at the same");
        QueryResponse<OverdueRepayPlan> resp = new QueryResponse<>();
        try {
            // 若只输入手机号时，可能他查处多个用户
            List<String> userIdList = Lists.newArrayList();
            if (StringUtils.isBlank(queryDto.getUserId())) {
                userIdList = getUserIdByMobileProductNo(queryDto.getMobile(), queryDto.getProductNo());
            } else {
                userIdList.add(queryDto.getUserId());
            }

            // 通过输入的userId，获取用户的主userId
            List<String> mainUserIdList = Lists.newArrayList();
            for (String userId : userIdList) {
                mainUserIdList.add(getMainUserId(userId, queryDto.getProductNo()));
            }
            if (CollectionUtils.isEmpty(mainUserIdList)) {
                throw new Exception("用户的主userId获取失败，请确认");
            }
            logger.info("===queryOverdueRepayPlan mainUserIdList={}", mainUserIdList);

            List<OverdueRepayPlan> urgePlanList = Lists.newArrayList();
            int total = repayService.countUserRepayPlanByReqVo(queryDto, mainUserIdList);
            if (total > 0) {
                List<UserRepayPlan> planList = repayService.queryRepayPlanForUrge(queryDto, buildPage(queryDto), mainUserIdList);
                if (CollectionUtils.isNotEmpty(planList)) {
                    Map<String, List<String>> totalLoanMap = Maps.newHashMap();
                    Map<String, TblVirtualContract> contractMap = Maps.newHashMap();
                    for (String userId : mainUserIdList) {
                        totalLoanMap.putAll(getUrgedRepayPlanByTime(userId, 60));
                        contractMap.put(userId, virtualContractService.queryByUserIdProductNo(userId, queryDto.getProductNo()));
                    }
                    urgePlanList = assembleOverdueRepayPlan(planList, contractMap, totalLoanMap);
                }
            }
            resp.setRows(urgePlanList);
            resp.setTotal(total);
        } catch (Exception e) {
            logger.error("===queryOverdueRepayPlan 异常:{}", e.getMessage(), e);
            resp.setRows(null);
            resp.setTotal(0);
        }
        logger.info("===queryOverdueRepayPlan 返回结果resultMap={}", resp);
        return resp;
    }

    /**
     *  待催收订单校验
     */
    @RequiresPermissions("finance:query")
    @RequestMapping(value = "/checkSelectedRepayPlan")
    @ResponseBody
    public ModelAndView checkSelectedRepayPlan(String repayReqVoListStr) {
        logger.info("===checkSelectedRepayPlan 选定待审核的逾期单，repayReqVoListStr={}", repayReqVoListStr);
        Boolean result = true;
        String returnMsg = null;
        String productNo = null;
        String orgChannel = null;
        try {
            List<UrgeRepayReqVo> repayReqVoList = new Gson().fromJson(repayReqVoListStr, new TypeToken<List<UrgeRepayReqVo>>(){}.getType());
            if (repayReqVoList.size() <= 0) {
                throw new Exception("未选择待催收的单");
            }
            logger.debug("===checkSelectedRepayPlan repayReqVoList={}", repayReqVoList);

            String userId = repayReqVoList.get(0).getUserId();
            productNo = repayReqVoList.get(0).getProductNo();
            orgChannel = this.getOrgChannel(repayReqVoList.get(0).getOrgChannel());
            for (UrgeRepayReqVo reqVo : repayReqVoList) {
                if (!userId.equals(reqVo.getUserId())) {
                    throw new Exception("当前勾选的催款订单不属于同一个账户，无法发起催款请求，请返回后重新选择");
                }
                if (!productNo.equals(reqVo.getProductNo())) {
                    throw new Exception("当前勾选的催款订单不属于同一个业务，无法发起催款请求，请返回后重新选择");
                }
                if (!orgChannel.equals(getOrgChannel(reqVo.getOrgChannel()))) {
                    throw new Exception("当前勾选的催款订单不属于同一个渠道，无法发起催款请求，请返回后重新选择");
                }
            }
            returnMsg = this.generateCheckPlanReturnMsg(repayReqVoList,productNo);
        } catch (Exception e) {
            logger.error("===checkSelectedRepayPlan：异常:{}", e.getMessage(), e);
            returnMsg = e.getMessage();
            result = false;
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("action", "launchUrgeRepay");
        mav.addObject("repayReqVoList", repayReqVoListStr);
        mav.addObject("notice", returnMsg);
        mav.addObject("result", result);
        mav.addObject("productNo", productNo);
        mav.addObject("orgChannel", orgChannel);
        mav.setViewName("query/sendDetail");
        logger.info("====checkSelectedRepayPlan# result={}, returnMsg={}", result, returnMsg);
        return mav;

    }

    /**
     *  催收订单下单
     */
    @RequiresPermissions("finance:query")
    @RequestMapping(value = "/launchUrgeRepay")
    @ResponseBody
    public BaseResponse launchUrgeRepay(String repayType, String repayReqVoList, String msgMobile, String remark) {
        logger.info("====launchUrgeRepay repayReqVoListStr{}, msgMobile={}", repayReqVoList, msgMobile);
        BaseResponse resp = new BaseResponse();
        try {
            List<UrgeRepayReqVo> reqVoList = new Gson().fromJson(repayReqVoList, new TypeToken<List<UrgeRepayReqVo>>(){}.getType());
            if (reqVoList.size() <= 0) {
                throw new Exception("催收下单，未传入下单信息或者解析json出错");
            }
            logger.debug("====launchUrgeRepay reqVoList={}", reqVoList);

            // 请求 repayKernel 下单
            UserRepayIndAgentReqDto repayIndAgentReqDto = new UserRepayIndAgentReqDto();
            repayIndAgentReqDto.setUserId(reqVoList.get(0).getUserId());
            List<UserRepayInfoDto> loanList = Lists.newArrayList();
            Map<String,List<UrgeRepayReqVo>> classifyRepayReqs = classifyUrgeRepayReqVo(reqVoList);
            if (RepayEntryEnum.IND_AGENT_ADV_FPP.name().equals(repayType)) {
                for (String loanNo : classifyRepayReqs.keySet()) {
                    UserRepayInfoDto infoDto = new UserRepayInfoDto();
                    infoDto.setLoanProvideNo(loanNo);
                    loanList.add(infoDto);
                }
            } else {
                for (UrgeRepayReqVo reqVo : reqVoList) {
                    UserRepayInfoDto infoDto = new UserRepayInfoDto();
                    infoDto.setLoanProvideNo(reqVo.getLoanProvideNo());
                    infoDto.setDueDate(DateUtil.date2str(new Date(Long.valueOf(reqVo.getDueDate())), "yyyyMMdd"));
                    loanList.add(infoDto);
                }
            }

            repayIndAgentReqDto.setLoanList(loanList);
            repayIndAgentReqDto.setRequestTime(DateUtil.date2str(new Date(), "yyyyMMddHHmmss"));
            repayIndAgentReqDto.setAgentMobile(msgMobile);
            repayIndAgentReqDto.setRepayEntry(repayType);
            repayIndAgentReqDto.setRemark(remark);

            logger.info("====launchUrgeRepay repayIndAgentReqDto={}", repayIndAgentReqDto);
            QResponse<UserRepayIndAgentResp> qResponse = userRepayIndAgentFacade.createUserRepayIndAgent(repayIndAgentReqDto);
            logger.info("====launchUrgeRepay qResponse={}", qResponse);
            if (qResponse == null) {
                throw new Exception("向还款核心下单失败");
            }

            if (!qResponse.isSuccess() || qResponse.getData() == null) {
                throw new Exception(qResponse.getReturnMsg());
            }

            ProductEnum productEnum = getProductNo(reqVoList.get(0).getProductNo());
            TblVirtualContract contract =
                    virtualContractService.queryByUserIdProductNo(reqVoList.get(0).getUserId(), productEnum.getProductNo());

            SendMessageResponseDto respDto = this.sendMsg(qResponse.getData(), msgMobile, reqVoList.get(0), contract.getUserName());
            if(respDto != null && "000000".equals(respDto.getResponseCode()) && SendStatus.SUCCESS.equals(respDto.getSendStatus())){
                resp.setSuccessReq();
                resp.setReturnMsg("发送成功! 短信已发送给手机" + msgMobile);
            } else {
                resp.setFailReq();
                resp.setReturnMsg("发送失败!\n" + "当先所选催款订单因贷款方原因无法发起发送成功! 短信已发送给手机");
            }
        } catch (Exception e) {
            logger.error("====launchUrgeRepay 异常:{}", e.getMessage(), e);
            resp = new BaseResponse();
            resp.setFailReq();
            resp.setReturnMsg(e.getMessage());
        }
        logger.info("====launchUrgeRepay result={}", resp);
        return resp;
    }

    private SendMessageResponseDto sendMsg(UserRepayIndAgentResp agentResp, String msgMobile, UrgeRepayReqVo reqVo, String userName) {
        logger.info("====sendMsg UserRepayIndAgentResp={},reqVo={},user_name={}", agentResp, reqVo, userName);
        SendMessageRequestDto reqDto = new SendMessageRequestDto();
        reqDto.setMessageAddress(msgMobile);
        reqDto.setMessageType(MessageType.FINANCE_NOTIFY);
        reqDto.setTemplateType(COMMON_TEMPLATE);
        Map<String,String> contentMap = Maps.newHashMap();
        contentMap.put("busiName", getChineseProductNo(reqVo.getProductNo()));
        contentMap.put("userName", userName);
        contentMap.put("amount", agentResp.getRepayAmt().toString());
        contentMap.put("url", agentResp.getUrl());
        reqDto.setTemplateParameters(contentMap);

        SendMessageResponseDto respDto = messageService.sendMsg(reqDto);
        logger.info("====sendMsg 短信发送结果={}",respDto);
        return respDto;
    }

    private ProductEnum getProductNo(String productNo) {
        if (ProductEnum.ifCASH(productNo)) {
            return ProductEnum.CASH;
        }
        return ProductEnum.IOUS;
    }

    private String getChineseProductNo(String productNo) {
        if (ProductEnum.ifCASH(productNo)) {
            return "借趣花";
        }
        return "拿去花";
    }

    private List<String> getUserIdByMobileProductNo(String mobile, String productNo) throws Exception {
        if (StringUtils.isBlank(mobile)) {
            throw new Exception("手机号没有填写");
        }

        List<String> userIdList = Lists.newArrayList();
        String mobileByTc = mobile;
        if (!KeyType.phone.encrypted(mobile)) {
            mobileByTc= secureStorageService.enCryptData(mobile, DataTypeEnum.MOBILE_TYPE);
        }
        List<TblVirtualContract> contractList = virtualContractService.queryByMobileProductNo(mobileByTc, productNo);
        if (CollectionUtils.isEmpty(contractList)) {
            throw new Exception("填写的手机号没有对应的虚拟合同信息");
        }
        for (TblVirtualContract contract : contractList) {
            if (!userIdList.contains(contract.getUserId())) {
                userIdList.add(contract.getUserId());
            }
        }
        return userIdList;
    }

    private String getMainUserId(String userId, String productNo) throws Exception {
        logger.debug("===getMainUserId userId={}, productNo={}", userId, productNo);
        // 设置主uid
        List<UserProductInfo> infoList = userProductInfoService.queryUserProductInfoByUserId(null, productNo, userId);
        logger.debug("===getMainUserId UserProductInfo={}", infoList);
        if (CollectionUtils.isNotEmpty(infoList)) {
            return infoList.get(0).getMainUserId();
        }
        return userId;
    }

    private Map<String, List<String>> getUrgedRepayPlanByTime(String userId, int intervalMinute) throws Exception {
        logger.info("===getUrgedRepayPlanByTime 查在有效期内单，userId={}，intervalMinute={}", userId, intervalMinute);
        Map<String, List<String>> loanNoDueDateMap = Maps.newHashMap();
        Date current = new Date();

        QueryUserRepayIndAgentReqReq req = new QueryUserRepayIndAgentReqReq();
        req.setUserId(userId);
        QResponse<QueryUserRepayIndAgentReqResp> qResponse = userRepayIndAgentFacade.queryUserRepayIndAgentReq(req);
        logger.info("===getUrgedRepayPlanByTime 查在有效期内单，qResponse={}", qResponse);
        if (qResponse == null || !qResponse.isSuccess()) {
            throw new Exception("查询用户已发起的催收单失败");
        }

        if (qResponse.getData() != null && CollectionUtils.isNotEmpty(qResponse.getData().getUserRepayIndAgentRespList())) {
            for (UserRepayIndAgentResp agentResp : qResponse.getData().getUserRepayIndAgentRespList()) {
                // 若不在有效期内，排除掉。有效期 intervalMinute：分钟数。
                if (DateUtil.dateAfterSeconds(current, -(60 * intervalMinute)).after(agentResp.getCreateTime())) {
                    continue;
                }

                for (UserRepayInfoDto repayInfo : agentResp.getLoanList()) {
                    if (loanNoDueDateMap.containsKey(repayInfo.getLoanProvideNo())) {
                        loanNoDueDateMap.get(repayInfo.getLoanProvideNo()).add(repayInfo.getDueDate());
                    } else {
                        List<String> dueDateList = Lists.newArrayList(repayInfo.getDueDate());
                        loanNoDueDateMap.put(repayInfo.getLoanProvideNo(), dueDateList);
                    }
                }
            }
        }
        logger.info("===getUrgedRepayPlanByTime 查在有效期内单，loanNoDueDateMap={}", loanNoDueDateMap);
        return loanNoDueDateMap;
    }

    private List<OverdueRepayPlan> assembleOverdueRepayPlan(List<UserRepayPlan> planList, Map<String, TblVirtualContract> contractMap,
                                                            Map<String, List<String>> loanMap) {
        List<OverdueRepayPlan> overdueRepayPlanList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(planList)) {
            return overdueRepayPlanList;
        }

        Date current = DateUtil.getZenoByToDays();
        // 注意：不要用反射直接赋值，启动时可能有坑
        for (UserRepayPlan plan : planList) {
            OverdueRepayPlan repayPlan = new OverdueRepayPlan();
            if (contractMap.get(plan.getUserId()) != null) {
                repayPlan.setUserName(contractMap.get(plan.getUserId()).getUserName());
                repayPlan.setMobile(contractMap.get(plan.getUserId()).getMobile());
            }
            repayPlan.setUserId(plan.getUserId());
            repayPlan.setProductNo(plan.getProductNo());
            repayPlan.setTppCode(plan.getTppCode());
            repayPlan.setOrgChannel(plan.getOrgChannel());
            repayPlan.setLoanProvideNo(plan.getLoanProvideNo());
            repayPlan.setDueDate(plan.getDueDate());
            repayPlan.setRepayIndex(plan.getRepayIndex());
            repayPlan.setPrcpAmt(plan.getPrcpAmt());
            repayPlan.setSetlFineAmt(plan.getSetlFineAmt());
            repayPlan.setIntAmt(plan.getIntAmt());
            repayPlan.setSetlIntAmt(plan.getSetlIntAmt());
            repayPlan.setSpreadsAmt(plan.getSpreadsAmt());
            repayPlan.setSetlSpreadsAmt(plan.getSetlSpreadsAmt());
            repayPlan.setFineAmt(plan.getFineAmt());
            repayPlan.setSetlFineAmt(plan.getSetlFineAmt());
            repayPlan.setStatus(plan.getStatus());
            repayPlan.setLockStatus(plan.getLockStatus());
            repayPlan.setGateStatus(plan.getGateStatus());
            repayPlan.setRepayFlag(plan.getRepayFlag());
            repayPlan.setActRepayDate(plan.getActRepayDate());
            repayPlan.setLockTime(plan.getLockTime());
            repayPlan.setLastSyncTime(plan.getLastSyncTime());
            repayPlan.setCreateTime(plan.getCreateTime());
            repayPlan.setUpdateTime(plan.getUpdateTime());
            try {
                int overDays = DateUtil.daysDiff(plan.getDueDate(), current);
                repayPlan.setOverDueDays(overDays > 0 ? overDays + "" : "0");
            } catch (ParseException e) {
                logger.error("assembleOverdueRepayPlan 计算逾期天数异常", e);
            }
            repayPlan.setRepayTotalAmt(plan.getUserRepayTotalAmount());
            repayPlan.setMsgStatus(this.getMsgStatus(loanMap, plan.getLoanProvideNo(), plan.getDueDate()));

            TblLoanInfo tblLoanInfo = loanInfoDao.queryLoanInfoByLoanProvideNo(plan.getLoanProvideNo());
            repayPlan.setLoanTerm(tblLoanInfo.getLoanTerm());
            repayPlan.setTerm(repayPlan.getRepayIndex() + "/" + repayPlan.getLoanTerm());

            overdueRepayPlanList.add(repayPlan);
        }
        return overdueRepayPlanList;
    }

    private String getMsgStatus(Map<String, List<String>> loanMap, String loanProvideNo, Date dueDate) {
        logger.info("===getMsgStatus 催收短信状态，loanProvideNo={},dueDate={}", loanProvideNo, dueDate);
        String dueDateStr = DateUtil.date2str(dueDate, DateUtil.DEFAULT_SYS_DATE_FMT);
        logger.info("===getMsgStatus 催收短信状态，dueDateStr={}", dueDateStr);
        if (!loanMap.containsKey(loanProvideNo)) {
            return "未发送";
        }

        if (loanMap.get(loanProvideNo).contains(dueDateStr)) {
            logger.info("===getMsgStatus 过滤已发送，loanProvideNo={},dueDate={}", loanProvideNo, dueDateStr);
            return "已发送";
        }
        return "未发送";
    }

    private String getOrgChannel(String orgChannel) {
        if (OrgChannelEnum.QUICKPASS_SH.getCode().equals(orgChannel)) {
            return OrgChannelEnum.QUNAR.getCode();
        }
        return orgChannel;
    }

    /**
     * 催收借据校验：
     * 同一个借据号若有多期需要催收，则选择的期数必须从最小可催收期数开始，且必须连续
     * @param repayReqVoList
     */
    private String generateCheckPlanReturnMsg(List<UrgeRepayReqVo> repayReqVoList,String productNo) throws Exception {
        // 按照借据号分类信息
        Map<String, List<UrgeRepayReqVo>> reqMap = classifyUrgeRepayReqVo(repayReqVoList);

        //校验并获取userRepayPlan记录，不得跨期序选择
        List<UserRepayPlan> planList = checkRepayReqOrder(reqMap);

        Map<String,List<UserRepayPlan>> classifRepayPlan = classifyUserRepayPlan(planList);


        BigDecimal normalRepayAmt = BigDecimal.ZERO;
        for (UserRepayPlan plan : planList) {
            normalRepayAmt = CalcUtil.add(normalRepayAmt, plan.getUserRepayTotalAmount());
        }
        /* 提前还款试算 */
        String advanceNoticeStr = "";
        if(ProductEnum.ifCASH(productNo)){
            BigDecimal advanceRepayAmt = BigDecimal.ZERO;
            for (Map.Entry<String, List<UserRepayPlan>> loanRepayPlanMaps : classifRepayPlan.entrySet()) {

                CalculateAdvanceRepayReqDto reqDto = buildCalculateAdvanceRepayReqDto(loanRepayPlanMaps.getKey());
                if (reqDto == null) {
                    continue;
                }
                QResponse<CalculateAdvanceRepayRespDto> response = repayKernelManageFacade.calculateAdvanceRepayment(reqDto);
                if (!response.isSuccess() || null == response.getData()) {
                    throw new FppException("提前还款试算失败");
                }
                advanceRepayAmt = CalcUtil.addAllIgnoreNull(advanceRepayAmt,response.getData().getTotalRepayAmt());
            }
            advanceNoticeStr = "提前还款金额合计："+ advanceRepayAmt +"元，";
        }
        return "您当前已勾选" + planList.size() + "笔催款订单，"+ advanceNoticeStr +"按期还款金额合计:" + normalRepayAmt + "元，请确认接受催收短信的手机号:";
    }

    /**
     * 分类催收信息：
     * key:loanProvideNo, value 按照dueDate排序的List<UrgeRepayReqVo>
     */
    private Map<String, List<UrgeRepayReqVo>> classifyUrgeRepayReqVo(List<UrgeRepayReqVo> repayReqVoList){
        Map<String, List<UrgeRepayReqVo>> map = Maps.newHashMap();
        for (UrgeRepayReqVo reqVo : repayReqVoList) {
            if (map.containsKey(reqVo.getLoanProvideNo())){
                map.get(reqVo.getLoanProvideNo()).add(reqVo);
                continue;
            }
            map.put(reqVo.getLoanProvideNo(), Lists.newArrayList(reqVo));
        }

        //按照dueDate从小到大排序
        for (Map.Entry<String, List<UrgeRepayReqVo>> entry : map.entrySet()) {
            Collections.sort(entry.getValue(), new Comparator<UrgeRepayReqVo>() {
                @Override
                public int compare(UrgeRepayReqVo o1, UrgeRepayReqVo o2) {
                    return Long.valueOf(o1.getDueDate()).compareTo(Long.valueOf(o2.getDueDate()));
                }
            });
        }
        return map;
    }

    /**
     * 校验并获取对应的userRepayPlan记录
     */
    private List<UserRepayPlan> checkRepayReqOrder(Map<String, List<UrgeRepayReqVo>> reqMap) throws Exception {
        List<UserRepayPlan> userRepayPlanList = Lists.newArrayList();
        for (Map.Entry<String, List<UrgeRepayReqVo>> entry : reqMap.entrySet()) {
            userRepayPlanList.addAll(checkSigleLoanNoUrgeRepayReq(entry.getKey(), entry.getValue()));
        }
        return userRepayPlanList;
    }

    private List<UserRepayPlan> checkSigleLoanNoUrgeRepayReq(String loanNo, List<UrgeRepayReqVo> repayReqVoList) throws Exception {
        List<UserRepayPlan> userRepayPlans = repayService.selectByLoanProvideNo(loanNo);
        logger.info("checkSigleLoanNoUrgeRepayReq# userRepayPlans:{}", userRepayPlans);
        if (CollectionUtils.isEmpty(userRepayPlans)){
            throw new Exception("未查询到loanProvideNo:" + loanNo + "对应的还款计划");
        }
        UserRepayPlan firstCanRepayPlan = getFirstCanRepayPlan(userRepayPlans);

        if (firstCanRepayPlan == null){
            throw new Exception("loanProvideNo:" + loanNo + " 对应还款计划都已经结清");
        }

        UrgeRepayReqVo firReqVo = repayReqVoList.get(0);
        if (firstCanRepayPlan.getDueDate().compareTo(new Date(Long.valueOf(firReqVo.getDueDate()))) != 0){
            throw new Exception("没有按期选择. loanProvideNo:" + loanNo + "对应的首个可还款plan到期日为:"+ DateUtil.date2str(firstCanRepayPlan.getDueDate()));
        }

        Map<Date, UserRepayPlan> dueDatePlanMap = Maps.newHashMap();
        for (UserRepayPlan userRepayPlan : userRepayPlans) {
            dueDatePlanMap.put(userRepayPlan.getDueDate(), userRepayPlan);
        }

        List<UserRepayPlan> choosedUserRepayPlans = Lists.newArrayList();
        for (UrgeRepayReqVo repayReqVo : repayReqVoList) {
            choosedUserRepayPlans.add(dueDatePlanMap.get(new Date(Long.valueOf(repayReqVo.getDueDate()))));
        }
        checkOrder(choosedUserRepayPlans);
        return choosedUserRepayPlans;
    }

    private Map<String, List<UserRepayPlan>> classifyUserRepayPlan(List<UserRepayPlan> planList) {
        Map<String,List<UserRepayPlan>> classRepayPlan = Maps.newHashMap();
        for (UserRepayPlan userRepayPlan : planList) {
            if(classRepayPlan.containsKey(userRepayPlan.getLoanProvideNo())){
                classRepayPlan.get(userRepayPlan.getLoanProvideNo()).add(userRepayPlan);
            } else {
                classRepayPlan.put(userRepayPlan.getLoanProvideNo(),Lists.<UserRepayPlan>newArrayList(userRepayPlan));
            }
        }
        return classRepayPlan;
    }

    private void checkOrder(List<UserRepayPlan> choosedUserRepayPlans) throws Exception {
        if (CollectionUtils.isEmpty(choosedUserRepayPlans)){
            throw new Exception("无对应还款计划");
        }
        // 不能跳序选择
        if (choosedUserRepayPlans.size() >= 2){
            UserRepayPlan temp = choosedUserRepayPlans.get(0);
            for (int i = 1; i < choosedUserRepayPlans.size(); i++) {
                if (choosedUserRepayPlans.get(i).getRepayIndex() - temp.getRepayIndex() != 1){
                    throw new Exception("您选择的loanProvideNo:" + temp.getLoanProvideNo() + "第" + choosedUserRepayPlans.get(i).getRepayIndex() + "期还款计划之前还有未还款记录。请按顺序选择！");
                }
                temp = choosedUserRepayPlans.get(i);
            }
        }
    }

    private static UserRepayPlan getFirstCanRepayPlan(List<UserRepayPlan> userRepayPlans) {
        for (UserRepayPlan repayPlan : userRepayPlans) {
            if (repayPlan.isCanRepay()){
                return repayPlan;
            }
        }
        return null;
    }

    private CalculateAdvanceRepayReqDto buildCalculateAdvanceRepayReqDto(String loanProvideNo) {
        List<UserRepayPlan> dbPlans = userRepayPlanDao.selectByLoanProvideNo(loanProvideNo);

        List<UserRepayPlan> filterPlans = Lists.newArrayList();
        for (UserRepayPlan dbPlan : dbPlans) {
            if(!dbPlan.isCanRepay()){
                continue;
            }
            filterPlans.add(dbPlan);
        }
        if(CollectionUtils.isEmpty(filterPlans)){
            return null;
        }
        UserRepayPlan repayPlan = filterPlans.get(0);
        CalculateAdvanceRepayReqDto reqDto = new CalculateAdvanceRepayReqDto();
        reqDto.setLoanProvideNo(repayPlan.getLoanProvideNo());
        reqDto.setOrgChannel(repayPlan.getOrgChannel());
        reqDto.setProductNo(repayPlan.getProductNo());
        reqDto.setUserId(repayPlan.getUserId());
        reqDto.setRequestTime(DateUtil.date2str(new Date(),DateUtil.FMT_yyyyMMddHHmmss));

        BigDecimal advanceRepayPrcpAmt = BigDecimal.ZERO;
        for (UserRepayPlan plan : filterPlans) {
            advanceRepayPrcpAmt = CalcUtil.addAllIgnoreNull(advanceRepayPrcpAmt,CalcUtil.sub(plan.getPrcpAmt(),plan.getSetlPrcpAmt()));
        }
        reqDto.setAdvanceRepayPrcpAmt(advanceRepayPrcpAmt);
        return reqDto;
    }

    private Page buildPage(QueryDto queryDto){
        Page page = new Page();
        page.setPageSize(queryDto.getPageSize());
        page.setCurrentIndex((queryDto.getPageIndex()-1)*queryDto.getPageSize());
        return page;
    }

    @Autowired
    private MessageService messageService;
    @Autowired
    private SecureStorageService secureStorageService;
    @Autowired
    private VirtualContractService virtualContractService;
    @Autowired
    private UserProductInfoService userProductInfoService;
    @Autowired
    private RepayService repayService;
    @Autowired
    private LoanInfoDao loanInfoDao;
    @Resource
    private UserRepayIndAgentFacade userRepayIndAgentFacade;
    @Autowired
    private UserRepayPlanDao userRepayPlanDao;
    @Resource
    private RepayKernelManageFacade repayKernelManageFacade;

    private static final Logger logger = LoggerFactory.getLogger(OverDueMsgController.class);

    private static final String prefix = "/query";

    /* 通用模板（短信内容自定义模板） */
    private static final Integer COMMON_TEMPLATE = 126;

}
