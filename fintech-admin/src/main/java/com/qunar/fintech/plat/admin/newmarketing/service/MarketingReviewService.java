package com.qunar.fintech.plat.admin.newmarketing.service;

import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewNodeDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewNodeQueryReq;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewUpdateReq;
import com.qunar.fintech.plat.admin.system.dao.entity.ReviewInfo;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewQueryReq;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewInfoDetailDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author qun.shi
 * @since 2019-01-31 7:49 PM
 */
public interface MarketingReviewService {

    /**
     * 查询审核节点详情
     */
    List<ReviewNodeDetailDto> queryNodesByReviewNo(String reviewNo);

    /**
     * 发布
     */
    void pubish(ReviewQueryReq req);

    /**
     * 节点批复：通过或者拒绝
     */
    void comment(ReviewNodeQueryReq req);
    /**
     * 增加一条审核记录
     */
    void add(MultipartFile userInfoFile,ReviewInfo reviewInfo);

    /**
     * 按场景查询审核记录
     */
    List<ReviewInfoDetailDto> getReviewInfoListByFilterType(ReviewQueryReq req);

    /**
     * 查询最新已通过的一条记录
     */
    ReviewInfo queryLastPassedReviewInfoByKey(String contentKey);

    /**
     * 提交待审核，不可编辑
     */
    void submit(ReviewQueryReq queryReq);

    /**
     * 更新审核信息
     */
    void updateReviewInfo(MultipartFile userInfoFile, ReviewUpdateReq req);
}
