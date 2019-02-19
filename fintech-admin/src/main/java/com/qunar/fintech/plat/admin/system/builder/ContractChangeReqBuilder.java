package com.qunar.fintech.plat.admin.system.builder;

import com.google.common.collect.Lists;
import com.qunar.fintech.plat.admin.contract.dao.enums.PaySwitchEnum;
import com.qunar.fintech.plat.admin.contract.dao.enums.PlatStatusEnum;
import com.qunar.fintech.plat.admin.contract.dto.PaySwitchReqDto;
import com.qunar.fintech.plat.admin.contract.dto.PlatStatusReqDto;
import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import com.qunar.fintech.plat.admin.support.security.ShiroUtils;
import com.qunar.fintech.plat.admin.system.constant.SerialNoConstant;
import com.qunar.fintech.plat.admin.system.dao.entity.ContractChangeReq;
import com.qunar.fintech.plat.admin.system.dao.entity.UserDO;
import com.qunar.fintech.plat.admin.system.dao.enums.ChangeBusiTypeEnum;
import com.qunar.fintech.plat.admin.system.dao.enums.ProcStatusEnum;
import com.qunar.fintech.plat.admin.system.dto.QueryContractChangeRespDto;
import com.qunar.fintech.plat.admin.util.CutUtil;
import com.qunar.fintech.plat.admin.util.DateUtil;
import com.qunar.fintech.toolclient.serialno.SerialNoClient;
import com.qunar.pay.finance.qf.commons.utils.base.CollectionUtils;

import java.util.List;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-04
 * Time: 14:51
 */
public class ContractChangeReqBuilder {

    public static ContractChangeReq buildReq4PlatStatus(PlatStatusReqDto request) {
        // 获取用户信息
        UserDO userDo = ShiroUtils.getUser();
        ParamChecker.notNull(userDo, "userDo is null");
        ContractChangeReq signReq = new ContractChangeReq();
        signReq.setReqNo(SerialNoClient.create(SerialNoConstant.CHANGE_SIGN_REQ));
        signReq.setCustomId(request.getCustomId());
        signReq.setProductNo(request.getProductNo());
        signReq.setOrgChannel(request.getOrgChannel());
        signReq.setTppCode(request.getTppCode());
        signReq.setEmail(userDo.getEmail());
        if (PlatStatusEnum.isValid(request.getTarStatus())) {
            signReq.setBusiType(ChangeBusiTypeEnum.PLAT_STATUS_OPEN);
        } else if (PlatStatusEnum.isClose(request.getTarStatus())) {
            signReq.setBusiType(ChangeBusiTypeEnum.PLAT_STATUS_CLOSE);
        }
        signReq.setProcStatus(ProcStatusEnum.PROCESSING);
        return signReq;
    }

    public static ContractChangeReq buildReq4PaySwitch(PaySwitchReqDto request) {
        // 获取用户信息
        UserDO userDo = ShiroUtils.getUser();
        ParamChecker.notNull(userDo, "userDo is null");
        ContractChangeReq signReq = new ContractChangeReq();
        signReq.setReqNo(SerialNoClient.create(SerialNoConstant.CHANGE_SIGN_REQ));
        signReq.setCustomId(request.getCustomId());
        signReq.setProductNo(request.getProductNo());
        signReq.setOrgChannel(request.getOrgChannel());
        signReq.setTppCode(request.getTppCode());
        signReq.setEmail(userDo.getEmail());
        PaySwitchEnum paySwitch = PaySwitchEnum.toEnum(request.getTarStatus());
        if (paySwitch.isOpen()) {
            signReq.setBusiType(ChangeBusiTypeEnum.PAY_SWITCH_OPEN);
        } else if (paySwitch.isClose()) {
            signReq.setBusiType(ChangeBusiTypeEnum.PAY_SWITCH_CLOSE);
        }
        signReq.setProcStatus(ProcStatusEnum.PROCESSING);
        return signReq;
    }

    /**
     * 填充数据
     * @param req          待req对象
     * @param tarProcStatus 客户号id
     * @param errCode      错误码
     * @param errCode      错误信息
     * @return 待更新对象req
     */
    public static ContractChangeReq fillSignReq(ContractChangeReq req, ProcStatusEnum tarProcStatus,
                                                String errCode, String errMsg) {
        //数据库对象处理
        ContractChangeReq up = new ContractChangeReq();
        up.setId(req.getId());
        up.setReqNo(req.getReqNo());
        up.setProcStatus(tarProcStatus);
        up.setErrCode(CutUtil.cutCode(errCode));
        up.setErrMsg(CutUtil.cutMsg(errMsg));
        up.setFinishTime(DateUtil.getDBNow());
        return up;
    }

    public static ContractChangeReq fillSignReq(ContractChangeReq req, ProcStatusEnum tarProcStatus) {
        //数据库对象处理
        ContractChangeReq up = new ContractChangeReq();
        up.setId(req.getId());
        up.setReqNo(req.getReqNo());
        up.setProcStatus(tarProcStatus);
        up.setFinishTime(DateUtil.getDBNow());
        return up;
    }

    public static List<QueryContractChangeRespDto> buildChangeResps(List<ContractChangeReq> changeReqs) {
        List<QueryContractChangeRespDto> resps = Lists.newArrayList();
        if (CollectionUtils.isEmpty(changeReqs)) {
            return resps;
        }
        for (ContractChangeReq changeReq : changeReqs) {
            resps.add(buildChangeResp(changeReq));
        }
        return resps;
    }

    public static QueryContractChangeRespDto buildChangeResp(ContractChangeReq changeReq) {
        QueryContractChangeRespDto resp = new QueryContractChangeRespDto();
        resp.setReqNo(changeReq.getReqNo());
        resp.setCustomId(changeReq.getCustomId());
        resp.setPlatId(changeReq.getPlatId());
        resp.setProductNo(changeReq.getProductNo());
        resp.setEmail(changeReq.getEmail());
        resp.setBusiType(changeReq.getBusiType().getCode());
        resp.setCreateTime(changeReq.getCreateTime());
        resp.setOrgChannel(changeReq.getOrgChannel());
        resp.setProcStatus(changeReq.getProcStatus().getCode());
        resp.setFinishTime(changeReq.getFinishTime());
        return resp;
    }
}
