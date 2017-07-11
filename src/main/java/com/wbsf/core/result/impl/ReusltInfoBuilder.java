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
	
	public ReusltInfoBuilder(String code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMsg() {
		return this.message;
	}

	public ReusltInfoBuilder setCode(String code) {
		this.code = code;
		return this;
	}

	public ReusltInfoBuilder setMessage(String message) {
		this.message = message;
		return this;
	}
}
