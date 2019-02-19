package com.qunar.fintech.plat.admin.newmarketing.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author qun.shi
 * @since 2019-02-11 10:52 AM
 */
public interface FileUploadService {

    /**
     * 保存用户信息文件
     */
    void saveUserInfoFile(MultipartFile file,String fileName);

    /**
     *  生成一个容器，存放文件
     */
    void createContainer(String name);

    /**
     * 下载用户信息文件
     */
    InputStream downloadUserInfoFile(String fileName);

    /**
     * 下载用户信息文件
     */
    InputStream downloadGrantCouponFile(String containerName,String fileName);

    /**
     * 获取所有的发券文件名称
     */
    List<String> getAllGrantCouponFileName(String containerName);
}
