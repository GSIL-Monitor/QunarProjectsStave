package com.qunar.fintech.plat.admin.query.vo;

/**
 * Describe : 锁定订单查询请求参数
 * Author： ruijie.zheng
 * email : ruijie.zheng@qunar.com
 * Date ： 17-8-10
 */
public class QueryLockOrderReq {
    private static final long serialVersionUID = -3612154745499243396L;


    /**
     * 用户uid
     **/
    private String userId;
    /**
     * 贷款订单号
     **/
    private String loanProvideNo;

    /**
     * 还款订单号
     */
    private String serialNo;

    /**
     * 订单日期开始时间
     **/
    private String startTime;

    /**
     * 订单日期截止时间
     **/
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QueryLockOrderOrderReq{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", loanProvideNo='").append(loanProvideNo).append('\'');
        sb.append(", serialNo='").append(serialNo).append('\'');
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", endTime='").append(endTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}