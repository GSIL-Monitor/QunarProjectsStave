package com.qunar.fintech.plat.admin.newmarketing.controller;

import com.google.common.collect.Lists;
import com.qunar.fintech.plat.admin.newmarketing.dto.CouponDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.CouponQueryReq;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewInfoDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewNodeDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewNodeQueryReq;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewQueryReq;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewUpdateReq;
import com.qunar.fintech.plat.admin.newmarketing.exception.MarketingExceptionCode;
import com.qunar.fintech.plat.admin.newmarketing.service.FileUploadService;
import com.qunar.fintech.plat.admin.newmarketing.service.MarketingReviewService;
import com.qunar.fintech.plat.admin.newmarketing.service.ParamValidate;
import com.qunar.fintech.plat.admin.query.utils.JSONUtils;
import com.qunar.fintech.plat.admin.support.security.AccessLog;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.plat.admin.system.dao.entity.ReviewInfo;
import com.qunar.fintech.plat.admin.system.dao.mapper.ReviewInfoMapper;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author qun.shi
 * @since 2019-02-02 11:20 AM
 */
@RequestMapping("/newmarketing/review")
@Controller
public class MarketingReviewController {

    /**
     * 按条件查询审核记录
     */
    @AccessLog("查询ALL审核信息")
    @RequiresPermissions("market:review:manage")
    @ResponseBody
    @PostMapping("/list/{filterType}")
    R getReviewList(CouponQueryReq req, @PathVariable("filterType") Integer filterType, HttpSession httpSession) {
        String userId = (String) httpSession.getAttribute("userId");

        try {
            ParamChecker.notNull(userId, "无法获取当期登录用户id！");
            ParamChecker.notNull(filterType, "过滤类型不能为空！");

            ReviewQueryReq reviewQueryReq = new ReviewQueryReq();
            reviewQueryReq.setCouponQueryReq(req);
            reviewQueryReq.setFilterType(filterType);
            reviewQueryReq.setCurLoginUserId(userId);

            List<ReviewInfoDetailDto> reviewList =
                    marketingReviewService.getReviewInfoListByFilterType(reviewQueryReq);
            return R.ok().put("data", reviewList.toArray());
        } catch (Exception e) {
            logger.error("查询审核记录失败！ couponQueryResp={},filterType = {},userId={} msg={}", req, filterType, userId,e.getMessage());
            return R.error(e.getMessage());
        }
    }

    /**
     * 查询审核详细内容
     */
    @AccessLog("查询审核详细内容")
    @RequiresPermissions("market:review:manage")
    @ResponseBody
    @PostMapping("/getReviewDetail")
    R getReviewDetail(String reviewNo) {
        try {
            ParamChecker.notNull(reviewNo, "审核流水不能为空！");
            List<ReviewInfo> reviewInfos =
                    reviewInfoMapper.selectByReviewNo(Lists.<String>newArrayList(reviewNo));

            ReviewInfo reviewInfo = reviewInfos.get(0);
            return R.ok().put("data", reviewInfo.getContentValue());
        } catch (Exception e) {
            logger.error("查询审核记录失败！ reviewNo={}, msg={}", reviewNo,e.getMessage());
            return R.error(e.getMessage());
        }
    }

    /**
     * 查询审核的节点批复详情
     */
    @AccessLog("查询审核的节点详细")
    @RequiresPermissions("market:review:manage")
    @ResponseBody
    @PostMapping("/getReviewNodeList")
    R getReviewNodeList(String reviewNo) {
        try {
            ParamChecker.notBlank(reviewNo, "审核流水不能为空！");

            List<ReviewNodeDetailDto>reviewNodes
                    = marketingReviewService.queryNodesByReviewNo(reviewNo);

            return R.ok().put("data",reviewNodes.toArray());
        } catch (Exception e) {
            logger.error("查询审核记录失败！ reviewNo={}, msg={}", reviewNo,e.getMessage());
            return R.error(e.getMessage());
        }
    }

    /**
     * 提交审核记录
     */
    @AccessLog("提交审核记录")
    @RequiresPermissions("market:review:manage")
    @ResponseBody
    @PostMapping("/submit")
    R submit(ReviewQueryReq queryReq, HttpSession httpSession) {
        String userId = (String) httpSession.getAttribute("userId");

        try {
            ParamChecker.notNull(queryReq,"查询条件不能为空！");
            ParamChecker.notNull(queryReq.getReviewNos(),"审核流水不能为空！");
            ParamChecker.isTrue(queryReq.getReviewNos().size() > 0,"审核流水不能为空！");
            ParamChecker.notNull(userId, "无法获取当期登录用户id！");

            queryReq.setCurLoginUserId(userId);
            marketingReviewService.submit(queryReq);
        } catch (Exception e) {
            logger.error("提交审核记录失败！ couponQueryResp={},userId = {}, msg={}", queryReq, userId,e.getMessage());
            return R.error(e.getMessage());
        }
        return R.ok("提交审核记录成功！");
    }

