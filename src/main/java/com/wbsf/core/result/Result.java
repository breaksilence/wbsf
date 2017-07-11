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
	 * 初始化rsult code、返回的消息信息以及结果状态
	 * 
	 * @param rsConfig
	 */
	Result<T> setResultConfig(ResultInfo resultEnum);

	/**
	 * 初始化rsult code和返回的消息信息
	 * @param code 返回状态码
	 * @param message 返回消息
	 * @param success 是否成功
	 */
	Result<T> setResultConfig(String code, String message);

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
	 * 获取返回的错误编码
	 * 
	 * @return
	 */
	String getCode();

	/**
	 * 格式化范围消息
	 * @param message 重置消息内容，格式化消息，为偷懒的人准备的
	 * @param formateValues
	 */
	Result<T> formateMessage(String message, Object ...  formateValues);
	
	/**
	 * 格式化范围消息
	 * @param formateValues
	 */
	Result<T> formateMessage(Object ...  formateValues);

	/**
	 * 获取返回的结果信息
	 * 
	 * @return
	 */
	String getMessage();
	
	/**
	 * 设置结果信息
	 * @return 返回result自身
	 */
	Result<T> setMessage(String message);

	/**
	 * 设置属性
	 * 
	 * @param atrribute
	 * @return
	 */
	Result<T> putAttributes(Map<String, ?> atrributes);

	/**
	 * 设置属性
	 * 
	 * @param atrributes
	 * @param clearAtrributes
	 *            是否在设置属性前清空所有属性
	 * @return
	 */
	Result<T> putAttributes(Map<String, ?> atrributes, boolean clearAtrributes);

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
	 * 清空结果对象
	 */
	Result<T> clearResult();
	
	/**
	 * 获取json
	 * 
	 * @return string
	 */
	String toJson();
}
