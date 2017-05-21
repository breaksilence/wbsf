package com.wbsf.core.enums;

/**
 * 排序枚举
 * @author hubery
 *
 */
public enum SortType {
	asc("asc"),desc("desc");
	private String sort ;
	private SortType(String sort){
		this.sort = sort;
	}
	
	/**
	 * 获取排序方式
	 * @return 返回枚举对应的排序方式 asc or desc
	 */
	public String sort(){
		return sort;
	}
	
}
