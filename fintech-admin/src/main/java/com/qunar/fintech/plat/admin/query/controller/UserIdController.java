package com.qunar.fintech.plat.admin.query.controller;

import com.qunar.fintech.plat.admin.query.service.QueryIdsService;
import com.qunar.fintech.plat.admin.query.vo.QueryIdDto;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with Intellij IDEA.
 * Description:
 * User: weiduan
 * Date: 2018-02-09
 * Time: 下午2:43
 */
@RequestMapping("/finance/queryId")
@Controller
public class UserIdController {

    @GetMapping()
    String list() {
        return "/query/queryId";
    }

    @RequiresPermissions("finance:query")
    @ResponseBody
    @GetMapping("/platOpenId")
    QueryIdDto queryId(String mobile) {
        logger.info("queryId param mobile is : {}",mobile);
        ParamChecker.notBlank(mobile,"mobile cannot be blank");
        return queryIdsService.queryIdsByMobile(mobile);
    }

    @Autowired
    private QueryIdsService queryIdsService;

    private static final Logger logger = LoggerFactory.getLogger(UserIdController.class);
}
