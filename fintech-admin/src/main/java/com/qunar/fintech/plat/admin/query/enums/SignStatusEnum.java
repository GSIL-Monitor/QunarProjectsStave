package com.qunar.fintech.plat.admin.query.enums;

public enum SignStatusEnum {
	// 状态 0:初始状态 1： 申请成功 2：签约成功，9 银行拒绝申请
	SIGNINIT(0, "初始状态"), APPLYSUCCESS(1, "申请成功"), SIGNSUCCESS(2, "签约成功"), BANKREJECT(
			9, "银行拒绝申请");

	private Integer code;

	private String name;

	private SignStatusEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static SignStatusEnum toEnum(int code) {
		switch (code) {
		case 1:
			return APPLYSUCCESS;
		case 2:
			return SIGNSUCCESS;
		case 9:
			return BANKREJECT;
		default:
			return SIGNINIT;
		}
	}
}
