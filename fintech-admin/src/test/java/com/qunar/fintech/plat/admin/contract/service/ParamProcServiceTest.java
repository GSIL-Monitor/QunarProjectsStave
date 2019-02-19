package com.qunar.fintech.plat.admin.contract.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @Author: zhengyang.zhong
 * @Date: 2018/10/18
 * @Despcription: 加解密测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/config/spring-admin.xml"})
public class ParamProcServiceTest {

    @Test
    public void mobileEnCrypt() {
        String mobile = paramProcService.mobileEnCrypt("13910989545","CTRIP");
        System.out.println("==========手机号结果："+ mobile + "==========");
    }

    @Test
    public void identityEnCrypt() {
        String identity = paramProcService.identityEnCrypt("622921198808081003");
        System.out.println("==========身份证结果："+ identity + "==========");
    }

    @Resource
    private ParamProcService paramProcService;
}