package com.qunar.fintech.plat.admin.contract.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.qunar.fintech.plat.admin.contract.builder.ContractBuilder;
import com.qunar.fintech.plat.admin.contract.dto.ChannelContractStatusReqDto;
import com.qunar.fintech.plat.admin.contract.dto.ChannelContractStatusRespDto;
import com.qunar.fintech.plat.admin.contract.dto.PaySwitchReqDto;
import com.qunar.fintech.plat.admin.contract.dto.PaySwitchRespDto;
import com.qunar.fintech.plat.admin.contract.dto.PlatStatusReqDto;
import com.qunar.fintech.plat.admin.contract.dto.PlatStatusRespDto;
import com.qunar.fintech.plat.admin.contract.dto.QueryChannelContractRecord;
import com.qunar.fintech.plat.admin.contract.dto.QueryContractVo;
import com.qunar.fintech.plat.admin.contract.dto.QueryPlatContractRecord;
import com.qunar.fintech.plat.admin.contract.exception.ContractException;
import com.qunar.fintech.plat.admin.contract.service.ContractServiceProxy;
import com.qunar.fintech.plat.admin.contract.service.ParamProcService;
import com.qunar.fintech.plat.admin.contract.service.PlatContractService;
import com.qunar.fintech.plat.admin.contract.util.ContractParamCheck;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.fintech.plat.admin.system.builder.ContractChangeReqBuilder;
import com.qunar.fintech.plat.admin.system.dao.entity.ContractChangeReq;
import com.qunar.fintech.plat.admin.system.dao.enums.ProcStatusEnum;
import com.qunar.fintech.plat.admin.system.dto.QueryContractChangeReqDto;
import com.qunar.fintech.plat.admin.system.dto.QueryContractChangeRespDto;
import com.qunar.fintech.plat.admin.system.service.ContractChangeReqService;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.utils.base.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;


@RequestMapping("/finance/contract")
@Controller
public class ContractController {

    @RequiresPermissions("finance:query")
    @GetMapping("/list")
    String list() {
        return "/contract/query";
    }

    @RequiresPermissions("finance:query")
    @GetMapping("/gotoChangeRecord")
    String gotoApplyRecord() {
        return "/contract/queryChangeReq";
    }

    /**
     * 拿去花账户查询controller
     *
     * @return
     */
    @RequiresPermissions("finance:query")
    @PostMapping(value = "/queryChannelAccount")
    @ResponseBody
    public QueryResponse<QueryChannelContractRecord> queryChannelAccount(@RequestBody QueryContractVo queryDto) {
        LOGGER.info("queryChannelAccount QueryAccountDto {}", queryDto);
        ContractParamCheck.checkQueryParam(queryDto);
        QueryResponse<QueryChannelContractRecord> response = new QueryResponse<>();

        try {
            List<String> customIds = getCustomId(queryDto);
            if (CollectionUtils.isEmpty(customIds) || StringUtils.isEmpty(customIds.get(0))) {
                LOGGER.error("无法取CustomId！");
                return response;
            }
            LOGGER.info("queryChannelAccount CustomIdList={}", customIds);
            for (String cid : customIds) {
                QueryResponse<QueryChannelContractRecord> resp = contractServiceProxy.queryChannelAccount(
                        ContractBuilder.buildQueryDto(queryDto, cid));
                if(null == response.getRows()) {
                    response.setRows(resp.getRows());
                }else{
                    response.getRows().addAll(resp.getRows());
                }
            }
            response.setTotal(response.getRows().size());
        } catch (Exception e) {
            LOGGER.error("queryChannelAccount#失败", e);
            return response;
        }
       LOGGER.info("queryChannelAccount resp={}", response);
        return response;
    }

    @RequiresPermissions("finance:query")
    @PostMapping(value = "/queryPlatAccount")
    @ResponseBody
    public QueryResponse<QueryPlatContractRecord> queryPlatAccount(@RequestBody QueryContractVo queryDto) {
        LOGGER.info("queryPlatAccount QueryAccountDto {}", queryDto);
        ContractParamCheck.checkQueryParam(queryDto);
        QueryResponse<QueryPlatContractRecord> response = new QueryResponse<QueryPlatContractRecord>();

        try {
            List<String> customIds = getCustomId(queryDto);
            if (CollectionUtils.isEmpty(customIds)) {
                LOGGER.error("无法取CustomId！");
                return response;
            }
            for (String cid : customIds) {
                QueryResponse<QueryPlatContractRecord> resp = contractServiceProxy.queryPlatAccount(
                        ContractBuilder.buildQueryDto(queryDto, cid));
                if(null == response.getRows()) {
                    response.setRows(resp.getRows());
                }else{
                    response.getRows().addAll(resp.getRows());
                }
            }
            response.setTotal(response.getRows().size());
        } catch (Exception e) {
            LOGGER.error("queryPlatAccount#失败", e);
            return response;
        }
        LOGGER.info("queryPlatAccount resp={}", response);
        return response;
    }

