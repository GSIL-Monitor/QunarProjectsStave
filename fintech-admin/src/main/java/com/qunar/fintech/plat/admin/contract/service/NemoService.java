package com.qunar.fintech.plat.admin.contract.service;


import com.qunar.fintech.nemo.api.dto.model.CustomerByPidRes;
import com.qunar.fintech.nemo.api.dto.request.FinUserInfoResDto;
import com.qunar.fintech.nemo.api.dto.request.QueryRealNameInfoReq;
import com.qunar.fintech.nemo.api.dto.response.QueryRealNameInfoRes;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/20
 * @Despcription: 调用nemo接口服务
 */
public interface NemoService {

    /**
     * 根据platId查询用户信息
     * @param userId
     * @return
     */
    public CustomerByPidRes queryCustomerByPlatId(String userId);

}
