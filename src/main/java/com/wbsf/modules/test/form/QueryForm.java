package com.wbsf.modules.test.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.wbsf.modules.test.entity.TestDemo;

/**
 * 查询表单后端注解验证form bean
 * @author hubery
 *
 */
public class QueryForm extends TestDemo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3806351174877423732L;

	@Min(value=20,message="{test.pagequery.min}")
	@Max(value=400,message="{test.pagequery.max}")
	@Override
	public Long getId() {
		return super.getId();
	}
	
	
}
