# Web Basic Service Framework Web基础服务平台

## 平台简介

基础服务平台为个人随手搭建的框架， 目前定位为基础的网络服务技术平台，对Spring、Spring Mvc、Mybatis、redis等多种开源技术的整合、集成，尽量做到清晰的模块划分，简单实用的框架。
框架本身不提供前段框架、不提供管理界面，只提供一个最基础的系统框架，为前后端功能实现提供基础的服务架构。后期可能会通过依赖wbsf构建一个后台的信息管理系统。

## 集成开源框架

1.  集成mybatis √
2.  集成mapper3插件 √
3.  集成pagehelper √
4.  集成redis √
5.  完成基础框架构建 SpringMvc+Spring+Mybatis+Mapper3+Pagehelper+Redis+spring-session √
6.  通用代码生成器:持久层 √
7.  构建通用的业务处理结果类  √
8.  编写单元测试 √
9.  实现spring-redis-seesion共享session √
10. 基于SpringMVC实现文件上传功能

## 基础功能

1. 全局异常管理,简单配置，可按需求进行详细配置
	com.wbsf.core.exception.ExceptionHandler 
2. 统一的结果辅助类，符合绝大多数结果传递
	com.wbsf.core.result.Result:建议通过ResultHelper构建
3. 单元测试基础类：JunitTestSupport，如果需要加载spring容器配置文件，继承此类即可，同时在单元测试执行钱进行初始化super.setUp();
4. 实现服务端国际化
5. 服务端数据校验提供国际化资源获取方式，服务端数据校验采用自定义form的形式，form bean需要集成对应模块的entity，通过重新父类的方法，在该方法上进行注解校验，可参考单元测试TestControllerTes
6. 内置日期格式转换 DateConverter 满足绝大多数日期类型的映射
7. ContextUtil是开发中可能常用到的类，该类提供了国际化与spring bean获取的常规静态方法
8. 以取巧的方式通过PropertyConfigurer，内附单元测试PropertyConfigurerTest。 实现ApplicationContextAware 在项目启动时spring对properties文件进行加载
9. 在服务段代码中进行国际化建议通过静态方法引用的形式：import static com.wbsf.core.spring.utils.ContextUtil.text;
10. 数据插入、参数服务端校校验、国际化都已提供单元测试，详见TestControllerTes、PropertyConfigurerTest
11. 自定义返回的结果编码、结果消息、结果对应的处理状态（成功or失败）建议通过实现ResultEnum接口且以枚举的形式定义，详细定义方式建议参考ResultConfig，这些消息都可以通过国际化的形式实现
12. 简单的方式重写org.mybatis.spring.SqlSessionFactoryBean，实现通配符扫描实体类包名
13. 实现PageHelper分页的常规操作+单元测试使用实例
14. 实现session缓存共享
15. 提供redis单机与集成配置，可以参考相关文件进行实际场景修改，目前配置已经经过测试可用
16. 实现基于接口的形式对文件进行操作，但要求数据库中存在对应的文件表
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
 
## 开发规范

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
    三、结果处理：无论是controller返回json数据还是service返回结果信息都要通过Result对象接收，通过ResultHelper构建Result实例，将以自定的result信息通过ResultEnum接口实现枚举，通过枚举配置，虽然方法中暴露了通过code和message初始化的方式
    四、分页查询的工具为PageQuery，提供了常规分页的所有信息，提供PageHeper分页拦截器的开启方法
    五、分页结果通过PageResult来接收，该类继承了ResultSupport，提供了结果的所有辅助方法，同时构建了分页的常规实用方法
    六、redis辅助工具为RedisSupport，该类继承了Spring的RedisTemplate,同时封装了对String\Set\Hash\List的常规操作方法，结合注解实用很方便
    七、不要在新增时间工具类，如果需要请扩展DateUtil方法即可。
    八、全局异常处理类为ExceptionHandler，可以修改该类的实现达到全局异常统一处理的效果。
    九、程序中的配置文件加载请采用PropertyConfigurer结合applicationContext的配置文件实用，不需要增加额外的properties文件解析类
    十、单表的crud操作不要新增额外的sql了，直接继承ServiceSupport接口，实现类继承ServiceSupportImpl即可完成基础的操作。
    十一、所有的数据库实体类都要继承BaseEntity，为后期统一扩展预留余地。
    十二、controller层表单进行服务端数据校验，请参考TestControllerTes中的示例
    
##内置ueditor

1、移除controller.jsp，由com.wbsf.core.controller.UeditorController.exec()来处理请求，修改配置文件ueditor.config.js→serverUrl: URL + "ueditor"
2、移除ueditor对json.jar依赖，改为fastjson处理
3、修改百度上传组件，默认通过spring 的方式上传方式
4、修正chrom浏览器上传图片窗口延迟问题
5、移除百度应用
6、移除谷歌地图
7、屏蔽搜索图片


#启动项目

#方法1 通过tomcat插件启动>>>将依赖包放入到工程下命令为：
	mvn dependency:copy-dependencies -DoutputDirectory=src/main/webapp/WEB-INF/lib  -DincludeScope=runtime
	
#方法2 直接通过maven-tomcat插件启动