package com.wbsf.core.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.common.collect.Maps;

/**
 * 基本的controller
 * @author hubery
 *
 */
public abstract class ControllerSupport {
	
	/**
	 * 获取request注入对象
	 */
	@Autowired
	protected HttpServletRequest request;
	/**
	 * 获取response对象
	 */
	@Autowired
	protected HttpServletResponse response;
	/**
	 * 获取错误信息,同一字段多种错误信息只会保留一种
	 * @param bindingResult 参数校验结果
	 * @return 返回表单验证的错误信息集合
	 */
	protected Map<String, String> getError(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LinkedHashMap<String, String> errorMap = Maps.newLinkedHashMap();
			bindingResult.getFieldErrors().forEach(errorObj -> {
				errorMap.put(errorObj.getField(), errorObj.getDefaultMessage());
			});
			return errorMap;
		} else {
			return null;
		}
	}
	
	/**
	 * 获取第一个校验错误
	 * @param bindingResult 参数校验结果
	 * @return Map 
	 */
	protected Entry<String, String> getFirstError(BindingResult bindingResult) {
		if(getError(bindingResult) != null){
			return getError(bindingResult).entrySet().iterator().next();
		}
		return null;
	}

	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public String bindException() {  
        return "error/400";
    }
	
	/**
	  * 获取IP地址
	  * @param request
	  * @return
	  */
	 public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
