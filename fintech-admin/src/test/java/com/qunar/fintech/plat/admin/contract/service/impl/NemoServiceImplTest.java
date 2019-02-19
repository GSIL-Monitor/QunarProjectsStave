package com.qunar.fintech.plat.admin.contract.service.impl;

import com.qunar.fintech.nemo.api.dto.model.CustomerByPidRes;
import com.qunar.fintech.nemo.api.enums.AccTypeEnum;
import com.qunar.fintech.plat.admin.base.BaseAdminDBTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/25
 * @Despcription:
 */
public class NemoServiceImplTest extends BaseAdminDBTest{

    @Test
    public void queryIdentityInfoByUserId() {
        String customId = "";
        CustomerByPidRes customer = nemoService.queryCustomerByPlatId(USERID);
        if (customer != null&& !AccTypeEnum.UN_BINDED.getCode().equals(customer.getAccType())) {
            customId = customer.getCustomerId();
        }
        if(!customId.isEmpty()) {
            LOGGER.info("CID：{}", customId);
        }else{
            LOGGER.error("无法获得CID：{}", USERID);
        }
    }

    @Resource
    private NemoServiceImpl nemoService;

    private static final String USERID = "1444646286";

    private static final Logger LOGGER = LoggerFactory.getLogger(NemoServiceImplTest.class);
}