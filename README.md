# Web Basic Service Framework Web基础服务平台

## 平台简介

基础服务平台为个人随手搭建的框架， 目前定位为基础的网络服务技术平台，对Spring、Spring Mvc、Mybatis、redis等多种开源技术的整合、集成，尽量做到清晰的模块划分，简单实用的框架。
框架本身不提供前段框架、不提供管理界面，只提供一个最基础的系统框架，为前后端功能实现提供基础的服务架构。后期可能会通过依赖wbsf构建一个后台的信息管理系统。

## 集成开源框架

1.  集成mybatis √
2.  集成mapper3插件 √
3.  集成pagehelper √
4.  集成redis √
5.  完成基础框架构建 SpringMvc+Spring+Mybatis+Mapper3+Pagehelper+Redis √
6.  通用代码生成器:持久层 √
7.  构建通用的业务处理结果类  √
8.  编写单元测试 √

## 基础功能

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
13. 实现PageHelper分页的常规操作+单元测试使用实例

## 配置文件说明

##### config/init/jdbc.properties
    
    配置用户数据库信息
   
##### config/init/sysConfig.properties
    
    配置系统相关信息

##### config/init/redis.properties

    配置redis缓存信息
    
##### config/messages/locale

    本地国际化信息配置
    
##### config/messages/validator

    表单校验国际化配置
    
##### config/spring/applicationContext.xml

    项目spring加载xml的根文件，所有spring文件都通过该文件进行include
    
##### config/spring/spring-jdbc.xml  

    spring 数据库配置文件
    
##### config/spring/spring-mvc.xml

    spring mvc集成配置文件
    
##### config/spring/spring-redis.xml

    spring redis集成配置文件 
    
##### generator/config
    mybatis-generator代码生成器配置信息
 
## 开发强制性

    一、所有的业务模块开发请遵循如下规则：
    1、业务模块开代码归档于：com/xx项目标识/modules/实际业务
          如：com/demoproject/modules/user
    2、业务controller
        如：com/demoproject/modules/user/controller
    3、业务service
        如：com/demoproject/modules/user/service
    4、业务mapper
        如：com/demoproject/modules/user/mapper
    5、业务mapping 就是mapper对应的xml文件
        如：com/demoproject/modules/user/mapping
    二、通过配置工具com.wbsf.core.spring.utils.PropertyConfigurer加载文件需要在applicationContext进行注册，如下：
    <bean id="propertyConfigurer" class="com.wbsf.core.spring.utils.PropertyConfigurer">
       <property name="ignoreUnresolvablePlaceholders" value="true"/>
       <property name="ignoreResourceNotFound" value="true"/>
       <property name="locations">
           <list>
              <value>classpath:config/init/jdbc.properties</value>
              <value>classpath:config/init/sysConfig.properties</value>
           </list>
       </property>
    </bean>
    三、结果处理：无论是controller返回json数据还是service返回结果信息都要通过Result对象接收
 
#启动项目

#方法1 通过tomcat插件启动>>>将依赖包放入到工程下命令为：
	mvn dependency:copy-dependencies -DoutputDirectory=src/main/webapp/WEB-INF/lib  -DincludeScope=runtime
	
#方法2 直接通过maven-tomcat插件启动