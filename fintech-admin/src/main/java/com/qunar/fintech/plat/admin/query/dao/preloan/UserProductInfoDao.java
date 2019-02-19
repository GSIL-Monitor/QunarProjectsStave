package com.qunar.fintech.plat.admin.query.dao.preloan;

import com.qunar.fintech.plat.admin.query.entity.UserProductInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dw.wang
 * @since 2016-12-12
 */
@Repository
public interface UserProductInfoDao {

    /**
     * 根据身份证号、产品编号和userId获取个人信息
     * @param identityCode
     * @param productNo
     * @param userId
     * @return
     */
    List<UserProductInfo> queryUserProductInfoByUserId(@Param("identityCode") String identityCode, @Param("productNo") String productNo, @Param("userId") String userId);

    List<UserProductInfo> queryReqUserIdProductInfo(@Param("mainUserId") String mainUserId, @Param("accType") Integer accType);
}
