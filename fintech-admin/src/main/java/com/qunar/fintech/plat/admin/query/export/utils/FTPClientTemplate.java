package com.qunar.fintech.plat.admin.query.export.utils;

import com.qunar.fintech.plat.admin.query.exception.FTPClientException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.SocketException;


public class FTPClientTemplate {

    private static final Logger logger = LoggerFactory.getLogger(FTPClientTemplate.class);
    private final static FTPClientTemplate ftp = new FTPClientTemplate();
    static{
        ftp.setHost(FtpServerConfigUtil.getFtpServer());
        ftp.setPort(Integer.valueOf(FtpServerConfigUtil.getPort()));
        ftp.setUsername(FtpServerConfigUtil.getUsername());
        ftp.setPassword(FtpServerConfigUtil.getPassword());
        ftp.setBinaryTransfer(true);
        ftp.setPassiveMode(true);
        ftp.setEncoding(FtpServerConfigUtil.getCharsetName());
    }
    public static FTPClientTemplate getInstance(){
        return ftp;
    }

    private String         host;
    private int            port;   
    private String         username;   
    private String         password;   
  
    private boolean        binaryTransfer = true;   
    private boolean        passiveMode    = true;   
    private String          encoding       = "UTF-8";
    private int            clientTimeout  = 3000;   
    private boolean flag=true;

    public String getHost() {   
        return host;   
    }   
  
    public void setHost(String host) {   
        this.host = host;   
    }   
  
    public int getPort() {   
        return port;   
    }   
  
    public void setPort(int port) {   
        this.port = port;   
    }   
  
    public String getUsername() {   
        return username;   
    }   
  
    public void setUsername(String username) {   
        this.username = username;   
    }   
  
    public String getPassword() {   
        return password;   
    }   
  
    public void setPassword(String password) {   
        this.password = password;   
    }   
  
    public boolean isBinaryTransfer() {   
        return binaryTransfer;   
    }   
  
    public void setBinaryTransfer(boolean binaryTransfer) {   
        this.binaryTransfer = binaryTransfer;   
    }   
  
    public boolean isPassiveMode() {   
        return passiveMode;   
    }   
  
    public void setPassiveMode(boolean passiveMode) {   
        this.passiveMode = passiveMode;   
    }   
  
    public String getEncoding() {   
        return encoding;   
    }   
  
    public void setEncoding(String encoding) {   
        this.encoding = encoding;   
    }   
  
    public int getClientTimeout() {   
        return clientTimeout;   
    }   
  
    public void setClientTimeout(int clientTimeout) {   
        this.clientTimeout = clientTimeout;   
    }   
  
    /**  
     * 返回一个FTPClient实例  
     *   
     * @throws FTPClientException  
     */  
    private FTPClient getFTPClient() throws FTPClientException {
        FTPClient ftpClient = new FTPClient(); //构造一个FtpClient实例   
        ftpClient.setControlEncoding(encoding); //设置字符集
        connect(ftpClient); //连接到ftp服务器   
        logger.info("ftp连接成功");
        //设置为passive模式   
        if (passiveMode) {
            ftpClient.enterLocalPassiveMode();
        }
        setFileType(ftpClient); //设置文件传输类型   
        try {
            ftpClient.setSoTimeout(clientTimeout);   
        } catch (SocketException e) {   
            throw new FTPClientException("Set timeout error.", e);   
        }   
        return ftpClient;
    }   
  