    /**
     * 审核信息中的用户信息文件下载
     */
    @AccessLog("下载用户信息文件")
    @RequiresPermissions("market:review:manage")
    @ResponseBody
    @PostMapping("/userInfoFileDownLoad")
    ResponseEntity<byte[]> userInfoFileDownLoad(ReviewQueryReq queryReq){
        ParamChecker.notNull(queryReq.getReviewNos(),"查询条件不能为空！");
        ParamChecker.isTrue(queryReq.getReviewNos().size() > 0,"查询条件不能为空！");

        InputStream in = fileUploadService.downloadUserInfoFile(queryReq.getReviewNos().get(0));
        byte[] body = null;
        try {
            body=new byte[in.available()];
            in.read(body);
        } catch (IOException e) {
            logger.error("读取用户信息文件失败！，reviewNo={}",queryReq.getReviewNos());
            throw new BusiException(MarketingExceptionCode.DOWN_LOAD_FILE_FAIL);
        }

        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename="+queryReq.getReviewNos().get(0));
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;
    }

    /**
     * 审核人审核操作：通过或者拒绝
     */
    @AccessLog("批复审核记录")
    @RequiresPermissions("market:review:manage")
    @ResponseBody
    @PostMapping("/comment")
    R comment(ReviewNodeQueryReq req, HttpSession httpSession){
        String userId = (String) httpSession.getAttribute("userId");
        try {
            ParamChecker.notNull(userId, "无法获取当期登录用户id！");
            req.setReviewer(userId);

            marketingReviewService.comment(req);
        } catch (Exception e) {
            logger.error("节点批复失败！ couponQueryResp={}", req,e.getMessage());
            return R.error(e.getMessage());
        }
        return R.ok("批复成功！");
    }

    /**
     * 审核通过，执行操作
     */
    @AccessLog("发布，同步数据到线上")
    @RequiresPermissions("market:review:manage")
    @ResponseBody
    @PostMapping("/publish")
    R publish(ReviewQueryReq req){
        try {
            ParamChecker.notNull(req.getReviewNos(),"审核流水不能为空！");
            ParamChecker.notNull(req.getReviewNos().size() > 0,"审核流水不能为空！");

            marketingReviewService.pubish(req);
        } catch (Exception e) {
            logger.error("审核通过执行操作失败！ reviewQueryReq={}", req,e.getMessage());
            return R.error(e.getMessage());
        }
        return R.ok("执行审核的操作成功！");
    }

    /**
     * 审核通过，执行操作
     */
    @AccessLog("提交审核记录")
    @RequiresPermissions("market:review:manage")
    @ResponseBody
    @PostMapping("/update")
    R updateReviewInfo(@RequestPart("userInfoFile") MultipartFile userInfoFile, @RequestPart("req") ReviewUpdateReq req){
        try {
            ParamChecker.notBlank(req.getOldReviewNo(),"旧审核流水no不能为空！");
            ParamChecker.notBlank(req.getNewReviewNo(),"新审核流水no不能为空！");
            ParamChecker.notBlank(req.getReviewContentKey(),"审核内容key不能为空！");
            ParamChecker.notBlank(req.getReviewContentValue(),"审核内容不能为空！");

            CouponDetailDto couponDetailDto
                    = JSONUtils.getGson().fromJson(req.getReviewContentValue(),CouponDetailDto.class);

            // check 活动和券的配置
            paramValidate.checkActivityConfig(couponDetailDto);
            paramValidate.checkActivityConfig(couponDetailDto);

            marketingReviewService.updateReviewInfo(userInfoFile, req);
        } catch (Exception e) {
            logger.error("审核内容更新失败！ reviewQueryReq={}", req,e.getMessage());
            return R.error(e.getMessage());
        }
        return R.ok("审核内容更新成功！");
    }

    @Resource
    ParamValidate paramValidate;

    @Resource
    ReviewInfoMapper reviewInfoMapper;

    @Resource
    private MarketingReviewService marketingReviewService;

    @Resource
    private FileUploadService fileUploadService;

    private static final Logger logger = LoggerFactory.getLogger(MarketingReviewController.class);
}
