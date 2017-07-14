package com.wbsf.common.utils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

/**
 * 提供常规的post 和 get请求的方法封装
 * 
 * @author xiangzheng
 *
 */
public class HttpClientUtil {
	private final static Logger logger = LogManager.getLogger(HttpClientUtil.class);
	/** HttpResponse实例 */
	private HttpResponse response;
	/** 请求参数 */
	private Map<String, String> paramsMap = new HashMap<String, String>();
	/** json param,该参数只适用与post请求 */
	private JSONObject jsonParam = new JSONObject(true);
	/** 用于存放返回json对象 */
	private JSONObject resultJson;
	/** 请求url地址 */
	private String url;

	/** 上传文件列表 */
	private List<FilePart> uploadFileList;   
	
	/**
	 * 用于存放响应的结果
	 */
	private String responEntity;

	/**
	 * 通过url构造HttpUtil
	 * 
	 * @param url
	 */
	public HttpClientUtil(String url) {
		this.url = url;
	}

	/**
	 * post 请求数据发送请求
	 * <li>jsonParam 通过json的形式传递数据参数，该参数与params参数互斥，当jsonParam不为空时，增优先采用</li>
	 * <li>params 通过常规post数据参数提交的方式发送请求，当设置jsonParam时，该参数会直接拼接到url中</li>
	 * @return 返回当前对象操作实例
	 */
	public HttpClientUtil doPost() {
			StringEntity paramEntity = null;
			if (jsonParam.isEmpty() && paramsMap != null && !paramsMap.isEmpty()) {
				List<BasicNameValuePair> values = new ArrayList<BasicNameValuePair>();
				for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
					values.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				paramEntity = new UrlEncodedFormEntity(values, StandardCharsets.UTF_8);
			}else{
				paramEntity = new StringEntity(jsonParam.toString(), StandardCharsets.UTF_8);// 解决中文乱码问题
				paramEntity.setContentEncoding("UTF-8");
				paramEntity.setContentType("application/json");
				//如果json参数不为空，则将paramsMap直接转换为url拼接的参数进行传递
				StringBuilder urlTemp = new StringBuilder(url);
				if (urlTemp.indexOf("?") == -1)
					urlTemp.append("?");
				for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
					urlTemp.append("&").append(entry.getKey()).append("=").append(entry.getValue());
				}
				this.url = urlTemp.toString();
			}
			HttpPost httpPost = new HttpPost(url);
			if (paramEntity != null)
				httpPost.setEntity(paramEntity);
			logger.info("Post 请求url：" + url + ",jsonParam:" + jsonParam + ",params:" + paramsMap);
			/** httpClient实例 */
			try(CloseableHttpClient client = HttpClientBuilder.create().build())
			{
				response = client.execute(httpPost);
				HttpEntity entity = response.getEntity();
				if (entity == null)
					return this;
				responEntity = EntityUtils.toString(entity);
				EntityUtils.consume(entity);
				if (getStatusCode() == 200) {
					logger.debug("请求成功.");
				}
				return this;
				
			} catch (Exception e) {
				logger.error("post请求未能正确处理!!!", e);
				return this;
			}
	}

	/**
	 * get 请求数据发送请求
	 * <li>jsonParam 在get请求中将被忽略</li>
	 * <li>params 该参数会直接拼接到url中</li>
	 * @return 返回当前对象操作实例
	 */
	public HttpClientUtil doGet() {
		if (paramsMap != null && !paramsMap.isEmpty()) {
			StringBuilder urlTemp = new StringBuilder(url);
			if (urlTemp.indexOf("?") == -1)
				urlTemp.append("?");
			for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
				urlTemp.append("&").append(entry.getKey()).append("=").append(entry.getValue());
			}
			this.url = urlTemp.toString();
		}
		logger.info("Get 请求url：" + url);
		HttpGet httpGet = new HttpGet(url);
		try(CloseableHttpClient client = HttpClientBuilder.create().build())
		{
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity == null)
				return this;
			responEntity = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
			if (getStatusCode() == 200) {
				logger.debug("请求成功.");
			}
			return this;
		} catch (Exception e) {
			logger.error("get请求未能正确处理!!!", e);
			return this;
		}
	}

	/**
	 * 上传文件
	 * @param  paramsMap {@link #paramsMap} 内部参数，通过{@link #putParam(String, String)}进行设置，用于传递文件上传参数 
	 * @return 返回当前对象操作实例
	 */
	public HttpClientUtil upload() {
		HttpPost post = new HttpPost(url);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
			builder.addTextBody(entry.getKey(), entry.getValue(), ContentType.APPLICATION_JSON);
		}
		
		if(uploadFileList == null || uploadFileList.isEmpty()){
			throw new RuntimeException("上传文件不能为空.");
		}
		
		int count=0;
		
		for (FilePart filepart:uploadFileList) {
			builder.addBinaryBody("file",filepart.getFile(), ContentType.MULTIPART_FORM_DATA, filepart.getUploadFileName());
			count++;
		}
		
		HttpEntity entity = builder.build();
		post.setEntity(entity);
		try(CloseableHttpClient client = HttpClientBuilder.create().build())
		{
			response = client.execute(post);
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				responEntity = EntityUtils.toString(resEntity);
				EntityUtils.consume(resEntity);
			} 
			if (getStatusCode() == 200) {
				logger.debug("文件上传成功，成功上传"+count+"个文件");
			}
			return this;
		} catch (Exception e) {
			throw new RuntimeException("上传文件失败.", e);
		}
	}
	
	/**
	 * 取得服务端回复的状态
	 */
	public String getStatus() {
		return response.getStatusLine().toString();
	}

	/**
	 * 取得状态码
	 */
	public int getStatusCode() {
		return response.getStatusLine().getStatusCode();
	}

	/**
	 * 获取请求的jsonParam参数
	 * 
	 * @return
	 */
	public JSONObject getJsonParam() {
		return jsonParam;
	}

	/**
	 * 设置参数
	 * 
	 * @param key
	 * @param value
	 */
	public HttpClientUtil putJsonParam(String key, Object value) {
		this.jsonParam.put(key, value);
		return this;
	}

	/**
	 * 获取请求的params参数
	 * 
	 * @return
	 */
	public Map<String, String> getParams() {
		return paramsMap;
	}

	/**
	 * 设置通用参数
	 * 
	 * @param key
	 * @param value
	 */
	public HttpClientUtil putParam(String key, String value) {
		this.paramsMap.put(key, value);
		return this;
	}
	
	/** 设置参数 */
	public HttpClientUtil putParam(Map<String, String> paramsMap){
		if(paramsMap != null && !paramsMap.isEmpty())
			this.paramsMap.putAll(paramsMap);
		return this;
		
	}
	
	public HttpClientUtil addUploadFile(File file, String uploadFileName) {
		if (this.uploadFileList == null) 
			this.uploadFileList = Lists.newArrayList();
		uploadFileList.add(new FilePart(uploadFileName, file));
		return this;
	}
	
	/**
	 * 返回原始的响应信息
	 * @return
	 */
	public String responEntity() {
		return this.responEntity;
	}
	
	/**
	 * 尝试将结果转换为JSONObject
	 * 
	 * @exception RuntimeException
	 *                当转化json数据错误时抛出该异常
	 * @return json格式化的JSONObject对象
	 */
	public JSONObject jsonResult() {
		if (StringUtils.isNotEmpty(responEntity)) {
			try{
				resultJson = JSONObject.parseObject(responEntity);
			}catch (Exception e) {
				throw new RuntimeException("获取json结果异常,返回的响应实体为"+responEntity,e);
			}
		}
		return this.resultJson;
	}
	
	/**
	 * 清空所有属性
	 */
	public void clear(){
		this.response = null;
		this.paramsMap.clear();
		this.jsonParam.clear();
		this.resultJson = null;
		responEntity = null;
		this.url = null;
	}
	
	/**
	 * 用于构建上传文件类型集合
	 * @author xiangzheng
	 *
	 */
	class FilePart {
		private String uploadFileName;
		private File file;
		public FilePart(String uploadFileName, File file){
			this.file = file;
			if (StringUtils.isBlank(uploadFileName)) {
				this.uploadFileName = file.getName();
			} else {
				this.uploadFileName = uploadFileName;
			}
		}
		public String getUploadFileName() {
			return uploadFileName;
		}
		
		public void setUploadFileName(String uploadFileName) {
			this.uploadFileName = uploadFileName;
		}
		
		public File getFile() {
			return file;
		}
		
		public void setFile(File file) {
			this.file = file;
		}
	}
}
