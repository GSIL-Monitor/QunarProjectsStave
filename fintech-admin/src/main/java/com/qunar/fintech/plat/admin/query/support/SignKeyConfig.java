package com.qunar.fintech.plat.admin.query.support;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import qunar.tc.qconfig.client.spring.QConfig;

import java.util.Iterator;
import java.util.Map;

/**
 * Description: qconfig配置类
 * User: kingmom.wang
 * Date: 2018-07-23
 * Time: 15:17
 */
@Service
public class SignKeyConfig {

    /**
     * 验签秘钥
     */
    public static String getSignKey(String appId) {
        return signkeys.get(appId);
    }

    @QConfig("signkey.properties")
    public void loadSignKeys(Map<String, String> config) {
        LOGGER.info("load resource signkey.properties success, old={},new={}", signkeys, config);
        signkeys.putAll(config);
        for (Iterator<Map.Entry<String, String>> it = signkeys.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, String> entry = it.next();
            if (config.get(entry.getKey()) == null) {
                it.remove();
            }
        }
    }

    private static volatile Map<String, String> signkeys = Maps.newHashMap();

    private static final Logger LOGGER = LoggerFactory.getLogger(SignKeyConfig.class);

}
