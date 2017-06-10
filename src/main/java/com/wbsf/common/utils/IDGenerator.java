package com.wbsf.common.utils;

import java.util.UUID;

/**
 * id生成器
 * @author hubery
 *
 */
public class IDGenerator {

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
