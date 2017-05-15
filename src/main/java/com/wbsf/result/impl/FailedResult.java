package com.wbsf.result.impl;

import com.wbsf.result.ResultEnum;
import com.wbsf.result.base.ResultConfig;
import com.wbsf.result.base.ResultSupport;
/**
 * 失败结果实现类，继承ResultSupport
 * @author xiangzheng
 *
 * @param <T>
 */
public class FailedResult<T> extends ResultSupport<T> {
	public FailedResult(){
		super(ResultConfig.FAILED);
	}
	
	public FailedResult(String resultMsg){
		super(ResultConfig.FAILED);
		this.setResultMsg(resultMsg);
	}
	
	/**
	 * 构建失败实例
	 * @param resultEnum
	 */
	public FailedResult(ResultEnum resultEnum){
		super(resultEnum);
	}
}
