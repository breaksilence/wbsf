package com.wbsf.modules.test.controller;

import static com.wbsf.core.spring.utils.ContextUtil.text;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;

import com.wbsf.core.controller.ControllerSupport;
import com.wbsf.core.page.PageQuery;
import com.wbsf.core.page.PageResult;
import com.wbsf.core.result.Result;
import com.wbsf.core.result.config.ResultBaseEnum;
import com.wbsf.core.result.impl.SuccessResult;
import com.wbsf.core.result.utils.ResultHelper;
import com.wbsf.modules.test.entity.TestDemo;
import com.wbsf.modules.test.form.QueryForm;
import com.wbsf.modules.test.form.TestInsertForm;
import com.wbsf.modules.test.result.DemoResultEnum;
import com.wbsf.modules.test.service.TestService;

/**
 * demo controller
 * @author xiangzheng
 *
 */
@Controller
@RequestMapping(value="/test")
public class TestController extends ControllerSupport {
	private final static Logger logger = LogManager.getLogger();
	@Resource
	private TestService testService;
	
	@ResponseBody
	@RequestMapping(value="/insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method={RequestMethod.POST,RequestMethod.GET})
	public String insertTest(@Valid TestInsertForm test, BindingResult testValid){
		Result<TestDemo> result = ResultHelper.buildSuccess();
		if(testValid.hasErrors()){
			result = ResultHelper.buildFailed(ResultBaseEnum.FAILED);
			result.putAttribute("errorField",getError(testValid));
			return result.toJson();
		}
		int rs = testService.save(test);
		result.formateMessage("共有{0}条数据插入", rs);
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
			result = ResultHelper.buildFailed(ResultBaseEnum.FAILED);
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
	
	@RequestMapping(value="/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method={RequestMethod.POST,RequestMethod.GET})
	public String deleteTest() throws ResourceAccessException{
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/i18n", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method={RequestMethod.POST,RequestMethod.GET})
	public String i18n(){
		//Locale.CHINA; 
		return ResultHelper.buildSuccess().putAttribute("i18n", text("request.send")).toJson();
	}
	
	@ResponseBody
	@RequestMapping(value="/sessionTest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method={RequestMethod.POST,RequestMethod.GET})
	public String sessionTest(HttpServletRequest request ,HttpServletResponse response){
		request.getSession().setAttribute("sessionTemp1", "test");
		Result<TestDemo> result = ResultHelper.buildSuccess();
		result.setResultConfig(ResultBaseEnum.SUCCESS);
		return result.toJson();
	}
	
	@ResponseBody
	@RequestMapping(value="/single", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method={RequestMethod.POST,RequestMethod.GET})
	public String testRequestSingle(){
		String numStr = request.getParameter("num");
		logger.info("获取到的num为{}",numStr);
		return new SuccessResult<>(DemoResultEnum.SUCCESS).putAttribute("numStr", numStr).toJson();
	}
	
	@ResponseBody
	@RequestMapping(value="/post", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method={RequestMethod.POST,RequestMethod.GET})
	public String testPost() throws IOException{
		String param1 = request.getParameter("param1");
		int len = request.getContentLength();
		ServletInputStream iii = request.getInputStream();
		byte[] buffer = new byte[len];
		iii.read(buffer, 0, len);
		String body = new String(buffer);
		Result<String> result = ResultHelper.buildSuccess();
		result.setResult(param1).putAttribute("body", body);
		return result.toJson();
	}
}
