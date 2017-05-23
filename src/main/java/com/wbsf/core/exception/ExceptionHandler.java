package com.wbsf.core.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver{
	private Logger logger = LogManager.getLogger(getClass());
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception ex) {  
		logger.error(ex);
        Map<String, Object> model = new HashMap<String, Object>();  
        model.put("ex", ex);  
        ex.printStackTrace();
        // 根据不同错误转向不同页面  
         if(ex instanceof BindException) {  
            return new ModelAndView("error/400", model);  
        } else if(ex instanceof ConstraintViolationException){
        	return new ModelAndView("error/400", model);
        }else if(ex instanceof ValidationException){
        	return new ModelAndView("error/400", model);
        }else{  
            return new ModelAndView("error", model);  
        }  
    }
}
