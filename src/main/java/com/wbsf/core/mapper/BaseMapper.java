package com.wbsf.core.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的MyMapper
 *
 * @author hubery
 * @since 2016-12-26 21:23
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
	
}
