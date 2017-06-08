package com.wbsf.core.spring.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.wbsf.core.exception.IllegalFileTypeException;
import com.wbsf.core.result.Result;
import com.wbsf.core.result.config.BaseResultEnum;
import com.wbsf.core.result.impl.FailedResult;
import com.wbsf.core.spring.utils.PropertyConfigurer;

/**
 * 全局异常处理类，对返回试图是否为json数据进行甄别处理
 * @author hubery
 *
 */
public class ExceptionHandler extends SimpleMappingExceptionResolver{
	private Logger logger = LogManager.getLogger(getClass());
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception ex) {
			 String viewName = determineViewName(ex,request);
			 logger.error(ex);
		     response.setCharacterEncoding("UTF-8");
		     if (viewName != null) {
		         if (!(request.getHeader("accept").contains("application/json")  || (request.getHeader("X-Requested-With")!= null && request
		             .getHeader("X-Requested-With").contains("XMLHttpRequest") ))) {
		        	 // JSP格式错误返回
		             Integer statusCode = determineStatusCode(request, viewName);
		             if (statusCode != null) {
		                 applyStatusCodeIfPossible(request, response, statusCode);
		             }
		             logger.debug("JSP格式返回" + viewName);
		             return getModelAndView(viewName, ex, request);
		         } else {// JSON格式错误返回
		        	 logger.debug("JSON格式返回" + viewName);
		        	 Result<?> result = new FailedResult<>(BaseResultEnum.FAILED);
		        	 if (ex instanceof MaxUploadSizeExceededException) { 
				         //对文件过大问题进行处理
		        		 result.setResultConfig(BaseResultEnum.FILE_MAX_UPLOAD_SIZE_EXCEEDED_EXCEPTION);
		        		 result.formateMessage(PropertyConfigurer.getProperty("sys.config.maxUploadSize")); //文件上传最大不能超过的配置
				     } else if (ex instanceof IllegalFileTypeException) {
				    	 result.setResultConfig(BaseResultEnum.ILLEGAL_FILE_TYPE_EXCEPTION);
		        		 result.formateMessage(PropertyConfigurer.getProperty("sys.config.allowFileType"));
				     }
		        	 //返回Json数据
		             try (PrintWriter writer = response.getWriter();){
		                 writer.write(result.toJson());
		                 writer.flush();
		             } catch (IOException e) {
						e.printStackTrace();
					}
		             return null;
		         }
		     } else {
		         return null;
		     }
		 }
	
}