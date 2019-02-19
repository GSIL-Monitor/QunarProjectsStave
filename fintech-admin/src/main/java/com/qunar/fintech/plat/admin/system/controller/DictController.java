package com.qunar.fintech.plat.admin.system.controller;

import com.qunar.fintech.plat.admin.support.web.PageData;
import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.plat.admin.system.dao.entity.DictDO;
import com.qunar.fintech.plat.admin.system.service.SysDictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * 字典表
 *
 * @author huanyunz
 * @email huanyun.zhou@qunar.com
 * @date 2017-09-29 18:28:07
 */

@Controller
@RequestMapping("/sys/dict")
public class DictController extends BaseController {

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("sys:dict:dict")
    public PageData list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<DictDO> sysDictList = sysDictService.list(query);
        int total = sysDictService.count(query);
        PageData pageData = new PageData(sysDictList, total);
        return pageData;
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("sys:dict:add")
    public R save(DictDO sysDict) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (sysDictService.save(sysDict) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("sys:dict:edit")
    public R update(DictDO sysDict) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        sysDictService.update(sysDict);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("sys:dict:remove")
    public R remove(Long id) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (sysDictService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("sys:dict:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        sysDictService.batchRemove(ids);
        return R.ok();
    }

    @GetMapping("/type")
    @ResponseBody
    public List<DictDO> listType() {
        return sysDictService.listType();
    }

    @GetMapping()
    @RequiresPermissions("sys:dict:sysDict")
    String SysDict() {
        return "sys/dict/sysDict";
    }

    @GetMapping("/add")
    @RequiresPermissions("sys:dict:add")
    String add() {
        return "sys/dict/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("sys:dict:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        DictDO sysDict = sysDictService.get(id);
        model.addAttribute("sysDict", sysDict);
        return "sys/dict/edit";
    }

    //类别已经指定增加
    @GetMapping("/add/{type}/{description}")
    @RequiresPermissions("sys:dict:add")
    String addD(Model model, @PathVariable("type") String type, @PathVariable("description") String description) {
        model.addAttribute("type", type);
        model.addAttribute("description", description);
        return "sys/dict/add";
    }

    ;

    @Autowired
    private SysDictService sysDictService;

}
