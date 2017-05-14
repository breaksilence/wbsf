package com.wbsf.result.impl;

import com.wbsf.result.base.ResultConfig;
import com.wbsf.result.base.ResultSupport;

public class ExceptionResult<T> extends ResultSupport<T> {
	
	public ExceptionResult(Exception excption){
		super();
		this.setResultCode(ResultConfig.EXCEPTION.getCode());
		this.setResultMsg(excption.getMessage());
		this.success = false;
	}
	
	public ExceptionResult(String exceptionMsg){
		super();
		this.setResultCode(ResultConfig.EXCEPTION.getCode());
		this.setResultMsg(exceptionMsg);
		this.success = false;
	}
}
