package com.qunar.fintech.plat.admin.hank.service.Impl;

import com.qunar.fintech.plat.admin.hank.entity.FtpConfig;
import com.qunar.fintech.plat.admin.hank.mapper.FtpConfigMapper;
import com.qunar.fintech.plat.admin.hank.service.FtpConfigService;
import com.qunar.fintech.plat.admin.support.exception.BDException;
import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.pay.g2.utils.common.CheckUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tongda.sun on 2018/12/09  16:30.
 * Description:
 */
@Service
public class FtpConfigServiceImpl implements FtpConfigService {

    @Override
    public FtpConfig selectById(Long id) {
        if(CheckUtils.isEmpty(id)){
            throw new BDException("FtpConfigService.selectById id参数为空");
        }
        return mapper.selectById(id);
    }

    @Override
    public List<FtpConfig> selectByTaskTypeAndReceiver(String taskType, String receiver) {
        logger.info("FtpConfigService.selectByTaskTypeAndReceiver# taskType={} receiver={}",taskType,receiver);
        if(CheckUtils.isEmpty(taskType)){
            throw new BDException("FtpConfigService.selectByTaskTypeAndReceiver # taskType 参数异常");
        }
        if(CheckUtils.isEmpty(receiver)){
            throw new BDException("FtpConfigService.selectByTaskTypeAndReceiver # receiver 参数异常");
        }
        return mapper.selectByTaskTypeAndReceiver(taskType.trim(),receiver.trim());
    }

    @Override
    public List<FtpConfig> select(Query query) {
        logger.info("FtpConfigService.select# query={}",query);
        return mapper.select(query);
    }

    @Override
    public int count(Query query) {
        logger.info("FtpConfigService.count# query={}",query);
        return mapper.count(query);
    }

    @Override
    public int insertSelective(FtpConfig record) {
        if(CheckUtils.isEmpty(record)){
            throw new BDException("FtpConfigService.insertSelective # record 参数异常");
        }
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(FtpConfig record) {
        if(CheckUtils.isEmpty(record)){
            throw new BDException("FtpConfigService.insertSelective # record 参数异常");
        }
        if(CheckUtils.isEmpty(record.getId())){
            throw new BDException("FtpConfigService.insertSelective # record.id 参数异常");
        }
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Autowired
    private FtpConfigMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(FtpConfigServiceImpl.class);
}
