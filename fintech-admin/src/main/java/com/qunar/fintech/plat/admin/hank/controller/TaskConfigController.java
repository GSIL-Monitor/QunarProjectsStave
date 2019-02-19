package com.qunar.fintech.plat.admin.hank.controller;

import com.qunar.fintech.plat.admin.hank.entity.TaskConfig;
import com.qunar.fintech.plat.admin.hank.service.TaskConfigService;
import com.qunar.fintech.plat.admin.support.web.PageData;
import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.pay.g2.utils.common.CheckUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by tongda.sun on 2018/12/08  16:17.
 * Description:
 */
@Controller
@RequestMapping("/hank/taskConfig")
public class TaskConfigController {

    @GetMapping()
    @RequiresPermissions("hank:taskConfig:list")
    String taskConfig(Model model){
        return prefix+"/search";
    }

    /**
     * 查询列表数据
     */
    @GetMapping("/list")
    @ResponseBody
    PageData list(@RequestParam Map<String, Object> params) {
        logger.info(prefix+"/list params={}",params);
        Query query = new Query(params);
        List<TaskConfig> taskConfigs = taskConfigService.select(query);
        logger.info(prefix+"/list return={}",taskConfigs);
        int count = taskConfigService.count(query);
        PageData pageUtil = new PageData(taskConfigs, count);
        return pageUtil;
    }

    /**
     * 跳转添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("hank:taskConfig:add")
    String add(Model model){
        return prefix+"/add";
    }

    /**
     * 跳转编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("hank:taskConfig:edit")
    String edit(@PathVariable("id") Long id, Model model){
        logger.info(prefix+"/edit# id={}",id);
        TaskConfig taskConfig = taskConfigService.selectById(id);
        model.addAttribute("taskConfig",taskConfig);
        return prefix+"/edit";
    }

    /**
     * 保存前检查 记录是否存在
     * @params taskType
     */
    @PostMapping("/exit")
    @ResponseBody
    boolean exit(@RequestParam Map<String,Object> params){
        logger.info(prefix+"/exit# params={}",params);
        String taskType = params.get("taskType").toString();
        TaskConfig taskConfig = taskConfigService.selectConfigByTaskType(taskType);
        return CheckUtils.isNull(taskConfig);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ResponseBody
    R save(TaskConfig taskConfig){
        logger.info(prefix+"/save # taskConfig={}",taskConfig);
        try{
            if(taskConfigService.insertSelective(taskConfig) > 0){
                return R.ok();
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return R.error();
    }

    /**
     * 更新数据文件
     */
    @PostMapping("/update")
    @ResponseBody
    R update(TaskConfig taskConfig){
        logger.info(prefix+"/update# taskConfig={}",taskConfig);
        try{
            if (taskConfigService.updateByPrimaryKeySelective(taskConfig) > 0){
                return R.ok();
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return R.error();
    }

    private static final String prefix = "hank/taskConfig";
    @Autowired
    public TaskConfigService taskConfigService;
    private static final Logger logger = LoggerFactory.getLogger(TaskConfigController.class);
}
