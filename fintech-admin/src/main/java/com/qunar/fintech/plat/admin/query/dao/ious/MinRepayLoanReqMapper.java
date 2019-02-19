package com.qunar.fintech.plat.admin.query.dao.ious;


import com.qunar.fintech.plat.admin.query.entity.MinRepayLoanReq;
import com.qunar.fintech.plat.admin.query.vo.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MinRepayLoanReqMapper {



    List<MinRepayLoanReq> selectByPage(@Param("userIdList") List<String> userIdList, @Param("minRepayTpp") String minRepayTpp,
                                       @Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                       @Param("processStatus") Integer processStatus, @Param("page") Page page);


    MinRepayLoanReq queryBySerialNo(@Param("serialNo") String serialNo);

}