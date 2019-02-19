package com.qunar.fintech.plat.admin.query.support;

import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import qunar.tc.qconfig.client.spring.QConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with Intellij IDEA.
 * Description:
 * User: weiduan
 * Date: 2018-02-08
 * Time: 下午4:34
 */
@Component
public class SuggestQconfig {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private AtomicReference<Map<String, String>> configs = new AtomicReference(new HashMap<String, String>());

    @QConfig(PRIORITY_CONFIG_FILE)
    public void onConfigChanged(Map<String, String> confMap) {
        logger.info("建议话术配置信息confMap: {}",confMap);
        Map<String, String> configMap = Maps.newHashMap();
        if(MapUtils.isNotEmpty(confMap)) {
            for (Map.Entry<String, String> entry : confMap.entrySet()) {
                configMap.put(entry.getKey(), entry.getValue());
            }
        }
        configs.set(configMap);
    }

    /**
     * 查询建议话术
     */
    public String getSuugest(String errorCode) {
        return configs.get().get(errorCode);
    }

    private static final String PRIORITY_CONFIG_FILE = "suggest.properties";

    private static final Logger logger = LoggerFactory.getLogger(SuggestQconfig.class);
}
