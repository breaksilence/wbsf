package com.wbsf.core.page;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.wbsf.core.config.SystemConfig;
import com.wbsf.core.enums.SortType;

/**
 * 功能概要：分页工具帮助类 在Mapper层可以直接通过以PageQuery作为参数来进行分页查询
 * 
 * @author hubery
 *
 * @param <T>
 */
public class PageQuery<T>{

	/** 为了满足通过实体类作为参数进行分页查询 */
	private T vo;
	/** 查询的当前页码 */
	private int pageNum = 1;
	/** 每页展示的条数 */
	private int pageSize = SystemConfig.PAGE_SIZE_DEFUALT.configInt();
	/** 分页查询排序属性，可以通过对象属性直接排序 */
	private StringBuilder orderBy = new StringBuilder();
	/** pagehelper 分页用于设置分页查询是否计算总数 */
	private boolean isCount = true;
	/** 预期导航页数 */
	private int navigatePages = SystemConfig.PAGE_NAVIGATEPAGES_DEFUALT.configInt();
	/** 分页查询的参数 */
	private Map<String, Object> params = Maps.newHashMap();

	/**
	 * 获取当前查询的页码
	 * 
	 * @return 当前查询页码
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * 设置查询页码
	 * 
	 * @param pageNum
	 *            当前待查询页码
	 * @return 当前查询实例对象
	 */
	public PageQuery<T> setPageNum(int pageNum) {
		this.pageNum = pageNum;
		return this;
	}

	/**
	 * 每页展示的数据条数
	 * 
	 * @return 每页展示的数据条数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页条数
	 * 
	 * @param pageSize
	 *            设置每页展示的数据条数
	 * @return 返回PageQuery当前实例
	 */
	public PageQuery<T> setPageSize(int pageSize) {
		this.pageSize = pageSize != 0 ? pageSize : 10;
		return this;
	}

	/**
	 * 获取分页的参数
	 * 
	 * @return
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * 获取参数值
	 * 
	 * @param paramKey
	 * @return
	 */
	public Object getParamValue(String paramKey) {
		return params.get(paramKey);
	}

	/**
	 * 增加sql查询的扩展参数
	 * 
	 * @param key
	 *            参数的key
	 * @param value
	 *            参数的值
	 */
	public PageQuery<T> addParam(String key, Object value) {
		this.params.put(key, value);
		return this;
	}

	/**
	 * 设置分页查询的参数，注意会清空原有参数
	 * 
	 */
	public PageQuery<T> setParams(Map<String, Object> paramsMap) {
		if (paramsMap != null) {
			this.params.clear();
			this.params.putAll(paramsMap);
		}
		return this;
	}

	/**
	 * 获取排序
	 * 
	 * @return 排序相关字段信息
	 */
	public String getOrderBy() {
		return orderBy.toString();
	}

	/**
	 * 设置排序，会清空历史排序信息
	 * 
	 * @param orderBy
	 */
	public PageQuery<T> setOrderBy(String orderBy) {
		this.orderBy.reverse();
		this.orderBy.append(orderBy);
		return this;
	}

	/**
	 * 设置排序
	 * 
	 * @param orderBy
	 *            排序字段
	 * @param sortType
	 *            排序方式
	 * @return 分页查询实例
	 */
	public PageQuery<T> setOrderBy(String orderBy, SortType sortType) {
		this.orderBy.append(orderBy).append(" ").append(sortType.sort());
		return this;
	}

	/**
	 * 追加分页排序
	 * 
	 * @param orderBy
	 * @param sortType
	 * @return
	 */
	public PageQuery<T> appendOrder(String orderBy, SortType sortType) {
		return null;
	}

	/**
	 * 是否计算总数
	 * 
	 * @return true sql查询执行count false不执行count
	 */
	public boolean isCount() {
		return isCount;
	}

	/**
	 * 设置查询是否计算总数
	 * 
	 * @param isCount
	 */
	public PageQuery<T> setCount(boolean isCount) {
		this.isCount = isCount;
		return this;
	}

	/**
	 * 追加分页
	 * 
	 * @param key
	 * @param value
	 */
	public void putParam(String key, Object value) {
		this.params.put(key, value);
	}

	/**
	 * 获取分页导航预期页码数
	 * 
	 * @return
	 */
	public int getNavigatePages() {
		return navigatePages;
	}

	/**
	 * 设置分页导航预期页码
	 * 
	 * @param navigatePages
	 *            导航页码数
	 */
	public PageQuery<T> setNavigatePages(int navigatePages) {
		this.navigatePages = navigatePages;
		return this;
	}

	/**
	 * 设置分页查询的实体参数
	 * 
	 * @return
	 */
	public T getVo() {
		return vo;
	}

	/**
	 * 设置分页查询的实体参数
	 * 
	 * @param vo
	 *            实体参数
	 */
	public PageQuery<T> setVo(T vo) {
		this.vo = vo;
		return this;
	}

	/**
	 * 开始分页线程,建议调用此方法时进行try处理，在出现异常的情况下会自动释放线程资源
	 */
	public Page<T> startPage() {
		return PageHelper.startPage(pageNum, pageSize, isCount);
	}
	
    /**
     * 清空分页数据
     */
	public void clearPage() {
		PageHelper.clearPage();
	}
	
	/**
	 * 可以通过PageQuery直接构建PageResult
	 * @param queryResult
	 * @return
	 */
	public PageResult<T> buildResult(List<T> queryResult){
		clearPage();
		return new PageResult<T>(queryResult,navigatePages);
	}
}
