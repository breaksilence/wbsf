package com.wbsf.core.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component("BeanDefineConfigue")
public class BeanDefineConfigue implements ApplicationListener<ContextRefreshedEvent> {
	
	private static final Logger logger = LoggerFactory.getLogger(BeanDefineConfigue.class);
	
	/**
	* 当一个ApplicationContext被初始化或刷新触发
	*/
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("--------------spring 初始化数据完成----------------");
		 if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")){ 
             //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。 
			 logger.info("--------------spring 初始化数据完成----------------");
        }  
	}
}
