package com.wbsf.modules.test.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.wbsf.modules.test.entity.TestDemo;

/**
 * 测试服务端进行表单数据校验demo
 * @author xiangzheng
 *
 */
public class TestInsertForm extends TestDemo{

	private static final long serialVersionUID = -4018613008756626672L;
	
	@NotNull(message="{test.code.NotNull}")
	@Size(min=3,message="{test.code.min}")
	@Override
	public String getCode() {
		return super.getCode();
	}
}
