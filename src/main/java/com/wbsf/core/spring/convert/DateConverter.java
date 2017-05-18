package com.wbsf.core.convert;

import java.net.BindException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

import com.wbsf.common.utils.DateUtils;

/**
 * Spring 日期转换器
 * @author hubery
 *
 */
public class DateConverter implements Converter<String, Date> {  
	private Logger logger = LogManager.getLogger(getClass());
	@Override  
	public Date convert(String source) {
		Date bindDate = DateUtils.parseDate(source);
		if(StringUtils.isNotBlank(source) && bindDate == null)
			logger.error("日期类型数据绑定异常，尝试将"+source+"转换为Date类型，但是没有成功!");
		return bindDate;
   }
}  