package org.litespring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.util.ClassUtils;

public class DefaultBeanFactory implements BeanFactory,BeanDefinationRegister {
	
	private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
	
	public DefaultBeanFactory() {
		
	}
	public void registerBeanDefination(String beanId, BeanDefinition bd) {
		beanDefinitionMap.put(beanId, bd);
	}

	public BeanDefinition getBeanDefinition(String beanId) {
		return this.beanDefinitionMap.get(beanId);
	}

	public Object getBean(String beanId) throws BeanCreationException {
		BeanDefinition bd = this.beanDefinitionMap.get(beanId);
		if(bd == null) {
			throw new BeanCreationException("Bean Definition does not exist");
		}
		ClassLoader cl = ClassUtils.getDefaultClassLoader();
		String className = bd.getBeanClassName();
		Class<?> clazz;
		try {
			clazz = cl.loadClass(className);
			return clazz.newInstance();
		} catch (Exception e) {
			throw new BeanCreationException("create bean " + className + " faild");
		}
	}
}
