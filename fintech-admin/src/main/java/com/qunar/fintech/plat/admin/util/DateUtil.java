package com.qunar.fintech.plat.admin.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 当前时间  去掉  毫秒时间
     * @return
     */
    public static Date getDBNow(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


}
