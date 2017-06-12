package com.wbsf.core.enums;

/**
 * 删除标识枚举枚举
 * @author hubery
 *
 */
public enum DeleteFlag {
	/** 文件正常 */
	NORMAL(1),
	/** 删除 */
	DELETE(0),
	/** 系统标识，禁止删除 */
	DELETE_FORBIDDEN(-1);
	private int deleteFlag ;
	private DeleteFlag(int deleteFlag){
		this.deleteFlag = deleteFlag;
	}
	
	/**
	 * 获取删除对应的值
	 */
	public int flag(){
		return deleteFlag;
	}
	
}
