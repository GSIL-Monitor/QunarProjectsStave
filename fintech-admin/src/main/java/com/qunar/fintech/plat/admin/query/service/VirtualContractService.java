package com.qunar.fintech.plat.admin.query.service;

import com.qunar.fintech.plat.admin.query.entity.TblVirtualContract;

import java.util.List;

/**
 * @author dw.wang
 * @since 2017-03-16.
 */
public interface VirtualContractService {

    List<TblVirtualContract> queryByMobileProductNo(String mobile, String productNo);

    /**
     * 根据用户UserId和产品码查询虚拟合同
     */
    TblVirtualContract queryByUserIdProductNo(String userId, String productNo);
}
