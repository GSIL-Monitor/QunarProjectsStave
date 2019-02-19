package com.qunar.fintech.plat.admin.query.dao.ious;

import com.qunar.fintech.plat.admin.query.entity.TblIousRefund;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import com.qunar.fintech.plat.admin.query.vo.QueryRefundRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by guoyue.sun on 2015/5/21.
 */
@Repository
public interface IousRefundDao {

    /**
     * 根据条件查询信任付退款记录
     * @param request
     * @return
     */
    List<QueryRefundRecord> queryIousRefundByRequest(@Param("request") QueryDto request, @Param("page") Page page);

    /**
     * 根据支付订单号查询退款记录
     * @param orderNo
     * @return
     */
    QueryRefundRecord queryIousRefundByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 统计符合request信任付退款记录数目
     * @param request
     * @return
     */
    Integer countIousRefundByRequest(@Param("request") QueryDto request);

    /**
     * 根据不同退款类型查询贷款金额
     * @param request
     * @return
     */
    public BigDecimal queryIousPayAmount(TblIousRefund request);

    /**
     * 查询退款流水
     * @param payId
     * @return
     */
    public List<TblIousRefund> queryIousRefundDetail(String payId);

    /**
     * 根据refundId查询退款记录
     * @param refundId
     * @return
     */
    TblIousRefund queryIousRefundByRefundId(String refundId);
}
