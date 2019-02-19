package com.qunar.fintech.plat.admin.query.entity;

import com.qunar.pay.g2.utils.persistence.Entity;

import java.math.BigDecimal;

/**
 * Created by bob.li on 2015/12/22.
 */
public class TblCreditReqDetail implements Entity<Long> {
    /**
     * 主键
     */
    private Long id;
    /**
     * 授信流水号
     */
    private String creditNo;
    /**
     * 用户Id
     */
    private String userId;
    /**
     *产品编码
     */
    private String productNo;

    /**
     * 贷款提供商编码
     */
    private String tppCode;
    /**
     * 后决策评分
     */
    private BigDecimal scoreAfterJudge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditNo() {
        return creditNo;
    }

    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public BigDecimal getScoreAfterJudge() {
        return scoreAfterJudge;
    }

    public void setScoreAfterJudge(BigDecimal scoreAfterJudge) {
        this.scoreAfterJudge = scoreAfterJudge;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TblCreditReqDetail{");
        sb.append("id=").append(id);
        sb.append(", creditNo='").append(creditNo).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", productNo='").append(productNo).append('\'');
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", scoreAfterJudge=").append(scoreAfterJudge);
        sb.append('}');
        return sb.toString();
    }
}
