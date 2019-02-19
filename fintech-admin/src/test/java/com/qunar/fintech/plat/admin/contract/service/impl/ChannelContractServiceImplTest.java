package com.qunar.fintech.plat.admin.contract.service.impl;

import com.qunar.fintech.plat.admin.contract.dto.ChannelContractStatusReqDto;
import com.qunar.fintech.plat.admin.contract.service.ChannelContractService;
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
public class ChannelContractServiceImplTest {

    @Test
    public void closeChannelContractStatus() {
        ChannelContractStatusReqDto request = new ChannelContractStatusReqDto();
        request.setCustomId(CUSTOMID);
        request.setProductNo(PRODUCTNO);
        request.setTarStatus(0);
        request.setTppCode(TPPCODE);
        if(channelContractService.closeChannelContractStatus(request)){
            LOGGER.info("===== 关闭channel合同成功 =====");
        }else{
            LOGGER.error("===== 关闭channel合同失败 =====");
        }
    }

    @Test
    public void openChannelContractStatus() {
        ChannelContractStatusReqDto request = new ChannelContractStatusReqDto();
        request.setCustomId(CUSTOMID);
        request.setProductNo(PRODUCTNO);
        request.setTarStatus(1);
        request.setTppCode(TPPCODE);
        if(channelContractService.openChannelContractStatus(request)){
            LOGGER.info("===== 打开channel合同成功 =====");
        }else{
            LOGGER.error("===== 打开channel合同失败 =====");
        }
    }

    @Resource
    private ChannelContractService channelContractService;

    private static final String CUSTOMID = "1444646286";
    private static final String PRODUCTNO = "IOUS";
    private static final String TPPCODE = "QUNARLOAN";

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelContractServiceImplTest.class);
}