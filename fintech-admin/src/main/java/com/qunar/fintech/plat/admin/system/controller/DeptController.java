package com.qunar.fintech.plat.admin.system.controller;

import com.qunar.fintech.plat.admin.support.util.BuildTree;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.plat.admin.system.dao.entity.DeptDO;
import com.qunar.fintech.plat.admin.system.dao.entity.Tree;
import com.qunar.fintech.plat.admin.system.dao.mapper.DeptDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author huanyunz
 * @email huanyun.zhou@qunar.com
 * @date 2017-09-27 14:40:36
 */

@Controller
@RequestMapping("/sys/dept")
public class DeptController extends BaseController {

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("sys:dept:dept")
    public List<DeptDO> list() {
        //查询列表数据
        // Query query = new Query(params);
        Map<String, Object> query = new HashMap<String, Object>();
        List<DeptDO> sysDeptList = sysDeptMapper.list(query);
        //	int total = sysDeptService.count(query);
        //PageData pageUtils = new PageData(sysDeptList, total);
        return sysDeptList;
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("sys:dept:add")
    public R save(DeptDO sysDept) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (sysDeptMapper.save(sysDept) > 0) {
            long id = sysDeptMapper.getMaxId();
            sysDept.setDeptId(id + 1);
            if (sysDeptMapper.updateDept(sysDept) > 0) {
                return R.ok();
            }
            return R.error();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("sys:dept:edit")
    public R update(DeptDO sysDept) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (sysDeptMapper.update(sysDept) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("sys:dept:remove")
    public R remove(Long deptId) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (sysDeptMapper.remove(deptId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("sys:dept:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] deptIds) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        sysDeptMapper.batchRemove(deptIds);
        return R.ok();
    }

    @GetMapping("/tree")
    @ResponseBody
    public Tree<DeptDO> tree() {
        Tree<DeptDO> tree = new Tree<DeptDO>();
        tree = getDepartmentTree();
        return tree;
    }

    @GetMapping()
    @RequiresPermissions("sys:dept:dept")
    String SysDept() {
        return "sys/dept/dept";
    }

    @GetMapping("/add/{pId}")
    @RequiresPermissions("sys:dept:add")
    String add(@PathVariable("pId") Long pId, Model model) {
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "总部门");
        } else {
            model.addAttribute("pName", sysDeptMapper.get(pId).getName());
        }
        return "sys/dept/add";
    }

    @GetMapping("/edit/{deptId}")
    @RequiresPermissions("sys:dept:edit")
    String edit(@PathVariable("deptId") Long deptId, Model model) {
        DeptDO sysDept = sysDeptMapper.get(deptId);
        model.addAttribute("sysDept", sysDept);
        return "sys/dept/edit";
    }

    @GetMapping("/treeView")
    String treeView() {
        return "sys/dept/deptTree";
    }

    @Autowired
    private DeptDao sysDeptMapper;

    private Tree<DeptDO> getDepartmentTree() {
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        List<DeptDO> SysDepts = sysDeptMapper.list(new HashMap<String, Object>());
        for (DeptDO SysDept : SysDepts) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(SysDept.getDeptId().toString());
            tree.setParentId(SysDept.getParentId().toString());
            tree.setText(SysDept.getName());
            Map<String, Object> state = new HashMap<String, Object>();
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        return BuildTree.build(trees);
    }

}
