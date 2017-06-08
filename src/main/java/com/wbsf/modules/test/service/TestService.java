package com.wbsf.modules.test.service;

import com.wbsf.core.page.PageQuery;
import com.wbsf.core.page.PageResult;
import com.wbsf.core.service.BaseService;
import com.wbsf.modules.test.entity.TestDemo;

public interface TestService extends BaseService<TestDemo> {
	/**
	 * 分页查询demo
	 * @param pageQuery
	 * @return
	 */
	PageResult<TestDemo> pageQuery(PageQuery<TestDemo> pageQuery);
}
