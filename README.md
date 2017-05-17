# Web Basic Service Framework Web基础服务平台

## 平台简介

wbsf:个人随手搭建的框架， 目前定位为基础的网络服务技术平台，对Spring、Spring Mvc、Mybatis、redis等多种开源技术的整合、集成，尽量做到清晰的模块划分，简单实用的框架。

## 集成开源框架

1. 集成mybatis √
2. 集成mapper3插件 √
3. 集成pagehelper √
4. 集成redis
5. 完成基础框架构建 SpringMvc+Spring+Mybatis+Mapper3+Pagehelper+Redis
6. 通用代码生成器:持久层 √
7. 构建通用的业务处理结果类  √
8. 编写单元测试 √

#内置功能

1. 全局异常管理,简单配置，可按需求进行详细配置
	com.wbsf.core.exception.ExceptionHandler 
2. 统一的结果辅助类，符合绝大多数结果传递
	com.wbsf.core.result.Result:建议通过ResultHelper构建
3. 单元测试基础类：JunitTestSupport

#启动项目

#方法1 将依赖包放入到工程下，通过tomcat插件启动：
	命令为：mvn dependency:copy-dependencies -DoutputDirectory=src/main/webapp/WEB-INF/lib  -DincludeScope=runtime
	
#方法2 直接通过maven-tomcat插件启动

## 预期内置功能

1.	用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.	机构管理：配置系统组织机构（公司、部门、小组），树结构展现，可随意调整上下级。
3.	区域管理：系统城市区域模型，如：国家、省市、地市、区县的维护。
4.	菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5.	角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6.	字典管理：对系统中经常使用的一些较为固定的数据进行维护，如：是否、男女、类别、级别等。
7.	操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
