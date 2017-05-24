/**
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.wbsf.core.spring.mybatis;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

/**
 * 包扫描辅助类,借鉴了mybatis-plus的写法
 * @author hubery
 *
 */
public class PackageHelper {
	/**
	 * 类别名包路径
	 * @param typeAliasesPackage
	 * @return 类别名包路径
	 */
	public static String convertTypeAliasesPackage(String typeAliasesPackage) {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
		String pkg = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				+ ClassUtils.convertClassNameToResourcePath(typeAliasesPackage) + "/*.class";
		/*
		 * 将加载多个绝对匹配的所有Resource
		 * 将首先通过ClassLoader.getResource("META-INF")加载非模式路径部分，然后进行遍历模式匹配，排除重复包路径
		 */
		try {
			StringBuilder resourcePathStringBuffer = new StringBuilder();
			Resource[] resources = resolver.getResources(pkg);
			if (resources != null && resources.length > 0) {
				MetadataReader metadataReader;
				for (Resource resource : resources) {
					if (resource.isReadable()) {
						metadataReader = metadataReaderFactory.getMetadataReader(resource);
						if(resourcePathStringBuffer.length() != 0){
							resourcePathStringBuffer.append(",");
						}
						resourcePathStringBuffer.append(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
					}
				}
			}
			if (resourcePathStringBuffer.toString().trim().length()>0) {
				return resourcePathStringBuffer.toString();
			} else {
				throw new RuntimeException("not find typeAliasesPackage:" + pkg);
			}
		} catch (Exception e) {
			throw new RuntimeException("not find typeAliasesPackage:" + pkg, e);
		}
	}

}
