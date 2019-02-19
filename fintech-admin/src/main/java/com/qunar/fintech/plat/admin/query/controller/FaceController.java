package com.qunar.fintech.plat.admin.query.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qunar.fintech.plat.admin.query.entity.UserLivingImg;
import com.qunar.fintech.plat.admin.query.entity.UserPartnerImg;
import com.qunar.fintech.plat.admin.query.service.FaceAuditService;
import com.qunar.fintech.plat.admin.query.support.SelectQconfig;
import com.qunar.fintech.plat.admin.query.vo.FaceAuditLogDto;
import com.qunar.fintech.plat.admin.query.vo.QueryFaceDto;
import com.qunar.fintech.plat.admin.query.vo.QueryFaceReq;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.fintech.util.simple.DateUtils;
import com.qunar.fintech.util.simple.JsonUtils;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import com.qunar.pay.g2.utils.common.StringUtils;
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
 * 人脸人工审核
 * Created by zengjing on 18-10-30.
 */
//todo 上线前不要删除，代码review
@RequestMapping("/finance/face")
@Controller
public class FaceController {
    @RequiresPermissions("finance:query")
    @GetMapping("/toAudit")
    public String toAudit(Model model, String token, String auditUser) {

        logger.info("#toAudit param token is {}", token);
        ParamChecker.notBlank(token, "token can not be blank!");

        Map<String, String> dataMap = JsonUtils.jsonToMap(faceAuditService.queryDetailByToken(token));
        Map<String, String> faceObj = JsonUtils.jsonToMap(dataMap.get("faceObj"));
        List<UserLivingImg> livingImgList = JSONObject.parseArray(dataMap.get("livingImgs"), UserLivingImg.class);


        faceObj.put("createTime", DateUtils.date2str(DateUtils.str2date(faceObj.get("translateTime"), DateUtils.FORMATTYPE8), DateUtils.FORMATTYPE1));
        String status = faceObj.get("status");
        if (status.equals("0")) {
            faceObj.put("status", "初始");
        } else if (status.equals("1")) {
            faceObj.put("status", "人脸采集成功");
        } else if (status.equals("2")) {
            faceObj.put("status", "人脸采集失败");
        } else if (status.equals("3")) {
            faceObj.put("status", "检验成功");
        } else if (status.equals("4")) {
            faceObj.put("status", "检验失败");
        }
        faceObj.put("auditUser", auditUser);


        for (UserLivingImg u : livingImgList) {
            switch (u.getAction()) {
                case "best":
                    u.setAction("最佳照片");
                    break;
                case "env":
                    u.setAction("云端检测照片");
                    break;
                case "1":
                    u.setAction("眨眼");
                    break;
                case "2":
                    u.setAction("张嘴");
                    break;
                case "3":
                    u.setAction("左右转头");
                    break;
                case "4":
                    u.setAction("上下点头");
                    break;
                case "5":
                    u.setAction("向右转头");
                    break;
                case "6":
                    u.setAction("向左转头");
                    break;
                case "7":
                    u.setAction("抬头");
                    break;
                case "8":
                    u.setAction("低头");
                    break;
                case "static":
                    u.setAction("静态");
                    break;
                default:
                    break;
            }
        }
        model.addAttribute("faceObj", faceObj);
        model.addAttribute("faceImages", livingImgList);
        if (faceObj.get("faceType").equals("2") || faceObj.get("faceType").equals("0")) {
            List<UserPartnerImg> partnerImgList = JSONObject.parseArray(dataMap.get("partnerImgs"), UserPartnerImg.class);
            model.addAttribute("cpFaceImages", partnerImgList);
        }
        return "/query/faceAudit";
    }

    @PostMapping("/doAudit")
    @ResponseBody
    public String doAudit(@RequestBody FaceAuditLogDto faceAuditLogDto) {
        try {
            faceAuditService.commitAuthRes(faceAuditLogDto);
        } catch (Exception th) {
            return "1111";
        }
        return "0000";
    }

    @GetMapping("/faceIndex")
    String faceIndex(Model model) {
        //下拉框数据
        Map<String, String> map = SelectQconfig.getSelectMap();
        model.addAttribute("sourceList", JsonUtils.jsonToMap(map.get("source")));
        model.addAttribute("checkChannelList", JsonUtils.jsonToMap(map.get("checkChannel")));
        return "/query/queryFace";
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @PostMapping("/face-Info")
    QueryResponse<QueryFaceDto> queryFaceInfo(@RequestBody QueryFaceReq queryFaceReq) {
        //参数校验
        String status = checkAuthReqLegal(queryFaceReq);
        QueryResponse res = new QueryResponse();
        if (!"1".equals(status)) {
            res.setTotal(Integer.parseInt(status));
            res.setRows(Lists.newArrayList());
            return res;
        }
        Map<String, String> data = JsonUtils.jsonToMap(faceAuditService.queryFaceList(queryFaceReq));
            List<QueryFaceDto> queryFaceReqs = JSONObject.parseArray(data.get("faceAuditList"), QueryFaceDto.class);
            for (QueryFaceDto q : queryFaceReqs) {
                q.setCreateTime(q.getTranslateTime());
                switch (q.getAuditResult()) {
                    case "1":
                        q.setAuditResult("一致");
                        break;
                    case "2":
                        q.setAuditResult("不一致");
                        break;
                    case "3":
                        q.setAuditResult("疑似");
                        break;
                    case "4":
                        q.setAuditResult("线下审核一致");
                        break;
                    default:
                        break;
                }
                switch (q.getStatus()) {
                    case "0":
                        q.setStatus("初始");
                        break;
                    case "1":
                        q.setStatus("人脸采集成功");
                        break;
                    case "2":
                        q.setStatus("人脸采集失败");
                        break;
                    case "3":
                        q.setStatus("校验成功");
                        break;
                    case "4":
                        q.setStatus("校验失败");
                        break;
                    default:
                        break;
                }

                switch (q.getFaceType()) {
                    case "0":
                        q.setFaceType("无源");
                        break;
                    case "1":
                        q.setFaceType("有源");
                        break;
                    case "2":
                        q.setFaceType("无源+有源");
                        break;
                    default:
                        break;
                }

                q.setAuditTime(DateUtils.date2str(DateUtils.str2date(q.getAuditTime(), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss"));
                q.setCreateTime(DateUtils.date2str(DateUtils.str2date(q.getTranslateTime(), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss"));
            }

        res.setRows(queryFaceReqs);
            res.setTotal(Integer.parseInt(data.get("dataCount")));
        return res;
    }

    private String checkAuthReqLegal(QueryFaceReq req) {

        if (org.apache.commons.lang.StringUtils.isBlank(req.getToken()) && org.apache.commons.lang.StringUtils.isBlank(req.getIdCode())
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

    private static final Logger logger = LoggerFactory.getLogger(FaceController.class);

    @Autowired
    private FaceAuditService faceAuditService;
}
