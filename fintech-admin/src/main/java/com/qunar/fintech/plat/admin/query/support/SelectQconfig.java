package com.qunar.fintech.plat.admin.query.support;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import qunar.tc.qconfig.client.spring.QConfig;

import java.util.Map;


/**
 * Created with Intellij IDEA.
 * Description:
 * User: weiduan
 * Date: 2018-02-08
 * Time: 下午4:34
 */
@Service
public class SelectQconfig {

    public static Map<String, String> getSelectMap() {
        return SelectMap;
    }

    @QConfig(PRIORITY_CONFIG_FILE)
    public void onConfigChanged(Map<String, String> confMap) {
        logger.info("下拉框值: {}", confMap);
        SelectMap.putAll(confMap);
    }

    private static volatile Map<String, String> SelectMap = Maps.newHashMap();

    private static final String PRIORITY_CONFIG_FILE = "select.properties";

    private static final Logger logger = LoggerFactory.getLogger(SelectQconfig.class);
}