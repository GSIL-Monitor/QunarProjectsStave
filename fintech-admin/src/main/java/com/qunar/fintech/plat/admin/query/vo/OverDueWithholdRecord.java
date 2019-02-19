package com.qunar.fintech.plat.admin.query.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cheng.she
 * @since 2016-02-23
 */
public class OverDueWithholdRecord implements Serializable{

	private static final long serialVersionUID = 4892388760509367322L;
	/**
     * 用户id
     */
    private String userId;
    /**
     * 贷款提供商
     */
    private String productNo;
    /**
     * 贷款提供商
     */
    private String tppCode;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 贷款欠款总笔数
     */
    private Integer debtNum;

    /**
     * 欠款总金额
     */
    private String debtSumAmount;
    /**
     * 当期余额
     */
    private String currentBalance;
    /**
     * 强扣金额
     */
    private String forceHoldAmt;
    /**
     * 贷款流水
     */
    private String loanProvideNo ;
    /**
     * 错误码
     */
    private String errCode ;
    /**
     * 错误信息
     */
    private String errMsg ;
    /**
     * 最大逾期天数
     */
    private Integer maxOverDueDayNum;

    /**
     * 用户最小逾期日期
     */
    private Date  minOverDue;
    
	/**
	 *0.初始状态 1.审核通过 2.审核拒绝 3.扣款成功 4.扣款失败
	 */
	private Integer withholdStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	
	public String getForceHoldAmt() {
		return forceHoldAmt;
	}

	public void setForceHoldAmt(String forceHoldAmt) {
		this.forceHoldAmt = forceHoldAmt;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getLoanProvideNo() {
		return loanProvideNo;
	}

	public void setLoanProvideNo(String loanProvideNo) {
		this.loanProvideNo = loanProvideNo;
	}

	public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getDebtNum() {
        return debtNum;
    }

    public void setDebtNum(Integer debtNum) {
        this.debtNum = debtNum;
    }

    public String getDebtSumAmount() {
        return debtSumAmount;
    }

    public void setDebtSumAmount(String debtSumAmount) {
        this.debtSumAmount = debtSumAmount;
    }

    public Integer getMaxOverDueDayNum() {
        return maxOverDueDayNum;
    }

    public void setMaxOverDueDayNum(Integer maxOverDueDayNum) {
        this.maxOverDueDayNum = maxOverDueDayNum;
    }

    public Date getMinOverDue() {
        return minOverDue;
    }

    public void setMinOverDue(Date minOverDue) {
        this.minOverDue = minOverDue;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getWithholdStatus() {
		return withholdStatus;
	}

	public void setWithholdStatus(Integer withholdStatus) {
		this.withholdStatus = withholdStatus;
	}

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    
    public String getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OverDueWithholdRecord{");
        sb.append("userId=").append(userId);
        sb.append(", productNo='").append(productNo).append('\'');
        sb.append(", tppCode='").append(tppCode).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", debtNum=").append(debtNum);
        sb.append(", withholdStatus=").append(withholdStatus);
        sb.append(", debtSumAmount='").append(debtSumAmount).append('\'');
        sb.append(", maxOverDueDayNum=").append(maxOverDueDayNum);
        sb.append(", minOverDue=").append(minOverDue);
        sb.append(", forceHoldAmt=").append(forceHoldAmt);
        sb.append(", currentBalance=").append(currentBalance);
        sb.append(", loanProvideNo=").append(loanProvideNo);
        sb.append('}');
        return sb.toString();
    }
}
