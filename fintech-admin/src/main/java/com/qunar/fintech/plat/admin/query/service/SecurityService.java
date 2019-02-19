package com.qunar.fintech.plat.admin.query.service;

/**
 * 安全存储
 * @author dw.wang
 * @since 2016-10-27
 */
public interface SecurityService {
    /**
     * 加密身份证
     *
     * @param card
     * @param identity
     * @return
     * @throws Exception
     */
    String saveCI(String card, String identity);

    /**
     * 解密身份证
     *
     * @param encryptIndex
     * @return
     * @throws Exception
     */
    String getCI(String encryptIndex);
    /**
     * 根据索引获取卡号或者证件号
     * @param encryptIndex
     * @param key
     * @return
     */
    String getCI(String encryptIndex, String key);
}
