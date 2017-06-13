package com.wbsf.core.generator;

import org.junit.Test;

import com.wbsf.core.mybatis.generator.MybatisGeneator;
/**
 * 代码生成器测试类
 * @author xiangzheng
 *
 */
public class MybatisGeneatorTest {
	/**
	 * 测试代码生成器
	 * @throws Exception
	 */
	@Test
	public void testGenerator() throws Exception {
		MybatisGeneator.generator();
	}

}
