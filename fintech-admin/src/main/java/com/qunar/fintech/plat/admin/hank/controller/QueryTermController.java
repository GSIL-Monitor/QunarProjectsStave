package com.qunar.fintech.plat.admin.hank.controller;

import com.qunar.fintech.plat.admin.hank.entity.QueryTerm;
import com.qunar.fintech.plat.admin.hank.service.QueryTermService;
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
 * Created by tongda.sun on 2018/12/08  16:55.
 * Description:
 */
@Controller
@RequestMapping("/hank/queryTerm")
public class QueryTermController {

    @GetMapping()
    @RequiresPermissions("hank:queryTerm:list")
    String queryTerm(Model model) {
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
        List<QueryTerm> queryTerms = queryTermService.select(query);
        int count = queryTermService.count(query);
        PageData pageUtil = new PageData(queryTerms, count);
        return pageUtil;
    }

    /**
     * 跳转添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("hank:queryTerm:add")
    String add(Model model){
        return prefix+"/add";
    }

    /**
     * 跳转编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("hank:queryTerm:edit")
    String edit(@PathVariable("id") Long id, Model model){
        logger.info(prefix+"/edit# id={}",id);
        QueryTerm queryTerm = queryTermService.selectById(id);
        model.addAttribute("queryTerm",queryTerm);
        return prefix+"/edit";
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    @ResponseBody
    R save(QueryTerm queryTerm){
        logger.info(prefix+"/save # queryTerm={}",queryTerm);
        try{
            if(queryTermService.insertSelective(queryTerm) > 0){
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
    R update(QueryTerm queryTerm){
        logger.info(prefix+"/update# queryTerm={}",queryTerm);
        try{
            if (queryTermService.updateByPrimaryKeySelective(queryTerm) > 0){
                return R.ok();
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return R.error();
    }

    private static final String prefix = "hank/queryTerm";
    @Autowired
    public QueryTermService queryTermService;
    private static final Logger logger = LoggerFactory.getLogger(QueryTermController.class);

}
