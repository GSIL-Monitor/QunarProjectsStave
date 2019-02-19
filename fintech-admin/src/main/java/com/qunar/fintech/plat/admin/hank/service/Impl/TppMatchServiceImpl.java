package com.qunar.fintech.plat.admin.hank.service.Impl;

import com.qunar.fintech.plat.admin.hank.entity.TppMatch;
import com.qunar.fintech.plat.admin.hank.mapper.TppMatchMapper;
import com.qunar.fintech.plat.admin.hank.service.TppMatchService;
import com.qunar.fintech.plat.admin.support.exception.BDException;
import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.pay.g2.utils.common.CheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Created by tongda.sun on 2018/12/08  12:05.
 * Description:
 */
@Service
public class TppMatchServiceImpl implements TppMatchService {

    @Override
    public TppMatch selectById(Long id) {
        if(CheckUtils.isEmpty(id)){
            throw new BDException("TppMatchService.selectById id参数为空");
        }
        return mapper.selectById(id);
    }

    @Override
    public List<TppMatch> selectByTaskTypeAndReceiver(String taskType, String receiver) {
        logger.info("TppMatchService.selectByTaskTypeAndReceiver# taskType={} receiver={}",taskType,receiver);
        if(CheckUtils.isEmpty(taskType)){
            throw new BDException("TppMatchService.selectByTaskTypeAndReceiver#  taskType 参数异常");
        }
        if(CheckUtils.isEmpty(receiver)){
            throw new BDException("TppMatchService.selectByTaskTypeAndReceiver#  receiver 参数异常");
        }
        return mapper.selectByTaskTypeAndReceiver(taskType.trim(),receiver.trim());
    }

    @Override
    public List<TppMatch> select(Query query) {
        logger.info("TppMatchService.select# query={}",query);
        return mapper.select(query);
    }

    @Override
    public int count(Query query) {
        logger.info("TppMatchService.count# query={}",query);
        return mapper.count(query);
    }

    @Override
    public int insertSelective(TppMatch record) {
        if(CheckUtils.isEmpty(record)){
            throw new BDException("TppMatchService.insertSelective# record 参数异常");
        }
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(TppMatch record) {
        if(CheckUtils.isEmpty(record)){
            throw new BDException("TppMatchService.updateByPrimaryKeySelective# record 参数异常");
        }
        if(CheckUtils.isEmpty(record.getId())){
            throw new BDException("TppMatchService.updateByPrimaryKeySelective# record.id 参数异常");
        }
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Autowired
    private TppMatchMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(TppMatchServiceImpl.class);
}
