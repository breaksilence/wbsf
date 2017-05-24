# Web Basic Service Framework Web基础服务平台

## 平台简介

基础服务平台为个人随手搭建的框架， 目前定位为基础的网络服务技术平台，对Spring、Spring Mvc、Mybatis、redis等多种开源技术的整合、集成，尽量做到清晰的模块划分，简单实用的框架。

## 集成开源框架

1. 集成mybatis √
2. 集成mapper3插件 √
3. 集成pagehelper √
4. 集成redis
5. 完成基础框架构建 SpringMvc+Spring+Mybatis+Mapper3+Pagehelper+Redis
6. 通用代码生成器:持久层 √
7. 构建通用的业务处理结果类  √
8. 编写单元测试 √
9. 防止重复提交
10. 文件上传下载

#内置功能

1. 全局异常管理,简单配置，可按需求进行详细配置
	com.wbsf.core.exception.ExceptionHandler 
2. 统一的结果辅助类，符合绝大多数结果传递
	com.wbsf.core.result.Result:建议通过ResultHelper构建
3. 单元测试基础类：JunitTestSupport
4. 实现国际化
5. 服务端数据校验提供国际化配置，服务端数据校验采用自定义form的形式，form bean需要集成entity，通过重新父类的方法，在该方法上进行注解校验
6. 内置日期格式转换 DateConverter 满足绝大多数日期类型的映射
7. ContextUtil是开发中可能常用到的类，该类提供了国际化与spring bean获取的常规静态方法
8. 以取巧的方式通过PropertyConfigurer，内附待援测试PropertyConfigurerTest。 实现ApplicationContextAware 在项目启动时spring对properties文件进行加载
9. 在服务段代码中进行国际化建议通过静态方法引用的形式：import static com.wbsf.core.spring.utils.ContextUtil.text;
10. 数据插入、参数服务端校、国际化都已提供单元测试，详见TestControllerTes、PropertyConfigurerTest
11. 自定义返回的结果编码、结果消息、结果对应的处理状态（成功or失败）建议通过实现ResultEnum接口且以枚举的形式定义，详细定义方式建议参考ResultConfig，这些消息都可以通过国际化的形式实现
12. 简单的方式重写org.mybatis.spring.SqlSessionFactoryBean，实现通配符扫描实体类包名

#下一步计划：
###### 提供分页的常规操作+单元测试
###### 验证项目依赖、通过工程依赖的形式构建其他实际项目
###### 分页使用demo
###### 防止重复提交
###### 文件上传下载

#启动项目

#方法1 通过tomcat插件启动>>>将依赖包放入到工程下命令为：：
	mvn dependency:copy-dependencies -DoutputDirectory=src/main/webapp/WEB-INF/lib  -DincludeScope=runtime
	
#方法2 直接通过maven-tomcat插件启动

## 预期内置功能-将在未来的后台管理系统中实现，该工程目前定位为提供开发最基础的技术平台

1.	用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.	机构管理：配置系统组织机构（公司、部门、小组），树结构展现，可随意调整上下级。
3.	区域管理：系统城市区域模型，如：国家、省市、地市、区县的维护。
4.	菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5.	角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6.	字典管理：对系统中经常使用的一些较为固定的数据进行维护，如：是否、男女、类别、级别等。
7.	操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
