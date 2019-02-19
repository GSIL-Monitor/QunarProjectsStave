package com.qunar.fintech.plat.admin.query.vo;

/**
 * 查询打款信息的请求
 *
 * Created by xi.cheng on 2016/9/20.
 */
public class CashRemitRequest {
    /**
     *渠道编码 */
    private String orgChannel;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 借据号
     */
    private String loanProvideNo;
    /**
     * 借款流水
     */
    private String serialNo;
    /**
     * 打款订单号
     */
    private String remitRequestNo;

    /**
     * 通道方
     */
    private String tppCode;

    /**
     * 打款申请日期（默认当天）开始时间
     */
    private String requestTime;
    /**
     * 打款申请日期（默认当天） 结束时间
     */
    private String requestEndTime;
    /**
     * 打款完成日期（默认当天） 开始时间
     */
    private String finishTime;
    /**
     * 打款完成日期（默认当天） 结束时间
     */
    private String finishEndTime;
    /**
     * 打款状态
     * @see com.qunar.pay.g2.fpp.common.enumtype.RemitOrderStatusEnum
     */
    private Integer status;
    /**
     * 可人工处理订单标识  0：全部  1：需要人工处理
     */
    private Integer manual;

    public Integer getManual() {
        return manual;
    }

    public void setManual(Integer manual) {
        this.manual = manual;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getRemitRequestNo() {
        return remitRequestNo;
    }

    public void setRemitRequestNo(String remitRequestNo) {
        this.remitRequestNo = remitRequestNo;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getRequestEndTime() {
        return requestEndTime;
    }

    public void setRequestEndTime(String requestEndTime) {
        this.requestEndTime = requestEndTime;
    }

    public String getFinishEndTime() {
        return finishEndTime;
    }

    public void setFinishEndTime(String finishEndTime) {
        this.finishEndTime = finishEndTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLoanProvideNo() {
        return loanProvideNo;
    }

    public void setLoanProvideNo(String loanProvideNo) {
        this.loanProvideNo = loanProvideNo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CashRemitRequest{");
        sb.append("orgChannel='").append(orgChannel).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", loanProvideNo='").append(loanProvideNo).append('\'');
        sb.append(", serialNo='").append(serialNo).append('\'');
        sb.append(", remitRequestNo='").append(remitRequestNo).append('\'');
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", requestTime='").append(requestTime).append('\'');
        sb.append(", requestEndTime='").append(requestEndTime).append('\'');
        sb.append(", finishTime='").append(finishTime).append('\'');
        sb.append(", finishEndTime='").append(finishEndTime).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
