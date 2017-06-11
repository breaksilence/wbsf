package com.wbsf.core.enums;

/**
 * 删除标识枚举枚举
 * @author hubery
 *
 */
public enum DeleteFlag {
	NORMAL(1),DELETE(0),DELETE_FORBIDDEN(-1);
	private int deleteFlag ;
	private DeleteFlag(int deleteFlag){
		this.deleteFlag = deleteFlag;
	}
	
	/**
	 * 获取枚举对应的值
	 */
	public int flag(){
		return deleteFlag;
	}
	
}
