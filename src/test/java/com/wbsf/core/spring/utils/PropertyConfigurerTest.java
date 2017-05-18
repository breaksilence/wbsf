package com.wbsf.core.spring.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.wbsf.core.test.JunitTestSupport;

/**
 * 测试spring启动后配置文件正常加载
 * @author xiangzheng
 *
 */
public class PropertyConfigurerTest extends JunitTestSupport {

	@Before
	public void setUp() {
		super.setUp();
	}
	
	/**
	 * 测试配置文件读取默认加载的jdbc\sysConfig两个配置文件的值是否正确
	 */
	@Test
	public void testGetPropertyString() {
		assertEquals(PropertyConfigurer.getProperty("jdbc.driver.main"), "com.mysql.jdbc.Driver");
		assertEquals(PropertyConfigurer.getProperty("property.init.check"), "success");
		logger.info("输出配置jdbc.driver.main值:"+PropertyConfigurer.getProperty("jdbc.driver.main"));
		logger.info("输出配置property.init.check值："+PropertyConfigurer.getProperty("property.init.check"));
	}

	/**
	 * 测试配置文件预设默认值
	 */
	@Test
	public void testGetPropertyStringDefualt() {
		assertEquals(PropertyConfigurer.getProperty("jdbc.driver.main","not set driver"), "com.mysql.jdbc.Driver");
		assertEquals(PropertyConfigurer.getProperty("property.init.check","not set init check"), "success");
		assertEquals(PropertyConfigurer.getProperty("property.notset.check","not set this value"), "not set this value");
		logger.info("输出配置jdbc.driver.main值:"+PropertyConfigurer.getProperty("jdbc.driver.main","not set driver"));
		logger.info("输出配置property.init.check值："+PropertyConfigurer.getProperty("property.init.check","not set init check"));
		logger.info("输出配置property.init.check值："+PropertyConfigurer.getProperty("property.notset.check","没有设置该值"));
	}
}
