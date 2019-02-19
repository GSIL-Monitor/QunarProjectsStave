package com.qunar.fintech.plat.admin.query.service;

import com.qunar.fintech.plat.admin.query.vo.QueryOcrDto;
import com.qunar.fintech.plat.admin.query.vo.QueryOcrReq;

import java.util.List;
import java.util.Map;

public interface OcrService {

    String queryOcrList(QueryOcrReq queryOcrReq);

    String queryOcrImg(String token);
}
