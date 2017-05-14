package com.wbsf.result.base;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import com.wbsf.result.Result;

public abstract class ResultSupport<T> implements Result<T> {

	protected T result;
	protected boolean success = true;
	protected String resultCode;
	protected String resultMsg;
	private Map<String, Object> atrributes = new LinkedHashMap<>();
	
	public void setResultConfig(ResultConfig rsConfig){
		this.resultCode = rsConfig.getCode();
		this.resultMsg = rsConfig.getMsg();
	}
	
	@Override
	public T setResult(T result) {
		this.result = result;
		return this.result;
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
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	@Override
	public String getResultCode() {
		return this.resultCode;
	}

	@Override
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;

	}

	@Override
	public String getResultMsg() {
		return this.resultMsg;
	}

	@Override
	public Map<String, Object> setAttributes(Map<String, ?> atrributes) {
		return this.setAttributes(atrributes, false);
	}

	@Override
	public Map<String, Object> setAttributes(Map<String, ?> atrributes, boolean clearAtrributes) {
		if (clearAtrributes)
			this.atrributes.clear();
		if (atrributes != null && !atrributes.isEmpty())
			this.atrributes.putAll(atrributes);
		return this.atrributes;
	}

	@Override
	public Map<String, Object> setAttribute(String attributeKey, Object attributeValue) {
		if (attributeKey != null && !attributeKey.trim().isEmpty()) {
			this.atrributes.put(attributeKey, attributeValue);
		}
		return this.atrributes;

	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.atrributes;
	}

	@Override
	public Object getAttributeValue(String attributeKey) {
		return this.atrributes.get(attributeKey);
	}

	@Override
	public String toJson() {
		JSONObject result =  new JSONObject();
		result.put("resultCode", this.resultCode);
		result.put("resultMsg", this.resultMsg);
		result.put("result", this.result);
		result.put("atrributes", this.atrributes);
		return result.toString();
	}

}
