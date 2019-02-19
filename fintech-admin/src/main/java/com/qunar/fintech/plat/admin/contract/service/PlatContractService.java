package com.qunar.fintech.plat.admin.contract.service;

import com.qunar.fintech.plat.admin.contract.dto.PaySwitchReqDto;
import com.qunar.fintech.plat.admin.contract.dto.PaySwitchRespDto;
import com.qunar.fintech.plat.admin.contract.dto.PlatStatusReqDto;
import com.qunar.fintech.plat.admin.contract.dto.PlatStatusRespDto;
import com.qunar.fintech.plat.admin.system.dao.entity.ContractChangeReq;

import java.util.List;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-02
 * Time: 20:53
 */
public interface PlatContractService {

    /**
     * 更新plat合同状态
     * @param request
     * @param changeReq
     * @return
     */
    PlatStatusRespDto modifyPlatStatus(PlatStatusReqDto request, ContractChangeReq changeReq);

    /**
     * 更新平台合同支付开关
     * @param reqDto
     * @param changeReq
     * @return
     */
    PaySwitchRespDto updatePaySwitch(PaySwitchReqDto reqDto, ContractChangeReq changeReq);

    /**
     *  通过加密的身份证号查询customId
     * @param identity
     * @return
     */
    public String queryCustomIdByIdentity(String identity);

    /**
     * 通过加密的手机号查询customId
     * @param mobile
     * @return
     */
    public List<String> queryCustomIdByMobile(String mobile, String productNo);
}
