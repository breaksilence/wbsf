package com.wbsf.core.mapper;

import java.util.List;

import com.wbsf.core.mapper.BaseMapper;
import com.wbsf.core.page.PageQuery;
import com.wbsf.core.persistence.FileInfo;

public interface FileMapper extends BaseMapper<FileInfo> {
	
	/**
	 * 分页demo，可以自己定义sql，此处为了明了，没有联合mapper工具进行单一实体查询
	 * @param pageQuery
	 * @return
	 */
	public List<FileInfo> pageQuery(PageQuery<FileInfo> pageQuery);
}