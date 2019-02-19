package com.qunar.fintech.plat.admin.contract.service;

import com.qunar.fintech.plat.admin.contract.dto.QueryUserProductChangeRecord;
import com.qunar.fintech.plat.admin.contract.dto.QueryUserProductDto;

import java.util.List;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-10-30
 * Time: 20:23
 */
public interface UserProductService {

    List<QueryUserProductChangeRecord> queryUserProductChangeInfo(QueryUserProductDto reqDto);
}
