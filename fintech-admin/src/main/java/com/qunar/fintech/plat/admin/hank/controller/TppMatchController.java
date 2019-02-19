package com.qunar.fintech.plat.admin.hank.controller;

import com.qunar.fintech.plat.admin.hank.entity.TppMatch;
import com.qunar.fintech.plat.admin.hank.service.TppMatchService;
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
 * Created by tongda.sun on 2018/12/08  11:48.
 * Description:
 */
@Controller
@RequestMapping("/hank/tppMatch")
public class TppMatchController {

    @GetMapping()
    @RequiresPermissions("hank:tppMatch:list")
    String tppMatch(Model model) {
        return prefix+"/search";
    }

    /**
     * 查询通道匹配数据
     */
    @GetMapping("/list")
    @ResponseBody
    PageData list(@RequestParam Map<String, Object> params) {
        logger.info(prefix+"/list# params={}",params);
        Query query = new Query(params);
        List<TppMatch> tppMatches = tppMatchService.select(query);
        int count = tppMatchService.count(query);
        PageData pageUtil = new PageData(tppMatches, count);
        return pageUtil;
    }

    /**
     * 添加通道匹配数据
     */
    @GetMapping("/add")
    @RequiresPermissions("hank:tppMatch:add")
    String add(Model model){
        return prefix+"/add";
    }

    /**
     * 编辑通道匹配数据
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("hank:tppMatch:edit")
    String edit(@PathVariable("id") Long id, Model model){
        logger.info(prefix+"/edit# id={}",id);
        TppMatch tppMatch = tppMatchService.selectById(id);
        model.addAttribute("tppMatch",tppMatch);
        return prefix+"/edit";
    }

    /**
     * 保存通道匹配数据
     */
    @PostMapping("/save")
    @ResponseBody
    R save(TppMatch tppMatch){
        logger.info(prefix+"/save# tppMatch={}",tppMatch);
        try{
            if(tppMatchService.insertSelective(tppMatch) > 0){
                return R.ok();
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return R.error();
    }

    /**
     * 更新通道匹配数据
     */
    @PostMapping("/update")
    @ResponseBody
    R update(TppMatch tppMatch){
        logger.info(prefix+"/update# tppMatch={}",tppMatch);
        try{
            if (tppMatchService.updateByPrimaryKeySelective(tppMatch) > 0){
                return R.ok();
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return R.error();
    }


    private static final String prefix = "hank/tppMatch";
    @Autowired
    private TppMatchService tppMatchService;
    private static final Logger logger = LoggerFactory.getLogger(TppMatchController.class);

}
