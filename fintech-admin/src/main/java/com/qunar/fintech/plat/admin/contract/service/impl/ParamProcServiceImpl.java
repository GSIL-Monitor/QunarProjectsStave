package com.qunar.fintech.plat.admin.contract.service.impl;

import com.qunar.fintech.plat.admin.contract.service.ParamProcService;
import com.qunar.fintech.plat.admin.query.enums.DataTypeEnum;
import com.qunar.fintech.plat.admin.query.service.SecureStorageService;
import com.qunar.pay.finance.qf.commons.api.enums.OrgChannelEnum;
import com.qunar.seccenter.coreinfo.api.CoreinfoService;
import com.qunar.seccenter.coreinfo.exception.DecryptException;
import com.qunar.tc.core.info.api.KeyType;
import com.qunar.tc.core.info.api.RawKey;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/10/18
 * @Despcription: 查询参数处理
 */
@Service
public class ParamProcServiceImpl implements ParamProcService {
    @Override
    public String mobileEnCrypt(String mobile, String orgChannel) {
        if(!KeyType.phone.encrypted(mobile)){
            String res = secureStorageService.enCryptData(mobile, DataTypeEnum.MOBILE_TYPE);
            if(OrgChannelEnum.ifCtripChannel(orgChannel)) {
                return transMobile(res);
            }
           return res;
        }
        return mobile;
    }

    @Override
    public String identityEnCrypt(String identity) {
        if(!KeyType.personal_id.encrypted(identity)){
            return secureStorageService.enCryptData(identity, DataTypeEnum.IDENTITY_CARD);
        }
        return identity;
    }

    /**
     * 将tc加密的手机号转换成神盾加密的
     */
    private String transMobile(String mobile){
        Set<RawKey> keys = new HashSet<>();
        keys.add(new RawKey(KeyType.phone,mobile));
        Map<KeyType, Map<String, String>>  result = new HashMap<>();
        try {
            result = coreinfoService.qcTransform(keys);
            if(MapUtils.isNotEmpty(result)) {
                return result.get(KeyType.phone).get(mobile);
            }
        } catch (DecryptException e) {
            LOGGER.error("手机号神盾加密出错",e);
        }
       return null;
    }

    @Resource
    private SecureStorageService secureStorageService;
    @Resource
    private CoreinfoService coreinfoService;

    public static final Logger LOGGER = LoggerFactory.getLogger(ParamProcServiceImpl.class);
}
