package com.qunar.fintech.plat.admin.hank.service.Impl;

import com.qunar.fintech.plat.admin.hank.entity.FormatField;
import com.qunar.fintech.plat.admin.hank.mapper.FormatFieldMapper;
import com.qunar.fintech.plat.admin.hank.service.FormatFieldService;
import com.qunar.fintech.plat.admin.support.exception.BDException;
import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.pay.g2.utils.common.CheckUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tongda.sun on 2018/12/09  19:10.
 * Description:
 */
@Service
public class FormatFieldServiceImpl implements FormatFieldService {

    @Override
    public FormatField selectById(Long id) {
        if(CheckUtils.isEmpty(id)){
            throw new BDException("FormatFieldService.selectById id参数为空");
        }
        return mapper.selectById(id);
    }

    @Override
    public List<FormatField> selectByTaskTypeAndReceiver(String taskType, String receiver) {
        logger.info("FormatFieldService.selectByTaskTypeAndReceiver# taskType={} receiver={}",taskType,receiver);
        if(CheckUtils.isEmpty(taskType)){
            throw new BDException("FormatFieldService.selectByTaskTypeAndReceiver # taskType 参数异常");
        }
        if(CheckUtils.isEmpty(receiver)){
            throw new BDException("FormatFieldService.selectByTaskTypeAndReceiver # receiver 参数异常");
        }
        return mapper.selectByTaskTypeAndReceiver(taskType.trim(),receiver.trim());
    }

    @Override
    public List<FormatField> select(Query query) {
        logger.info("FormatFieldService.select# query={}",query);
        return mapper.select(query);
    }

    @Override
    public int count(Query query) {
        logger.info("FormatFieldService.count# query={}",query);
        return mapper.count(query);
    }

    @Override
    public int insertSelective(FormatField record) {
        if(CheckUtils.isEmpty(record)){
            throw new BDException("FormatFieldService.insertSelective # receiver 参数异常");
        }
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(FormatField record) {
        if(CheckUtils.isEmpty(record)){
            throw new BDException("FormatFieldService.insertSelective # receiver 参数异常");
        }
        if(CheckUtils.isEmpty(record.getId())){
            throw new BDException("FormatFieldService.insertSelective # receiver.id 参数异常");
        }
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Autowired
    private FormatFieldMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(FormatFieldServiceImpl.class);
}
