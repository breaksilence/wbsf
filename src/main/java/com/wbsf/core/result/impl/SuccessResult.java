package com.wbsf.core.result.impl;

import com.wbsf.core.result.ResultEnum;
import com.wbsf.core.result.config.ResultConfig;

/**
 * 成功结果实现类，继承ResultSupport
 * @author xiangzheng
 *
 * @param <T>
 */
public class SuccessResult<T> extends ResultSupport<T> {
	
	/**
	 * 构建成功结果实例
	 */
	public SuccessResult() {
		super(ResultConfig.SUCCESS);
	}

	/**
	 * 构建成功结果实例
	 * @param resultMsg
	 */
	public SuccessResult(String resultMsg) {
		super(ResultConfig.SUCCESS);
		this.setMessage(resultMsg);
	}
	
	/**
	 * 构建成功实例
	 * @param resultEnum
	 */
	public SuccessResult(ResultEnum resultEnum){
		super(resultEnum);
	}
}
