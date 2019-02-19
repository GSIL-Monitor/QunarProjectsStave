package com.qunar.fintech.plat.admin.system.dao.mapper;

import com.qunar.fintech.plat.admin.system.dao.entity.ReviewFlowConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qun.shi
 * @since 2019-02-11 3:53 PM
 */
@Repository
public interface ReviewFlowConfigMapper {

    /**
     * 根据菜单id查审核流配置
     */
    List<ReviewFlowConfig> selectByReviewMenuId(@Param("reviewMenuId") String reviewMenuId);
}
