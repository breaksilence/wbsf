package com.wbsf.core.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
/**
 * 文件上传下载基础服务接口类
带参数上传如何同时处理文件和表单数据
如何进行MD5校验
文件已经存在如何进行操作
 * @author hubery
 *
 */
@Controller
@RequestMapping(value="/fileOpration")
public class FileOprationController extends ControllerSupport {
	/**
	 * 采用spring提供的上传文件的方法 
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "upload", method = { RequestMethod.GET, RequestMethod.POST })
	public String springUpload() throws IllegalStateException, IOException {
		// 创建一个计时器
		StopWatch watch = new StopWatch();
		// 计时器开始
		watch.start(); 
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile multipartFile = multiRequest.getFile(iter.next());
				if (multipartFile != null && !multipartFile.isEmpty()) {
					String originalFileName = multipartFile.getOriginalFilename();
					//文件名不能全部为空
					if(StringUtils.isNotBlank(originalFileName)){
						String path = "E:/springUpload" + originalFileName;//后期实现针对不同模块配置不同的上传路径
						// 上传
						multipartFile.transferTo(new File(path));
					}
				}

			}

		}else{
			//无效的上传提示处理
		}
		watch.stop();
		watch.prettyPrint();
		return "/success";
	}
}
