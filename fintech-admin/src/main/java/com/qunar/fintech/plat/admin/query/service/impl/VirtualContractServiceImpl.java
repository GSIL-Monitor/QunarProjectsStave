package com.qunar.fintech.plat.admin.query.service.impl;

import com.qunar.fintech.plat.admin.query.dao.preloan.TblVirtualContractDao;
import com.qunar.fintech.plat.admin.query.entity.TblVirtualContract;
import com.qunar.fintech.plat.admin.query.service.VirtualContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dw.wang
 * @since 2017-03-16.
 */
@Service
public class VirtualContractServiceImpl implements VirtualContractService {
    private static final Logger logger = LoggerFactory.getLogger(VirtualContractServiceImpl.class);

    @Resource
    private TblVirtualContractDao tblVirtualContractDao;

    @Override
    public List<TblVirtualContract> queryByMobileProductNo(String mobile, String productNo) {
        return tblVirtualContractDao.queryByMobileProductNo(mobile, productNo);
    }

    /**
     * 根据用户UserId和产品码查询虚拟合同
     */
    @Override
    public TblVirtualContract queryByUserIdProductNo(String userId, String productNo) {
        return tblVirtualContractDao.queryByUserIdProductNo(userId, productNo);
    }
}
