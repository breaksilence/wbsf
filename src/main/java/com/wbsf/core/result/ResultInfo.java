package com.wbsf.core.result;

/**
 * 处理结果枚举接口，自定义枚举类型可以实现该接口
 * 
 * @author xiangzheng
 *
 */
public interface ResultInfo {

	/**
	 * 获取结果状态码
	 * 
	 * @return String
	 */
	String getCode();

	/**
	 * 获取结果附加信息
	 * 
	 * @return String
	 */
	String getMsg();
	
	/**
	 * 获取成功状态
	 * @return boolean
	 */
	boolean successFlag();
}
