package com.qunar.fintech.plat.admin.bruce.util;

import com.qunar.fintech.plat.admin.bruce.vo.QueryStatisticDataVo;
import com.qunar.fintech.util.simple.DateUtils;

import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: xian.cheng
 * Date: 2019-01-16
 * Time: 14:55
 */
public class CheckParamUtils {
    /**
     * 检查并封装调用参数
     * @param params 传入的查询参数
     * @return QueryStatisticDataVo类型值
     */
    public QueryStatisticDataVo checkAndFill(Map<String, Object> params){
        Date startTime = DateUtils.str2date(params.get("startTime").toString(),DateUtils.FORMATTYPE1);
        Date endTime = DateUtils.str2date(params.get("endTime").toString(),DateUtils.FORMATTYPE1);
        if(null == params.get("tppCode").toString() || params.get("tppCode").toString().isEmpty()){
            throw new IllegalArgumentException("tppCode is null");
        }
        if(startTime.compareTo(endTime) > 0){
            throw new IllegalArgumentException("startTime > endTime");
        }
        QueryStatisticDataVo queryStatisticDataVo = new QueryStatisticDataVo();
        queryStatisticDataVo.setTppCode(params.get("tppCode").toString());
        queryStatisticDataVo.setOrgChannel(params.get("orgChannel").toString());
        queryStatisticDataVo.setStartTime(startTime);
        queryStatisticDataVo.setEndTime(endTime);
        return queryStatisticDataVo;
    }

}
