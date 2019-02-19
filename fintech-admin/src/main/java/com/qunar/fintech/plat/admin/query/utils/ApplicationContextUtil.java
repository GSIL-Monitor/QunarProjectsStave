package com.qunar.fintech.plat.admin.query.utils;

import org.springframework.context.ApplicationContext;

public class ApplicationContextUtil{

	
	private static ApplicationContext context;
	
	private ApplicationContextUtil(){
		
	}
	
	public synchronized static void setApplicationContext(ApplicationContext applicationContext) {
		synchronized (ApplicationContextUtil.class) {
			context = applicationContext;
		}
	}

	public static ApplicationContext getContext() {
		return context;
	}
}
