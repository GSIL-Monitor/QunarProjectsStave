package com.qunar.fintech.plat.admin.query.export;

import java.io.InputStream;

/**
 * 上传文件参数
 */
public class UploadFileParam {
    private InputStream inputStream;
    private String fileName;
    private String fileType;

    public UploadFileParam() {
    }

    public UploadFileParam(InputStream inputStream, String fileName, String fileType) {
        this.inputStream = inputStream;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
