package com.wbsf.core.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wbsf.core.config.FileOperationConfig;
import com.wbsf.core.persistence.FileOperation;
import com.wbsf.core.result.Result;
import com.wbsf.core.result.utils.ResultHelper;
import com.wbsf.core.service.FileOperationService;

/**
 * 提供简单的文件上传的接口
 * @author hubery
 *
 */
@Controller
@RequestMapping(value = "/fileOpration")
public class FileOprationController extends ControllerSupport {

	@Resource(name = "fileOperationSupport")
	private FileOperationService fileOperationSupport;

	/**
	 * 采用spring提供的上传文件的方法 ,但上传的文件表单名必须为file
	 * 
	 * @param files
	 *            上传的文件列表
	 * @param pathKey
	 *            为调用上传功能的模块key，根据该key寻找系统配置的上传根目录
	 * @return 返回上传的处理结果
	 */
	@ResponseBody
	@RequestMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = { RequestMethod.GET, RequestMethod.POST })
	public String upload(@RequestParam("file") MultipartFile[] files,
			@RequestParam(value = "pathKey", required = false) String pathKey) {
		try {
			// 创建一个计时器
			StopWatch watch = new StopWatch();
			// 计时器开始
			watch.start();
			Result<List<FileOperation>> uploadResult = fileOperationSupport
					.saveFile(FileOperationConfig.UPLOAD_TEMP_PATH.config(), null, files);
			if (uploadResult.success()) {
				uploadResult.formateMessage(uploadResult.getResult().size());
			}
			watch.stop();
			watch.prettyPrint();
			return uploadResult.toJson();//根据返回的文件资源ID做后续的处理
		} catch (Exception e) {
			return ResultHelper.buildFailed("文件上传失败").toJson();
		}

	}
}
