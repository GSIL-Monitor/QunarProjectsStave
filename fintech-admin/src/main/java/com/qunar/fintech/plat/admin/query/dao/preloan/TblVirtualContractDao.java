package com.qunar.fintech.plat.admin.query.dao.preloan;

import com.qunar.fintech.plat.admin.query.entity.TblVirtualContract;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cheng.she
 * @since 2016-02-24
 */
@Repository
public interface TblVirtualContractDao {

    /**
     * 根据用户UserId查询手机号
     * @param userIdList
     * @return
     */
    List<TblVirtualContract> queryMobileByUserId(@Param("userIdList") List<String> userIdList);

    List<TblVirtualContract> queryUserIdsByMobile(@Param("mobile") String mobile);


    List<TblVirtualContract> queryByMobileProductNo(@Param("mobile") String mobile, @Param("productNo") String productNo);

    /**
     * 根据用户UserId和产品码查询虚拟合同
     */
    TblVirtualContract queryByUserIdProductNo(@Param("userId") String userId, @Param("productNo") String productNo);

    /**
     *  根据userName mobile 中至少一个条件查询虚拟合同
     */
    List<TblVirtualContract> selectByUserNameMobile(@Param("userName") String userName, @Param("mobile") String mobile);

    /**
     *  根据 userId 查询虚拟合同
     */
    TblVirtualContract selectByUserId(@Param("userId") String userId, @Param("productNo") String productNo);
}
