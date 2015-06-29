/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spanbee.listeners;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @author sucheth.s
 */
public class SpringApplicationContext implements ApplicationContextAware
{
	private static final Logger LOGGER = Logger.getLogger(SpringApplicationContext.class);
	private static ApplicationContext CONTEXT;
	
	/**
	 * This method is called from within the ApplicationContext once it is done
	 * starting up, it will stick a reference to itself into this bean.
	 * 
	 * @param context
	 *            a reference to the ApplicationContext.
	 */
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException
	{
		LOGGER.info("Injecting the Bean" + context.getDisplayName());
		CONTEXT = context;
	}
	
	/**
	 * This is about the same as context.getBean("beanName"), except it has its
	 * own static handle to the Spring context, so calling this method
	 * statically will give access to the beans by name in the Spring
	 * application context. As in the context.getBean("beanName") call, the
	 * caller must cast to the appropriate target class. If the bean does not
	 * exist, then a Runtime error will be thrown.
	 * 
	 * @param beanName
	 *            the name of the bean to get.
	 * @return an Object reference to the named bean.
	 */
	public static Object getBean(String beanName)
	{
		LOGGER.info("Getting the Bean : " + beanName + " :: context : " + CONTEXT);
		return CONTEXT.getBean(beanName);
	}
	
}
