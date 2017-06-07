package com.wbsf.core.result.impl;

import com.wbsf.core.result.ResultInfo;
import com.wbsf.core.result.config.BaseResultEnum;

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
		super(BaseResultEnum.EXCEPTION);
		super.message = excption.getMessage();
		this.exception = excption;
	}
	
	/**
	 * 通过消息内容构建异常实例
	 * @param exceptionMsg
	 */
	public ExceptionResult(String exceptionMsg){
		super(BaseResultEnum.EXCEPTION);
		super.message = exceptionMsg;
		this.exception = new Exception(exceptionMsg);
	}
	
	/**
	 * 构建异常实例
	 * @param resultEnum
	 */
	public ExceptionResult(ResultInfo resultEnum){
		super(resultEnum);
		this.exception = new Exception(resultEnum.getMsg());
	}

	public Exception getException() {
		return exception;
	}
}
