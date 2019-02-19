package com.qunar.fintech.plat.admin.bruce.service.impl;

import com.qunar.fintech.plat.admin.bruce.entity.RepayDateDto;
import com.qunar.fintech.plat.admin.bruce.enums.StatisticTypeEnum;

import com.qunar.fintech.plat.admin.bruce.mapper.BruceStatisticMapper;
import com.qunar.fintech.plat.admin.bruce.entity.StatisticDataDto;

import com.qunar.fintech.plat.admin.bruce.service.QueryStatisticDataService;
import com.google.common.collect.Lists;
import com.qunar.fintech.plat.admin.bruce.vo.QueryStatisticDataVo;
import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import com.qunar.fintech.util.simple.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: xian.cheng
 * Date: 2018-12-26
 * Time: 23:31
 */
@Service
public  class QueryStatisticDataServiceImpl implements QueryStatisticDataService {
    /**
     *
     * @param orgChannel 渠道编码  eg: QUNAR  CTRIP
     * @param tppCode 通道编码  eg: JIMULOAN、 SCLOAN  等等
     * @param start 开始时间
     * @param end 结束时间
     *              注意：开始时间和结束时间不能相差 2 个月
     * @return
     */
    @Override
    public List<StatisticDataDto> queryStatisticData(String orgChannel, String tppCode, Date start, Date end){
        if(DateUtils.firstBeforeSecond(DateUtils.addMonth(start, 2), end)) {
            end = DateUtils.addMonth(start, 2);
        }
        List<StatisticDataDto> records = Lists.newArrayList();
        try{
            Date startTime = start;
            //按日期循环取得数据，放入records中
            while(DateUtils.firstBeforeSecond(startTime, end)) {
                //日期
                Date endTime = DateUtils.addDay(startTime, 1);
                StatisticDataDto staticData =  queryData(orgChannel, tppCode, startTime, endTime);
                if (null != staticData) {
                    records.add(staticData);
                }
                //查询日期加一天
                startTime = endTime;
            }
        } catch (Exception e) {
            LOGGER.error("queryStatisticData#失败", e);
            return records;
        }
        return records;
    }

