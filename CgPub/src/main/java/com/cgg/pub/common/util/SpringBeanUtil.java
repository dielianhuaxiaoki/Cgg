package com.cgg.pub.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * Description:
 * 工具类 - Spring
 * SpringBeanUtil.java Create on 2014-03-21 
 * @author held
 * @version 1.0
 * Copyright (c) 2013 held,Inc. All Rights Reserved.
 */

@Component
public class SpringBeanUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 根据Bean名称获取实例
	 * 
	 * @param name
	 *            Bean注册名称
	 * 
	 * @return bean实例
	 * 
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}
	/**
	 * 根据Class获取实例
	 * 
	 * @param
	 *            *Bean注册名称
	 * 
	 * @return bean实例
	 * 
	 * @throws BeansException
	 */
	public static Object getBean(Class cls) throws BeansException {
		return applicationContext.getBean(cls);
	}
}