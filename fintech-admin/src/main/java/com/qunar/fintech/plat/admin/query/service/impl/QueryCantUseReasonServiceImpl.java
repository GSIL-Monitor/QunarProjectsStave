package com.qunar.fintech.plat.admin.query.service.impl;

import com.google.common.collect.Lists;
import com.qunar.finance.capetown.api.dto.dto.FintechOrderLogDto;
import com.qunar.finance.capetown.api.dto.request.CantUseReasonReqDto;
import com.qunar.finance.capetown.api.dto.response.CantUseReasonRespDto;
import com.qunar.finance.capetown.api.facade.FinOrderLogQueryFacade;
import com.qunar.fintech.plat.admin.query.service.CommonService;
import com.qunar.fintech.plat.admin.query.service.QueryCantUseReasonService;
import com.qunar.fintech.plat.admin.query.service.QueryIdsService;
import com.qunar.fintech.plat.admin.query.vo.CantUseReasonDto;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import com.qunar.fintech.plat.admin.query.vo.QueryIdDto;
import com.qunar.pay.finance.ious.common.enums.OrgChannelEnum;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: weiduan
 * Date: 2018-03-19
 */
@Service
public class QueryCantUseReasonServiceImpl implements QueryCantUseReasonService {

    public List<CantUseReasonDto> queryCantUseReason(QueryDto queryDto) {
        setOpenId(queryDto);
        try {
            if(StringUtils.isBlank(queryDto.getUserId())) {
                commonService.addUserIdWithCheck(queryDto);
            }
        } catch (Exception e) {
            logger.error("addUserIdWithCheck error",e);
            return Collections.emptyList();
        }
        QResponse<CantUseReasonRespDto> response = finOrderLogQueryFacade.queryFinProductCantUseReason(buildCantUseReasonReq(queryDto));
        logger.info("finOrderLogQueryFacade.result is {}",response);
        if(response.isSuccess() && null != response.getData()) {
            return buildCantUseReasonDto(response.getData());
        }
        throw new BusiException(response.getReturnCode(),response.getReturnMsg());
    }

    private void setOpenId(QueryDto queryDto) {
        if(StringUtils.isBlank(queryDto.getUserId()) && OrgChannelEnum.ifCtripChannel(queryDto.getOrgChannel())) {
            QueryIdDto queryIdDto = queryIdsService.queryIdsByMobile(queryDto.getMobile());
            if(null != queryIdDto) {
                queryDto.setUserId(queryIdDto.getOpenId());
            }
        }
    }

    private CantUseReasonReqDto buildCantUseReasonReq(QueryDto queryDto) {
        CantUseReasonReqDto req = new CantUseReasonReqDto();
        req.setReqUserId(queryDto.getUserId());
        req.setProductNo(queryDto.getProductNo());
        req.setOrgChannel(queryDto.getOrgChannel());
        req.setStartTime(queryDto.getStartTime());
        req.setEndTime(queryDto.getEndTime());
        return req;
    }

    private List<CantUseReasonDto> buildCantUseReasonDto(CantUseReasonRespDto respDto) {
        List<CantUseReasonDto> list = Lists.newArrayList();
        if(CollectionUtils.isEmpty(respDto.getFinOrderLogDtoList())) {
            return list;
        }
        for(FintechOrderLogDto logDto : respDto.getFinOrderLogDtoList()) {
            CantUseReasonDto cantUseReasonDto = new CantUseReasonDto();
            cantUseReasonDto.setUserId(respDto.getReqUserId());
            cantUseReasonDto.setProductNo(respDto.getProductNo());
            cantUseReasonDto.setOrgChannel(respDto.getOrgChannel());
            String busiOrderNo = logDto.getBusiOrderNo();
            if("NA".equals(busiOrderNo)) {
                busiOrderNo = "";
            }
            cantUseReasonDto.setBusiOrderNo(busiOrderNo);
            cantUseReasonDto.setCreateTime(logDto.getCreateTime());
            cantUseReasonDto.setMerchantCode(logDto.getMerchantCode());
            cantUseReasonDto.setPayOrderNo(logDto.getPayOrderNo());
            cantUseReasonDto.setErrorCode(logDto.getErrorCode());
            cantUseReasonDto.setErrorMsg(logDto.getErrorMsg());
            list.add(cantUseReasonDto);
        }
        return list;
    }

    @Resource
    private QueryIdsService queryIdsService;

    @Resource
    private FinOrderLogQueryFacade finOrderLogQueryFacade;

    @Resource
    private CommonService commonService;

    private static final Logger logger = LoggerFactory.getLogger(QueryCantUseReasonServiceImpl.class);

}
