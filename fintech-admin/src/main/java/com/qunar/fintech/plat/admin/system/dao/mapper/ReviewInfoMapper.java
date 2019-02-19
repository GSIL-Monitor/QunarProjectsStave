package com.qunar.fintech.plat.admin.system.dao.mapper;

import com.qunar.fintech.plat.admin.system.dao.entity.ReviewInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewInfoMapper {

    /**
     * 按照提交查询记录
     */
    List<ReviewInfo> selectBySubmitter(@Param("submitter") String submitter);

    /**
     * 按照当前审核角色和状态查询
     */
    List<ReviewInfo> selectByCurRoleIdAndStatus(@Param("roleIds")List<Long> roleIds,@Param("status") String status);

    /**
     * 插入一条待审核的信息
     */
    int insert(@Param("record") ReviewInfo reviewInfo);

    int updateStatus(@Param("reviewNo") String reviewNo,
                     @Param("targetStatus") String targetStatus,
                     @Param("sourceStatus") String sourceStatus);

    int updateContentByStatus(@Param("reviewNo") String reviewNo,
                              @Param("content") String content,
                              @Param("sourceStatus") String sourceStatus);

    int updateStatusAndCurRoleIdAndSubmitter(@Param("reviewNo") String reviewNo,
                                 @Param("curRoleId") String curRoleId,
                                 @Param("targetStatus") String targetStatus,
                                 @Param("submitter") String submitter,
                                 @Param("sourceStatus") String sourceStatus);

    /**
     * 查询所有的审核信息
     */
    List<ReviewInfo> selectAll();

    List<ReviewInfo> selectByReviewNo(@Param("reviewNos") List<String> reviewNos);

    /**
     * 查询审核信息
     */
    List<ReviewInfo> queryReviewInfoByKey(@Param("contentKey") String contentKey);

    /**
     * 记录审核走到哪个节点
     */
    int updateCurRoleId(@Param("reviewNo") String reviewNo, @Param("curRoleId") String curRoleId);
}
