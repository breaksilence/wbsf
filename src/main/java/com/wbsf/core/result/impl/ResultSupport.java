package com.wbsf.core.result.impl;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.wbsf.core.result.Result;
import com.wbsf.core.result.ResultInfo;

/**
 * 基础实现类
 * @author xiangzheng
 *
 * @param <T>
 */
public abstract class ResultSupport<T> implements Result<T> {
	protected final static JSONObject resultJson = new JSONObject(true);
	/** 返回结果数据 */
	protected T result;
	
	/** 处理是否成功 */
	protected boolean success = true;
	
	/** 请求处理返回的状态码 */
	protected String code;
	
	/** 请求处理返回的消息 */
	protected String message;
	
	/** 处理过过程中设置的扩展属性 */
	protected Map<String ,Object> atrributes = new LinkedHashMap<>();
	
	protected ResultSupport() {}
	
	/**
	 * 根据result枚举类型构造result实例
	 * @param resultEnum
	 */
	public ResultSupport(ResultInfo resultEnum) {
		setResultConfig(resultEnum);
	}
	
	/**
	 * 受保护的方法，改变处理结果的状态
	 * @param success
	 */
	protected void setSuccess(boolean success) {
		this.success = success;
	}
	
	@Override
	public Result<T> setResultConfig(ResultInfo resultEnum){
		return setResultConfig(resultEnum.getCode() ,resultEnum.getMsg() ,resultEnum.successFlag());
	}
	
	@Override
	public Result<T> setResultConfig(String Code, String Message, boolean success) {
		this.code = Code;
		this.message = Message;
		this.success = success;
		return this;
	}
	
	@Override
	public Result<T> setResult(T result) {
		this.result = result;
		return this;
	}

	@Override
	public T getResult() {
		return this.result;
	}

	@Override
	public boolean failed() {
		return success != true ? true : false;
	}

	@Override
	public boolean success() {
		return success;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public Result<T> formateMessage(String message, Object ...  formateValues) {
		this.message = message;
		return formateMessage(formateValues);
	}
	
	@Override
	public Result<T> formateMessage(Object ...  formateValues) {
		if(formateValues != null){
			this.message = MessageFormat.format(this.getMessage(), formateValues);
		}
		return this;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public Result<T> putAttributes(Map<String, Object> atrributes) {
		return this.putAttributes(atrributes, false);
	}

	@Override
	public Result<T> putAttributes(Map<String, Object> atrributes, boolean clearAtrributes) {
		if (clearAtrributes)
			this.atrributes.clear();
		if (atrributes != null && !atrributes.isEmpty())
			this.atrributes.putAll(atrributes);
		return this;
	}

	@Override
	public Result<T> putAttribute(String attributeKey, Object attributeValue) {
		if (attributeKey != null && !attributeKey.trim().isEmpty()) {
			this.atrributes.put(attributeKey, attributeValue);
		}
		return this;

	}

	@Override
	public Map<String ,Object> getAttributes() {
		return this.atrributes;
	}

	@Override
	public Object getAttributeValue(String attributeKey) {
		return this.atrributes.get(attributeKey);
	}

	@Override
	public void clearAttributes() {
		this.getAttributes().clear();
	}

	@Override
	public String toJson() {
		resultJson.put("isSuccess", this.success);
		resultJson.put("code", this.code);
		resultJson.put("message", this.message);
		if(result != null)
			resultJson.put("result", this.result);
		if(!atrributes.isEmpty())
			resultJson.put("atrributes", this.atrributes);
		return resultJson.toJSONString();
	}

	@Override
	public String toString() {
		return toJson();
	}
}
