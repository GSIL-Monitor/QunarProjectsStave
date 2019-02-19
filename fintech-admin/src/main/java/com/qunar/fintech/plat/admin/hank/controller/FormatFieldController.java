package com.qunar.fintech.plat.admin.hank.controller;

import com.qunar.fintech.plat.admin.hank.entity.FormatField;
import com.qunar.fintech.plat.admin.hank.service.FormatFieldService;
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
 * Created by tongda.sun on 2018/12/09  21:36.
 * Description:
 */
@Controller
@RequestMapping("/hank/formatField")
public class FormatFieldController {

    @GetMapping()
    @RequiresPermissions("hank:formatField:list")
    String formatField(Model model) {
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
        List<FormatField> formatFields = formatFieldService.select(query);
        int count = formatFieldService.count(query);
        PageData pageUtil = new PageData(formatFields, count);
        return pageUtil;
    }

    /**
     * 跳转添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("hank:formatField:add")
    String add(Model model){
        return prefix+"/add";
    }

    /**
     * 跳转编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("hank:formatField:edit")
    String edit(@PathVariable("id") Long id, Model model){
        logger.info(prefix+"/edit# id={}",id);
        FormatField formatField = formatFieldService.selectById(id);
        model.addAttribute("formatField",formatField);
        return prefix+"/edit";
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    @ResponseBody
    R save(FormatField formatField){
        logger.info(prefix+"/save# formatField={}",formatField);
        try{
            if(formatFieldService.insertSelective(formatField) > 0){
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
    R update(FormatField formatField){
        logger.info(prefix+"/update# formatField={}",formatField);
        try{
            if (formatFieldService.updateByPrimaryKeySelective(formatField) > 0){
                return R.ok();
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return R.error();
    }

    private static final String prefix = "hank/formatField";
    @Autowired
    private FormatFieldService formatFieldService;
    private static final Logger logger = LoggerFactory.getLogger(FormatFieldController.class);
}
