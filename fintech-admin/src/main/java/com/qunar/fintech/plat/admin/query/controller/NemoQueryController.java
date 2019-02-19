package com.qunar.fintech.plat.admin.query.controller;

import com.google.common.collect.Lists;
import com.qunar.fintech.plat.admin.query.enums.nemo.ExportStatusEnum;
import com.qunar.fintech.plat.admin.query.export.ExportQuery;
import com.qunar.fintech.plat.admin.query.export.ExportTask;
import com.qunar.fintech.plat.admin.query.export.ExportTaskParam;
import com.qunar.fintech.plat.admin.query.export.UploadFileParam;
import com.qunar.fintech.plat.admin.query.export.intf.ExportService;
import com.qunar.fintech.plat.admin.query.export.utils.ExcelExportUtil;
import com.qunar.fintech.plat.admin.query.export.utils.FTPClientTemplate;
import com.qunar.fintech.plat.admin.query.export.utils.FtpServerConfigUtil;
import com.qunar.fintech.plat.admin.query.service.NemoQueryService;
import com.qunar.fintech.plat.admin.query.utils.AjaxJson;
import com.qunar.fintech.plat.admin.query.vo.NemoQueryVo;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.fintech.plat.admin.support.security.ShiroUtils;
import com.qunar.fintech.plat.admin.support.util.DateUtils;
import com.qunar.fintech.plat.admin.system.dao.entity.ExportFile;
import com.qunar.pay.finance.qf.commons.utils.base.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

@RequestMapping("/finance/nemo")
@Controller
public class NemoQueryController {

    @RequiresPermissions("finance:nemo")
    @GetMapping("/list")
    String list() {
        return "/query/queryNemoId";
    }

    @RequiresPermissions("finance:nemo")
    @GetMapping("/download")
    String download() {
        return "/query/nemoDownload";
    }

    /**
     * 根据平台ID查询
     * nemoQueryVo.exportType
     * 1-根据platID查询UID，
     * 2-根据UID查询platID，
     * 3-根据customerId查询tppID，
     * 4-根据tppId查询customerId
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectByPlatId")
    QueryResponse<NemoQueryVo> selectByPlatId(NemoQueryVo nemoQueryVo) {
        logger.info("selectByPlatId# param nemoQueryVo is : {}", nemoQueryVo);
        try {
            return nemoQueryService.selectByPlatId(nemoQueryVo);
        } catch (Exception e) {
            logger.error("selectByPlatId error", e);
            return null;
        }
    }
    /**
     * 根据excel条件批量导出
     * nemoQueryVo.exportType
     * 1-根据platID导出UID，
     * 2-根据UID导出platID，
     * 3-根据customerId导出tppID,
     * 4-根据tppId导出customerId
     */
    @RequiresPermissions("finance:nemo")
    @RequestMapping(value = "/queryIdExport", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson queryIdExport(NemoQueryVo query, @RequestParam("file") MultipartFile file) {
        AjaxJson j = new AjaxJson();
        j.setSuccess(false);
        j.setMsg("导出请求失败");
        try {
            Long userId = ShiroUtils.getUserId();
            if (query == null || userId == null || file == null) {
                return j;
            }
            logger.info("queryIdExport--upload-type:{}", file.getContentType());
            ExportQuery<NemoQueryVo> nemoQuery = new ExportQuery<>();
            nemoQuery.setQuery(query);
            ExportTaskParam<NemoQueryVo> exportTaskParam = new ExportTaskParam<>();
            exportTaskParam.setUserId(userId);
            exportTaskParam.setQuery(nemoQuery);
            exportTaskParam.setPojoClass(NemoQueryVo.class);
            exportTaskParam.setExportService(exportService);
            exportTaskParam.setNemoQueryService(nemoQueryService);
            exportTaskParam.setUploadFileParam(new UploadFileParam(file.getInputStream(),
                    file.getOriginalFilename(), file.getContentType()));
            ExportTask exportTesk = new ExportTask(exportTaskParam);
            ExcelExportUtil.exportExcelThread(exportTesk);
            j.setSuccess(true);
            j.setMsg("导出请求成功，请到下载列表等待下载！");
        } catch (Exception e) {
            logger.error("根据excel条件批量导出", e);
        }
        return j;
    }
    /**
     * 文件列表展示
     * @return
     */
    @RequiresPermissions("finance:nemo")
    @GetMapping("/downloadList")
    @ResponseBody
    public QueryResponse<ExportFile> downloadList(@RequestParam Integer pageSize,
                                                  @RequestParam Integer pageIndex) {
        Page page = new Page();
        page.setCurrentIndex((pageIndex-1) * pageSize);
        page.setPageSize(pageSize);
        QueryResponse<ExportFile> queryResponse = new QueryResponse<>();
        try {
            Long userId = ShiroUtils.getUserId();
            if (userId == null) {
                logger.error("ShiroUtils.getUserId() is null！");
                return queryResponse;
            }
            ExportFile ef = new ExportFile();
            ef.setUserId(ShiroUtils.getUserId());
            queryResponse.setTotal(exportService.selectExportFileTotal(ef.getUserId()));
            List<ExportFile> exportTask = exportService.selectExportList(ef, page);
            if (CollectionUtils.isEmpty(exportTask)) {
                queryResponse.setRows(Lists.<ExportFile>newArrayList());
                return queryResponse;
            }
            for (ExportFile exportFileTask : exportTask){
                if (DateUtil.isBeforeDate(exportFileTask.getEndTime())){
                    continue;
                }
                exportService.updateExportFileByPrimaryKey(exportFileTask, ExportStatusEnum.INVALID);
                exportFileTask.setExportStatus(ExportStatusEnum.INVALID.getKey());
                exportFileTask.setExportStatusDesc(ExportStatusEnum.INVALID.getValue());
            }
            queryResponse.setRows(exportTask);
        } catch (Exception e) {
            logger.error("NemoQueryController downloadList error {}", e);
        }
        return queryResponse;
    }

    /**
     * 下载导出文件
     * @param url 传入的文件URL
     * @param response
     */
    @RequiresPermissions("finance:nemo")
    @RequestMapping("/downLoadExportFile")
    public void downLoadExportFile(@RequestParam(required = true, value = "url")String url,
                                           HttpServletResponse response) {
        OutputStream os = null;
        try {
            //文件不存在本地服务器上，在ftp服务器上查找
            //下载文件
            if (url.length() < URL_LENGTH){
                logger.error("url长度小于8。");
                return ;
            }
            String str = url.substring(0, URL_LENGTH);
            FtpServerConfigUtil ftpServerConfigUtil = new FtpServerConfigUtil();
            String filePath = ftpServerConfigUtil.getPathName() + File.separator + str + File.separator + url;
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(url.getBytes("utf-8"), "ISO8859-1"));
            os = response.getOutputStream();
            FTPClientTemplate FTPClient = FTPClientTemplate.getInstance();
            boolean flags = FTPClient.get(filePath, os);
            if(flags == false){
                logger.error("ftp文件下载失败。");
            }
        } catch (Exception e) {
            logger.error("下载导出文件", e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error("下载导出文件：os关闭失败", e);
                }
            }
        }
    }

    //从文件名中截取URL_LENGTH长度，得到存储的文件夹
    private static final Integer URL_LENGTH = 8;

    @Autowired
    private NemoQueryService nemoQueryService;
    @Autowired
    private ExportService exportService;
    private static final Logger logger = LoggerFactory.getLogger(NemoQueryController.class);

}
