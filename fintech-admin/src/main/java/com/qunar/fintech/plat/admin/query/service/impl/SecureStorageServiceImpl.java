package com.qunar.fintech.plat.admin.query.service.impl;

import com.qunar.fintech.plat.admin.query.enums.DataTypeEnum;
import com.qunar.fintech.plat.admin.query.exception.FppException;
import com.qunar.fintech.plat.admin.query.service.SecureStorageService;
import com.qunar.tc.core.info.api.KeyType;
import com.qunar.tc.core.info.api.PersonInfo;
import com.qunar.tc.core.info.api.Result;
import com.qunar.tc.core.info.client.decrypt.InfoDecryptClient;
import com.qunar.tc.core.info.client.encrypt.InfoEncryptClient;
import com.qunar.tc.core.info.client.transform.InfoTransformClient;
import com.qunar.tc.core.info.exception.DecryptFailException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 安全存储服务实现类
 * @author liqiang.jiang
 * @since 15-11-03
 */
@Service
public class SecureStorageServiceImpl implements SecureStorageService {

    private static final Logger logger = LoggerFactory.getLogger(SecureStorageServiceImpl.class);

    @Resource
    private InfoEncryptClient infoEncryptClient;

    @Resource
    private InfoDecryptClient infoDecryptClient;
    @Resource
    private InfoTransformClient infoTransformClient;

    @Override
    public String enCryptData(String data, DataTypeEnum dataTypeEnum) {
        logger.info("infoEncryptClient.encrypt.req data is {}",data);
        String index = null;
        try {
            index = infoEncryptClient.encrypt(data, tranDataType(dataTypeEnum));
            logger.info("infoEncryptClient.encrypt.response,index:"+ index);
        } catch (Throwable e) {
            logger.error("call secureStorage encrypt", e);
            throw new FppException("调用tc安全存储失败");
        }
        if (StringUtils.isBlank(index) || index.equalsIgnoreCase(data)) {
            logger.error("infoEncryptClient.encrypt error,dataIndex is null or data equal dataIndex");
            throw new FppException("调用tc安全存储失败");
        }
        return index;
    }

    @Override
    public String deCryptData(String index, DataTypeEnum dataTypeEnum) {
        String data = null;
        try {
            logger.info("infoDecryptClient.decrypt.request:"+ index);
            data = infoDecryptClient.decrypt(index, tranDataType(dataTypeEnum));
            logger.info("infoDecryptClient.decrypt.response,index:"+ index);
        } catch (Throwable e) {
            logger.error("call secureStorage decrypt error,", e);
        }
        if (StringUtils.isBlank(data) || data.equalsIgnoreCase(index)) {
            logger.error("infoEncryptClient.decrypt error,data is null or dataIndex equal data");
            throw new FppException("调用tc安全存储失败");
        }
        return data;
    }


    /**
     * 将身份证信息解密
     * @param data 加密的身份证信息
     * @return 客户的相关信息
     */
    @Override
    public PersonInfo decryptData(String data) {
        Result<PersonInfo> result=null;
        try {
            if(StringUtils.isBlank(data)){
                logger.error("infoTransformClient.decryptData error,data is null");
                throw new FppException("解密数据位空");
            }
            result=infoTransformClient.personalIdInfoMask(data,null);
        } catch (DecryptFailException e) {
            logger.error("call secureStorage decrypt error,", e);
        }
        if (null ==result || null==result.getData()) {
            logger.error("infoTransformClient.decrypt error,data is null");
            throw new FppException("调用tc安全存储失败");
        }
        return result.getData();
    }

    /**
     * 数据类型转换
     * @param dataTypeEnum
     * @return
     */
    private KeyType tranDataType(DataTypeEnum dataTypeEnum){
        if(DataTypeEnum.IDENTITY_CARD.equals(dataTypeEnum)){
            return KeyType.personal_id;
        }else if(DataTypeEnum.CARD_TYPE.equals(dataTypeEnum)){
            return KeyType.bankNo;
        }else if(DataTypeEnum.PASSPORT.equals(dataTypeEnum)||DataTypeEnum.OFFICER_CERT.equals(dataTypeEnum)||DataTypeEnum.HKM_PASSER.equals(dataTypeEnum)){
            return KeyType.passport;
        }else if(DataTypeEnum.MOBILE_TYPE.equals(dataTypeEnum)){
            return KeyType.phone;
        }else if(DataTypeEnum.EMAIL_TYPE.equals(dataTypeEnum)){
            return KeyType.mail;
        }else {
            return KeyType.other;
        }
    }
}
