package com.qunar.fintech.plat.admin.query.export;

import com.alibaba.dubbo.common.utils.Assert;
import com.qunar.fintech.plat.admin.query.enums.nemo.ExportStatusEnum;
import com.qunar.fintech.plat.admin.query.export.utils.ExcelExportEngine;
import com.qunar.fintech.plat.admin.query.export.utils.FTPClientTemplate;
import com.qunar.fintech.plat.admin.query.export.utils.FtpServerConfigUtil;
import com.qunar.fintech.plat.admin.query.export.utils.ZipUtil;
import com.qunar.fintech.plat.admin.query.utils.RandomUtils;
import com.qunar.fintech.plat.admin.query.vo.NemoQueryVo;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import com.qunar.fintech.plat.admin.system.dao.entity.ExportFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;

public class ExportTask<Q extends NemoQueryVo, T extends ExportFile> implements Runnable {

    protected ExportTaskParam<Q> exportTaskParam;

    protected String fileSeq;

    protected String realPath;

    public ExportTask(ExportTaskParam<Q> exportTaskParam) {
        this.exportTaskParam = exportTaskParam;
        this.realPath = FtpServerConfigUtil.getLoclPathName();
        this.fileSeq = RandomUtils.generateFileSeq();
    }

    /**
     * 批量数据导出处理
     */
    @Override
    public void run() {
        try {
            exportCreate(exportTaskParam.getUserId());
            String dir = realPath + File.separator + fileSeq;
            File dirfile = new File(dir);
            if (!dirfile.exists()) {
                dirfile.mkdirs();
            }
            Long st = System.currentTimeMillis();
            exportAfterStart();
            //解析上传的文件
            log.info("导出文件开始:" + dir);
            if(exportTaskParam.getUploadFileParam()!=null){
                //处理文件
                fileTaskDeal(dir, exportTaskParam.getUploadFileParam());
            }
            String localFile = dir + ".zip";
            log.info("压缩文件夹:" + dir);
            ZipUtil.zipDir(dir, localFile, true);
            log.info("压缩文件夹完成:" + dir);
            exportAfterSucc();
            log.info("excel导出成功fileSeq:" + fileSeq);
            //上传导出的文件到ftp服务器上
            String folderName = fileSeq.substring(0, 8);
            FtpServerConfigUtil ftpServerConfigUtil = new FtpServerConfigUtil();
            String serverFilePath = ftpServerConfigUtil.getPathName() + File.separator + folderName;
            log.info("准备链接：");
            String fileNmae = fileSeq+ ".zip";
            FTPClientTemplate FTPClient =  FTPClientTemplate.getInstance();
            boolean flags = FTPClient.put(serverFilePath, fileNmae, localFile);
            if (flags == false){
                log.error("：文件上传ftp服务器失败！");
            }
            //删除本地文件
            File delfile = new File(localFile);
            delfile.delete();
            Long et = System.currentTimeMillis();
            log.info("fileSeq:" + fileSeq + ";导出总共耗时：" + (et - st));
        } catch (Exception e) {
            log.error("导出文件失败:{}", e);
            try {
                exportAfterFail();
            }catch (Exception ex){
                log.error("更新导出失败异常:{}", e);
            }
        }
    }

