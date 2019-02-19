package com.qunar.fintech.plat.admin.hank.controller;

import com.qunar.fintech.plat.admin.hank.entity.AccountFileLaunch;
import com.qunar.fintech.plat.admin.hank.service.AccountFileService;
import com.qunar.fintech.plat.admin.support.web.PageData;
import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.plat.admin.system.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hank/file")
public class TaskController extends BaseController {

    @GetMapping()
    @RequiresPermissions("hank:file")
    String list() {
        logger.info("hank list start");
        return prefix + "/list";
    }

    @GetMapping("/getTaskTypes")
    @ResponseBody
    List<String> getTaskTypes(String receiver){
        List<String> taskTypes = accountFileService.getTaskTypes(receiver);
        logger.info("taskTypes={}",taskTypes);
        return taskTypes;
    }

    @GetMapping("/getReceivers")
    @ResponseBody
    List<String> getReceivers(String taskType){
        List<String> receivers = accountFileService.getReceivers(taskType);
        logger.info("receivers={}",receivers);
        return receivers;
    }

    @PostMapping("/getFileName")
    @ResponseBody
    String getFileName(String taskType, String receiver){
        String fileName = accountFileService.getFileName(taskType,receiver);
        logger.info("fileName={}", fileName);
        return fileName;
    }

    @GetMapping("/getFileList")
    @ResponseBody
    PageData getAccountCheckFileList(@RequestParam Map<String,Object> params){
        logger.info(prefix+"/getFileList# params={}",params);
        Query query = new Query(params);
        List<AccountFileLaunch> accountFileLaunches = accountFileService.getAccountFileLaunchList(query,getUsername());
        int count = accountFileService.getAccountFileLaunchListCount(query);
        logger.info("accountFileLaunches={} count={}", accountFileLaunches,count);
        PageData pageData = new PageData(accountFileLaunches,count);
        return pageData;
    }

    /**
     * 执行请求
     */
    @PostMapping("/export")
    @ResponseBody
    R export(@RequestParam Map<String, Object> params){
        try{
            accountFileService.export(params,getUsername());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return R.error("对账任务添加失败");
        }
        return R.ok("对账任务添加成功");
    }

    @Autowired
    private AccountFileService accountFileService;
    private static final String prefix = "hank/file";

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
}
