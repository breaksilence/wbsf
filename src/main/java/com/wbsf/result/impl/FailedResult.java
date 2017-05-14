package com.wbsf.result.impl;

import com.wbsf.result.base.ResultConfig;
import com.wbsf.result.base.ResultSupport;

public class FailedResult<T> extends ResultSupport<T> {
	public FailedResult(){
		super();
		this.setResultConfig(ResultConfig.FAILED);
		this.success = false;
	}
	
	public FailedResult(String resultMsg){
		this.setResultConfig(ResultConfig.FAILED);
		this.setResultMsg(resultMsg);
		this.success = false;
	}
}
