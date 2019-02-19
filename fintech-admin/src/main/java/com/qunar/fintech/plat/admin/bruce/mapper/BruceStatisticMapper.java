package com.qunar.fintech.plat.admin.bruce.mapper;
import com.qunar.fintech.plat.admin.bruce.entity.RepayDateDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: xian.cheng
 * Date: 2018-12-29
 * Time: 17:06
 */
@Repository
public interface BruceStatisticMapper {
    /**
     * 在贷余额，还款金额，贷款金额，按每天统计总量
     * @param statisticType 类型
     * @param staticTime 日期
     * @return 在贷余额，还款金额，贷款金额，按每天统计总量
     */
    BigDecimal selectStatisticLoanAmt(@Param("statisticType") String statisticType,
                                      @Param("tppCode")  String tppCode,
                                      @Param("orgChannel") String orgChannel,
                                      @Param("staticTime") Date staticTime);
    /**
     *  逾期90+应还金额总数
     */
    BigDecimal selectOverdue90Amt(@Param("tppCode")  String tppCode,
                                  @Param("orgChannel") String orgChannel,
                                  @Param("staticTime") Date staticTime);
    /**
     *  逾期60+应还金额总数
     */
    BigDecimal selectOverdue60Amt(@Param("tppCode")  String tppCode,
                                  @Param("orgChannel") String orgChannel,
                                  @Param("staticTime") Date staticTime);
    /**
     * 查询
     */
    RepayDateDto selectRepayDate(@Param("tppCode")  String tppCode,
                           @Param("orgChannel") String orgChannel,
                           @Param("startTime") Date startTime,
                           @Param("endTime") Date endTime);
}
