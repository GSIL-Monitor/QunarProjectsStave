package com.qunar.fintech.plat.admin.contract.util;

import com.qunar.fintech.plat.admin.contract.dto.QueryContractVo;
import com.qunar.fintech.plat.admin.system.dto.QueryContractChangeReqDto;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import org.apache.commons.lang.StringUtils;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-02
 * Time: 20:47
 */
public final class ContractParamCheck {

    public static void checkQueryParam(QueryContractVo queryDto) {
        ParamChecker.notNull(queryDto, "queryDto cannot be null");
        ParamChecker.isTrue(StringUtils.isNotBlank(queryDto.getCustomId()) ||
                StringUtils.isNotBlank(queryDto.getMobile()) || StringUtils.isNotBlank(queryDto.getPlatId())
                || StringUtils.isNotBlank(queryDto.getIdentityCode()),
                "platId、mobile and identity cannot be blank at the same time!");
    }

    public static void checkQueryChangeReqParam(QueryContractChangeReqDto queryDto) {
        ParamChecker.notNull(queryDto, "queryDto cannot be null");
        ParamChecker.isTrue(StringUtils.isNotBlank(queryDto.getCustomId()) ||
                        StringUtils.isNotBlank(queryDto.getReqNo()) || StringUtils.isNotBlank(queryDto.getEmail())
                        || StringUtils.isNotBlank(queryDto.getStartTime())
                        || StringUtils.isNotBlank(queryDto.getEndTime()),
                "platId、mobile and identity cannot be blank at the same time!");
    }
}
