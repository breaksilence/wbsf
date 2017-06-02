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

/**
 * spring mvc 单元测试基础父类，如果需要加载spring配置项<p>
 * 进行完整的controller测试可以继承此类
 * 
 * @author xiangzheng
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")  
@DirtiesContext
@ContextConfiguration(locations = {"classpath:config/spring/applicationContext.xml","classpath:config/spring/spring-mvc.xml"})
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