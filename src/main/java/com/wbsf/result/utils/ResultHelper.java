package com.wbsf.result.utils;

import com.wbsf.result.Result;
import com.wbsf.result.impl.ExceptionResult;
import com.wbsf.result.impl.FailedResult;
import com.wbsf.result.impl.SuccessResult;

public class ResultHelper {
	public <T> Result<T> buildSuccess(){
		return new SuccessResult<>();
	}
	
	public <T> Result<T> buildSuccess(String resultMsg){
		return new SuccessResult<>(resultMsg);
	}
	
	public <T> Result<T> buildFailed(){
		return new FailedResult<>();
	}
	
	public <T> Result<T> buildFailed(String resultMsg){
		return new FailedResult<>(resultMsg);
	}
	
	public <T> Result<T> buildException(Exception exception){
		return new ExceptionResult<>(exception);
	}
	
	public <T> Result<T> buildException(String resultMsg){
		return new ExceptionResult<>(resultMsg);
	}
}
