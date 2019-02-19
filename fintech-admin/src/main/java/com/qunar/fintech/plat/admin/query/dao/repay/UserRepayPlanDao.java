package com.qunar.fintech.plat.admin.query.dao.repay;


import com.qunar.fintech.plat.admin.query.entity.UserRepayPlan;
import com.qunar.fintech.plat.admin.query.vo.OverDueWithholdRecord;
import com.qunar.fintech.plat.admin.query.vo.OverDueWithholdReq;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import com.qunar.fintech.plat.admin.query.vo.QueryLockOrderReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 查询用户还款计划Dao接口
 *
 * Created by shuaifeng.gao on 16-9-20.
 */
@Repository
public interface UserRepayPlanDao {

    /**
     * 按借据查询还款计划
     * @param loanProvideNo
     * @return
     */
    List<UserRepayPlan> selectByLoanProvideNo(@Param("loanProvideNo") String loanProvideNo);

    UserRepayPlan selectByLoanProvideNoDueDate(@Param("loanProvideNo") String loanProvideNo, @Param("dueDate") Date dueDate);

    /**
     * 统计满足查询条件还款计划数量
     */
    int countUserRepayPlanByReqVo(@Param("request") QueryDto reqVo, @Param("orgChannelList") List<String> orgChannelList, @Param("mainUserIdList") List<String> mainUserIdList);

    List<UserRepayPlan> selectRepayPlanForUrge(@Param("request") QueryDto reqVo, @Param("page") Page page,
                                               @Param("orgChannelList") List<String> orgChannelList, @Param("mainUserIdList") List<String> mainUserIdList);


	List<OverDueWithholdRecord> queryUserMaxOverDueUserList(OverDueWithholdReq query);

	List<OverDueWithholdRecord> queryUserScheduleDetailByUserIds(OverDueWithholdReq query, List<String> remainUserIds);

	BigDecimal queryUserCurrentBalance(String userId, String tppCode);

	int queryUserMaxOverDueUserListCount(OverDueWithholdReq query);

    public List<UserRepayPlan> queryOverDueDetailByUIdAndTppCode(OverDueWithholdReq overDueReq);

    /**
     * 根据贷款单号userId或锁定时间查询
     * @param req
     * @return
     */
    List<UserRepayPlan> queryLockPlanByLoanProvideNoOrTime(QueryLockOrderReq req);
}
