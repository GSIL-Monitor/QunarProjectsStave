package com.qunar.fintech.plat.admin.hank.controller;

import com.qunar.fintech.plat.admin.hank.entity.FileTask;
import com.qunar.fintech.plat.admin.hank.service.FileTaskService;
import com.qunar.fintech.plat.admin.support.web.PageData;
import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.fintech.plat.admin.support.web.R;
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
 * Created by tongda.sun on 2018/12/10  16:01.
 * Description:
 */
@Controller
@RequestMapping("/hank/fileTask")
public class FileTaskController {


    @GetMapping()
    @RequiresPermissions("hank:fileTask:list")
    String fileTask(Model model) {
        return prefix+"/search";
    }

    /**
     * 查询列表数据
     */
    @GetMapping("/list")
    @ResponseBody
    PageData list(@RequestParam Map<String, Object> params) {
        logger.info(prefix+"/list# params={}",params);
        Query query = new Query(params);
        List<FileTask> fileTasks = fileTaskService.select(query);
        int count = fileTaskService.count(query);
        PageData pageUtil = new PageData(fileTasks, count);
        return pageUtil;
    }

    /**
     * 跳转添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("hank:fileTask:add")
    String add(Model model){
        return prefix+"/add";
    }

    /**
     * 跳转编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("hank:fileTask:edit")
    String edit(@PathVariable("id") Long id, Model model){
        logger.info(prefix+"/edit# id={}",id);
        FileTask fileTask = fileTaskService.selectById(id);
        model.addAttribute("fileTask",fileTask);
        return prefix+"/edit";
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    @ResponseBody
    R save(FileTask fileTask){
        logger.info(prefix+"/save# fileTask={}",fileTask);
        try{
            if(fileTaskService.insertSelective(fileTask) > 0){
                return R.ok();
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return R.error();
    }

    /**
     * 更新数据
     */
    @PostMapping("/update")
    @ResponseBody
    R update(FileTask fileTask){
        logger.info(prefix+"/update# fileTask={}",fileTask);
        try{
            if (fileTaskService.updateByPrimaryKeySelective(fileTask) > 0){
                return R.ok();
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return R.error();
    }


    private static final String prefix = "hank/fileTask";
    @Autowired
    private FileTaskService fileTaskService;
    private static final Logger logger = LoggerFactory.getLogger(FileTaskController.class);
}
