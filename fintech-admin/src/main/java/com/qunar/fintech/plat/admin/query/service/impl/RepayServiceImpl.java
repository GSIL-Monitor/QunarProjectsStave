package com.qunar.fintech.plat.admin.query.service.impl;

import com.google.common.collect.Lists;
import com.qunar.es.enums.BusiKeyType;
import com.qunar.es.model.PrimaryKey;
import com.qunar.es.service.EsBusiKeyIndexService;
import com.qunar.fintech.plat.admin.query.dao.ious.BillDetailDao;
import com.qunar.fintech.plat.admin.query.dao.ious.BillInfoDao;
import com.qunar.fintech.plat.admin.query.dao.preloan.TblVirtualContractDao;
import com.qunar.fintech.plat.admin.query.dao.repay.LoanInfoDao;
import com.qunar.fintech.plat.admin.query.dao.repay.UserRepayExtDao;
import com.qunar.fintech.plat.admin.query.dao.repay.UserRepayPlanDao;
import com.qunar.fintech.plat.admin.query.dao.repay.UserRepayReqDao;
import com.qunar.fintech.plat.admin.query.dao.repay.UserRepayWithholdDao;
import com.qunar.fintech.plat.admin.query.entity.BillDetail;
import com.qunar.fintech.plat.admin.query.entity.BillInfo;
import com.qunar.fintech.plat.admin.query.entity.TblLoanInfo;
import com.qunar.fintech.plat.admin.query.entity.TblVirtualContract;
import com.qunar.fintech.plat.admin.query.entity.UserProductInfo;
import com.qunar.fintech.plat.admin.query.entity.UserRepayExt;
import com.qunar.fintech.plat.admin.query.entity.UserRepayPlan;
import com.qunar.fintech.plat.admin.query.entity.UserRepayReq;
import com.qunar.fintech.plat.admin.query.entity.UserRepayWithhold;
import com.qunar.fintech.plat.admin.query.service.CommonService;
import com.qunar.fintech.plat.admin.query.service.RepayService;
import com.qunar.fintech.plat.admin.query.service.UserProductInfoService;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryBillDetailRespDto;
import com.qunar.fintech.plat.admin.query.vo.QueryBillListRespDto;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.fintech.plat.admin.query.vo.UserRepayDetail;
import com.qunar.fintech.plat.admin.query.vo.UserRepayVo;
import com.qunar.pay.finance.ious.common.enums.OrgChannelEnum;
import com.qunar.pay.finance.ious.util.DateUtil;
import com.qunar.pay.finance.qf.commons.utils.base.AmountUtil;
import com.qunar.pay.finance.qf.commons.utils.base.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 还款查询服务类
 *
 * @author dw.wang
 * @since 2016-03-08.
 */
@Service
public class RepayServiceImpl implements RepayService {

    /**
     * 查询逾期的用户层还款计划
     * excludeList 排除在外的 UserRepayPlan，每一条都是
     */
    @Override
    public List<UserRepayPlan> queryRepayPlanForUrge(QueryDto reqVo, Page page, List<String> mainUserIdList) {
        List<String> orgChannelList = Lists.newArrayList(reqVo.getOrgChannel());
        if (OrgChannelEnum.ifQunarChannel(reqVo.getOrgChannel())) {
            orgChannelList.add(OrgChannelEnum.QUICKPASS_SH.getCode());
        }
        return userRepayPlanDao.selectRepayPlanForUrge(reqVo, page, orgChannelList, mainUserIdList);
    }

    /**
     * 统计逾期的用户层还款计划数量
     */
    @Override
    public int countUserRepayPlanByReqVo(QueryDto reqVo, List<String> mainUserIdList) {
        List<String> orgChannelList = Lists.newArrayList(reqVo.getOrgChannel());
        if (OrgChannelEnum.ifQunarChannel(reqVo.getOrgChannel())) {
            orgChannelList.add(OrgChannelEnum.QUICKPASS_SH.getCode());
        }
        return userRepayPlanDao.countUserRepayPlanByReqVo(reqVo, orgChannelList, mainUserIdList);
    }

    @Override
    public List<UserRepayPlan> selectByLoanProvideNo(String loanProvideNo) {
        return userRepayPlanDao.selectByLoanProvideNo(loanProvideNo);
    }

