package com.qunar.fintech.plat.admin.contract.service.impl;

import com.google.common.collect.Lists;
import com.qunar.fintech.contract.api.dto.QueryContractInfoReqDto;
import com.qunar.fintech.contract.api.dto.QueryContractInfoRespDto;
import com.qunar.fintech.contract.api.facade.ContractQueryFacade;
import com.qunar.fintech.contract.api.model.ChannelContractInfo;
import com.qunar.fintech.nemo.api.dto.model.CustomerByPidRes;
import com.qunar.fintech.nemo.api.enums.AccTypeEnum;
import com.qunar.fintech.plat.admin.contract.dto.ChannelContractStatusReqDto;
import com.qunar.fintech.plat.admin.contract.dto.ChannelContractStatusRespDto;
import com.qunar.fintech.plat.admin.contract.dto.QueryChannelContractRecord;
import com.qunar.fintech.plat.admin.contract.dto.QueryContractDto;
import com.qunar.fintech.plat.admin.contract.dto.QueryPlatContractRecord;
import com.qunar.fintech.plat.admin.contract.exception.ContractException;
import com.qunar.fintech.plat.admin.contract.exception.ContractExceptionCode;
import com.qunar.fintech.plat.admin.contract.service.ChannelContractService;
import com.qunar.fintech.plat.admin.contract.service.ContractServiceProxy;
import com.qunar.fintech.plat.admin.contract.service.NemoService;
import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/19
 * @Despcription: Contract服务代理实现
 */

@Service
public class ContractServiceProxyImpl implements ContractServiceProxy {

    @Override
    public QueryResponse<QueryPlatContractRecord> queryPlatAccount(QueryContractDto queryDto) {
        QResponse<QueryContractInfoRespDto> respDto = null;
        QueryContractInfoReqDto reqDto = QueryContractInfoReqDto.instanceQuota(queryDto.getCustomId(), queryDto.getProductNo());
        respDto = contractQueryFacade.queryContractInfo(reqDto);
        if(respDto == null || !respDto.isSuccess()){
            throw new ContractException(ContractExceptionCode.getExternalErrorCode("Contract调用失败"));
        }else if(null == respDto.getData() || null == respDto.getData().getPlatContractInfo()){
            throw new ContractException(respDto.getReturnMsg());
        }
        QueryResponse<QueryPlatContractRecord> result = getPlatContractRecord(respDto.getData());
        LOGGER.info("queryPlatAccount# resp:{}",result);
        return result;
    }

    @Override
    public QueryResponse<QueryChannelContractRecord> queryChannelAccount(QueryContractDto queryDto) {
        QResponse<QueryContractInfoRespDto> respDto = null;
        QueryContractInfoReqDto reqDto = QueryContractInfoReqDto.instanceQuota(queryDto.getCustomId(), queryDto.getProductNo());
        respDto = contractQueryFacade.queryContractInfo(reqDto);
        if(respDto == null || !respDto.isSuccess()){
            throw new ContractException(ContractExceptionCode.getExternalErrorCode("Contract调用失败"));
        }else if(null == respDto.getData() || null == respDto.getData().getChannelContractInfos()){
            throw new ContractException( respDto.getReturnMsg());
        }
        QueryResponse<QueryChannelContractRecord> result = getChannelContractRecord(queryDto, respDto.getData());
        LOGGER.info("queryChannelAccount# resp:{}",result);
        return result;
    }

    @Override
    public String queryCustomtIdByUserId(String userId) {
        CustomerByPidRes customer = nemoService.queryCustomerByPlatId(userId);
        if (customer != null && !AccTypeEnum.UN_BINDED.getCode().equals(customer.getAccType())) {
            return customer.getCustomerId();
        }
        return null;
    }

    @Override
    public ChannelContractStatusRespDto modifyChannelContractStatus(ChannelContractStatusReqDto request) {

        // 校验参数
        checkParams(request);

        ChannelContractStatusRespDto resp = new ChannelContractStatusRespDto();
        boolean success = false;
        if (1 == request.getTarStatus()){
            success = channelContractService.openChannelContractStatus(request);
        }else {
            success = channelContractService.closeChannelContractStatus(request);
        }
        if(success){
            resp.setReqSuccess();
            resp.setResult(success);
        } else {
            resp.setReqFail();
        }
        return resp;
    }

    private QueryResponse<QueryPlatContractRecord> getPlatContractRecord(QueryContractInfoRespDto respDto){
        QueryResponse<QueryPlatContractRecord> record = new QueryResponse<QueryPlatContractRecord>();
        record.setRows(buildPlatRecord(respDto));
        record.setTotal(record.getRows().size());
        return record;
    }

