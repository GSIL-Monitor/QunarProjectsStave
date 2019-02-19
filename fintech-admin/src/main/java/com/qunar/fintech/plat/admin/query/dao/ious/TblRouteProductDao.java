package com.qunar.fintech.plat.admin.query.dao.ious;

import com.qunar.fintech.plat.admin.query.vo.TppInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 贷款产品路由信息
 */
@Repository
public interface TblRouteProductDao {

    List<TppInfo> selectTppCodeAndName();

}
