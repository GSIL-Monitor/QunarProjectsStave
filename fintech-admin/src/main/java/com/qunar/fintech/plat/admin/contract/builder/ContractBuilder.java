package com.qunar.fintech.plat.admin.contract.builder;

import com.qunar.fintech.contract.api.dto.OperaSwitchReqDto;
import com.qunar.fintech.contract.api.dto.PlatStatusProcReqDto;
import com.qunar.fintech.contract.api.dto.QueryValidPlatContractByMobileReqDto;
import com.qunar.fintech.contract.api.enums.SwitchTypeEnum;
import com.qunar.fintech.plat.admin.contract.constant.CommConstant;
import com.qunar.fintech.plat.admin.contract.dto.ChannelContractStatusReqDto;
import com.qunar.fintech.plat.admin.contract.dto.PlatStatusReqDto;
import com.qunar.fintech.plat.admin.contract.dto.QueryContractDto;
import com.qunar.fintech.plat.admin.contract.dto.QueryContractVo;
import com.qunar.fintech.plat.admin.system.dao.entity.ContractChangeReq;

import java.util.Date;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-03
 * Time: 22:33
 */
public class ContractBuilder {

    public static QueryContractDto buildQueryDto(QueryContractVo vo, String customId) {
        QueryContractDto dto = new QueryContractDto();
        dto.setProductNo(vo.getProductNo());
        dto.setTppCode(vo.getTppCode());
        dto.setCustomId(customId);
        dto.setPageIndex(vo.getPageNum());
        dto.setPageSize(vo.getPageSize());
        return dto;
    }

    public static QueryValidPlatContractByMobileReqDto buildQueryReqByMobile(String mobile, String productNo){
        QueryValidPlatContractByMobileReqDto reqDto = new QueryValidPlatContractByMobileReqDto();
        reqDto.setMobile(mobile);
        reqDto.setProductNo(productNo);
        return reqDto;
    }

    public static PlatStatusProcReqDto buildPlatStatusReq(PlatStatusReqDto req, ContractChangeReq changeReq){
        PlatStatusProcReqDto reqDto = new PlatStatusProcReqDto();
        reqDto.setSerialNo(changeReq.getReqNo());
        reqDto.setCustomId(changeReq.getCustomId());
        reqDto.setProductNo(changeReq.getProductNo());
        reqDto.setTargetStatus(req.getTarStatus());
        reqDto.setOperator(CommConstant.CLOSE_PAY_SWITCH_PARTNER);
        reqDto.setClosePayswitchPartner(CommConstant.CLOSE_PAY_SWITCH_PARTNER);
        return  reqDto;
    }

    public static OperaSwitchReqDto buildReqByPaySwitch(ContractChangeReq changeReq) {
        OperaSwitchReqDto paySwitchReq = new OperaSwitchReqDto();
        paySwitchReq.setSerialNo(changeReq.getReqNo());
        paySwitchReq.setCustomId(changeReq.getCustomId());
        paySwitchReq.setProductNo(changeReq.getProductNo());
        paySwitchReq.setClosePayswitchPartner(CommConstant.CLOSE_PAY_SWITCH_PARTNER);
        paySwitchReq.setSwitchType(SwitchTypeEnum.PAY_SWITCH.getCode());
        return paySwitchReq;
    }

    public static OperaSwitchReqDto buildReqByChannelSwitch(ChannelContractStatusReqDto request){
        OperaSwitchReqDto reqDto = new OperaSwitchReqDto();
        Date current = new Date(System.currentTimeMillis());
        reqDto.setSerialNo(current.toString());
        reqDto.setTppCode(request.getTppCode());
        reqDto.setProductNo(request.getProductNo());
        reqDto.setCustomId(request.getCustomId());
        reqDto.setSwitchType(SwitchTypeEnum.CHANNEL_SWITCH.getCode());
        reqDto.setClosePayswitchPartner(CommConstant.CLOSE_PAY_SWITCH_PARTNER);
        return reqDto;
    }
}
