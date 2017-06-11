package com.wbsf.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbsf.core.BaseMapper;
import com.wbsf.core.persistence.FileOperation;

public interface FileOperationMapper extends BaseMapper<FileOperation> {
	
	/**
	 * 根据MD5值加载历史上传文件信息，只会获取历史的第一条数据，如果没有返回null
	 * @param MD5
	 * @return 返回包含MD5值的文件信息
	 */
	FileOperation loadFileByMD5(String MD5);
	
	/**
	 * 对文件进行逻辑删除
	 * @param id 资源ID
	 * @param deleteFlag 删除标识
	 * @return 返回删除的实际行数
	 */
	int deleteLogical(FileOperation fileOperation);
	
	/**
	 * 查询资源
	 * @param fileOperation
	 * @return
	 */
	List<FileOperation> queryFiles(@Param("fileOperations")FileOperation... fileOperation);
}