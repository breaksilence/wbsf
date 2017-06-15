package com.wbsf.core.service;

import java.util.List;

import com.wbsf.core.exception.ServiceException;

/**
 * 提供基础的服务层方法，主要提供单表的增/删/改/查的基础操作
 * @author hubery
 *
 * @param <T>
 */
public interface BaseService<T>{
	
	/**
	 * 根据数据库实例的主键查询实体对象
	 * @param key 数据库主键
	 * @return T 
	 */
	T selectByKey(Object key);
	
	/**
	 * 根据实体类对象保存实体，注意主键为必要条件
	 * @param entity
	 * @return int 返回处理后的影响数据
	 */
    int save(T entity);

    /**
     * 根据主键删除数据库实体
     * @param key
     * @return 返回删除影响的数据
     */
    int delete(Object key);

    /**
     * 更新实体类所有属性
     * @param entity
     * @return 返回影响的数据量
     */
    int updateAll(T entity);

    /**
     * 更新实体类中不为空的属性到数据库中
     * @param entity
     * @return
     */
    int updateNotNull(T entity);

    /**
     * 查询列表
     * @param example
     * @return
     */
    List<T> selectByExample(Object example);
    
    /**
     * 抛出业务异常
     * @return 业务系统异常
     */
    ServiceException throwServiceExcepiton(String message, Throwable cause);
}
