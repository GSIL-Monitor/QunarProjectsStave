package com.qunar.fintech.plat.admin.query.vo;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;

/**
 * @author shuaifeng.gao
 * @since 2017-12-19 13:43
 **/
public class ForceAdvanceResponse extends BaseResponse{
    private static final long serialVersionUID = -8705326849476723945L;

    /**
     * 强扣发起了多少钱
     */
    private BigDecimal repayAmt = BigDecimal.ZERO;

    /**
     * 强扣发起了多少条
     */
    private int count = 0;

    public static ForceAdvanceResponse newSucc(BigDecimal repayAmt, int count){
        ForceAdvanceResponse response = new ForceAdvanceResponse();
        response.setRepayAmt(repayAmt);
        response.setCount(count);
        response.setSuccessReq();
        return response;
    }

    public static ForceAdvanceResponse newFail(String errMsg){
        ForceAdvanceResponse response = new ForceAdvanceResponse();
        response.setReturnMsg(errMsg);
        response.setFailReq();
        return response;
    }

    public BigDecimal getRepayAmt() {
        return repayAmt;
    }

    public void setRepayAmt(BigDecimal repayAmt) {
        this.repayAmt = repayAmt;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDesc(){
        return "发起扣款条数:" + count + ";发起扣款金额" + repayAmt;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("repayAmt", repayAmt)
                .add("count", count)
                .toString();
    }
}
