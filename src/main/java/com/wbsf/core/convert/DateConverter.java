package com.wbsf.core.convert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;


public class DateConverter implements Converter<String, Date> {  
	// 支持转换的多种日期格式,可增加时间格式
	private final DateFormat[] dfs = { 
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new SimpleDateFormat("yyyyMMdd"),
			new SimpleDateFormat("yyyy/MM/dd"), 
			new SimpleDateFormat("yyyy年MM月dd日"),
			new SimpleDateFormat("MM/dd/yy"),
			new SimpleDateFormat("yyyy.MM.dd")
			};
	
	@Override  
	public Date convert(String source) {
		for (int i = 0; i < dfs.length; i++) {// 遍历日期支持格式,进行转换
			try {
				return dfs[i].parse(source);
			} catch (Exception e) {
				continue;
			}
		}
		return null;
   }
}  