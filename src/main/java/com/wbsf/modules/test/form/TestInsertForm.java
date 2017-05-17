package com.wbsf.modules.test.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.wbsf.modules.test.entity.TestDemo;

public class TestInsertForm extends TestDemo{

	private static final long serialVersionUID = -4018613008756626672L;

	@Override
	@Max(value=10,message="最大值为10")
	public Long getId() {
		return super.getId();
	}

	@Override
	@NotEmpty(message = "用户名不能为空")
	public String getName() {
		return super.getName();
	}

	@Override
	@Size(min=6,message="最小6位字符")
	public String getCode() {
		return super.getCode();
	}
}
