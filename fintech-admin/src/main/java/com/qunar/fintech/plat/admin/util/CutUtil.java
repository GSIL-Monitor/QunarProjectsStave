package com.qunar.fintech.plat.admin.util;

import com.qunar.pay.finance.qf.commons.utils.base.StringUtils;

/**
 * 切割长度与库中定义的字段长度保持一致
 */
public class CutUtil {

    /**
     * 错误码长度
     */
    private static final int ERR_CODE_LENGTH = 50;
    /**
     * 错误信息长度
     */
    private static final int ERR_MSG_LENGTH  = 200;

    /**
     * 切割错误码
     */
    public static String cutCode(String errCode){
        return StringUtils.cutOutString(errCode, ERR_CODE_LENGTH);
    }

    /**
     * 切割错误信息
     */
    public static String cutMsg(String errMsg){
        return StringUtils.cutOutString(errMsg, ERR_MSG_LENGTH);
    }
}
