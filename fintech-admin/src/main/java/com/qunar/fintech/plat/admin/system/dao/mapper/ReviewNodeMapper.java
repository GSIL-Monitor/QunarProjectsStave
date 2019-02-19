package com.qunar.fintech.plat.admin.system.dao.mapper;

import com.qunar.fintech.plat.admin.system.dao.entity.ReviewInfo;
import com.qunar.fintech.plat.admin.system.dao.entity.ReviewNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qun.shi
 * @since 2019-02-09 5:43 PM
 */
@Repository
public interface ReviewNodeMapper {

    /**
     * 按审核人和状态查询
     */
    List<ReviewNode> selectByReviewerAndStatus(@Param("reviewer")String reviewer,
                                               @Param("statusList") List<String> statusList);

    /**
     * 插入一条待审核的信息
     */
    int insert(@Param("record") ReviewNode reviewNode);

    /**
     * 查询当前审核流水的批复详情
     */
    List<ReviewNode> selectByReviewNo(@Param("reviewNos") List<String> reviewNos);

    /**
     * 按照reviewNo和流节点顺序查询
     */
    ReviewNode selectByReviewNoAndOrder(@Param("reviewNo") String reviewNo, @Param("order") Integer order);


    /**
     * 修改节点状态
     */
    int updateStatus(@Param("reviewNo") String reviewNo,
                     @Param("targetStatus") String targetStatus,
                     @Param("sourceStatus") String sourceStatus,
                     @Param("reviewOrder") Integer reviewOrder);

    /**
     * 根据状态更新节点信息
     */
    int updateInfoByStatusAndId(@Param("reviewer") String reviewer,
                                @Param("targetStatus") String targetStatus,
                                @Param("comment") String comment,
                                @Param("id") Long id,
                                @Param("sourceStatus") String sourceStatus);
}
