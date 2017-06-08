package com.wbsf.core.service.impl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wbsf.core.mapper.FileOprationMapper;
import com.wbsf.core.persistence.FileOpration;
import com.wbsf.core.result.Result;

/**
 * 文件操作的基础支撑类，继承AbstractFileService，
 * 拥有数据库持久化的操作能力，同时AbstractFileService完整实现了FileOperationService接口
 * @author xiangzheng
 *
 */
@Service("fileOperationSupport")
public class FileOperationSupport<T extends FileOpration ,M extends FileOprationMapper<T>> extends AbstractFileService<T, M> {

	/**
	 * <em>将文件保存文件到指定的存储路径下，给定的路径为相对路径</em><p>
	 * <em>上传文件将会根据文件名进行散列存储，防止同一文件夹下文件过多问题</em><p>
	 * <em>每个文件会通过jdk的UUID_文件名的MD5生成唯一的文件名</em><p>
	 * <em>该方法适合处理直接通过SpringMVC参数绑定，将上传文件以数组的形式作为接口参数的文件上传</em>
	 * <li>父级路径由sysConfig.properties文件#sys.config.fileStoragePath配置</li>
	 * <li>如果没有配置默认系统资源上传路径，则保存到项目的根路径下</li>
	 * @param relativeStoragePath 保存文件的相对路径
	 * @param multipartFiles 要上传的附件数组
	 * @return 返回文件上传的数据库列表
	 * <li>每次上传都会为文件生成唯一的分组ID</li>
	 * <li>该方法不会将多个资源文件归为一组，如果需要请调用</li>{@link #saveFile(String, boolean, MultipartFile...)}
	 * 
	 */
	@Override
	public Result<List<T>> saveFile(String relativeStoragePath, MultipartFile... multipartFiles) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * <em>将文件保存文件到指定的存储路径下，给定的路径为相对路径</em><p>
	 * <em>上传文件将会根据文件名进行散列存储，防止同一文件夹下文件过多问题</em><p>
	 * <em>每个文件会通过jdk的UUID_文件名的MD5生成唯一的文件名</em><p>
	 * <em>该方法适合处理直接通过SpringMVC参数绑定，将上传文件以数组的形式作为接口参数的文件上传</em>
	 * <li>父级路径由sysConfig.properties文件#sys.config.fileStoragePath配置</li>
	 * <li>如果没有配置默认系统资源上传路径，则保存到项目的根路径下</li>
	 * @param relativeStoragePath 保存文件的相对路径
	 * @param groupFolder 如果指定分组则按照分组进行保存文件信息，若未指定则生成新的
	 * @param multipartFiles 要上传的附件数组
	 * @return 返回文件上传的数据库列表
	 * <li>每次上传都会为文件生成唯一的分组ID</li>
	 * <li>如果isGroup分组为真，则会将本次所有给定的附件以固定的分组标识记录，该分组信息会在Result扩展属性中以groupFolder体现</li>
	 * <li>该方法不会将多个资源文件归为一组，如果需要请调用</li>{@link #saveFile(String, boolean, MultipartFile...)}
	 */
	@Override
	public Result<List<T>> saveFile(String relativeStoragePath, String groupFolder, MultipartFile... multipartFiles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<T>> saveFile(HttpServletRequest request, String relativeStoragePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<T>> saveFile(HttpServletRequest request, String relativeStoragePath, String groupFolder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<T>> saveFile(String relativeStoragePath, String groupFolder, File... saveFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<T>> saveFile(String relativeStoragePath, File... saveFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<T> deleteFileById(String fileOperationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<T> deleteFileByName(String fileOperationName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<T>> deleteFileByGroup(String groupFolder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<T> getFileById(String fileOperationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<T> getFileByName(String fileOperationName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<T>> getFileListByGroup(String groupFolder) {
		// TODO Auto-generated method stub
		return null;
	}

}
