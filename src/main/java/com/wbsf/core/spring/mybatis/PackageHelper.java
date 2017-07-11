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

import java.io.IOException;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Sets;

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
		
		String[] typeAliasPackageArray = StringUtils.tokenizeToStringArray(typeAliasesPackage, ",; \t\n");
		
		Set<String> resourcePath = Sets.newHashSet();
		for (String aliasesPackage : typeAliasPackageArray) {
			if (!aliasesPackage.contains("*")) {
				resourcePath.add(aliasesPackage);
			} else {
				String pkg = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
						+ ClassUtils.convertClassNameToResourcePath(aliasesPackage) + "/*.class";
				Resource[] resources;
				try {
					resources = resolver.getResources(pkg);
					if (resources != null && resources.length > 0) {
						MetadataReader metadataReader;
						for (Resource resource : resources) {
							if (resource.isReadable()) {
								metadataReader = metadataReaderFactory.getMetadataReader(resource);
								resourcePath.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		
		return resourcePath.toString().substring(1, resourcePath.toString().length()-1);
	}
}
