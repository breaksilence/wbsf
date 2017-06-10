package com.wbsf.core.bean;

/**
 * 用户用户登陆的用户实例需要实现该接口
 * @author hubery
 *
 */
public interface UserSession {
	/**
	 * 获取登陆用户的session中的用户id，该接口会被用于核心代码获取用户id，因为该框架为基础框架，用户登陆session
	 * 的用户实例没有实现，同时也为了扩展性，决定用户登陆的entity要实现该接口
	 */
	Long operateUserId();
	
}
