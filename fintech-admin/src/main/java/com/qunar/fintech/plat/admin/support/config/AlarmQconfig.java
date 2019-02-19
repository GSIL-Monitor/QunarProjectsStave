package com.qunar.fintech.plat.admin.support.config;

import com.google.common.collect.Maps;
import com.qunar.fintech.util.security.EncryptUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import qunar.tc.qconfig.client.spring.QConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author qun.shi
 * @since 2019-01-21 12:29 PM
 */
@Component
public class AlarmQconfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmQconfig.class);
    private static final String CONFIG_FILE = "alarm.properties";
    private static final String INTERFACE_MERCHANT_CODE_KEY = ".interface.merchant.code";
    private static final String BUSI_TYPE_ID_KEY = ".busi.type.id";
    private static final String SIGN_KEY = ".signKey";

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private AtomicReference<Map<String, String>> configs = new AtomicReference(new HashMap<String, String>(5));

    private static final String SECRET_KEY = "fintech_admin_marketing_account";

    /**
     * 监听Qconfig 配置文件一旦变化，重新拉取配置信息
     */
    @QConfig(CONFIG_FILE)
    private void onConfigChanged(Map<String, String> confMap) {
        LOGGER.info("活动配置信息confMap: {}", confMap);
        Map<String, String> newConfigMap = Maps.newHashMap();
        if (MapUtils.isNotEmpty(confMap)) {
            for (Map.Entry<String, String> entry : confMap.entrySet()) {
                newConfigMap.put(entry.getKey(), entry.getValue());
            }
        }

        configs.get().clear();
        configs.set(newConfigMap);
    }

    public static void main(String args[]){
        String signKey = "test1111";
        String es = EncryptUtils.desEncrypt(signKey,   SECRET_KEY);
        System.out.println(es);
    }

    /**
     * 根据商户号获取接口商户号
     */
    public String getInterfaceMerchantCode(String customerNo){
        return configs.get().get(customerNo+INTERFACE_MERCHANT_CODE_KEY);
    }

    /**
     * 根据商户号获取接口商户号对应的业务线id
     */
    public String getBusiTypeId(String customerNo){
        return configs.get().get(customerNo+BUSI_TYPE_ID_KEY);
    }

    /**
     * 根据商户号获取接口商户号对应的加签密钥
     */
    public String getSignKey(String customerNo){
        String signKey = null;
        try {
            signKey = EncryptUtils.desDecrypt(configs.get().get(customerNo+SIGN_KEY) ,   SECRET_KEY);
        } catch (Exception e) {
            LOGGER.error("接口商户号解密异常，customerNo ={}",customerNo);
        }
        return signKey;
    }
}
