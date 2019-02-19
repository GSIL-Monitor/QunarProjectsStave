package com.qunar.fintech.plat.admin.contract.service;

import com.qunar.fintech.plat.admin.contract.dto.ChannelContractStatusRespDto;
import com.qunar.fintech.plat.admin.contract.dto.ChannelContractStatusReqDto;
import com.qunar.fintech.plat.admin.contract.dto.QueryChannelContractRecord;
import com.qunar.fintech.plat.admin.contract.dto.QueryContractDto;
import com.qunar.fintech.plat.admin.contract.dto.QueryPlatContractRecord;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/19
 * @Despcription:
 */
public interface ContractServiceProxy {


    public QueryResponse<QueryPlatContractRecord> queryPlatAccount(QueryContractDto queryDto);


    public QueryResponse<QueryChannelContractRecord> queryChannelAccount(QueryContractDto queryDto);


    public String queryCustomtIdByUserId(String userId);

    /**
     * 更新channel合同状态
     * @param request
     * @return
     */
    ChannelContractStatusRespDto modifyChannelContractStatus(ChannelContractStatusReqDto request);

}
