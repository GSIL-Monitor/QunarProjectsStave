package com.qunar.fintech.plat.admin.query.controller;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.qunar.fintech.plat.admin.query.dao.ious.MinRepayLoanReqDetailMapper;
import com.qunar.fintech.plat.admin.query.dao.ious.MinRepayLoanReqMapper;
import com.qunar.fintech.plat.admin.query.dao.preloan.TblVirtualContractDao;
import com.qunar.fintech.plat.admin.query.dao.repay.LoanInfoDao;
import com.qunar.fintech.plat.admin.query.entity.MinRepayLoanReq;
import com.qunar.fintech.plat.admin.query.entity.MinRepayLoanReqDetail;
import com.qunar.fintech.plat.admin.query.entity.TblLoanInfo;
import com.qunar.fintech.plat.admin.query.entity.TblVirtualContract;
import com.qunar.fintech.plat.admin.query.entity.UserRepayPlan;
import com.qunar.fintech.plat.admin.query.enums.DataTypeEnum;
import com.qunar.fintech.plat.admin.query.service.IousQueryService;
import com.qunar.fintech.plat.admin.query.service.RepayService;
import com.qunar.fintech.plat.admin.query.service.SecureStorageService;
import com.qunar.fintech.plat.admin.query.service.VirtualContractService;
import com.qunar.fintech.plat.admin.query.vo.MinRepayDetailVo;
import com.qunar.fintech.plat.admin.query.vo.MinRepayReqVo;
import com.qunar.fintech.plat.admin.query.vo.MinRepayVo;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryBillDetailRespDto;
import com.qunar.fintech.plat.admin.query.vo.QueryBillDto;
import com.qunar.fintech.plat.admin.query.vo.QueryBillListRespDto;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.pay.finance.basicInfo.api.route.dto.request.QueryBillChannelReqDto;
import com.qunar.pay.finance.basicInfo.api.route.dto.response.QueryBillChannelRespDto;
import com.qunar.pay.finance.basicInfo.api.route.facade.ChannelQueryFacade;
import com.qunar.pay.finance.qf.commons.api.enums.ProductEnum;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import com.qunar.pay.finance.qf.commons.utils.base.CollectionUtils;
import com.qunar.tc.core.info.api.KeyType;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Created with Intellij IDEA.
 * Description:
 * User: weiduan
 * Date: 2018-02-09
 * Time: 下午2:43
 */
@RequestMapping("/finance/bill")
@Controller
public class BillController {

