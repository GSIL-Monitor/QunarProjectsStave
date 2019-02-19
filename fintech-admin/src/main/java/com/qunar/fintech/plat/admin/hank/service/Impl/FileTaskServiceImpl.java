package com.qunar.fintech.plat.admin.hank.service.Impl;

import com.qunar.fintech.plat.admin.hank.entity.FileTask;
import com.qunar.fintech.plat.admin.hank.mapper.FileTaskMapper;
import com.qunar.fintech.plat.admin.hank.service.FileTaskService;
import com.qunar.fintech.plat.admin.support.exception.BDException;
import com.qunar.fintech.plat.admin.support.web.Query;
import com.qunar.fintech.util.simple.DateUtils;
import com.qunar.pay.g2.utils.common.CheckUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by tongda.sun on 2018/12/10  15:58.
 * Description:
 */
@Service
public class FileTaskServiceImpl implements FileTaskService {

    @Override
    public FileTask selectById(Long id) {
        if(CheckUtils.isEmpty(id)){
            throw new BDException("FileTaskService.selectById id参数为空");
        }
        return mapper.selectById(id);
    }

    @Override
    public List<FileTask> selectByTaskTypeAndAccountTime(String taskType, Date accountTime) {
        logger.info("FileTaskService.selectByTaskTypeAndAccountTime# taskType={} accountTime={}",taskType, DateUtils.date2str(accountTime,DateUtils.FORMATTYPE9));
        if(CheckUtils.isEmpty(taskType)){
            throw new BDException("FileTaskService.selectByTaskTypeAndAccountTime # taskType 参数异常");
        }
        if(CheckUtils.isEmpty(accountTime)){
            throw new BDException("FileTaskService.selectByTaskTypeAndAccountTime # accountTime 参数异常");
        }
        return mapper.selectByTaskTypeAndAccountTime(taskType.trim(),accountTime);
    }

    @Override
    public List<FileTask> select(Query query) {
        logger.info("FileTaskService.select# query={}",query);
        return mapper.select(query);
    }

    @Override
    public int count(Query query) {
        logger.info("FileTaskService.count# query={}",query);
        return mapper.count(query);
    }

    @Override
    public int insertSelective(FileTask record) {
        if(CheckUtils.isEmpty(record)){
            throw new BDException("FileTaskService.insertSelective # receiver 参数异常");
        }
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(FileTask record) {
        if(CheckUtils.isEmpty(record)){
            throw new BDException("FileTaskService.updateByPrimaryKeySelective # receiver 参数异常");
        }
        if(CheckUtils.isEmpty(record.getId())){
            throw new BDException("FileTaskService.updateByPrimaryKeySelective # receiver.id 参数异常");
        }
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Autowired
    private FileTaskMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(FileTaskServiceImpl.class);
}
