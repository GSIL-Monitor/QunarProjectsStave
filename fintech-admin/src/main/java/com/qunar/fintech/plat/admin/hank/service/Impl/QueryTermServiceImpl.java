package com.qunar.fintech.plat.admin.hank.service.Impl;

import com.qunar.fintech.plat.admin.hank.entity.QueryTerm;
import com.qunar.fintech.plat.admin.hank.mapper.QueryTermMapper;
import com.qunar.fintech.plat.admin.hank.service.QueryTermService;
import com.qunar.fintech.plat.admin.support.exception.BDException;
import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.pay.g2.utils.common.CheckUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tongda.sun on 2018/12/08  16:45.
 * Description:
 */
@Service
public class QueryTermServiceImpl implements QueryTermService {

    @Override
    public QueryTerm selectById(Long id) {
        if(CheckUtils.isEmpty(id)){
            throw new BDException("QueryTermService.selectById id参数为空");
        }
        return mapper.selectById(id);
    }

    @Override
    public List<QueryTerm> selectByTaskTypeAndQueryMode(String taskType, Integer queryMode) {
        logger.info("QueryTermService.selectByTaskTypeAndQueryMode# taskType={} queryMode={}",taskType,queryMode);
        if(CheckUtils.isEmpty(taskType)){
            throw new BDException("QueryTermService.selectByTaskTypeAndQueryMode # taskType 参数异常");
        }
        if(CheckUtils.isEmpty(queryMode)){
            throw new BDException("QueryTermService.selectByTaskTypeAndQueryMode # queryMode 参数异常");
        }
        return mapper.selectByTaskTypeAndQueryMode(taskType.trim(),queryMode);
    }

    @Override
    public List<QueryTerm> select(Query query) {
        logger.info("QueryTermService.select# query={}",query);
        query.put("taskType",query.get("taskType").toString().trim());
        return mapper.select(query);
    }

    @Override
    public int count(Query query) {
        logger.info("QueryTermService.count# query={}",query);
        query.put("taskType",query.get("taskType").toString().trim());
        return mapper.count(query);
    }

    @Override
    public int insertSelective(QueryTerm record) {
        if(CheckUtils.isEmpty(record)){
            throw new BDException("QueryTermService.insertSelective # record 参数异常");
        }
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(QueryTerm record) {
        if(CheckUtils.isEmpty(record)){
            throw new BDException("QueryTermService.updateByPrimaryKeySelective # record 参数异常");
        }
        if(CheckUtils.isEmpty(record.getId())){
            throw new BDException("QueryTermService.updateByPrimaryKeySelective # record.id 参数异常");
        }
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Autowired
    private QueryTermMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(QueryTermServiceImpl.class);

}
