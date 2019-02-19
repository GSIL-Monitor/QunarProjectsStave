package com.qunar.fintech.plat.admin.system.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TaskTest {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    public void run() {
        for (int i = 0; i < 10; i++) {
            log.info(i + "-------计划任务测试---------" + (new Date()));
        }
    }

    public void run1() {
        for (int i = 0; i < 10; i++) {
            log.info(i + " run1......................................" + (new Date()));
        }
    }
}