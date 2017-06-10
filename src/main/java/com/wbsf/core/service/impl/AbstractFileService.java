package com.wbsf.core.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.wbsf.common.utils.IDGenerator;
import com.wbsf.core.config.SystemConfig;
import com.wbsf.core.exception.ServiceException;
import com.wbsf.core.mapper.FileOperationMapper;
import com.wbsf.core.persistence.FileOperation;
import com.wbsf.core.result.Result;
import com.wbsf.core.result.utils.ResultHelper;
import com.wbsf.core.service.FileOperationService;
/**
 * 继承了baseService拥有数据库持久化操作的功能，同时实现了部分FileOperationService接口
 * @author xiangzheng
 *
 */
public abstract class AbstractFileService extends BaseServiceSupport<FileOperation ,FileOperationMapper<FileOperation>> implements FileOperationService<FileOperation ,FileOperationMapper<FileOperation>> {
	
	@Override
	public Result<List<FileOperation>> storage(String relativeStoragePath, MultipartFile... multipartFiles) throws Exception {
		return saveFile(relativeStoragePath, null, false, multipartFiles);
	}

	
	@Override
	public Result<List<FileOperation>> saveFile(String relativeStoragePath, Date expiredDate, MultipartFile... multipartFiles) throws ServiceException,FileNotFoundException {
		return saveFile(relativeStoragePath, expiredDate, true, multipartFiles);
	}
	
	/**
	 * <em>将文件保存文件到指定的存储路径下，给定的路径为相对路径</em><p>
	 * <em>将通过MD5校验文件是否存在，如果文件已经存在则不在进行上传</em><p>
	 * <em>上传文件将会根据文件名进行散列存储，防止同一文件夹下文件过多问题</em><p>
	 * <em>每个文件会通过jdk的UUID_文件名生成唯一的文件名</em><p>
	 * <em>该方法适合处理直接通过SpringMVC参数绑定，将上传文件以数组的形式作为接口参数的文件上传</em>
	 * <li>父级路径由sysConfig.properties文件#sys.config.fileStoragePath配置</li>
	 * <li>如果没有配置默认系统资源上传路径，则会抛出业务异常信息</li>
	 * @param relativeStoragePath 保存文件的相对路径
	 * @param multipartFiles 要上传的附件数组
	 * @return 返回文件上传的数据库列表
	 * <li>每次上传都会为文件生成唯一的分组ID</li>
	 * <li>该方法不会将多个资源文件归为一组，如果需要请调用</li>{@link #saveFile(String, boolean, MultipartFile...)}
	 * @throws ServiceException 
	 * 
	 */
	public Result<List<FileOperation>> saveFile(String relativeStoragePath, Date expiredDate,boolean isPersistence, MultipartFile... multipartFiles) throws ServiceException,FileNotFoundException {
		if(multipartFiles == null  || multipartFiles.length == 0){
			throw new FileNotFoundException("上传文件不能为空");
		}
		Result<List<FileOperation>> result = ResultHelper.buildSuccess();//返回结果文件
		//用于保存上传文件的列表
		List<FileOperation> operFiles = Lists.newArrayListWithCapacity(multipartFiles.length);
		for(MultipartFile multipartFile: multipartFiles){
			FileOperation operFile = new FileOperation();
			//原始文件名、MultipartFile实例为空会抛出业务异常
			if (multipartFile != null && !multipartFile.isEmpty() && StringUtils.isNotBlank(multipartFile.getOriginalFilename())) {
				String originalFileName = multipartFile.getOriginalFilename();
				String md5 = null;
				try {
					md5 = DigestUtils.md5Hex((multipartFile.getInputStream()));
				} catch (IOException e1) {
					throw  new ServiceException("文件存储异常，读取文件"+originalFileName+"失败");
				}
				//根据MD5判断文件是否存在，如果存在则直接获取文件的引用，不在进行文件存储
				FileOperation sameMd5File = super.mapper.loadFileByMD5(md5);
				Long fileSize = multipartFile.getSize(); //获取文件大小
				String saveName = storageFileName(originalFileName);
				operFile.setFileName(saveName); //设置文件名-保存的真实文件名
				Date createDate = new Date();
				operFile.setCreateDate(createDate); //设置创建时间
				operFile.setSize(fileSize); //设置文件大小
				operFile.setOriginalFileName(originalFileName);//设置原始文件名
				operFile.setMd5(md5);//设置MD5
				operFile.setState(0); //设置文件状态为游离状态
				operFile.setType(multipartFile.getContentType()); //设置文件类型
				operFile.setDeleteFlag(1); //设置文件是否被逻辑删除
				operFile.setCreateBy(super.operateUserId()); //通过spring获取当前线程是否有用户信息，如果没有则认为是系统操作
				operFile.setExpiredDate(expiredDate);
				if (sameMd5File !=null) {
					operFile.setFileUri(sameMd5File.getFileUri()); //设置文件保存资源路劲
				} else {
					String storagePath = storagePath(saveName, relativeStoragePath);
					operFile.setFileUri(storagePath); //设置文件保存资源路劲
					try {
						multipartFile.transferTo(new File(storagePath));
					} catch (IllegalStateException | IOException e) {
						//回滚数据将历史文件清除，保证原子性
						throw  new ServiceException("文件存储异常，数据文件将会回滚");
					}
				}
				if(isPersistence){ //持久化标识
					super.save(operFile);//保存文件到数据库，同时将保存的文件id回写
				}
				operFiles.add(operFile);
			}else{
				throw  new ServiceException("上传文件名为空！");
			}
		}
		return result.setResult(operFiles);
	}

	@Override
	public Result<List<FileOperation>> updateFile(FileOperation... updateFiles) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<FileOperation>> deleteFile(FileOperation... deleteFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<FileOperation>> queryFile(FileOperation... queryFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 系统保存的实际文件名
	 * @param filename 原文件名
	 * @return 实际文件名
	 */
	protected String storageFileName(String filename) { // 2.jpg
		// 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		return IDGenerator.uuid() + "_" + filename;
	}  
	
	 /**
     * <em>获取保存文件的真实路径，该方法利用hashcode算法随机获取一、二级目录，将文件打散存放
     * 为防止一个目录下面出现太多文件，要使用hash算法打散存储</em>
     * @param filename 文件名，要根据文件名生成存储目录
     * @param relativeStoragePath 存储文件的相对路径
     * @return 真正存储文件的路径
     */
    protected String storagePath(String filename,String relativeStoragePath) throws NullPointerException{
    	String rootFolder = SystemConfig.FILEUPLOAD_ROOT_STORAGE.config();
    	if(StringUtils.isBlank(rootFolder)){
    		throw new NullPointerException("为配置系统根路径，请检查"+SystemConfig.FILEUPLOAD_ROOT_STORAGE.key()+"文件");
    	}
    	StringBuilder storagePathBuilder = new StringBuilder(rootFolder);
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int folderOneLevel = hashcode&0xf; //0--15 保存的以及文件目录
        int folderTwoLevel = (hashcode&0xf0)>>4; //0-15 //保存的文件文件目录
        //构造新的保存目录
        storagePathBuilder.append(relativeStoragePath).append(File.separatorChar)
        		.append(folderOneLevel).append(File.separatorChar).append(folderTwoLevel)
        		.append(File.separatorChar).append(filename); 
        //File既可以代表文件也可以代表目录   
        return storagePathBuilder.toString();
    }
}
