package com.qunar.fintech.plat.admin.query.service.impl;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qunar.fintech.coreinfo.api.facade.InfoConvertFacade;
import com.qunar.fintech.plat.admin.query.service.CipherConvertor;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import com.qunar.pay.finance.qf.commons.utils.base.CollectionUtils;
import com.qunar.pay.finance.qf.commons.utils.base.StringUtils;
import com.qunar.tc.core.info.api.KeyType;
import com.qunar.tc.core.info.api.RawKey;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * User: yupei.wang
 * Date: 2018/5/18
 */
@Service
public class CipherConvertorImpl implements CipherConvertor {

    @Override
    public String idCodeQunar2Ctrip(String idCode) {
        if (StringUtils.isEmpty(idCode)) {
            return "";
        }

        //设置请求参数
        HashSet<RawKey> keys = Sets.newHashSet();
        keys.add(new RawKey(KeyType.personal_id, idCode));

        //设置神盾信息
        Map<KeyType, Map<String, String>> keyTypeMapMap = this.convertToCtripCipher(keys);

        return this.convertSrcToTar(KeyType.personal_id, keyTypeMapMap, idCode);
    }

    @Override
    public String mobileQunar2Ctrip(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return "";
        }

        //设置请求参数
        HashSet<RawKey> keys = Sets.newHashSet();
        keys.add(new RawKey(KeyType.phone, mobile));

        //设置神盾信息
        Map<KeyType, Map<String, String>> keyTypeMapMap = this.convertToCtripCipher(keys);

        return this.convertSrcToTar(KeyType.phone, keyTypeMapMap, mobile);
    }

    @Override
    public Map<String, String> qunarBatch2Ctrip(Set<RawKey> rawKeys) {
        Map<KeyType, Map<String, String>> resMap = convertToCtripCipher(rawKeys);
        Map<String, String> retMap = Maps.newHashMap();
        for (RawKey key : rawKeys) {
            String s = convertSrcToTar(key.getType(), resMap, key.getKey());
            if (StringUtils.isNotEmpty(s)) {
                retMap.put(key.getKey(), s);
            }
        }
        return retMap;
    }

    private Map<KeyType, Map<String, String>> convertToCtripCipher(Set<RawKey> rawKeys) {
        if (CollectionUtils.isEmpty(rawKeys)) {
            return Maps.newHashMap();
        }
        QResponse<Map<KeyType, Map<String, String>>> result;
        try {
            LOGGER.info("infoConvertFacade.convertToCtripCipher#src rawKeys {}", rawKeys);
            result = infoConvertFacade.convertToCtripCipher(rawKeys);
            LOGGER.info("infoConvertFacade.convertToCtripCipher#response is {}", result);
            if (result.isSuccess() && result.getData() != null) {
                return result.getData();
            }
        } catch (Exception e) {
            LOGGER.error("神盾加密出错", e);
        }
        return null;
    }

    private String convertSrcToTar(KeyType keyType, Map<KeyType, Map<String, String>> keyTypeMapMap, String src) {
        if (MapUtils.isEmpty(keyTypeMapMap)) {
            return src;
        }

        if (StringUtils.isEmpty(src)) {
            return src;
        }

        Map<String, String> resMap = keyTypeMapMap.get(keyType);
        if (MapUtils.isEmpty(resMap)) {
            return src;
        }

        return resMap.get(src);
    }

    @Resource
    private InfoConvertFacade infoConvertFacade;

    private static final Logger LOGGER = LoggerFactory.getLogger(CipherConvertorImpl.class);
}
