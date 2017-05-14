package com.wbsf.result.base;

import com.wbsf.result.ResultEnum;

public enum ResultConfig implements ResultEnum {
	FAILED("error", "failed")
	, SUCCESS("success", "success")
	,EXCEPTION("exception","exception shoulde be init by Exption result");
	private String resultCode;
	private String resultMsg;
	private ResultConfig(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}
	@Override
	public String getCode() {
		return this.resultCode;
	}
	
	@Override
	public String getMsg() {
		return this.resultMsg;
	}
}
