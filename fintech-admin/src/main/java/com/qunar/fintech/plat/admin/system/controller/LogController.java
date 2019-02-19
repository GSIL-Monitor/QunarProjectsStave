package com.qunar.fintech.plat.admin.system.controller;

import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.plat.admin.system.dao.entity.LogDO;
import com.qunar.fintech.plat.admin.system.dao.entity.PageDO;
import com.qunar.fintech.plat.admin.system.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/sys/log")
@Controller
public class LogController {

    @GetMapping()
    String log() {
        return prefix + "/log";
    }

    @ResponseBody
    @GetMapping("/list")
    PageDO<LogDO> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<LogDO> page = logService.queryList(query);
        return page;
    }

    @ResponseBody
    @PostMapping("/remove")
    R remove(Long id) {
        if (logService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @ResponseBody
    @PostMapping("/batchRemove")
    R batchRemove(@RequestParam("ids[]") Long[] ids) {
        int r = logService.batchRemove(ids);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }

    @Autowired
    LogService logService;

    String prefix = "sys/log";
}
