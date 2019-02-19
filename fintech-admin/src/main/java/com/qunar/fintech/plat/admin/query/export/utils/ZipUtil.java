package com.qunar.fintech.plat.admin.query.export.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    public final static Logger log = LoggerFactory.getLogger(ZipUtil.class);

    public static void zipDir(String dir, String targetZipFile, boolean isDelete) {
        File dirFile = new File(dir);
        File[] files = dirFile.listFiles();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(targetZipFile));
            if (null == files) {
                throw new RuntimeException("zipDir: files is null");
            }
            for (int i = 0; i < files.length; i++) {
                zipFile(files[i], zos);
            }
            if (isDelete) {
                FileUtils.deleteDirectory(dirFile);
                log.info("删除文件夹：" + dir);
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    log.error("zipDir", e);
                }
            }
        }

    }

    public static void zipFile(File inputFile,ZipOutputStream zos) {
        try {
            if (inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream fis = new FileInputStream(inputFile);
                    log.info("正在压缩文件：" + inputFile.getName());
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    zos.putNextEntry(entry);
                    // 向压缩文件中输出数据   
                    int size = 0;
                    byte[] buffer = new byte[1024];
                    while ((size = fis.read(buffer, 0, buffer.length)) > 0) {
                        zos.write(buffer, 0, size);
                    }
                    // 关闭创建的流对象
                    zos.closeEntry();
                    if(fis!=null){
                        fis.close();
                    }
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        if (files == null) return;
                        Preconditions.checkState(files != null, inputFile.getAbsolutePath() + " is empty directory");
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], zos);
                        }
                    } catch (Exception e) {
                        log.error("zipDir exception", e);
                        throw new RuntimeException("zipDir exception", e);
                    }
                }
            }
        } catch (Exception e) {
            log.error("zipDir exception", e);
            throw new RuntimeException("zipDir exception", e);
        }
    }
}
