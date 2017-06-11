package com.wbsf.core.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbsf.core.BaseMapper;
import com.wbsf.core.bean.UserSession;
import com.wbsf.core.config.SystemConfig;
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
	
	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;
	
	@Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }
	
	@Override
    public int save(T entity) {
        return mapper.insert(entity);
    }
	
	public int saveSelective(T entity) {
		return mapper.insertSelective(entity);
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
	
	protected Long operateUserId() {
		UserSession userSession = (UserSession) request.getSession().getAttribute(SystemConfig.SESSION_KEY.config());
		if(userSession != null){
			return userSession.operateUserId();
		}else{
			return SystemConfig.SYSTEM_USER_ID.configLong();
		}
	}
}
