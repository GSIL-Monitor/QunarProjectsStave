package com.qunar.fintech.plat.admin.contract.builder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qunar.fintech.plat.admin.contract.dao.entity.UserProduct;
import com.qunar.fintech.plat.admin.contract.dao.entity.UserProductHistory;
import com.qunar.fintech.plat.admin.contract.dao.enums.BindStatusEnum;
import com.qunar.fintech.plat.admin.contract.dao.enums.ChangeSrcEnum;
import com.qunar.fintech.plat.admin.contract.dto.QueryUserProductChangeRecord;
import com.qunar.fintech.plat.admin.contract.dao.entity.PlatContract;
import com.qunar.pay.g2.utils.common.json.JSONUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

public class UserProductBuilder {

    public static List<QueryUserProductChangeRecord> buildUpRecords(List<UserProduct> ups, List<UserProductHistory> upHiss,
                                                              List<PlatContract> pcs) {
        List<QueryUserProductChangeRecord> upRecords = Lists.newArrayList();
        if (CollectionUtils.isEmpty(pcs) || (CollectionUtils.isEmpty(ups) && CollectionUtils.isEmpty(upHiss))) {
            return upRecords;
        }

        // 将平台合同列表转换为以productNo为key,合同作为value的map
        Map<String, PlatContract> pcMap = buildPlatContractMap(pcs);

        // 用户绑定
        for (UserProduct up : ups) {
            upRecords.add(createUpChangeRecordByUp(up, pcMap.get(up.getProductNo())));
        }

        for (UserProductHistory upHis : upHiss) {
            upRecords.add(createUpChangeRecordByUpHis(upHis, pcMap.get(upHis.getProductNo())));
        }

        return upRecords;
    }

    public static QueryUserProductChangeRecord createUpChangeRecordByUp(UserProduct up, PlatContract pc) {
        QueryUserProductChangeRecord upRecord = new QueryUserProductChangeRecord();
        upRecord.setCustomId(up.getCustomId());
        upRecord.setPlatId(up.getPlatId());
        upRecord.setProductNo(up.getProductNo());
        upRecord.setBindOrgChannel(up.getBindOrgChannel());
        upRecord.setActivateOrgChannel(up.getActivateOrgChannel());
        upRecord.setBindStatus(up.getBindStatus().getCode());
        upRecord.setBindSrc(up.getBindSrc());
        upRecord.setBindTime(up.getBindTime());
        if (BindStatusEnum.UNBIND.equals(up.getBindStatus())) {
            upRecord.setUnBindTime(up.getUpdateTime());
        }
        upRecord.setCreateTime(up.getCreateTime());
        upRecord.setUpdateTime(up.getUpdateTime());
        upRecord.setPaySwitch(pc.getPaySwitch());
        upRecord.setProcStatus(pc.getProcStatus());
        upRecord.setPlatStatus(pc.getPlatStatus());

        return upRecord;
    }

    public static QueryUserProductChangeRecord createUpChangeRecordByUpHis(UserProductHistory upHis, PlatContract pc) {
        QueryUserProductChangeRecord upRecord = new QueryUserProductChangeRecord();
        upRecord.setCustomId(upHis.getCustomId());
        upRecord.setPlatId(upHis.getPlatId());
        upRecord.setProductNo(upHis.getProductNo());
        upRecord.setBindOrgChannel(upHis.getBindOrgChannel());
        upRecord.setActivateOrgChannel(upHis.getActivateOrgChannel());
        upRecord.setCreateTime(upHis.getCreateTime());
        upRecord.setUpdateTime(upHis.getUpdateTime());
        upRecord.setPaySwitch(pc.getPaySwitch());
        upRecord.setProcStatus(pc.getProcStatus());
        upRecord.setPlatStatus(pc.getPlatStatus());
        UserProduct up = JSONUtils.jsonToBean(upHis.getUserProductInfo(), UserProduct.class);
        if (up != null) {
            upRecord.setBindSrc(up.getBindSrc());
            upRecord.setBindTime(up.getBindTime());
        }
        String changeSrc = upHis.getChangeSrc();
        if(ChangeSrcEnum.isUnbindChangeSrc(changeSrc)) {
            upRecord.setBindStatus(BindStatusEnum.UNBIND.getCode());
            upRecord.setUnBindTime(upHis.getCreateTime());
        }
        if (ChangeSrcEnum.isBindChangeSrc(changeSrc)) {
            upRecord.setBindStatus(BindStatusEnum.BIND.getCode());
            upRecord.setBindTime(upHis.getCreateTime());
        }

        return upRecord;
    }


    private static Map<String, PlatContract> buildPlatContractMap(List<PlatContract> pcs) {
        Map<String,PlatContract> pcMaps = Maps.newHashMap();
        for (PlatContract pc : pcs) {
            pcMaps.put(pc.getProductNo(), pc);
        }
        return pcMaps;
    }
}
