package com.qunar.fintech.plat.admin.query.service.impl;

import com.google.common.collect.Maps;
import com.qunar.fintech.plat.admin.query.service.CipherConvertor;
import com.qunar.fintech.plat.admin.query.service.FaceAuditService;
import com.qunar.fintech.plat.admin.query.support.CheckUtil;
import com.qunar.fintech.plat.admin.query.support.SignKeyConfig;
import com.qunar.fintech.plat.admin.query.vo.FaceAuditLogDto;
import com.qunar.fintech.plat.admin.query.vo.FaceAuditRes;
import com.qunar.fintech.plat.admin.query.vo.FaceRes;
import com.qunar.fintech.plat.admin.query.vo.QueryFaceReq;
import com.qunar.fintech.plat.admin.support.util.ImageUtil;
import com.qunar.fintech.util.http.HttpCaller;
import com.qunar.fintech.util.http.HttpRes;
import com.qunar.fintech.util.simple.DateUtils;
import com.qunar.fintech.util.simple.JsonUtils;
import com.qunar.fintech.util.web.api.SignatureSupport;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.exception.CommonApiErrorCodes;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.bind.SchemaOutputResolver;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 人脸人工审核
 * Created by zengjing on 18-10-30.
 */
@Service
public class FaceAuditServiceImpl implements FaceAuditService {

    @Override
    public String queryFaceList(final QueryFaceReq queryFaceReq) {
        final Map<String, String> map = Maps.newHashMap();

        map.put("faceType", queryFaceReq.getFaceType());
        //idCode tc->神盾
        map.put("idCode", cipherConvertor.idCodeQunar2Ctrip(queryFaceReq.getIdCode()));
        map.put("platOpenId", queryFaceReq.getPlatOpenId());
        map.put("source", queryFaceReq.getSource());
        map.put("status", queryFaceReq.getStatus());
        map.put("token", queryFaceReq.getToken());
        map.put("startTime", DateUtils.date2str(queryFaceReq.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
        map.put("endTime", DateUtils.date2str(queryFaceReq.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
        map.put("pageSize", queryFaceReq.getPageSize());
        map.put("pageIndex", String.valueOf((Integer.parseInt(queryFaceReq.getPageIndex()) - 1) * Integer.parseInt(queryFaceReq.getPageSize())));
        map.put("checkChannel", queryFaceReq.getCheckChannel());
        map.put("orgChannel", "QUNAR");

        HttpRes<FaceAuditRes> res = creditHttpCaller.jsonRequest(LOGGER, "fin.operate.queryFaceList",
                FaceAuditRes.class, faceRequestAddress, new Callable<Map<String, String>>() {
                    @Override
                    public Map<String, String> call() throws Exception {

                        Map<String, String> param = Maps.newHashMap();
                        param.put("appId", "ft_admin");
                        param.put("bizContent", JsonUtils.toJson(map));
                        param.put("partner", "QUNAR");
                        param.put("service", "fin.operate.queryFaceList");
                        param.put("version", "1.0.0");
                        param.put("hmac", SignatureSupport.sign(param, SignKeyConfig.getSignKey("100012628")));
                        return param;

                    }
                }, null, 1);

        CheckUtil.validHttpResponse(res);
        return res.getData().getBizContent();
    }


    @Override
    public String queryDetailByToken(final String token) {

        final Map<String, String> map = Maps.newHashMap();

        map.put("token", token);
        map.put("orgChannel", "QUNAR");

        HttpRes<FaceRes> res = creditHttpCaller.jsonRequest(LOGGER, "fin.operate.queryDetailByToken",
                FaceRes.class, faceRequestAddress, new Callable<Map<String, String>>() {
                    @Override
                    public Map<String, String> call() throws Exception {
                        Map<String, String> param = Maps.newHashMap();
                        param.put("appId", "ft_admin");
                        param.put("bizContent", JsonUtils.toJson(map));
                        param.put("partner", "QUNAR");
                        param.put("service", "fin.operate.queryDetailByToken");
                        param.put("version", "1.0.0");
                        param.put("hmac", SignatureSupport.sign(param, SignKeyConfig.getSignKey("100012628")));
                        return param;
                    }
                }, null, 1);

        CheckUtil.validHttpResponse(res);

        return res.getData().getBizContent();
    }

    @Override
    public void commitAuthRes(FaceAuditLogDto faceAuditLogDto) {

        final Map<String, String> map = Maps.newHashMap();

        map.put("token", faceAuditLogDto.getToken());
        map.put("auditUser", faceAuditLogDto.getAuditUser());
        map.put("auditResult", faceAuditLogDto.getAuditResult());
        map.put("auditRemark", faceAuditLogDto.getAuditRemark());
        if ("4".equals(faceAuditLogDto.getAuditResult())) {

            String base64IdImg = faceAuditLogDto.getBase64IdPositiveImg();
            String x = faceAuditLogDto.getX();
            String y = faceAuditLogDto.getY();
            String w = faceAuditLogDto.getWidth();
            String h = faceAuditLogDto.getHeight();
            if (StringUtils.isEmpty(base64IdImg) || StringUtils.isEmpty(x) || StringUtils.isEmpty(y)
                    || StringUtils.isEmpty(w) || StringUtils.isEmpty(h))
                throw new IllegalArgumentException("图片或者裁剪参数缺失");
            try {
                String cutIdImg = ImageUtil.cutImage(base64IdImg, Integer.valueOf(x), Integer.valueOf(y),
                        Integer.valueOf(w), Integer.valueOf(h));
                //idCode tc->神盾
                map.put("idCode", cipherConvertor.idCodeQunar2Ctrip(faceAuditLogDto.getIdCode()));
                map.put("imgUrl", cutIdImg);
                map.put("sourceType", "AUDIT");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        HttpRes<FaceAuditRes> res = creditHttpCaller.jsonRequest(LOGGER, "fin.operate.commitAuthRes",
                FaceAuditRes.class, faceRequestAddress, new Callable<Map<String, String>>() {
                    @Override
                    public Map<String, String> call() throws Exception {

                        Map<String, String> param = Maps.newHashMap();
                        param.put("appId", "ft_admin");
                        param.put("bizContent", JsonUtils.mapToJson(map));
                        param.put("partner", "QUNAR");
                        param.put("service", "fin.operate.commitAuthRes");
                        param.put("version", "1.0.0");
                        param.put("hmac", SignatureSupport.sign(param, SignKeyConfig.getSignKey("100012628")));
                        return param;
                    }
                }, null, 1);

        CheckUtil.validHttpResponse(res);
    }


    @Resource
    CipherConvertor cipherConvertor;

    @Resource(name = "creditHttpCaller")
    private HttpCaller creditHttpCaller;

    @Value("${ctrip.face.url}")
    private String faceRequestAddress;


    private static final Logger LOGGER = LoggerFactory.getLogger(FaceAuditServiceImpl.class);
}
