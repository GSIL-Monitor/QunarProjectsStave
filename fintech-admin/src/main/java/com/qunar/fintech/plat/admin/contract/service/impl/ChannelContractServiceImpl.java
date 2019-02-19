package com.qunar.fintech.plat.admin.contract.service.impl;

import com.qunar.fintech.contract.api.dto.OpearSwitchRespDto;
import com.qunar.fintech.contract.api.dto.OperaSwitchReqDto;
import com.qunar.fintech.contract.api.facade.ContractFacade;
import com.qunar.fintech.plat.admin.contract.builder.ContractBuilder;
import com.qunar.fintech.plat.admin.contract.dto.ChannelContractStatusReqDto;
import com.qunar.fintech.plat.admin.contract.service.ChannelContractService;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/20
 * @Despcription:
 */

@Service
public class ChannelContractServiceImpl implements ChannelContractService {

    @Override
    public boolean closeChannelContractStatus(ChannelContractStatusReqDto request) {
        OperaSwitchReqDto reqDto= ContractBuilder.buildReqByChannelSwitch(request);
        QResponse<OpearSwitchRespDto> response = contractFacade.closeSwitch(reqDto);
        if(null != response && null != response.getData()){
            LOGGER.debug("调用contract-api关闭channel合同开关，响应参数response:{}",response.getData().getSuccess());
            return response.getData().getSuccess();
        }else{
            LOGGER.error("调用contract-api打开channel合同开关，响应参数为null  req:[cid:{}, prd:{}]" , request.getCustomId(), request.getProductNo());
            return false;
        }
    }

    @Override
    public boolean openChannelContractStatus(ChannelContractStatusReqDto request) {
        OperaSwitchReqDto reqDto= ContractBuilder.buildReqByChannelSwitch(request);
        LOGGER.info("调用contract-apid打开channel合同开关，请求参数request:{}",reqDto);
        QResponse<OpearSwitchRespDto> response = contractFacade.openSwitch(reqDto);
        if(null != response && null != response.getData()){
            LOGGER.debug("调用contract-api打开channel合同开关，响应参数response:{}",response.getData().getSuccess());
            return response.getData().getSuccess();
        }else{
            LOGGER.error("调用contract-api打开channel合同开关，响应参数为null  req:[cid:{}, prd:{}]" , request.getCustomId(), request.getProductNo());
            return false;
        }
    }

    @Resource
    private ContractFacade contractFacade;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelContractServiceImpl.class);
}
