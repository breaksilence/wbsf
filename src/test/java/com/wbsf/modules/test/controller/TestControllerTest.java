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
	 * 测试code不符合条件插入，验证不通过 错误消息占位符规则：
	 * {验证注解属性名}，如@Length有min和max属性，则在错误消息文件中可以通过{min}和{max}来获取；如@Max有value属性，则在错误消息文件中可以通过{value}来获取
	 * 
	 * @throws Exception
	 */
	@Test
	public void testErrorInsert() throws Exception {
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
				.andExpect(jsonPath("$.code", is("error")))
				.andExpect(jsonPath("$.isSuccess", is(false)));
		logger.info("测试demo insert---end>>>time:"+(System.currentTimeMillis()-startTime));
	}
	
	/**
	 * 验证正常插入数据
	 * @throws Exception
	 */
	@Test
	public void testInsert() throws Exception {
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
		.andExpect(jsonPath("$.code", is("success")))
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
		.andExpect(jsonPath("$.code", is("success")))
		.andExpect(jsonPath("$.isSuccess", is(true)));
		logger.info("测试demo insert---end>>>time:"+(System.currentTimeMillis()-startTime));
	}
	
	/**
	 * 分页查询
	 */
	@Test
	public void testPageQuery() throws Exception {
		Long startTime = System.currentTimeMillis();
		logger.info("测试demo pageQueyr 分页查询---start>>>time:"+startTime);
			this.mockMvc
			.perform(post("/test/pageQuery").characterEncoding("UTF-8") 
					.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.param("maxId", "500")
					.param("minId", "20")
					.param("code", "testCode04")
					.param("orderBy", "modify_time desc,code asc")
					)
			.andDo(print());
		logger.info("测试demo pageQueyr 分页查询---end>>>time:"+(System.currentTimeMillis()-startTime));
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
		.andExpect(jsonPath("$.code", is("success")))
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
		.andExpect(jsonPath("$.code", is("success")))
		.andExpect(jsonPath("$.isSuccess", is(true)));
		logger.info("测试国际化---end>>>time:"+(System.currentTimeMillis()-startTime));
	}
}


