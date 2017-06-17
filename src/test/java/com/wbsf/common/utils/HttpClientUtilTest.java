package com.wbsf.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class HttpClientUtilTest {
	private static final Logger logger = LogManager.getLogger(HttpClientUtilTest.class);
	@Test
	public void testDoPost() {
		HttpClientUtil client = new HttpClientUtil("http://127.0.0.1:8080/wbsf/test/post").putJsonParam("testbody", "testbodyValue").putParam("param1","param1value");
		JSONObject jsonResult = client.doPost();
		logger.info(jsonResult.toJSONString());
	}

}
