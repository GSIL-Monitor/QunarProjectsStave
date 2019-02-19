package com.qunar.fintech.plat.admin.hank.controller;

import com.qunar.fintech.plat.admin.hank.entity.MultiTableRelate;
import com.qunar.fintech.plat.admin.hank.service.MultiTableRelateService;
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
 * Created by tongda.sun on 2018/12/09  17:34.
 * Description:
 */
@Controller
@RequestMapping("/hank/multiTableRelate")
public class MultiTableRelateController {

    @GetMapping()
    @RequiresPermissions("hank:multiTableRelate:list")
    String multiTableRelate(Model model) {
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
        List<MultiTableRelate> multiTableRelates = multiTableRelateService.select(query);
        int count = multiTableRelateService.count(query);
        PageData pageUtil = new PageData(multiTableRelates, count);
        return pageUtil;
    }

    /**
     * 跳转添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("hank:multiTableRelate:add")
    String add(Model model){
        return prefix+"/add";
    }

    /**
     * 跳转编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("hank:multiTableRelate:edit")
    String edit(@PathVariable("id") Long id, Model model){
        logger.info(prefix+"/edit# id={}",id);
        MultiTableRelate multiTableRelate = multiTableRelateService.selectById(id);
        model.addAttribute("multiTableRelate",multiTableRelate);
        return prefix+"/edit";
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    @ResponseBody
    R save(MultiTableRelate multiTableRelate){
        logger.info(prefix+"/save# multiTableRelate={}",multiTableRelate);
        try{
            if(multiTableRelateService.insertSelective(multiTableRelate) > 0){
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
    R update(MultiTableRelate multiTableRelate){
        logger.info(prefix+"/update# multiTableRelate={}",multiTableRelate);
        try{
            if (multiTableRelateService.updateByPrimaryKeySelective(multiTableRelate) > 0){
                return R.ok();
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return R.error();
    }

    private static final String prefix = "hank/multiTableRelate";
    @Autowired
    public MultiTableRelateService multiTableRelateService;
    private static final Logger logger = LoggerFactory.getLogger(MultiTableRelateController.class);

}
