package com.qunar.fintech.plat.admin.system.controller;

import com.qunar.fintech.plat.admin.support.web.PageData;
import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.plat.admin.system.dao.entity.TaskDO;
import com.qunar.fintech.plat.admin.system.service.TaskScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author huanyunz
 * @email huanyun.zhou@qunar.com
 * @date 2017-09-26 20:53:48
 */
@Controller
@RequestMapping("/sys/job")
public class JobController extends BaseController {

    @ResponseBody
    @GetMapping("/list")
    public PageData list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<TaskDO> taskScheduleJobList = taskScheduleJobService.list(query);
        int total = taskScheduleJobService.count(query);
        PageData pageData = new PageData(taskScheduleJobList, total);
        return pageData;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        TaskDO taskScheduleJob = taskScheduleJobService.get(id);
        return R.ok().put("taskScheduleJob", taskScheduleJob);
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(TaskDO taskScheduleJob) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (taskScheduleJobService.save(taskScheduleJob) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TaskDO taskScheduleJob) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        taskScheduleJobService.update(taskScheduleJob);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (taskScheduleJobService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Long[] ids) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        taskScheduleJobService.batchRemove(ids);

        return R.ok();
    }

    @PostMapping(value = "/changeJobStatus")
    @ResponseBody
    public R changeJobStatus(Long id, String cmd) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        String label = "停止";
        if (cmd.equals("start")) {
            label = "启动";
        } else {
            label = "停止";
        }
        try {
            taskScheduleJobService.changeStatus(id, cmd);
            return R.ok("任务" + label + "成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok("任务" + label + "失败");
    }

    @GetMapping()
    String TaskScheduleJob() {
        return "sys/job/taskScheduleJob";
    }

    @GetMapping("/add")
    String add() {
        return "sys/job/add";
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Long id, Model model) {
        TaskDO taskScheduleJob = taskScheduleJobService.get(id);
        model.addAttribute("TaskScheduleJob", taskScheduleJob);
        return "sys/job/edit";
    }

    @Autowired
    private TaskScheduleJobService taskScheduleJobService;

}
