package com.qunar.fintech.plat.admin.query.service;

import com.qunar.fintech.plat.admin.query.vo.QueryDto;

import java.util.Collection;

/**
 * Created by bob.li on 2015/12/23.
 */
public interface CommonService {

    /**
     * 获取查询征信需要的流水号
     */
    String getQCreditTradeNo();

    /**
     * 如果request中包含mobile则会查询userId填充进去
     * 注：仅限request中mobile等各个Field名称一致的情况
     */
    <Request> Request addUserIdWithCheck(Request request) throws Exception;

    /**
     * 给查询结果添加userName,identity和mobile
     * 通过反射赋值.
     *
     * @throws Exception
     */
    <QueryResult> void addUserNameAndMobile(Collection<QueryResult> result);

    void mobileToId(QueryDto queryDto);

}
