package com.wbsf.core.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.wbsf.core.mapper.FileOprationMapper;
import com.wbsf.core.persistence.FileOpration;
import com.wbsf.core.result.Result;

/**
 * 统一的文件上传接口，通过该接口进行所有的文件处理操作
 * @author xiangzheng
 *
 */
public interface FileOperationService<T extends FileOpration,M extends FileOprationMapper<T>> {
	

	/**
	 * 保存文件到指定目录下
	 * @param relativeStoragePath
	 * @param multipartFiles 文件数组
	 * @return 返回文件上传的数据库列表
	 */
	Result<List<T>> saveFile(String relativeStoragePath, MultipartFile... multipartFiles);

	/**
	 * 保存文件到指定目录下
	 * @param relativeStoragePath
	 * @param groupFolder 如果指定分组则按照分组进行保存文件信息，若未指定则生成新的
	 * @param multipartFiles 文件数组
	 * @return 返回文件上传的数据库列表
	 */
	Result<List<T>> saveFile(String relativeStoragePath, String groupFolder, MultipartFile... multipartFiles);
	
	/**
	 * 保存文件
	 * @param request 请求的request
	 * @param relativeStoragePath 保存文件的相对路径
	 * @return 返回文件保存文件的处理结果
	 */
	Result<List<T>> saveFile(HttpServletRequest request, String relativeStoragePath);
	
	/**
	 * 保存文件
	 * @param request 请求的request
	 * @param relativeStoragePath 保存文件的相对路径
	 * @param groupFolder 如果指定分组则按照分组进行保存文件信息，若未指定则生成新
	 * @return 返回文件保存文件的处理结果
	 */
	Result<List<T>> saveFile(HttpServletRequest request, String relativeStoragePath, String groupFolder);
	
	/**
	 * 将保存文件设置分组
	 * @param request 请求的request
	 * @param relativeStoragePath 保存文件的相对路径
	 * @param groupFolder 如果指定分组则按照分组进行保存文件信息，若未指定则生成新
	 * @return 返回文件保存文件的处理结果
	 */
	Result<List<T>> saveFile(String relativeStoragePath, String groupFolder, File... saveFile);
	
	/**
	 * 直接保存单个文件
	 * @param request 请求的request
	 * @param relativeStoragePath 保存文件的相对路径
	 * @return 返回文件保存文件的处理结果
	 */
	Result<List<T>> saveFile(String relativeStoragePath,File... saveFile);
	
	/**
	 * 根据文件资源Id删除文件
	 * @param fileOperationId 删除文件的ID
	 * @return
	 * 	返回删除的文件信息
	 */
	Result<T> deleteFileById(String fileOperationId);
	/**
	 * 根据文件资源名称删除文件
	 * @param fileOperationId 删除文件的ID
	 * @return
	 * 	返回删除的文件信息
	 */
	Result<T> deleteFileByName(String fileOperationName);
	
	/**
	 * 根据资源分组删除文件
	 * @param fileOperationName 文件名，保存后的文件名，非原文件名
	 * @return
	 */
	Result<List<T>> deleteFileByGroup(String groupFolder);
	
	/**
	 * 根据资源id获取文件
	 * @param fileOperationId 文件资源id
	 * @return
	 * 		文件的实例
	 */
	Result<T> getFileById(String fileOperationId);
	
	/**
	 * 根据文件名称获取资源对象
	 * @param fileOperationName 文件名，保存后的文件名，非原文件名
	 * @return
	 * 		文件的实例
	 */
	Result<T> getFileByName(String fileOperationName);
	
	/**
	 * 根据分组获取文件列表的实例
	 * @param groupFolder
	 * @return
	 * 		该组下的所有文件列表
	 */
	Result<List<T>> getFileListByGroup(String groupFolder);
}
