package com.wbsf.core.result.config;

import com.wbsf.core.result.ResultInfo;

/**
 * 处理结果默认的枚举实现类
 * 
 * @author xiangzheng
 *
 */
public enum BaseResultEnum implements ResultInfo {
	/** 成功默认枚举类型 */
	SUCCESS("success", "success",true)
	/** 失败默认枚举类型 */
	,FAILED("error", "failed", false)
	/** 异常默认枚举类型 */
	,EXCEPTION("exception", "some exception happened ! but the error info not be init." ,false);

	/** 结果编码 */
	private String resultCode;
	
	/** 结果消息 */
	private String resultMsg;
	
	/** 处理成功状态 */
	private boolean isSuccess;
	
	/**
	 * 结果枚举构造器
	 * @param resultCode 结果状态吗
	 * @param resultMsg 结果信息
	 * @param successFlag 结果是否成功返回 
	 * <li>true 处理成功</li>
	 * <li>false 处理失败</li>
	 */
	private BaseResultEnum(String resultCode, String resultMsg ,boolean successFlag) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.isSuccess = successFlag;
	}

	@Override
	public String getCode() {
		return this.resultCode;
	}

	@Override
	public String getMsg() {
		return this.resultMsg;
	}

	@Override
	public boolean successFlag() {
		return this.isSuccess;
	}
}
