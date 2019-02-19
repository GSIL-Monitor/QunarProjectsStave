package com.qunar.fintech.plat.admin.query.dao.repay;

import com.qunar.fintech.plat.admin.query.entity.UserRepayWithhold;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.UserRepayVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dw.wang
 * @since 2016-03-09.
 */
@Repository
public interface UserRepayWithholdDao {
    /**
     *  统计满足查询条件的记录数
     */
    int countByReqDto(@Param("request") UserRepayVo reqVo);

    /**
     *  返回满足查询条件的记录
     */
    List<UserRepayWithhold> queryUserRepayWithholdList(@Param("request") UserRepayVo reqVo, @Param("page") Page page);

    /**
     * 根据流水号查询用户的还款强扣记录表
     * @param userWithholdNo
     * @return
     */
	List<UserRepayWithhold> queryUserRepayWithholdListBySerialNo(@Param("userWithholdNo") String userWithholdNo);
}
