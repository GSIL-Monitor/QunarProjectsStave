package com.qunar.fintech.plat.admin.query.service;


import com.qunar.fintech.plat.admin.query.enums.DataTypeEnum;
import com.qunar.tc.core.info.api.PersonInfo;

/**
 * 安全存储服务接口
 *
 * @author liqiang.jiang
 * @since 15-11-03
 */
public interface SecureStorageService {

    /**
     * 加密数据
     *
     * @param data
     * @return
     */
    String enCryptData(String data, DataTypeEnum dataTypeEnum);

    /**
     * 解密数据
     *
     * @param index
     * @return
     */
    String deCryptData(String index, DataTypeEnum dataTypeEnum);


    /**
     * 将身份证信息解密
     *
     * @param data 加密的身份证信息
     * @return 客户的相关信息
     */
    PersonInfo decryptData(String data);
}
