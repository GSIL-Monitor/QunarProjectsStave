package com.qunar.fintech.plat.admin.query.export.utils;

import com.qunar.fintech.plat.admin.query.export.ExportTask;
import com.qunar.fintech.plat.admin.query.utils.ExecutorsThread;

import java.util.concurrent.ScheduledExecutorService;


public class ExcelExportUtil {

	@SuppressWarnings("rawtypes")
	public static void exportExcelThread(ExportTask task){
		ScheduledExecutorService exec = ExecutorsThread.getInstance().getScheduledExecutorService();
		exec.execute(task);
	}

    public static void exportExcelThread(Runnable task){
        ScheduledExecutorService exec = ExecutorsThread.getInstance().getScheduledExecutorService();
        exec.execute(task);
    }

}
