package com.qunar.fintech.plat.admin.query.utils;

import com.csvreader.CsvReader;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvUtils {

    public static List<List<Object>> readeCsvByIs(InputStream csvIs) {
        List<List<Object>> csvList = new ArrayList<>(); // 用来保存数据
        CsvReader reader = new CsvReader(csvIs,Charset.forName("GBK")); // 一般用这编码读就可以了
        try {
            reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。
            while (reader.readRecord()) { // 逐行读入除表头的数据
                csvList.add(Arrays.<Object>asList(reader.getValues()));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }finally {
            reader.close();
        }
        return csvList;
    }
}
