package com.qunar.fintech.plat.admin.bruce.controller;

import com.qunar.fintech.plat.admin.bruce.util.CheckParamUtils;
import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.fintech.plat.admin.bruce.entity.StatisticDataDto;
import com.qunar.fintech.plat.admin.bruce.vo.QueryStatisticDataVo;
import com.qunar.pay.finance.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import com.qunar.fintech.plat.admin.bruce.service.QueryStatisticDataService;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA
 * Description:
 * User: xian.cheng
 * Date: 2018-12-25
 * Time: 16:56
 */
@Controller
@RequestMapping("/bruce/querySummaryAmount")
public class QueryStatisticController {

    @GetMapping()
    String queryStatistic(Model model) {
        return "bruce/queryStatistic/queryStatistic";
    }

    /**
     *数据查询
     */
    @RequiresPermissions("finance:query")
    @PostMapping(value = "/queryStatistic")
    @ResponseBody
    public QueryResponse<StatisticDataDto> queryStatistic(@RequestBody QueryStatisticDataVo queryStatisticDataVo){
        LOGGER.info("===queryStatistic param queryStatisticDataVo is {} ", queryStatisticDataVo);
        //判断传入是否为空
        ParamChecker.notNull(queryStatisticDataVo, "queryStatisticDataVo 不能为空!");
        QueryResponse<StatisticDataDto> resp = new QueryResponse<>();
        try {
            if (StringUtils.isNotEmpty(queryStatisticDataVo.getTppCode())
                    && queryStatisticDataVo.getEndTime() != null
                    && queryStatisticDataVo.getStartTime() != null) {
                Date start = queryStatisticDataVo.getStartTime();
                Date end = queryStatisticDataVo.getEndTime();
                if (start != null) {
                    start = new DateTime(start).withTime(0,0,0,0).toDate();
                }
                if (end != null) {
                    end = new DateTime(end).withTime(0,0,0,0).toDate();
                }
                List<StatisticDataDto> records = new ArrayList<>();
                records = queryStatisticDataService.queryStatisticData(queryStatisticDataVo.getOrgChannel(),
                        queryStatisticDataVo.getTppCode(), start, end);
                if (records != null && !records.isEmpty()) {
                    resp.setTotal(records.size()) ;
                    resp.setRows(records);
                }
            } else {
                LOGGER.error("queryStatistic# query param error ");
            }
        } catch (Exception e) {
            LOGGER.error("queryStatistic#失败", e);
            return resp;
        } LOGGER.info("StatisticDataDto resp={}", resp);
        return resp;
    }

    /**
     *导出
     */
    @RequiresPermissions("finance:query")
    @PostMapping(value = "/export")
    @ResponseBody
    public void  export(@RequestParam Map<String, Object> params, HttpServletResponse response) {
        LOGGER.info("===queryStatisticExport param  is {} ", params);
        OutputStream os = null;
        try {
            CheckParamUtils checkParam = new CheckParamUtils();
            QueryStatisticDataVo queryStatisticDataVo = checkParam.checkAndFill(params);
            //导出的标题列
            String[] headers = {"时间", "贷款提供方", "在贷余额", "贷款金额", "还款总额", "还款本金", "利息", "罚息",
                    "手续费", "息差", "逾期90+", "逾期60+"};
            //要保存的文件名
            String suffix = DateUtil.dateToString(new Date(), FILE_SUFFIX);
            String fileName = "汇总查询统计表" + "_" + suffix + ".xls";
            //创建工作表单
            String sheetNames = "汇总数据对账报表";
            //导出到Excel
            HSSFWorkbook workbook = queryStatisticDataService.queryStatisticExport(queryStatisticDataVo, sheetNames, headers);
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName,
                    "UTF-8"));
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            os = response.getOutputStream();
            workbook.write(os);
            os.flush();
       } catch (Exception e){
           LOGGER.error("queryStatisticeExport#失败", e);
       } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    LOGGER.error("close Stream has exp, ", e);
                }
            }
        }
    }


    @Autowired
    private QueryStatisticDataService queryStatisticDataService;

    private static final String FILE_SUFFIX = "yyyyMMddHHmmssSSS";

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryStatisticController.class);
}
