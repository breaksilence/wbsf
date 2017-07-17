package com.wbsf.core.config;

import java.io.File;

import org.apache.commons.lang.StringUtils;

/**
 * 系统默认配置
 * @author hubery
 *
 */
public enum FileOperationConfig implements Config {
	/** 系统临时文件上传路径*/
	UPLOAD_TEMP_PATH(SystemConfig.FILEUPLOAD_TEMP_DIR.config(),File.separatorChar+"temp"),
	UPLOAD_DEFUALT_PATH(SystemConfig.FILEUPLOAD_ROOT_STORAGE.config(),File.separatorChar+"temp");
	private String key;
	
	/** 若系统不存在默认值 */
	private String config;
	
	private FileOperationConfig(String key, String config) {
		this.key = key;
		this.config = config;
	}
	
	@Override
	public String key() {
		return key;
	}

	@Override
	public String config() {
		return this.config;
	}
	
	public String config(String key) {
		if (StringUtils.isNotBlank(key)){
			for (FileOperationConfig config : FileOperationConfig.values())  {
				if(config.key.equals(key)){
					return config.config();
				}
			}
		}
		return UPLOAD_TEMP_PATH.config();
	}
}
