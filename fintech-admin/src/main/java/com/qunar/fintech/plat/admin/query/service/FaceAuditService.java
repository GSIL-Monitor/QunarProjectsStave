package com.qunar.fintech.plat.admin.query.service;

import com.qunar.fintech.plat.admin.query.vo.FaceAuditLogDto;
import com.qunar.fintech.plat.admin.query.vo.QueryFaceReq;

/**
 * 人脸人工审核
 * Created by zengjing on 18-10-30.
 */
public interface FaceAuditService {


    /**
     * 查询列表页
     *
     * @param queryFaceReq
     * @return
     */
    String queryFaceList(QueryFaceReq queryFaceReq);

    /**
     * 查询详情页
     *
     * @param token
     * @return
     */
    String queryDetailByToken(String token);

    /**
     * 提交审核结果
     *
     * @param faceAuditLogDto
     */
    void commitAuthRes(FaceAuditLogDto faceAuditLogDto);
}
