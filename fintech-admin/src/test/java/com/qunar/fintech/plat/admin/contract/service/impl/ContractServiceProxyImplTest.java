package com.qunar.fintech.plat.admin.contract.service.impl;

import com.qunar.fintech.plat.admin.contract.dto.QueryChannelContractRecord;
import com.qunar.fintech.plat.admin.contract.dto.QueryContractDto;
import com.qunar.fintech.plat.admin.contract.dto.QueryPlatContractRecord;
import com.qunar.fintech.plat.admin.contract.exception.ContractException;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author: zhengyang.zhong
 * @date: 2018/9/25
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/config/spring-admin.xml"})
public class ContractServiceProxyImplTest {

    @Test
    public void queryPlatAccount() {
        QueryResponse<QueryPlatContractRecord> respDto = null;
        QueryContractDto queryDto = new QueryContractDto();
        queryDto.setCustomId(CUSTOMID);
        queryDto.setProductNo(PRODUCTNO);
        try {
            respDto = contractQueryService.queryPlatAccount(queryDto);
            LOGGER.info("===== resp={} =====",respDto.getRows());
        }catch(ContractException e){
            LOGGER.error("===== 获取平台合同失败 =====",e.getErrorMsg(),e);
        }catch(Exception e){
            LOGGER.error("===== 未知错误 =====",e);
        }
    }

    @Test
    public void queryContractAccount() {
        QueryResponse<QueryChannelContractRecord> respDto = null;
        QueryContractDto queryDto = new QueryContractDto();
        queryDto.setCustomId(CUSTOMID);
        queryDto.setProductNo(PRODUCTNO);
        try {
            respDto = contractQueryService.queryChannelAccount(queryDto);
//            LOGGER.info("===== 合同状态={}，tppcode={} =====",respDto.getData()., respDto.getData().getTppCode());
        }catch(ContractException e){
            LOGGER.error("===== 获取平台合同失败 =====",e.getErrorMsg(),e);
        }catch(Exception e){
            LOGGER.error("===== 未知错误 =====",e);
        }
    }

    @Test
    public void queryCustomtIdByUserId() {
        LOGGER.info("===== customId={} =====",contractQueryService.queryCustomtIdByUserId(USERID));
    }

    @Resource
    private ContractServiceProxyImpl contractQueryService;

    private static final String CUSTOMID = "1444646286";
    private static final String PRODUCTNO = "IOUS";
    private static final String USERID = "1444646286";

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractServiceProxyImplTest.class);

}