package com.qunar.fintech.plat.admin.system.service;

import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.fintech.plat.admin.system.dao.entity.ContractChangeReq;
import com.qunar.fintech.plat.admin.system.dao.enums.ProcStatusEnum;
import com.qunar.fintech.plat.admin.system.dto.QueryContractChangeReqDto;
import com.qunar.fintech.plat.admin.system.dto.QueryContractChangeRespDto;

import java.util.List;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-04
 * Time: 14:43
 */
public interface ContractChangeReqService {

    ContractChangeReq saveChangeReq(ContractChangeReq signReq);

    void updateReqByProcStatus(ContractChangeReq upReq, List<ProcStatusEnum> procStatus, boolean isCheck);

    ContractChangeReq querySignReq(String reqNo);

    QueryResponse<QueryContractChangeRespDto> queryByParam(QueryContractChangeReqDto reqDto);
}
