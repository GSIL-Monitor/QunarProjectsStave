package com.qunar.fintech.plat.admin.query.service;

import com.qunar.fintech.plat.admin.query.vo.CantUseReasonDto;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;

import java.util.List;

public interface QueryCantUseReasonService {

    /**
     * 查询拿去花不可用原因
     * @return
     */
    List<CantUseReasonDto> queryCantUseReason(QueryDto queryDto);
}
