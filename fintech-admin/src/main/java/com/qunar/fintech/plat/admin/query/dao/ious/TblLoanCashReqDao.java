package com.qunar.fintech.plat.admin.query.dao.ious;


import com.qunar.fintech.plat.admin.query.entity.QueryCashReqRecord;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 借钱请求tbl_loan_cash_req表Dao接口
 *
 * Created by shuaifeng.gao on 16-9-20.
 */
@Repository
public interface TblLoanCashReqDao {

    /**
     * 根据request查询参数, 统计记录个数
     * @param request 查询参数
     * @return        统计符合查询条件的记录个数
     */
    int countCashReqByRequest(@Param("request") QueryDto request);

    /**
     * 分页查询借钱请求记录
     * @param request 查询参数
     * @return        分页查询结果
     */
    List<QueryCashReqRecord> pagingQueryCashReqRecordByRequest(@Param("request") QueryDto request, @Param("page")Page page);

    /**
     * 查询借钱请求记录
     *
     * @return
     */
    QueryCashReqRecord queryCashReqRecordByLoanProvideNo(@Param("list") List<String> list);
}
