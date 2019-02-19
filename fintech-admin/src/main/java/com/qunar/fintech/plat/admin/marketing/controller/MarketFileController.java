package com.qunar.fintech.plat.admin.marketing.controller;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.qunar.fintech.marketing.api.admin.facade.UserCouponFacade;
import com.qunar.fintech.marketing.api.admin.model.CouponPayDto;
import com.qunar.fintech.marketing.api.admin.model.CouponPayQueryDto;
import com.qunar.fintech.plat.admin.marketing.dto.ExportFileDto;
import com.qunar.fintech.plat.admin.support.security.AccessLog;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import com.qunar.pay.finance.utils.DateUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@RequestMapping("/marketing/file")
@Controller
public class MarketFileController {

    @GetMapping()
    String list() {
        return prefix + "/list";
    }

    @AccessLog("导出文件")
    @RequiresPermissions("market:file:export")
    @PostMapping("/export")
    void export(ExportFileDto exportFileDto, HttpServletResponse response) {
        logger.info("export file parameter is {}", exportFileDto);
        checkParam(exportFileDto);
        CouponPayQueryDto queryDto = new CouponPayQueryDto();
        queryDto.setType(exportFileDto.getType());
        queryDto.setStartTime(DateUtil.stringToDate(exportFileDto.getStartTime(), pattern));
        queryDto.setEndTime(DateUtil.stringToDate(exportFileDto.getEndTime(), pattern));
        queryDto.setChannel(exportFileDto.getChannel());
        queryDto.setActivityCode(exportFileDto.getActivityCode());
        List<CouponPayDto> payDtos = userCouponFacade.queryPayByTime(queryDto).getData();
        //导出txt文件
        exportTxt(payDtos, response, exportFileDto.getType());
    }

    private void checkParam(ExportFileDto exportFileDto){
        ParamChecker.notNull(exportFileDto,"exportFileDto不能为空");
        ParamChecker.notBlank(exportFileDto.getActivityCode(), "活动code不能为空");
        ParamChecker.notBlank(exportFileDto.getStartTime(), "startTime不能为空");
        ParamChecker.notBlank(exportFileDto.getEndTime(), "endTime不能为空");
        ParamChecker.notNull(exportFileDto.getType(), "type不能为空");
        ParamChecker.isTrue(exportFileDto.getStartTime().compareTo(exportFileDto.getEndTime())<0,"起始时间不能晚于结束时间");
    }

    /**
     * 导出文件
     */
    private void exportTxt(List<CouponPayDto> payDtos, HttpServletResponse response, String type) {

        response.setContentType("text/plain");
        String suffix = DateUtil.dateToString(new Date(), FILE_SUFFIX);
        String fileName = type + "-" + suffix;

        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("不支持的编码格式",e);
        }
        response.addHeader("Content-Disposition","attachment; filename=" + fileName + ".txt");
        BufferedOutputStream buff = null;
        StringBuilder write = new StringBuilder();
        String enter = System.getProperty("line.separator");
        ServletOutputStream os = null;
        //首行
        write.append(head).append(enter);
        try {
            os = response.getOutputStream();
            buff = new BufferedOutputStream(os);
            //写文件
            for(CouponPayDto payDto : payDtos){
                String s = "";
                if(type.equals("PAY") && payDto.getType()==0){
                    s = "核销";
                }
                if(type.equals("PAY") && payDto.getType()==1){
                    s = "使用";
                }
                if(type.equals("REFUND") && payDto.getType()==0){
                    s = "退款";
                }
                if(type.equals("REFUND") && payDto.getType()==1){
                    s = "撤销";
                }
                write.append(payDto.getUserId()).append(separator).
                        append(payDto.getCouponNo()).append(separator).
                        append(payDto.getOrgChannel()).append(separator).
                        append(payDto.getProductNo()).append(separator).
                        append(payDto.getQunarTradeNo()).append(separator).
                        append(payDto.getAmount()).append(separator).
                        append(payDto.getAccSeq()).append(separator).
                        append(DateUtil.dateToString(payDto.getOrderTime(), pattern)).append(separator).
                        append(s).append(enter);
            }
            buff.write(write.toString().getBytes("UTF-8"));
            buff.flush();
        } catch (Exception e) {
            logger.error("写入失败",e);
        } finally {
            try{
                if(buff != null){
                    buff.close();
                }
                if(os != null){
                    os.close();
                }
            } catch (Exception e) {
                logger.error("资源关闭失败",e);
            }
        }
    }

    @Autowired
    private UserCouponFacade userCouponFacade;

    private static final String prefix = "marketing/file";

    private static final String pattern = "yyyy-MM-dd HH:mm:ss";

    private static final String FILE_SUFFIX = "yyyyMMddHHmmssSSS";

    private static final String separator = "\t";

    private static final String head = Joiner.on(separator).join(Lists.newArrayList("userId","couponNo",
                                        "orgChannel","productNo", "qunarTradeNo" ,"amount" ,"accSeq" ,"orderTime",
                                        "type")).toString();

    private static final Logger logger = LoggerFactory.getLogger(MarketFileController.class);


}
