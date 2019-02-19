package com.qunar.fintech.plat.admin.support.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.io.BaseEncoding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Iterator;
import java.util.Map;

/**
 * @author shiliang.gao
 * @since 2016-01-29
 */
public final class JsonUtil {

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

    public static String toJson(Object object){
        return gson.toJson(object);
    }

    /**
     * 将json字符串转换为map（若map的value为对象，则取该对象的json字符串）
     * @param jsonStr
     * @return
     */
    public static Map<String,String> jsonToMap(String jsonStr){
        Map<String,String> resultMap = Maps.newHashMap();
        if (Strings.isNullOrEmpty(jsonStr)){
            return resultMap;
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Iterator ir = jsonObject.keySet().iterator();
        while(ir.hasNext()){
            String key = ir.next().toString();
            resultMap.put(key,jsonObject.getString(key));
        }
        return resultMap;
    }

    public static String base64Encode(String source) {
        return BaseEncoding.base64().encode(source.getBytes(Charsets.UTF_8));
    }

    public static String base64Decode(String source) {
        return new String(BaseEncoding.base64().decode(source), Charsets.UTF_8);
    }

}
