package com.qunar.fintech.plat.admin.query.controller;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.qunar.fintech.plat.admin.query.dao.ious.TblRouteProductDao;
import com.qunar.fintech.plat.admin.query.dao.repay.LoanInfoDao;
import com.qunar.fintech.plat.admin.query.entity.QueryCashReqRecord;
import com.qunar.fintech.plat.admin.query.entity.TblLoanInfo;
import com.qunar.fintech.plat.admin.query.entity.TblScheduleDetail;
import com.qunar.fintech.plat.admin.query.entity.UserRepayReq;
import com.qunar.fintech.plat.admin.query.entity.UserRepayWithhold;
import com.qunar.fintech.plat.admin.query.enums.DataTypeEnum;
import com.qunar.fintech.plat.admin.query.enums.RepayStatusEnum;
import com.qunar.fintech.plat.admin.query.service.LoanCashQueryService;
import com.qunar.fintech.plat.admin.query.service.QueryIdsService;
import com.qunar.fintech.plat.admin.query.service.QueryService;
import com.qunar.fintech.plat.admin.query.service.RepayService;
import com.qunar.fintech.plat.admin.query.service.SecureStorageService;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.PayDetailParamVo;
import com.qunar.fintech.plat.admin.query.vo.QueryAccountRecord;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import com.qunar.fintech.plat.admin.query.vo.QueryRefundRecord;
import com.qunar.fintech.plat.admin.query.vo.QueryRepayRecord;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.fintech.plat.admin.query.vo.TppInfo;
import com.qunar.fintech.plat.admin.query.vo.UserRepayDetail;
import com.qunar.fintech.plat.admin.query.vo.UserRepayVo;
import com.qunar.fintech.util.simple.DateUtils;
import com.qunar.pay.finance.ious.common.enums.OrgChannelEnum;
import com.qunar.pay.finance.ious.common.enums.ProductEnum;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import com.qunar.pay.g2.api.ious.dto.UpdateMobileReqDto;
import com.qunar.pay.g2.api.ious.facade.IousManageFacade;
import com.qunar.pay.g2.api.ious.response.dto.IousBaseRespDto;
import com.qunar.tc.core.info.api.KeyType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RequestMapping("/finance/query")
@Controller
public class QueryController {

    @GetMapping()
    String list() {
        return prefix + "/account";
    }

