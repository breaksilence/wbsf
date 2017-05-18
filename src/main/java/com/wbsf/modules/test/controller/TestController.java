package com.wbsf.modules.test.controller;

import static com.wbsf.core.spring.utils.ContextUtil.text;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wbsf.core.controller.ControllerSupport;
import com.wbsf.core.result.Result;
import com.wbsf.core.result.base.ResultConfig;
import com.wbsf.core.result.utils.ResultHelper;
import com.wbsf.modules.test.entity.TestDemo;
import com.wbsf.modules.test.form.TestInsertForm;
import com.wbsf.modules.test.service.TestService;

/**
 * demo controller
 * @author xiangzheng
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/test")
public class TestController extends ControllerSupport {
	
	@Resource
	private TestService testService;
	
	@ResponseBody
	@RequestMapping(value="/insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method={RequestMethod.POST})
	public String insertTest(@Valid TestInsertForm test, BindingResult testValid){
		Result<TestDemo> result = ResultHelper.buildSuccess();
		if(testValid.hasErrors()){
			result = ResultHelper.buildFailed(ResultConfig.FAILED);
			result.putAttribute("errorField",getError(testValid));
			return result.toJson();
		}
		int rs = testService.save(test);
		result.setResultMsg("共有{0}条数据插入", rs);
		return result.toJson();
	}
	
	@ResponseBody
	@RequestMapping(value="/select", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method={RequestMethod.POST})
	public String selectByPage(){
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method={RequestMethod.POST})
	public String updateTest(){
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method={RequestMethod.POST})
	public String deleteTest(){
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/i18n", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method={RequestMethod.POST})
	public String i18n(String locale){
		//Locale.CHINA; 
		return ResultHelper.buildSuccess().setResultMsg(locale).putAttribute("i18n", text("request.send")).toJson();
	}
	
}
