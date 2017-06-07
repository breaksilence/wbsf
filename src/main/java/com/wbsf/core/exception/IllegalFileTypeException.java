package com.wbsf.core.exception;

import org.springframework.web.multipart.MultipartException;

/**
 * 上传文件类型不合法异常类
 * @author hubery
 *
 */
public class IllegalFileTypeException extends MultipartException {
	
	private static final long serialVersionUID = 1710552699249426236L;
	
	public IllegalFileTypeException(String msg) {
		super(msg);
	}

}