    /**
     * 导出Excel表格
     * @param queryStatisticDataVo 写入表中的数据
     * @param title 表单名
     * @param headers 表头
     */
    @Override
    public HSSFWorkbook queryStatisticExport(QueryStatisticDataVo queryStatisticDataVo, String title, String[] headers){
        ParamChecker.notNull(queryStatisticDataVo, "queryStatisticDataVo 不能为空!");
        Date start = queryStatisticDataVo.getStartTime();
        Date end = queryStatisticDataVo.getEndTime();
        if(DateUtils.firstBeforeSecond(DateUtils.addMonth(start, 2), end)) {
            end = DateUtils.addMonth(start, 2);
        }
        //实例化Excel表格
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表单
        HSSFSheet sheet = workbook.createSheet(title);
        sheet.setDefaultColumnWidth((short) 15);
        // 生成表头样式和字体
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.RED.index);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        // 生成并设置另一个样式和字体
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont font2 = workbook.createFont();
        font2.setColor(HSSFColor.BLACK.index);
        style2.setFont(font2);
        // 产生表格标题行
        Integer rowNum = 0;
        HSSFRow row = sheet.createRow(rowNum);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //查询数据，写入到表单中
        try {
            Date startTime = start;
            //按日期循环取得数据
            while(DateUtils.firstBeforeSecond(startTime, end)) {
                Date endTime = DateUtils.addDay(startTime, 1);
                StatisticDataDto staticData =  queryData(queryStatisticDataVo.getOrgChannel(),
                        queryStatisticDataVo.getTppCode(), startTime, endTime);
                if (null != staticData) {
                    rowNum++;
                    row = sheet.createRow(rowNum);
                    // 利用反射，根据javabean属性的先后顺序，得到属性值
                    Field[] fields = staticData.getClass().getDeclaredFields();
                    for (short i = 0; i < fields.length; i++) {
                        HSSFCell cell = row.createCell(i);
                        cell.setCellStyle(style2);
                        Field field = fields[i];
                        String fieldName = field.getName();
                        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Class tCls = staticData.getClass();
                        Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
                        Object value = getMethod.invoke(staticData, new Object[] {});
                        // 判断值的类型后进行强制类型转换并写入
                        dealString(value, cell, workbook);
                    }
                }
                startTime = endTime;
            }
        } catch (Exception e) {
            LOGGER.error("导出Excel表格#失败", e);
            return workbook;
        }
        return workbook;
    }

    /**
     * 对导入到Excel的数据进行处理
     * @param value 值
     * @param cell 单元格
     * @param workbook 表单
     */
    private void dealString(Object value, HSSFCell cell, HSSFWorkbook workbook){
        String textValue = null;
        if (value instanceof Date) {
            Date date = (Date) value;
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            textValue = sdf.format(date);
        }
        else {
            // 其它数据类型都当作字符串简单处理
            textValue = value == null? "" : value.toString();
        }
        // 利用正则表达式判断textValue是否全部由数字组成
        if (textValue != null) {
            Matcher matcher = patternMatch.matcher(textValue);
            if (matcher.matches()) {
                // 是数字当作double处理
                cell.setCellValue(Double.parseDouble(textValue));
            }
            else {
                HSSFRichTextString richString = new HSSFRichTextString(textValue);
                HSSFFont font3 = workbook.createFont();
                font3.setColor(HSSFColor.BLACK.index);
                richString.applyFont(font3);
                cell.setCellValue(richString);
            }
        }
    }

    /**
     * 按条件并按天查询数据
     * @param orgChannel 渠道编码  eg: QUNAR  CTRIP
     * @param tppCode   通道编码  eg: JIMULOAN、 SCLOAN  等等
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return StatisticDataDto
     */
    private StatisticDataDto queryData(String orgChannel, String tppCode, Date startTime, Date endTime){
        //逾期
        BigDecimal overdue90Amt = bruceStatisticMapper.selectOverdue90Amt(tppCode, orgChannel, startTime);
        BigDecimal overdue60Amt = bruceStatisticMapper.selectOverdue60Amt(tppCode, orgChannel, startTime);
        //在数据库bruce的statistic_loan_info表中贷款金额，在贷余额，按每天统计总量
        StatisticTypeEnum[] statisticTypeEnum = StatisticTypeEnum.values() ;
        BigDecimal balanceAmt = bruceStatisticMapper.selectStatisticLoanAmt(statisticTypeEnum[0].toString(),
                tppCode, orgChannel, startTime);
        BigDecimal loanAmt = bruceStatisticMapper.selectStatisticLoanAmt(statisticTypeEnum[2].toString(),
                tppCode, orgChannel, startTime);
        //查询还款数据汇总
        RepayDateDto repayDateDto = bruceStatisticMapper.selectRepayDate(tppCode, orgChannel, startTime, endTime);

        SimpleDateFormat date = new SimpleDateFormat(DATE_FORMAT);
        StatisticDataDto res = new StatisticDataDto();
        res.setTppCode(tppCode);
        res.setStaticTime(date.format(startTime));
        res.setOverdue90Amt(overdue90Amt);
        res.setOverdue60Amt(overdue60Amt);
        res.setLoanAmt(balanceAmt);
        res.setLoanBalanceAmt(loanAmt);
        if(null != repayDateDto){
            BigDecimal repayAmt = repayDateDto.getActPrcpAmt().add(repayDateDto.getActSpreadsAmt());
            repayAmt = repayAmt.add(repayDateDto.getActIntAmt());
            repayAmt = repayAmt.add(repayDateDto.getActFineAmt());
            repayAmt = repayAmt.add(repayDateDto.getActFeeAmt());
            res.setTotalRepayAmt(repayAmt);
            res.setActPrcpAmt(repayDateDto.getActPrcpAmt());
            res.setActSpreadsAmt(repayDateDto.getActSpreadsAmt());
            res.setActIntAmt(repayDateDto.getActIntAmt());
            res.setActFineAmt(repayDateDto.getActFineAmt());
            res.setActFeeAmt(repayDateDto.getActFeeAmt());
        }
        if (res.ifNotEmpty()) {
            return res;
        }
        return null ;
    }

    private static final Pattern patternMatch = Pattern.compile("^[0-9]+\\.?[0-9]+?$");

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    @Autowired
    private BruceStatisticMapper bruceStatisticMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryStatisticDataServiceImpl.class);
}
