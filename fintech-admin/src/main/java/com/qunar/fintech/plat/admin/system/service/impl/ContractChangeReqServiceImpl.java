package com.qunar.fintech.plat.admin.system.service.impl;

import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.fintech.plat.admin.system.builder.ContractChangeReqBuilder;
import com.qunar.fintech.plat.admin.system.dao.entity.ContractChangeReq;
import com.qunar.fintech.plat.admin.system.dao.enums.ProcStatusEnum;
import com.qunar.fintech.plat.admin.system.dao.mapper.ContractChangeReqDao;
import com.qunar.fintech.plat.admin.system.dto.QueryContractChangeReqDto;
import com.qunar.fintech.plat.admin.system.dto.QueryContractChangeRespDto;
import com.qunar.fintech.plat.admin.system.service.ContractChangeReqService;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.exception.CommonApiErrorCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-04
 * Time: 14:44
 */
@Service
public class ContractChangeReqServiceImpl implements ContractChangeReqService {

    @Override
    public ContractChangeReq saveChangeReq(ContractChangeReq signReq) {
        ParamChecker.notNull(signReq, "ContractChangeReq is null");
        ParamChecker.notBlank(signReq.getReqNo(), "reqNo is null");
        ContractChangeReq dbSignReq = contractChangeReqDao.queryByReqNo(signReq.getReqNo());
        if (dbSignReq != null) {
            return dbSignReq;
        }
        if (contractChangeReqDao.insertReq(signReq) < 1) {
            LOGGER.error("ContractChangeReqService insertReq error, signReq:{}", signReq);
            throw new BusiException(CommonApiErrorCodes.SYSTEM_ERROR,"保存signReq出错");
        }
        return contractChangeReqDao.queryByReqNo(signReq.getReqNo());
    }

    @Override
    public void updateReqByProcStatus(ContractChangeReq upReq, List<ProcStatusEnum> procStatus, boolean isCheck) {
        ParamChecker.notNull(upReq, "upReq is null");
        ParamChecker.notEmpty(procStatus, "procStatus is null");
        int num = contractChangeReqDao.updateByIdAndProcStatus(upReq, procStatus);
        if (isCheck && num < 1) {
            throw new BusiException(CommonApiErrorCodes.SYSTEM_ERROR,"修改signReq出错");
        }
    }

    @Override
    public ContractChangeReq querySignReq(String reqNo) {
        ParamChecker.notBlank(reqNo, "reqNo is null");
        ContractChangeReq signReq = contractChangeReqDao.queryByReqNo(reqNo);
        return signReq;
    }

    @Override
    public QueryResponse<QueryContractChangeRespDto> queryByParam(QueryContractChangeReqDto reqDto) {
        QueryResponse<QueryContractChangeRespDto> response = new QueryResponse<>();
        if (reqDto == null) {
            return response;
        }

        int pageIndex = getPageIndex(reqDto.getPageNum(), reqDto.getPageSize());
        List<ContractChangeReq> resps = contractChangeReqDao.queryByParam(reqDto, reqDto.getPageSize(), pageIndex);
        response.setRows(ContractChangeReqBuilder.buildChangeResps(resps));

        int totalNum = contractChangeReqDao.countByRequest(reqDto);
        response.setTotal(totalNum);

        return response;
    }

    private int getPageIndex(int currentPage, int pageSize) {
        return currentPage == 0 ? 0 : (currentPage - 1) * pageSize;
    }

    @Resource
    private ContractChangeReqDao contractChangeReqDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractChangeReqServiceImpl.class);
}
