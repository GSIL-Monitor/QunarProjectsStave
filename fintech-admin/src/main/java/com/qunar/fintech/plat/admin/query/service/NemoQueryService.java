package com.qunar.fintech.plat.admin.query.service;

import com.qunar.fintech.plat.admin.query.exception.FppException;
import com.qunar.fintech.plat.admin.query.vo.NemoQueryVo;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;

public interface NemoQueryService {

    QueryResponse<NemoQueryVo> selectByPlatId(NemoQueryVo nemoQueryVo) throws FppException;
}