    @Override
    public QueryResponse<UserRepayReq> queryUserRepayReq(QueryDto reqVo, Page page) {
        // 设置主uid
        if (StringUtils.isNotBlank(reqVo.getUserId())) {
            List<UserProductInfo> infoList = userProductInfoService.queryUserProductInfoByUserId(reqVo.getIdentity(), reqVo.getProductNo(), reqVo.getUserId());
            if (CollectionUtils.isNotEmpty(infoList)) {
                reqVo.setUserId(infoList.get(0).getMainUserId());
            }
        }
        commonService.mobileToId(reqVo);
        try {
            commonService.addUserIdWithCheck(reqVo);
        } catch (Exception e) {
            logger.error("addUserIdWithCheck error",e);
            return new QueryResponse<>();
        }
        int total = userRepayReqDao.countByRequest(reqVo);
        if (total <= 0) {
            logger.info("queryUserRepayReq 记录数<＝0: total={}, reqVo={}", total, reqVo);
            return new QueryResponse<>();
        }
        QueryResponse<UserRepayReq> response = new QueryResponse<>();
        response.setTotal(total);
        response.setRows(getUserRepayReqList(reqVo, page));
        return response;
    }

    @Override
    public List<UserRepayDetail> queryUserRepayDetail(String serialNo) {
        List<UserRepayDetail> repayDetails = Lists.newArrayList();
        List<UserRepayExt> list = userRepayExtDao.queryUserRepayExt(serialNo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        // 过期日期转成 2016-03-15 形式，并将 operType 转为字符描述的形式。
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Long id = 1L;
        for (UserRepayExt ext : list) {
            String loanProvideNo = ext.getLoanProvideNo();
            TblLoanInfo tblLoanInfo = loanInfoDao.queryLoanInfoByLoanProvideNo(loanProvideNo);
            try {
                ext.setDueDateTime(format.parse(ext.getDueDate()));
                ext.setOperTypeStr(getOperTypeStr(ext.getOperType()));
            } catch (Exception e) {
                logger.error("日期转换异常");
            }
            ext.setId(id);
            id++;
            if(tblLoanInfo != null) {
                repayDetails.add(convertToUserRepayDetail(ext,tblLoanInfo.getBusiOrderNo()));
            }
        }
        return repayDetails;
    }

    @Override
    public QueryResponse<QueryBillListRespDto> queryBillList(Page pageQuery, String userId, Integer billStatus, Date start, Date end) {
        QueryResponse<QueryBillListRespDto> result = new QueryResponse<>();
        int count = billInfoDao.selectBillInfoCount(userId, billStatus, start, end);
        List<QueryBillListRespDto> records = Lists.newArrayList();
        if (count > 0) {
            List<BillInfo> billInfoList = billInfoDao.selectBillInfoByPage(userId, billStatus, start, end, pageQuery);
            records = convertQueryBillListRespDto(billInfoList);
        }
        result.setRows(records);
        result.setTotal(count);
        return result;
    }

    @Override
    public List<QueryBillDetailRespDto> queryBillDetail(String billNo) {
        int count = billDetailDao.selectBillDetailCount(billNo);
        List<QueryBillDetailRespDto> records = Lists.newArrayList();
        if (count > 0) {
            List<BillDetail> billDetailList = billDetailDao.selectBillDetailByPage(billNo, null);
            records = convertQueryBillDetailRespDto(billDetailList);
        }
        return records;
    }

    @Override
    public UserRepayPlan selectByLoanProvideNoDueDate(String loanProvideNo, Date dueDate) {
        return userRepayPlanDao.selectByLoanProvideNoDueDate(loanProvideNo, dueDate);
    }

    public  List<PrimaryKey> queryPrimaryKey(String orderNo) {
        logger.info("queryOrderInfo# start={}", orderNo);
        // es查询主键
        List<PrimaryKey> primaryKeys = queryPrimaryKeys(orderNo);
        logger.info("queryOrderInfo# primaryKeys={}", primaryKeys);
        return primaryKeys;
    }


    public QueryResponse<UserRepayWithhold> queryUserRepayWithhold(UserRepayVo reqVo, Page page) {
        QueryResponse<UserRepayWithhold> response = new QueryResponse<>();
        // 若userId 未传入，传了用户姓名或手机号，先查询用户userId
        if(StringUtils.isBlank(reqVo.getUserId()) &&  StringUtils.isNotBlank(reqVo.getMobile())) {
            try {
                commonService.addUserIdWithCheck(reqVo);
            } catch (Exception e) {
                //捕获异常，证明没有输入userId也无法通过姓名与手机号查表找到userId，无有效查找条件
                logger.error("##queryUserRepayWithhold##无法查找到userId",e);
                return response;
            }
        }
        int total = userRepayWithholdDao.countByReqDto(reqVo);
        if (total <= 0) {
            logger.info("queryUserRepayWithhold 记录数<＝0: total={}, reqVo={}", total, reqVo);
            return response;
        }
        response.setTotal(total);
        response.setRows(getUserRepayWithholdList(reqVo, page));
        return response;
    }

    @Override
    public List<UserRepayWithhold> getUserRepayWithholdList(UserRepayVo reqVo, Page page) {
        List<UserRepayWithhold> list = userRepayWithholdDao.queryUserRepayWithholdList(reqVo, page);
        if (CollectionUtils.isEmpty(list)) {
            logger.info("queryUserRepayWithhold  queryUserRepayWithholdList 查询的结果为null或空");
            return list;
        }
        //添加用户姓名和手机号
        commonService.addUserNameAndMobile(list);
        return list;
    }

    private List<UserRepayReq> getUserRepayReqList(QueryDto reqVo, Page page) {
        List<UserRepayReq> list = userRepayReqDao.queryUserRepayReqList(reqVo, page);
        if (CollectionUtils.isEmpty(list)) {
            logger.info("queryUserRepayReq queryUserRepayReqList 查询的结果为null或空");
            return list;
        }
        //添加姓名和手机号
        commonService.addUserNameAndMobile(list);
        return list;
    }

    private String getOperTypeStr(Integer operType) {
        if (operType.intValue() == 1) {
            return "还款请求 req_detail";
        }
        return "用户请求 repay_req";
    }

    /**
     * 格式转化
     * @param ext 还款扩展表bean
     * @param busiOrderNo 业务线订单号
     * @return 包含业务线流水号的还款信息
     */
    private UserRepayDetail convertToUserRepayDetail(UserRepayExt ext, String busiOrderNo) {
        UserRepayDetail userRepayDetail = new UserRepayDetail();
        userRepayDetail.setId(ext.getId());
        userRepayDetail.setCreateTime(ext.getCreateTime());
        userRepayDetail.setDueDateTime(ext.getDueDateTime());
        userRepayDetail.setFailAmount(ext.getFailAmount());
        userRepayDetail.setSucAmount(ext.getSucAmount());
        userRepayDetail.setFinishTime(ext.getFinishTime());
        userRepayDetail.setSerialNo(ext.getSerialNo());
        userRepayDetail.setStatus(ext.getStatus());
        userRepayDetail.setOperTypeStr(ext.getOperTypeStr());
        userRepayDetail.setLoanProvideNo(ext.getLoanProvideNo());
        userRepayDetail.setBusiOrderNo(busiOrderNo);
        userRepayDetail.setTppCode(ext.getTppCode());
        userRepayDetail.setErrMsg(ext.getErrMsg());
        userRepayDetail.setStatusDesc(transStatus(ext.getStatus()));
        return userRepayDetail;
    }

    /**
     * es查询主键信息
     *
     * @return
     */
    private List<PrimaryKey> queryPrimaryKeys(String orderNo) {
        List<PrimaryKey> keys = Collections.emptyList();
        logger.info("queryPrimaryKeys# start={}",orderNo);
        try {
            if (StringUtils.isNotBlank(orderNo)) {
                keys = esBusiKeyIndexService.queryPrimaryKeys(orderNo, BusiKeyType.ORDER_NO);
            }
        } catch (Exception e) {
            logger.error("es query batchOrderId failed", e);
        }
        logger.info("es 查询主键 keys={},request={}", keys,ReflectionToStringBuilder.reflectionToString(keys, ToStringStyle.SIMPLE_STYLE));
        return keys;
    }

    private String transStatus(Integer status) {
        if(status == 0) {
            return "初始";
        } else if(status == 1) {
            return "还款成功";
        } else if(status == 2) {
            return "还款失败";
        } else if(status == 3){
            return "还款中";
        } else {
            return "";
        }
    }

    private List<QueryBillListRespDto> convertQueryBillListRespDto(List<BillInfo> billInfoList) {
        Date zenoByToday = com.qunar.pay.finance.qf.commons.utils.base.DateUtil.getZenoByToDays();
        List<QueryBillListRespDto> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(billInfoList)) {
            for (BillInfo billInfo : billInfoList) {
                QueryBillListRespDto dto = new QueryBillListRespDto();
                dto.setBillPrcpAmt(AmountUtil.decimalToString(billInfo.getBillPrcpAmount()));
                dto.setBillIntAmt(AmountUtil.decimalToString(billInfo.getBillIntAmount()));
                dto.setBillNo(billInfo.getBillNo());
                dto.setUserId(billInfo.getUserId());
                dto.setBillDate(DateUtil.date2Str(1, billInfo.getBillDate()));
                dto.setUpdateTime(DateUtil.date2Str(1, billInfo.getUpdateTime()));
                dto.setRepayDate(DateUtil.date2Str(9, billInfo.getDueDate()));
                // 查询用户信息
                TblVirtualContract contract = virtualContractDao.selectByUserId(billInfo.getUserId(), billInfo.getProductNo());
                if (contract != null) {
                    dto.setUserName(contract.getUserName());
                    dto.setMobile(contract.getMobile());
                }
                // 查询还款计划信息获取逾期金额
                BigDecimal allFineAmt = BigDecimal.ZERO;
                List<BillDetail> details = billDetailDao.selectBillDetailByPage(billInfo.getBillNo(), null);
                if (CollectionUtils.isNotEmpty(details)) {
                    for (BillDetail detail : details) {
                        UserRepayPlan repayPlan = userRepayPlanDao.selectByLoanProvideNoDueDate(detail.getLoanProvideNo(), detail.getDueDate());
                        if(repayPlan != null) {
                            allFineAmt = AmountUtil.add(allFineAmt, repayPlan.getActRepayFineAmount());
                        }
                    }
                }
                dto.setBillFineAmt(AmountUtil.decimalToString(allFineAmt));
                // 账单状态
                if (Integer.valueOf(1).equals(billInfo.getBillStatus())) {
                    dto.setBillStatus("已还清");
                } else if (Integer.valueOf(0).equals(billInfo.getBillStatus()) && zenoByToday.compareTo(billInfo.getDueDate()) > 0) {
                    dto.setBillStatus("已逾期");
                } else if (Integer.valueOf(0).equals(billInfo.getBillStatus()) && zenoByToday.compareTo(billInfo.getDueDate()) <= 0) {
                    dto.setBillStatus("已出账");
                }
                result.add(dto);
            }
        }
        return result;
    }

