package com.wbsf.core.baidu.ueditor.upload;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.wbsf.core.baidu.ueditor.PathFormat;
import com.wbsf.core.baidu.ueditor.define.AppInfo;
import com.wbsf.core.baidu.ueditor.define.BaseState;
import com.wbsf.core.baidu.ueditor.define.FileType;
import com.wbsf.core.baidu.ueditor.define.State;

public class BinaryUploader {

	public static final State save(HttpServletRequest request,
			Map<String, Object> conf) {
		MultipartFile uploadFile = null;
		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());

        if ( isAjaxUpload ) {
            upload.setHeaderEncoding( "UTF-8" );
        }

		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
		        request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request))
		{
		    //将request变成多部分request
		    MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
		   //获取multiRequest 中所有的文件名
		    Iterator<String> iter=multiRequest.getFileNames();
		    while (iter.hasNext()) {
		        //一次遍历所有文件
		    	uploadFile = multiRequest.getFile(iter.next());
		        if (uploadFile != null && !uploadFile.isEmpty()) {
		        	break;
		        }
		        uploadFile = null;
		         
		    }
		   
		}
		
		if (uploadFile == null) {
			return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
		}

		String savePath = (String) conf.get("savePath");
		String originFileName = uploadFile.getOriginalFilename();
		String suffix = FileType.getSuffixByFilename(originFileName);

		originFileName = originFileName.substring(0,
				originFileName.length() - suffix.length());
		savePath = savePath + suffix;

		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
			return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
		}

		savePath = PathFormat.parse(savePath, originFileName);

		String physicalPath = (String) conf.get("rootPath") + savePath;
		
		if (uploadFile.getSize() > maxSize) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}
		
		State storageState = StorageManager.saveFileByMultipartFile(uploadFile, physicalPath);

		if (storageState.isSuccess()) {
			storageState.putInfo("url", PathFormat.format(savePath));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", originFileName + suffix);
		}

		return storageState;
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);
		return list.contains(type);
	}
}
