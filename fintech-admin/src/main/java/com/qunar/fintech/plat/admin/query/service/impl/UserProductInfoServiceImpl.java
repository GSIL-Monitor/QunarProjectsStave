package com.qunar.fintech.plat.admin.query.service.impl;

import com.qunar.fintech.plat.admin.query.dao.preloan.UserProductInfoDao;
import com.qunar.fintech.plat.admin.query.entity.UserProductInfo;
import com.qunar.fintech.plat.admin.query.service.UserProductInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dw.wang
 * @since 2016-12-12
 */
@Service("userProductInfoService")
public class UserProductInfoServiceImpl implements UserProductInfoService {

    @Resource
    private UserProductInfoDao userProductInfoMapper;

    @Override
    public List<UserProductInfo> queryUserProductInfoByUserId(String identityCode, String productNo, String userId) {
        return userProductInfoMapper.queryUserProductInfoByUserId(identityCode, productNo, userId);
    }

    @Override
    public List<UserProductInfo> queryReqUserIdProductInfo(String mainUserId, Integer accType) {
        return userProductInfoMapper.queryReqUserIdProductInfo(mainUserId, accType);
    }
}
