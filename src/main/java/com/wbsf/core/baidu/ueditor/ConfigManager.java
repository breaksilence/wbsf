package com.wbsf.core.baidu.ueditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wbsf.core.baidu.ueditor.define.ActionMap;

/**
 * 配置管理器
 * @author hancong03@baidu.com
 *
 */
public final class ConfigManager {

	private final String rootPath;
	@SuppressWarnings("unused")
	private final String contextPath;
	
	private final String defualtUrlPrefix;
	
	private JSONObject jsonConfig = null;
	// 涂鸦上传filename定义
	private final static String SCRAWL_FILE_NAME = "scrawl";
	// 远程图片抓取filename定义
	private final static String REMOTE_FILE_NAME = "remote";
	//config 资源路径
	private final static String CONFIG_URI = "classpath:config/init/ueditorConfig.json";
	
	
	private static ConfigManager instance = null;
	
	/*
	 * 通过一个给定的路径构建一个配置管理器， 该管理器要求地址路径所在目录下必须存在config.properties文件
	 */
	private ConfigManager ( String rootPath, String contextPath) throws FileNotFoundException, IOException {
		
		rootPath = rootPath.replace( "\\", "/" );
		
		this.rootPath = rootPath;
		this.contextPath = contextPath;
		
		this.initEnv();
		// 初始化默认的url
		defualtUrlPrefix = this.jsonConfig.getString("defualtUrlPrefix")+"/";
	}
	
	/**
	 * 配置管理器构造工厂
	 * @param rootPath 服务器根路径
	 * @param contextPath 服务器所在项目路径
	 * @param uri 当前访问的uri
	 * @return 配置管理器实例或者null
	 */
	public static ConfigManager getInstance ( String rootPath, String contextPath) {
		
		try {
			if (instance == null) {
				instance = new ConfigManager(rootPath, contextPath);
			}
			return instance;
			
		} catch ( Exception e ) {
			return null;
		}
		
	}
	
	/**
	 * 获取默认的prefixUrl 用于统一控制地址前缀，目前图片、附件列表应用到了
	 */
	public static String getDefualtUrlPrefix(){
		return instance.defualtUrlPrefix;
	}
	
	// 验证配置文件加载是否正确
	public boolean valid () {
		return instance.jsonConfig != null;
	}
	
	public JSONObject getAllConfig () {
		
		return instance.jsonConfig;
		
	}
	
	public Map<String, Object> getConfig ( int type ) {
		
		Map<String, Object> conf = new HashMap<String, Object>();
		String savePath = null;
		
		switch ( type ) {
		
			case ActionMap.UPLOAD_FILE:
				conf.put( "isBase64", "false" );
				conf.put( "maxSize", instance.jsonConfig.getLong( "fileMaxSize" ) );
				conf.put( "allowFiles", instance.getArray( "fileAllowFiles" ) );
				conf.put( "fieldName", instance.jsonConfig.getString( "fileFieldName" ) );
				savePath = instance.jsonConfig.getString( "filePathFormat" );
				break;
				
			case ActionMap.UPLOAD_IMAGE:
				conf.put( "isBase64", "false" );
				conf.put( "maxSize", instance.jsonConfig.getLong( "imageMaxSize" ) );
				conf.put( "allowFiles", instance.getArray( "imageAllowFiles" ) );
				conf.put( "fieldName", instance.jsonConfig.getString( "imageFieldName" ) );
				savePath = instance.jsonConfig.getString( "imagePathFormat" );
				break;
				
			case ActionMap.UPLOAD_VIDEO:
				conf.put( "maxSize", instance.jsonConfig.getLong( "videoMaxSize" ) );
				conf.put( "allowFiles", instance.getArray( "videoAllowFiles" ) );
				conf.put( "fieldName", instance.jsonConfig.getString( "videoFieldName" ) );
				savePath = instance.jsonConfig.getString( "videoPathFormat" );
				break;
				
			case ActionMap.UPLOAD_SCRAWL:
				conf.put( "filename", ConfigManager.SCRAWL_FILE_NAME );
				conf.put( "maxSize", instance.jsonConfig.getLong( "scrawlMaxSize" ) );
				conf.put( "fieldName", instance.jsonConfig.getString( "scrawlFieldName" ) );
				conf.put( "isBase64", "true" );
				savePath = instance.jsonConfig.getString( "scrawlPathFormat" );
				break;
				
			case ActionMap.CATCH_IMAGE:
				conf.put( "filename", ConfigManager.REMOTE_FILE_NAME );
				conf.put( "filter", instance.getArray( "catcherLocalDomain" ) );
				conf.put( "maxSize", instance.jsonConfig.getLong( "catcherMaxSize" ) );
				conf.put( "allowFiles", instance.getArray( "catcherAllowFiles" ) );
				conf.put( "fieldName", instance.jsonConfig.getString( "catcherFieldName" ) + "[]" );
				savePath = instance.jsonConfig.getString( "catcherPathFormat" );
				break;
				
			case ActionMap.LIST_IMAGE:
				conf.put( "allowFiles", instance.getArray( "imageManagerAllowFiles" ) );
				conf.put( "dir", instance.jsonConfig.getString( "imageManagerListPath" ) );
				conf.put( "count", instance.jsonConfig.getIntValue( "imageManagerListSize" ) );
				break;
				
			case ActionMap.LIST_FILE:
				conf.put( "allowFiles", instance.getArray( "fileManagerAllowFiles" ) );
				conf.put( "dir", instance.jsonConfig.getString( "fileManagerListPath" ) );
				conf.put( "count", instance.jsonConfig.getIntValue( "fileManagerListSize" ) );
				break;
				
		}
		
		conf.put( "savePath", savePath );
		conf.put( "rootPath", instance.rootPath );
		
		return conf;
		
	}
	
	private void initEnv () throws FileNotFoundException, IOException {
		
		File file = ResourceUtils.getFile(CONFIG_URI);;
		
		if ( !file.isAbsolute() ) {
			file = new File( file.getAbsolutePath() );
		}
		
		String configContent = this.readFile( file.getAbsolutePath() );
		
		try{
			JSONObject jsonConfig = JSONObject.parseObject(configContent);
			this.jsonConfig = jsonConfig;
		} catch ( Exception e ) {
			this.jsonConfig = null;
		}
		
	}

	private String[] getArray ( String key ) {
		
		JSONArray jsonArray = this.jsonConfig.getJSONArray( key );
		String[] result = new String[ jsonArray.size()];
		for ( int i = 0, len = jsonArray.size(); i < len; i++ ) {
			result[i] = jsonArray.getString( i );
		}
		
		return result;
		
	}
	
	private String readFile ( String path ) throws IOException {
		
		StringBuilder builder = new StringBuilder();
		
		try (InputStreamReader reader = new InputStreamReader( new FileInputStream( path ), "UTF-8" );
			BufferedReader bfReader = new BufferedReader( reader );)
		{
			String tmpContent = null;
			
			while ( ( tmpContent = bfReader.readLine() ) != null ) {
				builder.append( tmpContent );
			}
			
		} catch ( UnsupportedEncodingException e ) {
			// 忽略
		}
		
		return this.filter( builder.toString() );
		
	}
	
	// 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
	private String filter ( String input ) {
		
		return input.replaceAll( "/\\*[\\s\\S]*?\\*/", "" );
		
	}
	
}
