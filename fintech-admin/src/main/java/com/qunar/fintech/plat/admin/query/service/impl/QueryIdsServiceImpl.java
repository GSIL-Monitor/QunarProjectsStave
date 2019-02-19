package com.qunar.fintech.plat.admin.query.service.impl;

import com.alibaba.fastjson.JSON;
import com.ctrip.finance.common.exceptions.BusinessException;
import com.google.common.collect.Maps;
import com.qunar.fintech.nemo.api.dto.request.QueryPlatOpenIdReqDto;
import com.qunar.fintech.nemo.api.dto.response.QueryPlatOpenIdResDto;
import com.qunar.fintech.nemo.api.enums.UserChannel;
import com.qunar.fintech.nemo.api.facade.UserIdFacade;
import com.qunar.fintech.plat.admin.query.service.QueryIdsService;
import com.qunar.fintech.plat.admin.query.vo.QueryIdDto;
import com.qunar.fintech.plat.admin.support.util.HttpUtil;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import com.qunar.pay.finance.utils.Base64Util;
import com.qunar.pay.finance.utils.JsonUtil;
import com.qunar.pay.finance.utils.SignatureUtil;
import com.qunar.seccenter.coreinfo.api.CoreinfoService;
import com.qunar.seccenter.coreinfo.exception.DecryptException;
import com.qunar.tc.core.info.api.KeyType;
import com.qunar.tc.core.info.api.RawKey;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created with Intellij IDEA.
 * Description:
 * User: weiduan
 * Date: 2018-03-01
 * Time: 下午8:15
 */
@Service
public class QueryIdsServiceImpl implements QueryIdsService {

    public QueryIdDto queryIdsByMobile(String mobile){
        QueryIdDto queryIdDto = new QueryIdDto();
        String cMobile = transMobile(mobile);
        if(StringUtils.isNotEmpty(cMobile)) {
            //取携程的uid和openId
            queryIdDto = JsonUtil.getGson().fromJson(getCtripId(cMobile),QueryIdDto.class);
            logger.info("queryIdDto is {}",queryIdDto);
            //获取platOpenId
            if(StringUtils.isNotBlank(queryIdDto.getOpenId())) {
                String platOpenId = getPlatOpenId(queryIdDto.getOpenId());
                queryIdDto.setPlatOpenId(platOpenId);
            }
        }
        return queryIdDto;
    }

    /**
     * 将tc加密的手机号转换成神盾加密的
     */
    private String transMobile(String mobile) {
        Set<RawKey> keys = new HashSet<>();
        keys.add(new RawKey(KeyType.phone,mobile));
        Map<KeyType, Map<String, String>>  result;
        try {
            result = coreinfoService.qcTransform(keys);
            if(MapUtils.isNotEmpty(result)) {
                return result.get(KeyType.phone).get(mobile);
            }
        } catch (DecryptException e) {
            logger.error("手机号神盾加密出错",e);
        }
        return null;
    }

    /**
     * 通过openId获取platOpenId
     */
    private String getPlatOpenId(String openId) {
        QueryPlatOpenIdReqDto reqDto = QueryPlatOpenIdReqDto.buildWithUserId(openId, UserChannel.C.name());
        QResponse<QueryPlatOpenIdResDto> response = userIdFacade.queryPlatOpenId(reqDto);
        if(response != null){
            if(response.isSuccess()) {
                return response.getData().getPlatOpenId();
            } else {
                logger.error("获取platOpenId失败，{}",response.getReturnMsg());
            }
        } else {
            logger.error("获取platOpenId失败，response is null");
        }
        return null;
    }

    private String getCtripId(String mobile) {

        String response = null;
        try{
            logger.info("ctripURL is {}, signKey is {} ",ctripURL,signKey);
            Map<String,String> map = Maps.newHashMap();
            map.put(MOBILE,mobile);
            String param = JsonUtil.getGson().toJson(map);

            TreeMap<String, String> parameterMap = Maps.newTreeMap();
            // 对报文进行base64加密
            String base64Content = Base64Util.encode(param);
            parameterMap.put(CONTENT, base64Content);
            parameterMap.put(SERVICE,SERVICE_NAME);
            parameterMap.put(VERSION,"1.0");
            // 计算签名
            String sign = SignatureUtil.getSignature(parameterMap, signKey);
            // 把签名也作为请求参数放到请求中
            parameterMap.put(SIGN, sign);
            // 发送请求
            String requestBody = JsonUtil.getGson().toJson(parameterMap);
            // 请求携程
            logger.info("HttpUtil.post_request:{}", requestBody);
            response = HttpUtil.post(ctripURL, requestBody);
            logger.info("HttpUtil.post_ctripResult:{}", response);
            if (StringUtils.isEmpty(requestBody)) {
                //throw new BusinessException(ExceptionCode.EXTERNAL_SYSTEM_REQ_INVALID, "httpclient返回无内容");
                logger.error("返回无内容!");
                return null;
            }
        } catch (BusinessException e) {
            logger.error("sendCashData#{} request failed", e);
        } catch (Exception e) {
            logger.error("sendCashData#{} request failed", e);
        }
        if (StringUtils.isNotBlank(response)) {
            try {
                Map result = JSON.parseObject(response,Map.class);
                logger.debug("result is {}",result);
                return Base64Util.decode((String) result.get(CONTENT));
            } catch (Exception e) {
                logger.error("json转换出错",e);
            }
        }
        return null;
    }

    @Resource
    private UserIdFacade userIdFacade;

    @Resource
    private CoreinfoService coreinfoService;

    @Value("${signKey}")
    private String signKey;

    @Value("${ctripURL}")
    private String ctripURL;

    private static final String SIGN = "sign";

    private static final String MOBILE = "mobile";

    private static final String SERVICE = "service";

    private static final String SERVICE_NAME = "cfb_queryUserOpenIdByMobile";

    private static final String VERSION = "version";

    private static final String CONTENT = "content";

    private static final Logger logger = LoggerFactory.getLogger(QueryIdsService.class);
}
