package com.wbsf.core.service.impl;

import org.springframework.stereotype.Service;

/**
 * 文件操作的基础支撑类，继承AbstractFileService，
 * 拥有数据库持久化的操作能力，同时AbstractFileService完整实现了FileOperationService接口
 * @author xiangzheng
 *
 */
@Service("fileOperationSupport")
public class FileOperationSupport extends AbstractFileService {

	public FileOperationSupport() {
		// TODO Auto-generated constructor stub
	}

}
