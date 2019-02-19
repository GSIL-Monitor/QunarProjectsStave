package com.qunar.fintech.plat.admin.hank.service.Impl;

import com.qunar.fintech.hank.api.accountfile.dto.AccountCheckFileDto;
import com.qunar.fintech.hank.api.accountfile.facade.AccountFileLaunchFacade;
import com.qunar.fintech.plat.admin.hank.entity.AccountFileLaunch;
import com.qunar.fintech.plat.admin.hank.mapper.AccountFileMapper;
import com.qunar.fintech.plat.admin.hank.service.AccountFileService;
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
import java.util.Map;

/**
 * Created by tongda.sun on 2018/12/13  11:42.
 * Description:
 */
@Service
public class AccountFileServiceImpl implements AccountFileService {

    @Override
    public List<String> getTaskTypes(String receiver) {
        logger.info("receiver={}",receiver);
        if(CheckUtils.isEmpty(receiver)){
            return mapper.selectTaskType("");
        }
        return mapper.selectTaskType(receiver.trim());
    }

    @Override
    public List<String> getReceivers(String taskType) {
        logger.info("taskType={}",taskType);
        if(CheckUtils.isEmpty(taskType)){
            return mapper.selectReceiver("");
        }
        return mapper.selectReceiver(taskType.trim());
    }

    @Override
    public String getFileName(String taskType, String receiver) {
        logger.info("taskType={} receiver={}",taskType,receiver);
        if(CheckUtils.isEmpty(taskType)){
            return "taskType is empty";
        }
        if(CheckUtils.isEmpty(receiver)){
            return "receiver is empty";
        }
        return mapper.selectFileName(taskType,receiver);
    }

    @Override
    public List<AccountFileLaunch> getAccountFileLaunchList(Query query, String reqUser) {
        logger.info("getAccountCheckFileList# query={} reqUser={}",query,reqUser);
        checkAndFillQueryParams(query,reqUser);
        return mapper.selectAccountFileLaunchList(query);
    }

    @Override
    public int getAccountFileLaunchListCount(Query query) {
        logger.info("getAccountCheckFileListCount# query={}",query);
        return mapper.selectAccountFileLaunchListCount(query);
    }

    @Override
    public void export(Map<String, Object> params, String reqUser){
        logger.info("params={} reqUser={}",params,reqUser);
        AccountCheckFileDto req = checkAndFill(params,reqUser);
        logger.info("start: 调用 hank 系统对账服务");
        if(!accountFileLaunchFacade.launchAccountCheckFile(req)){
            throw new BDException("发起对账任务失败");
        }
    }

    /**
     * 检查并封装远程调用参数
     */
    private AccountCheckFileDto checkAndFill(Map<String, Object> params, String reqUser){
        Date startTime = DateUtils.str2date(params.get("startTime").toString(),DateUtils.FORMATTYPE9);
        Date endTime = DateUtils.str2date(params.get("endTime").toString(),DateUtils.FORMATTYPE9);
        Date runTime = DateUtils.str2date(params.get("runTime").toString(),DateUtils.FORMATTYPE1);
        if(CheckUtils.isEmpty(params.get("taskType"))){
            throw new IllegalArgumentException("taskType is null");
        }
        if(startTime.compareTo(endTime) > 0){
            throw new IllegalArgumentException("startTime > endTime");
        }
        AccountCheckFileDto req = new AccountCheckFileDto();
        req.setTaskType(params.get("taskType").toString().trim());
        req.setReceiver(params.get("receiver").toString().trim());
        req.setStartDate(DateUtils.date2str(startTime,DateUtils.FORMATTYPE9));
        req.setEndDate(DateUtils.date2str(endTime,DateUtils.FORMATTYPE9));
        req.setRunTime(DateUtils.date2str(runTime,DateUtils.FORMATTYPE1));
        req.setReqUser(reqUser.trim());
        return req;
    }

    /**
     * 检查封装Query参数
     */
    private void checkAndFillQueryParams(Query query,String reqUser){
        // 无参数时查询 当前用户执行的的任务列表
        if(CheckUtils.isEmpty(query.get("reqUser")) && CheckUtils.isEmpty(query.get("taskType")) &&
           CheckUtils.isEmpty(query.get("receiver")) && CheckUtils.isEmpty(query.get("runTime"))){
            query.put("reqUser",reqUser);
        }else{
            String runTime = query.get("runTime").toString().trim();
            if(runTime.length() == 0){
                return;
            }else if(runTime.length() == 23){
                // 合法的日期范围格式 "yyyy-MM-dd - yyyy-MM-dd"  (endRunTime + 1) 天为真实结束日期
                String startRunTime = runTime.substring(0,10);
                String endRunTime = runTime.substring(13);
                query.put("startRunTime",DateUtils.str2date(startRunTime,DateUtils.FORMATTYPE9));
                query.put("endRunTime",DateUtils.addDay(DateUtils.str2date(endRunTime,DateUtils.FORMATTYPE9),1));
            }else{
                throw new IllegalArgumentException("不合法的日期范围格式");
            }
        }
    }

    @Autowired
    private AccountFileMapper mapper;

    @Autowired
    private AccountFileLaunchFacade accountFileLaunchFacade;

    private static final Logger logger = LoggerFactory.getLogger(AccountFileServiceImpl.class);
}
