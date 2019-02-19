package com.qunar.fintech.plat.admin.hank.service.Impl;

import com.qunar.fintech.plat.admin.hank.entity.TppConfig;
import com.qunar.fintech.plat.admin.hank.mapper.TppConfigMapper;
import com.qunar.fintech.plat.admin.hank.service.TppConfigService;
import com.qunar.fintech.plat.admin.support.exception.BDException;
import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.pay.g2.utils.common.CheckUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tongda.sun on 2018/12/06  17:25.
 */
@Service
public class TppConfigServiceImpl implements TppConfigService {

    @Override
    public TppConfig selectById(Long id) {
        if(CheckUtils.isEmpty(id)){
            throw new BDException("TppConfigService.selectById id参数为空");
        }
        return mapper.selectById(id);
    }

    @Override
    public TppConfig selectByTaskTypeAndReceiver(String taskType, String receiver) {
        logger.info("TppConfigService.selectByTaskTypeAndReceiver# taskType={} receiver={}",taskType,receiver);
        if(CheckUtils.isEmpty(taskType)){
            throw new BDException("TppConfigService.selectByTaskTypeAndReceiver # taskType 参数异常");
        }
        if(CheckUtils.isEmpty(receiver)){
            throw new BDException("TppConfigService.selectByTaskTypeAndReceiver # receiver 参数异常");
        }
        return mapper.selectByTaskTypeAndReceiver(taskType.trim(),receiver.trim());
    }

    @Override
    public List<TppConfig>select(Query query) {
        logger.info("TppConfigService.select# query={}",query);
        return mapper.select(query);
    }

    @Override
    public int count(Query query) {
        logger.info("TppConfigService.count# query={}",query);
        return mapper.count(query);
    }

    @Override
    public int insertSelective(TppConfig record) {
        if(CheckUtils.isEmpty(record)){
            throw  new BDException("TppConfigService.insertSelective# record 参数异常");
        }
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(TppConfig record) {
        if(CheckUtils.isEmpty(record)){
            throw  new BDException("TppConfigService.insertSelective# record 参数异常");
        }
        if(CheckUtils.isEmpty(record.getId())){
            throw  new BDException("TppConfigService.insertSelective# record.id 参数异常");
        }
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Autowired
    private TppConfigMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(TppConfigServiceImpl.class);

}
