package com.qunar.fintech.plat.admin.query.dao.ious;

import com.qunar.fintech.plat.admin.query.entity.BillDetail;
import com.qunar.fintech.plat.admin.query.vo.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by libo on 2018/2/5.
 */
@Repository
public interface BillDetailDao {
    /**
     * 分页查询
     * @return
     */
    List<BillDetail> selectBillDetailByPage(@Param("billNo") String billNo, @Param("page") Page page);

    /**
     * 查询总数
     * @return
     */
    int selectBillDetailCount(@Param("billNo") String billNo);
}
