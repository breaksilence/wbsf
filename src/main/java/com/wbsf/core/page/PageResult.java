package com.wbsf.core.page;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wbsf.core.result.impl.ResultSupport;

/**
 * 
 * 分页结果类，分页信息采用的是pagehelper中的pageInfo，包含了分页的绝大部分信息，本项目不在独立封装
 * 
 * @author hubery
 *
 * @param <T>
 *            结果泛型
 */
public class PageResult<T> extends ResultSupport<PageInfo<T>> {
	
	/** 默认分页导航展示的页数 */
	private static final int DEFUALT_NAVIGATE_PAGES = 8;

	public PageResult() {
		super();
	}

	/**
	 * @param queryResult
	 *            查询结果
	 */
	public PageResult(List<T> queryResult) {
		initPage(queryResult);
	}

	/**
	 * @see <code>initpage</code>
	 * @param queryResult
	 *            查询结果
	 * @param navigatePages
	 *            页码数量
	 */
	public PageResult(List<T> queryResult, int navigatePages) {
		initPage(queryResult, navigatePages);
	}

	/**
	 * 初始化分页结果
	 * 
	 * @param queryResult
	 * @return
	 */
	public PageResult<T> initPage(List<T> queryResult) {
		return initPage(queryResult, DEFUALT_NAVIGATE_PAGES);
	}

	/**
	 * 初始化分页结果
	 * 
	 * @param queryResult
	 *            数据库查询结果
	 * @param navigatePages
	 *            实际导航页数
	 * @return
	 */
	public PageResult<T> initPage(List<T> queryResult, int navigatePages) {
		if (navigatePages <= 0)
			navigatePages = DEFUALT_NAVIGATE_PAGES;
		if (queryResult == null || queryResult.isEmpty()) {
			super.setSuccess(false);
		}
		this.result = new PageInfo<>(queryResult, navigatePages);
		return this;
	}

	/**
	 * 获取查询返回的list结果集
	 * 
	 * @return 获取当前分页的查询结果
	 */
	public List<T> getQueryList() {
		return this.result.getList();
	}
}
