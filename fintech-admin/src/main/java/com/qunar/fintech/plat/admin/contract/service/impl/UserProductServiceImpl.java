package com.qunar.fintech.plat.admin.contract.service.impl;

import com.google.common.collect.Lists;
import com.qunar.fintech.plat.admin.contract.builder.UserProductBuilder;
import com.qunar.fintech.plat.admin.contract.dao.entity.PlatContract;
import com.qunar.fintech.plat.admin.contract.dao.entity.UserProduct;
import com.qunar.fintech.plat.admin.contract.dao.entity.UserProductHistory;
import com.qunar.fintech.plat.admin.contract.dao.mapper.PlatContractDao;
import com.qunar.fintech.plat.admin.contract.dao.mapper.UserProductHistoryDao;
import com.qunar.fintech.plat.admin.contract.dao.mapper.UserProductDao;
import com.qunar.fintech.plat.admin.contract.dto.QueryUserProductChangeRecord;
import com.qunar.fintech.plat.admin.contract.dto.QueryUserProductDto;
import com.qunar.fintech.plat.admin.contract.service.UserProductService;
import com.qunar.pay.finance.qf.commons.api.util.ParamChecker;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserProductServiceImpl implements UserProductService {

    @Override
    public List<QueryUserProductChangeRecord> queryUserProductChangeInfo(QueryUserProductDto reqDto) {
        LOGGER.info("queryUserProductChangeInfo - start - reqDto:{}", reqDto);
        ParamChecker.notNull(reqDto, "queryUserProductChangeInfo reqDto is null");
        ParamChecker.isTrue(StringUtils.isNotBlank(reqDto.getCustomId()) || StringUtils.isNotBlank(reqDto.getPlatId()),
                "customId and platId cannot be blank at the same time");

        // 用户绑定变更记录
        List<UserProductHistory> upHisList = userProductHistoryDao.queryUpHisList(reqDto.getCustomId(), reqDto.getPlatId(),
                reqDto.getProductNo(), reqDto.getOrgChannel());
        List<UserProduct> ups = userProductDao.queryByParam(reqDto.getCustomId(), reqDto.getPlatId(),
                reqDto.getProductNo(), reqDto.getOrgChannel());

        // 获取平台合同
        List<PlatContract> pcs = getPlatContractByUps(ups, upHisList);

        List<QueryUserProductChangeRecord> upRecords = UserProductBuilder.buildUpRecords(ups, upHisList, pcs);
        LOGGER.info("queryUserProductChangeInfo - end - upRecords:{}", upRecords);
        return upRecords;
    }

    private List<PlatContract> getPlatContractByUps(List<UserProduct> ups, List<UserProductHistory> upHiss) {
        List<PlatContract> pcs = Lists.newArrayList();
        if(CollectionUtils.isEmpty(ups) && CollectionUtils.isEmpty(upHiss)) {
            return pcs;
        }
        for (UserProduct up : ups) {
            PlatContract pc = platContractDao.queryByCusIdAndPrd(up.getCustomId(), up.getProductNo());
            if (pc != null) {
                pcs.add(pc);
            }
        }

        for (UserProductHistory upHis : upHiss) {
            PlatContract pc = platContractDao.queryByCusIdAndPrd(upHis.getCustomId(), upHis.getProductNo());
            if (pc != null) {
                pcs.add(pc);
            }
        }
        return pcs;
    }

    @Resource
    private UserProductDao userProductDao;
    @Resource
    private UserProductHistoryDao userProductHistoryDao;
    @Resource
    private PlatContractDao platContractDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProductServiceImpl.class);
}
