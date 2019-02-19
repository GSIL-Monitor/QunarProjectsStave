package com.qunar.fintech.plat.admin.query.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class ExecutorsThread {
	
	private final static int POOL_SIZE = 5;
	
	private ExecutorsThread() {
	}
	 
	private static ExecutorsThread executors=null;  
	
	private static ScheduledExecutorService exec = Executors.newScheduledThreadPool(POOL_SIZE);;
	
    public synchronized  static ExecutorsThread getInstance() {  
         if (executors == null) {    
        	 executors = new ExecutorsThread();  
         }    
        return executors;  
    }  
    
    public ScheduledExecutorService getScheduledExecutorService(){
    	return exec;
    }

}
