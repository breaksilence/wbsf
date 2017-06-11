package com.wbsf.core.result.impl;

import com.wbsf.core.result.ResultInfo;
import com.wbsf.core.result.config.ResultBaseEnum;

/**
 * 异常结果实现类，继承ResultSupport
 * @author xiangzheng
 *
 * @param <T>
 */
public class ExceptionResult<T> extends ResultSupport<T> {
	/**
	 * 异常结果的异常实例
	 */
	private Exception excption;
	/**
	 * 通过异常信息构建结果实例
	 * @param excption
	 */
	public ExceptionResult(Exception excption){
		super(ResultBaseEnum.EXCEPTION.getCode(), excption.getMessage(), false);
	}
	
	/**
	 * 通过消息内容构建异常实例
	 * @param exceptionMsg
	 */
	public ExceptionResult(String exceptionMsg){
		super(ResultBaseEnum.EXCEPTION.getCode(), exceptionMsg, false);
	}
	
	/**
	 * 构建异常实例
	 * @param resultInfo
	 */
	public ExceptionResult(ResultInfo resultInfo){
		super(resultInfo.getCode(), resultInfo.getMsg(), false);
	}

	/**
	 * 获取异常实例
	 * @return
	 */
	public Exception exception() {
		if(excption == null)
			new Exception(getMessage());
		return this.excption;
	}
}
