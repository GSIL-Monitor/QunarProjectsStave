package com.qunar.fintech.plat.admin.contract.service.impl;

import com.google.common.base.Preconditions;
import com.qunar.fintech.contract.api.dto.OpearSwitchRespDto;
import com.qunar.fintech.contract.api.dto.OperaSwitchReqDto;
import com.qunar.fintech.contract.api.dto.PlatStatusProcReqDto;
import com.qunar.fintech.contract.api.dto.PlatStatusProcRespDto;
import com.qunar.fintech.contract.api.facade.ContractFacade;
import com.qunar.fintech.contract.api.facade.ManageFacade;
import com.qunar.fintech.plat.admin.contract.builder.ContractBuilder;
import com.qunar.fintech.plat.admin.contract.dao.entity.PlatContract;
import com.qunar.fintech.plat.admin.contract.dao.enums.PaySwitchEnum;
import com.qunar.fintech.plat.admin.contract.dao.mapper.PlatContractDao;
import com.qunar.fintech.plat.admin.contract.dto.BaseRespDto;
import com.qunar.fintech.plat.admin.contract.dto.PaySwitchReqDto;
import com.qunar.fintech.plat.admin.contract.dto.PaySwitchRespDto;
import com.qunar.fintech.plat.admin.contract.dto.PlatStatusReqDto;
import com.qunar.fintech.plat.admin.contract.dto.PlatStatusRespDto;
import com.qunar.fintech.plat.admin.contract.exception.ContractException;
import com.qunar.fintech.plat.admin.contract.exception.ContractExceptionCode;
import com.qunar.fintech.plat.admin.contract.service.PlatContractService;
import com.qunar.fintech.plat.admin.exception.ErrorCodes;
import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import com.qunar.fintech.plat.admin.system.dao.entity.ContractChangeReq;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import com.qunar.pay.finance.qf.commons.utils.base.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-02
 * Time: 20:53
 */
@Service
public class PlatContractServiceImpl implements PlatContractService {

    @Override
    public PlatStatusRespDto modifyPlatStatus(PlatStatusReqDto request, ContractChangeReq changeReq) {
        try {
            PlatStatusProcReqDto reqDto = ContractBuilder.buildPlatStatusReq(request, changeReq);
            LOGGER.info("modifyPlatStatus - start - PlatStatusProcReqDto:{}", reqDto);
            QResponse<PlatStatusProcRespDto> resp = manageFacade.modifyPlatContractStatus(reqDto);

            // 处理结果
            PlatStatusRespDto respDto = buildRespDto(resp, new PlatStatusRespDto());
            respDto.setResult(resp.getData().isResult());
            return respDto;
        } catch (ContractException cEx) {
            LOGGER.error("modifyPlatStatus - error - reqDto:{}，e:{}", request, cEx.getErrorCode());
            throw cEx;
        } catch (Exception e) {
            LOGGER.error("modifyPlatStatus - error - reqDto:{}，e:{}", request, e.getMessage());
            throw new BusiException(ErrorCodes.UNKNOWN_EXCEPTION, "修改平台合同状态出现未知异常");
        }
    }

    @Override
    public PaySwitchRespDto updatePaySwitch(PaySwitchReqDto reqDto, ContractChangeReq changeReq) {
        try {
            PaySwitchEnum paySwitch = PaySwitchEnum.toEnum(reqDto.getTarStatus());
            ParamChecker.notNull(paySwitch, "修改合同支付开关paySwitch is null");

            OperaSwitchReqDto paySwitchReq = ContractBuilder.buildReqByPaySwitch(changeReq);
            LOGGER.info("updatePaySwitch - start - paySwitchReq:{}", paySwitchReq);
            QResponse<OpearSwitchRespDto> resp = null;
            if (paySwitch.isClose()) {
                resp = contractFacade.closeSwitch(paySwitchReq);
            } else if (paySwitch.isOpen()) {
                resp = contractFacade.openSwitch(paySwitchReq);
            }

            // 处理结果
            PaySwitchRespDto respDto = buildRespDto(resp, new PaySwitchRespDto());
            respDto.setResult(respDto.isSucess() && paySwitch.name().equals(resp.getData().getSwitchStatus()));
            return respDto;
        } catch (BusiException busiEx) {
            LOGGER.error("updatePaySwitch - error - reqDto:{} ex:{}", reqDto, busiEx.getErrCode());
            throw busiEx;
        } catch (Exception e) {
            LOGGER.error("updatePaySwitch - error - reqDto:{} ex:{}", reqDto, e.getMessage());
            throw new BusiException(ErrorCodes.UNKNOWN_EXCEPTION, "修改合同支付开关未知异常");
        }
    }

    @Override
    public String queryCustomIdByIdentity(String identityCode) {
        ParamChecker.notBlank(identityCode, "identityCode is null");
        try {
            PlatContract info = platContractDao.queryByIdentityCode(identityCode);
            return info != null ? info.getCustomId() : null;
        } catch (Exception e) {
            LOGGER.error("通过PlatContract获取customId失败，请求参数:identity:{}, e:{}", identityCode, e.getMessage());
            throw new ContractException(ContractExceptionCode.SQL_EXECUTE_ERROR);
        }
    }

    @Override
    public List<String> queryCustomIdByMobile(String mobile, String productNo) {
        ParamChecker.notBlank(mobile, "mobile is null");
        List<String> customIds = new ArrayList<>();
        try {
            List<PlatContract> pcs = platContractDao.queryByMobile(mobile, productNo);
            if (CollectionUtils.isEmpty(pcs)) {
                return customIds;
            }
            for (PlatContract pc : pcs) {
                customIds.add(pc.getCustomId());
            }
            return customIds;
        } catch (Exception e) {
            LOGGER.error("通过PlatContract获取customId，请求参数:mobile:{} e:{}", mobile, e.getMessage());
            throw new ContractException(ContractExceptionCode.getExternalErrorCode("Contract调用失败"));
        }
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

    @Resource
    private ContractFacade contractFacade;
    @Resource
    private PlatContractDao platContractDao;
    @Resource
    private ManageFacade manageFacade;

    private static final Logger LOGGER = LoggerFactory.getLogger(PlatContractServiceImpl.class);
}
