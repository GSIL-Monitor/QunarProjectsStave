package com.qunar.fintech.plat.admin.contract.service;

import org.springframework.stereotype.Service;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/10/18
 * @Despcription: 查询参数处理
 */
public interface ParamProcService {
    /**
     * 明文手机号加密
     * @param mobile
     * @return
     */
    String mobileEnCrypt(String mobile, String orgChannel);
    /**
     * 明文手机号加密
     * @param identity
     * @return
     */
    String identityEnCrypt(String identity);
}
