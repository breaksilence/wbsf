package com.wbsf.common.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * 提供常规的post 和 get请求的方法封装
 * 
 * @author xiangzheng
 *
 */
public class HttpClientUtil {
	private static Logger logger = LogManager.getLogger(HttpClientUtil.class);
	/** httpClient实例 */
	private HttpClient client = HttpClientBuilder.create().build();
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
	 * <li>params
	 * 通过常规post数据参数提交的方式发送请求，该参数与jsonParam参数互斥，当设置jsonParam时，该参数再post提交时无效</li>
	 */
	public JSONObject doPost() {
		HttpPost httpPost = new HttpPost(url);
		try {
			StringEntity paramEntity = null;
			if (jsonParam != null && !jsonParam.isEmpty()) {
				paramEntity = new StringEntity(jsonParam.toString(), StandardCharsets.UTF_8);// 解决中文乱码问题
				paramEntity.setContentEncoding("UTF-8");
				paramEntity.setContentType("application/json");
			}
			
			if (jsonParam.isEmpty() && paramsMap != null && !paramsMap.isEmpty()) {
				List<BasicNameValuePair> values = new ArrayList<BasicNameValuePair>();
				for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
					values.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				paramEntity = new UrlEncodedFormEntity(values, StandardCharsets.UTF_8);
			}else{
				//如果json参数不为空，则将paramsMap直接转换为url拼接的参数进行传递
				StringBuilder urlTemp = new StringBuilder(url);
				if (urlTemp.indexOf("?") == -1)
					urlTemp.append("?");
				for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
					urlTemp.append("&").append(entry.getKey()).append("=").append(entry.getValue());
				}
				this.url = urlTemp.toString();
			}
			
			if (paramEntity != null)
				httpPost.setEntity(paramEntity);
			logger.info("Post 请求url：" + url + ",jsonParam:" + jsonParam + ",params:" + paramsMap);
			response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity == null)
				return null;
			String entityValue = EntityUtils.toString(entity);
			if (StringUtils.isNotEmpty(entityValue)) {
				resultJson = JSONObject.parseObject(entityValue);
			}
			EntityUtils.consume(entity);
			return this.resultJson;
		} catch (Exception e) {
			logger.error("请求未能正确处理!!!", e);
			return null;
		}
	}

	/**
	 * get 请求数据
	 */
	public JSONObject doGet() {
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
		try {
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity == null)
				return null;
			String entityValue = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
			if (StringUtils.isNotEmpty(entityValue)) {
				resultJson = JSONObject.parseObject(entityValue);
			}
			return this.resultJson;
		} catch (Exception e) {
			logger.error("请求未能正确处理!!!", e);
			return null;
		}
	}

	/**
	 * 上传文件
	 */
	public void upload(String url, File file) {
		HttpPost httppost = new HttpPost(url);
		FileBody bin = new FileBody(file);
		StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);

		HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();

		httppost.setEntity(reqEntity);

		HttpEntity resEntity = response.getEntity();
		try {
			response = client.execute(httppost);
			if (resEntity != null) {
				System.out.println("Response content length: " + resEntity.getContentLength());
			}
			EntityUtils.consume(resEntity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
	public String getStatusCode() {
		return Integer.valueOf(response.getStatusLine().getStatusCode()).toString();
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
	
	public HttpClientUtil putParam(Map<String, String> paramsMap){
		if(paramsMap != null && !paramsMap.isEmpty())
			this.paramsMap.putAll(paramsMap);
		return this;
		
	}
	
	/**
	 * 清空所有属性
	 */
	public void clear(){
		this.response = null;
		this.paramsMap.clear();
		this.jsonParam.clear();
		this.resultJson = null;
		this.url = null;
	}
}
