package com.qunar.fintech.plat.admin.query.service.impl;

import com.google.common.collect.Maps;
import com.qunar.fintech.plat.admin.query.service.CipherConvertor;
import com.qunar.fintech.plat.admin.query.service.OcrService;
import com.qunar.fintech.plat.admin.query.support.CheckUtil;
import com.qunar.fintech.plat.admin.query.support.SignKeyConfig;
import com.qunar.fintech.plat.admin.query.vo.FaceAuditRes;
import com.qunar.fintech.util.http.HttpCaller;
import com.qunar.fintech.plat.admin.query.vo.QueryOcrReq;

import com.qunar.fintech.util.http.HttpRes;
import com.qunar.fintech.util.simple.DateUtils;
import com.qunar.fintech.util.simple.JsonUtils;
import com.qunar.fintech.util.web.api.SignatureSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Callable;

@Service
public class OcrServiceImpl implements OcrService {

    @Override
    public String queryOcrList(final QueryOcrReq req) {
        final Map<String, String> map = Maps.newHashMap();

        //idCode tc->神盾
        map.put("idCode", cipherConvertor.idCodeQunar2Ctrip(req.getIdCode()));
        map.put("platOpenId", req.getPlatOpenId());
        map.put("source", req.getSource());
        map.put("errorCode", req.getErrorCode());
        map.put("startTime", DateUtils.date2str(req.getStartTime(), DateUtils.FORMATTYPE8));
        map.put("endTime", DateUtils.date2str(req.getEndTime(), DateUtils.FORMATTYPE8));
        map.put("pageSize", req.getPageSize());
        map.put("pageIndex", String.valueOf((Integer.parseInt(req.getPageIndex()) - 1) * Integer.parseInt(req.getPageSize())));
        map.put("orgChannel", "QUNAR");
        HttpRes<FaceAuditRes> res = creditHttpCaller.jsonRequest(LOGGER, "fin.operate.queryOcrList",
                FaceAuditRes.class, faceRequestAddress, new Callable<Map<String, String>>() {
                    @Override
                    public Map<String, String> call() throws Exception {

                        Map<String, String> param = Maps.newHashMap();
                        param.put("appId", "ft_admin");
                        param.put("bizContent", JsonUtils.toJson(map));
                        param.put("partner", "QUNAR");
                        param.put("service", "fin.operate.queryOcrList");
                        param.put("version", "1.0.0");
                        param.put("hmac", SignatureSupport.sign(param, SignKeyConfig.getSignKey("100012628")));
                        return param;

                    }
                }, null, 1);
        CheckUtil.validHttpResponse(res);
        return res.getData().getBizContent();
    }

    @Override
    public String queryOcrImg(String token) {
        final Map<String, String> map = Maps.newHashMap();
        map.put("token", token);
        HttpRes<FaceAuditRes> res = creditHttpCaller.jsonRequest(LOGGER, "fin.operate.queryUploadImgByToken",
                FaceAuditRes.class, faceRequestAddress, new Callable<Map<String, String>>() {
                    @Override
                    public Map<String, String> call() throws Exception {

                        Map<String, String> param = Maps.newHashMap();
                        param.put("appId", "ft_admin");
                        param.put("bizContent", JsonUtils.toJson(map));
                        param.put("partner", "QUNAR");
                        param.put("service", "fin.operate.queryUploadImgByToken");
                        param.put("version", "1.0.0");
                        param.put("hmac", SignatureSupport.sign(param, SignKeyConfig.getSignKey("100012628")));
                        return param;

                    }
                }, null, 1);
        CheckUtil.validHttpResponse(res);
        return res.getData().getBizContent();
    }

    @Resource
    CipherConvertor cipherConvertor;

    @Resource(name = "creditHttpCaller")
    private HttpCaller creditHttpCaller;

    @Value("${ctrip.face.url}") //${ctrip.face.url}
    private String faceRequestAddress;


    private static final Logger LOGGER = LoggerFactory.getLogger(OcrServiceImpl.class);
}
