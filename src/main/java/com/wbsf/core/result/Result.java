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
	public Result<T> setResultConfig(ResultEnum resultEnum);

	/**
	 * 初始化rsult code和返回的消息信息
	 * @param resultCode 返回状态码
	 * @param resultMsg 返回消息
	 */
	public Result<T> setResultConfig(String resultCode ,String resultMsg);

	/**
	 * 设置结果
	 * 
	 * @param result
	 * @return
	 */
	public Result<T> setResult(T result);

	/**
	 * 获取处理结果
	 * 
	 * @return
	 */
	public T getResult();

	/**
	 * 判断是否失败 失败为true
	 * 
	 * @return
	 */
	public boolean failed();

	/**
	 * 判断是否成功 成功为true
	 * 
	 * @return boolean
	 */
	public boolean success();

	/**
	 * 设置结果编码
	 * 
	 * @return Result<T>
	 */
	public Result<T> setResultCode(String resultCode);

	/**
	 * 获取返回的错误编码
	 * 
	 * @return
	 */
	public String getResultCode();

	/**
	 * 设置返回的结果信息
	 */
	public Result<T> setResultMsg(String resultMsg);

	/**
	 * 设置返回的结果信息
	 * @param resultMsg 设置返回消息
	 * @param formateValues 格式化参数
	 */
	public Result<T> setResultMsg(String resultMsg ,Object ...  formateValues);
	
	/**
	 * 格式化范围消息
	 * @param formateValues
	 */
	public Result<T> setResultMsg(Object ...  formateValues);

	/**
	 * 获取返回的结果信息
	 * 
	 * @return
	 */
	public String getResultMsg();

	/**
	 * 设置属性
	 * 
	 * @param atrribute
	 * @return
	 */
	public Result<T> putAttributes(Map<String, Object> atrributes);

	/**
	 * 设置属性
	 * 
	 * @param atrributes
	 * @param clearAtrributes
	 *            是否在设置属性前清空所有属性
	 * @return
	 */
	public Result<T> putAttributes(Map<String, Object> atrributes, boolean clearAtrributes);

	/**
	 * 设置属性
	 * 
	 * @param attributeKey
	 * @param attributeValue
	 * @return
	 */
	public Result<T> putAttribute(String attributeKey, Object attributeValue);

	/**
	 * 获取属性集合
	 * 
	 * @return Map
	 */
	public Map<String, ?> getAttributes();

	/**
	 * 获取属性对象
	 * 
	 * @return
	 */
	public Object getAttributeValue(String attributeKey);
	
	/**
	 * 清空attribute的所有属性值
	 */
	public void clearAttributes();
	
	/**
	 * 获取json
	 * 
	 * @return string
	 */
	public String toJson();
}
