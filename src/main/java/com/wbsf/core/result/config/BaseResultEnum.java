package com.wbsf.core.result.config;

import com.wbsf.core.result.ResultInfo;
import com.wbsf.core.spring.utils.PropertyConfigurer;

/**
 * 处理结果默认的枚举实现类
 * 
 * @author xiangzheng
 *
 */
public enum BaseResultEnum implements ResultInfo {
	/** 成功默认枚举类型 */
	SUCCESS("success", PropertyConfigurer.getProperty("request.success", "操作成功！"), true)
	/** 失败默认枚举类型 */
	,FAILED("error", PropertyConfigurer.getProperty("request.failed", "操作失败！"), false)
	/** 异常默认枚举类型 */
	,EXCEPTION("exception", PropertyConfigurer.getProperty("request.exception", "操作异常！"), false)
	,FILE_MAX_UPLOAD_SIZE_EXCEEDED_EXCEPTION("exception", PropertyConfigurer.getProperty("request.fileMaxUploadSizeException","上传的文件过大！"), false)
	,ILLEGAL_FILE_TYPE_EXCEPTION("exception", PropertyConfigurer.getProperty("request.IllegalFileTypeException", "上传的文件类型非法"), false);

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
