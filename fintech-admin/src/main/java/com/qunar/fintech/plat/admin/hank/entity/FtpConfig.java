package com.qunar.fintech.plat.admin.hank.entity;

import java.io.Serializable;

public class FtpConfig implements Serializable {

    private static final long serialVersionUID = -8772419192140213645L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 接收方
     */
    private String receiver;

    /**
     * 协议类型：FTP 或 SFTP
     */
    private String protocol;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 主机
     */
    private String host;

    /**
     * 端口号
     */
    private Integer port;

    /**
     * 路径
     */
    private String path;

    /**
     * 文件夹创建模式 1 按天   2按月
     */
    private Integer dirMode;

    /**
     * 压缩模式 1 不压缩 2压缩
     */
    private Integer compressMode;

    /**
     * 文件加密模式
     */
    private String encryptMode;

    /**
     * 根路径
     */
    private String root;

    /**
     * 配置状态 1有效 2无效
     */
    private Integer status = 1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver.trim();
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path.trim();
    }

    public Integer getDirMode() {
        return dirMode;
    }

    public void setDirMode(Integer dirMode) {
        this.dirMode = dirMode;
    }

    public Integer getCompressMode() {
        return compressMode;
    }

    public void setCompressMode(Integer compressMode) {
        this.compressMode = compressMode;
    }

    public String getEncryptMode() {
        return encryptMode;
    }

    public void setEncryptMode(String encryptMode) {
        this.encryptMode = encryptMode.trim();
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FtpConfig{");
        sb.append("id=").append(id);
        sb.append(", taskType='").append(taskType).append('\'');
        sb.append(", receiver='").append(receiver).append('\'');
        sb.append(", protocol='").append(protocol).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append(", path='").append(path).append('\'');
        sb.append(", dirMode=").append(dirMode);
        sb.append(", compressMode=").append(compressMode);
        sb.append(", encryptMode='").append(encryptMode).append('\'');
        sb.append(", root='").append(root).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}