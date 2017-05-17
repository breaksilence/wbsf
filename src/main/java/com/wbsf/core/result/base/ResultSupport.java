package com.wbsf.core.result.base;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.wbsf.core.result.Result;
import com.wbsf.core.result.ResultEnum;

/**
 * 基础实现类
 * @author xiangzheng
 *
 * @param <T>
 */
public abstract class ResultSupport<T> implements Result<T> {
	protected JSONObject resultJson = new JSONObject(true);
	/** 返回结果数据 */
	protected T result;
	
	/** 处理是否成功 */
	protected boolean success = true;
	
	/** 请求处理返回的状态码 */
	protected String resultCode;
	
	/** 请求处理返回的消息 */
	protected String resultMsg;
	
	/** 处理过过程中设置的扩展属性 */
	private Map<String ,Object> atrributes = new LinkedHashMap<>();
	
	protected ResultSupport() {
		this.success = true;
	}
	
	protected ResultSupport(ResultEnum resultEnum) {
		setResultConfig(resultEnum);
	}
	
	@Override
	public Result<T> setResultConfig(ResultEnum resultEnum){
		return setResultConfig(resultEnum.getCode() ,resultEnum.getMsg() ,resultEnum.successFlag());
	}
	
	@Override
	public Result<T> setResultConfig(String resultCode, String resultMsg) {
		return setResultConfig(resultCode ,resultMsg ,this.success);
	}

	protected  Result<T> setResultConfig(String resultCode, String resultMsg ,boolean success) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
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
	public Result<T> setResultCode(String resultCode) {
		this.resultCode = resultCode;
		return this;
	}

	@Override
	public String getResultCode() {
		return this.resultCode;
	}

	@Override
	public Result<T> setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
		return this;
	}
	
	@Override
	public Result<T> setResultMsg(String resultMsg, Object ...  formateValues) {
		return this.setResultMsg(MessageFormat.format(resultMsg, formateValues));
	}

	@Override
	public Result<T> setResultMsg(Object ...  formateValues) {
		this.setResultMsg(this.getResultMsg(), formateValues);
		return this;
	}

	@Override
	public String getResultMsg() {
		return this.resultMsg;
	}

	@Override
	public Result<T> putAttributes(Map<String, ?> atrributes) {
		return this.putAttributes(atrributes, false);
	}

	@Override
	public Result<T> putAttributes(Map<String, ?> atrributes, boolean clearAtrributes) {
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
		resultJson.put("resultCode", this.resultCode);
		resultJson.put("resultMsg", this.resultMsg);
		resultJson.put("result", this.result);
		resultJson.put("atrributes", this.atrributes);
		return resultJson.toJSONString();
	}

	@Override
	public String toString() {
		return toJson();
	}
	/*Map<String,User> result = mapper.readValue(src, new TypeReference<Map<String,User>>() { });*/
}
