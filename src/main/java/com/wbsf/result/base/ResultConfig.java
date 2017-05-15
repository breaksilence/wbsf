package com.wbsf.result.base;

import com.wbsf.result.ResultEnum;

/**
 * 处理结果默认的枚举实现类
 * 
 * @author xiangzheng
 *
 */
public enum ResultConfig implements ResultEnum {
	/** 成功默认枚举类型 */
	SUCCESS("success", "success",true)
	/** 失败默认枚举类型 */
	,FAILED("error", "failed", false)
	/** 异常默认枚举类型 */
	,EXCEPTION("exception", "some exception happened ! but the error not be init." ,false);

	/** 结果编码 */
	private String resultCode;
	
	/** 结果消息 */
	private String resultMsg;
	
	/** 处理成功状态 */
	private boolean isSuccess;

	private ResultConfig(String resultCode, String resultMsg ,boolean successFlag) {
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
