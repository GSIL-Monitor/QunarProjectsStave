package com.qunar.fintech.plat.admin.query.vo;

/**
 * @author kang.chen
 * @since 2016-06-12
 */
public class IousUserInfoRequest {
    /* 用户ID */
    private String userId;

    /*姓名*/
    private String userName;
    /* 手机号 */
    private String mobile;

    /* 签约日期查询开始时间 */
    private String startTime;


    private String endTime;


    private String tppCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

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

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IousUserInfoRequest{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", endTime='").append(endTime).append('\'');
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
