package com.qunar.fintech.plat.admin.contract.builder;

import com.qunar.fintech.contract.api.dto.PlatStatusProcRespDto;
import com.qunar.fintech.plat.admin.contract.dto.BaseRespDto;
import com.qunar.fintech.plat.admin.contract.dto.PaySwitchRespDto;
import com.qunar.fintech.plat.admin.contract.dto.PlatStatusRespDto;
import com.qunar.fintech.plat.admin.contract.exception.ContractException;
import com.qunar.fintech.plat.admin.contract.exception.ContractExceptionCode;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-06
 * Time: 11:40
 */
public class ContractBuilderTest {

    @Test
    public void test() {
        QResponse<PlatStatusProcRespDto> resp = new QResponse<>();
        resp.setSuccess();
        PlatStatusProcRespDto respDto = new PlatStatusProcRespDto();
        respDto.setResult(true);

        resp.setData(respDto);

        PlatStatusRespDto pcResp1 = buildRespDto(resp, new PlatStatusRespDto());

        BaseRespDto pcResp2 = buildRespDto(resp, new BaseRespDto());

        PaySwitchRespDto pcResp = buildRespDto(resp, new PaySwitchRespDto());

        System.out.println(pcResp);

    }

    private <T extends BaseRespDto> T buildRespDto(QResponse<?> resp, T t) {
        if(null == resp || null == resp.getData() || t == null){
            throw new ContractException(ContractExceptionCode.getExternalErrorCode("Contract调用失败"));
        }
        BaseRespDto respDto = new BaseRespDto();
        if (resp.isSuccess()) {
            respDto.setReqSuccess();
        } else {
            respDto.setReqFail();
            respDto.setErrorCode(resp.getReturnCode());
            respDto.setErrorMsg(resp.getReturnMsg());
        }

        BeanUtils.copyProperties(respDto, t);
        return t;
    }
}
