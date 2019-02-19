package com.qunar.fintech.plat.admin.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-06
 * Time: 10:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/config/spring-admin.xml"})
public class BaseAdminDBTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseAdminDBTest.class);

    @Test
    public void test() {
        logger.error("测试基累正确执行，不要改");
    }
}
