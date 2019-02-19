package com.qunar.fintech.plat.admin.query.controller;

import com.qunar.fintech.plat.admin.query.service.QueryCantUseReasonService;
import com.qunar.fintech.plat.admin.query.service.SecureStorageService;
import com.qunar.fintech.plat.admin.query.vo.CantUseReasonDto;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import com.qunar.pay.finance.qf.commons.utils.base.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: weiduan
 * Date: 2018-02-09
 */
@RequestMapping("/finance/cantUse")
@Controller
public class QueryReasonController {

    @GetMapping()
    String list() {
        return "/query/queryReason";
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @PostMapping("/reason")
    List<CantUseReasonDto> queryCantUseReason(@RequestBody QueryDto queryDto) {
        logger.info("queryCantUseReason param queryDto is {} : ",queryDto);
        checkParam(queryDto);
        List<CantUseReasonDto> response;
        return queryCantUseReasonService.queryCantUseReason(queryDto);
    }

    private void checkParam(QueryDto queryDto) {
        ParamChecker.notNull(queryDto,"queryDto cannot be null");
        ParamChecker.isTrue(StringUtils.isNotBlank(queryDto.getMobile()) || StringUtils.isNotBlank(queryDto.getUserId())
                                || StringUtils.isNotBlank(queryDto.getIdentity()),"uid,mobile,identity cannot be blank at the same!");
        ParamChecker.notBlank(queryDto.getOrgChannel(),"orgChannel cannot be blank!");
        ParamChecker.notBlank(queryDto.getProductNo(),"productNo cannot be blank!");
        ParamChecker.notNull(queryDto.getStartTime(),"startTime cannot be null!");
        ParamChecker.notNull(queryDto.getEndTime(),"endTime cannot be null!");
        ParamChecker.isTrue(DateUtil.isBeforeDate(queryDto.getEndTime(),queryDto.getStartTime()),"startTime cannot be later than endTime!");
    }

    @Resource
    private QueryCantUseReasonService queryCantUseReasonService;

    @Resource
    private SecureStorageService secureStorageService;

    private static final Logger logger = LoggerFactory.getLogger(QueryReasonController.class);
}
