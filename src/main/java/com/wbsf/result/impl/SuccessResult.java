package com.wbsf.result.impl;

import com.wbsf.result.base.ResultConfig;
import com.wbsf.result.base.ResultSupport;

public class SuccessResult<T> extends ResultSupport<T> {
	public SuccessResult(){
		super();
		this.setResultConfig(ResultConfig.SUCCESS);
	}
	public SuccessResult(String resultMsg){
		super();
		this.setResultConfig(ResultConfig.SUCCESS);
		this.setResultMsg(resultMsg);
	}
}
