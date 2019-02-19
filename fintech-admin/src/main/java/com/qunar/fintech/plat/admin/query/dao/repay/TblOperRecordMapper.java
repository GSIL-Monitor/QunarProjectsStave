package com.qunar.fintech.plat.admin.query.dao.repay;

import com.qunar.fintech.plat.admin.query.entity.TblOperRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by libo on 2017/4/17.
 */
@Repository
public interface TblOperRecordMapper {
    /**
     * 根据reqSerialNo,operStatus查询操作记录
     * @param reqSerialNo   userRepayReq流水号
     * @return
     */
    List<TblOperRecord> selectOperRecordByReqSerialNo(@Param("reqSerialNo") String reqSerialNo);

    List<TblOperRecord> selectOperRecordByLoanProvideNo(@Param("loanProvideNo") String loanProvideNo);


}