    /**
     * 传的TXT文件进行解析，按行查询
     * @param uploadFileParam
     */
    private void  fileTaskDeal(String dir, UploadFileParam uploadFileParam){
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream out = null;
        try{
            //创建导出的Excel文件
            ExcelExportEngine engine = new ExcelExportEngine();
            String filePathName = dir + File.separator + fileSeq + ".xlsx";
            File file = new File(filePathName);
            out = new FileOutputStream(file);
            engine.initExcel(exportTaskParam.getPojoClass());
            engine.createSheet("sheet");
            //处理传入的文件
            isr = new InputStreamReader(uploadFileParam.getInputStream());
            br = new BufferedReader(isr);
            String param = null;
            QueryResponse<NemoQueryVo> res = null;
            NemoQueryVo nemoQueryVo = exportTaskParam.getQuery().getQuery();
            Integer queryNumber = 0;
            //按行读取数据
            while((param = br.readLine()) != null){
                //查询
                if(param == null || "".equals(param)){
                    continue;
                }
                switch (nemoQueryVo.getExportType()) {
                    case 1:
                        //上传文件条件为平台id
                        nemoQueryVo.setPlatOpenId(param);
                        break;
                    case 2:
                        //上传文件条件为用户ID
                        nemoQueryVo.setOriginUserId(param);
                        break;
                    case 3:
                        //上传文件条件为会员ID
                        nemoQueryVo.setCustomerId(param);
                        break;
                    case 4:
                        //上传文件条件为TppId
                        nemoQueryVo.setTppOpenId(param);
                        break;
                    default:
                        break;
                }
                res = exportTaskParam.getNemoQueryService().selectByPlatId(nemoQueryVo);
                queryNumber++;
                //每查询数据库1000次，休眠500毫秒,防止过于频繁的访问数据库
                if(queryNumber > MAX_QUERY_NUMBER){
                    queryNumber = 0;
                    Thread.sleep(SLEEP_TIME);
                }
                //数据处理
                if (res != null) {
                    engine.dealDate(res.getRows());
                }
            }
            //写入文件
            engine.getWorkbook().write(out);
        } catch (Exception e){
            log.error("fileTaskDeal file deal error!", e);
        } finally {
            if (exportTaskParam.getUploadFileParam().getInputStream() != null) {
                try {
                    //文件流关闭
                    exportTaskParam.getUploadFileParam().getInputStream().close();
                }catch (IOException ex){
                    log.error("文件流关闭失败 {}", ex);
                }
            }
            if (br != null) {
                try{
                    br.close();
                }catch (IOException e) {
                    log.error("close BufferedWriter Stream error, ", e);
                }
            }
            if (isr != null) {
                try{
                    isr.close();
                }catch (IOException e) {
                    log.error("close InputStreamReader Stream error, ", e);
                }
            }
            if (out != null) {
                try{
                    out.close();
                }catch (IOException e) {
                    log.error("close OutputStream Stream error, ", e);
                }
            }
        }
    }

    /**
     * 根据fileSeq查询导出任务的信息
     * @return
     */
    protected T getDBExportFile() {
        T t = (T) exportTaskParam.getExportService().selectByFileSeq(fileSeq);
        Assert.notNull(t, "ExportFile 查询为空");
        return t;
    }

    /**
     * 创建导出数据
     */
    protected void exportCreate(Long userId) {
        exportTaskParam.getExportService().insertExportFile(fileSeq, userId,
                exportTaskParam.getQuery().getQuery().getExportType());
    }

    /**
     * 更新导出开始
     */
    protected void exportAfterStart() {
        T t = getDBExportFile();
        t.setStartTime(new Date());
        exportTaskParam.getExportService().updateExportFileByPrimaryKey(t, ExportStatusEnum.EXPORTING);
    }

    /**
     * 更新导出成功
     */
    protected void exportAfterSucc() {
        exportTaskParam.getExportService().updateExportFileByPrimaryKey(getDBExportFile(), ExportStatusEnum.SUCCESS);
    }

    /**
     * 更新导出失败
     */
    protected void exportAfterFail() {
        exportTaskParam.getExportService().updateExportFileByPrimaryKey(getDBExportFile(), ExportStatusEnum.FAIL);
    }



    public String getFileSeq() {
        return fileSeq;
    }

    public void setFileSeq(String fileSeq) {
        this.fileSeq = fileSeq;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    //连续查询数据库最大次数
    private static final Integer MAX_QUERY_NUMBER = 1000;
    //休眠时间
    private static final Integer SLEEP_TIME = 500;

    public final static Logger log = LoggerFactory.getLogger(ExportTask.class);

}
