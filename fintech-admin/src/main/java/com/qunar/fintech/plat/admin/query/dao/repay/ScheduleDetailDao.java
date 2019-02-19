package com.qunar.fintech.plat.admin.query.dao.repay;

import com.qunar.fintech.plat.admin.query.entity.TblLoanInfo;
import com.qunar.fintech.plat.admin.query.entity.TblScheduleDetail;
import com.qunar.fintech.plat.admin.query.vo.ExportOverDueOrderResp;
import com.qunar.fintech.plat.admin.query.vo.OverDueOrderAuditRecord;
import com.qunar.fintech.plat.admin.query.vo.OverDueOrderAuditReq;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryOverDueOrderDetailRecord;
import com.qunar.fintech.plat.admin.query.vo.QueryOverDueOrderRecord;
import com.qunar.fintech.plat.admin.query.vo.QueryOverDueOrderReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * Created by baron.jiang on 2015/2/5.
 */
@Repository
public interface ScheduleDetailDao {

    /**
     * 查询利率
     */
    List<TblScheduleDetail> queryIntRateByRequest(List<TblLoanInfo> loanInfos);

    /**
     * 查询逾期订单
     */
    List<QueryOverDueOrderRecord> queryOverDueOrder(QueryOverDueOrderReq query, Page page);

    /**
     * 导出逾期订单信息
     */
    List<ExportOverDueOrderResp> exportOverDueOrderInfo(QueryOverDueOrderReq query, Page page);

    /**
     * 查询逾期订单总数
     */
    int queryOverDueOrderCount(QueryOverDueOrderReq req);

    /**
     * 查询逾期订单详情
     */
    List<QueryOverDueOrderDetailRecord> queryOverDueOrderDetail(String userId, String tppCode, String loanProvideNo);

    /**
     * 查询订单各期逾期天数
     */
    List<Date> queryOverDueOrderDueDays(String userId, String tppCode, String loanProvideNo);

    /**
     * 查询逾期订单审核记录
     */
    @Deprecated
    List<OverDueOrderAuditRecord> queryOverDueOrderAudit(OverDueOrderAuditReq req, Page page);

    /**
     * 查询逾期订单审核记录数
     */
    @Deprecated
    int queryOverDueOrderAuditCount(OverDueOrderAuditReq req);

    /**
     * 根据贷款流水号查询还款计划
     */
    List<TblScheduleDetail> queryDetailByLoanProvideNo(@Param("loanProvideNo") String loanProvideNo);


    /**
     * 根据uid，查询当前逾期订单数量
     *
     * @param userId 用户id
     * @param productNo 产品码
     * @return result
     */
    int queryCurrentOverdueCountByUserIdAndProductNo(@Param("userId") String userId, @Param("productNo") String productNo, @Param("tppCode") String tppCode);

    /**
     * 根据uid，查询当前逾期金额
     *
     * @param userId 用户id
     * @param productNo 产品码
     * @return result
     */
    BigDecimal queryCurrentOverdueAmountByUserIdAndProductNo(@Param("userId") String userId, @Param("productNo") String productNo, @Param("tppCode") String tppCode);


    /**
     * 根据uid，查询欠款总金额
     *
     * @param userId 用户id
     * @param productNo 产品码
     * @return result
     */
    BigDecimal queryDebtAmountAllByUserIdAndProductNo(@Param("userId")String userId, @Param("productNo")String productNo, @Param("tppCode") String tppCode);

    /**
     * 根据uid，查询欠款笔数
     *
     * @param userId 用户id
     * @param productNo 产品码
     * @return result
     */
    int queryDebtCountByUserIdAndProductNo(@Param("userId")String userId, @Param("productNo")String productNo, @Param("tppCode") String tppCode);


    /**
     * 根据uid，tppCode查询通道欠款金额
     *
     * @param userId 用户id
     * @param productNo 产品码
     * @return result
     */
    BigDecimal queryDebtAmountByUserIdAndTpp(@Param("userId")String userId, @Param("productNo")String productNo, @Param("tppCode") String tppCode);

    /**
     * 查询当前余额
     * @param userId
     * @param tppCode
     * @return
     */
	public BigDecimal queryCurrentBalance(String userId, String tppCode);
    /**
     * 根据借据号，查询欠款总金额
     * @return result
     */
    BigDecimal queryDebtAmountAllByLoanProviderNo(@Param("loanProvideNo") String loanProvideNo);

    int queryCurrentOverdueCountByProvideNo(@Param("loanProvideNo") String loanProvideNo);

	BigDecimal queryCurrentOverdueAmountByProvideNo(@Param("loanProvideNo") String loanProvideNo);

}
