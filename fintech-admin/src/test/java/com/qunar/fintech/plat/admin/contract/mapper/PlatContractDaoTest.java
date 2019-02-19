package com.qunar.fintech.plat.admin.contract.mapper;

import com.qunar.fintech.plat.admin.contract.dao.mapper.PlatContractDao;
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
public class PlatContractDaoTest {

    @Test
    public void queryCustomIdByIdentity() {
        String cid = "";
        try{
            cid = platContractDao.queryByIdentityCode(IDENTITY).getCustomId();
            LOGGER.info("=====CID为{}=====",cid);
        }catch(Exception e){
            LOGGER.error("===== 无法获取cid，身份证号为{} =====", IDENTITY);
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PlatContractDaoTest.class);
    private static final String IDENTITY = "1305GxIKP9su2HA043";
    private static final Integer id = 22;

    @Resource
    private PlatContractDao platContractDao;
}