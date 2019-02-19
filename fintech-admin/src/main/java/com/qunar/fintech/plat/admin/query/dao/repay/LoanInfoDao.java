package com.qunar.fintech.plat.admin.query.dao.repay;


import com.qunar.fintech.plat.admin.query.entity.TblLoanInfo;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by baron.jiang on 2015/2/4.
 */
@Repository
public interface LoanInfoDao  {

    /**
     * 根据条件查询信任付支付订单表信息
     *
     * @param request
     * @return
     */
    List<TblLoanInfo> queryLoanInfoByRequest(@Param("request") QueryDto request, @Param("page") Page page);

    /**
     * 统计符合request信任付支付订单信息数目
     * @param request
     * @return
     */
    int countLoanInfoByRequest(@Param("request") QueryDto request);


    /**
     * 根据贷款流水查询贷款信息
     * @param loanProvideNo
     * @return
     */
    TblLoanInfo queryLoanInfoByLoanProvideNo(@Param("loanProvideNo") String loanProvideNo);

    /**
     * 根据业务订单号查询贷款信息
     * @param busiOrderNo
     * @return
     */
    List<TblLoanInfo> queryLoanInfoByBusiOrderNo(@Param("busiOrderNo") String busiOrderNo);
}