    /**  
     * 设置文件传输类型  
     *   
     * @throws FTPClientException  
     * @throws IOException  
     */  
    private void setFileType(FTPClient ftpClient) throws FTPClientException {   
        try {   
            if (binaryTransfer) {   
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);   
            } else {   
                ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE);   
            }   
        } catch (IOException e) {   
            throw new FTPClientException("Could not to set file type.", e);   
        }   
    }   
  
    /**  
     * 连接到ftp服务器  
     *   
     * @param ftpClient  
     * @return 连接成功返回true，否则返回false  
     * @throws FTPClientException  
     */  
    public boolean connect(FTPClient ftpClient) throws FTPClientException {   
        try {   
            ftpClient.connect(host, port);
            // 连接后检测返回码来校验连接是否成功
            int reply = ftpClient.getReplyCode();
            if (FTPReply.isPositiveCompletion(reply)) {
                //登陆到ftp服务器   
                if (ftpClient.login(username, password)) {   
                    setFileType(ftpClient);   
                    return true;   
                }
            } else {   
                ftpClient.disconnect();
                throw new FTPClientException("FTP server refused connection.");   
            }   
        } catch (IOException e) {   
            if (ftpClient.isConnected()) {   
                try {   
                    ftpClient.disconnect(); //断开连接   
                } catch (IOException e1) {   
                    throw new FTPClientException("Could not disconnect from server.", e);   
                }   
            }
            throw new FTPClientException("Could not connect to server.", e);   
        }   
        return false;   
    }   
  
    /**  
     * 断开ftp连接  
     *   
     * @throws FTPClientException  
     */  
    private void disconnect(FTPClient ftpClient) throws FTPClientException {   
        try {   
            ftpClient.logout();   
            if (ftpClient.isConnected()) {   
                ftpClient.disconnect();   
            }   
        } catch (IOException e) {   
            throw new FTPClientException("Could not disconnect from server.", e);   
        }   
    }   
  
    //---------------------------------------------------------------------   
    // public method   
    //---------------------------------------------------------------------
    /**
     * 上传一个本地文件到远程指定文件
     * @param serverFile 服务器端文件路径(不包括文件名的完整路径)
     * @param fileName 上传文件名(包含扩展名的完整文件名)
     * @param localFile 本地文件名(包括完整路径)
     * @return 成功时，返回true，失败返回false
     * @throws FTPClientException
     */
    public boolean put(String serverFile, String fileName, String localFile) throws FTPClientException {
        return put(serverFile, fileName, localFile, false);
    }   
  
    /**  
     * 上传一个本地文件到远程指定文件  
     *   
     * @param serverFile 服务器端文件路径(不包括文件名的完整路径)
     * @param fileName 上传文件名(包含扩展名的完整文件名)
     * @param localFile 本地文件名(包括完整路径)  
     * @param delFile 成功后是否删除文件  
     * @return 成功时，返回true，失败返回false  
     * @throws FTPClientException
     */
    public boolean put(String serverFile, String fileName, String localFile, boolean delFile) throws FTPClientException {
        FTPClient ftpClient = null;   
        InputStream input = null;   
        try {   
            ftpClient = getFTPClient();

            //路径不存在则创建
            ftpClient.makeDirectory(serverFile);
            int statusCode1 = ftpClient.getReplyCode();
            logger.info("ftp文件上传路径信息状态码 ： " + statusCode1);

            String serverUploadPath = serverFile + File.separator + fileName;
            // 处理传输
            input = new FileInputStream(localFile);
            if(!ftpClient.storeFile(serverUploadPath, input)){
                return false;
            }
            int statusCode2 = ftpClient.getReplyCode();
            logger.info("ftp文件上传文件状态码 : " + statusCode2);
            logger.debug("put " + localFile);
            input.close();   
            logger.debug("delete " + localFile);
            return true;   
        } catch (FileNotFoundException e) {   
            throw new FTPClientException("local file not found.", e);
        } catch (IOException e) {   
            throw new FTPClientException("Could not put file to server.", e);
        } finally {
            try {   
                if (input != null) {   
                    input.close();   
                }   
            } catch (Exception e) {   
                throw new FTPClientException("Couldn't close FileInputStream.", e);
            }   
            if (ftpClient != null) {   
                disconnect(ftpClient); //断开连接   
            }
            if (delFile) {
                (new File(localFile)).delete();
            }
        }   
    }   
  
    /**  
     * 下载一个远程文件到本地的指定文件  
     *   
     * @param serverFile 服务器端文件名(包括完整路径)  
     * @param localFile 本地文件名(包括完整路径)  
     * @return 成功时，返回true，失败返回false  
     * @throws FTPClientException  
     */  
    public boolean get(String serverFile, String localFile) throws FTPClientException {   
        return get(serverFile, localFile, false);   
    }   
  
    /**  
     * 下载一个远程文件到本地的指定文件  
     *   
     * @param serverFile 服务器端文件名(包括完整路径)  
     * @param localFile 本地文件名(包括完整路径)  
     * @return 成功时，返回true，失败返回false  
     * @throws FTPClientException  
     */  
    public boolean get(String serverFile, String localFile, boolean delFile) throws FTPClientException {   
        OutputStream output = null;   
        try {   
            output = new FileOutputStream(localFile);   
            return get(serverFile, output, delFile);   
        } catch (FileNotFoundException e) {   
            throw new FTPClientException("local file not found.", e);   
        } finally {   
            try {   
                if (output != null) {   
                    output.close();   
                }   
            } catch (IOException e) {   
                throw new FTPClientException("Couldn't close FileOutputStream.", e);   
            }   
        }   
    }   
       
    /**  
     * 下载一个远程文件到指定的流  
     * 处理完后记得关闭流  
     *   
     * @param serverFile  
     * @param output  
     * @return  
     * @throws FTPClientException  
     */  
    public boolean get(String serverFile, OutputStream output) throws FTPClientException {   
        return get(serverFile, output, false);   
    }   
    
    /**  
     * 下载一个远程文件到指定的流  
     * 处理完后记得关闭流  
     *   
     * @param serverFile  
     * @param output  
     * @param delFile  
     * @return  
     * @throws FTPClientException  
     */  
    public boolean get(String serverFile, OutputStream output, boolean delFile) throws FTPClientException {   
        FTPClient ftpClient = null;   
        try {   
            ftpClient = getFTPClient();   
            // 处理传输
            boolean flags = ftpClient.retrieveFile(serverFile, output);
            int n = ftpClient.getReplyCode();
            logger.info("ftp文件下载状态及状态码 ： " + flags + n);
            if (delFile) { // 删除远程文件   
                ftpClient.deleteFile(serverFile);   
            }   
            return true;   
        } catch (IOException e) {   
            throw new FTPClientException("Couldn't get file from server.", e);   
        } finally {   
            if (ftpClient != null) {   
                disconnect(ftpClient); //断开连接   
            }   
        }   
    }   
       
    /**  
     * 从ftp服务器上删除一个文件  
     *   
     * @param delFile  
     * @return  
     * @throws FTPClientException  
     */  
    public boolean delete(String delFile) throws FTPClientException {   
        FTPClient ftpClient = null;   
        try {   
            ftpClient = getFTPClient();   
            ftpClient.deleteFile(delFile);   
            return true;   
        } catch (IOException e) {   
            throw new FTPClientException("Couldn't delete file from server.", e);   
        } finally {   
            if (ftpClient != null) {   
                disconnect(ftpClient); //断开连接   
            }   
        }   
    }   
       
    /**  
     * 批量删除  
     * @param delFiles
     * @return  
     * @throws FTPClientException  
     */  
    public boolean delete(String[] delFiles) throws FTPClientException {   
        FTPClient ftpClient = null;   
        try {   
            ftpClient = getFTPClient();   
            for (String s : delFiles) {   
                ftpClient.deleteFile(s);   
            }   
            return true;   
        } catch (IOException e) {   
            throw new FTPClientException("Couldn't delete file from server.", e);   
        } finally {   
            if (ftpClient != null) {   
                disconnect(ftpClient); //断开连接   
            }   
        }   
    }   
  
    /**  
     * 列出远程默认目录下所有的文件  
     * @return 远程默认目录下所有文件名的列表，目录不存在或者目录下没有文件时返回0长度的数组
     * @throws FTPClientException  
     */  
    public String[] listNames() throws FTPClientException {   
        return listNames(null);   
    }   
  
    /**  
     * 列出远程目录下所有的文件  
     *   
     * @param remotePath 远程目录名  
     * @return 远程目录下所有文件名的列表，目录不存在或者目录下没有文件时返回0长度的数组  
     * @throws FTPClientException  
     */  
    public String[] listNames(String remotePath) throws FTPClientException {   
        FTPClient ftpClient = null;   
        try {   
            ftpClient = getFTPClient();   
            String[] listNames = ftpClient.listNames(remotePath);   
            return listNames;   
        } catch (IOException e) {   
            throw new FTPClientException("列出远程目录下所有的文件时出现异常", e);   
        } finally {   
            if (ftpClient != null) {   
                disconnect(ftpClient); //断开连接   
            }   
        }   
    }   
    public boolean isExist(String remoteFilePath)throws FTPClientException{
        FTPClient ftpClient = null;
    	try{
    		flag = false;
    		ftpClient = getFTPClient();
    		File file=new File(remoteFilePath);
    		String remotePath=remoteFilePath.substring(0,(remoteFilePath.indexOf(file.getName())-1));
    		String[] listNames = ftpClient.listNames(remotePath);   
    		System.out.println(remoteFilePath);
    		for(int i=0;i<listNames.length;i++){
    			if(remoteFilePath.equals(listNames[i])){
    				flag=true;
    				System.out.println("文件:"+file.getName()+"已经存在了");
    				break;
    			}else {
    				flag=false;
    			}
    		}
    	} catch (IOException e) {
            throw new FTPClientException("查询文件是否存在文件时出现异常", e);   
        } finally {   
            if (ftpClient != null) {   
                disconnect(ftpClient); //断开连接   
            }   
        }   
        return flag;
    }
    

}
