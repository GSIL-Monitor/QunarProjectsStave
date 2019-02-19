package com.qunar.fintech.plat.admin.newmarketing.service;

import com.qunar.fintech.plat.admin.system.dao.entity.ReviewFlowConfig;
import com.qunar.fintech.plat.admin.system.dao.entity.ReviewInfo;

import java.util.List;

/**
 * @author qun.shi
 * @since 2019-02-11 7:26 PM
 */
public interface FlowConfigService {

    /**
     * 获取可用的流配置
     */
    ReviewFlowConfig getCanUseFlowConfig(List<ReviewFlowConfig> reviewFlowConfigs, ReviewInfo reviewInfo);
}
