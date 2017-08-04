package com.wbsf.core.spring.utils;

import java.text.MessageFormat;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * spring properties配置文件读取类
 * 
 * @author xiangzheng
 *
 */
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {
	private static Properties props; // 存取properties配置文件key-value结果
	private static final Logger logger = LogManager.getLogger(PropertyConfigurer.class.getName());

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		if (PropertyConfigurer.props == null)
			PropertyConfigurer.props = props;
		logger.info("配置文件加载状态为：" + getProperty("sys.config.property.init.check", "init properties failed"));
	}

	/**
	 * 通过key获取value
	 * 
	 * @param key
	 *            properties key
	 * @return 返回配置文件key所对应的值
	 */
	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	/**
	 * 根据key 获取value,如果没有获取到设置为默认值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return 返回配置文件key所对应的值，如果没有返回defualt
	 */
	public static String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}
	
	/**
	 * 根据key 获取value,如果没有获取到设置为默认值
	 * 
	 * @param key 配置文件的key
	 * @param defaultValue 默认值
	 * @return 返回配置文件key所对应的值，如果没有返回defualt
	 */
	public static String getProperty(String key,Object[] formateArgs){
		String value = getProperty(key);
		return MessageFormat.format(value,formateArgs);
	}
	
	/**
	 * 根据key 获取value,如果没有获取到设置为默认值
	 * 
	 * @param key 配置文件的key
	 * @param defaultValue 默认值
	 * @param formateArgs 占位符格式化参数
	 * @return 返回配置文件key所对应的值，如果没有返回defualt
	 */
	public static String getProperty(String key, String defaultValue,Object[] formateArgs){
		String value = getProperty(key, defaultValue);
		return MessageFormat.format(value,formateArgs);
	}
}
