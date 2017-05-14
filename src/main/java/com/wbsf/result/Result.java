package com.wbsf.result;

import java.util.Map;

import com.wbsf.result.base.ResultConfig;

/**
 * 结果辅助工具类
 * @author hubery
 *
 * @param <T>
 */
public interface Result<T> {
	
	/**
	 * 初始化rsult code和返回的消息信息
	 * @param rsConfig
	 */
	public void setResultConfig(ResultConfig rsConfig);
	
	/**
	 * 设置结果
	 * @param result
	 * @return
	 */
	public T setResult(T result);
	
	/**
	 * 获取处理结果
	 * @return
	 */
	public T getResult();
	
	/**
	 * 判断是否失败 失败为true
	 * @return
	 */
	public boolean failed();
	
	/**
	 * 判断是否成功 成功为true
	 * @return
	 */
	public boolean success(); 
	
	/**
	 * 设置结果编码
	 * @return
	 */
	public void setResultCode(String resultCode);
	
	/**
	 * 获取返回的错误编码
	 * @return
	 */
	public String getResultCode();
	
	/**
	 * 设置返回的结果信息
	 */
	public void setResultMsg(String resultMsg);
	
	/**
	 * 获取返回的结果信息
	 * @return
	 */
	public String getResultMsg();
	
	/**
	 * 设置属性
	 * @param atrribute
	 * @return 
	 */
	public Map<String, Object> setAttributes(Map<String ,?> atrributes);
	
	/**
	 * 设置属性
	 * @param atrributes
	 * @param clearAtrributes 是否在设置属性前清空所有属性
	 * @return
	 */
	public Map<String, Object> setAttributes(Map<String, ?> atrributes, boolean clearAtrributes);
	
	/**
	 * 设置属性
	 * @param attributeKey
	 * @param attributeValue
	 * @return 
	 */
	public Map<String, Object> setAttribute(String attributeKey ,Object attributeValue);
	/**
	 * 获取属性集合
	 * @return Map
	 */
	public Map<String ,?> getAttributes();
	
	/**
	 * 获取属性对象
	 * @return
	 */
	public Object getAttributeValue(String attributeKey);
	
	/**
	 * 获取json
	 * @return string
	 */
	public String toJson();
}
