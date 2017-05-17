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
	
	@Test
	public void testInsertTest() throws Exception {
		logger.info("测试demo insert");
		Long startTime = System.currentTimeMillis();
		logger.info("测试demo insert---start>>>time:"+startTime);
		this.mockMvc
				.perform(post("/test/insert").characterEncoding("UTF-8") 
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.param("name", "testName01")
						.param("code", "testCode01"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.resultCode", is("success")))
				.andExpect(jsonPath("$.isSuccess", is(true)));
		logger.info("测试demo insert---end>>>time:"+(System.currentTimeMillis()-startTime));
	}

}


