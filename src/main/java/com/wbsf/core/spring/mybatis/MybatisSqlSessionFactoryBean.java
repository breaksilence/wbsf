package com.wbsf.core.spring.mybatis;

import static org.springframework.util.StringUtils.hasLength;

import org.mybatis.spring.SqlSessionFactoryBean;

/**
 * @ClassName: TQSqlSessionFactoryBean
 * @Description: mybatis自动扫描别名路径（新增通配符匹配功能）
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年12月9日 上午9:36:23
 */
/**
 * <p>为了实现mybatsi支持通配符的包名扫描</p>
 * <p>重写org.mybatis.spring.SqlSessionFactoryBean</p>
 * @author hubery
 *
 */
public class MybatisSqlSessionFactoryBean extends SqlSessionFactoryBean {
	
	@Override
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		if (hasLength(typeAliasesPackage) && typeAliasesPackage.contains("*")) 
				typeAliasesPackage = PackageHelper.convertTypeAliasesPackage(typeAliasesPackage);
		super.setTypeAliasesPackage(typeAliasesPackage);
	}

	
}
