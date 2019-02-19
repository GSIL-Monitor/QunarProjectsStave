package com.qunar.fintech.plat.admin.query.service;

import com.qunar.es.model.PrimaryKey;
import com.qunar.fintech.plat.admin.query.entity.UserRepayPlan;
import com.qunar.fintech.plat.admin.query.entity.UserRepayReq;
import com.qunar.fintech.plat.admin.query.entity.UserRepayWithhold;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryBillDetailRespDto;
import com.qunar.fintech.plat.admin.query.vo.QueryBillListRespDto;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.fintech.plat.admin.query.vo.UserRepayDetail;
import com.qunar.fintech.plat.admin.query.vo.UserRepayVo;

import java.util.Date;
import java.util.List;

/**
 * 还款服务类
 *
 * @author dw.wang
 * @since 2016-03-08.
 */
public interface RepayService {

    /**
     * 查询逾期的用户层还款计划
     */
    List<UserRepayPlan> queryRepayPlanForUrge(QueryDto reqVo, Page page, List<String> mainUserIdList);

    /**
     * 统计满足条件的还款计划数量
     */
    int countUserRepayPlanByReqVo(QueryDto reqVo, List<String> mainUserIdList);

    List<UserRepayPlan> selectByLoanProvideNo(String loanProvideNo);

    /**
     * 查询 user_repay_req
     */
    QueryResponse<UserRepayReq> queryUserRepayReq(QueryDto reqVo, Page page);

    /**
     * 查询 UserRepayDetail ,数据来自表user_repay_ext包含了loan_info表中的业务线流水号
     */
    List<UserRepayDetail> queryUserRepayDetail(String serialNo);

    /**
     * 查询账单列表
     */
    QueryResponse<QueryBillListRespDto> queryBillList(Page pageQuery, String userId, Integer billStatus, Date start, Date end);

    /**
     * 查询账单详情
     */
    List<QueryBillDetailRespDto> queryBillDetail(String billNo);

    UserRepayPlan selectByLoanProvideNoDueDate(String loanProvideNo, Date dueDate);

    List<PrimaryKey> queryPrimaryKey(String orderNo);

    /**
     * 查询 user_repay_withhold
     */
    QueryResponse<UserRepayWithhold> queryUserRepayWithhold(UserRepayVo reqVo, Page page);

    /**
     * 导出 user_repay_withhold
     */
    List<UserRepayWithhold> getUserRepayWithholdList(UserRepayVo reqVo, Page page);

}
