package com.qunar.fintech.plat.admin.query.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qunar.fintech.plat.admin.query.service.OcrService;
import com.qunar.fintech.plat.admin.query.support.SelectQconfig;
import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import com.qunar.fintech.plat.admin.query.vo.QueryOcrDto;
import com.qunar.fintech.plat.admin.query.vo.QueryOcrReq;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.fintech.plat.admin.query.vo.UserUploadImg;
import com.qunar.fintech.util.simple.JsonUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * ocr查询
 * Created by zengjing on 18-11-14.
 * 11：01
 */
@RequestMapping("/finance/ocr")
@Controller
public class OcrController {

    @RequiresPermissions("finance:query")
    @GetMapping("/toShowOcr")
    public String toAudit(Model model, String token) {
        logger.info("#toAudit param token is {}", token);
        ParamChecker.notBlank(token, "token can not be blank!");
        List<UserUploadImg> userUploadImgs = JSONObject.parseArray(ocrService.queryOcrImg(token), UserUploadImg.class);

        for (UserUploadImg u : userUploadImgs) {
            switch (u.getType()) {
                case "FRONT":
                    u.setType("正面");
                    break;
                case "BACK":
                    u.setType("反面");
                    break;
                default:
                    break;
            }
        }
        model.addAttribute("ocrImgs", userUploadImgs);
        return "/query/showOcrImg";
    }

    @GetMapping("/ocrIndex")
    String OcrIndex(Model model) {
        //下拉框数据
        Map<String, String> map = SelectQconfig.getSelectMap();
        model.addAttribute("sourceList", JsonUtils.jsonToMap(map.get("ocrSource")));
        return "/query/queryOcr";
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @PostMapping("/ocr-Info")
    QueryResponse<QueryOcrDto> queryFaceInfo(@RequestBody QueryOcrReq req) {
        QueryResponse res = new QueryResponse();
        //参数校验
        String status = checkAuthReqLegal(req);
        if (!"1".equals(status)) {
            res.setTotal(Integer.parseInt(status));
            res.setRows(Lists.newArrayList());
            return res;
        }
        Map<String, String> data = JsonUtils.jsonToMap(ocrService.queryOcrList(req));
        List<QueryOcrDto> queryOcrDtos = JSONObject.parseArray(data.get("ocrList"), QueryOcrDto.class);
        res.setRows(queryOcrDtos);
        res.setTotal(Integer.parseInt(data.get("dataCount")));
        return res;
    }

    private String checkAuthReqLegal(QueryOcrReq req) {
        if (org.apache.commons.lang.StringUtils.isBlank(req.getIdCode())
                && org.apache.commons.lang.StringUtils.isBlank(req.getPlatOpenId()) &&
                (req.getStartTime() == null || req.getEndTime() == null)) {
            return "-1";
        }

        if (req.getStartTime() != null && req.getEndTime() != null) {
            long milliSeconds = req.getEndTime().getTime() - req.getStartTime().getTime();
            if (milliSeconds <= 0) {
                return "-2";
            } else if (milliSeconds > 3600000) {
                return "-3";
            }
        }
        return "1";
    }

    @Autowired
    OcrService ocrService;

    private static final Logger logger = LoggerFactory.getLogger(OcrController.class);
}
