package com.qunar.fintech.plat.admin.query.service;


import java.util.List;

/**
 * 信任付查询相关service
 *
 * Created by baron.jiang on 2015/2/4.
 */
public interface IousQueryService {

    List<String> queryUserIdByMobile(String mobile, String productNo);

}
