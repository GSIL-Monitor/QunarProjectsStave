package com.qunar.fintech.plat.admin.query.dao.ious;


import com.qunar.fintech.plat.admin.query.entity.TblIousPay;
import com.qunar.fintech.plat.admin.query.entity.TblLoanInfo;
import com.qunar.fintech.plat.admin.query.vo.QueryIousPayRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TblIousPayDao {

   List<TblIousPay> queryTblIousPayByRequest(QueryIousPayRequest request);

   int countIousPayByRequest(QueryIousPayRequest request);

   List<TblIousPay> selectByOrderNo(String orderNo);

   List<TblIousPay> queryTblIousPayByBankSerialNo(String bankSerialNo);

   List<TblIousPay> getIousPayListByRequest(List<TblLoanInfo> list);

   TblIousPay selectIousPayByPayId(String payId);

   List<TblIousPay> selectIousPayByGwPayIdList(List<String> gwPayIdList);

   TblIousPay selectPayByOrderNo(@Param("orderNo") String orderNo);

}