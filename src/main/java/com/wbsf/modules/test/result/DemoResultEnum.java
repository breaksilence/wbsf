package com.wbsf.modules.test.result;

import com.wbsf.core.result.ResultInfo;

public enum DemoResultEnum implements ResultInfo {
	/** 成功默认枚举类型 */
	SUCCESS("success", "success");
	/** 结果编码 */
	private String resultCode;
	
	/** 结果消息 */
	private String resultMsg;
	
	/**
	 * 结果枚举构造器
	 * @param resultCode 结果状态吗
	 * @param resultMsg 结果信息
	 * @param successFlag 结果是否成功返回 
	 * <li>true 处理成功</li>
	 * <li>false 处理失败</li>
	 */
	private DemoResultEnum(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	@Override
	public String getCode() {
		return this.resultCode;
	}

	@Override
	public String getMsg() {
		return this.resultMsg;
	}
}