    @PostMapping(value = "/modifyChannelContractStatus")
    @ResponseBody
    public ChannelContractStatusRespDto modifyChannelContractStatus(ChannelContractStatusReqDto reqDto) {
        LOGGER.info("modifyChannelContractStatus#query request is {}", reqDto);
        ChannelContractStatusRespDto resp = new ChannelContractStatusRespDto();
        try {
            resp = contractServiceProxy.modifyChannelContractStatus(reqDto);
            LOGGER.info("modifyChannelContractStatus# result:{}", resp.getErrorMsg());
        } catch (ContractException ce) {
            LOGGER.error("modifyPlatContractStatus# error,", ce);
            resp.setReqFail();
            resp.setErrorCode(ce.getErrorCode());
            resp.setErrorMsg(ce.getErrorMsg());
        } catch (BusiException be) {
            LOGGER.error("modifyPlatContractStatus# error,", be);
            resp.setReqFail();
            resp.setErrorCode(be.getErrCode());
            resp.setErrorMsg(be.getErrMsg());
        } catch (Exception e) {
            LOGGER.error("modifyPlatContractStatus# error,", e);
            resp.setReqFail();
            resp.setErrorCode(UNKOWN_ERROR);
            resp.setErrorMsg("通道合同状态设置异常");
        }
        LOGGER.info("modifyChannelContractStatus# [end] result={}", resp);
        return resp;
    }

    @PostMapping(value = "/modifyPlatContractStatus")
    @ResponseBody
    public PlatStatusRespDto modifyPlatContractStatus(PlatStatusReqDto request) {
        LOGGER.info("modifyPlatContractStatus - start - request is {}", request);
        PlatStatusRespDto resp = new PlatStatusRespDto();
        ContractChangeReq changeReq = null;
        try {
            // 保存请求记录
            changeReq = contractChangeReqService.saveChangeReq(ContractChangeReqBuilder.buildReq4PlatStatus(request));
            if (ProcStatusEnum.inFinish(changeReq.getProcStatus())) {
                LOGGER.info("modifyPlatContractStatus - proc - 该次请求已经处理完成");
                resp.setReqSuccess();
                resp.setResult(ProcStatusEnum.inSuc(changeReq.getProcStatus()));
                return resp;
            }

            // 修改平台状态
            resp = platContractService.modifyPlatStatus(request, changeReq);
            LOGGER.info("modifyPlatContractStatus - proc - resp:{}", resp);
        } catch (ContractException ce) {
            LOGGER.error("modifyPlatContractStatus# error,", ce);
            resp.setReqFail();
            resp.setErrorCode(ce.getErrorCode());
            resp.setErrorMsg(ce.getErrorMsg());
        } catch (BusiException busiEx) {
            LOGGER.error("modifyPlatContractStatus# error,", busiEx);
            resp.setReqFail();
            resp.setErrorCode(busiEx.getErrCode());
            resp.setErrorMsg(busiEx.getErrMsg());
        } catch (Exception e) {
            LOGGER.error("modifyPlatContractStatus# error,", e);
            resp.setReqFail();
            resp.setErrorCode(UNKOWN_ERROR);
            resp.setErrorMsg("平台合同状态设置出现未知异常");
        }

        // 处理修改记录
        if (changeReq != null && StringUtils.isNotBlank(changeReq.getReqNo())) {
            procChangeSignReq(resp.isReqSuccess() && resp.isResult() ? ProcStatusEnum.SUC : ProcStatusEnum.FAIL, changeReq.getReqNo(),
                    resp.getReturnCode(), resp.getReturnMsg());
        }

        LOGGER.info("modifyPlatContractStatus - end - result:{}", resp);
        return resp;
    }

