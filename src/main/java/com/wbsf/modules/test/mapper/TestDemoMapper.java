package com.wbsf.modules.test.mapper;

import java.util.List;

import com.wbsf.core.mybatis.BaseMapper;
import com.wbsf.core.page.PageQuery;
import com.wbsf.modules.test.entity.TestDemo;

public interface TestDemoMapper extends BaseMapper<TestDemo> {
	
	/**
	 * 分页demo，可以自己定义sql，此处为了明了，没有联合mapper工具进行单一实体查询
	 * @param pageQuery
	 * @return
	 */
	public List<TestDemo> pageQuery(PageQuery<TestDemo> pageQuery);
}