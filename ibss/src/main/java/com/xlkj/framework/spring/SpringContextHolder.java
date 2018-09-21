package com.xlkj.framework.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时间从中取出ApplicaitonContext.
 * 
 * 
 */
public class SpringContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

	/**
	 * 实现ApplicationContextAware接口的context注入函数, 并保存上下文到静态变量中
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextHolder.applicationContext = applicationContext;
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋需要的对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();

		return (T) applicationContext.getBean(name);
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋变量需要的对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		return (T) applicationContext.getBeansOfType(clazz);
	}

	private static void checkApplicationContext() {
		if (applicationContext == null) {
			logger.error("applicaitonContext注册失败");
			throw new IllegalStateException("applicaitonContext注册失败,请在applicationContext.xml中定义SpringContextHolder");
		}
	}
}
