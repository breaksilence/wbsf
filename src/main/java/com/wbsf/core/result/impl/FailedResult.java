package com.wbsf.core.result.impl;

import com.wbsf.core.result.ResultInfo;
import com.wbsf.core.result.config.ResultBaseEnum;
/**
 * 失败结果实现类，继承ResultSupport
 * @author xiangzheng
 *
 * @param <T>
 */
public class FailedResult<T> extends ResultSupport<T> {
	public FailedResult(){
		super(ResultBaseEnum.FAILED);
	}
	
	public FailedResult(String resultMsg){
		super(ResultBaseEnum.FAILED);
		super.message = resultMsg;
	}
	
	/**
	 * 构建失败实例
	 * @param resultInfo
	 */
	public FailedResult(ResultInfo resultInfo){
		super(resultInfo);
	}
}
