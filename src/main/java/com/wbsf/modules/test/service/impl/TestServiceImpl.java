package com.wbsf.modules.test.service.impl;

import org.springframework.stereotype.Service;

import com.wbsf.core.service.impl.ServiceSupportImpl;
import com.wbsf.modules.test.entity.TestDemo;
import com.wbsf.modules.test.mapper.TestDemoMapper;
import com.wbsf.modules.test.service.TestService;
@Service("testService")
public class TestServiceImpl extends ServiceSupportImpl<TestDemo, TestDemoMapper> implements TestService {
	
}
