package com.wbsf.core.config;

import com.wbsf.core.spring.utils.PropertyConfigurer;

/**
 * 系统默认配置
 * @author hubery
 *
 */
public enum FileOperationConfig implements Config {
	/** 系统session的key标识 */
	COMMON_UPLOAD_PATH(SystemConfig.FILEUPLOAD_TEMP_DIR.config(),"/temp");
	private String key;
	
	/** 若系统不存在默认值 */
	private String defualtValue;
	
	private FileOperationConfig(String key, String defualtValue) {
		this.key = key;
		this.defualtValue = defualtValue;
	}
	
	@Override
	public String key() {
		return key;
	}

	@Override
	public String config() {
		return PropertyConfigurer.getProperty(key, this.defualtValue);
	}
}
