package com.qunar.fintech.plat.admin.query.vo;

import java.io.Serializable;

/**
 * 金融路由开关请求类
 *
 * Created by bob.li on 2015/8/11.
 */
public class QueryIousFinancialRouteSwitchRequest implements Serializable{
    private static final long serialVersionUID = -8027264112578902621L;

    /**
     * 贷款提供商代码
     */
    private String productNo;

    /**
     * 贷款提供商代码
     */
    private String tppCode;

    /**
     * 贷款提供商名称
     */
    private String tppName;

    /**
     * 支付开关(0:关闭 ； 1:开启)
     */
    private Integer paySwitch;

    /**
     * 预授信开关(0:关闭 ； 1:开启)
     */
    private Integer authSwitch;

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getTppName() {
        return tppName;
    }

    public void setTppName(String tppName) {
        this.tppName = tppName;
    }

    public Integer getPaySwitch() {
        return paySwitch;
    }

    public void setPaySwitch(Integer paySwitch) {
        this.paySwitch = paySwitch;
    }

    public Integer getAuthSwitch() {
        return authSwitch;
    }

    public void setAuthSwitch(Integer authSwitch) {
        this.authSwitch = authSwitch;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QueryIousFinancialRouteSwitchRequest{");
        sb.append("productNo='").append(productNo).append('\'');
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", tppName='").append(tppName).append('\'');
        sb.append(", paySwitch=").append(paySwitch);
        sb.append(", authSwitch=").append(authSwitch);
        sb.append('}');
        return sb.toString();
    }
}
