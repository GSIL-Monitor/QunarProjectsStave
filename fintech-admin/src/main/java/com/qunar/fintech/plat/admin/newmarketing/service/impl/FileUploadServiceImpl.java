package com.qunar.fintech.plat.admin.newmarketing.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.Lists;
import com.qunar.fintech.plat.admin.newmarketing.exception.MarketingExceptionCode;
import com.qunar.fintech.plat.admin.newmarketing.service.FileUploadService;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.utils.base.StringUtils;
import org.javaswift.joss.client.factory.AccountConfig;
import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.client.factory.AuthenticationMethod;
import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author qun.shi
 * @since 2019-02-11 10:52 AM
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    private AccountConfig accountConfig;

    @Value("${marketing.swift.account}")
    private String account;

    @Value("${marketing.swift.username}")
    private String username;

    @Value("${marketing.swift.password}")
    private String password;

    @Value("${marketing.swift.authurl}")
    private String authurl;

    @Value("${marketing.swift.container.userInfoFile}")
    private String userInfoFileContainerName;

    private static final AtomicBoolean userInfoFileContainerExist = new AtomicBoolean(false);

    private Supplier<Container> userInfoContainer = Suppliers.memoize(new Supplier<Container>() {
        @Override
        public Container get() {
            Account account = new AccountFactory(accountConfig).createAccount();
            Container ctn = account.getContainer(userInfoFileContainerName);

            if (!userInfoFileContainerExist.get() && !ctn.exists()) {
                ctn.create();
                userInfoFileContainerExist.set(true);
            }
            return ctn;
        }
    });

    /**
     *  为每一个券code生成一个容器，存放发券文件
     */
    @Override
    public void createContainer(String name){
        Account account = new AccountFactory(accountConfig).createAccount();
        Container ctn = account.getContainer(name);

        if (!ctn.exists()) {
            ctn.create();
        }
    }

    /**
     * 保存用户信息文件
     */
    @Override
    public void saveUserInfoFile(MultipartFile file,String fileName) {
        StoredObject storedObject = userInfoContainer.get().getObject(fileName);

        try {
            storedObject.uploadObject(file.getInputStream());
        } catch (IOException e) {
            LOGGER.error("上传文件失败，e={}", e);
            throw new BusiException(MarketingExceptionCode.UPLOAD_FILE_FAIL);
        }
    }

    /**
     * 下载用户信息文件
     */
    @Override
    public InputStream downloadUserInfoFile(String fileName) {
        StoredObject storedObject = userInfoContainer.get().getObject(fileName);
        return storedObject.downloadObjectAsInputStream();
    }

    /**
     * 下载用户信息文件
     */
    @Override
    public InputStream downloadGrantCouponFile(String containerName,String fileName) {
        Account account = new AccountFactory(accountConfig).createAccount();
        Container ctn = account.getContainer(containerName);
        StoredObject storedObject = ctn.getObject(fileName);
        return storedObject.downloadObjectAsInputStream();
    }

    /**
     * 获取所有的发券文件名称
     */
    @Override
    public List<String> getAllGrantCouponFileName(String containerName){
        Account account = new AccountFactory(accountConfig).createAccount();
        Container ctn = account.getContainer(containerName);

        List<String> fileNames = Lists.newArrayList();
        Collection<StoredObject> objects = ctn.list();
        for (StoredObject storedObject:objects) {
            fileNames.add(storedObject.getName());
        }
        return fileNames;
    }

    @PostConstruct
    public void afterPropertiesSet() {
        Preconditions.checkArgument(StringUtils.isNotBlank(account), " must not be empty");
        Preconditions.checkArgument(StringUtils.isNotBlank(username), " must not be empty");
        Preconditions.checkArgument(StringUtils.isNotBlank(password), " must not be empty");
        Preconditions.checkArgument(StringUtils.isNotBlank(authurl), " must not be empty");
        Preconditions.checkArgument(StringUtils.isNotBlank(userInfoFileContainerName), " must not be empty");

        accountConfig = new AccountConfig();
        accountConfig.setUsername(account + ":" + username);
        accountConfig.setPassword(password);
        accountConfig.setAuthUrl(authurl);
        accountConfig.setAuthenticationMethod(AuthenticationMethod.BASIC);
    }
}
