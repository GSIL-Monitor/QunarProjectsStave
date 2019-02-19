package com.qunar.fintech.plat.admin.contract.dao.mapper;

import com.qunar.fintech.plat.admin.contract.dao.entity.PlatContract;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Author: zhengyang.zhong
 * @Date: 2018/9/20
 * @Despcription: 平台合同Dao
 */
@Repository
public interface PlatContractDao {

    /**
     * 根据身份证查询平台合同
     * @param identityCode
     * @return
     */
    PlatContract queryByIdentityCode(@Param("identityCode") String identityCode);

    /**
     * 根据手机号查询平台合同
     * @param mobile 手机号(非空)
     * @param productNo 产品编码(可以为空)
     * @return
     */
    List<PlatContract> queryByMobile(@Param("mobile") String mobile, @Param("productNo") String productNo);

    PlatContract queryByCusIdAndPrd(@Param("customId") String customId, @Param("productNo") String productNo);

}