    @RequiresPermissions("finance:query")
    @GetMapping("/{type}")
    String queryAccount(Model model, @PathVariable("type") String type) {
        model.addAttribute("type",type);
        return prefix + "/query";
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @PostMapping("/account-info")
    QueryResponse<QueryAccountRecord> queryAccountInfo(@RequestBody QueryDto queryDto) {
        logger.info("queryAccountInfo param queryDto is {} ", queryDto);
        checkParam(queryDto);
        QueryResponse<QueryAccountRecord> response = null;
        if (StringUtils.isBlank(queryDto.getUserId())) {
            if (!Strings.isNullOrEmpty(queryDto.getMobile()) && !KeyType.phone.encrypted(queryDto.getMobile())) {
                response = queryService.queryAccount(queryDto, buildPage(queryDto));
                if (null == response.getRows() || response.getRows().size() <= 0) {
                    queryDto.setMobile(secureStorageService.enCryptData(queryDto.getMobile(), DataTypeEnum.MOBILE_TYPE));
                    response = queryService.queryAccount(queryDto, buildPage(queryDto));
                }
            } else if (!Strings.isNullOrEmpty(queryDto.getIdentity()) && !KeyType.personal_id.encrypted(queryDto.getIdentity())) {
                response = queryService.queryAccount(queryDto,buildPage(queryDto));
                if (null == response.getRows() || response.getRows().size() <= 0){
                    queryDto.setIdentity(secureStorageService.enCryptData(queryDto.getIdentity(), DataTypeEnum.IDENTITY_CARD));
                    response = queryService.queryAccount(queryDto,buildPage(queryDto));
                }
            } else {
                response = queryService.queryAccount(queryDto,buildPage(queryDto));
            }
        } else {
            response = queryService.queryAccount(queryDto,buildPage(queryDto));
        }
        return response;
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @PostMapping("/refund-info")
    QueryResponse<QueryRefundRecord> queryRefundInfo(@RequestBody QueryDto queryDto) {
        logger.info("queryRefundInfo param queryDto is {} : ",queryDto);
        checkParam(queryDto);
//        mobileToId(queryDto);
        QueryResponse<QueryRefundRecord> response = null;
        if(StringUtils.isBlank(queryDto.getUserId())) {
            if (!Strings.isNullOrEmpty(queryDto.getMobile()) && !KeyType.phone.encrypted(queryDto.getMobile())) {
                response = queryService.queryRefund(queryDto, buildPage(queryDto));
                if (null == response.getRows() || response.getRows().size() <= 0) {
                    queryDto.setMobile(secureStorageService.enCryptData(queryDto.getMobile(), DataTypeEnum.MOBILE_TYPE));
                    response = queryService.queryRefund(queryDto, buildPage(queryDto));
                }
            } else if (!Strings.isNullOrEmpty(queryDto.getIdentity()) && !KeyType.personal_id.encrypted(queryDto.getIdentity())) {
                response = queryService.queryRefund(queryDto, buildPage(queryDto));
                if (null == response.getRows() || response.getRows().size() <= 0) {
                    queryDto.setIdentity(secureStorageService.enCryptData(queryDto.getIdentity(), DataTypeEnum.IDENTITY_CARD));
                    response = queryService.queryRefund(queryDto, buildPage(queryDto));
                }
            } else {
                response = queryService.queryRefund(queryDto,buildPage(queryDto));
            }
        } else {
            response = queryService.queryRefund(queryDto, buildPage(queryDto));
        }
        return response;
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @PostMapping("/repay-ious")
    QueryResponse<QueryRepayRecord> queryIousRepayInfo(@RequestBody QueryDto queryDto) {
        logger.info("queryRepayInfo-ious param queryDto is {} : ",queryDto);
        checkParam(queryDto);
//        mobileToId(queryDto);
        QueryResponse<QueryRepayRecord> response = null;
        if(StringUtils.isBlank(queryDto.getUserId())) {
            if (!Strings.isNullOrEmpty(queryDto.getMobile()) && !KeyType.phone.encrypted(queryDto.getMobile())) {
                response = queryService.queryRepay(queryDto, buildPage(queryDto));
                if (null == response.getRows() || response.getRows().size() <= 0) {
                    queryDto.setMobile(secureStorageService.enCryptData(queryDto.getMobile(), DataTypeEnum.MOBILE_TYPE));
                    response = queryService.queryRepay(queryDto, buildPage(queryDto));
                }
            } else if (!Strings.isNullOrEmpty(queryDto.getIdentity()) && !KeyType.personal_id.encrypted(queryDto.getIdentity())) {
                response = queryService.queryRepay(queryDto, buildPage(queryDto));
                if (null == response.getRows() || response.getRows().size() <= 0) {
                    queryDto.setIdentity(secureStorageService.enCryptData(queryDto.getIdentity(), DataTypeEnum.IDENTITY_CARD));
                    response = queryService.queryRepay(queryDto, buildPage(queryDto));
                }
            } else {
                response = queryService.queryRepay(queryDto,buildPage(queryDto));
            }
        } else {
            response = queryService.queryRepay(queryDto,buildPage(queryDto));
        }
        return response;
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @PostMapping("/repay-cash")
    QueryResponse<QueryRepayRecord> queryCashRepayInfo(@RequestBody QueryDto queryDto) {
        logger.info("queryRepayInfo-cash param queryDto is {} : ",queryDto);
        checkParam(queryDto);
//        mobileToId(queryDto);
        QueryResponse<QueryRepayRecord> response = null;
        if(StringUtils.isBlank(queryDto.getUserId())) {
            if (!Strings.isNullOrEmpty(queryDto.getMobile()) && !KeyType.phone.encrypted(queryDto.getMobile())) {
                response = queryService.queryRepay(queryDto, buildPage(queryDto));
                if (null == response.getRows() || response.getRows().size() <= 0) {
                    queryDto.setMobile(secureStorageService.enCryptData(queryDto.getMobile(), DataTypeEnum.MOBILE_TYPE));
                    response = queryService.queryRepay(queryDto, buildPage(queryDto));
                }
            } else if (!Strings.isNullOrEmpty(queryDto.getIdentity()) && !KeyType.personal_id.encrypted(queryDto.getIdentity())) {
                response = queryService.queryRepay(queryDto, buildPage(queryDto));
                if (null == response.getRows() || response.getRows().size() <= 0) {
                    queryDto.setIdentity(secureStorageService.enCryptData(queryDto.getIdentity(), DataTypeEnum.IDENTITY_CARD));
                    response = queryService.queryRepay(queryDto, buildPage(queryDto));
                }
            } else {
                response = queryService.queryRepay(queryDto,buildPage(queryDto));
            }
        } else {
            response = queryService.queryRepay(queryDto,buildPage(queryDto));
        }
        return response;
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @PostMapping("/loan-cash")
    QueryResponse<QueryCashReqRecord> queryLoanCashInfo(@RequestBody QueryDto queryDto) throws Exception {
        logger.info("queryLoanCashInfo param queryDto is {} : ",queryDto);
        checkParam(queryDto);
        QueryResponse<QueryCashReqRecord> response = null;
        if(StringUtils.isBlank(queryDto.getUserId())) {
            if (!Strings.isNullOrEmpty(queryDto.getMobile()) && !KeyType.phone.encrypted(queryDto.getMobile())) {
                response = loanCashQueryService.queryCashReq(queryDto, buildPage(queryDto));
                if (null == response.getRows() || response.getRows().size() <= 0) {
                    queryDto.setMobile(secureStorageService.enCryptData(queryDto.getMobile(), DataTypeEnum.MOBILE_TYPE));
                    response = loanCashQueryService.queryCashReq(queryDto, buildPage(queryDto));
                }
            } else if (!Strings.isNullOrEmpty(queryDto.getIdentity()) && !KeyType.personal_id.encrypted(queryDto.getIdentity())) {
                response = loanCashQueryService.queryCashReq(queryDto, buildPage(queryDto));
                if (null == response.getRows() || response.getRows().size() <= 0) {
                    queryDto.setIdentity(secureStorageService.enCryptData(queryDto.getIdentity(), DataTypeEnum.IDENTITY_CARD));
                    response = loanCashQueryService.queryCashReq(queryDto, buildPage(queryDto));
                }
            } else {
                response = loanCashQueryService.queryCashReq(queryDto, buildPage(queryDto));
            }
        } else {
            response = loanCashQueryService.queryCashReq(queryDto, buildPage(queryDto));
        }
        return response;
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @PostMapping("/loan-ious")
    QueryResponse<TblLoanInfo> queryLoanIousInfo(@RequestBody QueryDto queryDto) throws Exception {
        logger.info("queryLoanIousInfo param queryDto is {} : ",queryDto);
        checkParam(queryDto);
        QueryResponse<TblLoanInfo> response = null;
        if(StringUtils.isBlank(queryDto.getUserId())) {
            if (!Strings.isNullOrEmpty(queryDto.getMobile()) && !KeyType.phone.encrypted(queryDto.getMobile())) {
                response = queryService.queryLoanIousReq(queryDto, buildPage(queryDto));
                if (null == response.getRows() || response.getRows().size() <= 0) {
                    queryDto.setMobile(secureStorageService.enCryptData(queryDto.getMobile(), DataTypeEnum.MOBILE_TYPE));
                    response = queryService.queryLoanIousReq(queryDto, buildPage(queryDto));
                }
            } else if (!Strings.isNullOrEmpty(queryDto.getIdentity()) && !KeyType.personal_id.encrypted(queryDto.getIdentity())) {
                response = queryService.queryLoanIousReq(queryDto, buildPage(queryDto));
                if (null == response.getRows() || response.getRows().size() <= 0) {
                    queryDto.setIdentity(secureStorageService.enCryptData(queryDto.getIdentity(), DataTypeEnum.IDENTITY_CARD));
                    response = queryService.queryLoanIousReq(queryDto, buildPage(queryDto));
                }
            } else {
                response = queryService.queryLoanIousReq(queryDto, buildPage(queryDto));
            }
        } else {
            response = queryService.queryLoanIousReq(queryDto, buildPage(queryDto));
        }
        return response;
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @PostMapping("/repayReq-info")
    QueryResponse<UserRepayReq> queryRepayReq(@RequestBody QueryDto queryDto) throws Exception {
        logger.info("queryRepayReq param queryDto is {} : ",queryDto);
        checkParam(queryDto);
        QueryResponse<UserRepayReq> response = null;
        if(StringUtils.isBlank(queryDto.getUserId())) {
            if (!Strings.isNullOrEmpty(queryDto.getMobile()) && !KeyType.phone.encrypted(queryDto.getMobile())) {
                response = repayService.queryUserRepayReq(queryDto, buildPage(queryDto));
                if (null == response.getRows() || response.getRows().size() <= 0) {
                    queryDto.setMobile(secureStorageService.enCryptData(queryDto.getMobile(), DataTypeEnum.MOBILE_TYPE));
                    response = repayService.queryUserRepayReq(queryDto, buildPage(queryDto));
                }
            } else if (!Strings.isNullOrEmpty(queryDto.getIdentity()) && !KeyType.personal_id.encrypted(queryDto.getIdentity())) {
                response = repayService.queryUserRepayReq(queryDto, buildPage(queryDto));
                if (null == response.getRows() || response.getRows().size() <= 0) {
                    queryDto.setIdentity(secureStorageService.enCryptData(queryDto.getIdentity(), DataTypeEnum.IDENTITY_CARD));
                    response = repayService.queryUserRepayReq(queryDto, buildPage(queryDto));
                }
            } else {
                response = repayService.queryUserRepayReq(queryDto, buildPage(queryDto));
            }
        } else {
            response = repayService.queryUserRepayReq(queryDto, buildPage(queryDto));
        }
        return response;
    }

    @RequiresPermissions("finance:query")
    @GetMapping("/schedule/{loanProvideNo}")
    String queryScheduleDetail(Model model, @PathVariable("loanProvideNo") String loanProvideNo) throws Exception {
        logger.info("queryScheduleDetail param queryDto is {} : ", loanProvideNo);
        ParamChecker.notBlank(loanProvideNo,"queryScheduleDetail param loanProvideNo cannot be blank");
        List<TblScheduleDetail> scheduleDetails = queryService.queryScheduleDetails(loanProvideNo);
        for(TblScheduleDetail scheduleDetail : scheduleDetails){
            scheduleDetail.setTotalAmount(scheduleDetail.getActRepayTotalAmount());
            RepayStatusEnum repayStatusEnum = RepayStatusEnum.toEnum(scheduleDetail.getStatus());
            if(repayStatusEnum != null){
                scheduleDetail.setStatusName(repayStatusEnum.getName());
            }
            int dueDay = 0;
            if(scheduleDetail.getStatus() != 3 && scheduleDetail.getStatus() != 4) {
                dueDay = getDiffDays(new Date(),scheduleDetail.getDueDate());
                if(dueDay < 0){
                    dueDay = 0;
                }
            }
            scheduleDetail.setDueDay(dueDay);
        }
        model.addAttribute("scheduleDetails",scheduleDetails);
        return "query/schedule";
    }

    @RequiresPermissions("finance:query")
    @GetMapping("/payDetail/{orderNo}")
    String gotoPayDetail(Model model, @PathVariable("orderNo") String orderNo) throws Exception {
        logger.info("gotoPayDetail param orderNo is {} : ", orderNo);
        ParamChecker.notBlank(orderNo,"queryPayDetail param orderNo cannot be blank");
        model.addAttribute("orderNo",orderNo);
        return "query/payDetail";
    }

    @RequiresPermissions("finance:query")
    @GetMapping("/goWithhold")
    String goWithhold() {
        return "query/repayWithhold";
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @GetMapping("/queryPayDetail/{serialNo}")
    PayDetailParamVo queryPayDetail(Model model, @PathVariable("serialNo") String serialNo) throws Exception {
        logger.info("queryPayDetail param serialNo is {} : ", serialNo);
        ParamChecker.notBlank(serialNo,"queryPayDetail param serialNo cannot be blank");
        PayDetailParamVo paramVo = queryService.queryPayDetail(serialNo);
        logger.info("paramVo is {}",paramVo);
        return paramVo;
    }

    @RequiresPermissions("finance:query")
    @GetMapping("/repayDetail/{serialNo}")
    String queryRepayDetail(Model model, @PathVariable("serialNo") String serialNo) throws Exception {
        logger.info("queryRepayDetail param serialNo is {} : ", serialNo);
        ParamChecker.notBlank(serialNo,"queryRepayDetail param serialNo cannot be blank");
        List<UserRepayDetail> detailList = Lists.newArrayList();
        Boolean result = true;
        try {
            detailList = repayService.queryUserRepayDetail(serialNo);
            if (CollectionUtils.isEmpty(detailList)) {
                logger.error("queryRepayDetail 未查到相关记录。serialNo={}", serialNo);
                result = false;
            }
        } catch (Exception e) {
            logger.error("queryRepayDetail exception, msg={}", e.getMessage(), e);
            result = false;
        }
        model.addAttribute("detailList",detailList);
        model.addAttribute("result",result);
        logger.info("queryRepayPayDetail detailList.size()={}, result={}", detailList == null ? 0 : detailList.size(), result);
        return "query/repayDetail";
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @GetMapping("/getTpp")
    List<TppInfo> getTpp() {
        logger.info("enter getTpp");
        return routeProductDao.selectTppCodeAndName();
    }

    @RequiresPermissions("finance:query")
    @GetMapping("/busiDetail")
    String queryBusiDetail(Model model, String busiOrderNo, String orderTime) {
        logger.info("queryBusiDetail busiOrderNo is : {}, orderTime is : {}",busiOrderNo,orderTime);
        ParamChecker.notBlank(busiOrderNo,"busiOrderNo cannot be blank");
        ParamChecker.notBlank(orderTime,"orderTime cannot be blank");
        model.addAttribute("busiOrderNo",busiOrderNo);
        model.addAttribute("occurTime",orderTime);
        return prefix + "/busiDetail";
    }

    @RequiresPermissions("finance:query")
    @GetMapping("/showMobile")
    String showMobile(Model model, String userId, String userName, String mobile) {
        logger.info("showMobile params userId is : {}, userName is : {}, mobile is : {}",userId,userName,mobile);
        ParamChecker.notBlank(userId,"userId cannot be blank");
//        try{
//            userName = new String(userName.getBytes("iso8859-1"),"UTF-8");
//        } catch (Exception e){
//            logger.error("用户名编码出错！",e);
//        }
        model.addAttribute("userId",userId);
        model.addAttribute("userName",userName);
        model.addAttribute("mobile",mobile);
        return prefix + "/phone";
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @PostMapping("/saveMobile")
    IousBaseRespDto saveMobile(String userId, String newMobile) {
        logger.info("saveMobile params userId is : {}, newMobile is : {}",userId,newMobile);
        ParamChecker.notBlank(userId,"userId cannot be blank");
        ParamChecker.notBlank(newMobile,"newMobile cannot be blank");
        IousBaseRespDto resp = new IousBaseRespDto();
        try{
            UpdateMobileReqDto reqDto = new UpdateMobileReqDto();
            reqDto.setUserId(userId);
            reqDto.setMobile(newMobile);
            reqDto.setProductNo(ProductEnum.IOUS);
            resp = iousManageFacade.updateUserMobile(reqDto);
        } catch (Exception e) {
            logger.error("updateUserMobile error:",e);
            resp.setReturnMsg(e.toString());
        }
        return resp;
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @PostMapping("/queryUserRepayWithhold")
    QueryResponse<UserRepayWithhold> queryUserRepayWithhold(@RequestBody UserRepayVo reqVo) {
        logger.info("queryUserRepayWithhold params UserRepayVo is : {}",reqVo);
        QueryResponse<UserRepayWithhold> response = new QueryResponse<>();
        try {
            Page page = new Page();
            page.setCurrentIndex(reqVo.getPageIndex()-1);
            page.setPageSize(reqVo.getPageSize());
            if (StringUtils.isNotBlank(reqVo.getMobile()) && !KeyType.phone.encrypted(reqVo.getMobile())) {
                reqVo.setMobile(secureStorageService.enCryptData(reqVo.getMobile(), DataTypeEnum.MOBILE_TYPE));
            }
            response = repayService.queryUserRepayWithhold(reqVo, page);
        } catch (Exception e) {
            logger.error("queryUserRepayWithhold the query is exception", e);
        }
        return response;
    }

    @RequiresPermissions("finance:query")
    @GetMapping("/queryQunarBusi/{busiOrderNo}")
    String queryQunarBusi(Model model, @PathVariable("busiOrderNo") String busiOrderNo) {
        logger.info("queryQunarBusi busiOrderNo is : {}", busiOrderNo);
        ParamChecker.notBlank(busiOrderNo,"busiOrderNo cannot be blank");
        List<TblLoanInfo> loanInfos = loanInfoDao.queryLoanInfoByBusiOrderNo(busiOrderNo);
        model.addAttribute("list",loanInfos);
        return prefix + "/qunarBusiDetail";
    }

    private static Integer getDiffDays(Date early, Date late) {
        if (null != early && null != late) {
            long diff = early.getTime() - late.getTime();
            long day = diff / 86400000L;
            return (int)day;
        } else {
            return null;
        }
    }

    private void checkParam(QueryDto queryDto){
        ParamChecker.notNull(queryDto,"queryDto cannot be null");
        ParamChecker.isTrue(StringUtils.isNotBlank(queryDto.getMobile()) || StringUtils.isNotBlank(queryDto.getIdentity()),"mobile and identity cannot be blank at the same time!");
        ParamChecker.isTrue(queryDto.getPageIndex() > 0,"pageIndex must bigger than 0");
        ParamChecker.isTrue(queryDto.getPageSize() > 0,"pageSize must bigger than 0");
        ParamChecker.notBlank(queryDto.getOrgChannel(),"orgChannel can not be blank");
    }

    private void setTime(QueryDto queryDto) {
        if(queryDto.getStartTime() == null){
            queryDto.setStartTime(DateUtils.getTodayMinTime());
        }
        if(queryDto.getEndTime() == null){
            queryDto.setEndTime(new Date());
        }
    }

    private void mobileToId(QueryDto queryDto) {
        if(StringUtils.isBlank(queryDto.getMobile()) || StringUtils.isNotBlank(queryDto.getUserId())) {
            return;
        }
        //查询携程拿去花数据,需要将手机号转为openId
        if(OrgChannelEnum.ifCtripChannel(queryDto.getOrgChannel()) && ProductEnum.ifIOUS(queryDto.getProductNo())) {
            queryDto.setUserId(queryIdsService.queryIdsByMobile(queryDto.getMobile()).getOpenId());
        }
        //查询携程拿去花数据,需要将手机号转为platOpenId
        if(OrgChannelEnum.ifCtripChannel(queryDto.getOrgChannel()) && ProductEnum.ifCASH(queryDto.getProductNo())) {
            queryDto.setUserId(queryIdsService.queryIdsByMobile(queryDto.getMobile()).getPlatOpenId());
        }
    }

    private Page buildPage(QueryDto queryDto){
        Page page = new Page();
        page.setPageSize(queryDto.getPageSize());
        page.setCurrentIndex((queryDto.getPageIndex()-1)*queryDto.getPageSize());
        return page;
    }

    @Autowired
    private QueryService queryService;

    @Autowired
    private LoanCashQueryService loanCashQueryService;

    @Autowired
    private TblRouteProductDao routeProductDao;

    @Autowired
    private LoanInfoDao loanInfoDao;

    @Autowired
    private QueryIdsService queryIdsService;

    @Autowired
    private RepayService repayService;

    @Resource
    private SecureStorageService secureStorageService;

    @Resource
    private IousManageFacade iousManageFacade;

    private static final String prefix = "/query";

    private static final Logger logger = LoggerFactory.getLogger(QueryController.class);
}
