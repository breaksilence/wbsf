package com.wbsf.core.result.config;

import com.wbsf.core.result.ResultInfo;
import static com.wbsf.core.spring.utils.PropertyConfigurer.getProperty;
/**
 * 处理结果默认的枚举实现类
 * 
 * @author xiangzheng
 *
 */
public enum ResultBaseEnum implements ResultInfo {
	/** 成功默认枚举类型 */
	SUCCESS("success", getProperty("request.success", "操作成功！"), true),
	SUCCESS_UPLOAD_FILE("success", getProperty("request.fileUploadSuccess", "文件上传成功！"), true),
	/** 失败默认枚举类型 */
	FAILED("error", getProperty("request.failed", "操作失败！"), false),
	/** 异常默认枚举类型 */
	EXCEPTION("exception", getProperty("request.exception", "操作异常！"), false),
	/** 默认的文件上传过大响应信息 */
	FILE_MAX_UPLOAD_SIZE_EXCEEDED_EXCEPTION("exception", getProperty("request.fileMaxUploadSizeException","上传的文件过大！"), false),
	/** 上传文件不合法的响应信息 */
	ILLEGAL_FILE_TYPE_EXCEPTION("exception", getProperty("request.IllegalFileTypeException", "上传的文件类型非法"), false);

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
	private ResultBaseEnum(String resultCode, String resultMsg ,boolean successFlag) {
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
