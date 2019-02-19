package com.qunar.fintech.plat.admin.hank.controller;

import com.qunar.fintech.plat.admin.hank.entity.TppConfig;
import com.qunar.fintech.plat.admin.hank.service.TppConfigService;
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
 * Created by tongda.sun on 2018/12/06  17:14.
 * Description:
 */
@Controller
@RequestMapping("/hank/tppConfig")
public class TppConfigController {

    @GetMapping()
    @RequiresPermissions("hank:tppConfig:list")
    String tppConfig(Model model) {
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
        List<TppConfig> tppConfigs = tppConfigService.select(query);
        int count = tppConfigService.count(query);
        PageData pageUtil = new PageData(tppConfigs, count);
        return pageUtil;
    }

    /**
     * 跳转添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("hank:tppConfig:add")
    String add(Model model){
        return prefix+"/add";
    }

    /**
     * 跳转编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("hank:tppConfig:edit")
    String edit(@PathVariable("id") Long id,Model model){
        logger.info(prefix+"/edit# id={}",id);
        TppConfig tppConfig = tppConfigService.selectById(id);
        model.addAttribute("tppConfig",tppConfig);
        return prefix+"/edit";
    }

    /**
     * 保存前处理
     * 校验指定数据文件是否存在
     * @params taskType & receiver
     */
    @PostMapping("/exit")
    @ResponseBody
    boolean exit(@RequestParam Map<String,Object> params){
        logger.info(prefix+"/exit# params={}",params);
        String taskType = params.get("taskType").toString();
        String receiver = params.get("receiver").toString();
        if(CheckUtils.isEmpty(taskType) || CheckUtils.isEmpty(receiver)){
            return true;
        }
        TppConfig tppConfig = tppConfigService.selectByTaskTypeAndReceiver(taskType,receiver);
        return CheckUtils.isNull(tppConfig);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    @ResponseBody
    R save(TppConfig tppConfig){
        logger.info(prefix+"/save # tppConfig={}",tppConfig);
        try{
            if(tppConfigService.insertSelective(tppConfig) > 0){
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
    R update(TppConfig tppConfig){
        logger.info(prefix+"/update# tppConfig={}",tppConfig);
        try{
            if (tppConfigService.updateByPrimaryKeySelective(tppConfig) > 0){
                return R.ok();
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return R.error();
    }

    private static final String prefix =  "hank/tppConfig";

    @Autowired
    public TppConfigService tppConfigService;
    private static final Logger logger = LoggerFactory.getLogger(TppConfigController.class);

}
