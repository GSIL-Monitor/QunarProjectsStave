package com.qunar.fintech.plat.admin.hank.controller;

import com.qunar.fintech.plat.admin.hank.entity.FtpConfig;
import com.qunar.fintech.plat.admin.hank.service.FtpConfigService;
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
 * Created by tongda.sun on 2018/12/09  16:35.
 * Description:
 */
@Controller
@RequestMapping("/hank/ftpConfig")
public class FtpConfigController {

    @GetMapping()
    @RequiresPermissions("hank:ftpConfig:list")
    String ftpConfig(Model model) {
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
        List<FtpConfig> ftpConfigs = ftpConfigService.select(query);
        int count = ftpConfigService.count(query);
        PageData pageUtil = new PageData(ftpConfigs, count);
        return pageUtil;
    }

    /**
     * 跳转添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("hank:ftpConfig:add")
    String add(Model model){
        return prefix+"/add";
    }

    /**
     * 跳转编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("hank:ftpConfig:edit")
    String edit(@PathVariable("id") Long id, Model model){
        logger.info(prefix+"/edit# id={}",id);
        FtpConfig ftpConfig = ftpConfigService.selectById(id);
        model.addAttribute("ftpConfig",ftpConfig);
        return prefix+"/edit";
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    @ResponseBody
    R save(FtpConfig ftpConfig){
        logger.info(prefix+"/save# ftpConfig={}",ftpConfig);
        try{
            if(ftpConfigService.insertSelective(ftpConfig) > 0){
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
    R update(FtpConfig ftpConfig){
        logger.info(prefix+"/update# ftpConfig={}",ftpConfig);
        try{
            if (ftpConfigService.updateByPrimaryKeySelective(ftpConfig) > 0){
                return R.ok();
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return R.error();
    }

    private static final String prefix = "hank/ftpConfig";
    @Autowired
    private FtpConfigService ftpConfigService;
    private static final Logger logger = LoggerFactory.getLogger(FtpConfigController.class);

}
