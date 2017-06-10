package com.wbsf.core.config;

import com.google.common.util.concurrent.UncheckedExecutionException;
import com.wbsf.core.spring.utils.PropertyConfigurer;

/**
 * 系统默认配置
 * @author hubery
 *
 */
public enum SystemConfig implements Config {
	/** 系统session的key标识 */
	SESSION_KEY("sys.config.session.key",""),
	/** 系统用户的用户id */
	SYSTEM_USER_ID("sys.config.system.user",""),
	SESSION_TIMEOUT("sys.config.session.sessionTimeout", "1800"),
	
	/** 系统分页默认的每页条数 */
	PAGE_SIZE_DEFUALT("sys.config.page.defualt.pageSize", "10"),
	/** 系统分页导航默认的导航页码 */
	PAGE_NAVIGATEPAGES_DEFUALT("sys.config.page.defualt.navigatePages", "8"),
	
	/** 系统上传文件最大配置 */
	FILEUPLOAD_MAX_SIZE("sys.config.maxUploadSize", "10485760"),
	/** 文件上传内存最大缓存配置 */
	FILEUPLOAD_MAX_INMEMORY_SIZE("sys.config.maxInMemorySize", "4096"),
	/** 文件上传缓存目录 */
	FILEUPLOAD_TEMP_DIR("sys.config.uploadTempDir", "fileUpload/temp"),
	/** 文件上传根目录 */
	FILEUPLOAD_ROOT_STORAGE("sys.config.fileStoragePath", "fileUpload/resources/"),
	/** 文件上传允许上传的文件类型 */
	FILEUPLOAD_ALLOW_TYPE("sys.config.allowFileType", "jpg,gif,png,ico,bmp,jpeg");
	/** 系统配置KEY */
	private String key;
	
	/** 若系统不存在默认值 */
	private String defualtValue;
	
	private SystemConfig(String key, String defualtValue) {
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
	
	public int configInt() {
		try {
			return Integer.parseInt(config());
		} catch (Exception e) {
			throw new UncheckedExecutionException("key:"+key+"数据格式化为int异常，请检查配置文件",e);
		}
	}
	
	public long configLong() {
		try {
			return Long.parseLong(config());
		} catch (Exception e) {
			throw new UncheckedExecutionException("key:"+key+"数据格式化为long异常，请检查配置文件",e);
		}
	}
	
}
