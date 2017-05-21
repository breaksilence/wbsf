package com.wbsf.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbsf.core.page.PageQuery;
import com.wbsf.core.page.PageResult;
import com.wbsf.core.service.impl.ServiceSupportImpl;
import com.wbsf.modules.test.entity.TestDemo;
import com.wbsf.modules.test.mapper.TestDemoMapper;
import com.wbsf.modules.test.service.TestService;
@Service("testService")
public class TestServiceImpl extends ServiceSupportImpl<TestDemo, TestDemoMapper> implements TestService {
	@Autowired
	protected TestDemoMapper mapper;
	@Override
	public PageResult<TestDemo> pageQuery(PageQuery<TestDemo> pageQuery) {
		Long s = mapper.countquery();
		System.out.println(s);
		pageQuery.startPage();
		List<TestDemo> queryResult = mapper.pageQuery(pageQuery);
		return pageQuery.buildResult(queryResult);
	}
	
}