    @PostMapping(value = "/modifyPaySwitch")
    @ResponseBody
    public PaySwitchRespDto modifyPaySwitch(PaySwitchReqDto request) {
        LOGGER.info("modifyPaySwitch - start - request is {}", request);
        PaySwitchRespDto resp = new PaySwitchRespDto();
        ContractChangeReq changeReq = null;
        try {
            // 保存请求记录
            changeReq = contractChangeReqService.saveChangeReq(ContractChangeReqBuilder.buildReq4PaySwitch(request));
            if (ProcStatusEnum.inFinish(changeReq.getProcStatus())) {
                LOGGER.info("modifyPaySwitch - proc - 该次请求已经处理完成");
                resp.setReqSuccess();
                resp.setResult(ProcStatusEnum.inSuc(changeReq.getProcStatus()));
                return resp;
            }

            resp = platContractService.updatePaySwitch(request, changeReq);
            LOGGER.info("modifyPaySwitch# result:{}", resp);
        } catch (BusiException busiEx) {
            LOGGER.error("modifyPaySwitch# error,", busiEx);
            resp.setReqFail();
            resp.setReturnMsg("平台合同支付开关修改异常");
            resp.setReturnCode(busiEx.getErrCode());
            resp.setReturnMsg(busiEx.getErrMsg());
        } catch (Exception e) {
            LOGGER.error("modifyPaySwitch# error,", e);
            resp.setReqFail();
            resp.setReturnMsg("平台合同支付开关修改异常");
            resp.setReturnCode(UNKOWN_ERROR);
            resp.setReturnMsg(e.getMessage());
        }

        // 处理修改记录
        if (changeReq != null && StringUtils.isNotBlank(changeReq.getReqNo())) {
            procChangeSignReq(resp.isReqSuccess() && resp.isResult() ? ProcStatusEnum.SUC : ProcStatusEnum.FAIL, changeReq.getReqNo(),
                    resp.getReturnCode(), resp.getReturnMsg());
        }

        LOGGER.info("modifyPaySwitch - end - result:{}", resp);
        return resp;
    }

    @PostMapping("/queryChangeRecord")
    @ResponseBody
    public QueryResponse<QueryContractChangeRespDto> queryChangeRecord(@RequestBody QueryContractChangeReqDto request) {
        LOGGER.info("queryChangeRecord - start - request is {}", request);
        QueryResponse<QueryContractChangeRespDto> response = new QueryResponse<>();
        ContractParamCheck.checkQueryChangeReqParam(request);
        try {
            response = contractChangeReqService.queryByParam(request);
        } catch (BusiException busiEx) {
            LOGGER.error("queryChangeRecord# error,", busiEx);
        } catch (Exception e) {
            LOGGER.error("queryChangeRecord# error,", e);
        }
        return response;
    }

    private List<String> getCustomId(QueryContractVo queryDto) {
        Set<String> customIds = Sets.newHashSet();
        if (StringUtils.isNotBlank(queryDto.getCustomId())) {
            customIds.add(queryDto.getCustomId());
        }

        // 根据platId查询
        if (StringUtils.isNotBlank(queryDto.getPlatId())) {
            String customId = contractServiceProxy.queryCustomtIdByUserId(queryDto.getPlatId());
            LOGGER.info("getCustomId - use platId:{} query customId:{}",queryDto.getPlatId(), customId);
            if (StringUtils.isNotBlank(customId)) {
                customIds.add(customId);
            }
        }

        // 根据身份证号查询
        if (StringUtils.isNotBlank(queryDto.getIdentityCode())) {
            String customId = platContractService.queryCustomIdByIdentity(paramProcService.identityEnCrypt(queryDto.getIdentityCode()));
            LOGGER.info("getCustomId - use identityCode:{} query customId:{}",queryDto.getIdentityCode(), customId);
            if (StringUtils.isNotBlank(customId)) {
                customIds.add(customId);
            }
        }

        // 根据手机号查询
        if (StringUtils.isNotBlank(queryDto.getMobile())) {
            List<String> cids = platContractService.queryCustomIdByMobile(
                    paramProcService.mobileEnCrypt(queryDto.getMobile(), queryDto.getOrgChannel()),queryDto.getProductNo());
            LOGGER.info("getCustomId - use mobile:{} query customId:{}",queryDto.getMobile(), cids);
            if (CollectionUtils.isNotEmpty(cids)) {
                customIds.addAll(cids);
            }
        }
        return Lists.newArrayList(customIds);
    }

    private void procChangeSignReq(ProcStatusEnum procStatus, String reqNo, String returnCode, String returnMsg) {
        ContractChangeReq signReq = contractChangeReqService.querySignReq(reqNo);
        if (signReq == null) {
            return;
        }
        ContractChangeReq upReq = ContractChangeReqBuilder.fillSignReq(signReq, procStatus, returnCode, returnMsg);
        LOGGER.info("updateReqByProcStatus - upReq:{}", upReq);
        contractChangeReqService.updateReqByProcStatus(upReq,
                Lists.newArrayList(ProcStatusEnum.INIT, ProcStatusEnum.PROCESSING), false);
    }


    @Resource
    private ParamProcService paramProcService;
    @Resource
    private PlatContractService platContractService;
    @Resource
    private ContractServiceProxy contractServiceProxy;
    @Resource
    private ContractChangeReqService contractChangeReqService;

    private static final String UNKOWN_ERROR = "UNKOWN_ERROR";
    private static final Logger LOGGER = LoggerFactory.getLogger(ContractController.class);
}

