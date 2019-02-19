package com.qunar.fintech.plat.admin.query.dao.ious;


import com.qunar.fintech.plat.admin.query.vo.CashRemit;
import com.qunar.fintech.plat.admin.query.vo.CashRemitRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xi.cheng on 2016/9/20.
 */
@Repository
public interface TblLoanCashRemitMapper {
    /**
     * 根据条件查询打款记录条数
     *
     * @param request
     * @return
     */
    List<CashRemit> queryCashRemitByRequest(CashRemitRequest request);
    /**
     * 根据条件查询打款记录条数
     *
     * @param request
     * @return
     */
    Integer countCashRemitByRequest(CashRemitRequest request);
}
