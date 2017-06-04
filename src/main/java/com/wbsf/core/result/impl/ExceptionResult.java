package com.wbsf.core.result.impl;

import com.wbsf.core.result.ResultEnum;
import com.wbsf.core.result.config.ResponseEnum;

/**
 * 异常结果实现类，继承ResultSupport
 * @author xiangzheng
 *
 * @param <T>
 */
public class ExceptionResult<T> extends ResultSupport<T> {
	
	private Exception exception;
	
	/**
	 * 通过异常信息构建结果实例
	 * @param excption
	 */
	public ExceptionResult(Exception excption){
		super(ResponseEnum.EXCEPTION);
		this.setException(excption);
	}
	
	/**
	 * 通过消息内容构建异常实例
	 * @param exceptionMsg
	 */
	public ExceptionResult(String exceptionMsg){
		super(ResponseEnum.EXCEPTION);
		this.setMessage(exceptionMsg);
	}
	
	/**
	 * 构建异常实例
	 * @param resultEnum
	 */
	public ExceptionResult(ResultEnum resultEnum){
		super(resultEnum);
	}

	public Exception getException() {
		return exception;
	}
	

	public void setException(Exception exception) {
		this.exception = exception;
	}
}
