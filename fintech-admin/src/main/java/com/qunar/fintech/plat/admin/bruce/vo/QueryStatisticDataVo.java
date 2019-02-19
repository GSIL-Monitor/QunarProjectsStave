package com.qunar.fintech.plat.admin.bruce.vo;

import com.qunar.pay.finance.qf.commons.api.model.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: xian.cheng
 * Date: 2018-12-25
 * Time: 17:01
 */
public class QueryStatisticDataVo extends ToString {
    //渠道来源
    private String orgChannel;
    //贷款提供商
    private String tppCode;
    // 查询开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    private Date startTime;
    //查询结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    private Date endTime;

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
