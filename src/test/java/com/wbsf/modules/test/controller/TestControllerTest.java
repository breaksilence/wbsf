package com.wbsf.modules.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import com.wbsf.core.test.JunitTestSupport;

public class TestControllerTest extends JunitTestSupport{
	
	@Before
	public void setUp() {
		super.setUp();
	}
	
	/**
	 * 测试code不符合条件插入，验证不通过
	 * @throws Exception
	 */
	@Test
	public void testInsertTest() throws Exception {
		logger.info("测试demo insert");
		Long startTime = System.currentTimeMillis();
		logger.info("测试demo insert---start>>>time:"+startTime);
		this.mockMvc
				.perform(post("/test/insert").characterEncoding("UTF-8") 
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.param("name", "testName01")
						.param("code", "11")
						)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.resultCode", is("error")))
				.andExpect(jsonPath("$.isSuccess", is(false)));
		logger.info("测试demo insert---end>>>time:"+(System.currentTimeMillis()-startTime));
	}
	
	/**
	 * 验证正常插入数据
	 * @throws Exception
	 */
	@Test
	public void testInsertTest1() throws Exception {
		logger.info("测试demo insert");
		Long startTime = System.currentTimeMillis();
		logger.info("测试demo insert---start>>>time:"+startTime);
		this.mockMvc
		.perform(post("/test/insert").characterEncoding("UTF-8") 
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.param("name", "testName01")
				.param("code", "testCode01")
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.resultCode", is("success")))
		.andExpect(jsonPath("$.isSuccess", is(true)));
		logger.info("测试demo insert---end>>>time:"+(System.currentTimeMillis()-startTime));
	}
	
	/**
	 * 验证正常插入数据
	 * @throws Exception
	 */
	@Test
	public void testDateConvert() throws Exception {
		logger.info("测试demo insert");
		Long startTime = System.currentTimeMillis();
		logger.info("测试demo insert---start>>>time:"+startTime);
		this.mockMvc
		.perform(post("/test/insert").characterEncoding("UTF-8") 
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.param("name", "testName01")
				.param("code", "testCode01")
				.param("modifyTime", "2017-05-18 00:19:21")
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.resultCode", is("success")))
		.andExpect(jsonPath("$.isSuccess", is(true)));
		logger.info("测试demo insert---end>>>time:"+(System.currentTimeMillis()-startTime));
	}

	/**
	 * 测试英文国际化
	 * @throws Exception
	 */
	@Test
	public void i18nTesten_US() throws Exception{
		Long startTime = System.currentTimeMillis();
		logger.info("测试国际化---start>>>time:"+startTime);
		this.mockMvc
		.perform(post("/test/i18n").characterEncoding("UTF-8") 
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.param("locale", "en_US")
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.resultCode", is("success")))
		.andExpect(jsonPath("$.isSuccess", is(true)));
		logger.info("测试国际化---end>>>time:"+(System.currentTimeMillis()-startTime));
	}
	
	/**
	 * 测试中文国际化
	 * @throws Exception
	 */
	@Test
	public void i18nzh_CNTest() throws Exception{
		Long startTime = System.currentTimeMillis();
		logger.info("测试国际化---start>>>time:"+startTime);
		this.mockMvc
		.perform(post("/test/i18n").characterEncoding("UTF-8") 
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.param("locale", "zh_CN")
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.resultCode", is("success")))
		.andExpect(jsonPath("$.isSuccess", is(true)));
		logger.info("测试国际化---end>>>time:"+(System.currentTimeMillis()-startTime));
	}
}


