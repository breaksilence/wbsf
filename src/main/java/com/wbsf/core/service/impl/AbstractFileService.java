package com.wbsf.core.service.impl;

import com.wbsf.core.mapper.FileOprationMapper;
import com.wbsf.core.persistence.FileOpration;
import com.wbsf.core.service.FileOperationService;
/**
 * 继承了baseService拥有数据库持久化操作的功能，同时实现了部分FileOperationService接口
 * @author xiangzheng
 *
 */
public abstract class AbstractFileService<T extends FileOpration ,M extends FileOprationMapper<T>> extends BaseServiceSupport<T,M> implements FileOperationService {

}
