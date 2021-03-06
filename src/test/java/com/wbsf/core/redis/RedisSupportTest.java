package com.wbsf.core.redis;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wbsf.core.test.JunitTestSupport;
/**
 * redis 辅助工具测试类
 * @author xiangzheng
 *
 */
public class RedisSupportTest extends JunitTestSupport {

	@Autowired
	private RedisSupport<String> redisSupport;
	@Before
	public void setUp() {
		super.setUp();
	}

	/**
	 * 测试缓存的设置和读取
	 */
	@Test
	public void testCacheValue() {
		String key = "test-key-1";
		String value = "test-value";
		redisSupport.cacheValue(key, value);
	}
	
	@Test
	public void testGetValue() {
		String key = "test-key-1";
		String valueTemp = redisSupport.getValue(key);
		logger.info("redis-key:"+key+";redis-value:"+valueTemp);
		assertEquals(valueTemp, "test-value");
	}
}
