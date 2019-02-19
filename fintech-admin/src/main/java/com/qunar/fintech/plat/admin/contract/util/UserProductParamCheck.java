package com.qunar.fintech.plat.admin.contract.util;

import com.qunar.fintech.plat.admin.contract.dto.QueryUserProductDto;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import org.apache.commons.lang.StringUtils;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-02
 * Time: 20:48
 */
public final class UserProductParamCheck {

    public static void checkQueryUpParam(QueryUserProductDto queryDto) {
        ParamChecker.notNull(queryDto, "queryUserProduct queryDto cannot be null");
        ParamChecker.isTrue(StringUtils.isNotBlank(queryDto.getCustomId()) || StringUtils.isNotBlank(queryDto.getPlatId()),
                "customId and platId cannot be blank at the same time");
    }
}
