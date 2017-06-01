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
import com.wbsf.core.page.PageQuery;
import com.wbsf.core.page.PageResult;
import com.wbsf.core.result.Result;
import com.wbsf.core.result.config.ResultConfig;
import com.wbsf.core.result.utils.ResultHelper;
import com.wbsf.modules.test.entity.TestDemo;
import com.wbsf.modules.test.form.QueryForm;
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
	@RequestMapping(value="/insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method={RequestMethod.POST,RequestMethod.GET})
	public String insertTest(@Valid TestInsertForm test, BindingResult testValid){
		Result<TestDemo> result = ResultHelper.buildSuccess();
		if(testValid.hasErrors()){
			result = ResultHelper.buildFailed(ResultConfig.FAILED);
			result.putAttribute("errorField",getError(testValid));
			return result.toJson();
		}
		int rs = testService.save(test);
		result.setMessage("共有{0}条数据插入", rs);
		return result.toJson();
	}
	
	/**
	 * 分页查询示例
	 * @param demo 查询的表单，通过validate验证 其中本查询中利用code作为一个动态变量进行查询，基本展示了如何使用PageQuery以及PageResult
	 * @param pageQuery 查询辅助工具类，所有的分页查询都通过该对象完成
	 * @param maxId 示例参数最大id
	 * @param minId 示例参数 最小id 
	 * 
	 * @return 分页结果
	 */
	@ResponseBody
	@RequestMapping(value="/pageQuery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method={RequestMethod.POST,RequestMethod.GET})
	public String pageQuery(@Valid QueryForm demo, BindingResult testValid ,PageQuery<TestDemo> pageQuery,Long maxId,Long minId){
		Result<TestDemo> result = ResultHelper.buildSuccess();
		if(testValid.hasErrors()){
			result = ResultHelper.buildFailed(ResultConfig.FAILED);
			result.putAttribute("errorField",getError(testValid));
			return result.toJson();
		}
		pageQuery.setVo(demo);
		pageQuery.addParam("maxId", maxId);
		pageQuery.addParam("minId", minId);
		PageResult<TestDemo> pageResult = testService.pageQuery(pageQuery);
		return pageResult.toJson();
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
		return ResultHelper.buildSuccess().setMessage(locale).putAttribute("i18n", text("request.send")).toJson();
	}
	
}
