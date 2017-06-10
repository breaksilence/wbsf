package com.wbsf.core.config;

/**
 * 处理结果枚举接口，自定义枚举类型可以实现该接口
 * 
 * @author xiangzheng
 *
 */
public interface Config {
	/**
	 * 获取配置的key
	 * @return 配置的key
	 */
	String key();
	
	/**
	 * 获取配置的value
	 * @return 返回值
	 */
	String config();
}