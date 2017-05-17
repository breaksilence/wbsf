package com.wbsf.core.convert;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.wbsf.common.utils.DateUtils;

/**
 * Spring 日期转换器
 * @author hubery
 *
 */
public class DateConverter implements Converter<String, Date> {  
	@Override  
	public Date convert(String source) {
		return DateUtils.parseDate(source);
   }
}  