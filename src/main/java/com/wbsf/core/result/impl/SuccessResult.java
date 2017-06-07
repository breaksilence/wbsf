package com.wbsf.core.result.impl;

import com.wbsf.core.result.ResultInfo;
import com.wbsf.core.result.config.BaseResultEnum;

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
		super(BaseResultEnum.SUCCESS);
	}

	/**
	 * 构建成功结果实例
	 * @param resultMsg
	 */
	public SuccessResult(String resultMsg) {
		super(BaseResultEnum.SUCCESS);
		super.message = resultMsg;
	}
	
	/**
	 * 构建成功实例
	 * @param resultInfo
	 */
	public SuccessResult(ResultInfo resultInfo){
		super(resultInfo);
	}
}
