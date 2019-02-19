package com.qunar.fintech.plat.admin.query.export.utils;

import com.qunar.pay.g2.utils.common.ResourceUtils;

import java.util.Map;
import java.util.Properties;

public class FtpServerConfigUtil {
	    
	    public static Properties PROPERTIES = new Properties(); 
	    
	    private static Map<String,String> map =null;    
	    
	    static {
	        map= ResourceUtils.getResource("ftpservercfg").getMap();
	    }   
	    
	    public static String getFtpServer(){
	    	return map.get("ftp_server");
	    }
	    
	    public static int getPort(){
	    	return Integer.valueOf(map.get("ftp_port"));
	    }
	    
	    public static String getUsername(){
	    	return map.get("ftp_user_name");
	    }
	    
	    public static String getPassword(){
	    	return map.get("ftp_password");
	    }
	    
	    public static  String getCharsetName(){
	    	return map.get("ftp_charset_name");
	    }
	    
	    public static  String getPathName(){
	    	return map.get("ftp_pathname");
	    }

	    public static  String getLoclPathName(){
		return map.get("ftp_local_path");
	}
	    
	    public static  String getProperty(String key){
	    	return map.get(key);
	    }

}