    private List<QueryPlatContractRecord> buildPlatRecord(QueryContractInfoRespDto respDto){
        List<QueryPlatContractRecord> records = new ArrayList<>();
        QueryPlatContractRecord record = new QueryPlatContractRecord();
        record.setMobile(respDto.getPlatContractInfo().getMobile());
        record.setCustomId(respDto.getPlatContractInfo().getCustomId());
        record.setCustomName(respDto.getPlatContractInfo().getCustomName());
        record.setProductNo(respDto.getPlatContractInfo().getProductNo());
        record.setIdentityCode(respDto.getPlatContractInfo().getIdentityCode());
        record.setPlatStatus(respDto.getPlatContractInfo().getPlatStatus());
        record.setAutoPaySwitch(respDto.getPlatContractInfo().getAutoRepaySwitch());
        record.setPaySwitch(respDto.getPlatContractInfo().getPaySwitch());
        record.setProcStatus(respDto.getPlatContractInfo().getSignStatus());
        record.setTppCode(respDto.getPlatContractInfo().getTppCode());
        record.setStartTime(respDto.getPlatContractInfo().getEffStartTime());
        record.setEndTime(respDto.getPlatContractInfo().getEffEndTime());
        record.setUserGroup(respDto.getPlatContractInfo().getUserGroup());
        record.setActivated(respDto.getPlatContractInfo().isActiveed());
        if(null != respDto.getMainQuotaInfo()) {
            record.setTotalAmount(respDto.getMainQuotaInfo().getTotalAmount());
            record.setUsedAmount(respDto.getMainQuotaInfo().getUsedAmount());
            record.setFreezeAmount(respDto.getMainQuotaInfo().getFreezeAmount());
            record.setBalanceAmount(respDto.getMainQuotaInfo().getBalanceAmount());
        }else{
            record.setTotalAmount(errCode);
            record.setUsedAmount(errCode);
            record.setFreezeAmount(errCode);
            record.setBalanceAmount(errCode);
        }
        record.setExpired(respDto.getPlatContractInfo().expired());
        record.setActivated(respDto.getPlatContractInfo().isActiveed());
        records.add(record);
        return records;

    }

    private QueryResponse<QueryChannelContractRecord> getChannelContractRecord(QueryContractDto queryDto, QueryContractInfoRespDto respDto){
        QueryResponse<QueryChannelContractRecord> record = new QueryResponse<QueryChannelContractRecord>();
        record.setRows(buildChannelRecord(queryDto, respDto));
        record.setTotal(record.getRows().size());;
        return record;
    }

    private List<QueryChannelContractRecord> buildChannelRecord(QueryContractDto queryDto, QueryContractInfoRespDto respDto){
        List<QueryChannelContractRecord> records = Lists.newArrayList();
        List<ChannelContractInfo> channelInfo = respDto.getChannelContractInfos();
        for(ChannelContractInfo info:channelInfo){
            QueryChannelContractRecord record = new QueryChannelContractRecord();
            record.setCustomId(info.getCustomId());
            record.setPlatId(info.getPlatId());
            record.setOrgChannel(info.getOrgChannel());
            record.setProductNo(info.getProductNo());
            record.setUserGroup(info.getUserGroup());
            record.setTppCode(info.getTppCode());
            record.setMainChannel(info.getMainTppCode());
            record.setProcStatus(info.getSignStatus());
            record.setChannelStatus(info.getChannelStatus());
            record.setCreditStartTime(info.getCreditStartTime());
            record.setCreditEndTime(info.getCreditEndTime());
            record.setCreditFinishTime(info.getCreditFinishTime());
            record.setActivateStartTime(info.getActivateStartTime());
            record.setActivateEndTime(info.getActivateEndTime());
            record.setActivateFinishTime(info.getActivateFinishTime());
            if(null != respDto.getMainQuotaInfo() && null != respDto.getChannelQuotaAmt(record.getTppCode())) {
                record.setTotalAmount(respDto.getChannelQuotaAmt(record.getTppCode()).getTotalAmount());
                record.setUsedAmount(respDto.getChannelQuotaAmt(record.getTppCode()).getUsedAmount());
                record.setFreezeAmount(respDto.getChannelQuotaAmt(record.getTppCode()).getFreezeAmount());
                record.setBalanceAmount(respDto.getChannelQuotaAmt(record.getTppCode()).getBalanceAmount());
            }else{
                record.setTotalAmount(errCode);
                record.setUsedAmount(errCode);
                record.setFreezeAmount(errCode);
                record.setBalanceAmount(errCode);
            }
            if(null == queryDto.getTppCode() || queryDto.getTppCode().isEmpty() || record.getTppCode().equals(queryDto.getTppCode())) {
                records.add(record);
            }
        }
        return records;
    }

    private void checkParams(ChannelContractStatusReqDto reqDto){
        ParamChecker.notNull(reqDto, "ChannelContractStatusReqDto is null");
        ParamChecker.notBlank(reqDto.getCustomId(), "ChannelContractStatusReqDto customId is null");
        ParamChecker.notBlank(reqDto.getProductNo(), "ChannelContractStatusReqDto productNo is null");
        ParamChecker.notBlank(reqDto.getTppCode(), "ChannelContractStatusReqDto tppCode is null");
    }

    @Resource
    private ContractQueryFacade contractQueryFacade;
    @Resource
    private NemoService nemoService;
    @Resource
    private ChannelContractService channelContractService;

    private static final BigDecimal errCode = new BigDecimal(-1);
    private static final Logger LOGGER = LoggerFactory.getLogger(ContractServiceProxyImpl.class);

}
