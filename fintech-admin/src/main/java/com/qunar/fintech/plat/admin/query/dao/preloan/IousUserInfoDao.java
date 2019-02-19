package com.qunar.fintech.plat.admin.query.dao.preloan;


import com.qunar.fintech.plat.admin.query.entity.TblIousUserInfo;
import com.qunar.fintech.plat.admin.query.entity.TblLoanInfo;
import com.qunar.fintech.plat.admin.query.vo.IousUserInfoRequest;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.query.vo.QueryDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by baron.jiang on 2015/2/2.
 */
@Repository
public interface IousUserInfoDao {

    /**
     * 根据条件查询信任付账户信息
     *
     * @param request
     * @return
     */
    List<TblIousUserInfo> queryIousAccountByRequest(@Param("request") QueryDto request, @Param("page")Page page);

    /**
     * 查询主通道额度
     * @return
     */
    BigDecimal queryMainAmtByRequest(@Param("request") QueryDto request, @Param("useStatus")Integer useStatus);

    /**
     * 统计符合request信任付账户信息数目
     *
     * @param request
     * @return
     */
    public int countIousAccountByRequest(@Param("request") QueryDto request);

    public  int getIousUserInfoCount(@Param("query") IousUserInfoRequest query);

    //public List<TblIousUserInfo> getIousUserInfoList(IousUserInfoRequest query, Page page);

    public List<TblIousUserInfo> getIousUserInfoListByMobile(String mobile);

    public List<TblIousUserInfo> getIousUserInfoListByUserId(String userId);

    public List<TblIousUserInfo> getIousUserInfoListByRequest(List<TblLoanInfo> list);

    /**
     * 根据userId更新相关字段(使用时需注意mapper中是否相关字段是否有set语句)
     * @param iousUserInfo
     * @return
     */
    int updateByUidSelective(TblIousUserInfo iousUserInfo);

    TblIousUserInfo selectByUniqueKey(String userId, String tppCode, String productNo);
}
