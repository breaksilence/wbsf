package com.wbsf.core.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.wbsf.common.utils.IDGenerator;
import com.wbsf.core.config.SystemConfig;
import com.wbsf.core.enums.DeleteFlag;
import com.wbsf.core.exception.ServiceException;
import com.wbsf.core.mapper.FileOperationMapper;
import com.wbsf.core.persistence.FileOperation;
import com.wbsf.core.result.Result;
import com.wbsf.core.result.config.ResultBaseEnum;
import com.wbsf.core.result.utils.ResultHelper;
import com.wbsf.core.service.FileOperationService;
/**
 * 继承了baseService拥有数据库持久化操作的功能，同时实现了部分FileOperationService接口
 * @author xiangzheng
 *
 */
public abstract class AbstractFileService extends BaseServiceSupport<FileOperation ,FileOperationMapper<FileOperation>> implements FileOperationService<FileOperation ,FileOperationMapper<FileOperation>> {
	private static final Logger logger = LogManager.getLogger(AbstractFileService.class);
	
	@Override
	public Result<List<FileOperation>> storage(String relativeStoragePath, MultipartFile... multipartFiles) {
		return saveFile(relativeStoragePath, null, false, multipartFiles);
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result<List<FileOperation>> saveFile(String relativeStoragePath, Date expiredDate, MultipartFile... multipartFiles){
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
	 * @param expiredDate 文件失效时间
	 * @param isPersistence 是否进行数据库持久化
	 * @param multipartFiles 要上传的附件数组
	 * @return 返回文件上传的数据库列表
	 * @throws ServiceException 
	 *  抛出文件保存中的逻辑异常
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Result<List<FileOperation>> saveFile(String relativeStoragePath, Date expiredDate,boolean isPersistence, MultipartFile... multipartFiles){
		if (multipartFiles == null  || multipartFiles.length == 0) {
			throw new ServiceException("上传文件不能为空");
		}
		
		if (StringUtils.isBlank(relativeStoragePath)) {
			  new ServiceException("未设置有效的设置文件上传路径！");
		}
		
		Result<List<FileOperation>> result = ResultHelper.buildSuccess();//返回结果文件
		//用于保存上传文件的列表
		List<FileOperation> operFiles = Lists.newArrayListWithCapacity(multipartFiles.length);
		List<FileOperation> md5FileList = Lists.newLinkedList();
		try {
			for (MultipartFile multipartFile: multipartFiles) {

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
						md5FileList.add(operFile);//存入MD5列表队列中，用于校对哪些文件是历史存在的文件，并非最新上传的
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
					if (isPersistence) { //持久化标识
						super.save(operFile);//保存文件到数据库，同时将保存的文件id回写
					}
					operFiles.add(operFile);
				}else{
					throw  new ServiceException("上传文件名为空！");
				}
			}
		} catch (Exception e) {
			//删除上传的文件,保证原子性，出现异常将上传的新文件清除掉，MD5的引用文件不可以清除，可能被其他资源共享uri
			for(FileOperation removeFileOper:operFiles){
				if(!md5FileList.contains(removeFileOper)){
					if(removeFileOper.getFile().exists()){
						removeFileOper.getFile().delete();//删除刚刚上传的文件
					}
				}
			}
			throw new ServiceException(e);
		}
		return result.setResult(operFiles);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result<List<FileOperation>> deleteFile(FileOperation... deleteFile){
		Result<List<FileOperation>> result = ResultHelper.buildSuccess();
		if (deleteFile == null || deleteFile.length == 0) {
			return ResultHelper.buildFailed("删除的文件为空，请检查文件的有效性");
		}
		String errorMsg = "";
		int deleteCount = 0;
		for (FileOperation fileOperation : deleteFile){
			//检查id是否有效
			Long fileId = fileOperation.getId();
			if (fileId == null) {
				errorMsg = "删除的资源ID不能为空";
			}
			
			FileOperation fileOperationDB = mapper.selectByPrimaryKey(fileOperation);
			
			if (fileOperationDB == null) {
				errorMsg = "删除文件不存在";
			}
			
			if (errorMsg.isEmpty()) {
				fileOperationDB.setDeleteFlag(DeleteFlag.DELETE.flag());
				fileOperationDB.setModifyBy(super.operateUserId());
				fileOperationDB.setModifyDate(new Date());
				int deleteData = mapper.deleteLogical(fileOperationDB);
				if (deleteData != 0) {
					logger.info("资源文件id为:",fileId,"被成功标记为删除.","资源名为",fileOperationDB.getOriginalFileName(),"资源URI为",fileOperationDB.getFileUri());
					deleteCount ++;
				}else{
					logger.warn("资源文件id为:",fileId,"标记删除没有成功,有可能已经不存在或者已经被标记为删除状态");
				}
			}else{
				throw new ServiceException(errorMsg);
			}
		}
		if (deleteCount == deleteFile.length) {
			result.setResultConfig(ResultBaseEnum.FAILED).formateMessage("成功删除{0}条记录，其中{1}条记录没有成功删除", deleteCount,(deleteFile.length-deleteCount));
		} else {
			result.formateMessage("成功删除{0}条记录", deleteFile.length);
		}
		return result;
	}

	@Override
	public Result<List<FileOperation>> queryFile(FileOperation... queryFile){
		Result<List<FileOperation>> result = ResultHelper.buildSuccess();
		if (queryFile == null || queryFile.length == 0) {
			return ResultHelper.buildFailed("删除的文件为空，请检查文件的有效性");
		} else {
			List<FileOperation> fileList = mapper.queryFiles(queryFile);
			result.setResult(fileList);
		}
		return result;
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
    protected String storagePath(String filename,String relativeStoragePath){
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
