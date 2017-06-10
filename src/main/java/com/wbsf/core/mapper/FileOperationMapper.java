package com.wbsf.core.mapper;

import com.wbsf.core.persistence.FileOperation;

public interface FileOperationMapper<T extends FileOperation> extends BaseMapper<T> {
	
	/**
	 * 根据MD5值加载历史上传文件信息，只会获取历史的第一条数据，如果没有返回null
	 * @param MD5
	 * @return 返回包含MD5值的文件信息
	 */
	FileOperation loadFileByMD5(String MD5);
}