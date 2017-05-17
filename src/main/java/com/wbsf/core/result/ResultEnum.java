package com.wbsf.result;

/**
 * 处理结果枚举接口，自定义枚举类型可以实现该接口
 * 
 * @author xiangzheng
 *
 */
public interface ResultEnum {

	/**
	 * 获取结果状态码
	 * 
	 * @return String
	 */
	public String getCode();

	/**
	 * 获取结果附加信息
	 * 
	 * @return String
	 */
	public String getMsg();
	
	/**
	 * 获取成功状态
	 * @return boolean
	 */
	public boolean successFlag();
}
