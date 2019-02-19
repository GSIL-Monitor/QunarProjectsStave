package com.qunar.fintech.plat.admin.query.dao.ious;

import com.qunar.fintech.plat.admin.query.entity.BillInfo;
import com.qunar.fintech.plat.admin.query.vo.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by guoyue.sun on 2015/5/21.
 */
@Repository
public interface BillInfoDao {

    int selectBillInfoCount(@Param("userId") String userId, @Param("billStatus") Integer billStatus, @Param("start") Date start, @Param("end") Date end);

    List<BillInfo> selectBillInfoByPage(@Param("userId") String userId, @Param("billStatus") Integer billStatus, @Param("start") Date start, @Param("end") Date end, @Param("page")Page page);
}
