package com.qunar.fintech.plat.admin.newmarketing.service.impl;

import com.qunar.fintech.plat.admin.newmarketing.dto.CouponDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.model.FlowConfigMatchRule;
import com.qunar.fintech.plat.admin.newmarketing.service.FlowConfigService;
import com.qunar.fintech.plat.admin.query.utils.JSONUtils;
import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import com.qunar.fintech.plat.admin.system.dao.entity.ReviewFlowConfig;
import com.qunar.fintech.plat.admin.system.dao.entity.ReviewInfo;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.utils.base.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.qunar.fintech.plat.admin.newmarketing.exception.MarketingExceptionCode.NO_MATCH_REVIEW_FLOW_CONFIG;

/**
 * @author qun.shi
 * @since 2019-02-11 7:26 PM
 */
@Service("marketingFlowConfigService")
public class MarketingFlowConfigServiceImpl implements FlowConfigService {

    /**
     * 营销获取可用的流配置
     */
    @Override
    public ReviewFlowConfig getCanUseFlowConfig(List<ReviewFlowConfig> reviewFlowConfigs, ReviewInfo reviewInfo) {
        ParamChecker.notNull(reviewFlowConfigs, "流配置不能为空！");
        ParamChecker.isTrue(reviewFlowConfigs.size() > 0, "流配置不能为空！");

        for (ReviewFlowConfig reviewFlowConfig : reviewFlowConfigs) {
            // 流匹配规则
            String machRuleStr = reviewFlowConfig.getMatchRule();
            if (StringUtils.isBlank(machRuleStr)) {
                return reviewFlowConfig;
            } else {
                // 解析流匹配规则
                FlowConfigMatchRule matchRule = JSONUtils.getGson()
                        .fromJson(reviewFlowConfig.getMatchRule(), FlowConfigMatchRule.class);

                // 审核发起的业务线
                CouponDetailDto couponDetailDto = JSONUtils.getGson()
                        .fromJson(reviewInfo.getContentValue(), CouponDetailDto.class);

                // 按照业务线匹配流配置
                if (StringUtils.isBlank(matchRule.getProductNo())) {
                    return reviewFlowConfig;
                } else {
                    if (matchRule.getProductNo().equals(couponDetailDto.getSupportProductNoList())) {
                        return reviewFlowConfig;
                    }
                }
            }
        }

        // 没有合适的流配置
        throw new BusiException(NO_MATCH_REVIEW_FLOW_CONFIG);
    }
}
