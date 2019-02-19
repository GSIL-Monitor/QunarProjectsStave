package com.qunar.fintech.plat.admin.contract.controller;

import com.qunar.fintech.plat.admin.contract.dto.QueryUserProductChangeRecord;
import com.qunar.fintech.plat.admin.contract.dto.QueryUserProductDto;
import com.qunar.fintech.plat.admin.contract.service.UserProductService;
import com.qunar.fintech.plat.admin.contract.util.UserProductParamCheck;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
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
 * Description:
 * User: rengang.pei
 * Date: 2018-11-03
 * Time: 22:45
 */
@RequestMapping("/finance/contract")
@Controller
public class UserProductController {

    /**
     * 查询用户解绑或绑定记录
     * @return
     */
    @RequiresPermissions("finance:query")
    @GetMapping("/gotoQueryUp")
    String gotoQueryUp() {
        return "/contract/queryUserProduct";
    }

    @RequiresPermissions("finance:query")
    @PostMapping(value = "/queryUserProduct")
    @ResponseBody
    public QueryResponse<QueryUserProductChangeRecord> queryUserProduct(@RequestBody QueryUserProductDto reqDto) {
        LOGGER.info("queryUserProduct - start - reqDto:{}", reqDto);
        // 校验参数
        UserProductParamCheck.checkQueryUpParam(reqDto);
        QueryResponse<QueryUserProductChangeRecord> response = new QueryResponse<>();
        try {
            List<QueryUserProductChangeRecord> upChangeInfos = userProductService.queryUserProductChangeInfo(reqDto);
            response.setRows(upChangeInfos);
            response.setTotal(upChangeInfos.size());
        } catch (BusiException busiEx) {
            LOGGER.error("queryUserProduct - error - busiEx", busiEx);
        } catch (Exception e) {
            LOGGER.error("queryUserProduct - error - ex", e);
        }
        return response;
    }

    @Resource
    private UserProductService userProductService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProductController.class);
}
