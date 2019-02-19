package com.qunar.fintech.plat.admin.contract.service;

import com.qunar.fintech.plat.admin.contract.dto.ChannelContractStatusReqDto;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/20
 * @Despcription: 调用contract接口开关平台合同，通过plat表查询cid
 */
public interface ChannelContractService {


    /**
     * 打开channel合同状态
     * @param request
     * @return
     */
    boolean openChannelContractStatus(ChannelContractStatusReqDto request);

    /**
     * 关闭channel合同状态
     * @param request
     * @return
     */
    boolean closeChannelContractStatus(ChannelContractStatusReqDto request);
}
