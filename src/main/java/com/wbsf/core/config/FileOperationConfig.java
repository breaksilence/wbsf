package com.wbsf.core.config;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.wbsf.core.spring.utils.PropertyConfigurer;

/**
 * 系统默认配置
 * @author hubery
 *
 */
public enum FileOperationConfig implements Config {
	/** 系统session的key标识 */
	COMMON_UPLOAD_PATH(SystemConfig.FILEUPLOAD_TEMP_DIR.config(),File.separatorChar+"temp");
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
	
	public String config(String key) {
		if (StringUtils.isNotBlank(key)){
			for (FileOperationConfig config : FileOperationConfig.values())  {
				if(config.key.equals(key)){
					return config.config();
				}
			}
		}
		return COMMON_UPLOAD_PATH.config();
	}
}
