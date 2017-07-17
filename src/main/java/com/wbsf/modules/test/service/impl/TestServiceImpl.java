package com.wbsf.modules.test.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wbsf.core.page.PageQuery;
import com.wbsf.core.page.PageResult;
import com.wbsf.core.service.impl.BaseServiceSupport;
import com.wbsf.modules.test.entity.TestDemo;
import com.wbsf.modules.test.mapper.TestDemoMapper;
import com.wbsf.modules.test.service.TestService;
@Service("testService")
public class TestServiceImpl extends BaseServiceSupport<TestDemo, TestDemoMapper> implements TestService {
	@Override
	public PageResult<TestDemo> pageQuery(PageQuery<TestDemo> pageQuery) {
		 pageQuery.query();
		 List<TestDemo> queryResult = mapper.pageQuery(pageQuery);
		 return pageQuery.buildResult(queryResult);
	}
	
}
