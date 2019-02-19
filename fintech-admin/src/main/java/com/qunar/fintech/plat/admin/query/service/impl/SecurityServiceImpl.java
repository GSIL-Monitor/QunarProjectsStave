package com.qunar.fintech.plat.admin.query.service.impl;

import com.qunar.fintech.plat.admin.query.service.SecurityService;
import com.qunar.fintech.plat.admin.query.utils.HttpCommUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 安全存储
 * @author dw.wang
 * @since 2016-10-27
 */
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
    private static Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
    @Value("${CIencryption.data.url}")
    private String encryptionCIDataUrl;

    @Value("${CIdecryption.data.url}")
    private String decryptionCIDataUrl;

    /**
     * 加密卡号身份证号 card 卡号 identity 身份证号
     */
    @Override
    public String saveCI(String card, String identity) {
        Map<String, String> paramMap = new HashMap<String, String>();
        String result = null;
        try {
            paramMap.put("cardId", card);
            paramMap.put("identityNo", identity);
            result = HttpCommUtil.doPost(encryptionCIDataUrl, paramMap);
        } catch (Exception e) {
            logger.error("encrypt fail:", e);
            result = null;
        }
        if (result != null && !(result.length() == 32)) {
            result = null;
        }

        return result;
    }

    /**
     * 解密卡号身份证号 param encryptIndex 加密索引号 return json字符串，包含卡号，身份证
     */
    @Override
    public String getCI(String encryptIndex) {
        Map<String, String> paramMap = new HashMap<String, String>();
        String result = null;
        try {
            paramMap.put("encrypIndex", encryptIndex);
            result = HttpCommUtil.doPost(decryptionCIDataUrl, paramMap);
        } catch (Exception e) {
            logger.error("dencrypt fail:", e);
            result = null;
        }
        return result;
    }
    /**
     * 根据索引获取卡号或者证件号
     * @param encryptIndex
     * @param key
     * @return
     */
    @Override
    public String getCI(String encryptIndex, String key) {
        if(StringUtils.isBlank(encryptIndex)){
            return "";
        }
        return getCIMapFromCIJson(this.getCI(encryptIndex),key);
    }
    /**
     * 从银行卡json数据获取银行卡信息
     *
     * @param
     * @return
     */
    public static String getCIMapFromCIJson(String str, String key) {
        String jsonStr = ESAPI.encoder().decodeForHTML(str);
        if (StringUtils.isNotBlank(jsonStr)) {
            try {
                JSONObject json = JSONObject.fromObject(jsonStr);
                return json.getString(key);
            } catch (Exception e) {
                logger.error("obtain info from json error", e);
                return null;
            }
        }
        return null;
    }
}
