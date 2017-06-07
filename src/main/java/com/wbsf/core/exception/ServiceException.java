package com.wbsf.core.exception;

import com.wbsf.core.result.Result;
import com.wbsf.core.result.ResultInfo;
import com.wbsf.core.result.config.BaseResultEnum;
import com.wbsf.core.result.config.ReusltInfoBuilder;
import com.wbsf.core.result.impl.ExceptionResult;

/**
 * 为服务层提供基础异常服务类<p>
 * 与结果对象整合，实现自定义服务异常类能够携带结果属性为controller提供便利的结果处理条件
 * @author xiangzheng
 *
 */
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5335284332447605426L;

	/**
	 * 异常错误结果枚举，反应错误的状态码和附加信息
	 */
	private ResultInfo exceptionInfo;
	
	/**
	 * 继承 Exception 父类构造方法
	 */
	public ServiceException() {
		super();
	}
	
	/**
	 * 通过结果枚举构建异常service异常类
	 * @param exceptionInfo
	 */
	public ServiceException(ResultInfo exceptionInfo){
		super(exceptionInfo.getMsg());
		this.exceptionInfo = exceptionInfo;
	}
	/**
	 * 继承 Exception 父类构造方法
	 */
	public ServiceException(String message) {
		super(message);
	}
	/**
	 * 继承 Exception 父类构造方法
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}
	/**
	 * 继承 Exception 父类构造方法
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 继承 Exception 父类构造方法
	 */
	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * 获取异常处理的结果信息
	 * @return
	 */
	public ResultInfo getResultInfo() {
		if (exceptionInfo == null){
			exceptionInfo = new ReusltInfoBuilder(BaseResultEnum.EXCEPTION.getCode(), this.getMessage(), BaseResultEnum.EXCEPTION.successFlag());
		}
		return exceptionInfo;
	}
	
	/**
	 * 获取异常结果实例
	 * @return
	 */
	public Result<?> exceptionResult(){
		if(exceptionInfo != null){
			return new ExceptionResult<>(exceptionInfo);
		}else{
			return new ExceptionResult<>(this);
		}
			
	}
}
