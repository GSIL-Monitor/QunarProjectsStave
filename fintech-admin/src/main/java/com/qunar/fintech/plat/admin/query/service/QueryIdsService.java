package com.qunar.fintech.plat.admin.query.service;

import com.qunar.fintech.plat.admin.query.vo.QueryIdDto;

public interface QueryIdsService {

    /**
     * 根据手机号查询uid,openid,platopenid
     * @param mobile
     * @return
     */
    QueryIdDto queryIdsByMobile(String mobile);
}
