package com.wbsf.core.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wbsf.core.config.FileOperationConfig;
import com.wbsf.core.mapper.FileOperationMapper;
import com.wbsf.core.persistence.FileOperation;
import com.wbsf.core.result.Result;
import com.wbsf.core.service.FileOperationService;
@Controller
@RequestMapping(value="/fileOpration")
public class FileOprationController extends ControllerSupport {

	@Resource(name="fileOperationSupport")
	private FileOperationService<FileOperation, FileOperationMapper<FileOperation>>  fileOperationSupport;
	/**
	 * 采用spring提供的上传文件的方法 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "upload", method = { RequestMethod.GET, RequestMethod.POST })
	public String springUpload(@RequestParam("file") MultipartFile[] files, @RequestParam("path")String pathKey) throws Exception {
		// 创建一个计时器
		StopWatch watch = new StopWatch();
		// 计时器开始
		watch.start(); 
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		Result<List<FileOperation>> uploadResult= fileOperationSupport.saveFile(FileOperationConfig.COMMON_UPLOAD_PATH.config(), null, files);
		watch.stop();
		watch.prettyPrint();
		if(uploadResult.success()){
			uploadResult.formateMessage(uploadResult.getResult().size());
		}
		uploadResult.setResult(null);//将文件结果信息清除，该信息不适合给前台
		return uploadResult.toJson();
	}
}
