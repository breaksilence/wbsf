package com.wbsf.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wbsf.core.mapper.FileOperationMapper;
import com.wbsf.core.persistence.FileOperation;
import com.wbsf.core.result.Result;

/**
 * <em> 统一的文件上传接口，通过该接口进行所有的文件处理操作 暴露上传文件持久化保存的接口，提供临时文件上传接口 </em>
 * 
 * @author xiangzheng
 *
 */
public interface FileOperationService<T extends FileOperation, M extends FileOperationMapper<T>> {

	/**
	 * 保存文件到指定文件夹，该相对文件路径以项目配置的根路径为父级，不会将记录保存到数据库中，如果文件已经存在且不允许覆盖原有文件会抛出异常
	 * 
	 * @param relativeStoragePath
	 *            保存的相对路径
	 * @param saveFile
	 *            保存的文件数组
	 * @return 返回保存的文件信息
	 * @throws Exception
	 *             抛出文件保存时发生的IO异常
	 */
	Result<List<T>> storage(String relativeStoragePath, MultipartFile... multipartFiles);

	/**
	 * 保存文件，同时将文件信息记录到数据库中，保存文件采用散列方式存储
	 * 
	 * @param relativeStoragePath
	 *            文件存储的相对路径
	 * @param multipartFiles
	 *            spring 提供的上传文件对象的数组
	 * @return 返回文件上传的数据库持久化列表
	 */
	Result<List<T>> saveFile(String relativeStoragePath, Date expiredDate, MultipartFile... multipartFiles);

	/**
	 * 根据资源实例删除资源，该方法会尝试根据资源ID，，获取能确定的唯一资源的条件进行删除文件的逻辑删除
	 * @param deleteFile
	 *            删除的文件条件
	 * @return 返回删除的文件信息列表
	 */
	Result<List<T>> deleteFile(@SuppressWarnings("unchecked") T... deleteFiles);

	/**
	 * 查询文件列表，该方法的实现会尝试根据资源ID，获取能确定的唯一资源信息列表
	 * 
	 * @param queryFile 加载文件的条件
	 * @return 返回命中的有效的文件信息列表，有效判定的几个条件为：非逻辑删除，非过期的条件匹配的文件列表
	 */
	Result<List<T>> queryFile(@SuppressWarnings("unchecked") T... queryFiles);
}
