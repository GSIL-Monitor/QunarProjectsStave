package com.qunar.fintech.plat.admin.contract.service.impl;

import com.google.common.collect.Lists;
import com.qunar.fintech.plat.admin.base.BaseAdminDBTest;
import com.qunar.fintech.plat.admin.system.builder.ContractChangeReqBuilder;
import com.qunar.fintech.plat.admin.system.constant.SerialNoConstant;
import com.qunar.fintech.plat.admin.system.dao.entity.ContractChangeReq;
import com.qunar.fintech.plat.admin.system.dao.enums.ChangeBusiTypeEnum;
import com.qunar.fintech.plat.admin.system.dao.enums.ProcStatusEnum;
import com.qunar.fintech.plat.admin.system.service.ContractChangeReqService;
import com.qunar.fintech.toolclient.serialno.SerialNoClient;
import com.qunar.pay.finance.qf.commons.api.enums.OrgChannelEnum;
import com.qunar.pay.finance.qf.commons.api.enums.ProductEnum;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-04
 * Time: 18:52
 */
public class ContractChangeReqServiceImplTest extends BaseAdminDBTest {

    @Test
    public void saveChangeReqTest() {

        ContractChangeReq signReq = contractChangeReqService.saveChangeReq(buildReq());

        ContractChangeReq dbSignReq = contractChangeReqService.querySignReq(signReq.getReqNo());

        ContractChangeReq upReq = ContractChangeReqBuilder.fillSignReq(dbSignReq, ProcStatusEnum.SUC);

        contractChangeReqService.updateReqByProcStatus(upReq, Lists.newArrayList(ProcStatusEnum.INIT), true);

    }

    private ContractChangeReq buildReq() {
        String customId = "1444630234";
        ContractChangeReq signReq = new ContractChangeReq();
        signReq.setReqNo(SerialNoClient.create(SerialNoConstant.CHANGE_SIGN_REQ));
        signReq.setCustomId(customId);
        signReq.setProductNo(ProductEnum.IOUS.getProductNo());
        signReq.setOrgChannel(OrgChannelEnum.QUNAR.getCode());
        signReq.setTppCode("JIMULOAN");
        signReq.setEmail("rengang.pei@qunar.com");
        signReq.setBusiType(ChangeBusiTypeEnum.PLAT_STATUS_CLOSE);
        signReq.setProcStatus(ProcStatusEnum.INIT);
        return signReq;
    }

    @Resource
    private ContractChangeReqService contractChangeReqService;
}
