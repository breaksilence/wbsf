package com.wbsf.core.result.impl;

import com.wbsf.core.result.ResultInfo;

/**
 * 结果信息基础实现类，在枚举的范围外提供了灵活的构建结果枚举的辅助类
 * @author xiangzheng
 *
 */
public class ReusltInfoBuilder implements ResultInfo {
	/**
	 * 结果编码
	 */
	private String code;
	/**
	 * 结果信息
	 */
	private String message;
	/**
	 * 结果状态
	 */
	private boolean success;
	
	public ReusltInfoBuilder(String code, String message, boolean success) {
		this.code = code;
		this.message = message;
		this.success = success;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMsg() {
		return this.message;
	}

	@Override
	public boolean successFlag() {
		return this.success;
	}

}
