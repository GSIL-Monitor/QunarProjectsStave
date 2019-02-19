package com.qunar.fintech.plat.admin.query.service.impl;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qunar.fintech.plat.admin.query.dao.preloan.IousUserInfoDao;
import com.qunar.fintech.plat.admin.query.dao.preloan.TblVirtualContractDao;
import com.qunar.fintech.plat.admin.query.entity.TblIousUserInfo;
import com.qunar.fintech.plat.admin.query.entity.TblVirtualContract;
import com.qunar.fintech.plat.admin.query.enums.DataTypeEnum;
import com.qunar.fintech.plat.admin.query.service.CommonService;
import com.qunar.fintech.plat.admin.query.service.QueryIdsService;
import com.qunar.fintech.plat.admin.query.service.SecureStorageService;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import com.qunar.fintech.plat.admin.query.vo.QueryUserIdReqDto;
import com.qunar.pay.finance.ious.common.enums.OrgChannelEnum;
import com.qunar.pay.finance.ious.common.enums.ProductEnum;
import com.qunar.tc.core.info.api.KeyType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by bob.li on 2015/12/23.
 */
@Service
public class CommonServiceImpl implements CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Resource
    private IousUserInfoDao iousUserInfoDao;
    @Resource
    private SecureStorageService secureStorageService;
    @Resource
    private TblVirtualContractDao tblVirtualContractDao;
    @Autowired
    private QueryIdsService queryIdsService;

    @Override
    public String getQCreditTradeNo() {
        return "FPP_IOUS_" + String.valueOf(new Date().getTime());
    }

    /**
     * 如果request中包含mobile则会查询userId填充进去
     * 注：仅限request中mobile等各个Field名称一致的情况
     */
    @Override
    public <Request> Request addUserIdWithCheck(Request request) throws Exception {
        if (request == null) {
            return request;
        }
        Class requestClass = request.getClass();
        String mobile = String.valueOf(getField(request, requestClass, "mobile"));
        String identity = String.valueOf(getField(request, requestClass, "identity"));
        String userName = String.valueOf(getField(request, requestClass, "userName"));
        if(StringUtils.isNotEmpty(identity) || StringUtils.isNotEmpty(mobile) || StringUtils.isNotEmpty(userName)) {
            if (StringUtils.isNotEmpty(mobile) && !KeyType.phone.encrypted(mobile)) {
                setField(request, requestClass, "mobile",
                        secureStorageService.enCryptData(mobile, DataTypeEnum.MOBILE_TYPE));
            }
            if (StringUtils.isNotEmpty(identity) && !identity.equals("null") && !KeyType.personal_id.encrypted(identity)) {
                setField(request, requestClass, "identity",
                        secureStorageService.enCryptData(identity, DataTypeEnum.IDENTITY_CARD));
            }
            String userId = this.getUserId(buildQueryUserIdReq(request));
            Preconditions.checkArgument(StringUtils.isNotEmpty(userId), "未查到用户id!request:{}", request);
            String originalUserId = String.valueOf(getField(request, requestClass, "userId"));
            Preconditions.checkArgument(StringUtils.isEmpty(originalUserId) || Objects.equal(userId, originalUserId),
                    "userId　not matched!");
            setField(request, requestClass, "userId", userId);
        }
        return request;
    }

    public void mobileToId(QueryDto queryDto) {
        if(org.apache.commons.lang.StringUtils.isBlank(queryDto.getMobile()) || org.apache.commons.lang.StringUtils.isNotBlank(queryDto.getUserId())) {
            return;
        }
        //查询携程拿去花数据,需要将手机号转为openId
        if(OrgChannelEnum.ifCtripChannel(queryDto.getOrgChannel()) && ProductEnum.ifIOUS(queryDto.getProductNo())) {
            String openId = queryIdsService.queryIdsByMobile(queryDto.getMobile()).getOpenId();
            if(com.qunar.pay.g2.utils.common.StringUtils.isNotBlank(openId)) {
                queryDto.setUserId(openId);
                queryDto.setMobile("");
            }
        }
        //查询携程拿去花数据,需要将手机号转为platOpenId
        if(OrgChannelEnum.ifCtripChannel(queryDto.getOrgChannel()) && ProductEnum.ifCASH(queryDto.getProductNo())) {
            String platOpenId = queryIdsService.queryIdsByMobile(queryDto.getMobile()).getPlatOpenId();
            if(com.qunar.pay.g2.utils.common.StringUtils.isNotBlank(platOpenId)) {
                queryDto.setUserId(platOpenId);
                queryDto.setMobile("");
            }
        }
        LOGGER.info("mobileToId queryDto resp is {}",queryDto);
    }

    private String getUserId(QueryUserIdReqDto queryUserIdReqDto) {
        QueryDto request = new QueryDto();
        request.setMobile(queryUserIdReqDto.getMobile());
        request.setUserName(queryUserIdReqDto.getUserName());
        request.setProductNo(queryUserIdReqDto.getProductNo());
        request.setTppCode(queryUserIdReqDto.getTppCode());
        request.setOrgChannel(queryUserIdReqDto.getOrgChannel());
        request.setIdentity(queryUserIdReqDto.getIdentityCode());
        LOGGER.info("queryIousAccountRequest:{}", request);
        List<TblIousUserInfo> iousUserInfos = iousUserInfoDao.queryIousAccountByRequest(request,null);
        return CollectionUtils.isEmpty(iousUserInfos) ? null : iousUserInfos.get(0).getUserId();
    }

    private  <Request> QueryUserIdReqDto buildQueryUserIdReq(Request request) throws Exception {
        Class requestClass = request.getClass();
        QueryUserIdReqDto queryUserIdReqDto = new QueryUserIdReqDto();
        queryUserIdReqDto.setMobile((String)getField(request, requestClass, "mobile"));
        queryUserIdReqDto.setOrgChannel((String)getField(request, requestClass, "orgChannel"));
        queryUserIdReqDto.setProductNo((String)getField(request, requestClass, "productNo"));
        queryUserIdReqDto.setTppCode((String)getField(request, requestClass, "tppCode"));
        queryUserIdReqDto.setUserName((String)getField(request, requestClass, "userName"));
        queryUserIdReqDto.setIdentityCode((String)getField(request, requestClass, "identity"));
        return queryUserIdReqDto;
    }

    /**
     * 若request是代理类，则此方法不好使，用getValueByReadMethod方法
     */
    private <T> Object getField(T request, Class<T> requestClass, String fieldName) throws Exception {
        Field field = tryGetDeclaredField(requestClass, fieldName);
        if (field == null) {
            LOGGER.error("get field error! request:{}", request);
            return null;
        }
        return tryGetFieldValue(field, request);
    }

    private Field tryGetDeclaredField(Class c, String fieldName) {
        try {
            Field field = c.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            LOGGER.error("getDeclaredField error! class:{}, fieldName:{}", c, fieldName, e);
            return null;
        }
    }

    private <T> Object tryGetFieldValue(Field field, T instance) {
        try {
            return field.get(instance);
        } catch (Exception e) {
            LOGGER.error("get field value error!field:{}, instance:{}", field, instance, e);
            return null;
        }
    }


    private <Request> void setField(Request request, Class<Request> requestClass, String fieldName, Object value)
            throws Exception {
        Field field = requestClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(request, value);
    }


    @Override
    public <QueryResult> void addUserNameAndMobile(Collection<QueryResult> result){
        if (CollectionUtils.isEmpty(result)){
            LOGGER.info("addUserNameAndMobile#resultList is empty");
            return;
        }
        try {
            Map<String, TblVirtualContract> virtualContractMap = getUserNameAndMobileMap(result);
            if (MapUtils.isEmpty(virtualContractMap)){
                LOGGER.info("addUserNameAndMobile#virtualContractMap is empty");
                return;
            }
            setUserNameAndMobileForQueryResult(result, virtualContractMap);
        }catch (Exception e){
            LOGGER.error("addUserNameAndMobile#ERROR.", e);
        }
    }

    /**
     * 获取用户姓名和手机号
     */
    private <QueryResult> Map getUserNameAndMobileMap(Collection<QueryResult> queryResultList) throws Exception {
        List<String> userIdList = Lists.newArrayList();
        for (QueryResult queryResult : queryResultList) {
            String uid = getUserIdFromQueryResult(queryResult);
            if (uid == null){
                continue;
            }
            userIdList.add(uid);
        }
        LOGGER.info("userIdList:{}", userIdList);
        List<TblVirtualContract> virtualContracts = tblVirtualContractDao.queryMobileByUserId(userIdList);
        Map<String,TblVirtualContract> map = Maps.newHashMap();
        for(TblVirtualContract virtualContract : virtualContracts) {
            map.put(virtualContract.getUserId(),virtualContract);
        }
        return map;
    }

    private <QueryResult> String getUserIdFromQueryResult(QueryResult queryResult) throws Exception {
        Class queryResultClass = queryResult.getClass();
        LOGGER.info("getUserIdFromQueryResult#queryResultClass:{}", queryResultClass.getCanonicalName());
        LOGGER.info("getUserIdFromQueryResult#Fields:{}", Arrays.toString(queryResultClass.getDeclaredFields()));

        return (String)getValueByReadMethod(queryResult, queryResultClass, "userId");
    }



    /**
     * 设置用户姓名,身份证号和手机号
     */
    private <QueryResult> void setUserNameAndMobileForQueryResult(Collection<QueryResult> queryResultList, Map<String, TblVirtualContract> virtualContractMap) throws Exception {
        for (QueryResult queryResult : queryResultList) {
            String userId = getUserIdFromQueryResult(queryResult);
            TblVirtualContract tblVirtualContract = virtualContractMap.get(userId);
            if (tblVirtualContract == null){
                continue;
            }
            Class queryResultClazz = queryResult.getClass();
            setValueByWriteMethod(queryResult, queryResultClazz, "userName", tblVirtualContract.getUserName());
            setValueByWriteMethod(queryResult, queryResultClazz, "mobile", tblVirtualContract.getMobile());
            setValueByWriteMethod(queryResult, queryResultClazz, "identity", tblVirtualContract.getIdentityCode());
        }
    }


    /**
     * 通过get方法获取属性值
     */
    private<T> Object getValueByReadMethod(T obj, Class<T> clz, String fieldName){
        Object fieldValue = null;
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clz);
            Method getMethod = pd.getReadMethod();
            fieldValue = getMethod.invoke(obj);
        }catch (Exception e){
            LOGGER.error("getValueByReadMethod#ERROR. class:{}, fieldName:{}", clz,fieldName, e);
        }
        return fieldValue;
    }

    /**
     * 通过set方法设置属性值
     */
    private<T> void setValueByWriteMethod(T obj, Class<T> clz, String fieldName, Object value){
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clz);
            LOGGER.info("setValueByWriteMethod# pd: {}", pd);
            Method setMethod = pd.getWriteMethod();
            LOGGER.info("setValueByWriteMethod# method: {}, value:{}", setMethod, value);
            setMethod.invoke(obj, value);
        }catch (Exception e){
            LOGGER.error("setValueByWriteMethod#ERROR. class:{}, fieldName:{}, value:{}", clz, fieldName, clz);
        }
    }
}