    @GetMapping()
    String list() {
        return "/query/bill";
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @PostMapping("/queryBillList")
    QueryResponse<QueryBillListRespDto> queryBillList(@RequestBody QueryBillDto query) {
        logger.info("queryBillList# param queryBillDto is : {}",query);
        ParamChecker.notNull(query,"queryBillDto is null!");
        QueryResponse<QueryBillListRespDto> response = new QueryResponse<>();
        try {
            if (!Strings.isNullOrEmpty(query.getMobile())) {
                // 指定了手机号作为查询条件
                if (!KeyType.phone.encrypted(query.getMobile())){
                    query.setMobile(secureStorageService.enCryptData(query.getMobile(), DataTypeEnum.MOBILE_TYPE));
                }
                List<TblVirtualContract> contract = virtualContractDao.selectByUserNameMobile(null, query.getMobile());
                if (contract != null) {
                    query.setUserId(contract.get(0).getUserId());
                } else {
                    response.setRows(null);
                    response.setTotal(0);
                    return response;
                }
            }
            if (StringUtils.isNotEmpty(query.getUserId()) || (null != query.getStartTime()) && null != query.getEndTime()) {
                Date start = query.getStartTime();
                Date end = query.getEndTime();
                if (end != null) {
                    end =new DateTime(end).withTime(23,59,59,0).toDate();
                }
                Page page = new Page();
                page.setCurrentIndex(query.getPageIndex()-1);
                page.setPageSize(query.getPageSize());
                response = repayService.queryBillList(page, query.getUserId(), query.getBillStatus(), start, end);
            } else {
                logger.error("queryBillList# query param error ");
            }
        } catch (Exception e) {
            logger.error("queryBillList# error ", e);
            response.setRows(null);
            response.setTotal(0);
        }
        logger.info("queryBillList# end:{}", response);
        return response;
    }

    @RequiresPermissions("finance:query")
    @GetMapping("/billDetail/{billNo}")
    String queryBillDetail(Model model, @PathVariable("billNo") String billNo) {
        logger.info("#queryBillDetail param billNo is {}",billNo);
        ParamChecker.notBlank(billNo,"billNo cannot be blank!");
        List<QueryBillDetailRespDto> billDetails = repayService.queryBillDetail(billNo);
        model.addAttribute("billDetails",billDetails);
        return "query/billDetail";
    }

    @RequiresPermissions("finance:query")
    @GetMapping("/goMinRepayOrder")
    String goMinRepayOrder(Model model) {
        logger.info("#goMinRepayOrder");
        return "query/queryMinRepayOrder";
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @GetMapping("/getTpp")
    List<String> getTpp() {
        List<String> tpps = queryBillChannelResp();
        logger.info("supportBillTppCodes is {}",tpps);
        return tpps;
    }

    /**
     * 查询转债订单
     */
    @RequiresPermissions("finance:query")
    @PostMapping(value = "/queryMinRepayOrder")
    @ResponseBody
    public QueryResponse<MinRepayVo> queryMinRepayOrder(@RequestBody MinRepayReqVo reqVo) {
        logger.info("===queryMinRepayOrder 查询转债订单，MinRepayReqVo={}", reqVo);

        QueryResponse<MinRepayVo> result = new QueryResponse<>();
        Integer total = 0;
        Page page = new Page();
        page.setCurrentIndex(reqVo.getPageIndex()-1);
        page.setPageSize(reqVo.getPageSize());
        List<MinRepayVo> minRepayVos = Lists.newArrayList();
        List<MinRepayLoanReq> minRepayLoanReqs = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(reqVo.getMobile()) && !KeyType.phone.encrypted(reqVo.getMobile())){
            reqVo.setMobile(secureStorageService.enCryptData(reqVo.getMobile(), DataTypeEnum.MOBILE_TYPE));
        }
        try {
            List<String> userIdList = null;
            if (StringUtils.isNotEmpty(reqVo.getUserId())) {
                userIdList = Lists.newArrayList(reqVo.getUserId());
            } else if (StringUtils.isNotEmpty(reqVo.getMobile())) {
                userIdList = iousQueryService.queryUserIdByMobile(reqVo.getMobile(), ProductEnum.IOUS.getProductNo());
                if (CollectionUtils.isEmpty(userIdList)){
                    throw new Exception("填写的手机号没有对应的虚拟合同信息");
                }
            }
            String minRepayTpp = StringUtils.isEmpty(reqVo.getMinRepayTpp()) ? null : reqVo.getMinRepayTpp();
            Date start = reqVo.getStartDate();
            Date end = reqVo.getEndDate();
            minRepayLoanReqs = minRepayLoanReqMapper.selectByPage(userIdList, minRepayTpp, start, end, reqVo.getProcessStatus(), page);
             if (CollectionUtils.isNotEmpty(minRepayLoanReqs)) {
                for (MinRepayLoanReq req : minRepayLoanReqs) {
                    TblVirtualContract contract =
                            virtualContractService.queryByUserIdProductNo(req.getUserId(), ProductEnum.IOUS.getProductNo());
                    MinRepayVo minRepayVo = new MinRepayVo();
                    minRepayVo.setCreateTime(req.getCreateTime());
                    minRepayVo.setMobile(contract.getMobile());
                    minRepayVo.setNewTppcode(req.getNewTppcode());
                    minRepayVo.setProcessStatus(req.getProcessStatus());
                    minRepayVo.setSerialNo(req.getSerialNo());
                    minRepayVo.setTotalTransAmount(req.getTotalTransAmount());
                    minRepayVo.setUserId(contract.getUserId());
                    minRepayVo.setUserName(contract.getUserName());
                    minRepayVos.add(minRepayVo);
                }
            }
        } catch (Exception e) {
            logger.error("===queryMinRepayOrder：异常:{}", e.getMessage(), e);
        }
        result.setRows(minRepayVos);
        result.setTotal(total);
        logger.info("====queryMinRepayOrder# result={}", result);
        return result;
    }

    @RequiresPermissions("finance:query")
    @GetMapping("/minDetail/{serialNo}")
    String queryMinDetail(Model model, @PathVariable("serialNo") String serialNo) {
        logger.info("#queryMinDetail param serialNo is {}",serialNo);
        ParamChecker.notBlank(serialNo,"serialNo cannot be blank!");
        Boolean result = true;
        String returnMsg = null;
        List<MinRepayDetailVo> minRepayDetailVos = Lists.newArrayList();
        try {
            Integer index = 1;
            List<MinRepayLoanReqDetail> minRepayLoanReqDetails = minRepayLoanReqDetailMapper.selectBySerialNo(serialNo);
            for (MinRepayLoanReqDetail reqDetail : minRepayLoanReqDetails) {
                MinRepayDetailVo vo = new MinRepayDetailVo();
                UserRepayPlan userRepayPlan = repayService.selectByLoanProvideNoDueDate(reqDetail.getLoanProvideNo(), reqDetail.getDueDate());
                TblLoanInfo tblLoanInfo = loanInfoDao.queryLoanInfoByLoanProvideNo(reqDetail.getLoanProvideNo());
                if (userRepayPlan==null) {
                    throw new Exception("订单不存在");
                }
                if (tblLoanInfo==null) {
                    throw new Exception("订单不存在");
                }
                vo.setIndex(index++);
                vo.setIousPayAmount(tblLoanInfo.getIousPayAmount());
                vo.setLoanProvideNo(tblLoanInfo.getLoanProvideNo());
                vo.setTppCode(tblLoanInfo.getTppCode());
                vo.setPayTime(tblLoanInfo.getPayTime());
                vo.setNeedRepayAmount(userRepayPlan.getUserRepayTotalAmount());
                vo.setRepaidAmount(userRepayPlan.gettSetlTotalAmount());
                vo.setProductName(tblLoanInfo.getProductName());
                minRepayDetailVos.add(vo);
            }
        } catch (Exception e) {
            logger.error("===queryMinRepayOrderDetails：异常:{}", e.getMessage(), e);
            returnMsg = e.getMessage();
            result = false;
        }
        model.addAttribute("detailList", minRepayDetailVos);
        model.addAttribute("result", result);
        logger.info("====queryMinRepayOrderDetails# result={}, returnMsg={}", result, returnMsg);
        return "query/minDetail";
    }

    private List<String> queryBillChannelResp() {
        QueryBillChannelReqDto reqDto = new QueryBillChannelReqDto();
        reqDto.setProductNo(ProductEnum.IOUS.getProductNo());
        QResponse<QueryBillChannelRespDto> queryBillChannelResp = channelQueryFacade.queryBillChannel(reqDto);
        logger.info("queryBillChannel#end queryBillChannelResp={}", queryBillChannelResp);
        if (queryBillChannelResp == null || !queryBillChannelResp.isSuccess() || queryBillChannelResp.getData() == null) {
            return Lists.newArrayList();
        }
        QueryBillChannelRespDto queryBillChannel = queryBillChannelResp.getData();

        return queryBillChannel.getSupportBillTppCodes();
    }

    @Resource
    private SecureStorageService secureStorageService;

    @Resource
    private TblVirtualContractDao virtualContractDao;

    @Resource
    private RepayService repayService;

    @Resource
    private ChannelQueryFacade channelQueryFacade;

    @Resource
    private IousQueryService iousQueryService;

    @Resource
    private VirtualContractService virtualContractService;

    @Resource
    private MinRepayLoanReqMapper minRepayLoanReqMapper;

    @Resource
    private MinRepayLoanReqDetailMapper minRepayLoanReqDetailMapper;

    @Resource
    private LoanInfoDao loanInfoDao;

    private static final Logger logger = LoggerFactory.getLogger(BillController.class);
}