    private List<QueryBillDetailRespDto> convertQueryBillDetailRespDto(List<BillDetail> billDetailList) {
        List<QueryBillDetailRespDto> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(billDetailList)) {
            for (BillDetail detail : billDetailList) {
                // 查询借据信息
                TblLoanInfo loanInfo = loanInfoDao.queryLoanInfoByLoanProvideNo(detail.getLoanProvideNo());
                // 查询还款计划信息
                UserRepayPlan repayPlan = userRepayPlanDao.selectByLoanProvideNoDueDate(detail.getLoanProvideNo(), detail.getDueDate());
                QueryBillDetailRespDto dto = new QueryBillDetailRespDto();
                dto.setLoanProvideNo(loanInfo.getLoanProvideNo());
                dto.setMerchantNo(loanInfo.getMerchantCode());
                dto.setRepayAmt(AmountUtil.decimalToString(loanInfo.getIousPayAmount()));
                if(repayPlan != null) {
                    dto.setTermInfo(repayPlan.getRepayIndex() + "/" + (Integer.valueOf(0).equals(loanInfo.getLoanTerm()) ? 1 : loanInfo.getLoanTerm()));
                    dto.setPrcpAmt(AmountUtil.decimalToString(repayPlan.getPrcpAmt()));
                    dto.setIntAmt(AmountUtil.decimalToString(repayPlan.getIntAmt()));
                    dto.setFineAmt(AmountUtil.decimalToString(repayPlan.getFineAmt()));

                }
                dto.setTppCode(loanInfo.getTppCode());
                dto.setLoanDate(DateUtil.date2Str(1, loanInfo.getPayTime()));
                dto.setBusiOrderNo(loanInfo.getBusiOrderNo());
                result.add(dto);
            }
        }
        return result;
    }

    @Resource
    private UserRepayPlanDao userRepayPlanDao;

    @Resource
    private UserRepayReqDao userRepayReqDao;

    @Resource
    private UserProductInfoService userProductInfoService;

    @Resource
    private CommonService commonService;

    @Resource
    private UserRepayExtDao userRepayExtDao;

    @Resource
    private LoanInfoDao loanInfoDao;

    @Resource
    private BillInfoDao billInfoDao;

    @Resource
    private BillDetailDao billDetailDao;

    @Resource
    private TblVirtualContractDao virtualContractDao;

    @Resource
    private EsBusiKeyIndexService esBusiKeyIndexService;

    @Resource
    private UserRepayWithholdDao userRepayWithholdDao;

    private static final Logger logger = LoggerFactory.getLogger(RepayServiceImpl.class);
}
