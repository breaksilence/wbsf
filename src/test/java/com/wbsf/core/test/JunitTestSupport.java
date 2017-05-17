package com.wbsf.core.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")  
@DirtiesContext
@ContextConfiguration(locations = {"classpath:config/spring/applicationContext.xml","classpath:config/spring/spring-mvc.xml","classpath:config/mybatis/mybatis-config.xml"})
public class JunitTestSupport {
	@Autowired
    protected WebApplicationContext wac;
	protected MockMvc mockMvc;
	protected Logger logger = LogManager.getLogger(JunitTestSupport.class);

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
}