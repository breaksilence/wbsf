package com.wbsf.core.result;

import java.util.Map;

/**
 * 结果辅助工具类
 * 
 * @author hubery
 *
 * @param <T>
 */
public interface Result<T> {

	/**
	 * 初始化rsult code和返回的消息信息
	 * 
	 * @param rsConfig
	 */
	Result<T> setResultConfig(ResultEnum resultEnum);

	/**
	 * 初始化rsult code和返回的消息信息
	 * @param Code 返回状态码
	 * @param Message 返回消息
	 */
	Result<T> setResultConfig(String Code ,String Message);

	/**
	 * 设置结果
	 * 
	 * @param result
	 * @return
	 */
	Result<T> setResult(T result);

	/**
	 * 获取处理结果
	 * 
	 * @return
	 */
	T getResult();

	/**
	 * 判断是否失败 失败为true
	 * 
	 * @return
	 */
	boolean failed();

	/**
	 * 判断是否成功 成功为true
	 * 
	 * @return boolean
	 */
	boolean success();

	/**
	 * 设置结果编码
	 * 
	 * @return Result<T>
	 */
	Result<T> setCode(String Code);

	/**
	 * 获取返回的错误编码
	 * 
	 * @return
	 */
	String getCode();

	/**
	 * 设置返回的结果信息
	 */
	Result<T> setMessage(String Message);

	/**
	 * 设置返回的结果信息
	 * @param Message 设置返回消息
	 * @param formateValues 格式化参数
	 */
	Result<T> setMessage(String Message ,Object ...  formateValues);
	
	/**
	 * 格式化范围消息
	 * @param formateValues
	 */
	Result<T> setMessage(Object ...  formateValues);

	/**
	 * 获取返回的结果信息
	 * 
	 * @return
	 */
	String getMessage();

	/**
	 * 设置属性
	 * 
	 * @param atrribute
	 * @return
	 */
	Result<T> putAttributes(Map<String, Object> atrributes);

	/**
	 * 设置属性
	 * 
	 * @param atrributes
	 * @param clearAtrributes
	 *            是否在设置属性前清空所有属性
	 * @return
	 */
	Result<T> putAttributes(Map<String, Object> atrributes, boolean clearAtrributes);

	/**
	 * 设置属性
	 * 
	 * @param attributeKey
	 * @param attributeValue
	 * @return
	 */
	Result<T> putAttribute(String attributeKey, Object attributeValue);

	/**
	 * 获取属性集合
	 * 
	 * @return Map
	 */
	Map<String, ?> getAttributes();

	/**
	 * 获取属性对象
	 * 
	 * @return
	 */
	Object getAttributeValue(String attributeKey);
	
	/**
	 * 清空attribute的所有属性值
	 */
	void clearAttributes();
	
	/**
	 * 获取json
	 * 
	 * @return string
	 */
	String toJson();
}
