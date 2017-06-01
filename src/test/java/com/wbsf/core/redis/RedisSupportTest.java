package com.wbsf.core.redis;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wbsf.core.test.JunitTestSupport;

public class RedisSupportTest extends JunitTestSupport {

	@Autowired
	private RedisSupport redisSupport;
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testCacheValueStringString() {
		String key = "test-key-1";
		String value = "test-value";
		redisSupport.cacheValue(key, value);
		String valueTemp = redisSupport.getValue(key);
		logger.info("redis-key:"+key+";redis-value:"+valueTemp);
		assertEquals(valueTemp, value);
	}
}
