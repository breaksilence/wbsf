package com.wbsf.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wbsf.core.baidu.ueditor.ActionEnter;

/**
 * 百度文本编辑器
 * @author hubery
 *
 */
@RestController
@RequestMapping(value="ueditor",method={RequestMethod.POST,RequestMethod.GET})
public class UeditorController{
	
	@RequestMapping(produces = "text/html;charset=UTF-8")
	public String exec(HttpServletRequest request){
		return new ActionEnter(request,request.getServletContext().getRealPath("/")).exec();
	}
	

}
