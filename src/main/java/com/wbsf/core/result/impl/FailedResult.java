package com.wbsf.core.result.impl;

import com.wbsf.core.result.ResultInfo;
import com.wbsf.core.result.config.BaseResultEnum;
/**
 * 失败结果实现类，继承ResultSupport
 * @author xiangzheng
 *
 * @param <T>
 */
public class FailedResult<T> extends ResultSupport<T> {
	public FailedResult(){
		super(BaseResultEnum.FAILED);
	}
	
	public FailedResult(String resultMsg){
		super(BaseResultEnum.FAILED);
		super.message = resultMsg;
	}
	
	/**
	 * 构建失败实例
	 * @param resultEnum
	 */
	public FailedResult(ResultInfo resultEnum){
		super(resultEnum);
	}
}
