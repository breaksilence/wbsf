package com.wbsf.core.service.impl;

import org.springframework.stereotype.Service;

import com.wbsf.core.mapper.FileOprationMapper;
import com.wbsf.core.persistence.FileOpration;

/**
 * 文件操作的基础支撑类，继承AbstractFileService，
 * 拥有数据库持久化的操作能力，同时AbstractFileService完整实现了FileOperationService接口
 * @author xiangzheng
 *
 */
@Service("fileOperationSupport")
public class FileOperationSupport<T extends FileOpration ,M extends FileOprationMapper<T>> extends AbstractFileService<T, M> {

}
