package com.qunar.fintech.plat.admin.query.service.impl;

import com.google.common.collect.Lists;
import com.qunar.fintech.plat.admin.query.entity.TblVirtualContract;
import com.qunar.fintech.plat.admin.query.enums.DataTypeEnum;
import com.qunar.fintech.plat.admin.query.service.IousQueryService;
import com.qunar.fintech.plat.admin.query.service.SecureStorageService;
import com.qunar.fintech.plat.admin.query.service.VirtualContractService;
import com.qunar.pay.g2.utils.common.StringUtils;
import com.qunar.tc.core.info.api.KeyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by baron.jiang on 2015/2/4.
 */
@Service
public class IousQueryServiceImpl implements IousQueryService {
    private static final Logger logger = LoggerFactory.getLogger(IousQueryServiceImpl.class);


    @Override
    public List<String> queryUserIdByMobile(String mobile, String productNo) {
            if (StringUtils.isBlank(mobile)) {
               return Lists.newArrayList();
            }

            List<String> userIdList = Lists.newArrayList();
            String mobileByTc = mobile;
            if (!KeyType.phone.encrypted(mobile)) {
                mobileByTc= secureStorageService.enCryptData(mobile, DataTypeEnum.MOBILE_TYPE);
            }
            List<TblVirtualContract> contractList = virtualContractService.queryByMobileProductNo(mobileByTc, productNo);
            if (org.apache.commons.collections.CollectionUtils.isEmpty(contractList)) {
                logger.info("填写的手机号没有对应的虚拟合同信息,mobile={},mobileByTc={}",mobile,mobileByTc);
                return Lists.newArrayList();
            }

            for (TblVirtualContract contract : contractList) {
                if (!userIdList.contains(contract.getUserId())) {
                    userIdList.add(contract.getUserId());
                }
            }
            return userIdList;
    }

    @Resource
    private SecureStorageService secureStorageService;

    @Resource
    private VirtualContractService virtualContractService;
}
