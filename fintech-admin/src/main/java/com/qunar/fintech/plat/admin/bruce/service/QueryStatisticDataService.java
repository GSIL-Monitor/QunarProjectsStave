package com.qunar.fintech.plat.admin.bruce.service;

import com.qunar.fintech.plat.admin.bruce.entity.StatisticDataDto;
import com.qunar.fintech.plat.admin.bruce.vo.QueryStatisticDataVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: xian.cheng
 * Date: 2018-12-26
 * Time: 23:29
 */
public interface QueryStatisticDataService {
  /**
   * 查询统计数据
   * @param orgChannel 渠道编码  eg: QUNAR  CTRIP
   * @param tppCode 通道编码  eg: JIMULOAN、 SCLOAN  等等
   * @param start 开始时间
   * @param end   结束时间
   *              注意：开始时间和结束时间不能相差 1 年
   * @return
   */
    List<StatisticDataDto> queryStatisticData(String orgChannel,
                                              String tppCode,
                                              Date start,
                                              Date end);
  /**
   * 导出Excel表格
   * @param queryStatisticDataVo 写入表中的数据
   * @param title 表单名
   * @param headers 表头
   */
  HSSFWorkbook queryStatisticExport(QueryStatisticDataVo queryStatisticDataVo, String title, String[] headers);
}
