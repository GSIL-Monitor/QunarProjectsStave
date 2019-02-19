package com.qunar.fintech.plat.admin.query.dao.ious;


import com.qunar.fintech.plat.admin.query.entity.MinRepayLoanReqDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MinRepayLoanReqDetailMapper {

    List<MinRepayLoanReqDetail> selectBySerialNo(@Param("serialNo") String serialNo);

}