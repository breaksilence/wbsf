package com.wbsf.core.result.utils;

import com.wbsf.core.result.Result;
import com.wbsf.core.result.ResultEnum;
import com.wbsf.core.result.impl.ExceptionResult;
import com.wbsf.core.result.impl.FailedResult;
import com.wbsf.core.result.impl.SuccessResult;

/**
 * 处理结果帮助类，用于构建不同类型的结果实例
 * 
 * @author xiangzheng
 *
 */
public class ResultHelper<T> {

	/**
	 * 构建成功的结果实例
	 * 
	 * @return Result<T>
	 */
	public static <T> Result<T> buildSuccess() {
		return new SuccessResult<T>();
	}

	/**
	 * 构建成功结果实例
	 * 
	 * @param resultMsg
	 *            返回的附件信息
	 * @return Result<T>
	 */
	public static  <T> Result<T> buildSuccess(String resultMsg) {
		return new SuccessResult<T>(resultMsg);
	}
	
	/**
	 *  构建成功结果实例
	 * @param resultEnum
	 * @return
	 */
	public static  <T> Result<T> buildSuccess(ResultEnum resultEnum) {
		return new SuccessResult<T>(resultEnum);
	}

	/**
	 * 构建失败的结果实例
	 * 
	 * @return Result<T>
	 */
	public static  <T> Result<T> buildFailed() {
		return new FailedResult<T>();
	}

	/**
	 * 构建失败的结果实例
	 * 
	 * @param resultMsg
	 * @return Result<T>
	 */
	public static  <T> Result<T> buildFailed(String resultMsg) {
		return new FailedResult<T>(resultMsg);
	}
	
	/**
	 * 构建失败实例
	 * @param resultEnum
	 * @return
	 */
	public static  <T> Result<T> buildFailed(ResultEnum resultEnum) {
		return new FailedResult<T>(resultEnum);
	}
	
	/**
	 * 构建异常的结果实例
	 * 
	 * @param exception
	 * @return Result<T>
	 */
	public static  <T> Result<T> buildException(Exception exception) {
		return new ExceptionResult<T>(exception);
	}

	/**
	 * 构建异常结果实例
	 * 
	 * @param resultMsg
	 * @return Result<T>
	 */
	public static  <T> Result<T> buildException(String resultMsg) {
		return new ExceptionResult<T>(resultMsg);
	}
	
	/**
	 * 构建异常实例
	 * @param resultEnum
	 * @return
	 */
	public static  <T> Result<T> buildException(ResultEnum resultEnum) {
		return new ExceptionResult<T>(resultEnum);
	}
	
}
