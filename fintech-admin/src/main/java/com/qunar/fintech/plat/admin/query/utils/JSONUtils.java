package com.qunar.fintech.plat.admin.query.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * json工具类，提供统一的gson对象，使用gson的方法进行转换
 * @author song.shi
 * @since 2015-12-03
 */
public class JSONUtils {
    private static final Gson gson;

    static {
        gson = new GsonBuilder().serializeNulls()         // null 也序列化
                .enableComplexMapKeySerialization()       // 支持Map的key为复杂对象的形式
                .setDateFormat("yyyy-MM-dd HH:mm:ss")     // 时间转化为特定格式 yyyy-MM-dd HH:mm:ss:SSS
                .create();
    }

    public static Gson getGson() {
        return gson;
    }
}
