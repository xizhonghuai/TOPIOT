/**
 * 
 */
package com.application;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author XIZHONGHUAI
 *
 */
public class SpringBeanUtils implements BeanFactoryAware {

	private static BeanFactory beanFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org.
	 * springframework.beans.factory.BeanFactory)
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		SpringBeanUtils.beanFactory = beanFactory;
	}

	/**
	 * 根据bean的名称获取相应类型的对象
	 *
	 * @param id
	 * 
	 * @return Object类型的对象
	 */
	public static Object getBean(String id) {
		return beanFactory.getBean(id);
	}

}
