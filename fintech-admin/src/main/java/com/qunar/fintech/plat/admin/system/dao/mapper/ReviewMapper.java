package com.qunar.fintech.plat.admin.system.dao.mapper;

import com.qunar.fintech.plat.admin.system.dao.entity.ReviewDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: shiqun
 * Date: 2018-11-17
 * Time: 2:07 PM
 */
@Repository
public interface ReviewMapper {

    /**
     * 插入一条待审核的信息
     * @return
     */
    int insert(@Param("record") ReviewDO reviewDO);

    /**
     * 查询所有的审核记录
     * @return
     */
    List<ReviewDO> selectAll();

    /**
     * 查询某条审核信息
     * @param id
     * @return
     */
    ReviewDO queryReviewById(@Param("id") Long id);

    /**
     * 更新审核状态：通过或者失败
     * @param id
     * @param sourceStatus
     * @param targetStatus
     * @return
     */
    int updateReviewStatus(@Param("id") Long id,@Param("sourceStatus") Integer sourceStatus,
                           @Param("targetStatus") Integer targetStatus,@Param("reviewUser") String reviewUser);

}
