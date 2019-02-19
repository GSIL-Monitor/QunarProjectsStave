package com.qunar.fintech.plat.admin.system.dao.mapper;

import com.qunar.fintech.plat.admin.system.dao.entity.ContractChangeReq;
import com.qunar.fintech.plat.admin.system.dao.enums.ProcStatusEnum;
import com.qunar.fintech.plat.admin.system.dto.QueryContractChangeReqDto;
import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-04
 * Time: 12:02
 */
@Repository
public interface ContractChangeReqDao {

    int insertReq(ContractChangeReq signReq);

    int updateByIdAndProcStatus(@Param("record") ContractChangeReq signReq, @Param("procStatusList") List<ProcStatusEnum> procStatusList);

    ContractChangeReq queryByReqNo(@Param("reqNo") String reqNo);

    List<ContractChangeReq> queryByCusIdAndPrd(@Param("customId") String customId, @Param("productNo") String productNo);

    List<ContractChangeReq> queryByParam(@Param("request") QueryContractChangeReqDto queryDto,
                                         @Param("pageSize") Integer pageSize,
                                         @Param("pageIndex") Integer pageIndex);

    int countByRequest(@Param("request") QueryContractChangeReqDto queryDto);
}
