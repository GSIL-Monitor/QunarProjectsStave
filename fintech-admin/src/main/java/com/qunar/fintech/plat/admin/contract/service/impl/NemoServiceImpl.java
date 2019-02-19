package com.qunar.fintech.plat.admin.contract.service.impl;

import com.qunar.fintech.nemo.api.dto.model.CustomerByPidRes;
import com.qunar.fintech.nemo.api.facade.FinUserIdFacade;
import com.qunar.fintech.plat.admin.contract.service.NemoService;
import com.qunar.fintech.plat.admin.exception.ErrorCodes;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/20
 * @Despcription:
 */
@Service
public class NemoServiceImpl implements NemoService {

    @Override
    public CustomerByPidRes queryCustomerByPlatId(String userId) {
        try {
            LOGGER.info("finUserIdFacade.queryCustomerByPlatId# platId:{}", userId);
            QResponse<CustomerByPidRes> resp = finUserIdFacade.queryCustomerByPlatOpenId(userId);

            if (resp == null || !resp.isSuccess() || resp.getData() == null) {
                throw new BusiException(ErrorCodes.CALL_DUBBO_ERROR, "调用nemo接口失败");
            }
            LOGGER.info("finUserIdFacade.queryCustomerByPlatId# respDto:{}", resp.getData());
            return resp.getData();
        } catch (Exception e) {
            LOGGER.error("finUserIdFacade.queryCustomerByPlatId error", e);
            throw new BusiException(ErrorCodes.CALL_DUBBO_ERROR, "调用nemo接口失败");
        }
    }

    @Resource
    private FinUserIdFacade finUserIdFacade;

    private static final Logger LOGGER = LoggerFactory.getLogger(NemoServiceImpl.class);

}
