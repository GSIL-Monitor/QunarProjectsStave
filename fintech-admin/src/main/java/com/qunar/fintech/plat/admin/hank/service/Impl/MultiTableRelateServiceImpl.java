package com.qunar.fintech.plat.admin.hank.service.Impl;

import com.qunar.fintech.plat.admin.hank.entity.MultiTableRelate;
import com.qunar.fintech.plat.admin.hank.mapper.MultiTableRelateMapper;
import com.qunar.fintech.plat.admin.hank.service.MultiTableRelateService;
import com.qunar.fintech.plat.admin.support.exception.BDException;
import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.pay.g2.utils.common.CheckUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tongda.sun on 2018/12/09  17:29.
 * Description:
 */
@Service
public class MultiTableRelateServiceImpl implements MultiTableRelateService {

    @Override
    public MultiTableRelate selectById(Long id) {
        if(CheckUtils.isEmpty(id)){
            throw new BDException("MultiTableRelateService.selectById id参数为空");
        }
        return mapper.selectById(id);
    }

    @Override
    public List<MultiTableRelate> selectByTaskType(String taskType) {
        logger.info("MultiTableRelateService.selectByTaskType# taskType={}",taskType);
        if(CheckUtils.isEmpty(taskType)){
            throw new BDException("MultiTableRelateService.selectByTaskType# taskType 参数异常");
        }
        return mapper.selectByTaskType(taskType.trim());
    }

    @Override
    public List<MultiTableRelate> select(Query query) {
        logger.info("MultiTableRelateService.select# query={}",query);
        query.put("taskType",query.get("taskType").toString().trim());
        return mapper.select(query);
    }

    @Override
    public int count(Query query) {
        logger.info("MultiTableRelateService.count# query={}",query);
        query.put("taskType",query.get("taskType").toString().trim());
        return mapper.count(query);
    }

    @Override
    public int insertSelective(MultiTableRelate record) {
        if(CheckUtils.isEmpty(record)){
            throw new BDException("MultiTableRelateService.insertSelective# record 参数异常");
        }
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(MultiTableRelate record) {
        if(CheckUtils.isEmpty(record)){
            throw new BDException("MultiTableRelateService.insertSelective# record 参数异常");
        }
        if(CheckUtils.isEmpty(record.getId())){
            throw new BDException("MultiTableRelateService.insertSelective# recordid. 参数异常");
        }
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Autowired
    private MultiTableRelateMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(MultiTableRelateServiceImpl.class);
}
