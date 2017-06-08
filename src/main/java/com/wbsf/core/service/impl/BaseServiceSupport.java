package com.wbsf.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbsf.core.mapper.BaseMapper;
import com.wbsf.core.service.BaseService;

/**
 * 基础服务实现类，该类提供了数据库单表操作的基础方法
 * @author xiangzheng
 *
 * @param <T>
 * @param <M>
 */
@Service("baseService")
public abstract class BaseServiceSupport<T,M extends BaseMapper<T>> implements BaseService<T>{
	
	@Autowired
	protected M mapper;
	
	@Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }
	
	@Override
    public int save(T entity) {
        return mapper.insert(entity);
    }

	@Override
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

	@Override
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

	@Override
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

	@Override
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }
}
