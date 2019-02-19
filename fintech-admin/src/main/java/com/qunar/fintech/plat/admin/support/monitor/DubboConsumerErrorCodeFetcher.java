package com.qunar.fintech.plat.admin.support.monitor;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.qunar.fintech.monitor.core.ErrorCode;
import com.qunar.fintech.monitor.extension.dubbo.IResponseErrorCodeFetcher;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import com.qunar.pay.g2.api.account.dto.AccountingResultDto;
import com.qunar.pay.g2.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yupei.wang on 2016/3/9
 */
public class DubboConsumerErrorCodeFetcher implements IResponseErrorCodeFetcher {

    @Override
    public ErrorCode getErrorCode(Invoker<?> invoker, Invocation inv, Result result) {
        Object o = result.getValue();
        String errCode = DEFAULT_ERROR_CODE;
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("MinosProviderErrorCodeFetcher method :{}  return object :{}",
                        invoker.getInterface().getName() + "." + inv.getMethodName(), o);
            }
            if (o instanceof QResponse<?>) {
                QResponse r = (QResponse) o;
                errCode = r.getReturnCode();
                return r.isSuccess() ? ErrorCode.createSuccess(errCode) : ErrorCode.createFail(errCode);
            } else {
                LOGGER.debug("cannot get error code, method : {}, return object : {}",
                        invoker.getInterface().getName() + "." + inv.getMethodName(), o);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return ErrorCode.createFail(errCode);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(DubboConsumerErrorCodeFetcher.class);

    private static final String DEFAULT_ERROR_CODE = "UNKNOWN_ERROR_CODE";
}
