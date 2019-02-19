package com.qunar.fintech.plat.admin.query.service;

import com.qunar.fintech.plat.admin.query.entity.UserProductInfo;

import java.util.List;

/**
 * @author dw.wang
 * @since 2016-12-12
 */
public interface UserProductInfoService {

    /**
     * 根据身份证号、产品编号和userId获取个人信息
     */
    List<UserProductInfo> queryUserProductInfoByUserId(String identityCode, String productNo, String userId);

    List<UserProductInfo> queryReqUserIdProductInfo(String mainUserId, Integer accType);
}
