package com.qunar.fintech.plat.admin.system.controller;

import com.qunar.fintech.plat.admin.support.util.FileType;
import com.qunar.fintech.plat.admin.support.util.FileUtils;
import com.qunar.fintech.plat.admin.support.web.PageData;
import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.plat.admin.system.dao.entity.FileDO;
import com.qunar.fintech.plat.admin.system.service.SysFileService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 *
 * @author huanyunz
 * @email huanyun.zhou@qunar.com
 * @date 2017-09-19 16:02:20
 */
@Controller
@RequestMapping("/sys/file")
public class FileController extends BaseController {

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("sys:file:file")
    public PageData list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<FileDO> sysFileList = sysFileService.list(query);
        int total = sysFileService.count(query);
        PageData pageData = new PageData(sysFileList, total);
        return pageData;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("common:info")
    public R info(@PathVariable("id") Long id) {
        FileDO sysFile = sysFileService.get(id);
        return R.ok().put("sysFile", sysFile);
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("common:save")
    public R save(FileDO sysFile) {
        if (sysFileService.save(sysFile) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("common:update")
    public R update(@RequestBody FileDO sysFile) {
        sysFileService.update(sysFile);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    // @RequiresPermissions("common:remove")
    public R remove(Long id, HttpServletRequest request) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        String fileName = uploadPath + sysFileService.get(id).getUrl().replace("/files/", "");
        if (sysFileService.remove(id) > 0) {
            boolean b = FileUtils.deleteFile(fileName);
            if (!b) {
                return R.error("数据库记录删除成功，文件删除失败");
            }
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("common:remove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        sysFileService.batchRemove(ids);
        return R.ok();
    }

    @GetMapping()
    @RequiresPermissions("sys:file:file")
    String SysFile(Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        return "sys/file/file";
    }

    @GetMapping("/add")
        // @RequiresPermissions("common:bComments")
    String add() {
        return "sys/file/add";
    }

    @GetMapping("/edit")
        // @RequiresPermissions("common:bComments")
    String edit(Long id, Model model) {
        FileDO sysFile = sysFileService.get(id);
        model.addAttribute("sysFile", sysFile);
        return "sys/file/edit";
    }

    @ResponseBody
    @PostMapping("/upload")
    R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        String fileName = file.getOriginalFilename();
        fileName = FileUtils.RenameToUUID(fileName);
        FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
        try {
            FileUtils.uploadFile(file.getBytes(), uploadPath, fileName);
        } catch (Exception e) {
            return R.error();
        }

        if (sysFileService.save(sysFile) > 0) {
            return R.ok().put("fileName", sysFile.getUrl());
        }
        return R.error();
    }

    @Autowired
    private SysFileService sysFileService;

    @Value("uploadPath")
    private String uploadPath;
}
