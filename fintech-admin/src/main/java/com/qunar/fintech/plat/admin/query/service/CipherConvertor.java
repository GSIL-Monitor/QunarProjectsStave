package com.qunar.fintech.plat.admin.query.service;

import com.qunar.tc.core.info.api.RawKey;

import java.util.Map;
import java.util.Set;

/**
 * 神盾密文转换
 * User: yupei.wang
 * Date: 2018/5/18
 */
public interface CipherConvertor {

    /**
     * 身份证密文转换：qunar->ctrip
     */
    String idCodeQunar2Ctrip(String idCode);

    /**
     * 手机号密文转换：qunar->ctrip
     */
    String mobileQunar2Ctrip(String mobile);

    /**
     * 批量转换
     */
    Map<String, String> qunarBatch2Ctrip(Set<RawKey> rawKeys);
}
